package dbk.qacourse.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
        save(contacts, new File(file));
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        String[] firstnames = {"Jan", "Adam", "Piotr", "Witold"};
        String[] lastnames = {"Pierwszy", "Drugi", "Trzeci", "Czwarty"};
        String[] emails = {"Pierwszy@wp.pl", "Drugi@wp.pl", "Trzeci@wp.pl", "Czwarty@wp.pl"};
        String[] mobiles = {"500500500", "500600700", "500700800", "500800900"};
        for(int i=0; i<count; i++){
            contacts.add(new ContactData().withFirstname(firstnames[i]).withLastname(lastnames[i])
                        .withEmail(emails[i]).withMobilePhone(mobiles[i]));
        }
        return contacts;
    }

    private void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
                    contact.getEmail(), contact.getMobilePhone()));
        }
        writer.close(); //close the file
    }

}