package pl.budyn.eman_app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.budyn.eman_app.model.entity.Employee;

import java.util.Optional;

/**
 * Created by hlibe on 29.12.2016.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByName(String name);
    Optional<Employee> findBySurname(String surname);
}
