package dbk.qacourse.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String firstname;
    private final String lastname;
    private final String nick;
    private final String address;
    private final String mobile;
    private final String email;
    private String group;

    // constructor for a contact with a known id (taken from www)
    public ContactData(int id, String firstname, String lastname, String nick, String address, String mobile, String email,
                       String group) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nick = nick;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    // constructor for a contact with an unknown id (not downloaded from www, only created in the test)
    public ContactData(String firstname, String lastname, String nick, String address, String mobile, String email,
                       String group) {
        this.id = 0;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nick = nick;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    // Getter
    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNick() {
        return nick;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;   // after "Create getter for 'group'", getting the value
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(address, that.address) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstname, lastname, address, mobile, email);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }
}
