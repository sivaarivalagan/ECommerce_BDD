package com.at.ecom.shopping.stepdefinitions;

import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.at.ecom.shopping.framework.DriverManager;
import com.at.ecom.shopping.framework.Util;
import com.at.ecom.shopping.pages.MainPage;
import com.at.ecom.shopping.utility.Constants;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;

public class MainPageStepDefs extends MasterStepDefs {

	static Logger accountsstepdef = Logger.getLogger(MainPageStepDefs.class);
	MainPage mainpage;
	WebDriver mainPageDriver = DriverManager.getWebDriver();
	Map<String, String> testdata = null;
	DataTable table = null;

	public void mainPageHooks() {
		mainpage = new MainPage(mainPageDriver);
		try {
			testdata = excelReader("Tshirt", "Product");
		} catch (Exception e) {
			log("TestData File not found : error message : " + e);
		}

	}
	
	@Then("^user adds a product to the cart$")
	public void addProductToCart() {

		mainPageHooks();
		mainpage.navigateToShirts();
		log("User navigated to T-Shirts Section");
		mainpage.addingAProductToCart(testdata);

		log("User is able to select the product");
		currentScenario.embed(Util.takeScreenshot(mainPageDriver), Constants.IMAGE);

	}
	
	@Then("^checks out and Order the product$")
	public void checkOutAndOrder() {

		mainPageHooks();
		mainpage.checkOutAndOrder();
		log("User is able to complete Ordering the product");
		currentScenario.embed(Util.takeScreenshot(mainPageDriver), Constants.IMAGE);

	}
	
	@Then("^navigate to Personal Info page$")
	public void navigateToPersonalInfo() {

		mainPageHooks();
		mainpage.navigateToPersonalInfo();
		log("User navigated to Persona information page");
		currentScenario.embed(Util.takeScreenshot(mainPageDriver), Constants.IMAGE);

	}

	
	@Then("^Update the first name$")
	public void updateFirstName() {

		mainPageHooks();
		mainpage.updateFirstName();
		log("First name is updated");
		currentScenario.embed(Util.takeScreenshot(mainPageDriver), Constants.IMAGE);

	}
}