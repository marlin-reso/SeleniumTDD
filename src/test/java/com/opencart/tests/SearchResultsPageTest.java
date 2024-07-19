package com.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;

public class SearchResultsPageTest extends BaseTest {

	// I have to provide pre-condition to test this page
	// 1. do login
	// 2. navigate to searchResults page
	@BeforeClass
	public void searchResultsSetup() {

		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider
	public Object[][] getProductCountData() {

		return new Object[][] {

				{ "macbook", 3 }, { "imac", 1 }, { "samsung", 2 }

		};

	}

	@Test(dataProvider = "getProductCountData")
	public void getProductCount(String searchKey, int productCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		Assert.assertEquals(searchResultsPage.getSearchProductCount(), productCount);
	}

	/*
	 * Before Data provider
	 * 
	 * @Test public void getProductCount() { searchResultsPage =
	 * accPage.doSearch("macbook");
	 * Assert.assertEquals(searchResultsPage.getSearchProductCount(), 3); }
	 * 
	 */

}
