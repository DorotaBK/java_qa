package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeletionOnEditTests extends TestBase {

    @Test
    public void testContactDeletionOnEdit() {
        app.getNavigationHelper().goToHomePage();
        int start = app.getContactHelper().getContactCount();
        System.out.println("number of contacts at the beginning: " + start);

        //checking pre-conditions and providing them if necessary
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Edyta", "Klocek", "klocek",
                    null, "601601601", "eklocek@wp.pl", "[none]"));
        }

        int before = app.getContactHelper().getContactCount();
        System.out.println("number of contacts before test: " + before);
        app.getContactHelper().selectContactToEdit();
        app.getContactHelper().deleteOnEditPage();
        app.getNavigationHelper().goToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1 );
        System.out.println("number of contacts at the end: " + after);
    }
}
