package pl.budyn.eman_app.security.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.budyn.eman_app.security.domain.UserCreateForm;
import pl.budyn.eman_app.security.service.user.UserService;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.regex.Matcher;

/**
 * Created by hlibe on 06.02.2017.
 */
@Component
public class UserCreateFormValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserCreateFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCreateForm form = (UserCreateForm) target;
        validatePasswords(errors, form);
        validateEmail(errors, form);
    }

    private void validatePasswords(Errors errors, UserCreateForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
        errors.getAllErrors().forEach(System.out::println);
    }

    private void validateEmail(Errors errors, UserCreateForm form) {
        try {
            InternetAddress email = new InternetAddress(form.getEmail());
            email.validate();
        } catch (AddressException e) {
            errors.reject("email.incorrect", "Email is incorrect");
        }
        if (userService.getUserByEmail(form.getEmail()).isPresent()) {
            errors.reject("email.exists", "User with this email already exists");
        }
        errors.getAllErrors().forEach(System.out::println);
    }
}
