package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        System.out.println("number of contacts before test: " + before.size());

        app.getContactHelper().createContact(new ContactData(null,"Krystyna", "Balerina",
                "krycha","Wesoła 4, 10-100 Reda","500400200", "krycha@wp.pl",
                "[none]"));
        app.getNavigationHelper().goToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() + 1);
        System.out.println("number of contacts after test: " + after.size());
    }

}
