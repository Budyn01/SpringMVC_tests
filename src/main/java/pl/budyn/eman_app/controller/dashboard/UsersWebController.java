package pl.budyn.eman_app.controller.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.budyn.eman_app.model.entity.Job;
import pl.budyn.eman_app.security.repository.UserRepository;

/**
 * Created by hlibe on 09.02.2017.
 */
@Controller
@RequestMapping("dashboard/users")
public class UsersWebController {

    private final UserRepository userRepository;

    @Autowired
    public UsersWebController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getJob(Model model){
        model.addAttribute("viewName", "dashboard/users");
        model.addAttribute("users", userRepository.findAll());
        return "dashboard/dashboard";
    }

}
