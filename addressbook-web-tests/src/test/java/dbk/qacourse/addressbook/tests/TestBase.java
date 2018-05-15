package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.appmanager.ApplicationManager;
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
            Contacts uiContacts = app.contacts().all();
            assertThat(uiContacts, equalTo(dbContacts.stream().map((g) -> new ContactData().withId(g.getId())
                            .withLastname(g.getLastname())
                            .withFirstname(g.getFirstname())
                            .withAddress(g.getAddress())
                            .withAllEmails(g.getAllEmails())
                            .withAllPhones(g.getAllPhones()))
                    .collect(Collectors.toSet())));
        }
    }
}