package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_10_WebElement_Waits {
    WebDriver driver;
    WebDriverWait waitExplicit;
	
    @BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
    }
    
   
	
	public void TC_01_Implicit_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}
	
	public void TC_02_Explicit_Wait() {
		//Step 1:
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		//Step 2: Presence
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='contentWrapper']")));
		//Step 2: Visible
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='contentWrapper']")));
		//Step 3:
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(), "No Selected Dates to display.");
		//Step 4:
		driver.findElement(By.xpath("//a[text()='9']/parent::td")).click();
		//Step 5:
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		//Step 6:
		 waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='9']/parent::td[@class='rcSelected']")));
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='9']/parent::td[@class='rcSelected']")).isDisplayed());
		//Step 7:
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(), "Sunday, September 09, 2018");
	}
	
	@ Test
	public void TC_03_Fluent_Wait() {
		//Step 1:
		driver.get("https://daominhdam.github.io/fluent-wait/");
		//Step2:
		WebElement countDount = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		waitExplicit.until(ExpectedConditions.visibilityOf(countDount));
		// Khoi tao Fluent wait
		new FluentWait<WebElement>(countDount)
		           // Tong time wait la 12s
		           .withTimeout(12, TimeUnit.SECONDS)
		            // Tan so moi 1s check 1 lan
		            .pollingEvery(1, TimeUnit.SECONDS)
		           // Neu gap exception la find ko thay element se bo  qua
		            .ignoring(NoSuchElementException.class)
		            // Kiem tra dieu kien
		            .until(new Function<WebElement, Boolean>() {
		                public Boolean apply(WebElement element) {
		                           // Kiem tra dieu kien countdount = 00
		                           boolean flag =  element.getText().endsWith("00");
		                           System.out.println("Time = " +  element.getText());
		                           // return gia tri cho function apply
		                           return flag;
		                      }
		               });	
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}