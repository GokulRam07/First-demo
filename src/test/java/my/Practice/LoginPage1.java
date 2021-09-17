package my.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage1 {
	WebDriver driver;


public  LoginPage1(WebDriver driver) {
	this. driver = driver;
}


public void login(String username , String Password ) {
	 driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(username);
	 driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(Password);
	 driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
	
	
}
	
}
