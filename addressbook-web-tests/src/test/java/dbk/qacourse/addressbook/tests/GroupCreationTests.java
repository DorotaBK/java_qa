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
        System.out.println("number of groups at the beginning: " + before.size());
        GroupData group = new GroupData().withName("grupa_7");
        app.groups().create(group);

        // comparison of size of the Lists (before and after)
        Groups after = app.groups().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        System.out.println("number of groups at the end: " + after.size());

        // direct comparison with Hamcrest and guava
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((gr) -> gr.getId()).max().getAsInt()))));
    }
}
