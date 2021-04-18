package com.at.ecom.shopping.framework;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * A generic WebDriver manager, which handles multiple instances of WebDriver.
 * 
 * @author siva
 */
public class DriverManager {

	/*
	 * Used for Multithreading of WebDriver Object
	 */
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<SeleniumTestParameters> testParameters = new ThreadLocal<SeleniumTestParameters>();

	static Logger log;

	static {
		log = Logger.getLogger(DriverManager.class);
	}

	/**
	 * Function to return the object for WebDriver {@link WebDriver} object
	 * 
	 * @return Instance of the {@link WebDriver} object
	 */
	public static WebDriver getWebDriver() {
		if (webDriver.get() == null) {
			// this is need when running tests from IDE
			log.info("Thread has no WedDriver, creating new one");
			setWebDriver(DriverFactory.createWebDriverInstance(null));
		}
		log.debug("Getting instance of remote driver" + webDriver.get().getClass());
		return webDriver.get();
	}

	/**
	 * Function to set the WebDriver Object{@link WebDriver} object
	 * 
	 * @param driver
	 */
	public static void setWebDriver(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverManager.webDriver.set(driver);
	}

	/**
	 * Function to set the SeleniumTestParameters
	 * Object{@link SeleniumTestParameters} object
	 * 
	 * @param testParameters
	 */
	public static void setTestParameters(SeleniumTestParameters testParameters) {
		DriverManager.testParameters.set(testParameters);
	}

	/**
	 * Function to return the object for SeleniumTestParameters
	 * {@link SeleniumTestParameters} object
	 * 
	 * @return Instance of the {@link SeleniumTestParameters} object
	 */
	public static SeleniumTestParameters getTestParameters() {
		return testParameters.get();
	}
}