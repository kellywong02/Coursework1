package com.SDDEVOPS;

import org.testng.annotations.Test;

import junit.framework.Assert;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class NewTest {

  private WebDriver webDriver;
  
  @Test
  public void clickThread() {
	  webDriver.navigate().to("http://localhost:8085/CourseworkFinal/InsertThreadServlet");
	  webDriver.findElement(By.name("CPUBURN")).click();

  }
  @Test
  public void addThread() {
	  webDriver.navigate().to("http://localhost:8085/CourseworkFinal/InsertThreadServlet");
	  Assert.assertEquals(webDriver.getTitle(), "Threads");
	  WebElement TA_threadName = webDriver.findElement(By.name("thread-name"));
	  WebElement TA_threadMessage = webDriver.findElement(By.name("thread-message"));
	  WebElement BTN_SUBMIT = webDriver.findElement(By.name("submit_button"));
	  TA_threadName.sendKeys("Laptop Cannot On");
	  TA_threadMessage.sendKeys("MY LAPTOP CANNOT ON!!! HOW????");
	  BTN_SUBMIT.click();
  }
  public void f() {
  }
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\chromedriver.exe");
	  webDriver = new ChromeDriver();
  }

  @AfterTest
  public void afterTest() {
	  webDriver.quit();
  }

}
