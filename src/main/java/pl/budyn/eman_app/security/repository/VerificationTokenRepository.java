package pl.budyn.eman_app.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.budyn.eman_app.security.domain.entity.User;
import pl.budyn.eman_app.security.domain.entity.VerificationToken;

/**
 * Created by hlibe on 05.03.2017.
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);
}
