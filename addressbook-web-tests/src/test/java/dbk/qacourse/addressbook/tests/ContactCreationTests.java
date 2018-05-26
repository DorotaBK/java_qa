package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.groups().create(new GroupData().withName("grupa_A"));
        }
    }

    // Test with selecting a group from the drop-down list
    @Test
    public void testContactCreation() {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData newContact = new ContactData().withFirstname("SpongeBob").withLastname("Testowy")
                .withAddress("Polna").withHomePhone("666555444").withMobilePhone("200500200").withWorkPhone("586006060")
                .withEmail("jan_testowy@wp.pl").withPhoto("src/test/resources/photo/spongebob.jpg")
                .inGroup(groups.iterator().next());
        app.goTo().homePage();
        app.contacts().initContactCreation();
        app.contacts().fillContactForm(newContact, true);
        app.contacts().submitContactCreation();
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() + 1));
        verifyContactListInUI();
    }
}