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
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OranizationPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteOrgTest {

	public static void main(String[] args) throws Throwable {
		/* Create Object */
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();

		// read common data from properties file
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		// generate the random number
		//Random random = new Random();
		//int randomInt = random.nextInt(1000);

		// read testScript data from Excel file
		String orgName = eLib.getDataFromExcel("org1", 10, 2) + jLib.getRandomNumber();

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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));

		//driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		//lp.loginToapp(USERNAME, PASSWORD,URL);
		lp.loginToapp(USERNAME, PASSWORD);

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
		
		//go back to Organisation Page
		
		hp.getOrgLink().click();
		
		//search for Organization
		cnp.getSerchEdt().sendKeys(orgName);
		
		//select oradropdown organisation no 
		wLib.select(cnp.getSearchDropDown(), "Organization Name");
		
		//Click Search Now Button
		cnp.getSearchBtn().click();
		
		// first search the name Go to Action and select del option
		//driver.findElement(By.xpath("//a[text()='Manisha_2993']/../../td[8]/a[text()='del']")).click();//manisha is static data thats why we concate with excel data
		//bcz it will gererate the always dynamic xpath 
		
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();

		// Step-5 logout
		//hp.logout();

		//driver.close();

	}

}
