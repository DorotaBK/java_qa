package dbk.qacourse.addressbook.appmanager;

import dbk.qacourse.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void selectGroupByID(int id) {
        // select a specific element on the page
        if (!wd.findElement(By.cssSelector("input[value='" + id + "']")).isSelected()) {
            wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
        }
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

    // create group if it's absent - precondition for group editing/modification tests
    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void modify(int index, GroupData currentGroup) {
        selectGroup(index);
        initGroupModification();
        fillGroupForm(currentGroup);
        submitGroupModification();
        returnToGroupPage();
    }

    public void delete(int index) {
        selectGroup(index);
        deleteSelectedGroups();
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupByID(group.getId());
        deleteSelectedGroups();
        returnToGroupPage();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        // fill the List with objects - find and add all items with the tag "span" and class "group"
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));

        for (WebElement element : elements) {
            // get the group name from 'group page' -> <span class="group">
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            // create a new object and add them to the List 'groups'
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }

    public Set<GroupData> all() {
        Set<GroupData> groups = new HashSet<>();
        // fill the List with objects - find and add all items with the tag "span" and class "group"
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));

        for (WebElement element : elements) {
            // get the group name from 'group page' -> <span class="group">
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            // create a new object and add them to the List 'groups'
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
