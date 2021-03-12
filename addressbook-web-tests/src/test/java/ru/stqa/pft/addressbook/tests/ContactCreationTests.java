// Generated by Selenium IDE
package ru.stqa.pft.addressbook.tests;
import com.solidfire.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType()); // List<ContactData>>.class
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }


  @Test(dataProvider = "validContactsFromJson")
  public void testAddContactFromJson(ContactData contact) {
    Contacts before = app.contact().all();
    app.contact().initContactCreation();
    app.contact().create(contact, true);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.
            withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }
}















/*package ru.stqa.pft.addressbook.tests;
import com.beust.jcommander.Parameter;
import org.junit.Ignore;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {"firstname1", "lastname1", "address1", "homephone1", "mobilephone1", "workphone1", "email@email11",
    "email@email21", "email@email31"});
    list.add(new Object[] {"firstname2", "lastname2", "address2", "homephone2", "mobilephone2", "workphone2", "email@email12",
            "email@email22", "email@email32"});
    list.add(new Object[] {"firstname3", "lastname3", "address3", "homephone3", "mobilephone3", "workphone3", "email@email13",
            "email@email23", "email@email33"});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testAddContact(String firstname, String lastname, String address, String homephone, String mobilephone,
                             String workphone, String email1, String email2, String email3) {
    Contacts before = app.contact().all();
    app.contact().initContactCreation();
    //File photo = new File("src/test/resources/2.jpg");
    ContactData contact = new ContactData().withFirstname(firstname).withLastname(lastname)
            .withAddress(address).withHomephone(homephone).withMobilephone(mobilephone).withWorkphone(workphone)
            .withEmail(email1).withEmail2(email2).withEmail3(email3);
    app.contact().create(contact, true);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.
            withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Ignore
  public void currentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath() + " -- this is my print");
    File photo = new File("src/test/resources/2.jpg");
    // Выводим на консоль полный путь к файлу
    System.out.println(photo.getAbsolutePath());
    // Проверяем, что файл существует
    System.out.println(photo.exists());
  }*/



  /*@Test
  public void testAddContactSortedList() {
    List<ContactData> before = app.contact().list();
    app.contact().initContactCreation();

    ContactData contact = new ContactData().withFirstname("test_name").withMiddlename("test_middlename").withLastname("test_lastname")
            .withTitle("111").withCompany("222").withAddress("333").withHomephone("666-55-77").withEmail("test@gmail.com").withGroup("xxx");

    app.contact().create(contact, true);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testAddContact() {
    List<ContactData> before = app.contact().list();
    app.contact().initContactCreation();

    ContactData contact = new ContactData().withFirstname("test_name").withMiddlename("test_middlename").withLastname("test_lastname")
            .withTitle("111").withCompany("222").withAddress("333").withHomephone("666-55-77").withEmail("test@gmail.com").withGroup("xxx");
    ;

    app.contact().create(contact, true);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    // Находим max Id, и добавляем в contact
    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}*/


