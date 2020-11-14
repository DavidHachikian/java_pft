package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void stop() {
    wd.quit();
  }

  public void logout(String logout) {
    wd.findElement(By.linkText(logout)).click();
  }

  public void submitProfileCreation(String s) {
    wd.findElement(By.cssSelector(s)).click();
  }

  public void fillProfileCreation() {
    type("firstname", "David");
    type("lastname", "Test");
    type("home", "22222222");
    type("email", "test@gmail.com");
  }

  private void type(String firstname, String david) {
    wd.findElement(By.name(firstname)).sendKeys(david);
  }


  public void selectContactDeletion() {
    click(By.xpath("//td/input"));
  }

  public void initContactDeletion() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
  }


  /*public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }*/
}
