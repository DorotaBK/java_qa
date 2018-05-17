package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.appmanager.ApplicationManager;
import dbk.qacourse.addressbook.appmanager.ContactHelper;
import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test " + m.getName());
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.groups().all();
            assertThat(uiGroups, equalTo(dbGroups.stream().map((g) -> new GroupData().withId(g.getId())
                    .withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contacts().splitDataFromUI();

            assertThat(uiContacts, equalTo(dbContacts.stream().map((g) -> new ContactData().withId(g.getId())
                    .withLastname(g.getLastname()).withFirstname(g.getFirstname()).withAddress(g.getAddress())
                    .withEmail(g.getEmail()).withEmail2(g.getEmail2()).withEmail3(g.getEmail3())
                    .withHomePhone(g.getHomePhone()).withMobilePhone(g.getMobilePhone()).withWorkPhone(g.getWorkPhone()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUIwithMERGE(){
        if (Boolean.getBoolean("verifyUIwithMERGE")) {
            Contacts dbContacts = app.db().contacts();
            Contacts dbPhones = dbContacts.mergePhones();
            Contacts dbEmails = app.db().contacts().mergeEmailsFromDB();
            ContactData uiContacts = app.contacts().all().iterator().next();

            assertThat(uiContacts.getId(), equalTo(dbContacts.stream().map((g) -> new ContactData().withId(g.getId()))));
            assertThat(uiContacts.getLastname(), equalTo(dbContacts.stream().map((g) -> new ContactData().withLastname(g.getLastname()))));
            assertThat(uiContacts.getFirstname(), equalTo(dbContacts.stream().map((g) -> new ContactData().withFirstname(g.getFirstname()))));
            assertThat(uiContacts.getAddress(), equalTo(dbContacts.stream().map((g) -> new ContactData().withAddress(g.getAddress()))));
            //assertThat(uiContacts.getHomePhone(), equalTo(dbContacts.stream().map((g) -> new ContactData().withHomePhone(g.getHomePhone()))));
            //assertThat(uiContacts.getMobilePhone(), equalTo(dbContacts.stream().map((g) -> new ContactData().withMobilePhone(g.getMobilePhone()))));
            //assertThat(uiContacts.getWorkPhone(), equalTo(dbContacts.stream().map((g) -> new ContactData().withWorkPhone(g.getWorkPhone()))));
            assertThat(uiContacts.getAllPhones(), equalTo(dbContacts.stream().map((g) -> new ContactData().withAllPhones(g.getAllPhones()))));
            //assertThat(uiContacts.getEmail(), equalTo(dbContacts.stream().map((g) -> new ContactData().withEmail(g.getEmail()))));
            //assertThat(uiContacts.getEmail2(), equalTo(dbContacts.stream().map((g) -> new ContactData().withEmail2(g.getEmail2()))));
            //assertThat(uiContacts.getEmail3(), equalTo(dbContacts.stream().map((g) -> new ContactData().withEmail3(g.getEmail3()))));
            assertThat(uiContacts.getAllEmails(), equalTo(dbContacts.stream().map((g) -> new ContactData().withAllEmails(g.getEmail()))));
        }
    }

    public String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).stream()
                .filter((s) -> !s.equals(""))
                .map(TestBase::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "")
                .replaceAll("[-()]", "");
    }

    public String mergeEmailsFromDB(ContactData contact) {
        return Arrays.asList(app.db().contacts().stream().map((g)
                -> new ContactData().withEmail(g.getAllEmails()).withEmail2(g.getEmail2()).withEmail3(g.getEmail3())))
                .filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}