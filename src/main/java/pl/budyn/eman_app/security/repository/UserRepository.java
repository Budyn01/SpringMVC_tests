package pl.budyn.eman_app.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.budyn.eman_app.security.domain.entity.User;

import java.util.Optional;

/**
 * Created by hlibe on 06.02.2017.
 */
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findOneByEmail(String email);
}
