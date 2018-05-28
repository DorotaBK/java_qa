package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

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
    public void testAddContactToGroup() {
        GroupData selectedGroup;
        ContactData modifiedContact = app.db().contacts().iterator().next(); //selected contact
        Groups groupsBefore = modifiedContact.getGroups(); //groups of selected contact
        if (groupsBefore.size() < app.db().groups().size()) {
            selectedGroup = app.db().groups().iterator().next(); //selecting a group from drop-down list
            app.contacts().addToGroup(modifiedContact, selectedGroup);
        } else {
            selectedGroup = groupsBefore.iterator().next();
        }

        modifiedContact = app.db().contactById(modifiedContact.getId());
        Groups groupsAfter = modifiedContact.getGroups();
        assertThat(groupsAfter, equalTo(groupsBefore.withAdded(selectedGroup)));
    }
}
