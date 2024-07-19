package com.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.constants.AppConstants;
import com.opencart.utils.ExcelUtil;
import com.opencart.utils.StringUtils;

public class RegistrationPageTest extends BaseTest {

	@BeforeTest
	public void registrationSetup() {

		registerationPage = loginPage.navigateToRegisterPage();

	}

	@DataProvider
	public Object[][] getUserRegTestData() {
		return new Object[][] { { "Chota", "Rajan", "9898767875", "cr@123", "yes" },
				{ "Manya", "Survey", "9898767876", "ms@123", "no" }, { "Rauf", "lala", "9898767877", "rl@123", "yes" },
				{ "Chota", "Shakeel", "9898767878", "cs@123", "no" }

		};

	}

	// ************************ Using Excel as Data provider
	// ***************************//
	@DataProvider(name = "getUserRegTestDataFromExcel")
	public Object[][] getUserRegTestDataFromExcel() {
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}

	@Test(dataProvider = "getUserRegTestData")
	public void userRegTest(String firstname, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue(registerationPage.userRegister(firstname, lastName, StringUtils.getRandomEmailId(), telephone,
				password, subscribe));

	}

}
