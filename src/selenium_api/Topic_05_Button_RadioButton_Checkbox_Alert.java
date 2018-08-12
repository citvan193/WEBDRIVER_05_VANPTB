package selenium_api;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Button_RadioButton_Checkbox_Alert {
    WebDriver driver;
	
    @BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
    }
    
   
	@Test (enabled = false)
	public void TC_01_Button() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='login-form']")).isDisplayed());
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		
		//click button bang javascript
		String button = "//a[@class='button']";
		clickElementByJavascript(button);
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='form-validate']")).isDisplayed());
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	}
	
	
	@Test (enabled = false)
	public void TC_02_Checkbox() {
	//Step 01 - Truy cap vao trang: http://demos.telerik.com/kendo-ui/styling/checkboxes
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
	//Step 02 + 03 - Click vao checkbox: Dual-zone air conditioning va kiem tra checkbox duoc chon

		//Case 4: Dung 1 elemennt vua click duoc vua verify duoc
		String dualZoneCheckbox = "//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
		clickElementByJavascript(dualZoneCheckbox);
		Assert.assertTrue(isElementSelected(dualZoneCheckbox));
	// Step 04 - Sau khi checkbox da duoc chon - deselect no va kiem tra no chua duoc chon
		unCheckTheCheckbox(dualZoneCheckbox);
		Assert.assertFalse(isElementSelected(dualZoneCheckbox));
	}
	
	
	@Test
	public void TC_03_RadioButton() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		String petrol2Radio = "//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input";
		String diesel16Radio = "//label[text()='1.6 Diesel, 77kW']/preceding-sibling::input";
		
		clickElementByJavascript(petrol2Radio);
		Assert.assertTrue(isElementSelected(petrol2Radio));
	//Step 3 - Click vao raido khac, kiem tra radio 2 petrol chua duoc chon va chon lai	
		clickElementByJavascript(diesel16Radio);
		Assert.assertFalse(isElementSelected(petrol2Radio));
		clickElementByJavascript(petrol2Radio);
		Assert.assertTrue(isElementSelected(petrol2Radio));
	}
	
	
	@Test (enabled = false)
	public void TC_04_Alert() {
		driver.get("http://daominhdam.890m.com/");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		Alert alert = driver.switchTo().alert();
		String alertMsg = alert.getText();
		Assert.assertTrue(alertMsg.equals("I am a JS Alert"));
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked an alert successfully");
	}
	
	
	@Test (enabled = false)
	public void TC_05_Confirm() {
		driver.get("http://daominhdam.890m.com/");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Alert alert = driver.switchTo().alert();
		String alertMsg = alert.getText();
		Assert.assertTrue(alertMsg.equals("I am a JS Confirm"));
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked: Cancel");
	}
	
	
	@Test (enabled = false)
	public void TC_06_Prompt() {
		driver.get("http://daominhdam.890m.com/");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Alert alert = driver.switchTo().alert();
		String alertMsg = alert.getText();
		Assert.assertTrue(alertMsg.equals("I am a JS prompt"));
		alert.sendKeys("Automatic Testing online 05");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You entered: Automatic Testing online 05");
	}
	
	
	public void clickElementByJavascript(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
	    JavascriptExecutor je = (JavascriptExecutor) driver;
	    je.executeScript("arguments[0].click();", element);
	}
	
	public boolean isElementSelected (String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}
	
	public void unCheckTheCheckbox (String locator) {
		if (isElementSelected(locator)) {
			clickElementByJavascript(locator);
		}
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}