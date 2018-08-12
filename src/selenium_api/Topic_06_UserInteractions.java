package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_06_UserInteractions {
    WebDriver driver;
	
    @BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
    }
    
   
	@Test (enabled = false)
	public void TC_01_HoverMouse() {
		//Case 01:
		driver.get("http://daominhdam.890m.com/");
		WebElement hoverText = driver.findElement(By.xpath("//a[text()='Hover over me']"));
		Actions action = new Actions(driver);
		//hover mouse
		action.moveToElement(hoverText).perform();
		//Verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='tooltip-inner']")).getText(), "Hooray!");
		
				
		//Case 02:
		driver.get("http://www.myntra.com/");
		WebElement hoverMenu = driver.findElement(By.xpath("//span[@class='myntraweb-sprite desktop-iconUser sprites-user']"));
		action = new Actions(driver);
		action.moveToElement(hoverMenu).perform();
		driver.findElement(By.xpath("//a[@data-track='login']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());
	}
	
	@Test (enabled = false)
	public void TC_02_ClickAndHold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List <WebElement> selectList = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		Actions action = new Actions(driver);
		/*
		//Case 1:
		action.clickAndHold(selectList.get(0)).moveToElement(selectList.get(3)).release().perform();
		List <WebElement> selected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(selected.size(), 4);
		*/
		
		//Case 2:
		action.keyDown(Keys.CONTROL).build().perform();
		selectList.get(0).click();
		selectList.get(2).click();
		selectList.get(4).click();
		selectList.get(6).click();
		selectList.get(8).click();
		action.keyUp(Keys.CONTROL).build().perform();
		List <WebElement> selected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(selected.size(), 5);
	}
	
	@Test (enabled = false)
	public void TC_03_DoubleClick() {
		driver.get("http://www.seleniumlearn.com/double-click");
		WebElement doubleClick = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		Actions action = new Actions(driver);
		action.doubleClick(doubleClick).perform();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
		alert.accept();
	}
	
	@Test (enabled = false)
	public void TC_04_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightClickButton = driver.findElement(By.xpath("//span[text()='right click me']"));
		Actions action = new Actions(driver);
		action.contextClick(rightClickButton).perform();
		
		//Hover vao quit
		WebElement quitBefore = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));
		action.moveToElement(quitBefore).perform();
		//Verify hover thanh congs
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-hover') and contains(@class,'context-menu-visible')]")).isDisplayed());
		action.click(quitBefore).perform();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: quit");
		alert.accept();
		
	}
	
	@Test (enabled = false)
	public void TC_05_DragAndDrop_Case1() throws InterruptedException {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
		Assert.assertEquals(targetElement.getText(), "Drag the small circle here.");
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, targetElement).perform();
		Thread.sleep(4000);
		Assert.assertEquals(targetElement.getText(), "You did great!");
	}
	
	@Test 
	public void TC_05_DragAndDrop_Case2() throws InterruptedException {
		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
		WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetElement = driver.findElement(By.xpath("//div[@id='droppable']"));
		Assert.assertEquals(targetElement.getText(), "Drop here");

		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, targetElement).perform();
		Thread.sleep(4000);
		Assert.assertEquals(targetElement.getText(), "Dropped!");
	}
	
	@Test (enabled = false)
	public void TC_05_DragAndDrop_HTML5() throws InterruptedException {
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}