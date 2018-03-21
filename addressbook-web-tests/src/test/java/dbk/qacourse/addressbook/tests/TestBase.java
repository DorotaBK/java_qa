package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.appmanager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeSuite;

public class TestBase {

     protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

     @BeforeSuite
    public void setUp() throws Exception {
        app.init(); // method from ApplicationManager
    }

     @AfterSuite
    public void tearDown() {
        app.stop(); // method from ApplicationManager
    }

 }
