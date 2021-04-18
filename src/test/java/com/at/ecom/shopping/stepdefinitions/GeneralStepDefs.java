package com.at.ecom.shopping.stepdefinitions;

/*
 * Author : siva 
 * Functinality : Launch URL
 *  
 */

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import com.at.ecom.shopping.framework.DriverManager;
import com.at.ecom.shopping.framework.Util;
import com.at.ecom.shopping.utility.Constants;

import cucumber.api.java.en.Given;

public class GeneralStepDefs extends MasterStepDefs {

	static Logger log = Logger.getLogger(GeneralStepDefs.class);

	WebDriver driver = DriverManager.getWebDriver();
	int width = 15000;
	int height = 12500;
	Dimension dimension;
	
	// Comment :: Added Dimension to eliminate screen Resolution 
	
	@Given("^user launch the e-commerce application$")
	public void launchApplication() {
		log.info("E-Commerce Application is about to Launch");
		driver.get(properties.getProperty("ApplicationUrl"));
		log.info("E-Commerce Application sucessfully Launched");
		dimension = new Dimension(width, height);
		driver.manage().window().setSize(dimension);
		assertTrue(driver.getTitle().contains("My Store"));
		log.info("Page Title is successfully displayed and validated");
		
		currentScenario.embed(Util.takeScreenshot(driver),Constants.IMAGE);
	}
}