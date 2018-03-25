package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactDeletionOnHomeAbortTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactData().withFirstname("Edyta").withLastname("Klocek").withNick("klocek")
                    .withAddress("Nowa 4, 10-100 Puck").withMobile("601601601").withEmail("eklocek@wp.pl").withGroup("[none]"));
        }
    }

    @Test
    public void testContactDeletionOnHomeAbort() {
        Set<ContactData> before = app.contacts().all();
        System.out.println("number of contacts before test: " + before.size());

        // random selection of an element to be removed
        ContactData deletedContact = before.iterator().next();
        app.contacts().deleteContactOnHomeById(deletedContact);
        app.contacts().deleteOnHome();
        app.contacts().isAlertPresent();

        // comparing the size of collections
        Set<ContactData> after = app.contacts().all();
        Assert.assertEquals(after.size(), before.size() );
        System.out.println("number of contacts at the end: " + after.size());

        // comparing of collections
        Assert.assertEquals(after, before);
    }
}
