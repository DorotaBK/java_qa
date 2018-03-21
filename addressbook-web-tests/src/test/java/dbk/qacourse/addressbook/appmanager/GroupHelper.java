package dbk.qacourse.addressbook.appmanager;

import dbk.qacourse.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void selectGroup(int index) {
        // select a specific element on the page
        if (!wd.findElements(By.name("selected[]")).get(index).isSelected()) {
            wd.findElements(By.name("selected[]")).get(index).click();
        }
        /* select the first element on the page
        if (!wd.findElement(By.name("selected[]")).isSelected()) {
            click(By.name("selected[]"));}
        */
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    // check the existence of group on the page - precondition for group editing/modification tests
    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    // create group if it's absent - precondition for group editing/modification tests
    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void modifyGroup(int index, GroupData currentGroup) {
        selectGroup(index);
        initGroupModification();
        fillGroupForm(currentGroup);
        submitGroupModification();
        returnToGroupPage();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<GroupData>();
        // fill the List with objects - find and add all items with the tag "span" and class "group"
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));

        for (WebElement element : elements){
            // get the group name from 'group page' -> <span class="group">
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            // create a new object with the name from page; on 'group page' the header and footer are absent!
            GroupData group = new GroupData(id, name, null, null);

            // add new element 'group' to the List 'groups'
            groups.add(group);
        }
        return groups;
    }
}
