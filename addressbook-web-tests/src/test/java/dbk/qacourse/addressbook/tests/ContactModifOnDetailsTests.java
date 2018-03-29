package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModifOnDetailsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactData().withFirstname("Edyta").withLastname("Klocek").withNick("klocek")
                    .withAddress("Nowa 4, 10-100 Puck").withMobile("601601601").withEmail("kloc@wp.pl").withGroup("[none]"));
        }
    }

    @Test
    public void testContactModifOnDetails() {
        Contacts before = app.contacts().all();
        System.out.println("before test: " + before.size());

        ContactData modifiedContact = before.iterator().next(); //random selection of an element to be removed
        ContactData currentContact = new ContactData().withId(modifiedContact.getId()).withFirstname("Kornelia")
                .withLastname("Nowak").withAddress("Zmęczona 1, 10-100 Gdynia").withMobile("500555000").withEmail("selen@wp.pl");
        app.contacts().modifyOnDetails(currentContact);
        app.goTo().homePage();
        assertEquals(app.contacts().count(), before.size()); //comparing the size of collections
        Contacts after = app.contacts().all();
        System.out.println("after test: " + after.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(currentContact)));
    }
}