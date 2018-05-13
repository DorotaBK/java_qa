package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.groups().create(new GroupData().withName("grupa_6"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().groups();
        System.out.println("number of groups before test: " + before.size());
        GroupData modifiedGroup = before.iterator().next();  //random selection of an element to be modified
        GroupData currentGroup = new GroupData().withId(modifiedGroup.getId()).withName("grupa_test")
                                .withHeader("test").withFooter("tst");
        app.goTo().groupPage();
        app.groups().modify(currentGroup);
        Assert.assertEquals(app.groups().count(), before.size()); //comparison of size of the Lists
        Groups after = app.db().groups();
        System.out.println("number of groups after test: " + after.size());
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(currentGroup))); //comparison with Hamcrest and guava
    }
}
