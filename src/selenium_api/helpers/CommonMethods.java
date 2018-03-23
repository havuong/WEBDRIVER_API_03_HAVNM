package selenium_api.helpers;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class CommonMethods {
	static WebElement driver;

	public static int randomData() {
		Random rand = new Random();
		int numberRand = rand.nextInt(1000);
		System.out.println(numberRand);
		return numberRand;

	}

	public static void checkElementEnabled(WebElement xpathElement) {
		//WebElement element = driver.findElement(By.xpath(xpathElement));
		if(xpathElement.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is disabled");
		}
	}	

}
