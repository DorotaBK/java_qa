package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModifOnEditTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contacts().contactList().size() == 0) {
            app.contacts().create(new ContactData("Edyta", "Klocek", "klocek",
                    null, "601601601", "eklocek@wp.pl", "[none]"));
        }
    }

    @Test
    public void testContactModifOnEdit(){
        List<ContactData> before = app.contacts().contactList();
        System.out.println("number of contacts before test: " + before.size());

        int index = before.size() - 6;     // the element that I want to modify
        ContactData currentContact = new ContactData(before.get(index).getId(),"Paula", "Kot",
                "igasz","Późna 1/2, 10-120 Płock","800200300", "igaiga@wp.pl",null);
        app.contacts().modifyOnEdit(index, currentContact);
        app.goTo().homePage();

        // comparing the size of collections
        List<ContactData> after = app.contacts().contactList();
        Assert.assertEquals(after.size(), before.size());
        System.out.println("number of contacts at the end: " + after.size());

        // direct comparison - only Java8 and next
        before.remove(index);
        before.add(currentContact);
        Comparator<? super ContactData> byId = (ct1, ct2) -> (Integer.compare(ct1.getId(), ct2.getId()));
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
