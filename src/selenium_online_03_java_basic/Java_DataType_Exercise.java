package selenium_online_03_java_basic;



public class Java_DataType_Exercise {

	public static void main(String[] args) {
		String text = "Automation Testing Tutorials Online 123456";
		
		char someChar = 'a';
		int countText = 0;
		int countNumber = 0;
		
		for (int i = 0; i < text.length(); i++) {
		    if (text.toLowerCase().charAt(i) == someChar) {
		    	countText+=1;
		    }
		    if (Character.isDigit(text.charAt(i))) {
		    	countNumber+=1;
		    }
		}
		System.out.println("So luong ky tu a: " + countText);
		
		boolean compare = text.contains("Testing");
		System.out.println("Chuoi co chua 'Testing': " + compare);
			
		boolean startText = text.startsWith("Automation");
		System.out.println("Chuoi co bat dau voi 'Automation': " + startText);
		
		boolean endsText = text.endsWith("Online");
		System.out.println("Chuoi co ket thuc voi 'Online': " + endsText);
		
		int indexText = text.indexOf("Tutorials");
		System.out.println("Vi tri cua 'Tutorials': " + indexText);
		
		String newText = text.replace("Online", "Offline");
		System.out.println("Thay the 'Online' bang 'Offline': " + newText);
		
		System.out.println("So luong ky tu co so: " + countNumber);
	}

}
