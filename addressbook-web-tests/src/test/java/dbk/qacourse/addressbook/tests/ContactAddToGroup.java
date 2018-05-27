package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contacts().create(new ContactData().withFirstname("Agata").withLastname("Jasna").withNick("jasnaruda")
                    .withAddress("Wesoła 4, 10-100 Opole").withMobilePhone("666777888").withEmail("ruda@wp.pl")
                    .withPhoto("src/test/resources/photo/spongebob.jpg"));
        }

        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.groups().create(new GroupData().withName("grupa_1"));
        }
    }

    @Test
    public void testAddToGroup() {
        GroupData selectedGroup; //variable only
        ContactData modifiedContact = app.db().contacts().iterator().next(); //selected contact
        Groups contactGroupsBefore = modifiedContact.getGroups(); //groups of selected contact
        if (contactGroupsBefore.size() < app.db().groups().size()) {
            selectedGroup = app.db().groups().iterator().next(); //selecting a group from drop-down list
            app.contacts().addToGroup(modifiedContact, selectedGroup);
            //modifiedContact = app.db().contactById(modifiedContact.getId());
            //contactGroupsBefore = modifiedContact.getGroups();
        } else {
            selectedGroup = contactGroupsBefore.iterator().next();
        }

        modifiedContact = app.db().contactById(modifiedContact.getId());
        Groups contactGroupsAfter = modifiedContact.getGroups();
        assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withAdded(selectedGroup)));
    }
}
