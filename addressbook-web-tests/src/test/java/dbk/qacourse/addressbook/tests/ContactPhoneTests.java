package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0){
                app.goTo().groupPage();
                app.groups().create(new GroupData().withName("grupa_F"));
            }
            Groups groups = app.db().groups();
            app.goTo().homePage();
            app.contacts().create(new ContactData().withFirstname("Marta").withLastname("Senna").withNick("sennaM")
                    .withAddress("Szara 4, 10-100 Sopot").withMobilePhone("666777888").withEmail("senna@wp.pl")
                    .withPhoto("src/test/resources/photo/spongebob.jpg").addGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contacts().all().iterator().next(); //contact from main page
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).stream()
                .filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "")
                .replaceAll("[-()]","");
    }

    /* test with cutting of data chains (only if each contact has 3 telephone numbers!):
    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contacts().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);

        assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
        assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
        assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
    }
    */
}