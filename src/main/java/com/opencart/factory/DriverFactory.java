package com.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.opencart.errors.AppError;
import com.opencart.exceptions.BrowserException;
import com.opencart.exceptions.FrameworkException;
import com.opencart.logger.Log;
import com.opencart.pathConstant.PropertyFilePath;

public class DriverFactory {
	// step-2

	WebDriver driver;
	Properties prop;

	// Things for run in incognito mode

	OptionsManager optionsManager;
	
	

	// Thread local implementaion

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		 
		// when we need to provide the browser name while doing the execution of maven
		// on cmd.
		// mvn clean install -Denv="qa" -Dbrowser="chrome"
		// but we can not achieve cross browser testing via this approach
		// String browserName = System.getProperty("browser");

		System.out.println("Browser name is : " + browserName);

		optionsManager = new OptionsManager(prop);

		switch (browserName.trim().toLowerCase()) {
		case "chrome":

			// ***** for remote execution using docker*******//
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote - grid execution
				init_remoteDriver("chrome");

			} else {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}

			break;
		case "firefox":

			// ***** for remote execution using docker*******//
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote - grid execution
				init_remoteDriver("firefox");

			} else {
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}

			break;
		case "edge":

			// ***** for remote execution using docker*******//
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote - grid execution
				init_remoteDriver("edge");

			} else {
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}

			break;
		case "safari":

			tlDriver.set(new SafariDriver());

			break;

		default:

			Log.error("Pass the right browser " + browserName);
			throw new BrowserException("No Browser found : " + browserName);

		}

		// driver.manage().deleteAllCookies();
		getTLDriver().manage().deleteAllCookies();
		getTLDriver().manage().window().maximize();
		getTLDriver().get(prop.getProperty("url"));

		return getTLDriver();

	}

	public static WebDriver getTLDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {

		// env name = qa,dev,stage,prod,UAT etc.
		// mvn clean install -Denv="qa"

		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		System.out.println("Running tests on Env : " + envName);

		try {

			if (envName == null) {
				System.out.println("No env is given ...hence running it n QA env......");
				ip = new FileInputStream(PropertyFilePath.qaPath);

			} else {

				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream(PropertyFilePath.qaPath);
					break;

				case "dev":
					ip = new FileInputStream(PropertyFilePath.devPath);
					break;

				case "stage":
					ip = new FileInputStream(PropertyFilePath.stagePath);
					break;

				case "uat":
					ip = new FileInputStream(PropertyFilePath.uatPath);
					break;

				case "prod":
					ip = new FileInputStream(PropertyFilePath.prodPath);
					break;

				default:
					System.out.println("");
					throw new FrameworkException(AppError.ENV_NAME_NOT_FOUND + " : " + envName);

				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		try {
			prop.load(ip);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return prop;

	}

	public static String getScreenshot(String methodName) {

		File srcFile = ((TakesScreenshot) getTLDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + "-" + System.currentTimeMillis() + ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * run tests on selenium grid
	 * 
	 * @param browserName
	 */
	private void init_remoteDriver(String browserName) {

		System.out.println("Running tests on Remote GRID on browser : " + browserName);

		try {
			switch (browserName.toLowerCase().trim()) {
			case "chrome":

				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));

				break;

			case "firefox":

				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));

				break;

			case "edge":

				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));

				break;

			default:
				System.out.println("Please pass the right supported browser on GRID.....");
				break;
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
	}
}
