package selenium_testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_01_Annotations {
	WebDriver driver;

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Before Suite");
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test");
	}
	@BeforeClass
	public void beforeClass() {
		
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.manage().window().maximize();
	}

	
  @Test
  public void TC_01_CheckUrl() {
	  String loginUrl = driver.getCurrentUrl();
	  Assert.assertEquals(loginUrl, "http://live.guru99.com/index.php/customer/account/login/");
  }
  
  @Test
  public void TC_02_CheckTitle() {
	  String loginTitle = driver.getTitle();
	  Assert.assertEquals(loginTitle, "Customer Login");
  }
  
  @Test
  public void TC_03() {
	  System.out.println("Testcase 03");
  }
  
  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

  @AfterClass
  public void afterClass() {
	  
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("After Test");
  }
  
  @AfterSuite
  public void afterSuite() {
	  System.out.println("After Suite");
  }

}
