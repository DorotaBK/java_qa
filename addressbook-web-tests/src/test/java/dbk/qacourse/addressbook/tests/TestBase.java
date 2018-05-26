package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.appmanager.ApplicationManager;
import dbk.qacourse.addressbook.model.ContactData;
import dbk.qacourse.addressbook.model.Contacts;
import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

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
            Groups uiGroups = app.groups().all();
            Groups dbGroups = app.db().groups();
            assertThat(uiGroups, equalTo(dbGroups.stream().map((g) -> new GroupData().withId(g.getId())
                    .withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts uiContacts = app.contacts().splitDataFromUI();
            Contacts dbContacts = app.db().contacts();

            assertThat(uiContacts, equalTo(dbContacts.stream().map((g) -> new ContactData().withId(g.getId())
                    .withLastname(g.getLastname()).withFirstname(g.getFirstname()).withAddress(g.getAddress())
                    .withEmail(g.getEmail()).withEmail2(g.getEmail2()).withEmail3(g.getEmail3())
                    .withHomePhone(g.getHomePhone()).withMobilePhone(g.getMobilePhone()).withWorkPhone(g.getWorkPhone()))
                    .collect(Collectors.toSet())));
        }
    }

    /* WRONG!!! didn't work
    public void verifyContactListInUIwithMerge(){
        if (Boolean.getBoolean("verifyUIwithMERGE")) {
            Contacts uiContacts = app.contacts().all();
            Contacts dbContacts = app.db().contacts();

            assertThat(uiContacts, equalTo(dbContacts.mergePhones().mergeEmails().stream().map((g) -> new ContactData().withId(g.getId())
                    .withLastname(g.getLastname()).withFirstname(g.getFirstname()).withAddress(g.getAddress())
                    .withAllEmails(g.getAllEmails()).withAllPhones(g.getAllPhones()))
                    .collect(Collectors.toSet())));
        }
    }
    */
}