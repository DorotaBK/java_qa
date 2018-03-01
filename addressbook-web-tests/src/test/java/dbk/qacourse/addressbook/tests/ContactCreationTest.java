package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.goToAddNewContactPage();
        app.getGroupHelper().fillContactForm(new ContactData("Maria", "Cicha", "mania","Jakaś 1/2, 10-100 Opole",
                        "600600600", "mania@wp.pl"));
        app.getGroupHelper().submitContactCreation();
    }

}
