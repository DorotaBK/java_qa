package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groups().groupList().size() == 0){
            app.groups().create(new GroupData("nowa6!", null, null));
        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.groups().groupList();
        System.out.println("number of groups before test: " + before.size());

        int index = before.size() - 1;  //the element I want to delete
        app.groups().delete(index);

        // comparing the size of collections
        List<GroupData> after = app.groups().groupList();
        Assert.assertEquals(after.size(), before.size() - 1 );
        System.out.println("number of groups at the end: " + after.size());

        // comparing of whole collections
        before.remove(index);
        Assert.assertEquals(after, before);
    }
}
