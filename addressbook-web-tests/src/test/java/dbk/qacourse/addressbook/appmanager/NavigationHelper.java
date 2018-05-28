package dbk.qacourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

// Before each transition, we check whether we really have to make this transition
    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))            //checking the presence of a header
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")   //checking the header text
                && isElementPresent(By.name("new"))) {    //checking the presence of the 'New group' button
            return;                                       //exit from the method, we don't have to do anything
        }
        click(By.linkText("groups"));
    }

    public void homePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        super.homePage();
    }
}
