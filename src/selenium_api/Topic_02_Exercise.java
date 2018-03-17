package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Exercise {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test(priority=1)
	public void verifyURLandTitle() {
		String homepageTitle = driver.getTitle();
		Assert.assertEquals(homepageTitle,"Home page");
		
		driver.get("http://live.guru99.com/index.php/customer/account/");
		driver.navigate().to("http://live.guru99.com/index.php/customer/account/create/");
		
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.guru99.com/index.php/customer/account/login/");
		
		driver.navigate().forward();
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.guru99.com/index.php/customer/account/create/");
		
	}
	
	@Test(priority=2)
	public void loginEmpty(){
		driver.navigate().to("http://live.guru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//input[@id='email']"));
		driver.findElement(By.xpath("//input[@id='pass']"));
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='advice-required-entry-email']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='advice-required-entry-pass']")).getText(), "This is a required field.");
		
	}
	
	@Test(priority=3)
	public void loginWithEmailIncorrect(){
		driver.navigate().to("http://live.guru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");;
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals
		(driver.findElement(By.xpath("//*[@id='advice-validate-email-email']")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	@Test(priority=4)
	public void loginWithPasswordIncorrect(){
		driver.navigate().to("http://live.guru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");;
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");;
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals
		(driver.findElement(By.xpath("//*[@id='advice-validate-password-pass']")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	@Test(priority=5,enabled = false)
	public void createAnAccount(){
				
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
