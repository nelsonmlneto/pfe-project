package com.shems.web;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ObjectFilterTestSuite {
  private static WebDriver driver;
  private static String baseUrl;
  private static StringBuffer verificationErrors = new StringBuffer();
  
  private final String OFF_COLOR = "#eb5a4";

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
  public void testObjectFilterAll() throws Exception {
    driver.get(baseUrl + "/");

    try {
    	assertTrue(isElementPresent(By.linkText("Air Conditioner")));
    	String objSwitchColor = getColorHex(driver.findElement(By.xpath("//ul[@id='objects-list']/li/a/span")).getCssValue("background-color"));
    	assertEquals(this.OFF_COLOR, objSwitchColor);
    	
    	assertTrue(isElementPresent(By.linkText("Clothers Iron")));
    	
    	assertTrue(isElementPresent(By.linkText("Hair Straightener")));
    	
    	assertTrue(isElementPresent(By.linkText("Couple Bedroom Light")));
    	
    	assertTrue(isElementPresent(By.linkText("Oven")));
    	
    	assertTrue(isElementPresent(By.linkText("Kitchen Light")));
    	
    	assertTrue(isElementPresent(By.linkText("TV")));
    	
    	assertTrue(isElementPresent(By.linkText("Garage Light")));
    	
    	assertTrue(isElementPresent(By.linkText("Shaving Machine")));
    	
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  @Test
  public void testObjectFilterKitchen() throws Exception {
    driver.get(baseUrl + "/");
    Select objFilter = new Select(driver.findElement(By.id("rooms-select")));
    objFilter.selectByVisibleText("Kitchen");
    driver.findElement(By.cssSelector("option[value=\"1\"]")).click();
      
    try {
    	assertTrue(isElementPresent(By.linkText("Oven")));
    	assertTrue(isElementPresent(By.linkText("Kitchen Light")));
    
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  @Test
  public void testObjectFilterBathroom() throws Exception {
    driver.get(baseUrl + "/");
    Select objFilter = new Select(driver.findElement(By.id("rooms-select")));
    objFilter.selectByVisibleText("Bathroom");
    driver.findElement(By.cssSelector("option[value=\"7\"]")).click();
    
    try {
    	assertTrue(isElementPresent(By.linkText("Hair Straightener")));	
    	assertTrue(isElementPresent(By.linkText("Shaving Machine")));
    	
    } catch (Error e) {
    	verificationErrors.append(e.toString());
    }
  }

  @AfterClass
  public static void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
  
  private String getColorHex(String color){
	  String s1 = color.substring(5);
	  StringTokenizer st = new StringTokenizer(s1);
	  int r = Integer.parseInt(st.nextToken(",").trim());
	  int g = Integer.parseInt(st.nextToken(",").trim());
	  int b = Integer.parseInt(st.nextToken(",").trim());
	  Color c = new Color(r, g, b);
	  String hex = "#"+Integer.toHexString(c.getRGB()).substring(2);
	  
	  return hex;
  }
  
  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}
