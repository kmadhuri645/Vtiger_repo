package com.comcast.crm.orgtestPOM;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OranizationPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class createOrganizationTest {

	public static void main(String[] args) throws Throwable {
		/* Create Object */
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();

		// read common data from properties file
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		// generate the random number
		Random random = new Random();
		int randomInt = random.nextInt(1000);

		// read testScript data from Excel file
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {

			driver = new FirefoxDriver();

			
			
		} else if (BROWSER.equals("edge")) {

			driver = new EdgeDriver();
		}

		// step:1 Login to application

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

		//driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		//lp.loginToapp(USERNAME, PASSWORD, URL);
		lp.loginToapp(USERNAME, PASSWORD);

		// LoginPage lp = PageFactory.initElements(driver, LoginPage.class);//pageF
		// intialise the element at rum time
		// lp.getUsernameEdt().sendKeys("admin");
		// lp.getPasswordEdt().sendKeys("admin");
		// lp.getLoginBtn().click();

		// Step-2 Navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// Step-3 Click on "create Organization" Button
		
		OranizationPage cnp = new OranizationPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// Step-4 Enter all the details and create new Organisation
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		// verify Header msg Expected Result
		
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();//inside the varible we  are capture the data from the element([ ACC30 ] abbbbeee - Organization Information)
		if(actOrgName.contains(orgName)) {
			System.out.println(orgName+"name is verified==PASS");
		}else {
			System.out.println(orgName+"name is not verified==FAIL");
		}
		
		// Step-5 logout
		//hp.logout();
		hp.logout(driver);

		driver.close();

	}

}
