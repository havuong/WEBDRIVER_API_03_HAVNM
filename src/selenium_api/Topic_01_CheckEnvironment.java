package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_01_CheckEnvironment {
	WebDriver driver;
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		String homepageTitle = driver.getTitle();
		Assert.assertEquals("Home page", homepageTitle);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
