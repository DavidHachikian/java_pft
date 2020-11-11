package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class ApplicationManager {
  public WebDriver wd;

  public SessionHelper sessionHelper;
  public NavigationHelper navigationHelper;
  public GroupHelper groupHelper;
  JavascriptExecutor js;
  private Map<String, Object> vars;

  public void init() {
    wd = new FirefoxDriver();

    wd.get("http://localhost/addressbook/");
    wd.manage().window().setSize(new Dimension(896, 868));

    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
    js = (JavascriptExecutor) wd;
    vars = new HashMap<String, Object>();
  }

  public void stop() {
    wd.quit();
  }




  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
