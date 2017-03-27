package pl.budyn.eman_app.security.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.budyn.eman_app.security.domain.OnRegistrationCompleteEvent;
import pl.budyn.eman_app.security.domain.UserCreateForm;
import pl.budyn.eman_app.security.domain.entity.User;
import pl.budyn.eman_app.security.domain.entity.VerificationToken;
import pl.budyn.eman_app.security.service.user.UserService;

import java.util.Calendar;

/**
 * Created by hlibe on 04.03.2017.
 */
@Controller
public class RegisterUserWebController {

    private static final Log logger = LogFactory.getLog(RegisterUserWebController.class);

    private final UserService userService;
    private final Validator validator;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public RegisterUserWebController(UserService userService, @Qualifier("userCreateFormValidator") Validator validator, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.validator = validator;
        this.eventPublisher = eventPublisher;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.setValidator(validator);
    }

    @ModelAttribute("userCreateForm")
    public UserCreateForm createForm(){
        return new UserCreateForm();
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userCreateForm") @Validated UserCreateForm userCreateForm,
                               BindingResult bindingResult,
                               Model model,
                               WebRequest request){
        logger.info("Get userCreateForm: " + userCreateForm.toString());
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(logger::info);
            return "user/register";
        }
        User user = userService.createUser(userCreateForm);
        try{
            String appUrl = request.getContextPath();
            logger.info("ContextPath: " + appUrl);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
        } catch (Exception e) {
            logger.info("Error in eventPublisher");
            return "user/register";
        }
        model.addAttribute("title", "Registration complete");
        model.addAttribute("information", "Congratulation, registration complete. Check your email to enable your account!");
        return "info";
    }

    @GetMapping("/registrationConfirm")
    public String confirmRegistration(Model model, @RequestParam("token") String token){
        VerificationToken verificationToken = userService.getVerificationToken(token);

        if(verificationToken == null){
            model.addAttribute("error", "Token not found!");
            return "redirect:/error";
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0){
           model.addAttribute("error", "Token expired");
           return "redirect:/error";
        }
        userService.enableUser(user);
        logger.info("Enabling:" + user.getEmail());
        model.addAttribute("title", "Account enable");
        model.addAttribute("information", "Account enabled and ready to start your adventure!");
        return "info";
    }

}
