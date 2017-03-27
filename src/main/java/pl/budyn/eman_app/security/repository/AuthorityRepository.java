package pl.budyn.eman_app.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.budyn.eman_app.security.domain.entity.Authority;

/**
 * Created by hlibe on 04.03.2017.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
