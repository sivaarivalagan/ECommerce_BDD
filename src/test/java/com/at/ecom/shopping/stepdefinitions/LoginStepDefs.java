package com.at.ecom.shopping.stepdefinitions;

import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.When;

import com.at.ecom.shopping.framework.DriverManager;
import com.at.ecom.shopping.framework.Settings;
import com.at.ecom.shopping.framework.Util;
import com.at.ecom.shopping.pages.*;

public class LoginStepDefs extends MasterStepDefs {

	private static Properties properties = Settings.getInstance();

	WebDriver loginDriver = DriverManager.getWebDriver();

	@When("^user able to login the application$")
	public void userAbleToLogin() {
		
		LoginPage login = new LoginPage(loginDriver);
		login.entercred("testuser01@gmail.com", "testuser01");

		log.info("Logged into the E-commerce applicaiton as successfully");
		currentScenario.embed(Util.takeScreenshot(loginDriver), "image/png");
	}


	@When("^logout the Application$")
	public void iLogout() {
		LoginPage login = new LoginPage(loginDriver);
		login.logout();
		log.info("Logged out the e-commerce applicaiton successfully");
		currentScenario.embed(Util.takeScreenshot(loginDriver), "image/png");
	}
}
