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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ObjectVisualizationTestSuite {
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
  public void testObjectVisualization1() throws Exception {
	driver.get(baseUrl + "/");
	driver.findElement(By.linkText("Air Conditioner")).click();
	assertEquals("Object Visualization", driver.findElement(By.cssSelector("h3.icon-battery")).getText());
	assertEquals("Air Conditioner", driver.findElement(By.id("obj-title")).getText());
	assertTrue(isElementVisible(By.id("obj-chart")));
  }
  
  @Test
  public void testObjectVisualization2() throws Exception {
	driver.get(baseUrl + "/");
	driver.findElement(By.linkText("Clothers Iron")).click();
	assertEquals("Object Visualization", driver.findElement(By.cssSelector("h3.icon-battery")).getText());
	assertEquals("Clothers Iron", driver.findElement(By.id("obj-title")).getText());
	assertTrue(isElementVisible(By.id("obj-chart")));
  }
  
  @Test
  public void testObjectVisualization3() throws Exception {
	driver.get(baseUrl + "/");
	driver.findElement(By.linkText("Hair Straightener")).click();
	assertEquals("Object Visualization", driver.findElement(By.cssSelector("h3.icon-battery")).getText());
	assertEquals("Hair Straightener", driver.findElement(By.id("obj-title")).getText());
	assertTrue(isElementVisible(By.id("obj-chart")));
  }
  
  @Test
  public void testObjectVisualization4() throws Exception {
	driver.get(baseUrl + "/");
	driver.findElement(By.linkText("Couple Bedroom Light")).click();
	assertEquals("Object Visualization", driver.findElement(By.cssSelector("h3.icon-battery")).getText());
	assertEquals("Couple Bedroom Light", driver.findElement(By.id("obj-title")).getText());
	assertTrue(isElementVisible(By.id("obj-chart")));
  }
  
  @Test
  public void testObjectVisualization5() throws Exception {
	driver.get(baseUrl + "/");
	driver.findElement(By.linkText("Oven")).click();
	assertEquals("Object Visualization", driver.findElement(By.cssSelector("h3.icon-battery")).getText());
	assertEquals("Oven", driver.findElement(By.id("obj-title")).getText());
	assertTrue(isElementVisible(By.id("obj-chart")));
  }
  
  @Test
  public void testObjectVisualization6() throws Exception {
	driver.get(baseUrl + "/");
	driver.findElement(By.linkText("Kitchen Light")).click();
	assertEquals("Object Visualization", driver.findElement(By.cssSelector("h3.icon-battery")).getText());
	assertEquals("Kitchen Light", driver.findElement(By.id("obj-title")).getText());
	assertTrue(isElementVisible(By.id("obj-chart")));
  }
  
  @Test
  public void testObjectVisualization7() throws Exception {
	driver.get(baseUrl + "/");
	driver.findElement(By.linkText("TV")).click();
	assertEquals("Object Visualization", driver.findElement(By.cssSelector("h3.icon-battery")).getText());
	assertEquals("TV", driver.findElement(By.id("obj-title")).getText());
	assertTrue(isElementVisible(By.id("obj-chart")));
  }
  
  @Test
  public void testObjectVisualization8() throws Exception {
	driver.get(baseUrl + "/");
	driver.findElement(By.linkText("Garage Light")).click();
	assertEquals("Object Visualization", driver.findElement(By.cssSelector("h3.icon-battery")).getText());
	assertEquals("Garage Light", driver.findElement(By.id("obj-title")).getText());
	assertTrue(isElementVisible(By.id("obj-chart")));
  }
  
  @Test
  public void testObjectVisualization9() throws Exception {
	driver.get(baseUrl + "/");
	driver.findElement(By.linkText("Shaving Machine")).click();
	assertEquals("Object Visualization", driver.findElement(By.cssSelector("h3.icon-battery")).getText());
	assertEquals("Shaving Machine", driver.findElement(By.id("obj-title")).getText());
	assertTrue(isElementVisible(By.id("obj-chart")));
  }
  
  @Test
  public void testSwicthObject1() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Air Conditioner")).click();
    driver.findElement(By.id("switch")).click();
    assertEquals(this.ON_COLOR, getSwicthHexColor());
    assertEquals(this.ON_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li/a/span"));
    driver.findElement(By.linkText("SHEMS - Smart Home Energy Management System")).click();
    assertEquals(this.ON_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li/a/span"));
    driver.findElement(By.linkText("Air Conditioner")).click();
    driver.findElement(By.id("switch")).click();
    assertEquals(this.OFF_COLOR, getSwicthHexColor());
    assertEquals(this.OFF_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li/a/span"));
  }
  
  @Test
  public void testSwicthObject2() throws Exception {
	driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Clothers Iron")).click();
    driver.findElement(By.id("switch")).click();
    assertEquals(this.OFF_COLOR, getSwicthHexColor());
    assertEquals(this.OFF_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[2]/a/span"));
    driver.findElement(By.linkText("SHEMS - Smart Home Energy Management System")).click();
    assertEquals(this.OFF_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[2]/a/span"));
    driver.findElement(By.linkText("Clothers Iron")).click();
    driver.findElement(By.id("switch")).click();
    assertEquals(this.ON_COLOR, getSwicthHexColor());
    assertEquals(this.ON_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[2]/a/span"));
  }
  
  @Test
  public void testSwicthObject3() throws Exception {
	driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Oven")).click();
    driver.findElement(By.id("switch")).click();
    assertEquals(this.ON_COLOR, getSwicthHexColor());
    assertEquals(this.ON_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[5]/a/span"));
    driver.findElement(By.linkText("SHEMS - Smart Home Energy Management System")).click();
    assertEquals(this.ON_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[5]/a/span"));
    driver.findElement(By.linkText("Oven")).click();
    driver.findElement(By.id("switch")).click();
    assertEquals(this.OFF_COLOR, getSwicthHexColor());
    assertEquals(this.OFF_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[5]/a/span"));
  }
  
  @Test
  public void testSwicthObject4() throws Exception {
	driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Garage Light")).click();
    driver.findElement(By.id("switch")).click();
    assertEquals(this.OFF_COLOR, getSwicthHexColor());
    assertEquals(this.OFF_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[8]/a/span"));
    driver.findElement(By.linkText("SHEMS - Smart Home Energy Management System")).click();
    assertEquals(this.OFF_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[8]/a/span"));
    driver.findElement(By.linkText("Garage Light")).click();
    driver.findElement(By.id("switch")).click();
    assertEquals(this.ON_COLOR, getSwicthHexColor());
    assertEquals(this.ON_COLOR, getObjectStateHexColor("//ul[@id='objects-list']/li[8]/a/span"));
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
  
  private boolean isElementVisible(By by){
	return driver.findElement(by).isDisplayed();
  }
	 
}
