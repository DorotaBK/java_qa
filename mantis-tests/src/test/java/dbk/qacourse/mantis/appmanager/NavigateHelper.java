package dbk.qacourse.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigateHelper extends HelperBase {

    public NavigateHelper(ApplicationManager app) {
        super(app);
    }

    public void loginAs(String username, String password) {
        wd.get(app.getProperty("web.baseUrl"));
        type(By.name("username"), username);
        click(By.cssSelector("input[value='Login']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }
}