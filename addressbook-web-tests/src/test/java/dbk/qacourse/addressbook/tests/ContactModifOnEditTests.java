package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ContactModifOnEditTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contacts().create(new ContactData().withFirstname("Edyta").withLastname("Klocek").withNick("klocek")
                    .withAddress("Nowa 4, 10-100 Puck").withMobilePhone("601601601").withEmail("kloc@wp.pl").withGroup("[none]"));
        }
    }

    @Test
    public void testContactModifOnEdit(){
        Contacts before = app.db().contacts();
        System.out.println("before test: " + before.size());
        ContactData modifiedContact = before.iterator().next();     //random selection of an element to be removed
        ContactData currentContact = new ContactData().withId(modifiedContact.getId()).withFirstname("Paula")
                .withLastname("Kot").withAddress("Polna 1/2, 10-120 Opole").withMobilePhone("800200300")
                .withEmail("paja@wp.pl").withPhoto("src/test/resources/photo/tom.png");
        app.goTo().homePage();
        app.contacts().modifyOnEditPage(currentContact);
        app.goTo().homePage();
        assertEquals(app.contacts().count(), before.size()); //comparing the size of collections
        Contacts after = app.db().contacts();
        System.out.println("after test: " + after.size());
        //assertThat(after, equalTo(before.without(modifiedContact).withAdded(currentContact)));
    }
}