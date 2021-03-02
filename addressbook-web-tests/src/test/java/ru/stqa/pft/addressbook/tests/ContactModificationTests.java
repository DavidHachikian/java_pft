package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Before
  public void ensurePreconditionsSorted() {
    if (app.contact().list().size() == 0)
    {
      app.contact().initContactCreation();
      app.contact().create(new ContactData("test_name", null, "test_lastname", null, null, null, null, null, "xxx"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testModifyContactSorted() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "test_name", null, "test_lastname", null, null, null, null, null, "xxx");
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
      app.contact().create(new ContactData("test_name", null, "test_lastname", null, null, null, null, null, "xxx"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testModifyContact() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "test_name", null, "test_lastname", null, null, null, null, null, "xxx");
    app.contact().modify(index, contact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    // Преобразовываем списки в множества (set)
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }


}