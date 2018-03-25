package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Set<ContactData> before = app.contacts().all();
        System.out.println("number of contacts before test: " + before.size());
        ContactData contact = new ContactData().withFirstname("Jan").withLastname("Polski").withNick("polak")
                .withAddress("Nowa 4, 10-100 Puck").withMobile("700600500").withEmail("polak@wp.pl").withGroup("[none]");
        app.contacts().create(contact);
        app.goTo().homePage();

        // comparing the size of the List
        Set<ContactData> after = app.contacts().all();
        Assert.assertEquals(after.size(),before.size() + 1);
        System.out.println("number of contacts after test: " + after.size());

        // direct comparison
        contact.withId(after.stream().mapToInt((ct) -> ct.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
