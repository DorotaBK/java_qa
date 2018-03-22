package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionOnEditTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contacts().list().size() == 0) {
            app.contacts().create(new ContactData("Edyta", "Klocek", "klocek",
                    null, "601601601", "eklocek@wp.pl", "[none]"));
        }
    }

    @Test
    public void testContactDeletionOnEdit() {
        List<ContactData> before = app.contacts().list();
        System.out.println("number of contacts before test: " + before.size());

        int index = before.size() - 4;     //the element I want to delete
        app.contacts().selectContactToEdit(index);
        app.contacts().deleteOnEditPage();
        app.goTo().homePage();

        // comparing the size of collections
        List<ContactData> after = app.contacts().list();
        Assert.assertEquals(after.size(), before.size() - 1 );
        System.out.println("number of contacts at the end: " + after.size());

        // comparing of whole collections
        before.remove(index);
        Assert.assertEquals(after, before);
    }
}
