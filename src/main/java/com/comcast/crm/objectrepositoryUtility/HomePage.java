package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage extends WebDriverUtility { // Rule-1 create a separate java class


	public HomePage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Organizations")
	private WebElement orgLink; // Rule-2 Object creation
								               // Rule-3 Object Initialization
	@FindBy(linkText = "Contacts")             // Rule-4 Object Encapsulation
	private WebElement contactLink;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignsLink;
	
	@FindBy(linkText = "More")
	private WebElement moreLink;
	
    @FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
    private WebElement adminImg;
    
    @FindBy(linkText = "Sign Out")
    private WebElement signOutLink;
    
	                                                                       // Rule-5 Object Utilization
	

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}
//	public void navigateToCampaginPage(WebDriver driver) {
//		
//		Actions act=new Actions(driver);
//		act.moveToElement(moreLink).perform();
//		campaignsLink.click();
//		
//	}
    public void logout(WebDriver driver) {
    	
    	adminImg.click();
    mousemoveOnElement(driver, signOutLink);
    	
    }

}
