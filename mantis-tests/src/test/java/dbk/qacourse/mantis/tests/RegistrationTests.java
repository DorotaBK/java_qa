package dbk.qacourse.mantis.tests;

import dbk.qacourse.mantis.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        app.registration().start("user1", "user1@localhost.localdomain");
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    }

    @AfterMethod (alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}