package dbk.qacourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        if (isElementPresent(By.tagName("h1")))
        click(By.linkText("groups"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

}
