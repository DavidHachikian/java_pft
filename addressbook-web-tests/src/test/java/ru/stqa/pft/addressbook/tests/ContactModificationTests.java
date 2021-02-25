package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testModifyContactSorted() {
    if (! app.getContactHelper().isThereAContact())
    {
      app.getContactHelper().initContactCreation();
      app.getContactHelper().createContact(new ContactData("test_name", null, "test_lastname", null, null, null, null, null, "xxx"), true);
      app.getNavigationHelper().returnToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "test_name", null, "test_lastname", null, null, null, null, null, "xxx");
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testModifyContact() {
    if (! app.getContactHelper().isThereAContact())
    {
      app.getContactHelper().initContactCreation();
      app.getContactHelper().createContact(new ContactData("test_name", null, "test_lastname", null, null, null, null, null, "xxx"), true);
      app.getNavigationHelper().returnToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "test_name", null, "test_lastname", null, null, null, null, null, "xxx");

    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    // Преобразовываем списки в множества (set)
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}