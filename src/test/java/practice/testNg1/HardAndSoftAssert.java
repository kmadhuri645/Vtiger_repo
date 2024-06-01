package practice.testNg1;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HardAndSoftAssert {
	
	@Test
     public void HomePageTest(Method mtd) {
		//Hard Assert
		System.out.println(mtd.getName()+"Test Start");
		System.out.println("step-1");
		System.out.println("step-2");
	    Assert.assertEquals("HOME", "Home-Page");
		System.out.println("step-3");
		 Assert.assertEquals("HOME-CRM", "Home-CRM");
		System.out.println("step-4");
		System.out.println(mtd.getName()+"Test End");
	}
	
	@Test
    public void verifyLogoHomePageTest(Method mtd) {
		//Hard Assert
		System.out.println(mtd.getName()+"Test Start");
		System.out.println("step-1");
		System.out.println("step-2");
	    Assert.assertTrue(true);
		System.out.println("step-3");
		System.out.println("step-4");
		System.out.println(mtd.getName()+"Test End");
	}
	
	@Test
    public void HomePageTest1(Method mtd) {
		//Hard Assert As well as SoftAssert
		
		System.out.println(mtd.getName()+"Test Start");
	
		SoftAssert assertObj=new SoftAssert();
		
		System.out.println("step-1");
		System.out.println("step-2");
	    Assert.assertEquals("HOME", "HOME");//impotant
		System.out.println("step-3");
		
		assertObj.assertEquals("Title", "Title=1");//minor
		System.out.println("step-4");
		assertObj.assertAll();
		System.out.println(mtd.getName()+"Test End");
	}
	
	@Test
   public void verifyLogoHomePageTest1(Method mtd) {
		
		System.out.println(mtd.getName()+"Test Start");
		SoftAssert assertObj=new SoftAssert();
		System.out.println("step-1");
		System.out.println("step-2");
	    Assert.assertTrue(true);
		System.out.println("step-3");
		System.out.println("step-4");
		assertObj.assertAll();
		System.out.println(mtd.getName()+"Test End");
	}

 
  

}