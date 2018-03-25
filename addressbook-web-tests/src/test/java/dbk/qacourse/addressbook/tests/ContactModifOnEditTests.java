package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

public class ContactModifOnEditTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactData().withFirstname("Edyta").withLastname("Klocek").withNick("klocek")
                    .withAddress("Nowa 4, 10-100 Puck").withMobile("601601601").withEmail("kloc@wp.pl").withGroup("[none]"));
        }
    }

    @Test
    public void testContactModifOnEdit(){
        Set<ContactData> before = app.contacts().all();
        System.out.println("number of contacts before test: " + before.size());

        // random selection of an element to be removed
        ContactData modifiedContact = before.iterator().next();
        ContactData currentContact = new ContactData().withId(modifiedContact.getId()).withFirstname("Paula")
                .withLastname("Kot").withAddress("Późna 1/2, 10-120 Płock").withMobile("800200300").withEmail("paja@wp.pl");
        app.contacts().modifyOnEdit(currentContact);
        app.goTo().homePage();

        // comparing the size of collections
        Set<ContactData> after = app.contacts().all();
        Assert.assertEquals(after.size(), before.size());
        System.out.println("number of contacts at the end: " + after.size());

        // direct comparison
        before.remove(modifiedContact);
        before.add(currentContact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
