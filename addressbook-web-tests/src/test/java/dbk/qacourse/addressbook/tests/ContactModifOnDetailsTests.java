package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactModifOnDetailsTests extends TestBase {

    @Test
    public void testContactModifOnDetails() {
        app.getNavigationHelper().goToHomePage();
        int start = app.getContactHelper().getContactCount();
        System.out.println("number of contacts at the beginning: " + start);

        //checking pre-conditions and providing them if necessary
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Edyta", "Klocek", "klocek",
                    null, "601601601", "eklocek@wp.pl", "[none]"));
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        System.out.println("number of contacts before test: " + before.size());

        app.getContactHelper().selectContactToDetails(2);
        app.getContactHelper().initContactModifOnDetailsPage();
        app.getContactHelper().fillContactForm(new ContactData("Iza", "Smutna", "smutna",
                "Uczona 54a, 10-100 Opole","600111222", "smutna@wp.pl", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        System.out.println("number of contacts at the end: " + after.size());
    }
}
