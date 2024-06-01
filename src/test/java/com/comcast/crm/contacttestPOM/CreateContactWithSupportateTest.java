package com.comcast.crm.contacttestPOM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

import com.comcast.crm.generic.webdriverutility.JavaUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithSupportateTest {

	public static void main(String[] args) throws IOException, Throwable {
		/*create object */
		
		 JavaUtility jLib = new JavaUtility();
		 
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
		Sheet sh = wb.getSheet("contact");
		Row row = sh.getRow(4);
		String lastName = row.getCell(2).toString()+randomInt;
		//System.out.println(lastName);
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

		// Step-2 Navigate to Contact module
		driver.findElement(By.linkText("Contacts")).click();

		// Step-3 Click on "create Contact" Button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step-4 Enter all the details and create new lastname
		
		//driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequriedDateYYYYDDMM(30);
	
	    driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		//set the date 
		driver.findElement(By.xpath("//input[@name='support_start_date']")).clear();
		driver.findElement(By.xpath("//input[@name='support_start_date']")).sendKeys(startDate);
		
		driver.findElement(By.xpath("//input[@name='support_end_date']")).clear();
		driver.findElement(By.xpath("//input[@name='support_end_date']")).sendKeys(endDate);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click(); 
		
		
		
		//verify StartDate  and  EndDate
		
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		
		if(actStartDate.equals(startDate)) {
			System.out.println(startDate+"information is verified  ==PASS");
		}else {
			System.out.println(startDate+"information is not created ==FAIL");
		}
		
      String actendDate = driver.findElement(By.id("account_name")).getText();
		
		if(actendDate.equals(endDate)) {
			System.out.println(endDate+"information is verified  ==PASS");
		}else {
			System.out.println(endDate+"information is not created ==FAIL");
		}

		//Step-5 Logout 
		driver.close();

	}

}
