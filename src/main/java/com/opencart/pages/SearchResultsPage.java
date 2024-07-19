package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.utils.ElementUtil;
import com.opencart.utils.TimeUtil;

public class SearchResultsPage {
	// page class/page library/page object
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	// 1. private By locators
	private By searchProducts = By.cssSelector("div.product-thumb");
	
	
	
	// 2. Public Page Class const.....
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int getSearchProductCount() {
		
		int productCount =  eleUtil.waitForElementsVisible(searchProducts, TimeUtil.DEFAULT_LONG_TIME).size();
		return productCount;
		
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Searching for product : " + productName);
		eleUtil.waitForElementVisible(By.linkText(productName), TimeUtil.DEFAULT_LONG_TIME).click();
		return new ProductInfoPage(driver);
	}
	

} 
