package Listenerss;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentSampleReportWithScreenshot {

	ExtentReports report;

	@BeforeSuite
	public void configBS() {
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

	@AfterSuite
	public void configAS() {
		report.flush();// backup
	}

	@Test
	public void createContactTest() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888");
		
		TakesScreenshot eDriver=(TakesScreenshot)driver;
		String filePath=eDriver.getScreenshotAs(OutputType.BASE64);//ER support BASE64 if you attach the ss u hv to use Base64

		ExtentTest test = report.createTest("createContactTest");// taking help of test we genetate a logs
		//it will insert one  testcase in side the extent report

		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to connect page");
		test.log(Status.INFO, "create contact");
		if ("HDFC".equals("HDFDC")) {
			test.log(Status.PASS, "contact is created");
		} else {
			test.addScreenCaptureFromBase64String(filePath, "ErrorFile");// adding the report and attaching the screenshot here
		}
		driver.close();
	}

//here it will give high level report as well as low level reports
	

}
