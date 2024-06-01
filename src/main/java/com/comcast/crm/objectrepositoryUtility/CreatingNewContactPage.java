package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	
	WebDriver driver;

	public CreatingNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement createContact;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//input[@name='support_start_date' and @id='jscal_field_support_start_date']")
	private WebElement startDate;
	
	@FindBy(xpath = "//input[@name='support_end_date' and @id='jscal_field_support_end_date']")
	private WebElement endDate;
	
	public WebElement getEndDate() {
		return endDate;
	}

	public WebElement getStartDate() {
		return startDate;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getCreateContact() {
		return createContact;
	}
	
	public void createContactWithSupportDate(String lastName ,String startdate ,String enddate) throws Exception {
		
		createContact.sendKeys(lastName);
		startDate.click();
		Thread.sleep(1000);
		startDate.clear();
		startDate.sendKeys(startdate);
		endDate.click();
		Thread.sleep(1000);
		endDate.clear();
		endDate.sendKeys(enddate);
		
		
			
	}
}
