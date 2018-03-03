package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().goToAddNewContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Zofia Janina", "Testowa", "zosiajan","Ciemna 4, 10-100 Reda",
                        "888666444", "zosiatst@wp.pl"));
        app.getContactHelper().submitContactCreation();
    }

}
