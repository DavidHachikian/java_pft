package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.tests.TestBase;

public class NavigationHelper extends HelperBase {


  public NavigationHelper(WebDriver wd) {

    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  public void goToNewProfile(String s, TestBase testBase) {
    click(By.linkText(s));
  }
}
