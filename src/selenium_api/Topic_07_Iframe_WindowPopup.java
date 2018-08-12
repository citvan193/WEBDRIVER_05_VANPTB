package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Iframe_WindowPopup {
    WebDriver driver;
    WebDriverWait wait;
	
    @BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
    }
    
   
	@Test
	public void TC_01_Frame_Iframe() {
		driver.get("http://www.hdfcbank.com/");
		//Issue 1: Xu ly kiem tra 1 element is display. Neu su dung WebElement: failed -> Throw no such element exception
		List <WebElement> notificationFrame = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		System.out.println("Number element = " + notificationFrame.size());
		if (notificationFrame.size() > 0) {
			driver.switchTo().frame(notificationFrame.get(0));
			WebElement closeIcon = driver.findElement(By.xpath("//div[@id='div-close']"));
			//Javascript click element
			JavascriptExecutor je = (JavascriptExecutor) driver;
		    je.executeScript("arguments[0].click();", closeIcon);
		    System.out.println("Close popup!");
		    
		    // Issue 3: Swich between 2 frame -> swich to top window
		    driver.switchTo().defaultContent();
		}
		//Issue 2: Xu ly dynamic frame
		WebElement lookingforIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(lookingforIframe);
		//Check text is display
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='messageText']")).getText(), "What are you looking for?");
		//Swich to default content
		driver.switchTo().defaultContent();
		
		//Step 4: verify banner 6 image
		WebElement bannerIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(bannerIframe);
		
		//Verify banner image
		By bannerImageXpath = By.xpath("//div[@id='productcontainer']//img");
		List <WebElement> bannerImages = driver.findElements(bannerImageXpath);
		//Check 6 image
		Assert.assertEquals(bannerImages.size(), 6);
		//Check all image are presence
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bannerImageXpath));
		//Swich to default content
		driver.switchTo().defaultContent();
		
		//Step 5: Verify flipper banner duoc hien thi va co 8 item
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='flipBanner']")).isDisplayed());
		List <WebElement> flipBannerImages = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		Assert.assertEquals(flipBannerImages.size(), 8);
		for (WebElement image : flipBannerImages) {
			Assert.assertTrue(image.isDisplayed());
			System.out.println("element is display!");
		}	
	}
	
	
	
	@Test (enabled = false)
	public void TC_02_WindowPopup() {
		
		
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}