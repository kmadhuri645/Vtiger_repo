package com.comcast.crm.contacttestPOM;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTest {

	public static void main(String[] args) throws Throwable {
		
		/* create object*/
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		
		//read common data from properties file1
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		
		//generate the random number
		//Random random = new Random();
		//int randomInt = random.nextInt(1000);
		
		//read testScript data from Excel file
		String lastName = eLib.getDataFromExcel("contact", 1, 2)+jLib.getRandomNumber();
		
		
		//System.out.println(lastName);
		

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
        Thread.sleep(30000);
		// Step-4 Enter all the details and create new lastname
		
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click(); 
		
		//verify LastName  Expected Result
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		
		if(actLastName.equals(lastName)) {
			System.out.println(lastName+"information is verified  ==PASS");
		}else {
			System.out.println(lastName+"information is not created ==FAIL");
		}

		//Step-5 Logout 
		driver.close();

	}

}
