package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModifOnDetailsTests extends TestBase {

    @Test
    public void testContactModifOnDetails() {
        app.getContactHelper().selectContactDetails();
        app.getContactHelper().initModifOnDetailsPage();
        app.getContactHelper().fillContactForm(new ContactData("Janusz", "Cichy", "cichy",
                "Schowana 1/2, 10-100 Piłą","700000000", "cochy@wp.pl", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}
