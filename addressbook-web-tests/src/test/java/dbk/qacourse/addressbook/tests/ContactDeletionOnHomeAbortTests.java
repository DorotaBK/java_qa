package dbk.qacourse.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionOnHomeAbortTests extends TestBase {

    @Test
    public void testContactDeletionOnHomeAbort() {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteOnHome();
        app.getContactHelper().isAlertPresent();
    }

}