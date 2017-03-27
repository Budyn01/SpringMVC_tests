package pl.budyn.eman_app.security.service.user;

import pl.budyn.eman_app.model.entity.Photo;
import pl.budyn.eman_app.security.domain.entity.User;
import pl.budyn.eman_app.security.domain.UserCreateForm;
import pl.budyn.eman_app.security.domain.entity.VerificationToken;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by hlibe on 06.02.2017.
 */
public interface UserService {

    Optional<User> getUserById(long id);
    Optional<User> getUserByEmail(String email);
    Collection<User> getAllUsers();

    User createUser(UserCreateForm form);

    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String token);

    User enableUser(User user);
    User disableUser(User user);
    User changePassword(User user, String newPassword);

    Collection<Photo> getUserPhotos(User user);

}
