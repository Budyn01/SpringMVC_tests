import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.budyn.eman_app.EManApplication;
import pl.budyn.eman_app.model.service.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

/**
 * Created by hlibe on 05.03.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(EManApplication.class)
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    private GreenMail smtpServer;

    @Before
    public void setUp() throws Exception {
        smtpServer = new GreenMail(new ServerSetup(25, null, "smtp"));
        smtpServer.start();
    }
    @After
    public void tearDown() throws Exception {
        smtpServer.stop();
    }

    @Test
    public void shouldSendMail() throws Exception {
        String recipient = "name@domain.com";
        String message = "Test message";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Test Subject");
        simpleMailMessage.setFrom("test@test.pl");
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setText(message);

        mailService.prepareAndSend(simpleMailMessage);
        assertReceivedMessageContains(message);
    }

    public void assertReceivedMessageContains(String expected) throws IOException, MessagingException {
        MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();
        Assert.assertEquals(1, receivedMessages.length);
        String content = (String) receivedMessages[0].getContent();
        Assert.assertTrue(content.contains(expected));
    }

}
