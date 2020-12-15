package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Ignore
  //@Test
  public void testContactModification() {
    //int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name", "test_surname", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.get(before.size()-1).getId());
    ContactData contact = new ContactData(before.get(before.size()-1).getId(), "test_name", "test_surname", "test1");
    app.getContactHelper().fillContactCreation(contact);
    app.getContactHelper().submitContactModification();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    //int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);

  }
}
