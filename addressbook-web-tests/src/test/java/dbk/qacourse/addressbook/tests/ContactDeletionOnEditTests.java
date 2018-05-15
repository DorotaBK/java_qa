package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionOnEditTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contacts().create(new ContactData().withFirstname("Edyta").withLastname("Klocek").withNick("klocek")
                    .withAddress("Nowa 4, 10-100 Puck").withMobilePhone("601601601").withEmail("eklocek@wp.pl").withGroup("[none]"));
        }
    }

    @Test
    public void testContactDeletionOnEdit() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();  //random selection of an element to be removed
        app.goTo().homePage();
        app.contacts().contactEditById(deletedContact);
        app.contacts().deleteOnEditPage();
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        assertEquals(after.size(),before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactListInUI();
    }
}