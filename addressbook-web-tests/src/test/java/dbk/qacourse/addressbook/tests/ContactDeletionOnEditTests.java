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
        app.goTo().homePage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactData().withFirstname("Edyta").withLastname("Klocek").withNick("klocek")
                    .withAddress("Nowa 4, 10-100 Puck").withMobile("601601601").withEmail("eklocek@wp.pl").withGroup("[none]"));
        }
    }

    @Test
    public void testContactDeletionOnEdit() {
        Contacts before = app.contacts().all();
        System.out.println("before test: " + before.size());

        ContactData deletedContact = before.iterator().next();  //random selection of an element to be removed
        app.contacts().contactEditById(deletedContact);
        app.contacts().deleteOnEditPage();
        app.goTo().homePage();

        Contacts after = app.contacts().all();
        assertEquals(after.size(), before.size() - 1 );
        System.out.println("after test: " + after.size());

        assertThat(after, equalTo(before.without(deletedContact)));
    }
}