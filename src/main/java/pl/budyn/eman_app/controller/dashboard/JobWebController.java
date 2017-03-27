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
import pl.budyn.eman_app.model.entity.Job;
import pl.budyn.eman_app.model.repository.JobRepository;

import javax.validation.Valid;

/**
 * Created by hlibe on 20.01.2017.
 */
@Controller
@RequestMapping("dashboard/job")
public class JobWebController {

    private static final Log logger = LogFactory.getLog(JobWebController.class);

    private final JobRepository jobRepository;

    @Autowired
    public JobWebController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping
    public String getJob(Model model, Job job){
       model.addAttribute("jobs", jobRepository.findAll());
       model.addAttribute("viewName", "dashboard/job");
       return "dashboard/dashboard";
    }

    @PostMapping("/add")
    public String addJob(@Valid Job job, BindingResult bindingResult){
        System.out.println("Hi");
        if(bindingResult.hasErrors()){
            logger.info("Added or edited unsuccessfully: " + job.toString());
            return "redirect:/dashboard/job";
        }
        logger.info("Added: " + job.toString());
        jobRepository.save(job);
        logger.info("Added or editied successfully: " + job.toString());
        return "redirect:/dashboard/job";
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id){
        logger.info("Deleted: " + jobRepository.getOne(id).toString());
        jobRepository.delete(id);
        return "redirect:/dashboard/job";
    }
}
