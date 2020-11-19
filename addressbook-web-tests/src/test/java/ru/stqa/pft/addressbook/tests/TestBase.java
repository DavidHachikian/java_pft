package ru.stqa.pft.addressbook.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.util.Map;

public class TestBase {

  public final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
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
