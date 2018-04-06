package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactData().withFirstname("Agata").withLastname("Wredna").withNick("ruda")
                    .withAddress("Szara 4, 10-100 Opole").withMobilePhone("666777888").withEmail("ruda@wp.pl")
                    .withGroup("[none]"));
        }
    }

    @Test
    public void testContactEmails() {
        app.goTo().homePage();
        ContactData contact = app.contacts().all().iterator().next(); //contact from main page
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);
        System.out.println(contact.getLastname());
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream()
                .filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}