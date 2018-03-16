package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHomePage();
        int before = app.getContactHelper().getContactCount();
        System.out.println("number of contacts at the beginning: " + before);
        app.getContactHelper().createContact(new ContactData("Krystyna", "Balerina",
                "krycha","Wesoła 4, 10-100 Reda","500400200", "krycha@wp.pl",
                "[none]"));
        app.getNavigationHelper().goToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before + 1);
        System.out.println("number of contacts at the end: " + after);
    }

}
