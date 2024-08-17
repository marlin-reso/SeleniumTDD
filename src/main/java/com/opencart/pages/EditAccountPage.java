package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.opencart.constants.AppConstants;
import com.opencart.utils.ElementUtil;
import com.opencart.utils.TimeUtil;

public class EditAccountPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// Private By locators of edit Account page
	private By firstName = By.name("firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By continueBtn = By.xpath("//input[@type='submit']");
	private By successMsg = By.xpath("//*[@id='account-account']/div[1]/text()");
	private By logoutLink = By.linkText("Logout");

	public EditAccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean userEditAccount(String firstName, String lastName, String email, String telephone) {

		eleUtil.waitForElementVisible(this.firstName, TimeUtil.DEFAULT_LONG_TIME).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);

		eleUtil.doClick(continueBtn);
		//
		String editSuccessMsg = eleUtil.waitForElementVisible(successMsg, TimeUtil.DEFAULT_LONG_TIME).getText();
		if (editSuccessMsg.equals(AppConstants.USER_EDIT_ACCOUNT_SUCCESS_MSG)) {
			eleUtil.doClick(logoutLink);
			System.out.println("test_01");

			return true;
		}
		return false;

	}

}
