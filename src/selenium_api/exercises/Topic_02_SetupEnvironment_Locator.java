package selenium_api.exercises;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import selenium_api.helpers.CommonMethods;

public class Topic_02_SetupEnvironment_Locator {
	WebDriver driver;
	private String loginPage;
	private String createPage;

	@BeforeClass
	public void initData() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();

		// System.setProperty("webdriver.ie.driver",
		// ".\\driver\\IEDriverServer.exe");
		// driver = new InternetExplorerDriver();

		// driver = new FirefoxDriver();
	}

	@BeforeMethod

	public void dataForEachTC() {

		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		loginPage = "http://live.guru99.com/index.php/customer/account/login/";
		createPage = "http://live.guru99.com/index.php/customer/account/create/";
	}

	@Test(priority = 1, enabled = false)
	public void verifyURLandTitle() {
		String homepageTitle = driver.getTitle();
		Assert.assertEquals(homepageTitle, "Home page");

		driver.get("http://live.guru99.com/index.php/customer/account/");
		driver.navigate().to(createPage);

		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), loginPage);

		driver.navigate().forward();
		Assert.assertEquals(driver.getCurrentUrl(), createPage);

	}

	@Test(priority = 2, enabled = false)
	public void loginEmpty() {
		driver.navigate().to(loginPage);
		driver.findElement(By.xpath("//input[@id='email']"));
		driver.findElement(By.xpath("//input[@id='pass']"));
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='advice-required-entry-email']")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='advice-required-entry-pass']")).getText(),
				"This is a required field.");

	}

	@Test(priority = 3, enabled = false)
	public void loginWithEmailIncorrect() {
		driver.navigate().to(loginPage);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		;
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='advice-validate-email-email']")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test(priority = 4, enabled = false)
	public void loginWithPasswordIncorrect() {
		driver.navigate().to(loginPage);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		;
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		;
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='advice-validate-password-pass']")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test(priority = 5)
	public void createAnAccount() {
		driver.navigate().to(loginPage);
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		driver.findElement(By.xpath("//*[@id='firstname']")).sendKeys("Ha");
		driver.findElement(By.xpath("//*[@id='lastname']")).sendKeys("Vuong");

		driver.findElement(By.xpath("//*[@id='email_address']"))
				.sendKeys("havuong" + CommonMethods.randomData() + "@yopmail.com");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("abcd123456");
		driver.findElement(By.xpath("//*[@id='confirmation']")).sendKeys("abcd123456");

		driver.findElement(By.xpath("//button[@title='Register']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']/ul/li/span")).getText(),
				"Thank you for registering with Main Website Store.");

		driver.findElement(By.xpath("//*[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath("//li[@class=' last']//a[@title='Log Out']")).click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
