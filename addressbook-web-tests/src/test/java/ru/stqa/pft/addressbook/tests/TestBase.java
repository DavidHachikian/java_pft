package ru.stqa.pft.addressbook.tests;
import org.junit.After;
import org.junit.Before;
import org.junit.runners.Suite;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.util.Map;

public class TestBase {

  public static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
  public WebDriver wd;
  public Map<String, Object> vars;
  JavascriptExecutor js;

  @Before
  public void setUp() {
    app.init();
  }

  @After
  public void tearDown() {
    app.stop();
  }


}
