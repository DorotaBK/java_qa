package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groups().all().size() == 0){
            app.groups().create(new GroupData().withName("grupa_6"));
        }
    }

    @Test
    public void testGroupModification() {
        Set<GroupData> before = app.groups().all();
        System.out.println("number of groups before test: " + before.size());

        // random selection of an element to be modified
        GroupData modifiedGroup = before.iterator().next();
        GroupData currentGroup = new GroupData().withId(modifiedGroup.getId()).withName("grupa_test")
                                .withHeader("test").withFooter("tst");
        app.groups().modify(currentGroup);

        // comparison of size of the Lists (before and after)
        Set<GroupData> after = app.groups().all();
        Assert.assertEquals(after.size(), before.size());
        System.out.println("number of groups after test: " + after.size());

        // direct comparison - only Java8 and next
        before.remove(modifiedGroup);
        before.add(currentGroup);
        Assert.assertEquals(before, after);
    }
}
