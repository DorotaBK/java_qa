package dbk.qacourse.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import dbk.qacourse.addressbook.model.GroupData;
import dbk.qacourse.addressbook.model.Groups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    Logger logger = LoggerFactory.getLogger(GroupCreationTests.class);

    // for JSON data file:
    @DataProvider
    public Iterator<Object[]> validGroupsFromJSON() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());  //List<GroupData>.class
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    // for XML data file:
    @DataProvider
    public Iterator<Object[]> validGroupsFromXML() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    // for CSV data file:
    @DataProvider
    public Iterator<Object[]> validGroupsFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @Test(dataProvider = "validGroupsFromJSON")
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