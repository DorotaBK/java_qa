package dbk.qacourse.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserAccountHelper extends HelperBase {

    WebDriverWait wait = new WebDriverWait(wd, 5);

    public UserAccountHelper(ApplicationManager app) {
        super(app);
    }

    public void resetPasswordForUser(String username) {
        //click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_overview_page.php']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Manage Users")));
        click(By.linkText("Manage Users"));
        wait.until(ExpectedConditions.elementToBeClickable(By.name("username"))); // Search input
        type(By.name("username"), username);
        click(By.cssSelector("input[value='Manage User']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Reset Password']")));
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void confirmNewPassword(String link, String password) {
        wd.get(link);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }
}