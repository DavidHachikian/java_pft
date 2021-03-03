// Generated by Selenium IDE
package ru.stqa.pft.addressbook.tests;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddContact() {
    Contacts before = app.contact().all();
    app.contact().initContactCreation();
    ContactData contact = new ContactData().withFirstname("test_name").withMiddlename("test_middlename").withLastname("test_lastname")
            .withTitle("111").withCompany("222").withAddress("333").withHomephone("666-55-77").withEmail("test@gmail.com").withGroup("xxx");
    app.contact().create(contact, true);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.
            withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }

  /*@Test
  public void testAddContactSortedList() {
    List<ContactData> before = app.contact().list();
    app.contact().initContactCreation();

    ContactData contact = new ContactData().withFirstname("test_name").withMiddlename("test_middlename").withLastname("test_lastname")
            .withTitle("111").withCompany("222").withAddress("333").withHomephone("666-55-77").withEmail("test@gmail.com").withGroup("xxx");

    app.contact().create(contact, true);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testAddContact() {
    List<ContactData> before = app.contact().list();
    app.contact().initContactCreation();

    ContactData contact = new ContactData().withFirstname("test_name").withMiddlename("test_middlename").withLastname("test_lastname")
            .withTitle("111").withCompany("222").withAddress("333").withHomephone("666-55-77").withEmail("test@gmail.com").withGroup("xxx");
    ;

    app.contact().create(contact, true);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    // Находим max Id, и добавляем в contact
    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }*/
}


