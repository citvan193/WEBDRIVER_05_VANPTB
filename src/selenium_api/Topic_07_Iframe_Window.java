package selenium_api;

import java.util.List;
import java.util.Set;
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

public class Topic_07_Iframe_Window {
    WebDriver driver;
    WebDriverWait wait;
	
    @BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//Timeout default of Selenium = 0.5s
		//Explicit wait
		wait = new WebDriverWait(driver, 10);
		
		//Implicit wait/Global wait: findElement, findElements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
    }
    
   
	@Test (enabled = false)
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
	public void TC_02_Window() {
		driver.get("http://daominhdam.890m.com/");
		String ParentGUID = driver.getWindowHandle();
		System.out.println("Parent Window ID = "+ ParentGUID);
		System.out.println("Title parent = "+ driver.getTitle());
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		
		switchToChildWindowbyGUID(ParentGUID);
		//switchToWindowByTitle("Google");
		
		//Verify switch success
		String googleTitle = driver.getTitle();
		System.out.println("Title switch after = "+ driver.getTitle());
		Assert.assertEquals(googleTitle, "Google");
		closeAllWithoutParentWindows(ParentGUID);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
	}
	
	@Test 
	public void TC_03_hdfcbankWindow() {
		driver.get("http://www.hdfcbank.com/");
		String ParentGUID = driver.getWindowHandle();
		System.out.println("Parent Window ID = "+ driver.getTitle());
		//Kiem tra va close quang cao neu co xuat hien
		overrideGlobalWait(15);
		List <WebElement> notificationFrame = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		System.out.println("Number element = " + notificationFrame.size());
		if (notificationFrame.size() > 0) {
			driver.switchTo().frame(notificationFrame.get(0));
			WebElement closeIcon = driver.findElement(By.xpath("//div[@id='div-close']"));
			JavascriptExecutor je = (JavascriptExecutor) driver;
		    je.executeScript("arguments[0].click();", closeIcon);
		    System.out.println("Close popup!");
		    driver.switchTo().defaultContent();
		}
		overrideGlobalWait(30);

		//Click vao Ari link va switch qua Ari tab
		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		
		//Click vao account detail va switch qua
		driver.findElement(By.xpath("//p[text()='Account Details']")).click();
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
		
		//Switch qua frame footer
		WebElement footerFrame = driver.findElement(By.xpath("//frame[@name='footer']"));
		driver.switchTo().frame(footerFrame);
		
		//Click Click Privacy Policy link
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		//Click vao CSR link
		driver.findElement(By.xpath("//a[text()='CSR']")).click();
		
		//Close window
		closeAllWithoutParentWindows(ParentGUID);
		Assert.assertEquals(driver.getTitle(), "HDFC Bank: Personal Banking Services");
		
		
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void switchToChildWindowbyGUID(String parentID) {
		//Get all current window
		Set<String> allWindows = driver.getWindowHandles();
		//Duyet qua tat ca windows/tabs, cai nao khac voi parent ID thi switch qua
		for (String runWindow : allWindows) {
			System.out.println("Window ID = "+ runWindow);
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}
	  
	public void switchToWindowByTitle(String expectedTitle) {
		//Get all current window
		Set<String> allWindows = driver.getWindowHandles();
		//Duyet qua tat ca windows/tabs
		for (String runWindows : allWindows) {
			//Switch qua tung window/tab
			driver.switchTo().window(runWindows);
			//Get title cua window/tab dang switch qua
			String currentWin = driver.getTitle();
			//Neu title get duoc khi switch qua = expectedTitle thi ngung
			if (currentWin.equals(expectedTitle)) {
				break;
			}
		}
	}
	  //Dong tat ca windows/tabs ngoai tru parent window
	public boolean closeAllWithoutParentWindows(String ParentGUID) {
		//Get all current window
		Set<String> allWindows = driver.getWindowHandles();
		//Duyet qua tung id cua tung window/tab
		for (String runWindows : allWindows) {
			//kiem tra neu gui id nao khac voi parent window thi switch qua va close va duyet qua tab khac
			if (!runWindows.equals(ParentGUID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		//Neu no bang chi co 1 parent id thi thoi
		driver.switchTo().window(ParentGUID);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}
	public void overrideGlobalWait(long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
}