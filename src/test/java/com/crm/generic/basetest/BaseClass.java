package com.crm.generic.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	/* create object*/
	public FileUtility fLib=new FileUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public JavaUtility jLib=new JavaUtility();
	public DataBaseUtility dbLib = new DataBaseUtility();
	public WebDriverUtility wLib=new WebDriverUtility();

	public WebDriver driver = null;
	
	public WebDriver sdriver = null;
	
	@BeforeSuite
	public void configBS() throws Throwable {
		System.out.println("====connect to DB, Report Config=====");
		dbLib.getDbConnection();
	}
//	@Parameters("BROWSER")
//	@BeforeClass
//	public void configBC(String browser) throws Throwable {
//	String BROWSER =browser;
	
	@BeforeClass
	public void configBC() throws Throwable {
		System.out.println("==Launch The BROWSER====");
	    String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL=fLib.getDataFromPropertiesFile("url");

		if (BROWSER.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}

		else {
			driver = new ChromeDriver();
		}
		sdriver=driver;
		wLib.waitForPageToLoad(driver);
		driver.get(URL);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void configBM() throws Throwable {
		System.out.println("===Login To Application====");
		
		String URL=fLib.getDataFromPropertiesFile("url");
		String USERNAME=fLib.getDataFromPropertiesFile("username");
		String PASSWORD=fLib.getDataFromPropertiesFile("password");
		
		LoginPage lp=new LoginPage(driver);
		//lp.loginToapp(URL, USERNAME, PASSWORD);
		lp.loginToapp(USERNAME, PASSWORD);
	}

	@AfterMethod
	public void configAM() {
		System.out.println("======Logout The Application=====");
		HomePage hp= new HomePage(driver);
		//hp.logout();
		hp.logout(driver);
	}

	@AfterClass
	public void configAC() {
		System.out.println("====CLOSE THE Browser========");
		driver.quit();
		
	}

	@AfterSuite
	public void configAS() throws Throwable {
		System.out.println("=====close DB AND Report backUp==== ");
		dbLib.closeDbConnection();
	}

}
