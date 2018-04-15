package dbk.qacourse.addressbook.generators;

import dbk.qacourse.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);     //file path
        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);
    }

    private static List<ContactData> generateContacts(int count) {
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

    private static void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
                    contact.getEmail(), contact.getMobilePhone()));
        }
        writer.close(); //close the file
    }

}
