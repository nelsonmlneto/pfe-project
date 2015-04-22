package com.shems.web;

import static org.junit.Assert.assertEquals;
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
import org.openqa.selenium.support.ui.Select;

public class RegisterObjectTestSuite {
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
  public void testE() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("+")).click();
    driver.findElement(By.name("serial")).sendKeys("123456");
    driver.findElement(By.name("title")).sendKeys("Washing Machine");
    new Select(driver.findElement(By.id("rooms-modal-select"))).selectByVisibleText("Garage");
    driver.findElement(By.name("description")).sendKeys("The Washing Machine of the Garage");
    driver.findElement(By.id("submit-add")).click();
    
    assertTrue(isElementPresent(By.linkText("Washing Machine")));
	assertEquals(this.OFF_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[10]/a/span"));
    
    driver.findElement(By.linkText("Washing Machine")).click();    
    driver.findElement(By.id("switch")).click();
    
    assertEquals(this.ON_COLOR, getSwicthHexColor());
    assertEquals(this.ON_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[10]/a/span"));
    
  }
  
  @AfterClass
  public static void tearDown() throws Exception {
	  driver.quit();
  }
  
  private String getObjectStateHexColor(String selector){
	String color = driver.findElement(By.xpath(selector)).getCssValue("background-color");
  	return covertColorToHex(color);
  }
  
  private String getSwicthHexColor(){
	String color = driver.findElement(By.xpath("//div[@id='switch']")).getCssValue("background-color");
	return covertColorToHex(color);
  }
  
  private String covertColorToHex(String color){
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
