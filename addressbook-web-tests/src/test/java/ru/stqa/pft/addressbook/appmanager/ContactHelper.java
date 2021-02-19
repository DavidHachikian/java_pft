package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase {

  private int id;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void stop() {
    wd.quit();
  }

  public void logout() {
    click(By.linkText("Logout"));
  }

  public void submitContactCreation() {
    click(By.cssSelector("input:nth-child(87)"));
  }

  public void fillContactCreation(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());

    /*if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }*/

    /*type("firstname", "David");
    type("lastname", "Test");
    type("home", "22222222");
    type("email", "test@gmail.com");*/
  }

  private void type(String firstname, String david) {
    wd.findElement(By.name(firstname)).sendKeys(david);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int id) {
    wd.findElements(By.name("selected[]")).get(id).click(); //клик по элементу при выборе контакта для удаления и модификации
    //click(By.id("MassCB")); удаление всех контактов
  }

  public void initContactDeletion() {
    //click(By.xpath("//input[@value='Delete']"));
    wd.findElements(By.xpath("//input[@value='Delete']")).get(id).click();
  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
    //wd.findElement(By.cssSelector("div.msgbox"));
  }


  public void initContactModification() {
    //this.id = id;
    //click(By.xpath("//img[@alt='Edit']"));
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(id).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  /*public void createContact(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactCreation(contact, creation);
    submitContactCreation();
    returnToHomePage();
    //logout();
  }*/

  private void returnToHomePage() {
    click(By.linkText("home"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactCreation(contact);
    submitContactCreation();
    returnToHomePage();
  }


  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = super.wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String firstname = element.getText();
      String lastname = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, "test_name", "test_surname", "test1");
      contacts.add(contact);

      /*List<WebElement> cells = element.findElements(By.cssSelector("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      ContactData contact = new ContactData("test_name", "test_surname", "test1");
      contacts.add(contact);*/
    }

    return contacts;
  }


}
