package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {


    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name", "test_surname", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    //int before = app.getContactHelper().getContactCount(); //контролль количества контактов до
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"test_name2", "test_surname", null);
    app.getContactHelper().fillContactCreation(contact);
    app.getContactHelper().submitContactModification();
    List<ContactData> after = app.getContactHelper().getContactList();
    //int after = app.getContactHelper().getContactCount(); //контролль количества контактов после
    Assert.assertEquals(after.size(), before.size()); //количество контактов не изменилось

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());  //с1 и с2 - контакты, которые сравниваются
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}