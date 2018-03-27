package selenium_api.exercises;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import selenium_api.helpers.CommonMethods;

public class Topic_04_Textbox_TextArea_Dropdown {
	private WebDriver driver;
	private String email, customerid, name, birthday, address, city, state, pin, phone, password;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		email = "havuong" + CommonMethods.randomData() + "@yopmail.com";
		name = "ha vuong";
		birthday = "11/11/1990";
		address = "123 Q3";
		city = "HCM";
		state = "SG";
		pin = "123456";
		phone = "123456789";
		password = "123456";
	}

	@Test(enabled = false)
	public void handleDropdownList() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Select job1dropdown = new Select(driver.findElement(By.xpath("//*[@id='job1']")));
		Assert.assertFalse(job1dropdown.isMultiple());

		job1dropdown.selectByVisibleText("Automation Tester");
		Assert.assertEquals(job1dropdown.getFirstSelectedOption().getText(), "Automation Tester");

		job1dropdown.selectByValue("manual");
		Assert.assertEquals(job1dropdown.getFirstSelectedOption().getText(), "Manual Tester");

		job1dropdown.selectByIndex(3);
		Assert.assertEquals(job1dropdown.getFirstSelectedOption().getText(), "Mobile Tester");

		Assert.assertEquals(5, job1dropdown.getOptions().size());
	}

	@Test
	public void handleTextboxAndTextArea() throws Exception {
		driver.get("http://demo.guru99.com/v4");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr124581");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("nAgYqUh");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();

		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@id='dob']")).sendKeys(birthday);
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(address);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(phone);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='sub']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),
				"Customer Registered Successfully!!!");

		customerid = driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]/following-sibling::td"))
				.getText();

		driver.findElement(By.xpath("//a[contains(text(),'Edit Customer')]")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerid);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(), address);

		driver.findElement(By.xpath("//textarea[@name='addr']")).clear();
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("890 ABC");
		driver.findElement(By.xpath("//input[@name='city']")).clear();
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys("HN");
		driver.findElement(By.xpath("//input[@name='sub']")).click();

		Assert.assertEquals(
				driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText(),
				"890 ABC");
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText(), "HN");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
