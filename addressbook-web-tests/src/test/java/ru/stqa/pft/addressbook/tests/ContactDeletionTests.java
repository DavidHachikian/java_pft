package ru.stqa.pft.addressbook.tests;

import org.junit.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {

    app.getContactHelper().selectContactDeletion();
    app.getContactHelper().initContactDeletion();
    app.getContactHelper().submitContactDeletion();

  }
}
