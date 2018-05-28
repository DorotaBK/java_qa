package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModifOnDetailsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0){
                app.goTo().groupPage();
                app.groups().create(new GroupData().withName("grupa_D"));
            }
            Groups groups = app.db().groups();
            app.goTo().homePage();
            app.contacts().create(new ContactData().withFirstname("Agata").withLastname("Jasna").withNick("jasnaruda")
                    .withAddress("Wesoła 4, 10-100 Opole").withMobilePhone("666777888").withEmail("ruda@wp.pl")
                    .withPhoto("src/test/resources/photo/spongebob.jpg").addGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactModifOnDetails() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData currentContact = new ContactData().withId(modifiedContact.getId()).withFirstname("Kornelia")
                .withLastname("Nowak").withAddress("Zmoczona 1, 10-100 Gdynia").withMobilePhone("500555000")
                .withEmail("selen@wp.pl").withPhoto("src/test/resources/photo/JerryMouse.png");
        app.goTo().homePage();
        app.contacts().modifyOnDetails(currentContact);
        app.goTo().homePage();
        assertEquals(app.db().contacts().size(), before.size()); //comparing the size of collections
        Contacts after = app.db().contacts();
        //assertThat(after, equalTo(before.without(modifiedContact).withAdded(currentContact)));
        verifyContactListInUI();
    }
}