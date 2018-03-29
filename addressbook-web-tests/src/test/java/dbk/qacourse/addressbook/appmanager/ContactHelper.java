package dbk.qacourse.addressbook.appmanager;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void initContactModifOnDetailsPage() {
        click(By.name("modifiy"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNick());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        // creation => there is drop-down list, so we are on the creation form
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

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[1]"));       // top button UPDATE
        // click(By.xpath("//div[@id='content']/form[1]/input[22]"));   // bottom button UPDATE
    }

    public void contactEditById(ContactData contact) {
        findContactToEditById(contact.getId());
    }

    public void contactDetailsById(ContactData contact) {
        findContactToDetailsById(contact.getId());
    }

    public void contactDeleteById(ContactData contact) {
        findContactToDeleteById(contact.getId());
    }
    
    public void findContactToEditById(int id) {
        wd.findElement(By.cssSelector("a[href*='edit.php?id=" + id + "']")).click();
    }

    private void findContactToDetailsById(int id) {
        wd.findElement(By.cssSelector("a[href*='view.php?id=" + id + "']")).click();
    }

    public void findContactToDeleteById(int id) {
        if (!wd.findElement(By.cssSelector("input[id='" + id + "']")).isSelected()) {
            wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
        }
    }

    public void modifyOnDetails(ContactData currentContact) {
        contactDetailsById(currentContact);
        initContactModifOnDetailsPage();
        fillContactForm(currentContact, false);
        submitContactModification();
        contactCache = null;
    }

    public void modifyOnEditPage(ContactData currentContact) {
        contactEditById(currentContact);
        fillContactForm(currentContact, false);
        submitContactModification();
        contactCache = null;
    }

    public void deleteOnEditPage() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
        contactCache = null;
    }

    public void deleteOnHome() {
        click(By.name("selected[]"));
    }

    // precondition for contact editing/modification tests
    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));    //list of all data rows
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.cssSelector("td.center>input")).getAttribute("id"));
            String lastName = element.findElement(By.xpath("td[2]")).getText();
            String firstName = element.findElement(By.xpath("td[3]")).getText();
            String address = element.findElement(By.xpath("td[4]")).getText();
            String email = element.findElement(By.xpath("td[5]")).getText();
            String mobile = element.findElement(By.xpath("td[6]")).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
                    .withAddress(address).withMobile(mobile).withEmail(email));
        }
        return contactCache;
    }
}