package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_04_Custom_Dropdowlist {
    WebDriver driver;
    WebDriverWait wait;
	
    @BeforeClass
	public void beforeClass() {
    	//TC2 Algula chay loi tren FF47
    	System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
   	  	driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
    }
    
   
	@Test (enabled = false)
	public void TC_01_Jquery() throws InterruptedException {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		selectCustomDropdowlist("//span[@id='speed-button']", "//ul[@id='speed-menu']//li[@class ='ui-menu-item']/div", "Fast");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text' and text()='Fast']")).isDisplayed());
		
		selectCustomDropdowlist("//span[@id='files-button']", "//ul[@id='files-menu']//li[@class ='ui-menu-item']/div", "Some unknown file");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='files-button']//span[@class='ui-selectmenu-text' and text()='Some unknown file']")).isDisplayed());
		
		selectCustomDropdowlist("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class ='ui-menu-item']/div", "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		
		selectCustomDropdowlist("//span[@id='salutation-button']", "//ul[@id='salutation-menu']//li[@class='ui-menu-item']/div", "Prof.");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='salutation-button']//span[@class='ui-selectmenu-text' and text()='Prof.']")).isDisplayed());
		
	}
	@Test (enabled = false)
	public void TC_02_Angular() throws InterruptedException {
		driver.get("https://material.angular.io/components/select/examples");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		selectCustomDropdowlist("//mat-select[@id='mat-select-0']", "//mat-option/span", "Pizza");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Pizza']")).isDisplayed());
		
		selectCustomDropdowlist("//mat-select[@placeholder='State']", "//mat-option/span", "Hawaii");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Hawaii']")).isDisplayed());
		
	}
	@Test (enabled = false)
	public void TC_03_KendoUI() throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		selectCustomDropdowlist("//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']//li", "Orange");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='k-input' and text()='Orange']")).isDisplayed());
			
		selectCustomDropdowlist("//span[@aria-owns='size_listbox']", "//ul[@id='size_listbox']//li", "L - 7 1/8\"");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='k-input' and text()='L - 7 1/8\"']")).isDisplayed());
		
	}
	
	@Test 
	public void TC_04_VueJS() throws InterruptedException {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		selectCustomDropdowlist("//li[@class='dropdown-toggle']", "//ul/li", "Second Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
		
	}
	
	public void selectCustomDropdowlist(String dropdown, String listItems, String valueItem) throws InterruptedException {
		//Click vao Dropdown
		WebElement dropdownElement = driver.findElement(By.xpath(dropdown));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",dropdownElement);
		dropdownElement.click();
		//Get tat ca item trong dropdown vao 1 list element (List <WebElement>
		List<WebElement> allItems = driver.findElements(By.xpath(listItems));
		//Wait de tat ca phan tu trong dropdown duoc hien thi
		wait.until(ExpectedConditions.visibilityOfAllElements(allItems));
		//Dung vong lap for de duyet qua tung phan tu
		for (WebElement item :allItems) {
			if (item.getText().trim().equals(valueItem)) {
				//Scroll to item
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",item);
				item.click();
				Thread.sleep(2000);
				break;
			}
		}
	}
	
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}