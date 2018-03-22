package selenium_api.helpers;

import java.util.Random;

public class CommonMethods {
	
	public static int randomData() {
	     Random rand = new Random();
	     int numberRand = rand.nextInt(1000);
	     System.out.println(numberRand);
	     return numberRand;
	     
	}

}
