package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contacts().all();
        File photo = new File("src/test/resources/Elmo.jpg");
        ContactData contact = new ContactData().withFirstname("Jan").withLastname("Polski").withNick("polak")
                .withAddress("Nowa 4, 10-100 Puck").withMobilePhone("700600500").withEmail("polak@wp.pl")
                .withPhoto(photo).withGroup("[none]").withPhoto(photo);
        app.contacts().create(contact);
        app.goTo().homePage();
        assertEquals(app.contacts().count(),before.size() + 1);
        Contacts after = app.contacts().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((ct) -> ct.getId()).max().getAsInt()))));
    }

    @Test (enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/Elmo.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }

}