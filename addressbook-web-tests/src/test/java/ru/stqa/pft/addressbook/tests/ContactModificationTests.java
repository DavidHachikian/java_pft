package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Before
  public void ensurePreconditionsSorted() {
    if (! app.getContactHelper().isThereAContact())
    {
      app.getContactHelper().initContactCreation();
      app.getContactHelper().createContact(new ContactData("test_name", null, "test_lastname", null, null, null, null, null, "xxx"), true);
      app.getNavigationHelper().returnToHomePage();
    }
  }

  @Test
  public void testModifyContactSorted() {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "test_name", null, "test_lastname", null, null, null, null, null, "xxx");
    app.getContactHelper().modifyContact(index, contact);
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
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
    if (! app.getContactHelper().isThereAContact())
    {
      app.getContactHelper().initContactCreation();
      app.getContactHelper().createContact(new ContactData("test_name", null, "test_lastname", null, null, null, null, null, "xxx"), true);
      app.getNavigationHelper().returnToHomePage();
    }
  }

  @Test
  public void testModifyContact() {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "test_name", null, "test_lastname", null, null, null, null, null, "xxx");
    app.getContactHelper().modifyContact(index, contact);
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    // Преобразовываем списки в множества (set)
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }


}