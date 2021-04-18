package com.at.ecom.shopping.runners;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.at.ecom.shopping.framework.Settings;
import com.at.ecom.shopping.framework.TimeStamp;
import com.at.ecom.shopping.framework.Util;
import com.at.ecom.shopping.utility.TestBase;
import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import com.github.mkolisnyk.cucumber.reporting.CucumberDetailedResults;
import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;


@ExtendedCucumberOptions(jsonReport = "./target/cucumber-report/Regresssion/cucumber.json", jsonUsageReport = "./target/cucumber-report/Regresssion/cucumber-usage.json", outputFolder = "./target/cucumber-report/Regresssion", detailedReport = true, detailedAggregatedReport = true, overviewReport = true, usageReport = true)

/**
 * Please notice that com...stepDefinations.CukeHooks class is in
 * the same package as the steps definitions. It has two methods that are
 * executed before or after scenario. I'm using to take a screenshot if scenario
 * fails.
 */

/* Feature file is modified by Chiranjeevi*/

@CucumberOptions(features = "./src/test/resources/features", glue = { "com.at.ecom.shopping.stepdefinitions" }, tags = {
		"@ECommerce"}, monochrome = true, plugin = { "pretty", "pretty:./target/cucumber-report/Regresssion/pretty.txt",
				"html:./target/cucumber-report/Regresssion", "json:./target/cucumber-report/Regresssion/cucumber.json",
				"junit:./target/cucumber-report/Regresssion/cucumber-junitreport.xml",
				"com.cucumber.listener.ExtentCucumberFormatter:" })

public class RunCucumberTestRegressionLocal extends TestBase {

	static String resultFolder;
	Properties properties = Settings.getInstance();

	@BeforeSuite
	private void beforeSuite() {
		if ((Boolean.parseBoolean(properties.getProperty("SaveReports")))) {
			resultFolder = TimeStamp.getInstance();
		}
	}

	@BeforeClass
	private void beforeClass() {

		ExtentProperties extentProperties = ExtentProperties.INSTANCE;
		extentProperties.setReportPath("./target/ExtentReport/TestExecutionReport.html");
		new File("./target/ExtentReport/screenshots").mkdir();
		new File("./target/RunTimeDataLogs").mkdir();
	}

	@AfterClass
	private void afterClass() {
		Properties prop = Settings.getInstance();
		Reporter.loadXMLConfig(new File("./src/test/resources/extent-config.xml"));
		Reporter.setSystemInfo("Project Name", prop.getProperty("ProjectName"));
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setSystemInfo("os", System.getProperty("os.name"));
	}

	@AfterTest
	private void afterTest() {
		generateCustomReports();
		//logsSave();
	}

	@AfterSuite()
	private void afterSuite() {
		if ((Boolean.parseBoolean(properties.getProperty("SaveReports")))) {
			copyReportsFolder();			
		}
	}

	private void generateCustomReports() {

		CucumberResultsOverview results1 = new CucumberResultsOverview();
		results1.setOutputDirectory("./target/cucumber-report/Regresssion");
		results1.setOutputName("cucumber-results");
		results1.setSourceFile("./target/cucumber-report/Regresssion/cucumber.json");
		try {
			results1.executeFeaturesOverviewReport();
		} catch (Exception e) {
			e.printStackTrace();
		}

		CucumberDetailedResults detailedResults = new CucumberDetailedResults();
		detailedResults.setOutputDirectory("./target/cucumber-report/Regresssion");
		detailedResults.setOutputName("cucumber-results");
		detailedResults.setSourceFile("./target/cucumber-report/Regresssion/cucumber.json");
		detailedResults.setScreenShotLocation("./Regresssion");
		try {
			detailedResults.executeDetailedResultsReport(false, false);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void copyReportsFolder() {

		File sourceCucumber = new File(Util.getTargetPath());
		File sourceExtent = new File(Util.getTargetExtentReportPath());
		File sourceDataLogs = new File(Util.getTargetCaptureLogsPath()); 
		File destination = new File(resultFolder);
		try {
			FileUtils.copyDirectory(sourceCucumber, destination);
			FileUtils.copyDirectory(sourceExtent, destination);
			FileUtils.copyDirectory(sourceDataLogs, destination);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}