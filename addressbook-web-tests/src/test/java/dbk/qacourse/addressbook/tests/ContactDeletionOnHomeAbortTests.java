package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionOnHomeAbortTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactData().withFirstname("Edyta").withLastname("Klocek").withNick("klocek")
                    .withAddress("Nowa 4, 10-100 Puck").withMobilePhone("601601601").withEmail("eklocek@wp.pl").withGroup("[none]"));
        }
    }

    @Test
    public void testContactDeletionOnHomeAbort() {
        Contacts before = app.contacts().all();
        System.out.println("before test: " + before.size());

        ContactData deletedContact = before.iterator().next();  //random selection of an element to be removed
        app.contacts().contactDeleteById(deletedContact);
        app.contacts().deleteOnHome();
        app.contacts().isAlertPresent();
        assertEquals(app.contacts().count(),before.size()); // comparing the size of collections
        Contacts after = app.contacts().all();
        System.out.println("after test: " + after.size());
        assertThat(after, equalTo(before));
    }
}