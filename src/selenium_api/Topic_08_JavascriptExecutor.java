package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_JavascriptExecutor {
    WebDriver driver;
	
    @BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
    }
    
   
	
	public void TC_01_JavascriptExecutor() {
		openAnyUrlByJS("http://live.guru99.com/");
		
		String homePageDomain = (String) executeJSForWebBrowser("return document.domain;");
		Assert.assertEquals(homePageDomain, "live.guru99.com");
		
		//String homePageURL = driver.getCurrentUrl();
		String homePageURL = (String) executeJSForWebBrowser("return document.URL;");
		Assert.assertEquals(homePageURL, "http://live.guru99.com/");
		
		WebElement mobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
		highlightElement(mobileLink);
		clickToElementByJS(mobileLink);
		
		WebElement samsungProduct = driver.findElement(By.xpath("//h2[a[text()='Samsung Galaxy']]/following-sibling::div[@class='actions']/button"));
		highlightElement(samsungProduct);
		clickToElementByJS(samsungProduct);
		
		String samsungMsg = (String) executeJSForWebBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(samsungMsg.contains("Samsung Galaxy was added to your shopping cart."));
		
		WebElement privacyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		highlightElement(privacyLink);
		clickToElementByJS(privacyLink);
		
		String privacyPageTitle = (String) executeJSForWebBrowser("return document.title;");
		Assert.assertEquals(privacyPageTitle, "Privacy Policy");
		
		scrollToBottomPage();
		
		WebElement wishlistTableContent = driver.findElement(By.xpath("//th[text()='WISHLIST']/following-sibling::td[text()='An encrypted list of products added to your Wishlist.']"));
		highlightElement(wishlistTableContent);
		Assert.assertTrue(wishlistTableContent.isDisplayed());
		
		openAnyUrlByJS("http://demo.guru99.com/v4/");
		String demoPageDomain = (String) executeJSForWebBrowser("return document.domain;");
		Assert.assertEquals(demoPageDomain, "demo.guru99.com");
		
		}
	@Test 
	public void TC_02_RemoteAttributeInDOM() {
		openAnyUrlByJS("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		String firstName = "Automation", lastName = "Testing";
		WebElement iframeResult = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(iframeResult);
		
		driver.findElement(By.xpath("//input[@name='fname']")).sendKeys(firstName);
		WebElement lastnameTextbox = driver.findElement(By.xpath("//input[@name='lname']"));
		removeAnyAttributeInDOM(lastnameTextbox, "disabled");
		lastnameTextbox.sendKeys(lastName);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		String msgSuccess = driver.findElement(By.xpath("//div[@class='w3-container w3-large w3-border']")).getText();
		Assert.assertTrue(msgSuccess.contains(firstName) && msgSuccess.contains(lastName));
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	//Open any url by JS
	public Object openAnyUrlByJS(String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '"+ url + "'");
	}
	//Highlight an element
	public Object highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].style.border='6px groove red'", element);
	}
	//Execute for Browser
	public Object executeJSForWebBrowser(String javaSript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(javaSript);
	}

	// Execute for Browser
	public void clickToElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	// Scroll to bottom page
	public Object scrollToBottomPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	//Remove thuoc tinh trong DOM
	public Object removeAnyAttributeInDOM(WebElement element, String attribute) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);

	}
}