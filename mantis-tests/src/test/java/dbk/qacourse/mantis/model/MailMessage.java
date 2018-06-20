package dbk.qacourse.mantis.model;

public class MailMessage {

    public String to;
    public String text;

    public MailMessage(String to, String text) {
        this.to = to;       //recipient
        this.text = text;   //content
    }
}
