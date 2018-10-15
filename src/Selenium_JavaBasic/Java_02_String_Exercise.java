package Selenium_JavaBasic;

public class Java_02_String_Exercise {
	public static void main(String[] args) {
		String chuoitext = "Automation Testing Tutorials Online 123456";
		int digitCount = 0;
		int count = 0;
		// Cau 1
		for (int i = 0; i < chuoitext.length(); i++) {
			if (chuoitext.toLowerCase().charAt(i) == 'a') {
				count++;
			}
		}

		System.out.println("Câu 1: Số lượng ký tự a trong chuỗi = " + count);

		// Cau 2
		boolean checkText = chuoitext.contains("Testing");
		System.out.println("Câu 2: " + checkText);

		// Cau 3:
		boolean checkStartText = chuoitext.startsWith("Automation");
		System.out.println("Câu 3: " + checkStartText);

		// Cau 4:
		boolean checkEndText = chuoitext.endsWith("Online");
		System.out.println("Câu 4: " + checkEndText);

		// Cau 5:
		int index = chuoitext.indexOf("Tutorials");
		System.out.println("Câu 5: Vị trí chuỗi Tutorials = " + index);

		// Cau 6:
		String chuoiReplace = chuoitext.replace("Online", "Offline");
		System.out.println("Câu 6: Chuỗi sau khi replay = " + chuoiReplace);

		// Cau 7:
		for (int i = 0; i < chuoitext.length(); i++) {
			if (Character.isDigit(chuoitext.charAt(i)))
				digitCount++;
		}
		System.out.println("Câu 7: Số lượng ký tự số trong chuỗi = " + digitCount);
	}
}
