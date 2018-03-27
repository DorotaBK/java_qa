package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Groups before = app.groups().all();
        System.out.println("number of groups before test: " + before.size());

        GroupData modifiedGroup = before.iterator().next();  //random selection of an element to be modified
        GroupData currentGroup = new GroupData().withId(modifiedGroup.getId()).withName("grupa_test")
                                .withHeader("test").withFooter("tst");
        app.groups().modify(currentGroup);

        // comparison of size of the Lists
        Groups after = app.groups().all();
        Assert.assertEquals(after.size(), before.size());
        System.out.println("number of groups after test: " + after.size());

        // direct comparison with Hamcrest and guava
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(currentGroup)));


    }
}
