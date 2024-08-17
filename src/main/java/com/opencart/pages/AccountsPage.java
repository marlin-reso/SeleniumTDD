package com.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.constants.AppConstants;
import com.opencart.utils.ElementUtil;
import com.opencart.utils.TimeUtil;

public class AccountsPage {

	// page class/page library/page object
    private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. private By locators

	private By logoutLink = By.linkText("Logout");
	private By myAccountLink = By.linkText("My Account");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	/***Edit Account Page*******/
	private By editAccountLink = By.linkText("Edit Account");
	

	// 2. Public Page Class const.....

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccPageTitle() {

		String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println("Acc page title : " + title);
		return title;
	}

	public String getAccPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println("Acc page URL : " + url);
		return url;
	}

	public boolean isLogoutLinkExist() {

		return eleUtil.waitForElementVisible(logoutLink, TimeUtil.DEFAULT_LONG_TIME).isDisplayed();
	}

	public boolean isMyAccLinkExist() {

		return eleUtil.waitForElementVisible(myAccountLink, TimeUtil.DEFAULT_LONG_TIME).isDisplayed();
	}

	public List<String> getAccountsPageHeadersList() {

		List<WebElement> headersEleList = eleUtil.getElements(headers);
		List<String> headersList = new ArrayList<String>();
		for (WebElement e : headersEleList) {
			String header = e.getText();
			headersList.add(header);
		}
		return headersList;
	}

	public SearchResultsPage doSearch(String searchKey) {

		System.out.println("searching for : " + searchKey);
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon); 
		return new SearchResultsPage(driver);

	}
	
	public EditAccountPage editAccountPage() {
		
		eleUtil.doClick(editAccountLink);
		return new EditAccountPage(driver);
	}
	
	
	
	

}
