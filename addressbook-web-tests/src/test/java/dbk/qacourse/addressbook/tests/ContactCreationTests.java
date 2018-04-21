package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        /* list.add(new Object[]{new ContactData().withFirstname("Paweł").withLastname("Biały").withNick("bialy")
                .withAddress("Jasna 41, 10-100 Puck").withMobilePhone("800800800").withEmail("bialy@wp.pl")
                .withPhoto(new File("src/test/resources/kermit.jpg")).withGroup("[none]")});  */
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[]{new ContactData().withFirstname(split[0]).withLastname(split[1])
                    .withEmail(split[2]).withMobilePhone(split[3])});
            line = reader.readLine();
        }
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