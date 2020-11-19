package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.HashMap;
import java.util.Map;

public class ApplicationManager {

  public WebDriver wd;

  public ContactHelper contactHelper;
  public SessionHelper sessionHelper;
  public NavigationHelper navigationHelper;
  public GroupHelper groupHelper;
  JavascriptExecutor js;
  private Map<String, Object> vars;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {

    if (browser == BrowserType.FIREFOX) {
      wd = new FirefoxDriver();
    } else if (browser == BrowserType.CHROME) {
      wd = new ChromeDriver();
    } else if (browser == BrowserType.IE) {
      wd = new InternetExplorerDriver();
    }


    wd.get("http://localhost/addressbook/");
    wd.manage().window().setSize(new Dimension(896, 868));

    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
    contactHelper = new ContactHelper(wd);

    js = (JavascriptExecutor) contactHelper.wd;
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

  public ContactHelper getContactHelper() { return contactHelper; }
}
