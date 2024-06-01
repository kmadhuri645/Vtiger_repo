package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws IOException {
		/*Create Object */
		WebDriverUtility wLib=new WebDriverUtility();

		// read common data from properties file
		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
		Properties pro = new Properties();
		pro.load(fis);

		String BROWSER = pro.getProperty("browser");
		String URL = pro.getProperty("url");
		String USERNAME = pro.getProperty("username");
		String PASSWORD = pro.getProperty("password");

		// generate the random number
		Random random = new Random();
		int randomInt = random.nextInt(1000);

		// read testScript data from Excel file

		FileInputStream fis1=new FileInputStream("./testData/testData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("contact");
		Row row = sh.getRow(7);
		String orgName = row.getCell(2).toString() + randomInt;
		String contactLastName = row.getCell(3).getStringCellValue()+randomInt;
		wb.close();

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

		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		wLib.waitForPageToLoad(driver);

		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step-2 Navigate to Organization module
		driver.findElement(By.linkText("Organizations")).click();

		// Step-3 Click on "create Organization" Button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step-4 Enter all the details and create new Organisation
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify Header msg Expected Result

		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "is created ==PASS");
		} else {
			System.out.println(orgName + "is not created ==FAIL");
		}

		// Step-5 navigate to contact module

		driver.findElement(By.linkText("Contacts")).click();

		// Step-6 Click on "create Contact" Button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
         String parentID=driver.getWindowHandle();
         
		// Step-7 Enter all the details and create new lastname
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		//switch to child window
		wLib.switchToTabOnURL(driver, "module=Accounts" );
		
//		Set<String> set = driver.getWindowHandles();
//		Iterator<String> it = set.iterator();
//		while(it.hasNext()) {
//			
//			String windowID=it.next();
//			driver.switchTo().window(windowID);
//			
//			String actURL = driver.getCurrentUrl();
//			if(actURL.contains("module=Accounts")) {
//				break;
//			}
//			
//		}
		
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		
		wLib.switchToTabOnURL(driver, "Contacts&action" );//this is url
		
		//switch to parent window
		
		//driver.switchTo().window(parentID);
		
//		Set<String> set1 = driver.getWindowHandles();
//		Iterator<String> it1 = set1.iterator();
//		while(it.hasNext()) {
//			
//			String windowID=it1.next();
//			driver.switchTo().window(windowID);
//			
//			String actURL1 = driver.getCurrentUrl();
//			if(actURL1.contains("Contacts&action")) {
//				break;
//			}
//			
//		}

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify Header OrgName info Expected Result
		
				 headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerInfo.contains(contactLastName)) {
					System.out.println(contactLastName+"is created ==PASS");
				}else {
					System.out.println(contactLastName+"is not created ==FAIL");
				}
				
				//verify Header orgName info Expected Result
				String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
				System.out.println(actOrgName);
				if(actOrgName.trim().equals(orgName)) {
					System.out.println(orgName+"information is created ==PASS");
				}else {
					System.out.println(orgName+"information is not created ==FAIL");
				}

	}

}
