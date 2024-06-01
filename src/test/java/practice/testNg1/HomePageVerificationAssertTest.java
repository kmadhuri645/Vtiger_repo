package practice.testNg1;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HomePageVerificationAssertTest {
	
	@Test
	public void homePageTest(Method mtd) { //To capture the method name dynamically
		
		System.out.println(mtd.getName()+"Test Start");
		String expectedPage="Home Page";
		
		WebDriverManager.chromiumdriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("http://localhost:8888");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		String actTitle=driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		//HardAssert
		Assert.assertEquals(actTitle, expectedPage);
		driver.close();
		System.out.println(mtd.getName()+"Test End");
	}
	@Test
	public void verifyLogoHomePageTest(Method mtd) {
		
		System.out.println(mtd.getName()+"Test Start");
		
		WebDriverManager.chromiumdriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("http://localhost:8888");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		boolean status = driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isEnabled();
		
		if(status) {
			System.out.println("Logo is Verified==PASS");
		}
		else {
			System.out.println("logo is not Verified==FAIL");
		}
		//HardAssert
		Assert.assertTrue(status);
		driver.close();
		System.out.println(mtd.getName()+"Test END");
	}

}
