package selenium_api.exercises;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_UserInteractions {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		// driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_01_hoverMouse() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement hoverText = driver.findElement(By.xpath("//a[contains(text(),'Hover over me')]"));

		Actions action = new Actions(driver);
		action.moveToElement(hoverText).perform();

		WebElement hoorayText = driver.findElement(By.xpath("//div[@class='tooltip-inner']"));
		Assert.assertTrue(hoorayText.isDisplayed());
		Assert.assertEquals(hoorayText.getText(), "Hooray!");
	}

	@Test
	public void TC_01_02_hoverMouse() {
		driver.get("http://www.myntra.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement menuLink = driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']"));

		Actions action = new Actions(driver);
		action.moveToElement(menuLink).perform();

		WebElement loginBtn = driver.findElement(By.xpath("//a[@class='desktop-linkButton' and @data-track='login']"));
		loginBtn.click();

		WebElement loginForm = driver.findElement(By.xpath("//div[@id='mountRoot']//div[@class='login-box']"));
		Assert.assertTrue(loginForm.isDisplayed());
	}

	@Test
	public void TC_02_clickAndHold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		List<WebElement> selectNumber = driver.findElements(By.xpath("//*[@id='selectable']/li"));

		Actions action = new Actions(driver);
		action.clickAndHold(selectNumber.get(0)).clickAndHold(selectNumber.get(3)).click().perform();

		List<WebElement> selectedNumber = driver
				.findElements(By.xpath("//*[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(selectedNumber.size(), 4);
	}

	@Test
	public void TC_03_doubleClick() {
		driver.get("http://www.seleniumlearn.com/double-click");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement dclickBtn = driver.findElement(By.xpath("//button[@type='button']"));

		Actions action = new Actions(driver);
		action.doubleClick(dclickBtn).perform();

		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
		alert.accept();
	}

	@Test
	public void TC_04_rightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement rightclickBtn = driver.findElement(By.xpath("//span[contains(text(),'right click me')]"));
		Actions action = new Actions(driver);
		action.contextClick(rightclickBtn).perform();

		WebElement quitElement = driver
				.findElement(By.xpath("//li[contains(@class,'context-menu-item') and contains(.,'Quit')]"));

		// hover Quit element
		action.moveToElement(quitElement).perform();
		WebElement quitHover = driver
				.findElement(By.xpath("//li[contains(@class,'context-menu-hover') and contains(.,'Quit')]"));
		Assert.assertTrue(quitHover.isDisplayed());

		WebElement quitVisible = driver
				.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(.,'Quit')]"));
		Assert.assertTrue(quitVisible.isDisplayed());

		quitVisible.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	@Test
	public void TC_05_01_dragAndDrop() {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement sourceBtn = driver.findElement(By.xpath("//*[@id='draggable']"));
		WebElement targetBtn = driver.findElement(By.xpath("//*[@id='droptarget']"));

		Actions action = new Actions(driver);
		action.dragAndDrop(sourceBtn, targetBtn).build().perform();
		Assert.assertEquals(targetBtn.getText(), "You did great!");

	}

	@Test
	public void TC_05_02_dragAndDrop() {
		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement sourceBtn = driver.findElement(By.xpath("//*[@id='draggable']"));
		WebElement targetBtn = driver.findElement(By.xpath("//*[@id='droppable']"));

		Actions action = new Actions(driver);
		action.dragAndDrop(sourceBtn, targetBtn).build().perform();
		Assert.assertEquals(targetBtn.getText(), "Dropped!");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
