package com.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.opencart.factory.DriverFactory;
import com.opencart.pages.AccountsPage;
import com.opencart.pages.LoginPage;
import com.opencart.pages.ProductInfoPage;
import com.opencart.pages.RegisterationPage;
import com.opencart.pages.SearchResultsPage;

import io.qameta.allure.Step;

public class BaseTest {
	// step-4
	WebDriver driver;
	protected Properties prop;
	DriverFactory df;

	// make it protected for access in LoginPageTest Class.
	// We Do here all the configuration related work or setup related work.
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterationPage registerationPage;
	// Soft assertion
	protected SoftAssert softAssert;

	// we adding parameter to run the xml suite with different browser which is
	// provided into xml file;
	@Step("Setup : launching {0} browser & init the properties")
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {

		df = new DriverFactory();
		prop = df.initProp();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}

		driver = df.initDriver(prop);
		// we return the driver from initDriver class

		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();

	}

	@Step("Closing browser")
	@AfterTest
	public void tearDown() {

		driver.quit();
	}

}
