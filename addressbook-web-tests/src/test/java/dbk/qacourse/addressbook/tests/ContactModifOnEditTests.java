package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Contacts before = app.contacts().all();
        System.out.println("before test: " + before.size());

        ContactData modifiedContact = before.iterator().next();  //random selection of an element to be removed
        ContactData currentContact = new ContactData().withId(modifiedContact.getId()).withFirstname("Paula")
                .withLastname("Kot").withAddress("Późna 1/2, 10-120 Płock").withMobile("800200300").withEmail("paja@wp.pl");
        app.contacts().modifyOnEditPage(currentContact);
        app.goTo().homePage();

        //comparing the size of collections
        Contacts after = app.contacts().all();
        assertEquals(after.size(), before.size());
        System.out.println("after test: " + after.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(currentContact)));
    }
}