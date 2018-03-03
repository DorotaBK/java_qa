package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModifByDetailsTests extends TestBase {

    @Test
    public void testContactModifByDetails() {
        app.getContactHelper().selectContactToModifByDetails();
        app.getContactHelper().initModifyOnDetails();
        app.getContactHelper().fillContactForm(new ContactData(" Nina", "Balerina", "balerina",
                "Nowa 1/2, 10-100 Piłą","705705705", "balerina@wp.pl"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
