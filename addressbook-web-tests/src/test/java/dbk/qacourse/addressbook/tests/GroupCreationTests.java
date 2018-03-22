package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        List<GroupData> before = app.groups().list();
        System.out.println("number of groups at the beginning: " + before.size());

        GroupData group = new GroupData().withName("grupa_6");
        app.groups().create(group);

        // comparison of size of the Lists (before and after)
        List<GroupData> after = app.groups().list();
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
