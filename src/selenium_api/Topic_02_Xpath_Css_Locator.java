package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Locator {
	WebDriver driver;
	
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
  }
  @Test
  public void TC_02_LoginEmpty() {
	  driver.get("http://live.guru99.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  driver.findElement(By.id("send2")).click();
	  String UsernameEmptyMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
	  Assert.assertEquals(UsernameEmptyMessage, "This is a required field.");
	  String PasswordEmptyMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
	  Assert.assertEquals(PasswordEmptyMessage, "This is a required field.");
  }
  @Test
  public void TC_03_LoginWithEmailInvalid() {
	  driver.get("http://live.guru99.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  driver.findElement(By.cssSelector("#email")).sendKeys("123434234@12312.123123");
	  driver.findElement(By.id("send2")).click();
	  String EmailInvalidMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
	  Assert.assertEquals(EmailInvalidMessage, "Please enter a valid email address. For example johndoe@domain.com.");
	 
  }
  @Test
  public void TC_04_LoginWithPassIncorect() {
	  driver.get("http://live.guru99.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  driver.findElement(By.cssSelector("#pass")).sendKeys("12345");
	  driver.findElement(By.id("send2")).click();
	  String PasswordIncorectMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
	  Assert.assertEquals(PasswordIncorectMessage, "Please enter 6 or more characters without leading or trailing spaces.");
	  
  }
  @Test
  public void TC_05_CreateAnAccount() {
	  driver.get("http://live.guru99.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  driver.findElement(By.cssSelector("#firstname")).sendKeys("Phan Thi");
	  driver.findElement(By.cssSelector("#middlename")).sendKeys("Bich");
	  driver.findElement(By.cssSelector("#lastname")).sendKeys("Van");
	  driver.findElement(By.cssSelector("#email_address")).sendKeys("automation" + randomEmail() + "@gmail.com");
	  driver.findElement(By.cssSelector("#password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("#confirmation")).sendKeys("123456");
	  driver.findElement(By.xpath("//button[@title='Register']")).click();
	  String SuccessMessage = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
	  Assert.assertEquals(SuccessMessage, "Thank you for registering with Main Website Store.");
	  
  }
  
  
  @AfterClass
  public void afterClass() {
  }

  public int randomEmail() {
  Random random =new Random();
  int number = random.nextInt(999999);
  return number;
  }
}
