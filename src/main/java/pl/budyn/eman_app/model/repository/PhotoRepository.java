package pl.budyn.eman_app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.budyn.eman_app.model.entity.Photo;
import pl.budyn.eman_app.security.domain.entity.User;

import java.util.List;

/**
 * Created by hlibe on 14.03.2017.
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByUser(User user);
}
