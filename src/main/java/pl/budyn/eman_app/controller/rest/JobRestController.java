package pl.budyn.eman_app.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.budyn.eman_app.model.entity.Job;
import pl.budyn.eman_app.model.repository.JobRepository;

import java.util.Collection;

/**
 * Created by hlibe on 29.12.2016.
 */
@RestController
@RequestMapping("/resources/job")
public class JobRestController {

    private final JobRepository jobRepository;

    @Autowired
    public JobRestController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping
    Collection<Job> getJobs(){
        return jobRepository.findAll();
    }

    @GetMapping("/{id}")
    Job getJob(@PathVariable Long id){
        return jobRepository.findOne(id);
    }

}
