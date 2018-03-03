package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().goToAddNewContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Paweł", "Miły", "milyp","Jasna 6, 10-100 Olsztyn",
                        "500300200", "milyp@wp.pl"));
        app.getContactHelper().submitContactCreation();
    }

}
