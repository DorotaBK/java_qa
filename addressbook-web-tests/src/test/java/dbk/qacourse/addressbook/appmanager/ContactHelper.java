package dbk.qacourse.addressbook.appmanager;

import dbk.qacourse.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNick());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        // if there is element - dropdown list "new_group" - select a value from the list:
        // if creation = there is an element in the drop-down list, so we are on the creation form
        // else = there is no drop-down list, so we are on the modification form
        if (creation) {
            // choose element from drop-down list
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent((By.name("new_group"))));
        }
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
        //click(By.name("submit"));
    }

    public void selectContactToEdit(int index) {
        // click(By.cssSelector("img[alt='Edit']"));                            // select the first element on the page
        wd.findElements(By.cssSelector("img[alt='Edit']")).get(index).click();  //select a specific element on the page
    }

    public void selectContactToDetails(int index) {
        // click(By.cssSelector("img[alt='Details']"));                         // select the first element on the page
        wd.findElements(By.cssSelector("img[alt='Details']")).get(index).click(); //select a specific element on the page
    }

    public void selectContactToDelete(int index) {
        /* select the first element on the page:
        if (!wd.findElement(By.name("selected[]")).isSelected()) {
            click(By.name("selected[]"));
        }*/

        // select a specific element on the page
        if (!wd.findElements(By.name("selected[]")).get(index).isSelected()) {
            wd.findElements(By.name("selected[]")).get(index).click();
        }
    }

    public void initContactModifOnDetailsPage() {
        click(By.name("modifiy"));
    }

    public void modifyOnDetails(int index, ContactData currentContact) {
        selectContactToDetails(index);
        initContactModifOnDetailsPage();
        fillContactForm(currentContact, false);
        submitContactModification();
    }

    public void modifyOnEdit(int index, ContactData currentContact) {
        selectContactToEdit(index);
        fillContactForm(currentContact, false);
        submitContactModification();
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[1]"));       // top button UPDATE
        // click(By.xpath("//div[@id='content']/form[1]/input[22]"));   // bottom button UPDATE
    }

    public void deleteOnHome() {
        click(By.name("selected[]"));
        // click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));     // default from Selenium Builder
        // click(By.xpath("//div/div[4]/form[2]/div[2]/input"));            // optional from -//-
    }

    public void deleteOnEditPage() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    // create contact if it's absent - precondition for contact editing/modification tests
    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.cssSelector("td.center>input")).getAttribute("id"));
            String lastName = element.findElement(By.xpath("td[2]")).getText();
            String firstName = element.findElement(By.xpath("td[3]")).getText();
            String address = element.findElement(By.xpath("td[4]")).getText();
            String email = element.findElement(By.xpath("td[5]")).getText();
            String mobile = element.findElement(By.xpath("td[6]")).getText();
            ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
                    .withAddress(address).withMobile(mobile).withEmail(email);
            contacts.add(contact);
        }
        return contacts;
    }
}
