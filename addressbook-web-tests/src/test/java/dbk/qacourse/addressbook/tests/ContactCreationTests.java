package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        System.out.println("number of contacts before test: " + before.size());

        ContactData contact = new ContactData("Anna", "Zmęczona","senna",
                "Nocna 4, 10-100 Puck","500400200", "krycha@wp.pl","[none]");
        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().goToHomePage();

        // comparing the size of the List before and after
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() + 1);
        System.out.println("number of contacts after test: " + after.size());

        // comparing of whole collections (requires conversion list -> into a set)
        int max = 0;
        for (ContactData c : after) {
            if (c.getId() > max) {
                max = c.getId();
            }
        }
        contact.setId(max);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
