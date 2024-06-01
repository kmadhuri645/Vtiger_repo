package practice.testng;

import org.testng.annotations.Test;

public class invocationcountt {
	
	//invocation count
		@Test(invocationCount = 5)
		public void createOrderTest() {
			System.out.println("Execute createOrderTest===>123");
		}
		@Test(enabled = false)
		public void billingAndOrderTes2t() {
			System.out.println("Execute billingAndOrderTest==>123");
		}

}
