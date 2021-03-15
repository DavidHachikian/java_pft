package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditionsSorted() {
    if (app.db().contacts().size() == 0) {
      app.contact().initContactCreation();
      app.contact().create(new ContactData().withFirstname("test_name").withLastname("test_lastname").withGroup("xxx"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, CoreMatchers.equalTo(before.without(deletedContact)));
  }



/*
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
*/

}

 //ContactData("test_name", "test_middlename", "test_lastname", "111", "222", "333", "666-55-77", "test@gmail.com", "xxx")

//new ContactData("test_name", null, "test_lastname", null, null, null, null, null, "xxx")

//new ContactData().withFirstname("test_name").withMiddlename("test_middlename").withLastname("test_lastname")
//            .withTitle("111").withCompany("222").withAddress("333").withHomephone("666-55-77").withEmail("test@gmail.com").withGroup("xxx");