package ru.stqa.pft.addressbook.appmanager;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void delete(int index) {
    selectContact(index);
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));
    click(By.id("logo"));
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));
    click(By.id("logo"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("tr[name='entry'] input[value ='" + id + "']")).click();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href ='edit.php?id=%s']", id))).click();
  }

  public void selectContactNotInGroup(ContactData contact) {
    wd.findElement(By.xpath(String.format("//input[@type='checkbox']", contact.getId()))).click();
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());

    type(By.name("middlename"), contactData.getMiddlename());

    type(By.name("lastname"), contactData.getLastname());

    //attach(By.name("photo"), contactData.getPhoto()); Закомментирвал при выполнении задания 15



    /*if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }
    else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }*/
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(ContactData contact, boolean creation) {
    fillContactForm(contact);
    submitContactCreation();
  }

  public void modify(int index, ContactData contact) {
    initContactModification(index);
    fillContactForm(contact);
    submitContactModification();
  }

  public void modifyById(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact);
    submitContactModification();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return (wd.findElements(By.name("selected[]")).size());

  }
/*
   public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement row : rows) {
      int id = Integer.parseInt(row.findElement(By.cssSelector("td:nth-child(1) input")).
              getAttribute("value"));
      String lastname = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstname = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }*/

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement row : rows) {
      int id = Integer.parseInt(row.findElement(By.cssSelector("td:nth-child(1) input")).
              getAttribute("value"));
      String lastname = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstname = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String allPhones = row.findElement(By.cssSelector("td:nth-child(6)")).getText();
      String allEmail = row.findElement(By.cssSelector("td:nth-child(5)")).getText();
      String address = row.findElement(By.cssSelector("td:nth-child(4)")).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAddress(address).withAllEmail(allEmail));
    }
    return contacts;
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String homephone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workphone = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId())
            .withFirstname(firstname)
            .withLastname(lastname)
            .withHomephone(homephone)
            .withMobilephone(mobilephone)
            .withWorkphone(workphone)
            .withEmail(email)
            .withEmail2(email2)
            .withEmail3(email3)
            .withAddress(address);

  }

  public void selectGroup(GroupData group) {
    wd.findElement(By.xpath(String.format("//select[@name='to_group']/option[@value='%s']", group.getId()))).click();
  }

  public void pushButtonAddToGroup() {
    wd.findElement(By.xpath("//input[@name='add']")).click();
  }

  public void getGroupData(GroupData groupData) {
    WebElement element = wd.findElement(By.xpath(String.format("//select[@name='group']/option[text() = '%s']", groupData.getName())));
    element.click();
  }

  public void pushButtonRemoveFromGroup() {
    wd.findElement(By.xpath("//input[@name='remove']")).click();
  }


/*
  public void selectDisplayGroup(String name) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(name);
  }

  public void addContactToGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    click(By.name("add"));
  }

  public void removeContactFromGroup(ContactData contact, GroupData group) {
    selectDisplayGroup(group.getName());
    selectContactById(contact.getId());
    removeFromGroup(group.getName());
  }

  private void removeFromGroup(String name) {
    click(By.name("remove"));
  }*/


}
