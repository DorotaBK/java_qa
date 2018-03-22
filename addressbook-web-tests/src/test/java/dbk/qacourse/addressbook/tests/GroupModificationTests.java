package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groups().list().size() == 0){
            app.groups().create(new GroupData().withName("grupa_6"));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.groups().list();
        System.out.println("number of groups before test: " + before.size());

        int index = before.size() - 2;     // the element that I want to modify
        GroupData currentGroup = new GroupData()
                .withId(before.get(index).getId()).withName("grupa_A").withHeader("A").withFooter("AA");
        app.groups().modify(index, currentGroup);

        // comparison of size of the Lists (before and after)
        List<GroupData> after = app.groups().list();
        Assert.assertEquals(after.size(), before.size());
        System.out.println("number of groups after test: " + after.size());

        // direct comparison - only Java8 and next
        before.remove(index);
        before.add(currentGroup);
        Comparator<? super GroupData> byId = (gr1, gr2) -> (Integer.compare(gr1.getId(), gr2.getId()));
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
