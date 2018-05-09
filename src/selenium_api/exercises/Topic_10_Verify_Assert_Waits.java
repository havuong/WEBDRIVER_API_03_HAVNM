package selenium_api.exercises;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_10_Verify_Assert_Waits {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);
	}

	@Test
	public void TC_01_ImplicitWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//*[@id='start']/button")).click();
		String resultText = driver.findElement(By.xpath("//*[@id='finish']/h4")).getText();
		Assert.assertEquals(resultText, "Hello World!");
	}

	@Test
	public void TC_02_ExplicitWait() {
		driver.get(
				"http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		By dateTimePicker = By.xpath("//*[@id='ctl00_ContentPlaceholder1_Panel1']/div");
		wait.until(ExpectedConditions.visibilityOfElementLocated(dateTimePicker));

		WebElement selectedDateBefore = driver
				.findElement(By.xpath("//*[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']"));
		System.out.println("Truoc khi chon = " + selectedDateBefore.getText());
		Assert.assertEquals(selectedDateBefore.getText(), "No Selected Dates to display.");

		WebElement today = driver.findElement(By.xpath("//a[text()='10']"));
		today.click();

		By ajaxIcon = By.xpath("//div[@class='raDiv']");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxIcon));

		By todaySelected = By.xpath("//td[contains(@class,'rcSelected')]//a[text()='10']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(todaySelected));

		WebElement todaySelected_ = driver.findElement(By.xpath("//td[contains(@class,'rcSelected')]//a[text()='10']"));
		Assert.assertTrue(todaySelected_.isDisplayed());

		WebElement selectedDateAfter = driver
				.findElement(By.xpath("//*[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']"));
		System.out.println("Sau khi chon = " + selectedDateAfter.getText());
		Assert.assertEquals(selectedDateAfter.getText(), "Thursday, May 10, 2018");
	}

	@Test
	public void TC_03_FluentWait01() {
		driver.get("https://stuntcoders.com/snippets/javascript-countdown/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement countdount = driver.findElement(By.xpath("//*[@id='javascript_countdown_time']"));
		Assert.assertTrue(countdount.isDisplayed());

		By countDownBy = By.xpath("//*[@id = 'javascript_countdown_time']");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(countDownBy));

		new FluentWait<WebElement>(countdount).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						boolean flag = element.getText().endsWith("00");
						System.out.println("Time = " + element.getText());
						return flag;
					}
				});
	}

	@Test
	public void TC_03_FluentWait02() {
		driver.get("http://toolsqa.wpengine.com/automation-practice-switch-windows/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement changeColorBtn = driver.findElement(By.xpath("//*[@id='colorVar']"));
		WebElement clockText = driver.findElement(By.xpath("//*[@id='clock']"));

		new FluentWait<WebElement>(changeColorBtn).withTimeout(60, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class).until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						boolean flag = element.getAttribute("style").contentEquals("color: red;");
						System.out.println(element.getAttribute("style"));
						return flag;
					}
				});

		new FluentWait<WebElement>(clockText).withTimeout(60, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class).until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						boolean flag = element.getText().contains("Buzz Buzz");
						System.out.println(element.getText());
						return flag;
					}
				});

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
