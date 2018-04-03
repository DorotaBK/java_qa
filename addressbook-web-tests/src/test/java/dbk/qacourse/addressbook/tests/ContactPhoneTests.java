package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactData().withFirstname("Agata").withLastname("Wredna").withNick("ruda")
                    .withAddress("Szara 4, 10-100 Opole").withMobile("666777888").withEmail("ruda@wp.pl").withGroup("[none]"));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contacts().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);
    }
}
