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
        type(By.name("mobile"), contactData.getMobilePhone());
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
        selectContactToEditById(contact.getId());
    }

    public void contactDetailsById(ContactData contact) {
        selectContactToDetailsById(contact.getId());
    }

    public void contactDeleteById(ContactData contact) {
        selectContactToDeleteById(contact.getId());
    }
    
    public void selectContactToEditById(int id) {
        //by QA-Courses:
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

        /* my own, first version:
        wd.findElement(By.cssSelector("a[href*='edit.php?id=" + id + "']")).click();
        */

        /* other versions by QA-Courses:
        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
        wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
        wd.findElement(By.cssSelector(String.format("a[href*='edit.php?id=%s']", id))).click();
        */
    }

    private void selectContactToDetailsById(int id) {
        wd.findElement(By.cssSelector("a[href*='view.php?id=" + id + "']")).click();
    }

    public void selectContactToDeleteById(int id) {
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
        List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));    //list of all data rows
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));                                        //QA code
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));  //QA code
            //int id = Integer.parseInt(row.findElement(By.cssSelector("td.center>input")).getAttribute("id")); //my code
            String lastName = cells.get(1).getText();
            //String lastName = row.findElement(By.xpath("td[2]")).getText();
            String firstName = cells.get(2).getText();
            //String firstName = row.findElement(By.xpath("td[3]")).getText();
            String address = cells.get(3).getText();
            //String address = row.findElement(By.xpath("td[4]")).getText();
            String email = cells.get(4).getText();
            //String email = row.findElement(By.xpath("td[5]")).getText();
            String[] phones = cells.get(5).getText().split("\n");
            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
                    .withAddress(address).withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2])
                    .withEmail(email));
        }
        return contactCache;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        selectContactToEditById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

}