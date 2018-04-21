package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new GroupData().withName("testA").withHeader("header A").withFooter("footer A")});
        list.add(new Object[]{new GroupData().withName("testB").withHeader("header B").withFooter("footer B")});
        list.add(new Object[]{new GroupData().withName("testC").withHeader("header C").withFooter("footer C")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.groups().all();
        app.groups().create(group);
        assertThat(app.groups().count(), equalTo(before.size() + 1));
        Groups after = app.groups().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((gr) -> gr.getId()).max().getAsInt()))));
    }

    //negative test; yet without data provider
    @Test(enabled = false)
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