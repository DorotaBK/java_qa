package dbk.qacourse.addressbook.model;

import com.google.common.collect.ForwardingSet;
import dbk.qacourse.addressbook.tests.TestBase;

import java.util.*;
import java.util.stream.Collectors;

public class Contacts extends ForwardingSet<ContactData> {

    //copy of an existing object
    private Set<ContactData> delegate;

    //constructors
    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactData>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<ContactData>();
    }

    public Contacts(Collection<ContactData> contacts) {
        this.delegate = new HashSet<ContactData>(contacts);
    }

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    // new objects
    public Contacts withAdded(ContactData contact) {
        Contacts contacts = new Contacts(this);   //create a copy of an existing collection
        contacts.add(contact);
        return contacts;
    }

    public Contacts without(ContactData contact) {
        Contacts contacts = new Contacts(this);   //create a copy of an existing collection
        contacts.remove(contact);
        return contacts;
    }
}