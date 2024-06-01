package com.comcast.crm.orgtestPOM;

import java.io.FileInputStream;
import java.io.IOException;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class createOrgWithPhoneNumberTest {
	
	public static void main(String[] args) throws IOException {
		//read common data from properties file
		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
		Properties pro = new Properties();
		pro.load(fis);

		String BROWSER = pro.getProperty("browser");
		String URL = pro.getProperty("url");
		String USERNAME = pro.getProperty("username");
		String PASSWORD = pro.getProperty("password");
		
		//generate the random number
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		
		//read testScript data from Excel file
		
		FileInputStream fis1=new FileInputStream("./testData/testData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("org1");
		Row row = sh.getRow(7);
		String orgName = row.getCell(2).toString()+randomInt;
		String phoneNumber=row.getCell(3).getStringCellValue();
		//System.out.println(orgName);
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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		
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
		driver.findElement(By.id("phone")).sendKeys(phoneNumber);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click(); 
		
		//verify Header phone number info Expected Result
		String actPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
		if(actPhoneNumber.contains(phoneNumber)) {
			System.out.println(phoneNumber+"is created ==PASS");
		}else {
			System.out.println(phoneNumber+"is not created ==FAIL");
		}
		
		//verify Header orgName info Expected Result
		String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actOrgName.contains(orgName)) {
			System.out.println(orgName+"information is created ==PASS");
		}else {
			System.out.println(orgName+"information is not created ==FAIL");
		}
		

		//Step-5 Logout 
		driver.quit();


	}
	
	

}