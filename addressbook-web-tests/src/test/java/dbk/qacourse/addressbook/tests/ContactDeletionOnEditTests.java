package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionOnEditTests extends TestBase {

    @Test
    public void testContactDeletionOnEdit() {
        app.getNavigationHelper().goToHomePage();
        int start = app.getContactHelper().getContactCount();
        System.out.println("number of contacts at the beginning: " + start);

        //checking pre-conditions and providing them if necessary
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData(null, "Edyta", "Klocek", "klocek",
                    null, "601601601", "eklocek@wp.pl", "[none]"));
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        System.out.println("number of contacts before test: " + before.size());

        int contactToDelete = before.size() - 2;     //the element I want to delete
        app.getContactHelper().selectContactToEdit(contactToDelete);
        app.getContactHelper().deleteOnEditPage();
        app.getNavigationHelper().goToHomePage();

        // comparing the size of collections
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1 );
        System.out.println("number of contacts at the end: " + after.size());

        // comparing of whole collections (requires conversion of the list into a set)
        before.remove(contactToDelete);
        Assert.assertEquals(after, before);
    }
}
