package selenium_api.exercises;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import selenium_api.helpers.CommonMethods;

public class Topic_09_UploadFile {
	WebDriver driver;
	String filePath = "C:\\Users\\Dell.LAPTOP-KERGBHK6\\Downloads\\crop3.jpg";
	String fileName = "crop3.jpg";

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
//		driver = new ChromeDriver();

		 System.setProperty("webdriver.ie.driver",
		 ".\\driver\\IEDriverServer.exe");
		 driver = new InternetExplorerDriver();
//		driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_sendKey() throws Exception {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(filePath);
		Thread.sleep(2000);

		WebElement selectedImage = driver
				.findElement(By.xpath("//p[@class='name' and contains(text(),'" + fileName + "')]"));
		Assert.assertTrue(selectedImage.isDisplayed());
		Thread.sleep(2000);
		
		WebElement startBtn = driver.findElement(By.xpath("//span[text()='Start']"));
		CommonMethods.clickForElement(driver, startBtn);
		Thread.sleep(5000);
		
		WebElement uploadedImg = driver.findElement(By.xpath("//img[contains(@src,'crop3.jpg')]"));
		CommonMethods.checkAnyImageLoaded(driver,uploadedImg);
	}

	public void TC_02_AutoIT() {
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_03_Robot() {
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_04_upload() {
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
