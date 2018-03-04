package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModifOnEditTests extends TestBase {

    @Test
    public void testContactModifByEdit(){
        app.getContactHelper().selectContactToEdit();
        app.getContactHelper().fillContactForm(new ContactData(" Nina", "Nowakowska", "mnina",
                "Nowa 1/2, 10-100 Puck","650650650", "nina@wp.pl"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
