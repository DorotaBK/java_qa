package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.groups().all();
        System.out.println("before test: " + before.size());
        GroupData group = new GroupData().withName("grupa_A");
        app.groups().create(group);
        assertThat(app.groups().count(), equalTo(before.size() + 1)); //comparison of size of the Lists
        Groups after = app.groups().all();
        System.out.println("after test: " + after.size());
        //comparison all with Hamcrest and guava
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((gr) -> gr.getId()).max().getAsInt()))));
    }

    @Test
    public void testNegativeGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.groups().all();
        GroupData group = new GroupData().withName("grupa'_B");
        app.groups().create(group);
        assertThat(app.groups().count(), equalTo(before.size())); //comparison of size of the Lists
        Groups after = app.groups().all();
        assertThat(after, equalTo(before));  //comparison all with Hamcrest and guava
    }
}