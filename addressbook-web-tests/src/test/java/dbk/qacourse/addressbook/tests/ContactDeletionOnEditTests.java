package dbk.qacourse.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionOnEditTests extends TestBase {

    @Test
    public void testContactDeletionOnEdit() {
        app.getContactHelper().selectContactToEdit();
        app.getContactHelper().deleteOnEditPage();
    }
}
