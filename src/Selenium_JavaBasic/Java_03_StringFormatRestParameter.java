package Selenium_JavaBasic;

public class Java_03_StringFormatRestParameter {

	public static void main(String[] args) {
		String locator_00 = "//a[text()='New Customer']";
		String locator_01 = "//a[text()='%s']";
		String locator_02 = "//a[text()='%s']//button[text()='%s']";
		String locator_03 = "//a[text()='%s']//button[text()='%s']//a[text()='%s']";
		String locator_04 = "//a[text()='%s']//button[text()='%s']//a[text()='%s']//button[text()='%s']";
		
		clickToElement(locator_00);
		clickToElement(locator_01, "New Customer");
		clickToElement(locator_02, "New Customer", "Save");
		clickToElement(locator_03, "New Customer", "Save","Cancel");
		clickToElement(locator_04, "New Customer", "Save","Cancel", "Delete");
	}
	public static void clickToElement(String locator) {
		System.out.println(locator);
	}
	public static void clickToElement(String locator, String value) {
		locator = String.format(locator, value);
		System.out.println(locator);
	}
	public static void clickToElement(String locator, String value01, String value02) {
		locator = String.format(locator, value01, value02);
		System.out.println(locator);
	}
	//Rest parameter (String java langure)
	public static void clickToElement(String locator, String...values) {
		locator = String.format(locator, (Object[]) values);
		System.out.println(locator);
	}
	

}
