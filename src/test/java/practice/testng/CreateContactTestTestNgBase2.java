package practice.testng;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryUtility.ContactPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.OranizationPage;
import com.comcast.crm.objectrepositoryUtility.SearchPage;
import com.crm.generic.basetest.BaseClass;
import com.crm.generic.basetest.BaseClass2;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTestTestNgBase2 extends BaseClass2 {

	@Test(groups="smokeTest")
	public void createContactTest() throws Throwable {

		// read testScript data from Excel file
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// Step-2 Navigate to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// Step-3 Click on "create Contact" Button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// Step-4 Enter all the details and create new lastname
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.getCreateContact().sendKeys(lastName);

		// click on save button
		CreatingNewContactPage sb = new CreatingNewContactPage(driver);
		sb.getSaveBtn().click();

		// verify LastName Expected Result
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();

		if (actLastName.equals(lastName)) {
			System.out.println(lastName + "information is verified  ==PASS");
		} else {
			System.out.println(lastName + "information is not created ==FAIL");
		}
	}

	@Test(groups="regressionTest")
	public void CreateContactWithOrgTest() throws Throwable {

		// read testScript data from Excel file
		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
		String contactLastName = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNumber();

		// Step-2 Navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// Step-3 click on create organisation page
		OranizationPage cnp = new OranizationPage(driver);
		cnp.getCreateNewOrgBtn().click();

         
		// Step-4 Enter all the details and create new Organisation
		CreatingNewOrganizationPage cnp1=new CreatingNewOrganizationPage(driver);
		cnp1.getOrgNameEdt().sendKeys(orgName);
	
		// click on save button
		CreatingNewContactPage sb = new CreatingNewContactPage(driver);
		sb.getSaveBtn().click();

		// verify Header msg Expected Result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "is created ==PASS");
		} else {
			System.out.println(orgName + "is not created ==FAIL");
		}

		// Step-5 navigate to contact module
		hp.getContactLink().click();

		// Step-6 Click on "create Contact" Button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// Step-7 Enter all the details and create new lastname
		CreatingNewOrganizationPage cnp2=new CreatingNewOrganizationPage(driver);
		cnp2.getLastNameBtn().sendKeys(contactLastName);
		cnp2.getNewBtnImg().click();
		
		Thread.sleep(5000);

		// switch the windows
		wLib.switchToTabOnURL(driver, "module=Accounts");

		SearchPage sp=new SearchPage(driver);
		sp.getSearchText().sendKeys(orgName);
		sp.getSearchClick().click();
		
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		wLib.switchToTabOnURL(driver, "Contacts&action");
		sb.getSaveBtn().click();

		// verify Header orgName info Expected Result
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actOrgName);
		if (actOrgName.trim().equals(orgName)) {
			System.out.println(orgName + "information is created ==PASS");
		} else {
			System.out.println(orgName + "information is not created ==FAIL");
		}
	}

	@Test(groups="regressionTest")
	public void ceateContactWithsupportDateTest() throws Throwable {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// read testScript data from Excel file
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		// Step-2 Navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// Step-3 Click on "create Contact" Button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// Step-4 Enter all the details and create new lastname

		String startdate = jLib.getSystemDateYYYYDDMM();
		String enddate = jLib.getRequriedDateYYYYDDMM(20);
		
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContactWithSupportDate(lastName, startdate, enddate);
	
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		// verify StartDate and EndDate

		WebElement actStartDate = driver.findElement(By.xpath("//td[@id='mouseArea_Support Start Date']//span[@id='dtlview_Support Start Date']"));
		String ActualDate=actStartDate.getText();

		if (ActualDate.equals(startdate)) {
			System.out.println(ActualDate + "information is verified  ==PASS");
		} else {
			System.out.println(startdate + "information is not created ==FAIL");
		}

		String actendDate = driver.findElement(By.xpath("//td[@id='mouseArea_Support End Date']//span[@id='dtlview_Support End Date']")).getText();

		if (ActualDate.equals(enddate)) {
			System.out.println(enddate + "information is verified  ==PASS");
		} else {
			System.out.println(enddate + "information is not created ==FAIL");
		}

	}

}
