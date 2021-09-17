package com.picnic;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;	
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
public class SampleSelenium {
			ExtentSparkReporter reporter;
			ExtentReports extent;
			
			@BeforeSuite
			public void setUp() {
				reporter = new ExtentSparkReporter("myreport.html");
				extent = new ExtentReports();
				extent.attachReporter(reporter);
	}
	    @Test
		public void TestMethod() {
	    	ExtentTest test = extent.createTest("My first test");
	    	test.log(Status.INFO, "This shows info");
	
		//System.setProperty("webdriver.gecko.driver","C:/Users/aravindhl/eclipse-SeliniumProject/seliniumproject/drivers/geckodriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();//create instance of chrome driver
		driver.get("http://www.google.com");
		driver.manage().window().maximize();//method used to open URL and then it will wait till the whole page gets loaded
		WebElement searchbox=driver.findElement(By.name("q"));
		searchbox.sendKeys("chromeDriver");
		searchbox.submit();
		//Thread.sleep(5000);//its sleep time(waiting time)
		driver.quit();//quitting(Close the browser and terminates the web driver session) 
		}
	    @Test
		public void TestMethod2() {
	    	ExtentTest test = extent.createTest("My second test");
	    	test.log(Status.INFO, "This shows info");
	
		//System.setProperty("webdriver.gecko.driver","C:/Users/aravindhl/eclipse-SeliniumProject/seliniumproject/drivers/geckodriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();//create instance of chrome driver
		driver.get("http://www.google.com");
		driver.manage().window().maximize();//method used to open URL and then it will wait till the whole page gets loaded
		WebElement searchbox=driver.findElement(By.name("q"));
		searchbox.sendKeys("chromeDriver");
		searchbox.submit();
		//Thread.sleep(5000);//its sleep time(waiting time)
		driver.quit();//quitting(Close the browser and terminates the web driver session) 
		}
	    @AfterSuite
	    public void tearDown() {
	    extent.flush();
	    }
}
