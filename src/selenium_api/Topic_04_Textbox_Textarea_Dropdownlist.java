package selenium_api;


import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_04_Textbox_Textarea_Dropdownlist {
    WebDriver driver;
    
    String customerID=null, name, dob, addr, city, state, pin, mobilenumber, email, password, newaddr, newcity;
	
    By nameTextbox = By.xpath("//input[@name='name']");
    By genderTextbox = By.xpath("//input[@name='gender']");
    By dobTextbox = By.xpath("//input[@name='dob']");
    By addressTextarea = By.xpath("//textarea[@name='addr']");
    By cityTextbox = By.xpath("//input[@name='city']");
    By stateTextbox = By.xpath("//input[@name='state']");
    By pinTextbox = By.xpath("//input[@name='pinno']");
    By mobileNumberTextbox = By.xpath("//input[@name='telephoneno']");
    By emailTextbox = By.xpath("//input[@name='emailid']");
    By passwordTextbox = By.xpath("//input[@name='password']");
    
    @BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		name = "Automation test";
		dob = "1995-01-01";
		addr = "01 Ly Tu Trong";
		city = "Can Tho";
		state = "Ninh Kieu";
		pin = "123456";
		mobilenumber = "0123456789";
		email ="Automation" + randomEmail() + "@gmail.com";
		password ="123123";
		newaddr = "169 Nguyen Van Linh";
		newcity = "Da Nang";
    }
    
    @Test (enabled = false)
	public void TC_01_Dropdownlist() throws InterruptedException {
		driver.get("http://daominhdam.890m.com/");
		
		Select jobrole = new Select(driver.findElement(By.xpath("//select [@id='job1']")));
		Assert.assertFalse(jobrole.isMultiple());
		
		jobrole.selectByVisibleText("Automation Tester");
		Assert.assertEquals(jobrole.getFirstSelectedOption().getText(), "Automation Tester");
		Thread.sleep(2000);
		
		jobrole.selectByValue("manual");
		Assert.assertEquals(jobrole.getFirstSelectedOption().getText(), "Manual Tester");
		Thread.sleep(2000);
		
		jobrole.selectByIndex(3);
		Assert.assertEquals(jobrole.getFirstSelectedOption().getText(), "Mobile Tester");
		Thread.sleep(2000);
		
		int jobitems = jobrole.getOptions().size();
		Assert.assertEquals(jobitems, 5);
		
	}
    
	
    @Test 
	public void TC_02_Textbox_Textarea() {
    	driver.get("http://demo.guru99.com/v4/");
    	driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr134681");
    	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("mudUhaj");
    	driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
    	Assert.assertTrue(driver.findElement(By.xpath("//marquee[text() = \"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
    	
    	driver.findElement(By.xpath("//a[text()='New Customer']")).click();
    	
    	driver.findElement(nameTextbox).sendKeys(name);
    	driver.findElement(dobTextbox).sendKeys(dob);
    	driver.findElement(addressTextarea).sendKeys(addr);
    	driver.findElement(cityTextbox).sendKeys(city);
    	driver.findElement(stateTextbox).sendKeys(state);
    	driver.findElement(pinTextbox).sendKeys(pin);
    	driver.findElement(mobileNumberTextbox).sendKeys(mobilenumber);
    	driver.findElement(emailTextbox).sendKeys(email);
    	driver.findElement(passwordTextbox).sendKeys(password);
    	
    	driver.findElement(By.xpath("//input[@name='sub']")).click();

    	customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

    	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
    	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
    	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addr);
    	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
    	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
    	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
    	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobilenumber);
    	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
    	

    	driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
    	driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
    	driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
    	
    	Assert.assertFalse(driver.findElement(nameTextbox).isEnabled());
    	Assert.assertFalse(driver.findElement(genderTextbox).isEnabled());
    	Assert.assertFalse(driver.findElement(dobTextbox).isEnabled());
    	
    	Assert.assertEquals(driver.findElement(nameTextbox).getAttribute("value"), name);
    	Assert.assertEquals(driver.findElement(addressTextarea).getText(), addr);
    	
    	driver.findElement(addressTextarea).clear();;
    	driver.findElement(addressTextarea).sendKeys(newaddr);
    	driver.findElement(cityTextbox).clear();
    	driver.findElement(cityTextbox).sendKeys(newcity);
    	
    	driver.findElement(By.xpath("//input[@name='sub']")).click();
    	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), newaddr);
    	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), newcity);
    	
	}
		

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	 public int randomEmail() {
		  Random random =new Random();
		  int number = random.nextInt(999999);
		  return number;
		  }
}