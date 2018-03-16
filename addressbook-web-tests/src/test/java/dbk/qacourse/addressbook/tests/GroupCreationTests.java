package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        System.out.println("number of groups at the beginning: " + before);
        app.getGroupHelper().createGroup(new GroupData("jeszcze_jedna", null, "grupa"));
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
        System.out.println("number of groups at the end: " + after);
    }
}
