package dbk.qacourse.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase{

    @Test
    public void testGroupCreation() {
        goToGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("testowaZ", "TestTestZZ", "Pierwszy test Z"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
