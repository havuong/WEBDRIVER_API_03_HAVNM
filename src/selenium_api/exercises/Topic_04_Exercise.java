package selenium_api.exercises;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Exercise {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	@Test
	public void handleDropdownList(){
		
	}
	
	@Test
	public void handleTextboxAndTextArea(){
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
