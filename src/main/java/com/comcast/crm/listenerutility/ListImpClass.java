package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import BaseClassUtility.BaseclassNew;




public class ListImpClass implements ITestListener,ISuiteListener{

	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
	}

	public void onFinish(ISuite suite) {
		System.out.println("Report backUP");
	}

	//before executing every test case on tast start it will display name of the test case into console
	public void onTestStart(ITestResult result) {
		System.out.println("================="+result.getMethod().getMethodName()+"====START=====");//display name of the test case
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("================="+result.getMethod().getMethodName()+"====END======");
	}

	public void onTestFailure(ITestResult result) {
		try {
		String methodname = result.getMethod().getMethodName();//testcase name geeting
		TakesScreenshot ts = (TakesScreenshot) BaseclassNew.sdriver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		//File dst = new File("./screenshot/"+methodname+".png"); //here always pass testcase name bcz whereever we get screnshot so it will always give testcase name
		
		String time=new Date().toString().replace("","_").replace(":","_");
		
		File dst = new File("./screenshot/"+methodname+"+"+time+".png");
				
					FileUtils.copyFile(src, dst);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	
		
		//FileUtils.copyFile(srcFile, new File("./screenshot/test.png"));
	}

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		
	}
	
	

}
