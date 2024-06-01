package com.comcast.crm.generic.webdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class ExtentUtilityClassObject {
	
	//share the this testobject into multiple thread
	public static ThreadLocal<ExtentTest> test=new ThreadLocal<ExtentTest>();//ThreadLocal is class it will create multiple copy to static
	public static ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();
	
	//provide getter and setter to access 
	public static ExtentTest getTest() { //when we achive parallel execution "test" method will share the object "getTest()" eventhough it is static it will share the object by taking help of ThreadLocal
		return test.get();// this method will give the extent report object for the multiple threads
	}
	public static void setTest(ExtentTest actTest) {
		test.set(actTest);
	}
	public static WebDriver getDriver() {
		return driver.get();
	}
	public static void setDriver(WebDriver actDriver) {
		driver.set(actDriver);
	}
	
	
//this class helps us to share my satic variable for multiple threads in case of parallel execution	
	
	//here we have wrriten class utility for share the satic variable now use this 

}
