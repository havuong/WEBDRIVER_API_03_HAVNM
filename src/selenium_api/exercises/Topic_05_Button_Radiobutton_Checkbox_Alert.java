package selenium_api.exercises;

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

public class Topic_05_Button_Radiobutton_Checkbox_Alert {
	WebDriver driver;
	private String name = "Ha Vuong";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	@Test
	public void TC01_javascriptClick() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//*[@id='button-enabled']")).click();

		Assert.assertEquals("http://daominhdam.890m.com/#", driver.getCurrentUrl());
		driver.navigate().back();

		WebElement enable = driver.findElement(By.xpath("//*[@id='button-enabled']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", enable);
		Assert.assertEquals("http://daominhdam.890m.com/#", driver.getCurrentUrl());
	}

	@Test
	public void TC02_checkbox() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement checkbox = driver.findElement(
				By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);

		Assert.assertTrue(checkbox.isSelected());

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
		Assert.assertFalse(checkbox.isSelected());
		Assert.assertTrue(!checkbox.isSelected());
	}

	@Test
	public void TC03_radioButton() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement radiobtn = driver
				.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", radiobtn);

		if (!radiobtn.isSelected()) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", radiobtn);
		}

	}

	@Test
	public void TC04_acceptAlert() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement alert_btn = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]"));
		alert_btn.click();

		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("I am a JS Alert", alert.getText());

		alert.accept();

		WebElement alert_result = driver.findElement(By.xpath("//*[@id='result']"));
		Assert.assertEquals(alert_result.getText(), "You clicked an alert successfully");
	}

	@Test
	public void TC05_acceptAndCancelAlert() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement alert_btn = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]"));
		alert_btn.click();

		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("I am a JS Confirm", alert.getText());

		alert.dismiss();

		WebElement alert_result = driver.findElement(By.xpath("//*[@id='result']"));
		Assert.assertEquals(alert_result.getText(), "You clicked: Cancel");
	}

	@Test
	public void TC06_inputToAlert() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement alert_btn = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]"));
		alert_btn.click();

		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("I am a JS prompt", alert.getText());

		alert.sendKeys(name);
		alert.accept();

		Assert.assertTrue(
				driver.findElement(By.xpath("//*[@id='result' and contains(text(),'" + name + "')]")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
