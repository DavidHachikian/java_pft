package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromTheGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditionsSorted() {
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
  public void testDeleteContactFromTheGroup() {
    Contacts beforeContacts = app.db().contacts();
    ContactData contactAdded = beforeContacts.iterator().next();
    Groups beforeGroups = app.db().groups();
    GroupData modifiedGroup = beforeGroups.iterator().next();
    app.goTo().homePage();

    if (contactAdded.getGroups().isEmpty() || !contactAdded.getGroups().contains(modifiedGroup)) {
      app.contact().selectDisplayGroup("[all]");
      app.contact().addContactToGroup(contactAdded, modifiedGroup);
      assertThat(contactAdded.getGroups().withAdded(modifiedGroup), equalTo(app.db().contacts().stream()
              .filter((c) -> c.getId() == contactAdded.getId()).collect(Collectors.toList()).get(0).getGroups()));
      app.goTo().homePage();
    }

    app.contact().removeContactFromGroup(contactAdded, modifiedGroup);
    app.goTo().homePage();
    app.contact().selectDisplayGroup("[all]");
    assertThat(contactAdded.getGroups().without(modifiedGroup), equalTo(app.db().contacts().stream().
            filter((c) -> c.getId() == contactAdded.getId()).collect(Collectors.toList()).get(0).getGroups()));
  }
}

