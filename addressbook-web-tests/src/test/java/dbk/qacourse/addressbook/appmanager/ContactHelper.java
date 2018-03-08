package dbk.qacourse.addressbook.appmanager;

import dbk.qacourse.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNick());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        if (isElementPresent((By.name("new_group")))) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }

    }

    private boolean isElementPresent(By locator) {
        try{
            wd.findElement(locator);
        } catch (NoSuchElementException exc){

        }
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
        //click(By.name("submit"));
    }

    public void selectContactEdit() {
        // click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));     // default from Selenium Builder
        //click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img));     // optional from -//-
        click(By.cssSelector("img[alt='Edit']"));                                   // QA-Courses code
    }

    public void selectContactDetails() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[5]/td[7]/a/img"));        // default from Selenium Builder
        // click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[8]/td[7]/a/img"));   // optional from -//-
    }

    public void initModifOnDetailsPage() {
        click(By.name("modifiy"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[1]"));       // top button
        // click(By.xpath("//div[@id='content']/form[1]/input[22]"));   // bottom button
    }

    public void selectContact() {
        if (!wd.findElement(By.name("selected[]")).isSelected()) {
            click(By.name("selected[]"));
        }
        // click(By.name("selected[]"));                                            // optional from Selenium Builder
        // click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));   // optional -//-
    }

    public void deleteOnHome() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));               // default from Selenium Builder
        // click(By.xpath("//div/div[4]/form[2]/div[2]/input"));                    // optional from -//-
    }

    public void deleteOnEditPage() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }
}
