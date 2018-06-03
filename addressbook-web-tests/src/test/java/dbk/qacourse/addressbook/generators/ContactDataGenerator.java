package dbk.qacourse.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import dbk.qacourse.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCSV(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXML(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJSON(contacts, new File(file));
        }else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private List<ContactData> generateContacts (int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        String[] firstnames = {"Jan", "Adam", "Piotr", "Witold"};
        String[] lastnames = {"Pierwszy", "Drugi", "Trzeci", "Czwarty"};
        String[] emails = {"Pierwszy_0@wp.pl", "Drugi_0@wp.pl", "Trzeci_0@wp.pl", "Czwarty_0@wp.pl"};
        String[] emails2 = {"Pierwszy_1@wp.pl", "Drugi_1@wp.pl", "Trzeci_1@wp.pl", "Czwarty_1@wp.pl"};
        String[] emails3 = {"Pierwszy_2@wp.pl", "Drugi_2@wp.pl", "Trzeci_2@wp.pl", "Czwarty_2@wp.pl"};
        String[] home = {"100000000", "100222222", "100333333", "100444444"};
        String[] mobiles = {"500500500", "500600700", "500700800", "500800900"};
        String[] work = {"300000000", "300222222", "300333333", "400333333"};
        String[] photos = {"src/test/resources/photo/bert.jpg", "src/test/resources/photo/ernie.jpg",
                "src/test/resources/photo/minion.jpg", "src/test/resources/photo/bigbird.jpg"};
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(firstnames[i]).withLastname(lastnames[i])
                            .withEmail(emails[i]).withEmail2(emails2[i]).withEmail3(emails3[i])
                            .withHomePhone(home[i]).withMobilePhone(mobiles[i]).withWorkPhone(work[i])
                            .withPhoto(photos[i]));
                            //.addGroup(app.db().groups().iterator().next()));
        }
        return contacts;
    }

    private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try(Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try(Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }

    private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try(Writer writer = new FileWriter(file)){
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
                        contact.getEmail(), contact.getMobilePhone()));
                        //, contact.getGroups()
            }
        }
    }
}