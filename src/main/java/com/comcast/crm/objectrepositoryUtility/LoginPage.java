package com.comcast.crm.objectrepositoryUtility;
/*
 * Rule-1 create a separate java class
 * Rule-2 Object Creation
 * Rule-3 Object Initization-it will do in testscript
 */

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
/**
 * 
 * @author kmadh
 * 
 * contains Login page elements and business lib like login()
 *
 */

public class LoginPage extends WebDriverUtility {
	
	
	public LoginPage(WebDriver driver) {
		
		PageFactory.initElements(driver,this); //this means current object reference
	}
	
	@FindBy(name="user_name")//if  ui changes come to repostry and change it
	private WebElement usernameEdt; //to avoid write  access to everybody 
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	//Rules-4 Object Encapsulation

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	/**
	 * login to application based username,password arguments
	 * @param username
	 * @param password
	 */
	public void loginToapp(String username ,String password) //Provide Action
	{//this is call business library
		
		usernameEdt.sendKeys(username);//this method can not use for anyother application
		passwordEdt.sendKeys(password);
		loginBtn.click();
		
	}
	
	
	

}
