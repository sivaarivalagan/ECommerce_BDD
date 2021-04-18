package com.at.ecom.shopping.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.at.ecom.shopping.utility.TestBase;

import org.openqa.selenium.TimeoutException;

/**
 * Class containing useful WebDriver utility functions
 * 
 * @author siva
 */
public class WebDriverUtil extends TestBase {
	private WebDriver utildriver;

	/**
	 * Constructor to initialize the {@link WebDriverUtil} object
	 * 
	 * @param driver
	 *            The {@link WebDriver} object
	 */
	public WebDriverUtil(WebDriver driver) {
		this.utildriver = driver;
	}

	/**
	 * Function to pause the execution for the specified time period
	 * 
	 * @param milliSeconds
	 *            The wait time in milliseconds
	 */
	public void waitFor(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (Exception e) {
			log("Interrupted Exception : Unable to initialized Thread at this moment : " +e  );
		}
	}

	/**
	 * Function to wait until the specified element is located
	 * 
	 * @param by
	 *            The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds
	 *            The wait timeout in seconds
	 */
	public void waitUntilElementLocated(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions
				.presenceOfElementLocated(by));
	}

	/**
	 * Function to wait until the specified element is visible
	 * 
	 * @param by
	 *            The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds
	 *            The wait timeout in seconds
	 */
	public void waitUntilElementVisible(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions
				.visibilityOfElementLocated(by));
	}

	/**
	 * Function to wait until the specified element is enabled
	 * 
	 * @param by
	 *            The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds
	 *            The wait timeout in seconds
	 */
	public void waitUntilElementEnabled(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions
				.elementToBeClickable(by));
	}

	/**
	 * Function to wait until the specified element is disabled
	 * 
	 * @param by
	 *            The {@link WebDriver} locator used to identify the element
	 * @param timeOutInSeconds
	 *            The wait timeout in seconds
	 */
	public void waitUntilElementDisabled(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions
				.not(ExpectedConditions.elementToBeClickable(by)));
	}

	/**
	 * Function to select the specified value from a listbox
	 * 
	 * @param by
	 *            The {@link WebDriver} locator used to identify the listbox
	 * @param item
	 *            The value to be selected within the listbox
	 */
	public void selectListItem(By by, String item) {
		Select dropDownList = new Select(driver.findElement(by));
		dropDownList.selectByVisibleText(item);
	}

	/**
	 * Function to verify whether the specified object exists within the current
	 * page
	 * 
	 * @param by
	 *            The {@link WebDriver} locator used to identify the element
	 * @return Boolean value indicating whether the specified object exists
	 */
	public Boolean objectExists(By by) {
		if (utildriver.findElements(by).size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Function to verify whether the specified text is present within the
	 * current page
	 * 
	 * @param textPattern
	 *            The text to be verified
	 * @return Boolean value indicating whether the specified test is present
	 */
	public Boolean isTextPresent(String textPattern) {
		if (utildriver.findElement(By.cssSelector("BODY")).getText()
				.matches(textPattern)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Function to check if an alert is present on the current page
	 * 
	 * @param timeOutInSeconds
	 *            The number of seconds to wait while checking for the alert
	 * @return Boolean value indicating whether an alert is present
	 */
	public Boolean isAlertPresent(long timeOutInSeconds) {
		try {
			new WebDriverWait(driver, timeOutInSeconds)
					.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (TimeoutException ex) {
			return false;
		}
	}
}