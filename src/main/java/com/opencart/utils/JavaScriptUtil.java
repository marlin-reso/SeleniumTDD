package com.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	WebDriver driver;
	
	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	public void flash(WebElement element) {
		
		String bgcolor = element.getCssValue("backgroundColor");//red
		for(int i=0; i<10;i++) {
			changeColor("rgb(0,200,0)", element);//green
			changeColor(bgcolor, element);//red
		}
		
		
	}
	
	private void changeColor(String color, WebElement element) {
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", driver);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
	}
	
	public String getTitleByJS() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title;").toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
