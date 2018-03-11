package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("nowa6!", null, "nowa_6"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }

}
