package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

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
  public void testContactDeletion() {
    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}

 //ContactData("test_name", "test_middlename", "test_lastname", "111", "222", "333", "666-55-77", "test@gmail.com", "xxx")