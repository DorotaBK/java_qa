package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        System.out.println("number of groups at the beginning: " + before.size());

        app.getGroupHelper().createGroup(new GroupData("grupa_1", null, "grupa pierwsza"));

        List<GroupData> after = app.getGroupHelper().getGroupList();
        // compare the size of the List before and after
        Assert.assertEquals(after.size(), before.size() + 1);
        System.out.println("number of groups at the end: " + after.size());
    }
}
