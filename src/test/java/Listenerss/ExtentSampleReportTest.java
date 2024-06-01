package Listenerss;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentSampleReportTest {
	
	@Test
	public void createContactTest() {
		//Spark report config
		ExtentSparkReporter spark =new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//add Env information and create test
		ExtentReports report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
		
		//whenever we create a test case then createtest give the object of the extentTest
        //bcz ExtentTest class is mandatory to generate a log
		
		ExtentTest test = report.createTest("createContactTest");//taking help of test we genetate a logs
		
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to connect page");
		test.log(Status.INFO, "create contact");
		if("HDFC".equals("HDFC")) {
			test.log(Status.PASS, "contact is created");
		}
		else {
			test.log(Status.FAIL, "contact is not created");
		}
		report.flush();//backup 
		System.out.println("login to app");
	}

}
