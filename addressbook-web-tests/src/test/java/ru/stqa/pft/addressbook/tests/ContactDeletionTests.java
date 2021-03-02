package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

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
  public void testContactDeletion() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }


}

 //ContactData("test_name", "test_middlename", "test_lastname", "111", "222", "333", "666-55-77", "test@gmail.com", "xxx")