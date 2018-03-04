package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModifOnDetailsTests extends TestBase {

    @Test
    public void testContactModifOnDetails() {
        app.getContactHelper().selectContactToDetails();
        app.getContactHelper().initModifOnDetailsPage();
        app.getContactHelper().fillContactForm(new ContactData("Józef", "Tester", "jozek",
                "Tajemna 1/2, 10-100 Piłą","500000000", "jozek@wp.pl"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHomePage();
    }
}
