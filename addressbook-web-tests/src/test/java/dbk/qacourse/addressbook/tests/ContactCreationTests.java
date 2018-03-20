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

        ContactData contact = new ContactData("Jan", "Polski","polak",
                "Olchowa 4, 10-100 Puck","700600500", "polak@wp.pl","[none]");
        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().goToHomePage();

        // comparing the size of the List before and after
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() + 1);
        System.out.println("number of contacts after test: " + after.size());

        /* searching of max id before Java8
        int max = 0;
        for (ContactData c : after) {
            if (c.getId() > max) {
                max = c.getId();
            }
        }*/

        // Comparison of whole collections in Java8
        // step 1: max object found with lambda expression -> get his id -> new contact has max id:
        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        // step 2: convert list into set and comparison of old and new collections:
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
