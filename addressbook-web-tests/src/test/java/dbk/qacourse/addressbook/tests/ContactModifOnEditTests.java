package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModifOnEditTests extends TestBase {

    @Test
    public void testContactModifOnEdit(){
        app.getContactHelper().selectContactEdit();
        app.getContactHelper().fillContactForm(new ContactData(" Nina", "Nowakowska",
                "nina","Nowa Jasna 1/2, 10-100 Opole","600700800", "opole@wp.pl",
                null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}
