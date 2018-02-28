package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goToGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("testowaZ", "TestTestZZ", "Pierwszy test Z"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
