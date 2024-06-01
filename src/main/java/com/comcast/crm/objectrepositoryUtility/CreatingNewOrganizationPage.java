package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver ,this);//this will indicates the current class driver
	}
	
	@FindBy(xpath ="//input[@name='accountname']")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryBD;
	
	@FindBy(xpath = "//select[@name='accounttype']")
	private WebElement typeBD;
	
	@FindBy(id="phone")
	private WebElement phoneEdit;
	
	public WebElement getPhoneEdit() {
		return phoneEdit;
	}

	public WebElement getTypeBD() {
		return typeBD;
	}

	public WebElement getIndustryBD() {
		return industryBD;
	}

	public void setIndustryBD(WebElement industryBD) {
		this.industryBD = industryBD;
	}

	@FindBy(name="lastname")
	private WebElement lastNameBtn;
	
	@FindBy(xpath ="//input[@name='account_name']/following-sibling::img")
	private WebElement newBtnImg;
	
	
	

	public WebElement getNewBtnImg() {
		return newBtnImg;
	}

	public WebElement getLastNameBtn() {
		return lastNameBtn;
	}

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg(String orgName) {//business method
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	public void createOrg(String orgName ,String industry) {//business method
		orgNameEdt.sendKeys(orgName);
		Select sel = new Select(industryBD);
		sel.selectByVisibleText(industry);
		saveBtn.click();
		
		
	}
	

}
