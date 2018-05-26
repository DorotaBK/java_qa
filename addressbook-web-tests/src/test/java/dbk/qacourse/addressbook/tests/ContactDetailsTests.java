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

public class ContactDetailsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contacts().create(new ContactData().withFirstname("Agata").withLastname("Miła").withNick("ruda")
                    .withAddress("Szara 4, 10-100 Opole").withMobilePhone("666777888").withEmail("ruda@wp.pl")
                    .withPhoto("src/test/resources/photo/spongebob.jpg"));
        }
    }

    @Test
    public void testContactDetails() {
        app.goTo().homePage();
        ContactData contact = app.contacts().all().iterator().next(); //contact from main page
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);
        String contactInfoFromDetailsPage = app.contacts().infoFromDetailsPage(contact);
        assertThat(mergeAllData(contactInfoFromEditForm), equalTo(contactInfoFromDetailsPage));
    }

    private String mergeAllData(ContactData contact) {
        return Arrays.asList(contact.getFirstname(), contact.getLastname(), contact.getNick(), contact.getAddress(),
                contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getAddress2())
                .stream()
                .filter((s) -> ! s.equals(""))
                .collect(Collectors.joining(" "));
    }
}