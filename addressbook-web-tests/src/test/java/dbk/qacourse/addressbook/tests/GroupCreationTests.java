package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Set<GroupData> before = app.groups().all();
        System.out.println("number of groups at the beginning: " + before.size());

        GroupData group = new GroupData().withName("grupa_7");
        app.groups().create(group);

        // comparison of size of the Lists (before and after)
        Set<GroupData> after = app.groups().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        System.out.println("number of groups at the end: " + after.size());

        // direct comparison - only Java8 and next
        group.withId(after.stream().mapToInt((gr) -> gr.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);
    }
}
