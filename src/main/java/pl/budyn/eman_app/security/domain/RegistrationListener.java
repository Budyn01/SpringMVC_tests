package pl.budyn.eman_app.security.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import pl.budyn.eman_app.model.service.MailService;
import pl.budyn.eman_app.security.domain.entity.User;
import pl.budyn.eman_app.security.service.user.UserService;

import javax.persistence.Column;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by hlibe on 07.03.2017.
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final MailService mailService;
    private final UserService userService;

    @Autowired
    public RegistrationListener(MailService mailService, UserService userService) {
        this.mailService = mailService;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }
    private void confirmRegistration(OnRegistrationCompleteEvent event){
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "http://localhost:8080/registrationConfirm?token=" + token;
        String message = "Complete registration by clicking: " + confirmationUrl;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("register@app.com");
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);

        mailService.prepareAndSend(email);
    }
}
