package com.at.ecom.shopping.framework;

import org.openqa.selenium.Platform;

/**
 * Class to encapsulate various input parameters required for each test script
 * 
 * @author siva
 */
public class SeleniumTestParameters {

	private ExecutionMode executionMode;
	private Browser browser;
	private String browserVersion;
	private Platform platform;
	private String platformVersion;
	private String manufacturer;
	private String modelName;
	private boolean isDeviceUdid;
	private String scenarioName;
	private boolean isAPIExecution;

	/**
	 * Function to get the {@link ExecutionMode} for the test being executed
	 * 
	 * @return The {@link ExecutionMode} for the test being executed
	 */
	public ExecutionMode getExecutionMode() {
		return executionMode;
	}

	/**
	 * Function to set the {@link ExecutionMode} for the test being executed
	 * 
	 * @param executionMode
	 *            The {@link ExecutionMode} for the test being executed
	 */
	public void setExecutionMode(ExecutionMode executionMode) {
		this.executionMode = executionMode;
	}

	/**
	 * Function to get the {@link Browser} on which the test is to be executed
	 * 
	 * @return The {@link Browser} on which the test is to be executed
	 */
	public Browser getBrowser() {
		return browser;
	}

	/**
	 * Function to set the {@link Browser} on which the test is to be executed
	 * 
	 * @param browser
	 *            The {@link Browser} on which the test is to be executed
	 */
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	/**
	 * Function to get the name of the manufacturer Name on which the test is to
	 * be executed
	 * 
	 * @return The name of the manufacturer Name on which the test is to be
	 *         executed
	 */
	public String getManuFacturerName() {
		return manufacturer;
	}

	/**
	 * 
	 * @param manufacturer
	 *            The name of the manufacturer Name on which the test is to be
	 *            executed
	 */
	public void setManuFacturerName(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Function to get the name of the modelName on which the test is to be
	 * executed
	 * 
	 * @return The name of the modelName on which the test is to be executed
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * 
	 * @param modelName
	 *            The name of the modelName on which the test is to be executed
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * Function to get the boolean if Device is UDID
	 * 
	 * @return boolean if Device is UDID
	 */
	public boolean getIsDeviceUdid() {
		return isDeviceUdid;
	}

	/**
	 * set the boolean if the device is UDID
	 */
	public void setIsDeviceUdid(boolean isDeviceUdid) {
		this.isDeviceUdid = isDeviceUdid;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public String getScenarioName() {
		return scenarioName;
	}

	private String seeTestPort;

	/**
	 * Function to get the See Test Port on which the test is to be executed
	 * 
	 * @return The See Test Port on which the test is to be executed
	 */
	public String getSeeTestPort() {
		return seeTestPort;
	}

	/**
	 * Function to set the See Test Port on which the test is to be executed
	 * 
	 * @param version
	 *            The See Test Port on which the test is to be executed
	 */
	public void setSeeTestPort(String seeTestPort) {
		this.seeTestPort = seeTestPort;
	}

	/**
	 * Function to get the Browser Version on which the test is to be executed
	 * 
	 * @return The Browser Version on which the test is to be executed
	 */
	public String getBrowserVersion() {
		return browserVersion;
	}

	/**
	 * Function to set the Browser Version on which the test is to be executed
	 * 
	 * @param version
	 *            The Browser Version on which the test is to be executed
	 */
	public void setBrowserVersion(String version) {
		this.browserVersion = version;
	}

	/**
	 * Function to get the {@link Platform} on which the test is to be executed
	 * 
	 * @return The {@link Platform} on which the test is to be executed
	 */
	public Platform getPlatform() {
		return platform;
	}

	/**
	 * Function to set the {@link Platform} on which the test is to be executed
	 * 
	 * @param platform
	 *            The {@link Platform} on which the test is to be executed
	 */
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	/**
	 * Function to get the Platform Version on which the test is to be executed
	 * 
	 * @return The Platform Version on which the test is to be executed
	 */
	public String getPlatformVersion() {
		return platformVersion;
	}

	/**
	 * Function to set the Platform Version on which the test is to be executed
	 * 
	 * @param platformVersion
	 *            The Platform Version on which the test is to be executed
	 */
	public void setPlatformVersion(String platformVersion) {
		this.platformVersion = platformVersion;
	}

	/**
	 * Function to get the if its API based execution
	 * 
	 * @return The isAPIExecution
	 */
	public boolean isAPIExecution() {
		return isAPIExecution;
	}

	/**
	 * Function to set the if its API based execution
	 * 
	 * @param isAPIExecution
	 */
	public void setAPIExecution(boolean isAPIExecution) {
		this.isAPIExecution = isAPIExecution;
	}

}