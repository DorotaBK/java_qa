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

        // if there is element - dropdown list named "new_group" - select a value from the list
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

    // checking the existence of contact on the page - precondition for contact editing/modification tests
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    // create contact if it's absent - precondition for contact editing/modification tests
    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
                //(By.);
                // (By.name("selected[]"));

        for (WebElement element : elements) {
            String name = element.findElement(By.xpath("//tr[@name='entry']/td[3]")).getText();
            String lastName = element.findElement(By.xpath("//tr[@name='entry']/td[2]")).getText();
        // DOKONCZ WYCIAGANIE ARTOSCI I SPRAWDZ CZY DOBRZE WYCIAGASZ ID
            String id = wd.findElement(By.xpath("//.center/input")).getAttribute("id");
            ContactData contact = new ContactData(null, name, lastName, null, null, null,
                                                    null, null);

            contacts.add(contact);
        }
        return contacts;
    }
}
