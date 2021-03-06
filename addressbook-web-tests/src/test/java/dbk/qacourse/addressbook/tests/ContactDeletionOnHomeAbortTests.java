package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import dbk.qacourse.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionOnHomeAbortTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            app.goTo().homePage();
            app.contacts().create(new ContactData().withFirstname("Edyta").withLastname("Klocek").withNick("klocek")
                    .withAddress("Nowa 4, 10-100 Puck").withMobilePhone("601601601").withEmail("eklocek@wp.pl")
                    .addGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactDeletionOnHomeAbort() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();  //random selection of an element to be removed
        app.goTo().homePage();
        app.contacts().contactDeleteById(deletedContact);
        app.contacts().deleteOnHome();
        app.contacts().isAlertPresent();
        assertEquals(app.db().contacts().size(),before.size()); // comparing the size of collections
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before));
        verifyContactListInUI();
    }
}