package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name", "test_surname", "test1"), true);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactCreation(new ContactData("test_name", "test_surname", null), false);
    app.getContactHelper().submitContactModification();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);

  }
}
