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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OranizationPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.crm.generic.basetest.BaseClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class createOrganizationTestTestNgBase extends BaseClass {

	@Test
	public void createTest() throws Throwable {

		// read testScript data from Excel file
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

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
		String actOrgName = oip.getHeaderMsg().getText();// inside the varible we are capture the data from the
															// element([ ACC30 ] abbbbeee - Organization Information)
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + "name is verified==PASS");
		} else {
			System.out.println(orgName + "name is not verified==FAIL");
		}
	}

	@Test
	public void createOrganizationWithIndustriesTest() throws Throwable {

		// read testScript data from Excel file
		String orgName = eLib.getDataFromExcel("org1", 4, 2) + jLib.getRandomNumber();

		String industries = eLib.getDataFromExcel("org1", 4, 3);
		String type = eLib.getDataFromExcel("org1", 4, 4);

		// Step-2 Navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// Step-3 Click on "create Organization" Button
		OranizationPage op = new OranizationPage(driver);
		op.getCreateNewOrgBtn().click();
		
		// Step-4 Enter all the details and create new Organisation
		CreatingNewOrganizationPage cp = new CreatingNewOrganizationPage(driver);
		cp.getOrgNameEdt().sendKeys(orgName);

		// select the dropdown
		CreatingNewOrganizationPage cp1 = new CreatingNewOrganizationPage(driver);
		WebElement webSel1 = cp1.getIndustryBD();
		wLib.select(webSel1, industries);
		WebElement webSel2 = cp1.getTypeBD();
		wLib.select(webSel2, type);

		CreatingNewContactPage sb = new CreatingNewContactPage(driver);
		sb.getSaveBtn().click();

		// verify the industries and type info
		String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actIndustries.equals(industries)) {
			System.out.println(industries + " information is verified ==PASS");
		} else {
			System.out.println(industries + " information is not verified  ==FAIL");
		}

		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actType.equals(type)) {
			System.out.println(type + " information is verified ==PASS");
		} else {
			System.out.println(type + " information is not verified  ==FAIL");
		}
	}

	@Test
	public void createOrgWithPhoneNumberTest() throws Throwable {
		
		// read testScript data from Excel file
		String orgName = eLib.getDataFromExcel("org1", 7, 2) + jLib.getRandomNumber();
		String phoneNumber=eLib.getDataFromExcel("org1", 7, 3);
		
		// Step-2 Navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
		// Step-3 Click on "create Organization" Button
		OranizationPage op = new OranizationPage(driver);
		op.getCreateNewOrgBtn().click();
		
		// Step-4 Enter all the details and create new Organisation
		CreatingNewOrganizationPage cnp=new CreatingNewOrganizationPage(driver);
		cnp.getOrgNameEdt().sendKeys(orgName);
	
		cnp.getPhoneEdit().sendKeys(phoneNumber);
		
		CreatingNewContactPage sb = new CreatingNewContactPage(driver);
		sb.getSaveBtn().click();
	
		// verify Header phone number info Expected Result
		String actPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actPhoneNumber.contains(phoneNumber)) {
			System.out.println(phoneNumber + "is created ==PASS");
		} else {
			System.out.println(phoneNumber + "is not created ==FAIL");
		}

		// verify Header orgName info Expected Result
		String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + "information is created ==PASS");
		} else {
			System.out.println(orgName + "information is not created ==FAIL");
		}

	}

}
