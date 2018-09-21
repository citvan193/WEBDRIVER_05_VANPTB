package selenium_testng;

import org.testng.annotations.Test;

public class TestNG_03_Priority_Skip {
  @Test(priority = 4)
  public void TC_01() {
	  System.out.println("Testcase 01");
  }
  @Test(priority = 3)
  public void TC_02() {
	  System.out.println("Testcase 02");
  }
  @Test(priority = 5)
  public void TC_03() {
	  System.out.println("Testcase 03");
  }
  @Test(priority = 2)
  public void TC_04() {
	  System.out.println("Testcase 04");
  }
  @Test(priority = 1)
  public void TC_05() {
	  System.out.println("Testcase 05");
  }
}
