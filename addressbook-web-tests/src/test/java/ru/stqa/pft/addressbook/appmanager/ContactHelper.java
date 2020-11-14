package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactHelper {

  public WebDriver wd;

  public ContactHelper(WebDriver wd) {
    this.wd = wd;
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


}
