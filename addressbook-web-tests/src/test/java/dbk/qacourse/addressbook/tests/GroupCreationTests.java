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

        // comparison of size of the Lists (before and after)
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
        System.out.println("number of groups at the end: " + after.size());

        // Comparison of whole collections in Java8
        // step 1: max object found with lambda expression -> get his id -> new group has max id:
        group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);

        // step 2: convert list into set and comparison of old and new collections:
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
