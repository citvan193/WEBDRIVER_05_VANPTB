package selenium_testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class TestNG_05_Parameter_MultiBrowser {
	WebDriver driver;

	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(String browsername) {

		if (browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browsername.equals("ie")) {
			System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else {
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.manage().window().maximize();
	}
	@Test(invocationCount = 2)
	  public void TC_01_loginToSystem() {
		  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automationvalid_01@gmail.com");
		  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("111111");
		  driver.findElement(By.xpath("//button[@id='send2']")).click();
		  
		  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	  }
	
  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
