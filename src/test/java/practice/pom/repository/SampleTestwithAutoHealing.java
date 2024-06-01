package practice.pom.repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class SampleTestwithAutoHealing {
	
	@FindBy(name="user_name")
	WebElement ele1;
	
	@FindBy(name="user_password")
	WebElement ele2;
	
	
	  @FindAll({@FindBy(id="submitButton33333"),@FindBy(xpath = "//input[@type='submit']")})
	//@FindBys ({@FindBy(id="submitButton"),@FindBy(xpath = "//input[@value='Login']")})
	  private WebElement ele3;
	
	
	@Test
	public void sampleTest() {
		ChromeDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.get("http://localhost:8888");//when call the pf method all the annotation is executed than load ele insidethe  variable
		SampleTestwithAutoHealing s = PageFactory.initElements(driver ,SampleTestwithAutoHealing.class );
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("admin");//ele2 take the latested version and perforing the action
		
		
		s.ele3.click();
	}


	
	}

