package dbk.qacourse.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String nick;
    private final String address;
    private final String mobile;
    private final String email;
    private String group;

    public ContactData(String firstname, String lastname, String nick, String address, String mobile, String email,
                       String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nick = nick;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
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
        return group;   // after 'Create getter for '...', getting the value
    }
}
