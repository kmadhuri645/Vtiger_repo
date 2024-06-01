package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	

	WebDriver driver;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "search_txt")
	private WebElement searchText;
	
	@FindBy(name="search")
	private WebElement searchClick;

	public WebElement getSearchText() {
		return searchText;
	}

	public WebElement getSearchClick() {
		return searchClick;
	}


}
