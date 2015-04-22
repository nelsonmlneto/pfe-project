package com.shems.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DashboardTestSuite {
  private static WebDriver driver;
  private static String baseUrl;

  @BeforeClass
  public static void setUpAll() throws Exception {
	System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
	driver = new ChromeDriver();	  
	//driver = new FirefoxDriver();
	baseUrl = "http://localhost:3000/";
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @Before
  public void setUp() throws Exception {}
  
  @Test
  public void testGoalAndConsumedMarch() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.cssSelector("#month-select > option[value=\"2\"]")).click();
    assertEquals("100", driver.findElement(By.id("goal")).getText());
    assertEquals("84", driver.findElement(By.id("consumed")).getText());
   
    assertTrue(isElementVisible(By.id("general-chart")));
    assertFalse(isElementVisible(By.id("objects-chart")));
    driver.findElement(By.linkText("Objects Chart")).click();
    assertFalse(isElementVisible(By.id("general-chart")));
    assertTrue(isElementVisible(By.id("objects-chart")));

  }
 
  @Test
  public void testGoalAndConsumedFebruary() throws Exception {
  	driver.get(baseUrl + "/");
	driver.findElement(By.cssSelector("#month-select > option[value=\"1\"]")).click();
	assertEquals("100", driver.findElement(By.id("goal")).getText());
	assertEquals("89", driver.findElement(By.id("consumed")).getText());

    assertTrue(isElementVisible(By.id("general-chart")));
    assertFalse(isElementVisible(By.id("objects-chart")));
    driver.findElement(By.linkText("Objects Chart")).click();
    assertFalse(isElementVisible(By.id("general-chart")));
    assertTrue(isElementVisible(By.id("objects-chart")));

  }
  
  @Test
  public void testGoalAndConsumedJanuary() throws Exception {
  	driver.get(baseUrl + "/");
	driver.findElement(By.cssSelector("#month-select > option[value=\"0\"]")).click();
	assertEquals("100", driver.findElement(By.id("goal")).getText());
	assertEquals("77", driver.findElement(By.id("consumed")).getText());
	
    assertTrue(isElementVisible(By.id("general-chart")));
    assertFalse(isElementVisible(By.id("objects-chart")));
    driver.findElement(By.linkText("Objects Chart")).click();
    assertFalse(isElementVisible(By.id("general-chart")));
    assertTrue(isElementVisible(By.id("objects-chart")));

  }
  
  @AfterClass
  public static void tearDown() throws Exception {
	  driver.quit();
  }
  
  private boolean isElementVisible(By by){
	return driver.findElement(by).isDisplayed();
  }
			
}
