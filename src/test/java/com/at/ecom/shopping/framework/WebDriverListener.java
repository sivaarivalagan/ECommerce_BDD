package com.at.ecom.shopping.framework;

import java.lang.reflect.Field;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.BaseTestMethod;

/**
 * Will be called before every TestNG Method This Implements
 * IInvokedMethodListener {@link IInvokedMethodListener}
 * 
 * @author siva: 
 */
public class WebDriverListener implements IInvokedMethodListener {

	static Logger log = Logger.getLogger(WebDriverListener.class);

	private static Properties properties = Settings.getInstance();
	private static final String BROWSERNAME = "BrowserName";
	private static final String DEFAULTBROWSER = "DefaultBrowser";
	private static final String BROWSERVERSION = "BrowserVersion";
	private static final String DEFAULTBROWSERVERSION = "DefaultBrowserVersion";
	private static final String PLATFORM = "Platform";
	private static final String DEFAULTPLATFORM = "DefaultPlatform";

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

		SeleniumTestParameters testParameters = new SeleniumTestParameters();

		log.debug("BEGINNING: com.at.crm.salesforce.supportLibraries.WebDriverListener-beforeInvocation");
		if (method.isTestMethod()) {
			try {
				setDefaultTestParameters(method, testParameters);
				DriverManager.setTestParameters(testParameters);

			} catch (Exception ex) {
				log.error(ex.getMessage());
				log.info("Method not Found :  " + ex);
			}
		}
		log.debug("END: com.at.crm.salesforce.supportLibraries.WebDriverListener-beforeInvocation");
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

		log.debug("BEGINNING: WebDriverListener.afterInvocation");
		/*
		 * change the name of the test method that will appear in the report to one that
		 * will contain very handy when analysing results.
		 */
		if (method.isTestMethod()) {
			try {
				BaseTestMethod bm = (BaseTestMethod) testResult.getMethod();
				Field f = bm.getClass().getSuperclass().getDeclaredField("m_methodName");
				f.setAccessible(true);
				String newTestName = testResult.getTestContext().getCurrentXmlTest().getName() + " - "
						+ bm.getMethodName() + " - ";
				log.info("Renaming test method name from: '" + bm.getMethodName() + "' to: '" + newTestName);
				f.set(bm, newTestName);
			} catch (Exception ex) {
				log.info("afterInvocation exception:\n" + ex.getMessage());

			}
		}
		log.debug("END: WebDriverListener.afterInvocation");
	}

	private void setDefaultTestParameters(IInvokedMethod method, SeleniumTestParameters testParameters) {

		try {
			String executionMode = method.getTestMethod().getXmlTest().getLocalParameters().get("ExecutionMode");

			isAPIExecution(executionMode, testParameters);

			switch (executionMode) {

			case "API":

				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

				break;

			case "LOCAL":

				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

				if (method.getTestMethod().getXmlTest().getLocalParameters().get(BROWSERNAME) == null) {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty(DEFAULTBROWSER)));

				} else {
					testParameters.setBrowser(
							Browser.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get(BROWSERNAME)));
				}

				break;

			case "GRID":

				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

				if (method.getTestMethod().getXmlTest().getLocalParameters().get(BROWSERNAME) == null) {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty(DEFAULTBROWSER)));

				} else {
					testParameters.setBrowser(
							Browser.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get(BROWSERNAME)));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get(BROWSERVERSION) == null) {
					testParameters.setBrowserVersion(properties.getProperty(DEFAULTBROWSERVERSION));

				} else {
					testParameters.setBrowserVersion(
							method.getTestMethod().getXmlTest().getLocalParameters().get(BROWSERVERSION));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get(PLATFORM) == null) {
					testParameters.setPlatform(Platform.valueOf(properties.getProperty(DEFAULTPLATFORM)));

				} else {
					testParameters
							.setBrowserVersion(method.getTestMethod().getXmlTest().getLocalParameters().get(PLATFORM));
				}

				break;

			case "PERFECTO":

			case "SEETEST":

				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
				
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("SeeTestPort") == null) {
					testParameters.setSeeTestPort(properties.getProperty("SeeTestDefaultPort"));

				} else {
					testParameters.setSeeTestPort(
							method.getTestMethod().getXmlTest().getLocalParameters().get("SeeTestPort"));
				}

				break;

			case "SAUCELABS":

				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

				// For Sauce Labs Browser based execution

				if (method.getTestMethod().getXmlTest().getLocalParameters().get(BROWSERNAME) == null) {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty(DEFAULTBROWSER)));

				} else {
					testParameters.setBrowser(
							Browser.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get(BROWSERNAME)));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get(BROWSERVERSION) == null) {
					testParameters.setBrowserVersion(properties.getProperty(DEFAULTBROWSERVERSION));

				} else {
					testParameters.setBrowserVersion(
							method.getTestMethod().getXmlTest().getLocalParameters().get(BROWSERVERSION));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get(PLATFORM) == null) {
					testParameters.setPlatform(Platform.valueOf(properties.getProperty(DEFAULTPLATFORM)));

				} else {
					testParameters
							.setBrowserVersion(method.getTestMethod().getXmlTest().getLocalParameters().get(PLATFORM));
				}

				break;

			case "TESTOBJECT":

			default:
				testParameters.setExecutionMode(ExecutionMode.valueOf(properties.getProperty("DefaultExecutionMode")));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get(BROWSERNAME) == null) {
					testParameters.setBrowser(
							Browser.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get(BROWSERNAME)));
				} else {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty(DEFAULTBROWSER)));
				}
			}

		} catch (Exception ex) {
			log.error(
					"Error at settings TestParameters , please check the TestNG XML suite File and pass valid key & values"
							+ ex.getMessage());
		}
	}

	private void isAPIExecution(String executionMode, SeleniumTestParameters testParameters) {

		testParameters.setAPIExecution(false);
		if (executionMode.equals(ExecutionMode.API.toString())) {
			testParameters.setAPIExecution(true);
		}
	}

}