package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();
        int start = app.getGroupHelper().getGroupCount();
        System.out.println("number of groups at the beginning: " + start);

        //checking pre-conditions and providing them if necessary
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("nowa6!", null, null));
        }

        List<GroupData> before = app.getGroupHelper().getGroupList();
        System.out.println("number of groups before test: " + before.size());

        int modifGroup = before.size() - 2;
        app.getGroupHelper().selectGroup(modifGroup);
        app.getGroupHelper().initGroupModification();
        GroupData currentGroup = new GroupData("grupa_2", "grupa_2", "grupa_2");
        app.getGroupHelper().fillGroupForm(currentGroup);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
        System.out.println("number of groups at the end: " + before.size());

        before.remove(modifGroup);
        before.add(currentGroup);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}
