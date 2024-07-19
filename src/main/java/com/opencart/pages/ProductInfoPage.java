package com.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.utils.ElementUtil;
import com.opencart.utils.TimeUtil;

public class ProductInfoPage {
	// page class/page library/page object
	private WebDriver driver;
	private ElementUtil eleUtil;
	//for store the the value of getProductMetaData(), into HashMap or key: value format.
	private Map<String, String> productMap = new HashMap<String, String>();
	
	
	// 1. private By locators
	
	private By productHeader = By.tagName("h1");
	//nice xpath finding way..understand
	private By images = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	
	
	
	// 2. Public Page Class const.....
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	
	public String getProductHeader() {
		String header = eleUtil.doGetElementText(productHeader);
		System.out.println(header);
		return header;
	}
	
	public int getProductImagesCount() {
		
		int totalImage = eleUtil.waitForElementsVisible(images, TimeUtil.DEFAULT_LONG_TIME).size();
		System.out.println("Image count for " + getProductHeader() + " : " + totalImage);
		return totalImage;
	}
	//Brand: Apple
	//Product Code: Product 18
	//Reward Points: 800
	//Availability: In Stock
	private void getProductMetaData() {
		
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		for(WebElement e:metaList) {
			String text = e.getText();
			String metaKey = text.split(":")[0].trim();
			String metaValue = text.split(":")[1].trim();
			productMap.put(metaKey, metaValue);
		}
	
	}
	
	//$2,000.00
	//Ex Tax: $2,000.00
	private void getProductPriceData() {
		
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		
		String price = priceList.get(0).getText();
		String exTaxPrice =priceList.get(1).getText().split(":")[1].trim();
		
		productMap.put("productPrice", price);
		productMap.put("extaxPrice", exTaxPrice);
		

	}
	
	public Map<String, String> getProductDetailsMap() {
		
		productMap.put("header", getProductHeader());
		productMap.put("productImages", String.valueOf(getProductImagesCount()));
		 getProductMetaData();
		 getProductPriceData();
		 return productMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
