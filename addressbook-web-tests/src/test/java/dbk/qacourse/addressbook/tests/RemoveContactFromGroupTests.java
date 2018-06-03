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
    public void testRemoveContactFromGroup() {
        ContactData selectedContact = null;
        GroupData modifiedGroup = app.db().groups().iterator().next(); //choose some group from db
        Contacts contactsBefore = modifiedGroup.getContacts();  //list of contacts in the selected group
        if(contactsBefore.size() == 0) {
            selectedContact = app.db().contacts().iterator().next();    //choose some contact from db
            app.contacts().addToGroup(selectedContact, modifiedGroup);  //add selected contact to the selected group
            modifiedGroup = app.db().groupById(modifiedGroup.getId());  //current data of seleted group
            contactsBefore = modifiedGroup.getContacts();               //current contact details in the selected group
        }
        app.groups().removeContact(selectedContact, modifiedGroup);
        modifiedGroup = app.db().groupById(modifiedGroup.getId());
        Contacts contactAfter = modifiedGroup.getContacts();
        assertThat(contactAfter, equalTo(contactsBefore.without(selectedContact)));
    }
}
