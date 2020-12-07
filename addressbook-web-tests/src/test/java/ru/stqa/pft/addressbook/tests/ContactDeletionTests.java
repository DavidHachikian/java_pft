package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {

    if (! app.getContactHelper().isThereAContact()) {

      app.getContactHelper().createContact(new ContactData("test_name", "test_surname", "test1"), true);
    }
    app.getContactHelper().selectContactDeletion();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().initContactDeletion();
    app.getContactHelper().submitContactDeletion();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);

  }
}
