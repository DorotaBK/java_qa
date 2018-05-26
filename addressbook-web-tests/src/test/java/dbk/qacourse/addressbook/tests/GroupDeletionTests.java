package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.groups().create(new GroupData().withName("grupa_G"));
        }
    }

    @Test
    public void testGroupDeletion() {
        Groups before = app.db().groups();
        System.out.println("number of groups before test: " + before.size());
        GroupData deletedGroup = before.iterator().next();  //random selection of an element to be removed
        app.goTo().groupPage();
        app.groups().delete(deletedGroup);
        assertEquals(app.db().groups().size(), before.size() - 1 ); //comparing the size of collections
        Groups after = app.db().groups();
        System.out.println("number of groups at the end: " + after.size());
        assertThat(after, equalTo(before.without(deletedGroup)));   // direct comparison with Hamcrest and guava
        verifyGroupListInUI();
    }
}
