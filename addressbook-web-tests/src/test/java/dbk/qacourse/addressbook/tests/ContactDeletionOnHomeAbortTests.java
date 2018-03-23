package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionOnHomeAbortTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contacts().list().size() == 0) {
            app.contacts().create(new ContactData().withFirstname("Edyta").withLastname("Klocek").withNick("klocek")
                    .withAddress("Nowa 4, 10-100 Puck").withMobile("601601601").withEmail("eklocek@wp.pl").withGroup("[none]"));
        }
    }

    @Test
    public void testContactDeletionOnHomeAbort() {
        List<ContactData> before = app.contacts().list();
        System.out.println("number of contacts before test: " + before.size());

        int index = before.size() - 1;     //the element I want to delete
        app.contacts().selectContactToDelete(index);
        app.contacts().deleteOnHome();
        app.contacts().isAlertPresent();

        // comparing the size of collections
        List<ContactData> after = app.contacts().list();
        Assert.assertEquals(after.size(), before.size() );
        System.out.println("number of contacts at the end: " + after.size());

        // comparing of collections
        Assert.assertEquals(after, before);
    }
}
