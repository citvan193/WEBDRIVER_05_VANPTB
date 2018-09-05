package selenium_api;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_UploadFile {
    WebDriver driver;
    String projectDirectory = System.getProperty("user.dir");
    String fileName = "upload.png";
    String fileName_01 = "upload.png";
    String fileName_02 = "upload.png";
    String fileName_03 = "upload.png";
    
    String uploadFilePath = projectDirectory + "\\images\\" + fileName;
    String uploadFilePath_00 = projectDirectory + "\\images\\" + fileName;
    String uploadFilePath_01 = projectDirectory + "\\images\\" + fileName;
    String uploadFilePath_02 = projectDirectory + "\\images\\" + fileName;
   
    String chromeUpload = projectDirectory + "\\upload\\chrome.exe";
    String firefoxUpload = projectDirectory + "\\upload\\firefox.exe";
    String ieUpload = projectDirectory + "\\upload\\ie.exe";
    String folderName = "online05"+ randomNumber();
    String email = "online05" + randomNumber() + "@gmail.com";
    String name = "Automation FC";
    
    

	
    @BeforeClass
	public void beforeClass() {
    	//System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
   	  	//driver = new ChromeDriver();
		driver = new FirefoxDriver();
   	  	//System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
   	  	//driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
    }
    
   
	
	public void TC_01_SendkeyAPI() {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement uploadFileElment = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFileElment.sendKeys(uploadFilePath);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName + "']")).isDisplayed());
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName + "']")).isDisplayed());
	}
	
	//lam tay chon duong dan doi voi IE
	public void TC_02_AutoIT() throws IOException, InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		Thread.sleep(4000);
		/*Chrome/FF
		WebElement uploadFileElment = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadFileElment.click();
		Runtime.getRuntime().exec(new String[] { chromeUpload, uploadFilePath });*/
		
		//IE
		WebElement uploadFileElment = driver.findElement(By.xpath("//input[@type='file']"));
		clickToElementByJS(uploadFileElment);
		Thread.sleep(3000);
		Runtime.getRuntime().exec(new String[] { ieUpload, uploadFilePath });
		Thread.sleep(3000);
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName + "']")).isDisplayed());
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName + "']")).isDisplayed());
	}
	
	public void TC_03_Robot() throws InterruptedException, Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		StringSelection select = new StringSelection(uploadFilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		WebElement uploadFileElment = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadFileElment.click();
		Thread.sleep(3000);
		
		Robot robot = new Robot();
		// forcus to textbox
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		//Nhan phim Ctrl V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	
	public void TC_04_UploadFileChucker() {
		driver.get("https://encodable.com/uploaddemo/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(uploadFilePath);
		WebElement uploadFileElment = driver.findElement(By.xpath("//select[@name='subdir1']"));
		Select select = new Select(uploadFileElment);
		select.selectByVisibleText("/uploaddemo/files/");
		driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(folderName);
		driver.findElement(By.xpath("//input[@id='formfield-email_address']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='formfield-first_name']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dd[text()='Email Address: " + email + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dd[text()='First Name: " + name + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']//dt[contains(text(),'File 1 of 1:')]/a[text()='" + fileName + "']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='View Uploaded Files']")).click();
		driver.findElement(By.xpath("//a[text()='" + folderName + "']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + folderName + "']")).isDisplayed());
	}
	@Test
    public void TC_05_Upload_Multiple_Files() throws Exception {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");

		String files[] = {uploadFilePath_00, uploadFilePath_01, uploadFilePath_02};
		
		int fileLength = files.length;
		// Duyet qua tung phan tu cua mang
		// Mang (3 phan tu): 0 - 1 - 2
		
		// For
		for (int i = 0; i < fileLength; i++) {
			WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
			System.out.println("Item = " + files[i]);
			uploadElement.sendKeys(files[i]);
		}

		// Check 3 images displayed
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName_03 + "']")).isDisplayed());
		
		// Click 3 Start button

		List<WebElement> startButton = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		
		// For-each
		for (WebElement start : startButton) {
			start.click();
			Thread.sleep(1000);
		}

		// Check 3 images uploaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName_03 + "']")).isDisplayed());

		// Check 3 images real
		List<WebElement> images = driver.findElements(By.xpath("//table[@class='table table-striped']//img"));
		for (WebElement image : images) {
			Assert.assertTrue(checkAnyImageLoaded(image));
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public int randomNumber() {
		  Random random =new Random();
		  int number = random.nextInt(999999);
		  return number;
		  }
	// Execute for Browser
		public void clickToElementByJS(WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		}
		public boolean checkAnyImageLoaded (WebElement element) {
			return element.isDisplayed();
		}
}