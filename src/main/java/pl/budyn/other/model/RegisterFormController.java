package pl.budyn.other.model;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.budyn.other.users.RegisterUserForm;

import javax.validation.Valid;


/**
 * Created by hlibe on 17.12.2016.
 */
@Controller
public class RegisterFormController {

    @GetMapping("/register")
    public String showForm(RegisterUserForm registerUserForm){
        return "jqa";
    }
    @PostMapping("/register")
    public String checkRegisterUserForm(@Valid RegisterUserForm registerUserForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registerUserForm";
        }
        System.out.println("UserEntity registered sucefully: " + registerUserForm.toString());
        return "jqa";
    }
}
