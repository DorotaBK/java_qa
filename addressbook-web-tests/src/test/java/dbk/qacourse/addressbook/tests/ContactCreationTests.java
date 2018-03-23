package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        List<ContactData> before = app.contacts().list();
        System.out.println("number of contacts before test: " + before.size());

        ContactData contact = new ContactData().withFirstname("Jan").withLastname("Polski").withNick("polak")
                .withAddress("Nowa 4, 10-100 Puck").withMobile("700600500").withEmail("polak@wp.pl").withGroup("[none]");
        app.contacts().create(contact);
        app.goTo().homePage();

        // comparing the size of the List before and after
        List<ContactData> after = app.contacts().list();
        Assert.assertEquals(after.size(),before.size() + 1);
        System.out.println("number of contacts after test: " + after.size());

        // direct comparison - only Java8 and next
        before.add(contact);
        Comparator<? super ContactData> byId = (ct1, ct2) -> (Integer.compare(ct1.getId(), ct2.getId()));
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
