package selenium_api.exercises;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import selenium_api.helpers.CommonMethods;

public class Topic_07_Iframe_WindowPopup {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_Iframe() {
		driver.get("http://www.hdfcbank.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// verify lookingforText
		WebElement lookingIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(lookingIframe);

		WebElement lookingText = driver.findElement(By.xpath("//*[@id='messageText']"));
		Assert.assertEquals(lookingText.getText(), "What are you looking for?");

		// verify images number
		driver.switchTo().defaultContent();
		WebElement imageIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(imageIframe);

		List<WebElement> images = driver.findElements(By.xpath("//img[@class='bannerimage']"));
		Assert.assertEquals(images.size(), 6);

		for (WebElement image : images) {
			image.isDisplayed();
		}

		// verify 8 items
		driver.switchTo().defaultContent();

		WebElement flipBanner = driver.findElement(By.xpath("//div[@class='flipBanner']"));
		Assert.assertTrue(flipBanner.isDisplayed());

		List<WebElement> items = driver.findElements(By.xpath("//img[@class='front icon']"));
		Assert.assertEquals(items.size(), 8);
	}

	@Test
	public void TC_02_Window() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		String parentID = driver.getWindowHandle();
		WebElement clickhere = driver.findElement(By.xpath("//a[contains(text(),'Click Here')]"));
		clickhere.click();

		CommonMethods.switchToChildWindow(driver, parentID);

		String googleTitle = driver.getTitle();
		Assert.assertEquals(googleTitle, "Google");

		driver.close();
		driver.switchTo().window(parentID);
		System.out.println(driver.getCurrentUrl());

	}

	@Test
	public void TC_03_closeWindows() {
		driver.get("http://www.hdfcbank.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		/*
		 * WebElement bannerImage =
		 * driver.findElement(By.xpath("//*[@id='imageDiv']")); WebElement
		 * bannerClose = driver.findElement(By.xpath("//*[@id='div-close']"));
		 * if(bannerImage.isDisplayed()){ bannerClose.click(); }
		 */

		WebElement agriLink = driver.findElement(By.xpath("//a[contains(text(),'Agri')]"));
		agriLink.click();

		CommonMethods.switchToWindowByTitle(driver, "HDFC Bank Kisan Dhan Vikas e-Kendra");
		WebElement accountDetails = driver.findElement(By.xpath("//p[contains(text(),'Account Details')]"));
		accountDetails.click();

		CommonMethods.switchToWindowByTitle(driver, "Welcome to HDFC Bank NetBanking");
		driver.switchTo().frame("footer");
		WebElement policyLink = driver.findElement(By.xpath("//a[contains(text(),'Privacy Policy')]"));
		policyLink.click();

		CommonMethods.switchToWindowByTitle(driver,
				"HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		WebElement CSRLink = driver.findElement(By.xpath("//a[contains(text(),'CSR')]"));
		CSRLink.click();

		driver.switchTo().defaultContent();
		String parentID = driver.getWindowHandle();
		CommonMethods.closeAllWithoutParentwindows(driver, parentID);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
