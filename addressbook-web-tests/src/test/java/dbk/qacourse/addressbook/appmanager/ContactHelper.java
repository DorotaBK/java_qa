package dbk.qacourse.addressbook.appmanager;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import dbk.qacourse.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.util.List;

public class ContactHelper extends HelperBase {

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
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        attach(By.name("photo"), new File(contactData.getPhoto()));

        // creation => we are on the creation form, there is drop-down list of groups
        // else => there is no drop-down list, so we are on the modification form
        if (creation) {
            if(contactData.getGroups().size() > 0){
                //try to get group from drop-down list - choose only one element
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator()
                        .next().getName());
            }
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
        selectContactById(contact.getId());
    }

    public void selectContactToEditById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
        /* other:
        wd.findElement(By.cssSelector("a[href*='edit.php?id=" + id + "']")).click();
        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
        wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
        wd.findElement(By.cssSelector(String.format("a[href*='edit.php?id=%s']", id))).click();
        */
    }

    public void selectContactToDetailsById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(6).findElement(By.tagName("a")).click();
        /*
        wd.findElement(By.cssSelector("a[href*='view.php?id=" + id + "']")).click();
        */
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

    public ContactData infoFromEditForm(ContactData contact) {
        selectContactToEditById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String nick = wd.findElement(By.name("nickname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String address2 = wd.findElement(By.name("address2")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withNick(nick)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address).withAddress2(address2);
    }

    public String infoFromDetailsPage(ContactData contact) {
        selectContactToDetailsById(contact.getId());
        String details = wd.findElement(By.id("content")).getText().replaceAll("H: ", "")
                .replaceAll("M: ", "").replaceAll("W: ", "")
                .replaceAll("\n", " ").replaceAll("   ", " ")
                .replaceAll("  ", " ");
        return details;
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
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
                    .withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
        }
        return contactCache;
    }

    public Contacts splitDataFromUI() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));    //list of all data rows
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String[] emails = cells.get(4).getText().split("\n");
            String[] phones = cells.get(5).getText().split("\n");
            contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
                    .withAddress(address)
                    .withEmail(emails[0]).withEmail2(emails[1]).withEmail3(emails[2])
                    .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
        }
        return contactCache;
    }

    public void addToGroup(ContactData contact, GroupData group) {
        homePage();
        selectContactById(contact.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByValue(Integer.toString(group.getId()));
        click(By.name("add"));
        homePage();
    }
}