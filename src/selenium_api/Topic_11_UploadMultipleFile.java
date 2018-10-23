package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_UploadMultipleFile {
    WebDriver driver;
    String projectDirectory = System.getProperty("user.dir");
    String fileName_01 = "01.png";
    String fileName_02 = "02.png";
    String fileName_03 = "03.png";
    
    String uploadFilePath_01 = projectDirectory + "\\images\\" + fileName_01;
    String uploadFilePath_02 = projectDirectory + "\\images\\" + fileName_02;
    String uploadFilePath_03 = projectDirectory + "\\images\\" + fileName_03;
     
    @BeforeClass
	public void beforeClass() {
    	System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
   	  	driver = new ChromeDriver();
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   	    driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		driver.manage().window().maximize();
    }
    
  	@Test
    public void TC_05_Upload_Multiple_Files_OneTime() throws Exception {
  		System.out.println(uploadFilePath_01);
  		System.out.println(uploadFilePath_02);
  		System.out.println(uploadFilePath_03);
  		
  		WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
  		uploadElement.sendKeys(uploadFilePath_01 + "\n " + uploadFilePath_02 + "\n " + uploadFilePath_03);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName_03 + "']")).isDisplayed());
		Thread.sleep(5000);
		List<WebElement> startButton = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for (WebElement start : startButton) {
			start.click();
			Thread.sleep(1000);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName_03 + "']")).isDisplayed());

		List<WebElement> images = driver.findElements(By.xpath("//table[@class='table table-striped']//img"));
		for (WebElement image : images) {
			Assert.assertTrue(checkAnyImageLoaded(image));
			Thread.sleep(1000);
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public boolean checkAnyImageLoaded(WebElement element) {
		return element.isDisplayed();
	}
}