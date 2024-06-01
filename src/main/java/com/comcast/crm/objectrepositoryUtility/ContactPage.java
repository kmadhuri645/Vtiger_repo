package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

	WebDriver driver;

	public ContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement CreateNewContactBtn;
	
    @FindBy(className = "dvHeaderText")
    private WebElement headerMsg;
    

	public WebElement getHeaderMsg() {
		return headerMsg;
	}


	public WebElement getCreateNewContactBtn() {
		return CreateNewContactBtn;
	}

}
