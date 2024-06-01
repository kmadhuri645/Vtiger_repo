package practice.testNg1;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTestForScreenShot {
	
	@Test
	public void amazonTest() throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://amazon.com");
	
	//Step-1 : create an object to EventFiring Webdriver
	
	EventFiringWebDriver edriver=new EventFiringWebDriver(driver);
	
	//Step-2 : use getScreenshotAs method to get file type of screenshot
	
	File srcFile = edriver.getScreenshotAs(OutputType.FILE);
	
	//Step-: Store screenshot on local driver
	
	File destFile=new File("./screenshot/test.png");
	FileUtils.copyFile(srcFile, destFile);
	driver.findElement(By.id(""));
	
	//FileUtils.copyFile(srcFile, new File("./screenshot/test.png"));

	}
}