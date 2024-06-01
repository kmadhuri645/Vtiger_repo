package practice.testng;

import org.testng.annotations.Test;

public class orderTest {
	
	@Test
	public void createOrderTest() {
		System.out.println("Execute createOrderTest===>123");
	}
	
	@Test(dependsOnMethods="createOrderTest")
	public void billingAndOrderTest() {
		System.out.println("Execute billingAndOrderTest==>123");
		System.out.println("==============================================================================");
	}
	//if we do not have database connection that time also go for depandence
	@Test
	public void createOrderTest1() {
		System.out.println("Execute createOrderTest===>123");//fail
		String str=null;
		//System.out.println(str.equals("123"));
	}
	@Test(dependsOnMethods="createOrderTest1")
	public void billingAndOrderTest1() {
		System.out.println("Execute billingAndOrderTest==>123");//skip
	}
	
}
