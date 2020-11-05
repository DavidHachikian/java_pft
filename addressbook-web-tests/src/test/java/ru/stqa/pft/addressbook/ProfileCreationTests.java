// Generated by Selenium IDE
package ru.stqa.pft.addressbook;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.Dimension;

import org.openqa.selenium.JavascriptExecutor;

import java.util.*;


public class ProfileCreationTests {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void profileCreationTests() {
    driver.get("http://localhost/addressbook/");
    driver.manage().window().setSize(new Dimension(896, 868));
    driver.findElement(By.name("user")).sendKeys("admin");
    driver.findElement(By.name("pass")).click();
    driver.findElement(By.name("pass")).sendKeys("secret");
    driver.findElement(By.cssSelector("input:nth-child(7)")).click();
    driver.findElement(By.linkText("add new")).click();
    driver.findElement(By.name("firstname")).sendKeys("David");
    driver.findElement(By.name("lastname")).sendKeys("Test");
    driver.findElement(By.name("home")).sendKeys("22222222");
    driver.findElement(By.name("email")).sendKeys("test@gmail.com");
    driver.findElement(By.cssSelector("input:nth-child(87)")).click();
    driver.findElement(By.linkText("Logout")).click();
  }
}