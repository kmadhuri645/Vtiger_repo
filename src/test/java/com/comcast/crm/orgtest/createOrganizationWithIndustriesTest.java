package com.comcast.crm.orgtest;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class createOrganizationWithIndustriesTest {

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
				Row row = sh.getRow(4);
				String orgName = row.getCell(2).toString()+randomInt;
				String industries = row.getCell(3).toString();//here random is not required bcz its a dropdown data drpdown is a static data
				String type = row.getCell(4).toString();
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
				
				//select the dropdown
				WebElement webSel1 = driver.findElement(By.xpath("//select[@name='industry']"));
				Select sel = new Select(webSel1);
				sel.selectByVisibleText(industries);
				
				WebElement webSel2 = driver.findElement(By.xpath("//select[@name='accounttype']"));
				Select sel2 = new Select(webSel2);
				sel2.selectByVisibleText(type);
				
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click(); 
				
				//verify the industries and type info
				String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
				if(actIndustries.equals(industries)) {
					System.out.println(industries+" information is verified ==PASS");
				}else {
					System.out.println(industries+" information is not verified  ==FAIL");
				}
				
				
				String actType = driver.findElement(By.id("dtlview_Type")).getText();
				if(actType.equals(type)) {
					System.out.println(type+" information is verified ==PASS");
				}else {
					System.out.println(type+" information is not verified  ==FAIL");
				}
				//step-5 logout
				driver.quit();
				

	}

}
