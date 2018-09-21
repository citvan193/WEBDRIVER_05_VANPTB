package selenium_testng;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class TestNG_04_DataProvider {
	WebDriver driver;
	
	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.manage().window().maximize();
	}
  @Test(dataProvider = "loginToSystem")
  public void TC_01_loginToSystem(String username, String password) {
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys(username);
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
  }
  @Test(dataProvider = "newCustomer")
  public void TC_02_newCustomer(String username, String password) {
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys(username);
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
  }
  @DataProvider
  public Object[][] loginToSystem() {
    return new Object[][] {
      new Object[] {"automationvalid_01@gmail.com", "111111" },
      new Object[] {"automationvalid_02@gmail.com", "111111" },
      new Object[] {"automationvalid_03@gmail.com", "111111" },
    };
  }
  @DataProvider
  public Object[][] newCustomer() {
    return new Object[][] {
      new Object[] {"automationvalid_01@gmail.com", "111111" },
      new Object[] {"automationvalid_02@gmail.com", "111111" },
      new Object[] {"automationvalid_03@gmail.com", "111111" },
    };
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
