package com.shems.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

public class ObjectFilterTestSuite {
  private static WebDriver driver;
  private static String baseUrl;
  
  private final String OFF_COLOR = "#eb5a46";
  private final String ON_COLOR = "#70b500";

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

	assertTrue(isElementPresent(By.linkText("Air Conditioner")));
	assertEquals(this.OFF_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li/a/span"));
	
	assertTrue(isElementPresent(By.linkText("Clothers Iron")));
	assertEquals(this.ON_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[2]/a/span"));
	
	assertTrue(isElementPresent(By.linkText("Hair Straightener")));
	assertEquals(this.ON_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[3]/a/span"));
	
	assertTrue(isElementPresent(By.linkText("Couple Bedroom Light")));
	assertEquals(this.OFF_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[4]/a/span"));
	
	assertTrue(isElementPresent(By.linkText("Oven")));
	assertEquals(this.OFF_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[5]/a/span"));
	
	assertTrue(isElementPresent(By.linkText("Kitchen Light")));
	assertEquals(this.ON_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[6]/a/span"));
	
	assertTrue(isElementPresent(By.linkText("TV")));
	assertEquals(this.OFF_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[7]/a/span"));
	
	assertTrue(isElementPresent(By.linkText("Garage Light")));
	assertEquals(this.ON_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[8]/a/span"));
	
	assertTrue(isElementPresent(By.linkText("Shaving Machine")));
	assertEquals(this.OFF_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[9]/a/span"));

  }
  
  @Test
  public void testObjectFilterKitchen() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.cssSelector("option[value=\"1\"]")).click();
      
	assertTrue(isElementPresent(By.linkText("Oven")));
	assertTrue(isElementPresent(By.linkText("Kitchen Light")));
	
	assertFalse(isElementPresent(By.linkText("Air Conditioner")));
	assertFalse(isElementPresent(By.linkText("Clothers Iron")));  
	assertFalse(isElementPresent(By.linkText("Hair Straightener")));  
	assertFalse(isElementPresent(By.linkText("Couple Bedroom Light"))); 
	assertFalse(isElementPresent(By.linkText("TV")));
	assertFalse(isElementPresent(By.linkText("Garage Light")));  
	assertFalse(isElementPresent(By.linkText("Shaving Machine")));
    
  }
  
  @Test
  public void testObjectFilterBathroom() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.cssSelector("option[value=\"7\"]")).click();

	assertTrue(isElementPresent(By.linkText("Hair Straightener")));	
	assertTrue(isElementPresent(By.linkText("Shaving Machine")));
	
	assertFalse(isElementPresent(By.linkText("Air Conditioner")));
	assertFalse(isElementPresent(By.linkText("Clothers Iron")));  
	assertFalse(isElementPresent(By.linkText("Couple Bedroom Light")));
	assertFalse(isElementPresent(By.linkText("Oven")));
	assertFalse(isElementPresent(By.linkText("Kitchen Light")));  
	assertFalse(isElementPresent(By.linkText("TV")));
	assertFalse(isElementPresent(By.linkText("Garage Light")));  

  }
  
  @Test
  public void testObjectFilterKidsBedroom() throws Exception{
	driver.get(baseUrl + "/");
	driver.findElement(By.cssSelector("option[value=\"5\"]")).click();
	
	assertFalse(isElementPresent(By.xpath("//ul[@id='objects-list']/li")));		
	  
  }

  @AfterClass
  public static void tearDown() throws Exception {
	  driver.quit();
  }
  
  private String getObjectStateHexColor(String selector){
	  String color = driver.findElement(By.xpath(selector)).getCssValue("background-color");
	  
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
    	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	driver.findElement(by);
    	driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
    	return true;
    
    } catch (NoSuchElementException e) {
    	return false;
    }
  }
}
