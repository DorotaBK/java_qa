package dbk.qacourse.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
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
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    // for JSON data file:
    @DataProvider
    public Iterator<Object[]> validContactsFromJSON() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());  //List<ContactData>.class
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    //for XML data file:
    @DataProvider
    public Iterator<Object[]> validContactsFromXML() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))){
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    // for CSV data file - use as a switch!
    @DataProvider
    public Iterator<Object[]> validContactsFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[]{new ContactData().withFirstname(split[0]).withLastname(split[1])
                    .withEmail(split[2]).withMobilePhone(split[3]).withGroup(split[4])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @Test (dataProvider = "validContactsFromJSON")
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