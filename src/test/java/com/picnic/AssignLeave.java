package com.picnic;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion.*;
import org.openqa.selenium.support.ui.Select;

 

import io.github.bonigarcia.wdm.WebDriverManager;

 

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

 

import java.util.List;
import java.util.concurrent.TimeUnit;

 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

 

public class AssignLeave {
    
    WebDriver driver;
  @BeforeTest(alwaysRun=true) 
  @Parameters({"browser"})
  public void launchBrowser(String browser) {
      if(browser.equalsIgnoreCase("chrome")) {
          WebDriverManager.chromedriver().setup();
          driver = new ChromeDriver();
      }else if(browser.equalsIgnoreCase("firefox")) {
          WebDriverManager.firefoxdriver().setup();
             driver= new FirefoxDriver();
      }
      driver.get("https://opensource-demo.orangehrmlive.com/");
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

 

  @Test(priority=1)
  public void logoCheck() {
      WebElement logo= driver.findElement(By.xpath("//*[@id=\"divLogo\"]/img"));
      Assert.assertTrue(logo.isDisplayed());
      
  }
  
  @Test(priority=2)
  public void titleCheck() {
      String title= "OrangeHRM";
      if(driver.getPageSource().contains("Invalid credentials"))
        {
            driver.close();
            Assert.assertTrue(false);
        }else {
            Assert.assertEquals(title, driver.getTitle());
        }
  }
 
  
  @Test(priority=3)
  public void loginCredentials() throws InterruptedException
  {
      driver.findElement(By.id("txtUsername")).sendKeys("Admin");
      driver.findElement(By.name("txtPassword")).sendKeys("admin123");
  
      Thread.sleep(1000);
      driver.findElement(By.id("btnLogin")).click();
      Thread.sleep(1000);
  }
 
  @Test(priority=4)
  public void assignleavePage() {
      driver.findElement(By.id("menu_leave_viewLeaveModule")).click();
      driver.findElement(By.id("menu_leave_assignLeave")).click();
  }
  
  
  @Test(dependsOnMethods="assignleavePage",priority=5)
  public void empName() {
        WebElement empName=driver.findElement(By.id("assignleave_txtEmployee_empName"));
        empName.sendKeys("John Smith");
        empName.click();
  }
  
  @Test(priority=6)
  public void leaveType(){
      WebElement leaveType=driver.findElement(By.name("assignleave[txtLeaveType]"));
        
        //To check the field is dislayed or not
        Assert.assertTrue(leaveType.isDisplayed());

 

        //To check the field is enabled or not
        Assert.assertTrue(leaveType.isEnabled());
      
        Select typeOfLeave=new Select(leaveType);
        typeOfLeave.selectByValue("1");
        
        //To get the selected Text from the dropdown
        WebElement selectedOption= typeOfLeave.getFirstSelectedOption();
        String selectedOptionText= selectedOption.getText();
        System.out.println("Selected option from the dropdown is:" +selectedOptionText);    
  }

 

  @Test(priority=7)
  public void Date() throws InterruptedException {
    //To select the FromDate from date picker for assigning leave
            WebElement fromDate= driver.findElement(By.id("assignleave_txtFromDate"));
            fromDate.click();
            Thread.sleep(1000);
            WebElement month= driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]"));
            selectedDropOptions(month, "Oct");
            Thread.sleep(1000);
            WebElement year= driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[2]"));
            selectedDropOptions(year,"2021");
            Thread.sleep(1000);
            WebElement day= driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[2]/a"));
            day.click();
            
            //To select the ToDate from date picker for assigning leave
            WebElement toDate= driver.findElement(By.xpath("//*[@id=\"frmLeaveApply\"]/fieldset/ol/li[5]/img"));
            toDate.click();
            WebElement toMonth= driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]"));
            selectedDropOptions(toMonth, "Oct");
            WebElement toYear= driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[2]"));
            selectedDropOptions(toYear,"2021");
            WebElement toDay= driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[4]/a"));
            toDay.click();
  }
  
  @Test(priority=8)
  public void noOfLeaveDays() throws Exception {
      WebElement halfDay= driver.findElement(By.name("assignleave[firstDuration][duration]"));
        selectedDropOptions(halfDay, "Half Day");
        
        WebElement duration= driver.findElement(By.name("assignleave[firstDuration][ampm]"));
        selectedDropOptions(duration, "Afternoon");
        
        WebElement comments= driver.findElement(By.name("assignleave[txtComment]"));
        comments.sendKeys("Assigning leave to John Smith for Vacation");
        
        Thread.sleep(1000);
        
        driver.findElement(By.id("assignBtn")).click();
        Thread.sleep(1000);
  }
  
  //Generic Method to handle multiple dropdowns
  public static void selectedDropOptions(WebElement element, String value) {
        Select dropdown = new Select(element);
        List <WebElement> selectOption= dropdown.getOptions();
        for(WebElement option:selectOption) {
            if(option.getText().equals(value)) {
                option.click();
                break;
            }
        }
    }

 

  
  @AfterTest
  public void successMsg() {
      String expectedMsg="Successfully Assigned";
      if(driver.getPageSource().contains("Successfully Assigned"))
        {
         Assert.assertTrue(true);
         System.out.println(expectedMsg);
        }else {
            System.out.println("Failed to Assign Leave");
        }
  }

 

  @AfterClass
  public void close_Browser() {
      driver.quit();
  }

 

}