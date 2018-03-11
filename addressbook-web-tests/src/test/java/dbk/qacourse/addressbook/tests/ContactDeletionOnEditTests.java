package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionOnEditTests extends TestBase {

    @Test
    public void testContactDeletionOnEdit() {
        app.getNavigationHelper().goToHomePage();

        //checking pre-conditions and providing them if necessary
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Edyta", "Klocek", "klocek",
                    null, "601601601", "eklocek@wp.pl", "[none]"));
        }
        app.getContactHelper().selectContactToEdit();
        app.getContactHelper().deleteOnEditPage();
    }
}
