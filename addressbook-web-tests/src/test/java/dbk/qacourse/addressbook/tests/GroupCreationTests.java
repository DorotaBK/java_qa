package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        System.out.println("number of groups at the beginning: " + before.size());

        GroupData group = new GroupData("grupa_4", "grupa_4","grupa 4");
        app.getGroupHelper().createGroup(group);

        // compare the size of the List before and after
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
        System.out.println("number of groups at the end: " + after.size());

        // comparing of whole collections (requires conversion of the list into a set)
        int max = 0;
        for (GroupData gr : after) {
            if (gr.getId() > max) {
                max = gr.getId();
            }
        }
        group.setId(max);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
