package selenium_testng;

import org.testng.annotations.Test;

public class TestNG_02_GroupTestcase {
  @Test(groups="customer", priority = 2)
  public void TC_01() {
	  System.out.println("Testcase 01");
  }
  @Test(groups="payment")
  public void TC_02() {
	  System.out.println("Testcase 02");
  }
  @Test(groups="manager")
  public void TC_03() {
	  System.out.println("Testcase 03");
  }
  @Test(groups="payment")
  public void TC_04() {
	  System.out.println("Testcase 04");
  }
  @Test(groups="customer", priority = 1)
  public void TC_05() {
	  System.out.println("Testcase 05");
  }
}
