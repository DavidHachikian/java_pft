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

  public void selectContactDeletion() {
    click(By.xpath("//td/input"));
  }

  public void initContactDeletion() {
    click(By.xpath("//input[@value='Delete']"));
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }


  public void createContact(ContactData contact, boolean b) {
    fillContactCreation(contact, true);
    submitContactCreation();
    logout();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }
}
