package dbk.qacourse.mantis.tests;

import dbk.qacourse.mantis.model.MailMessage;
import dbk.qacourse.mantis.model.UserData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordResetTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testPasswordReset() throws IOException, MessagingException {

        List<UserData> users = app.db().users();
        UserData user = null;
        for (UserData u : users) {
            if (!u.getUsername().equals("administrator")) {
                user = u;
                break;
            }
        }
        String newPassword = "newpassword";
        app.navigate().loginAs(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.userAccount().resetPasswordForUser(user.getUsername());

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
        app.userAccount().confirmNewPassword(confirmationLink, newPassword);

        assertTrue(app.newSession().login(user.getUsername(), newPassword));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}