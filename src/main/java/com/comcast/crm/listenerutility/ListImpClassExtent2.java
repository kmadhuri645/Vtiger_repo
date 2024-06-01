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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.ExtentUtilityClassObject;

import BaseClassUtility.BaseClassExtent;
import BaseClassUtility.BaseclassNew;

public class ListImpClassExtent2 implements ITestListener, ISuiteListener {

	public static ExtentTest test;
	public ExtentReports report;// if u want to exceute every test case make a variable as static so taking help
								// of class we can use inside the logs

	public void onStart(ISuite suite) {

		// no need to write this baseclass bcz onStart method also will execute before a suite
		
		System.out.println("Report configuration");
		String time = new Date().toString().replace("", "_").replace(":", "_");
		// Spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+"html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add Env information and create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}

	public void onFinish(ISuite suite) {
		System.out.println("Report backUP");
		report.flush();
	}

	// before executing every test case ontaststart it will display name of the
	// test case into console
	// to create a test case inside the extent report
	public void onTestStart(ITestResult result) {
		System.out.println("=================" + result.getMethod().getMethodName() + "====START=====");// display name
																										// of the test
																										// case
		// ExtentTest test = report.createTest("createContactTest");
		//ExtentUtilityClassObject.setTest(test);
		
		test = report.createTest(result.getMethod().getMethodName());//here test is created 
		ExtentUtilityClassObject.setTest(test); //here we are set the result
		test.log(Status.INFO, result.getMethod().getMethodName() + "===>STARTED<========");

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("=================" + result.getMethod().getMethodName() + "====END======");
		test.log(Status.PASS, result.getMethod().getMethodName() + "===>COMPLETED<========");
	}

	public void onTestFailure(ITestResult result) {

		String testName = result.getMethod().getMethodName();// testcase name geeting
		TakesScreenshot ts = (TakesScreenshot) BaseclassNew.sdriver;
		String filePath = ts.getScreenshotAs(OutputType.BASE64);

		String time = new Date().toString().replace("", "_").replace(":", "_");
		
		test.addScreenCaptureFromBase64String(filePath, testName + "_" + time);
		test.log(Status.FAIL, result.getMethod().getMethodName() + "===>FAILED<========");

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
