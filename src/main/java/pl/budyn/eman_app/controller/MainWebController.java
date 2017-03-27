package pl.budyn.eman_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by hlibe on 30.12.2016.
 */
@Controller
public class MainWebController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}