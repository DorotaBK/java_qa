package dbk.qacourse.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import dbk.qacourse.addressbook.model.ContactData;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        String[] firstnames = {"Jan", "Adam", "Piotr", "Witold"};
        String[] lastnames = {"Pierwszy", "Drugi", "Trzeci", "Czwarty"};
        String[] emails = {"Pierwszy@wp.pl", "Drugi@wp.pl", "Trzeci@wp.pl", "Czwarty@wp.pl"};
        String[] mobiles = {"500500500", "500600700", "500700800", "500800900"};
        String group = "[none]";
        File[] photos = {new File("src/test/resources/bert.jpg"), new File("src/test/resources/ernie.jpg"),
                new File("src/test/resources/minion.jpg"), new File("src/test/resources/minion.jpg")};
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(firstnames[i]).withLastname(lastnames[i])
                    .withEmail(emails[i]).withMobilePhone(mobiles[i]).withGroup(group).withPhoto(photos[i]));
        }
        return contacts;
    }

    private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
                    contact.getEmail(), contact.getMobilePhone(), contact.getGroup()));
        }
        writer.close(); //close the file
    }

    private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }
}