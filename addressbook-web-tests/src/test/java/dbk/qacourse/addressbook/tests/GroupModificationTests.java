package dbk.qacourse.addressbook.tests;

import dbk.qacourse.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

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

        int before = app.getGroupHelper().getGroupCount();
        System.out.println("number of groups before test: " + before);
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("nowa_7", "nowa_7", "nowa_7"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before );
        System.out.println("number of groups at the end: " + before);
    }
}
