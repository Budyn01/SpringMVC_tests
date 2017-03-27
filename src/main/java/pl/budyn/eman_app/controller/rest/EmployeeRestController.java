package pl.budyn.eman_app.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.budyn.eman_app.model.entity.Employee;
import pl.budyn.eman_app.model.repository.EmployeeRepository;

import java.util.Collection;

/**
 * Created by hlibe on 29.12.2016.
 */
@RestController
@RequestMapping("/resources/employee")
public class EmployeeRestController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRestController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    Collection<Employee> readEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    Employee getEmployee(@PathVariable Long id){
            return employeeRepository.findOne(id);
    }

}
