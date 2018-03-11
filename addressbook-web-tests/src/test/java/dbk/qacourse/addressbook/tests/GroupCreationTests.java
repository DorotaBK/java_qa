package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().createGroup(new GroupData("nowa6!", null, "nowa_6"));
    }

}
