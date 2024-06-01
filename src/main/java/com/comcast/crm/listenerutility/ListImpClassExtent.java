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
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseClassUtility.BaseclassNew;

public class ListImpClassExtent implements ITestListener, ISuiteListener {

	public ExtentSparkReporter spark;
	public static ExtentReports report;//if u want to exceute every test case make a variable as static so taking help of class we can use inside the logs

	public void onStart(ISuite suite) {
		//no need to write this baseclass bcz onStart method also will execute before a suite
		
		System.out.println("Report configuration");

		// Spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
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

	// before executing every test case on tast start it will display name of the
	// test case into console
	public void onTestStart(ITestResult result) {
		System.out.println("=================" + result.getMethod().getMethodName() + "====START=====");// display name
																										// of the test
																										// case
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("=================" + result.getMethod().getMethodName() + "====END======");
	}

	public void onTestFailure(ITestResult result) {
		try {
			String methodname = result.getMethod().getMethodName();// testcase name geeting
			TakesScreenshot ts = (TakesScreenshot) BaseclassNew.sdriver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			// File dst = new File("./screenshot/"+methodname+".png"); //here always pass
			// testcase name bcz whereever we get screnshot so it will always give testcase
			// name

			String time = new Date().toString().replace("", "_").replace(":", "_");

			File dst = new File("./screenshot/" + methodname + "+" + time + ".png");

			FileUtils.copyFile(src, dst);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// FileUtils.copyFile(srcFile, new File("./screenshot/test.png"));
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
