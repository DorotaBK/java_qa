package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ContactModifOnEditTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0){
                app.goTo().groupPage();
                app.groups().create(new GroupData().withName("grupa_E"));
            }
            Groups groups = app.db().groups();
            app.goTo().homePage();
            app.contacts().create(new ContactData().withFirstname("Iza").withLastname("Wredna").withNick("ruda")
                    .withAddress("Szara 4, 10-100 Opole").withMobilePhone("666777888").withEmail("ruda@wp.pl")
                    .withPhoto("src/test/resources/photo/spongebob.jpg").inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactModifOnEdit(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();     //random selection of an element to be removed
        ContactData currentContact = new ContactData().withId(modifiedContact.getId()).withFirstname("Paula")
                .withLastname("Kot").withAddress("Polna 1/2, 10-120 Opole").withMobilePhone("800200300")
                .withEmail("paja@wp.pl").withPhoto("src/test/resources/photo/tom.png");
        app.goTo().homePage();
        app.contacts().modifyOnEditPage(currentContact);
        app.goTo().homePage();
        assertEquals(app.contacts().count(), before.size()); //comparing the size of collections
        Contacts after = app.db().contacts();
        //assertThat(after, equalTo(before.without(modifiedContact).withAdded(currentContact)));
        verifyContactListInUI();
    }
}