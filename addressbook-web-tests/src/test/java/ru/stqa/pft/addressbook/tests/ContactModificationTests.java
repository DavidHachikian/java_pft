package ru.stqa.pft.addressbook.tests;

import org.junit.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactCreation(new ContactData("test_name", "test_surname", null), false);
    app.getContactHelper().submitContactModification();

  }
}
