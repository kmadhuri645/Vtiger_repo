package com.comcast.crm.orgtestPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.webdriverutility.ExtentUtilityClassObject;
import com.comcast.crm.objectrepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.OranizationPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;

import BaseClassUtility.BaseclassNew;

public class createOrganizationTestExtentReportTest extends BaseclassNew {

	@Test
	public void createOrgTest() throws Throwable {

		ExtentUtilityClassObject.getTest().log(Status.INFO, "read data from Excel");//static
		
		//ListImpClassExtent2.test.log(Status.INFO, "read data from Excel");//no static
		
		// read testScript data from Excel file
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		// Step-2 Navigate to Organization module
		ExtentUtilityClassObject.getTest().log(Status.INFO, "navigate to org Page");
		
		//ListImpClassExtent2.test.log(Status.INFO, "navigate to org Page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// Step-3 Click on "create Organization" Button
		ExtentUtilityClassObject.getTest().log(Status.INFO, "navigate to create Org Page");
		
		//ListImpClassExtent2.test.log(Status.INFO, "navigate to create Org Page");
		OranizationPage cnp = new OranizationPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// Step-4 Enter all the details and create new Organisation
		ExtentUtilityClassObject.getTest().log(Status.INFO, "create a new Org");
		
		//ListImpClassExtent2.test.log(Status.INFO, "navigate to create Org Page");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		
		ExtentUtilityClassObject.getTest().log(Status.INFO,orgName+ " create a new Org ");
		//ListImpClassExtent2.test.log(Status.INFO,orgName+ " create a new Org ");

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
