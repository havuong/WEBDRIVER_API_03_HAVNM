package selenium_online_03_testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG_DataProvider {
  public WebDriver driver;
	@Test
	public void BeforeClass(){
		driver = new FirefoxDriver();
	}
	
	@DataProvider(name = "User/Pass")
	public static Object[][] userAndPassword(){
		return new Object[][]{{"mngr131812","vavAtap"},{"mngr131814","meduhAv"},{"mngr131815","UvEnEgY"}};
	}
	
	@Test(dataProvider = "User/Pass")
	public void TestNG_Reporters_Demo(String sUsername, String sPassword){
		driver.navigate().to("http://demo.guru99.com/v4");
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(sUsername);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(sPassword);
		driver.findElement(By.name("btnLogin")).click();
	}
}
