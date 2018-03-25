package dbk.qacourse.addressbook.appmanager;

import dbk.qacourse.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        // if there is element - dropdown list "new_group" - select a value from the list
        // creation => there is an element in the drop-down list, so we are on the creation form
        // else => there is no drop-down list, so we are on the modification form
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

    public void findContactToEditById(int id) {
        wd.findElement(By.cssSelector("a[href*='edit.php?id=" + id + "']")).click();
    }

    public void editContactById(ContactData contact) {
        findContactToEditById(contact.getId());
    }

    public void modifyOnEdit(ContactData currentContact) {
        editContactById(currentContact);
        fillContactForm(currentContact, false);
        submitContactModification();
    }

    private void findContactToDetailsById(int id) {
        wd.findElement(By.cssSelector("a[href*='view.php?id=" + id + "']")).click();
    }

    public void contactDetailsById(ContactData contact) {
        findContactToDetailsById(contact.getId());
    }

    public void modifyOnDetails(ContactData currentContact) {
        contactDetailsById(currentContact);
        initContactModifOnDetailsPage();
        fillContactForm(currentContact, false);
        submitContactModification();
    }


    public void selectContactToDeleteOnHome(int index) {
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

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));    //list of all data rows
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.cssSelector("td.center>input")).getAttribute("id"));
            String lastName = element.findElement(By.xpath("td[2]")).getText();
            String firstName = element.findElement(By.xpath("td[3]")).getText();
            String address = element.findElement(By.xpath("td[4]")).getText();
            String email = element.findElement(By.xpath("td[5]")).getText();
            String mobile = element.findElement(By.xpath("td[6]")).getText();
            contacts.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
                    .withAddress(address).withMobile(mobile).withEmail(email));
        }
        return contacts;
    }
}
