package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("nowa6!", null, "nowa_6"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("nowa_7", "nowa_7", "nowa_7"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }
}
