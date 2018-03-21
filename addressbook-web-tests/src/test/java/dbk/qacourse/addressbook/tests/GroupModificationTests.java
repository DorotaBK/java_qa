package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();
        //int start = app.getGroupHelper().getGroupCount();
        //System.out.println("number of groups at the beginning: " + start);

        //checking pre-conditions and providing them if necessary
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("nowa_test", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        System.out.println("number of groups before test: " + before.size());

        int groupToModify = before.size() - 2;     // the element that I want to modify
        GroupData currentGroup = new GroupData(before.get(groupToModify).getId(),"grupa_testA","A","AA");
        app.getGroupHelper().modifyGroup(groupToModify, currentGroup);

        // comparison of size of the Lists (before and after)
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
        System.out.println("number of groups after test: " + after.size());

        // direct comparison - only Java8 and next
        before.remove(groupToModify);
        before.add(currentGroup);
        Comparator<? super GroupData> byId = (gr1, gr2) -> (Integer.compare(gr1.getId(), gr2.getId()));
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
