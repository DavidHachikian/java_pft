package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.junit.Ignore;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Ignore
  //@Test
  public void testContactDeletion() {
    app.goTo().goToNewProfile();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name", "test_surname", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContactDeletion();
    app.getContactHelper().initContactDeletion();
    app.getContactHelper().submitContactDeletion();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
