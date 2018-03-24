package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groups().list().size() == 0){
            app.groups().create(new GroupData().withName("grupa_6"));
        }
    }

    @Test
    public void testGroupDeletion() {
        Set<GroupData> before = app.groups().all();
        System.out.println("number of groups before test: " + before.size());
        GroupData deletedGroup = before.iterator().next();
        app.groups().delete(deletedGroup);

        // comparing the size of collections
        Set<GroupData> after = app.groups().all();
        Assert.assertEquals(after.size(), before.size() - 1 );
        System.out.println("number of groups at the end: " + after.size());

        // comparing of whole collections
        before.remove(deletedGroup);
        Assert.assertEquals(after, before);
    }
}
