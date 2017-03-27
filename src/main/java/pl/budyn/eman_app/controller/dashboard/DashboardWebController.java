package pl.budyn.eman_app.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hlibe on 20.01.2017.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardWebController {

    @GetMapping
    public String getDashboard(){
        return "redirect:/dashboard/employee";
    }

}
