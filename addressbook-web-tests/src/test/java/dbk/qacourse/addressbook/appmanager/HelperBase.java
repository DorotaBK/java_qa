package dbk.qacourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    // a comparison of the parameter and the default value in the field
    protected void type(By locator, String text) {
        click(locator);
        if (text != null){
            String existingText = wd.findElement(locator).getAttribute("value");
            if(!text.equals(existingText)){
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void attach(By locator, File file) {
        if (file != null){
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    // capturing exceptions (IF alert...)
    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();  // ok, alert is present, visible
            return true;
        } catch (NoAlertPresentException e) {   // in () is the type of exception
            return false;
        }
    }

    // a method for checking the presence/absence of an element on the page
    protected boolean isElementPresent(By locator) {
        try{
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException exc){
            return false;
        }
    }

    protected void homePage() {
        click(By.linkText("home"));
    }

    public void selectContactById(int id) {
        if (!wd.findElement(By.cssSelector("input[id='" + id + "']")).isSelected()) {
            wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
        }
    }

    public void selectGroupById(int id) {
        // select a specific element on the page
        if (!wd.findElement(By.cssSelector("input[value='" + id + "']")).isSelected()) {
            wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
        }
    }

    public void selectAllGroup() {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
    }
}