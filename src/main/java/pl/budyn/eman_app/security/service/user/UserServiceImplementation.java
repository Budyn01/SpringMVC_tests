package pl.budyn.eman_app.security.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.budyn.eman_app.model.entity.Photo;
import pl.budyn.eman_app.model.repository.PhotoRepository;
import pl.budyn.eman_app.security.domain.entity.User;
import pl.budyn.eman_app.security.domain.UserCreateForm;
import pl.budyn.eman_app.security.domain.entity.VerificationToken;
import pl.budyn.eman_app.security.repository.UserRepository;
import pl.budyn.eman_app.security.repository.VerificationTokenRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by hlibe on 06.02.2017.
 */
@Service
public class UserServiceImplementation implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    private final UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;
    private final PhotoRepository photoRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, VerificationTokenRepository tokenRepository, PhotoRepository photoRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.photoRepository = photoRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll(new Sort("email"));
    }

    @Override
    public User createUser(UserCreateForm form) {
        User user = new User();
        user.setName(form.getName());
        user.setSurname(form.getSurname());
        user.setEmail(form.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setAuthorities(form.getAuthorities());
        User newUser = userRepository.save(user);
        logger.info("User created: " + newUser.toString());
        return newUser;
    }

    @Override
    public void createVerificationToken(User user, String verificationToken) {
        VerificationToken myToken = new VerificationToken(user, verificationToken);
        logger.info("VerificationToken created: " + myToken.toString());
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return tokenRepository.findByToken(verificationToken);
    }

    @Override
    public User enableUser(User user) {
        user.setEnable(true);
        return userRepository.save(user);
    }

    @Override
    public User disableUser(User user) {
        user.setEnable(false);
        return userRepository.save(user);
    }

    @Override
    public Collection<Photo> getUserPhotos(User user) {
        return photoRepository.findByUser(user);
    }

    @Override
    public User changePassword(User user, String newPassword) {
        user.setPasswordHash(User.hashPassword(newPassword));
        return userRepository.save(user);
    }



}
