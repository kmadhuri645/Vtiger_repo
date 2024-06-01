package Listenerss;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



import BaseClassUtility.BaseclassNew;


public class InvoiceTest2 extends BaseclassNew{
	
	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImp.class)
	public void createInvoiceTest() {
		System.out.println("execute createInvoiceTest");
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
	
}
