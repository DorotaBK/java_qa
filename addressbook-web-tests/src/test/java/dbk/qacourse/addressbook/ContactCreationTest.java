package dbk.qacourse.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        goToAddNewContactPage();
        fillContactForm(new ContactData("Maria", "Cicha", "mania","Jakaś 1/2, 10-100 Opole",
                        "600600600", "mania@wp.pl"));
        submitContactCreation();
    }

}
