package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        System.out.println("number of groups at the beginning: " + before.size());

        GroupData group = new GroupData("grupa_6", "grupa_6","grupa 6");
        app.getGroupHelper().createGroup(group);

        // comparison of size of the Lists (before and after)
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
        System.out.println("number of groups at the end: " + after.size());

        // direct comparison - only Java8 and next
        before.add(group);
        Comparator<? super GroupData> byId = (gr1, gr2) -> (Integer.compare(gr1.getId(), gr2.getId()));
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
