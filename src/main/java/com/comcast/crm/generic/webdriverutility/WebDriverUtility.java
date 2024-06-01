
package com.comcast.crm.generic.webdriverutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	/*
	 * wait for browser
	 */

	public void waitForPageToLoad(WebDriver driver) {// if we not pass driver than which browser we wait for bcz so many
														// browser open in local system
														// driver means browser season id which is avilable in
														// testscript
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	/*
	 * wait for element WebDriver also pass bcz which browser wait for the element;
	 */
	public void waitForElementPresent(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/*
	 * switch to window based on URL
	 */

	public void switchToTabOnURL(WebDriver driver, String partialURL) {

		Set<String> set1 = driver.getWindowHandles();// set is not a index based thats why we take iterator
		Iterator<String> it = set1.iterator();
		while (it.hasNext()) {

			String windowID = it.next();
			driver.switchTo().window(windowID);

			String actURL1 = driver.getCurrentUrl();
			if (actURL1.contains("partialURL")) {
				break;
			}
		}
	}
	/*
	 * switch to window based on Title
	 */

	public void switchToTabOnTitle(WebDriver driver, String partialTitle) {

		Set<String> set1 = driver.getWindowHandles();// set is not a index based thats why we take iterator
		Iterator<String> it = set1.iterator();
		while (it.hasNext()) {

			String windowID = it.next();
			driver.switchTo().window(windowID);

			String actURL1 = driver.getTitle();
			if (actURL1.contains("partialURL")) {
				break;
			}
		}
	}
	/*
	 * switch to frame using  based on index  and name and webelement
	 */
	public void switchToFrame(WebDriver driver ,int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver ,String nameID) {
		driver.switchTo().frame(nameID);
	}
	public void switchToFrame(WebDriver driver ,WebElement element) {
		driver.switchTo().frame(element);
	}
	
	/*
	 * switch to Alert 
	 */
	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndCancle(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	/*
	 * dropdown action 
	 */
	public void select(WebElement element ,String text) {
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	public void select(WebElement element ,int index) {
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	/*
	 * mouse related operation
	 */
	public void mousemoveOnElement(WebDriver driver ,WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	public void doubleClick(WebDriver driver ,WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
		
	/*
	 * Screenshot 
	 */
	public void takeScreenshot(WebDriver driver) throws Throwable {
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File TempFile = ts.getScreenshotAs(OutputType.FILE);
		File PermanentFile=new File("./screenshot/firstshot.jpg");
		FileHandler.copy(TempFile, PermanentFile);
		
		
	}
	//mvn -Dtest=classnameTest#methodNmae test;
	
}



