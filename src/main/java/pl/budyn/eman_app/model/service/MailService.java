package pl.budyn.eman_app.model.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * Created by hlibe on 05.03.2017.
 */
@Service
public class MailService {

    private static final Log logger = LogFactory.getLog(MailService.class);


    private JavaMailSender mailSender;


    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void prepareAndSend(SimpleMailMessage simpleMailMessage) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(simpleMailMessage.getFrom());
            messageHelper.setTo(simpleMailMessage.getTo());
            messageHelper.setSubject(simpleMailMessage.getSubject());
            messageHelper.setText(simpleMailMessage.getText());
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            logger.info("Error while sending email!");
        }
    }
}
