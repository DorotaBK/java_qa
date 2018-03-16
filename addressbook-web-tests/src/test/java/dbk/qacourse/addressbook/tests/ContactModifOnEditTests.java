package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactModifOnEditTests extends TestBase {

    @Test
    public void testContactModifOnEdit(){
        app.getNavigationHelper().goToHomePage();

        int start = app.getContactHelper().getContactCount();
        System.out.println("number of contacts at the beginning: " + start);

        //checking pre-conditions and providing them if necessary
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Edyta", "Klocek", "klocek",
                    null, "601601601", "eklocek@wp.pl", "[none]"));
        }

        int before = app.getContactHelper().getContactCount();
        System.out.println("number of contacts before test: " + before);

        app.getContactHelper().selectContactToEdit(before - 4);
        app.getContactHelper().fillContactForm(new ContactData(" Nina", "Nowakowska",
                "nina","Nowa Jasna 1/2, 10-100 Opole","600700800", "opole@wp.pl",
                null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();

        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
        System.out.println("number of contacts at the end: " + after);
    }
}
