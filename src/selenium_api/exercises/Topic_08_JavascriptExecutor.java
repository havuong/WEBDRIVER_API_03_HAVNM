package selenium_api.exercises;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import selenium_api.helpers.CommonMethods;

public class Topic_08_JavascriptExecutor {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		// driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_JavascriptExcecutor() throws Exception {
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		String domainName = (String) CommonMethods.executeForBrowser(driver, "return document.domain");
		Assert.assertEquals(domainName, "live.guru99.com");

		String url = (String) CommonMethods.executeForBrowser(driver, "return document.URL");
		Assert.assertEquals(url, "http://live.guru99.com/");

		WebElement mobileLink = driver.findElement(By.xpath("//a[contains(text(),'Mobile')]"));
		CommonMethods.highlightElement(driver, mobileLink);
		CommonMethods.clickForElement(driver, mobileLink);

		WebElement ssAddToCard = driver.findElement(By.xpath(
				"//h2[contains(.,'Samsung Galaxy')]/following-sibling::div[@class='actions']/button[@title='Add to Cart']"));
		CommonMethods.highlightElement(driver, ssAddToCard);
		CommonMethods.clickForElement(driver, ssAddToCard);

		Thread.sleep(5000);
		String addedText = (String) CommonMethods.executeForBrowser(driver,
				"return document.documentElement.innerText");
		Assert.assertTrue(addedText.contains("Samsung Galaxy was added to your shopping cart."));

		WebElement policyText = driver.findElement(By.xpath("//a[contains(text(),'Privacy Policy')]"));
		CommonMethods.highlightElement(driver, policyText);
		CommonMethods.clickForElement(driver, policyText);

		Thread.sleep(5000);
		String policyTitle = (String) CommonMethods.executeForBrowser(driver, "return document.title");
		Assert.assertEquals(policyTitle, "Privacy Policy");

		CommonMethods.scrollToBottom(driver);
		WebElement lastRow = driver.findElement(By.xpath(
				"//th[contains(text(),'WISHLIST_CNT')]/following-sibling::td[contains(text(),'The number of items in your Wishlist.')]"));
		CommonMethods.highlightElement(driver, lastRow);
		Assert.assertTrue(lastRow.isDisplayed());

		Thread.sleep(5000);
		CommonMethods.navigateToURLbyJS(driver, "http://demo.guru99.com/v4/");
		Thread.sleep(5000);
		String guruURL = (String) CommonMethods.executeForBrowser(driver, "return document.URL");
		Assert.assertEquals(guruURL, "http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_02_removeAttribute() throws Exception {
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement iframeResult = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(iframeResult);

		WebElement lastNameTxt = driver.findElement(By.xpath("//input[@name='lname']"));
		CommonMethods.removeAttribute(driver, lastNameTxt, "disabled");

		WebElement firstNameTxt = driver.findElement(By.xpath("//input[@name='fname']"));
		String firstName = "Automation";
		String lastName = "Testing";
		firstNameTxt.sendKeys(firstName);
		lastNameTxt.sendKeys(lastName);

		WebElement submitBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		CommonMethods.clickForElement(driver, submitBtn);

		Thread.sleep(5000);
		String resultText = (String) CommonMethods.executeForBrowser(driver,
				"return document.documentElement.innerText");
		Assert.assertTrue(resultText.contains(firstName));
		Assert.assertTrue(resultText.contains(lastName));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
