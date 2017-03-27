package pl.budyn.eman_app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.budyn.eman_app.model.entity.Job;

import java.util.Optional;

/**
 * Created by hlibe on 29.12.2016.
 */
public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByName(String name);
}
