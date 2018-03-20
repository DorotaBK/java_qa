package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
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

        int contactToModify = before.size() - 2;     // the element that I want to modify
        app.getContactHelper().selectContactToEdit(contactToModify);
        ContactData currentContact = new ContactData(before.get(contactToModify).getId(),"Iga", "Nocna",
                "igasz","Późna 1/2, 10-120 Płock","800200300", "igaiga@wp.pl",null);
        app.getContactHelper().fillContactForm(currentContact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();

        // comparing the size of collections
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        System.out.println("number of contacts at the end: " + after.size());

        // direct comparison - only Java8 and next
        before.remove(contactToModify);
        before.add(currentContact);
        Comparator<? super ContactData> byId = (ct1, ct2) -> (Integer.compare(ct1.getId(), ct2.getId()));
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
