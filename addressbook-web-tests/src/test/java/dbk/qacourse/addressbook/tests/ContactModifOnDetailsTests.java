package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactModifOnDetailsTests extends TestBase {

    @Test
    public void testContactModifOnDetails() {
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

        int contactToModify = before.size() - 1;    // the element that I want to modify
        app.getContactHelper().selectContactToDetails(contactToModify);
        app.getContactHelper().initContactModifOnDetailsPage();
        ContactData currentContact = new ContactData(before.get(contactToModify).getId(),"Ewa", "Szczęśliwa",
                null,"Zmęczona 100, 10-100 Gdynia","500555000", "selenium@wp.pl", null);
        app.getContactHelper().fillContactForm(currentContact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();

        // comparing the size of collections
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        System.out.println("number of contacts at the end: " + after.size());

        // comparing of whole collections (requires conversion of the list into a set)
        before.remove(contactToModify);
        before.add(currentContact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}
