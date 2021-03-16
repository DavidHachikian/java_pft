package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditionsSorted() {
    if (app.db().contacts().size() == 0) {
      app.contact().initContactCreation();
      app.contact().create(new ContactData().withFirstname("test_name").withLastname("test_lastname")/*.withGroup("xxx")*/, true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testModifyContact() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).
            withFirstname("test_name").withLastname("test_lastname")/*.withGroup("xxx")*/;
    app.contact().modifyById(contact);
    app.goTo().homePage();
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }

/*
  @Test
  public void testModifyContactSorted() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData()
            .withId(before.get(index).getId()).withFirstname("test_name").withLastname("test_lastname").withGroup("xxx");
    app.contact().modify(index, contact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


  @Before
  public void ensurePreconditions() {
    if (! app.contact().isThereAContact())
    {
      app.contact().initContactCreation();
      app.contact().create(new ContactData().withFirstname("test_name").withLastname("test_lastname").withGroup("xxx"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testModifyContact() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData()
            .withId(before.get(index).getId()).withFirstname("test_name").withLastname("test_lastname").withGroup("xxx");    app.contact().modify(index, contact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    // Преобразовываем списки в множества (set)
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

*/
}

//new ContactData().withId(before.get(index).getId()).withFirstname("test_name").withLastname("test_lastname").withGroup("xxx");