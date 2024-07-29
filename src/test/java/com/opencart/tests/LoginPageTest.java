package com.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.constants.AppConstants;
import com.opencart.errors.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Desing open cart login page")
@Story("user story 101: Design login page features ofr open cart application")
@Feature("feature 201: Adding login feature")
public class LoginPageTest extends BaseTest {

	@Description("Checking login page title.....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {

		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);

	}

	@Description("Checking login page URL.....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPageURLTest() {

		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND);

	}

	@Description("Checking forget pwd link on login page.....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {

		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Description("Verify that login page logo visibility")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 4)
	public void verifyLoginPageLogoVisibleTest() {
		Assert.assertTrue(loginPage.isPageLogoDisplayed());

	}

	@Description("Verify that login page currency visibility")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 5)
	public void verifyThatIsCorrencyToggleExist() {
		Assert.assertTrue(loginPage.isCurrencyToggleExist());
	}

	@Description("Verify that login page Container visibility")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 6)
	public void verifyThatIsContainerToggleExist() {
		Assert.assertTrue(loginPage.isContainerToggleExist());
	}

	@Description("Verify that login page Search Field visibility")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 7)
	public void verifyThatIsSearchFieldDisplayed() {

		Assert.assertTrue(loginPage.isSearchFieldExist());
	}

	@Description("Verify that login page Cart visibility")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 8)
	public void verifyThatIsCartExist() {

		Assert.assertTrue(loginPage.isCartExist());
	}

	@Description("Checking user is able to login.....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 9)
	public void loginTest() {

		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}

}
