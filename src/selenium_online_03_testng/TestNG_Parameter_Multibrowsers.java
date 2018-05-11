package selenium_online_03_testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import selenium_api.helpers.CommonMethods;

public class TestNG_Parameter_Multibrowsers {
	WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod
	public void beforeMethod(String browser) {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");
	}

	@Parameters({ "username", "password" })
	@Test()
	public void Customer_01_LoginToApplication(String username, String pass) {
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
		WebElement clickBtn = driver.findElement(By.xpath("//input[@name='btnLogin']"));
		CommonMethods.clickForElement(driver, clickBtn);
		WebElement welcomeMsg = driver.findElement(By.xpath("//marquee[@class='heading3']"));
		Assert.assertTrue(welcomeMsg.isDisplayed());
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
