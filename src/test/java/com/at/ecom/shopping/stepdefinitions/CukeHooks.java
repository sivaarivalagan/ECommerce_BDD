package com.at.ecom.shopping.stepdefinitions;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.at.ecom.shopping.framework.DriverManager;
import com.at.ecom.shopping.framework.Settings;
import com.at.ecom.shopping.framework.TestHarness;
import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CukeHooks extends MasterStepDefs {

	static Logger log = Logger.getLogger(CukeHooks.class);

	static Properties propertiesFile = Settings.getInstance();
	private TestHarness testHarness;

	@Before
	public void setUp(Scenario scenario) {
		

		testHarness = new TestHarness();
		Reporter.addScenarioLog(scenario.getName());
		currentScenario = scenario;
		properties = propertiesFile;
		pauseScript(2);
		currentTestParameters = DriverManager.getTestParameters();
		currentTestParameters.setScenarioName(scenario.getName());
		testHarness.invokeDriver(scenario, currentTestParameters);
	}

	@After
	public void tearDown(Scenario scenario) throws IOException {

		testHarness.closeRespestiveDriver(currentTestParameters, scenario);
	}

}