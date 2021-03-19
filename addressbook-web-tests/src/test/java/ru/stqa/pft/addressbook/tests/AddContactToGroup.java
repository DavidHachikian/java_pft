package ru.stqa.pft.addressbook.tests;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class AddContactToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("test_name2").withLastname("test_lastname2"), true);
      app.goTo().homePage();
    }

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Group2"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testAddContactToGroup() {
    app.goTo().homePage();
    ContactData contactData = app.db().contactNotInGroup();
    app.contact().selectContactNotInGroup(contactData);
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    app.contact().selectGroup(group);
    app.contact().pushButtonAddToGroup();
    ContactData contactData1 = app.db().contactById(contactData.getId());
    assertTrue(contactData1.getGroups().contains(group));
  }
}






    /*Contacts beforeContacts = app.db().contacts();
    ContactData contactAdded = beforeContacts.iterator().next();
    Groups beforeGroups = app.db().groups();
    GroupData modifiedGroup = beforeGroups.iterator().next();

    app.goTo().homePage();
    if (!contactAdded.getGroups().isEmpty() && contactAdded.getGroups().contains(modifiedGroup)) {
      app.contact().removeContactFromGroup(contactAdded, modifiedGroup);
      assertThat(contactAdded.getGroups().without(modifiedGroup), equalTo(app.db().contacts().stream()
              .filter((c) -> c.getId() == contactAdded.getId()).collect(Collectors.toList()).get(0).getGroups()));
      app.goTo().homePage();
    }
    app.contact().selectDisplayGroup("[all]");
    app.contact().addContactToGroup(contactAdded, modifiedGroup);
    app.goTo().homePage();
    assertThat(contactAdded.getGroups().withAdded(modifiedGroup), equalTo(app.db().contacts().stream()
            .filter((c) -> c.getId() == contactAdded.getId()).collect(Collectors.toList()).get(0).getGroups()));*/