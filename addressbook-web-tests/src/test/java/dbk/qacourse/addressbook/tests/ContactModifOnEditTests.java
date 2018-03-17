package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

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

        List<ContactData> before = app.getContactHelper().getContactList();
        System.out.println("number of contacts before test: " + before.size());

        app.getContactHelper().selectContactToEdit(before.size() - 1);
        app.getContactHelper().fillContactForm(new ContactData("Halina", "Szczęścliwa",
                "nina","Niema 1/2, 10-120 Palice","100200300", "halina@wp.pl",
                null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        System.out.println("number of contacts at the end: " + after.size());
    }
}
