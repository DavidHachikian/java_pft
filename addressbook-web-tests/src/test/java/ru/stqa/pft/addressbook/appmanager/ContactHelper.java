package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactHelper extends HelperBase {




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

  public void fillContactCreation(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

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

  public void selectContactDeletion() {
    //wd.findElements(By.name("selected[]"));

    click(By.id("MassCB"));

  }

  public void initContactDeletion() {
    click(By.xpath("//input[@value='Delete']"));

  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
    //wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }


  public void createContact(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactCreation(contact, creation);
    submitContactCreation();
    returnToHomePage();
    //logout();
  }

  private void returnToHomePage() {
    click(By.linkText("home"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
