package selenium_api.exercises;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import selenium_api.helpers.CommonMethods;

public class Topic_09_UploadFile {
	WebDriver driver;
	String filePath = "C:\\Users\\Dell.LAPTOP-KERGBHK6\\Downloads\\crop3.jpg";
	String fileName = "crop3.jpg";
	String subFolder = "Automation" + CommonMethods.randomData();
	String email = "havuong" + CommonMethods.randomData() + "@yopmail.com";
	String name = "Ha";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();

		// System.setProperty("webdriver.ie.driver",
		// ".\\driver\\IEDriverServer.exe");
		// driver = new InternetExplorerDriver();
		// driver = new FirefoxDriver();
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
		Assert.assertTrue(CommonMethods.checkAnyImageLoaded(driver, uploadedImg));
	}

	@Test
	public void TC_02_AutoIT() throws Exception {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(filePath);
		Thread.sleep(2000);

		Runtime.getRuntime().exec(new String[] { ".\\autoIT\\chrome.exe", filePath });
		Thread.sleep(2000);

		WebElement startBtn = driver.findElement(By.xpath("//span[text()='Start']"));
		CommonMethods.clickForElement(driver, startBtn);
		Thread.sleep(5000);

		WebElement uploadedImg = driver.findElement(By.xpath("//img[contains(@src,'crop3.jpg')]"));
		Assert.assertTrue(CommonMethods.checkAnyImageLoaded(driver, uploadedImg));
	}

	@Test
	public void TC_03_Robot() throws Exception {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		StringSelection select = new StringSelection("C:\\Users\\Dell.LAPTOP-KERGBHK6\\Downloads\\crop3.jpg");

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		driver.findElement(By.className("fileinput-button")).click();

		Robot robot = new Robot();
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	@Test
	public void TC_04_Workflow() throws Exception {
		driver.get("https://encodable.com/uploaddemo/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(filePath);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//select[@name='subdir1']")).click();
		driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(subFolder);
		driver.findElement(By.xpath("//input[@id='formfield-email_address']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='formfield-first_name']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();

		// Verify
		WebElement emailVerify = driver.findElement(By.xpath("//dd[contains(text(),'Email Address: " + email + "')]"));
		Assert.assertTrue(emailVerify.isDisplayed());
		WebElement nameVerify = driver.findElement(By.xpath("//dd[contains(text(),'First Name: " + name + "')]"));
		Assert.assertTrue(nameVerify.isDisplayed());
		WebElement fileVerify = driver.findElement(By.xpath("//a[contains(text(),'" + fileName + "')]"));
		Assert.assertTrue(fileVerify.isDisplayed());

		driver.findElement(By.xpath("//a[contains(text(),'View Uploaded Files')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'" + subFolder + "')]")).click();
		WebElement imageDisplayed = driver.findElement(By.xpath("//img[contains(@src,'" + fileName + "')]"));
		Assert.assertTrue(imageDisplayed.isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
