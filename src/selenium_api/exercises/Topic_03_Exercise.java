package selenium_api.exercises;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_03_Exercise {
	private WebDriver driver;
	WebElement email;
	WebElement edu;
	WebElement under18;
	WebElement development;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		email = driver.findElement(By.xpath("//*[@id='mail']"));
		edu = driver.findElement(By.xpath("//*[@id='edu']"));
		under18 = driver.findElement(By.xpath("//*[@id='under_18']"));
		development = driver.findElement(By.xpath("//*[@id='development']"));
	}
	

	@Test
	public void checkIsDisplayed() {
		Assert.assertEquals(email.isDisplayed(), true);
		Assert.assertEquals(edu.isDisplayed(), true);
		Assert.assertEquals(under18.isDisplayed(), true);
		
		email.sendKeys("Automation Testing");
		edu.sendKeys("Automation Testing");
		under18.click();	
		
	}
	
	@Test
	public void checkEnabledDisabled() {
		}
	
	@Test
	public void checkIsSelected() {
		under18.click();
		development.click();
		
		if (under18.isSelected()){
			System.out.println("Age (Under 18) is selected");
		}else{
			under18.click();
		}
		
		if (development.isSelected()){
			System.out.println("Interests (Development) is selected");
		}else{
			development.click();
		}
	}
	
	@Test
	public void run3Browsers() {
		}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
