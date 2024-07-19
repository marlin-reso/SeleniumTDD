package com.opencart.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.opencart.factory.DriverFactory;

import io.qameta.allure.Attachment;

public class AllureReportListner implements ITestListener{
	
	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	//Text attachments for allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		
		
	}
	
	//Text attachments for allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	
	//HTML attachments for allure
	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}
	
	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println(("I am in onStart method " + iTestContext.getName()));
	}
	
	@Override
	public void onFinish (ITestContext iTestContext) {
		System.out.println("I am in onfinish method "+ iTestContext.getName());
	}
	
	
	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in onTestStart method  " + getTestMethodName(iTestResult) + " start");
	}
	
	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		
		System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " Success");
	}
	
	
	@Override
	public void  onTestFailure(ITestResult iTestResult) {
		System.out.println(" I am in onTesFailure method " + getTestMethodName(iTestResult) + " Failed");
		Object testClass = iTestResult.getInstance();
		//WebDriver driver = BasePage.getDriver();
		//Allure ScreenShotRobot and SaveTestLog
		
		if(DriverFactory.getTLDriver() instanceof WebDriver) {
			
			System.out.println("screenshot captured for test case : " + getTestMethodName(iTestResult));
			saveScreenshotPNG(DriverFactory.getTLDriver());
		}
		//save a log on allure
		saveTextLog(getTestMethodName(iTestResult) + "failed and screenshot taken!");
		
		
	}
	
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		
		System.out.println(" I am in onTestSkipped method " + getTestMethodName(iTestResult) + " Skipped");
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		
		System.out.println("Test faild but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
			
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
