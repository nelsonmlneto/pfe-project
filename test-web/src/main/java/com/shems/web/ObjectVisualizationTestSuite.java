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

public class ObjectVisualizationTestSuite {
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
	  public void testE() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("Air Conditioner")).click();
		assertEquals("Object Visualization", driver.findElement(By.cssSelector("h3.icon-battery")).getText());
		assertEquals("Air Conditioner", driver.findElement(By.id("obj-title")).getText());
	  }
	  
	  @AfterClass
	  public static void tearDown() throws Exception {
		  driver.quit();
	  }
}
