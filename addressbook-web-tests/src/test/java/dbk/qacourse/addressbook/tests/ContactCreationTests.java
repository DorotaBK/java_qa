package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new ContactData().withFirstname("Paweł").withLastname("Biały").withNick("bialy")
                .withAddress("Jasna 41, 10-100 Puck").withMobilePhone("800800800").withEmail("bialy@wp.pl")
                .withPhoto(new File("src/test/resources/kermit.jpg")).withGroup("[none]")});
        list.add(new Object[]{new ContactData().withFirstname("Iza").withLastname("Kret").withNick("bigbird")
                .withAddress("Morska 4, 10-100 Sopot").withMobilePhone("700700700").withEmail("izunia@wp.pl")
                .withPhoto(new File("src/test/resources/bigbird.jpg")).withGroup("[none]")});
        list.add(new Object[]{new ContactData().withFirstname("Marta").withLastname("Kos").withNick("cookie")
                .withAddress("Hutnicza 4, 10-100 Gdynia").withMobilePhone("600600600").withEmail("kosa@wp.pl")
                .withPhoto(new File("src/test/resources/cookie.jpg")).withGroup("[none]")});
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        app.goTo().homePage();
        Contacts before = app.contacts().all();
        app.contacts().create(contact);
        app.goTo().homePage();
        assertThat(app.contacts().count(), equalTo(before.size() + 1));
        /*Contacts after = app.contacts().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((ct) -> ct.getId()).max().getAsInt()))));
                */
    }

    // searching for the current working directory
    @Test (enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/Elmo.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}