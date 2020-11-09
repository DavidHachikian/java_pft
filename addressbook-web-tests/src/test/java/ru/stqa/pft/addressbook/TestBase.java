package ru.stqa.pft.addressbook;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class TestBase {
  JavascriptExecutor js;
  public WebDriver wd;
  private Map<String, Object> vars;

  @Before
  public void setUp() {
    wd = new FirefoxDriver();
    js = (JavascriptExecutor) wd;
    vars = new HashMap<String, Object>();
  }

  @After
  public void tearDown() {
    wd.quit();
  }

  protected void startTest() {
    wd.get("http://localhost/addressbook/");
    wd.manage().window().setSize(new Dimension(896, 868));
  }

  protected void returnToGroupPage() {
    wd.findElement(By.linkText("group page")).click();
    wd.findElement(By.linkText("Logout")).click();
  }

  protected void submitGroupCreation(String submit) {
    wd.findElement(By.name(submit)).click();
  }

  protected void fillGroupForm(GroupData groupData) {
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
  }

  protected void initGroupCreation(String s) {
    wd.findElement(By.name(s)).click();
  }

  protected void gotoGroupPage() { wd.findElement(By.linkText("groups")).click();
  }

  protected void login(String username, String password) {
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.cssSelector("input:nth-child(7)")).click();
  }

  protected void deleteSelectedGroups() {
    wd.findElement(By.name("delete")).click();
  }

  protected void selectGroup() {
    wd.findElement(By.name("selected[]")).click();
  }
}
