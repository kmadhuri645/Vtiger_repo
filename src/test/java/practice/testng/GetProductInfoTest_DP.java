package practice.testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetProductInfoTest_DP {
	
	@Test(dataProvider = "getDate")
	public void getProductInfoTest(String brandName , String productName) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone" ,Keys.ENTER);
		
		//capture product information
		//String x="//span[text()='Apple iPhone 15 (128 GB) - Black']/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']/descendant::span[@class='a-price-whole']";
		String x="//span[text()='"+productName+"']/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']/descendant::span[@class='a-price-whole']";
		String price=driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		driver.quit();
	}
	
//		@DataProvider
//		public Object[][] getDate() {
//			Object[][] objArr=new Object[3][2];///org.testng.internal.reflect.MethodMatcherException
//			
//			objArr[0][0]="iphone";                //Row And Column
//			objArr[0][1]="Apple iPhone 15 (128 GB) - Blue";
//			
//			objArr[1][0]="iphone"; 
//			objArr[1][1]="Apple iPhone 13 (128GB) - Pink";
//			
//			objArr[2][0]="iphone"; 
//			objArr[2][1]="Apple iPhone 14 (128 GB) - Purple";
//			
//			
//			return objArr;
//		}
	
	@DataProvider
	public Object[][] getDate() throws Throwable {
		ExcelUtility eLib=new ExcelUtility();
		int rowCount=eLib.getRowCount("product");
		
		Object[][] objArr=new Object[rowCount][2];
		
//for loop helps us to create object array and onces object array is getting created and tc will executed		
		for(int i=0;i<rowCount;i++) {
			
			objArr[i][0]=eLib.getDataFromExcel("product", i+1, 0);
			objArr[i][1]=eLib.getDataFromExcel("product", i+1, 1);
		}
		
		
		return objArr;
	}
	
	

}
