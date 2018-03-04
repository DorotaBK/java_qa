package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().goToAddNewContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Krystyna", "Balerina", "krycha",
                "Wesoła 4, 10-100 Reda","500400200", "krycha@wp.pl"));
        app.getContactHelper().submitContactCreation();
    }

}
