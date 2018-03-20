package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
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

        // direct comparison - only Java8 and next
        before.add(contact);
        Comparator<? super ContactData> byId = (ct1, ct2) -> (Integer.compare(ct1.getId(), ct2.getId()));
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
