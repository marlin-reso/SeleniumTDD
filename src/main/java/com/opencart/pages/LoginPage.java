package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.constants.AppConstants;
import com.opencart.utils.ElementUtil;
import com.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	// *** top container contains all the toggle****//

	private By currencyToggle = By.xpath("//nav[@id='top']//form[1]");
	private By listInline = By.xpath("//*[@id='top-links']/ul");
	private By search = By.id("search");
	private By cart = By.id("cart");
	private By pageLogo = By.id("logo");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	@Step("Getting the page logo")
	public boolean isPageLogoDisplayed() {
		return eleUtil.isElementDisplayed(pageLogo);
	}

	@Step("Getting that currency toggle")
	public boolean isCurrencyToggleExist() {

		return eleUtil.isElementDisplayed(currencyToggle);
	}

	@Step("Getting inline list")
	public boolean isContainerToggleExist() {

		return eleUtil.isElementDisplayed(listInline);

	}

	@Step("Getting Search field")
	public boolean isSearchFieldExist() {
		return eleUtil.isElementDisplayed(search);

	}

	@Step("Getting cart")
	public boolean isCartExist() {

		return eleUtil.isElementDisplayed(cart);
	}

	// 3. Public page actions/method
	@Step("getting login page title")
	public String getLoginPageTitle() {

		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println("Login page title : " + title);
		return title;

	}

	@Step("Getting the login page URL")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println("login page url : " + url);
		return url;
	}

	@Step("Getting the forgot password link")
	public boolean isForgotPwdLinkExist() {

		return eleUtil.isElementDisplayed(forgotPwdLink);
	}

	@Step("login with username: {0} and password: {1}")
	public AccountsPage doLogin(String username, String pwd) {

		eleUtil.waitForElementVisible(emailId, TimeUtil.DEFAULT_LONG_TIME).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);

	}

	@Step("Navigate to the register page")
	public RegisterationPage navigateToRegisterPage() {

		eleUtil.waitForElementVisible(registerLink, TimeUtil.DEFAULT_LONG_TIME).click();
		// It will return the object of the registration page

		return new RegisterationPage(driver);

	}

}
