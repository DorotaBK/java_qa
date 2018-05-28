package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.groups().create(new GroupData().withName("grupa_C"));
        }

        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            app.goTo().homePage();
            app.contacts().create(new ContactData().withFirstname("Magda").withLastname("Kowalska").withNick("madzia")
                    .withAddress("Szumna 40, 10-100 Puck").withMobilePhone("666777888").withEmail("ruda@wp.pl")
                    .withPhoto("src/test/resources/photo/spongebob.jpg").addGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactRemoveFromGroup() {
        GroupData modifiedGroup = app.db().groups().iterator().next(); //selected group
        Contacts contactsBefore = modifiedGroup.getContacts();
        ContactData selectedContact = app.db().contacts().iterator().next();
        app.groups().removeContact(selectedContact, modifiedGroup);
        modifiedGroup = app.db().groupById(modifiedGroup.getId());
        Contacts contactAfter = modifiedGroup.getContacts();
        assertThat(contactAfter, equalTo(contactsBefore.without(selectedContact)));
    }
}
