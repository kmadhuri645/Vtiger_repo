package com.comcast.crm.generic.webdriverutility;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
		/**
		 * This method is used to wait for the page loading
		 * @author madhuri
		 * @param driver
		 * @param sec
		 */
		public void waitForPageLoad(WebDriver driver,int sec)
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
		}

		public void maximizeTheWebPage(WebDriver driver)
		{
			driver.manage().window().maximize();
		}

		public void minimizeTheWebPage(WebDriver driver)
		{
			driver.manage().window().minimize();
		}

		public WebDriverWait webdriverWaitObject(WebDriver driver,int sec)
		{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(sec));
			return wait;
		}

		public void waitUntilElementVisble(WebElement element, WebDriver driver, int sec)
		{
			webdriverWaitObject(driver, sec).until(ExpectedConditions.visibilityOf(element));
		}

		public void waitUntilElementIsClickable(WebDriver driver, int sec, WebElement element)
		{
			webdriverWaitObject(driver, sec).until(ExpectedConditions.elementToBeClickable(element));
		}

		public void waitAlertToBePreseent(WebDriver driver,int sec)
		{
			webdriverWaitObject(driver,sec).until(ExpectedConditions.alertIsPresent());
		}

		public Select dropdownObj(WebElement element)
		{
			Select select=new Select(element);
			return select;
		}

		public void handleDropdown(WebElement element,int index)
		{
			dropdownObj(element).selectByIndex(index);
		}



		public Actions actionsClassObje(WebDriver driver)
		{
			Actions act=new Actions(driver);
			return act;
		}



		public void dragAndDrop( WebDriver driver,WebElement src, WebElement dstn)
		{
			actionsClassObje(driver).dragAndDrop(src, dstn).perform();
		}



		public void MouseHoverAction(WebDriver driver,WebElement element)
		{
			actionsClassObje(driver).moveToElement(element).perform();
		}



		public void ToClick(WebDriver driver, WebElement element)
		{
			actionsClassObje(driver).click(element).perform();
		}


	/**
	 * This method will perform double click on the webPage
	 * @author Madhuri
	 * @param driver
	 * @param element
	 */
		public void doubleClickAction(WebDriver driver,WebElement element)
		{
			actionsClassObje(driver).doubleClick(element).perform();
		}

		public void switchToWindow(WebDriver driver,String expWindow)
		{
			Set<String> allwindow = driver.getWindowHandles();
			Iterator<String> it = allwindow.iterator();
			while(it.hasNext())
			{
				String window = it.next();
				String currentTitle = driver.switchTo().window(window).getTitle();
				
				if(currentTitle.contains(expWindow))
				{
					break;
				}
			}
		}
		
		public void rightClickAction(WebDriver driver, WebElement element)
		{
			actionsClassObje(driver).contextClick(element).perform();
		}
		
		public void enterKeyPress(WebDriver driver)
		{
			actionsClassObje(driver).sendKeys(Keys.ENTER).perform();
		}
		
		public void enterKey() throws Throwable
		{
			Robot robo=new Robot();
			robo.keyPress(KeyEvent.VK_ENTER);
		}
		
		
		
		public void releaseKey() throws Throwable
		{
			Robot robo=new Robot();
			robo.keyRelease(KeyEvent.VK_ENTER);
		}
		
		public void switchToFrame(WebDriver driver,int index)
		{
			driver.switchTo().frame(index);
		}
		
		public void switchToFrame(WebDriver driver ,String nameORid)
		{
			driver.switchTo().frame(nameORid);
		}
		
		public void switchToFrame(WebDriver driver, WebElement address)
		{
			driver.switchTo().frame(address);
		}
		
		public void acceptAlert(WebDriver driver)
		{
			driver.switchTo().alert().accept();
		}
		
		public void cancelAlert(WebDriver driver)
		{
			driver.switchTo().alert().dismiss();
		}
		
	//	public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException
	//	{
			//JavaUtility jlib=new JavaUtility();
			//TakesScreenshot ts=(TakesScreenshot) driver;
			//File src = ts.getScreenshotAs(OutputType.FILE);
			
		//	String path=".\\Screenshot\\"+ screenshotName+jlib.getSystemDateInForma()+".png";
		//	File dst=new File(path);
		//	String srcpath=dst.getAbsolutePath();
		//	FileUtils.copyFile(src, dst); 
		//	return srcpath;
		//}
		
		public void scrollAction(WebDriver driver)    
		{
			JavascriptExecutor js=(JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)", "");
		}
	}


