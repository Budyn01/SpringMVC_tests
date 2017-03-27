package pl.budyn.eman_app.security.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * Created by hlibe on 06.02.2017.
 */
@Controller
public class LoginWebController {

    private static final Log logger = LogFactory.getLog(LoginWebController.class);

    @GetMapping("/login")
    public String getLoginPage(Model model, @RequestParam Optional<String> error){
        model.addAttribute("error", error);
        return "login";
    }
    @GetMapping("/access_denied")
    public String getAccessDenied(){
        return "access_denied";
    }

}
