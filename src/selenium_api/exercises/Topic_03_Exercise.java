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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import selenium_api.helpers.CommonMethods;

public class Topic_03_Exercise {
	private WebDriver driver;
	WebElement email;
	WebElement edu;
	WebElement under18;
	WebElement development;
	WebElement jobrole01;
	WebElement slider01;
	WebElement btnEnabled;
	WebElement password;
	WebElement ageRadioDisabled;
	WebElement biography;
	WebElement jobrole02;
	WebElement interestsDisable;
	WebElement slider02;
	WebElement btnDisabled;
	
	@BeforeClass
	public void initData() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
	    driver = new ChromeDriver();
		
//		System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();
		
//		driver = new FirefoxDriver();
	}
	
	@BeforeMethod
	public void dataForEachTC() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		email = driver.findElement(By.xpath("//*[@id='mail']"));
		edu = driver.findElement(By.xpath("//*[@id='edu']"));
		under18 = driver.findElement(By.xpath("//*[@id='under_18']"));
		development = driver.findElement(By.xpath("//*[@id='development']"));
		jobrole01 = driver.findElement(By.xpath("//*[@id='job1']"));
		slider01 = driver.findElement(By.xpath("//*[@id='slider-1']"));
		btnEnabled = driver.findElement(By.xpath("//*[@id='button-enabled']"));
		password = driver.findElement(By.xpath("//*[@id='password']"));
		ageRadioDisabled = driver.findElement(By.xpath("//*[@id='radio-disabled']"));
		biography = driver.findElement(By.xpath("//*[@id='bio']"));
		jobrole02 = driver.findElement(By.xpath("//*[@id='job2']"));
		interestsDisable = driver.findElement(By.xpath("//*[@id='check-disbaled']"));
		slider02 = driver.findElement(By.xpath("//*[@id='slider-2']"));
		btnDisabled = driver.findElement(By.xpath("//*[@id='button-disabled']"));
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
		CommonMethods.checkElementEnabled(email);
		CommonMethods.checkElementEnabled(under18);
		CommonMethods.checkElementEnabled(edu);
		CommonMethods.checkElementEnabled(jobrole01);
		CommonMethods.checkElementEnabled(development);
		CommonMethods.checkElementEnabled(slider01);
		CommonMethods.checkElementEnabled(btnEnabled);
		CommonMethods.checkElementEnabled(password);
		CommonMethods.checkElementEnabled(ageRadioDisabled);
		CommonMethods.checkElementEnabled(biography);
		CommonMethods.checkElementEnabled(jobrole02);
		CommonMethods.checkElementEnabled(interestsDisable);
		CommonMethods.checkElementEnabled(slider02);
		CommonMethods.checkElementEnabled(btnDisabled);
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
