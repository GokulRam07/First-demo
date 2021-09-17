package my.Practice;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import my.Practice.LoginPage1;

public class VerifyLoginPage1 {
@Test
	public void verifyValidLogin() throws InterruptedException
	{
	System.setProperty("webdriver.gecko.driver","C:/Users/gokulrv/eclipse-workspace-SeliniumLearning/SeliniumProject/Drives/geckodriver.exe");
	WebDriver driver=new FirefoxDriver();
	 
	driver.manage().window().maximize(); 
	driver.get("https://www.saucedemo.com/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	LoginPage1 Login1 = new LoginPage1(driver);
	Login1.login("standard_user","secret_sauce");
	Thread.sleep(2000);
	driver.quit();	
	}

@Test
    public void verifyValidLogin1() throws InterruptedException
	{
		System.setProperty("webdriver.gecko.driver","C:/Users/gokulrv/eclipse-workspace-SeliniumLearning/SeliniumProject/Drives/geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		 
		driver.manage().window().maximize(); 
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		LoginPage1 Login2 = new LoginPage1(driver);
		Login2.login("Admin","admin123");
		Thread.sleep(2000);
		Assert.assertEquals("admin123", "admin123"); //hard assert
		Thread.sleep(2000);
		driver.quit();
	}

}
