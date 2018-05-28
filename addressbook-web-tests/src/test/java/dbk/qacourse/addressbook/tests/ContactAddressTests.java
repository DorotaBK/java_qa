package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0){
                app.goTo().groupPage();
                app.groups().create(new GroupData().withName("grupa_A"));
            }
            Groups groups = app.db().groups();
            app.goTo().homePage();
            app.contacts().create(new ContactData().withFirstname("Agata").withLastname("Wredna").withNick("ruda")
                    .withAddress("Szara 4, 10-100 Opole").withMobilePhone("666777888").withEmail("ruda@wp.pl")
                    .withPhoto("src/test/resources/photo/spongebob.jpg").addGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contacts().all().iterator().next();       //contact from main page
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);
        //System.out.println(contact.getLastname());
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }
}