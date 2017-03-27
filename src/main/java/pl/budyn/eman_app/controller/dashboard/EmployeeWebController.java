package pl.budyn.eman_app.controller.dashboard;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.budyn.eman_app.model.entity.Employee;
import pl.budyn.eman_app.model.repository.EmployeeRepository;
import pl.budyn.eman_app.model.repository.JobRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hlibe on 29.12.2016.
 */
@Controller
@RequestMapping("/dashboard/employee")
public class EmployeeWebController {

    private static final Log logger = LogFactory.getLog(EmployeeWebController.class);

    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;

    @Autowired
    public EmployeeWebController(EmployeeRepository employeeRepository, JobRepository jobRepository) {
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
    }

    @GetMapping
    public String showForm(Model model, Employee employee){
        Map jobMap = new HashMap<Long, String>();
        jobRepository.findAll().forEach((x) -> jobMap.put(x.getId(), x.getName()));
        model.addAttribute("jobs", jobRepository.findAll());
        model.addAttribute("jobsMap", jobMap);
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("viewName", "dashboard/employee");
        return "dashboard/dashboard";
    }
    @PostMapping("/add")
    public String addEmployee(@Valid Employee employee, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            logger.info("Added or edited unsuccessfully: " + employee.toString());
            return "redirect:/dashboard/employee ";
        }
        employeeRepository.save(employee);
        logger.info("Added or editied successfully: " + employee.toString());
        return "redirect:/dashboard/employee";
    }
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        logger.info("Deleted: " + employeeRepository.getOne(id).toString());
        employeeRepository.delete(id);
        return "redirect:/dashboard/employee";
    }
}
