package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_03_WebElement_Browser {
	WebDriver driver;
 
  @BeforeClass
  public void beforeClass() {
	  //System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
	  //driver = new InternetExplorerDriver();
	  System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("http://daominhdam.890m.com/");
	  
  }
  
  @Test (enabled = false)
  public void TC01_IsDisplayed() throws InterruptedException {
	  WebElement emailTextbox = driver.findElement(By.xpath("//input [@id='mail']"));
	  WebElement ageRadioButtton = driver.findElement(By.xpath("//input [@id='under_18']"));
	  WebElement eduTextarea = driver.findElement(By.xpath("//textarea [@id='edu']"));
	  
	  Assert.assertTrue(isControlDisplayed(emailTextbox));
	  Assert.assertTrue(isControlDisplayed(ageRadioButtton));
	  Assert.assertTrue(isControlDisplayed(eduTextarea));
	  
	  if (isControlDisplayed(emailTextbox) && isControlDisplayed(eduTextarea) && isControlDisplayed(ageRadioButtton)) {
		  emailTextbox.sendKeys("Automation Testing");
		  ageRadioButtton.click();
		  eduTextarea.sendKeys("Automation Testing");
	  }
	 
	  Thread.sleep(5000);
  }

 @Test (enabled = false)
 public void TC02_IsEnable() {
	 WebElement emailTextbox = driver.findElement(By.xpath("//input [@id='mail']"));
	 WebElement ageRadioButtton = driver.findElement(By.xpath("//input [@id='under_18']"));
	 WebElement eduTextarea = driver.findElement(By.xpath("//textarea [@id='edu']"));
	 WebElement job1Select = driver.findElement(By.xpath("//select [@id='job1']"));
	 WebElement developmentCheckbox = driver.findElement(By.xpath("//input [@id='development']")); 
	 WebElement slider1Range = driver.findElement(By.xpath("//input [@id='slider-1']"));
	 WebElement buttonEnabe = driver.findElement(By.xpath("//button [@id='button-enabled']"));
	 WebElement passTextbox = driver.findElement(By.xpath("//input [@id='password']"));
	 WebElement ageRadioButttonDisable = driver.findElement(By.xpath("//input [@id='radio-disabled']"));
	 WebElement bioTextarea = driver.findElement(By.xpath("//textarea [@id='bio']"));
	 WebElement job2Select = driver.findElement(By.xpath("//select [@id='job2']"));
	 WebElement disableCheckbox = driver.findElement(By.xpath("//input [@id='check-disbaled']"));
	 WebElement slider2Range = driver.findElement(By.xpath("//input [@id='slider-2']"));
	 WebElement buttonDisable = driver.findElement(By.xpath("//button [@id='button-disabled']"));
	 
	 isControlEnabled(emailTextbox);
	 isControlEnabled(ageRadioButtton);
	 isControlEnabled(eduTextarea);
	 isControlEnabled(job1Select);
	 isControlEnabled(developmentCheckbox);
	 isControlEnabled(slider1Range);
	 isControlEnabled(buttonEnabe);
	 isControlEnabled(passTextbox);
	 isControlEnabled(ageRadioButttonDisable);
	 isControlEnabled(bioTextarea);
	 isControlEnabled(job2Select);
	 isControlEnabled(disableCheckbox);
	 isControlEnabled(slider2Range);
	 isControlEnabled(buttonDisable);
	 
 }
 
 @Test
 public void TC03_IsSelected() {
	 WebElement ageRadioButtton = driver.findElement(By.xpath("//input [@id='under_18']"));
	 WebElement developmentCheckbox = driver.findElement(By.xpath("//input [@id='development']"));
	 ageRadioButtton.click();
	 //developmentCheckbox.click();
	 isControlSelected(ageRadioButtton);
	 isControlSelected(developmentCheckbox);
 }

 public boolean isControlDisplayed(WebElement element) {
	 return element.isDisplayed();	 
 }
 public void isControlEnabled(WebElement element) {
	 if (element.isEnabled()) {
		 System.out.println("Element is enabled");
	 }else {
		 System.out.println("Element is disabled");
	 }
 }
 public void isControlSelected(WebElement element) {
	 if (!element.isSelected()) {
		 System.out.println("Element isn's selected");
		 element.click();		 
	 }else {
		 System.out.println("Element is selected");
	 }
 }
 
  @AfterClass
  public void afterClass() {
	 driver.quit();
  }

}
