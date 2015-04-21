package com.shems.web;

import static org.junit.Assert.assertEquals;

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

	  }
	 
	  @Test
	  public void testGoalAndConsumedFebruary() throws Exception {
	  	driver.get(baseUrl + "/");
		driver.findElement(By.cssSelector("#month-select > option[value=\"1\"]")).click();
		assertEquals("100", driver.findElement(By.id("goal")).getText());
		assertEquals("89", driver.findElement(By.id("consumed")).getText());

	  }
	  
	  @Test
	  public void testGoalAndConsumedJanuary() throws Exception {
	  	driver.get(baseUrl + "/");
		driver.findElement(By.cssSelector("#month-select > option[value=\"0\"]")).click();
		assertEquals("100", driver.findElement(By.id("goal")).getText());
		assertEquals("77", driver.findElement(By.id("consumed")).getText());

	  }
	  
	  @AfterClass
	  public static void tearDown() throws Exception {
		  driver.quit();
	  }
}
