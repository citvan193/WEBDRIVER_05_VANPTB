package Selenium_JavaBasic;

public class Java_01_String {
	public static void main(String [] args) {
		String chuoi1 = "Automation Testing";
		
		//Lay do dai chuoi
		int length = chuoi1.length();
		System.out.println("Độ dài của chuỗi = " + length);
		
		//Ky tu tu vi tri thu...
		String chuoi2 = "Automation Testing Tutorials";
		char kytu = chuoi2.charAt(5);
		System.out.println("Ký tự ở vị trí thứ 5 = " + kytu);
		
		//Noi chuoi concate 1
		String chuoi3 = "Automation Testing";
		String chuoi4 = "Tutorials";
		String testing = chuoi3.concat(chuoi4);
		System.out.println("Chuỗi sau khi nối = " + testing);
		
		//Noi chuoi concate 2
		String testing2 = chuoi1.concat("Tutorials");
		System.out.println("Chuỗi sau khi nối = " + testing2);
		
		//So sanh chuoi tuyet doi
		boolean compareValue = testing2.equals(chuoi1);
		System.out.println("So sánh chuỗi = " + compareValue);
		compareValue = chuoi1.equals(chuoi1);
		System.out.println("So sánh chuỗi tuyệt đối = " + compareValue);
		//So sanh chuoi tuong doi
		compareValue = testing.contains(chuoi1);
		System.out.println("So sánh chuỗi tương đối = " + compareValue);
		
		//Vị trí của từ trong chuỗi
		int index1 = chuoi2.indexOf("Testing");
		int index2 = chuoi2.indexOf("Tutorials");
		System.out.println("Vị trí chuỗi 1 = " + index1);
		System.out.println("Vị trí chuỗi 2 = " + index2);
		
		//Chuỗi con
		String testingOnline = "Automation Testing Online Advance 05";
		int index_01 = chuoi2.indexOf("Testing");
		String subStringStart = testingOnline.substring(index_01);
		System.out.println("Chuoi con từ vị trí Testing = " + subStringStart);
		String subStringStartToEnd = testingOnline.substring(11, 18);
		System.out.println("Chuoi con từ vị trí Testing = " + subStringStartToEnd);
		subStringStartToEnd = testingOnline.substring(19, 25);
		System.out.println("Chuoi con từ vị trí Testing = " + subStringStartToEnd);
		
		//Tách chuỗi Viewing 36 of 12591 resuilt
		String text = "  Viewing 36 of 12591 resuilt   ";
		text = text.trim();
		String[] subString = text.split(" ");
		for (int i=0; i<subString.length; i++) {
			System.out.println("Vị trí thứ " + i + " = " + subString[i]);
		}
		System.out.println(extractNumberFromString(text, 1));
		System.out.println(extractNumberFromString(text, 3));
		
		String price ="$500.00";
		price = price.replace("$", "");
		price = price.replace(".00", "");
		System.out.println("Price sau khi replace = " + price);
		
		//In hoa
		String upper = testing.toUpperCase();
		System.out.println("Chuỗi in hoa = " + upper);
		
		//In thường
		String lower = testing.toLowerCase();
		System.out.println("Chuỗi in hoa = " + lower);
		
		//Trim String
		String trimString = "  			Viewing 36 of 12591 resuilt   ";
		System.out.println("Chuỗi trước khi trim = " + trimString);
		trimString = trimString.trim();
		System.out.println("Chuỗi sau khi trim = "  + trimString);
		
		String giaTien ="$500.00";
		giaTien = giaTien.replace("$", "");
		giaTien = giaTien.replace(".00", "");
		int tien = Integer.parseInt(giaTien);
		double tiendouble = Double.parseDouble(giaTien);
		float tienFloat = Float.parseFloat(giaTien);
		System.out.println("Gia tien sau khi chuyen thanh kieu int = " + tien);
		System.out.println("Gia tien sau khi chuyen thanh kieu double = " + tiendouble);
		System.out.println("Gia tien sau khi chuyen thanh kieu float = " + tienFloat);
	}
	
	public static int extractNumberFromString (String text, int number) {
		String[] subString = text.split(" ");
		int results = Integer.parseInt(subString[number]);
		return results;
	}

}
