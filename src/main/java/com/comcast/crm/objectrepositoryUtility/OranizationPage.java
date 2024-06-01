package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OranizationPage {
	
	WebDriver driver;
	public OranizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver , this);
	}
	
	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement serchEdt;
	
	@FindBy(xpath = "//select[@id='bas_searchfield' ]")
	private WebElement searchDropDown;
	
	@FindBy(xpath = "//input[@value=' Search Now ']")
	private WebElement searchBtn;
	
	public WebElement getSerchEdt() {
		return serchEdt;
	}
	

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSearchDropDown() {
		return searchDropDown;
	}
	
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createNewOrgBtn;

	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
	
	

}
