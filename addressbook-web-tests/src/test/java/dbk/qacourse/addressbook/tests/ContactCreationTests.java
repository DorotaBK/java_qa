package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contacts().all();
        System.out.println("number of contacts before test: " + before.size());
        ContactData contact = new ContactData().withFirstname("Jan").withLastname("Polski").withNick("polak")
                .withAddress("Nowa 4, 10-100 Puck").withMobile("700600500").withEmail("polak@wp.pl").withGroup("[none]");
        app.contacts().create(contact);
        app.goTo().homePage();

        // comparing the size of the List
        Contacts after = app.contacts().all();
        assertEquals(after.size(),before.size() + 1);
        System.out.println("number of contacts after test: " + after.size());

        // direct comparison
        assertThat(after, equalTo(contact.withId(after.stream().mapToInt((ct) -> ct.getId()).max().getAsInt())));
    }
}
