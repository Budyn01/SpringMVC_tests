package pl.budyn.eman_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hlibe on 07.03.2017.
 */
@Controller
@RequestMapping("/error")
public class ErrorWebController {

    @GetMapping
    public String getError(Model model){
        return "error";
    }
}

