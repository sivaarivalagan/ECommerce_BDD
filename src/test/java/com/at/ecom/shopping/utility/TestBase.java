package com.at.ecom.shopping.utility;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/*
Author : siva
Email : sivasurya.prasand@qualitestgroup.com
Functionality : Reusable methods across execution level
*/

public abstract class TestBase extends AbstractTestNGCucumberTests {

	protected WebDriver driver;
	Logger logger = Logger.getLogger(this.getClass());
	private String log4jpath = "C:/Jenkins/workspace/SFDC/AT-SFDC-Regression/SFDC_Cucumber/src/test/resources/log4j.properties";
	File textfile = new File("./target/RunTimeDataLogs/Testdatalogs.txt");
	/*
	 * Method : To initiate Logs
	 */

	public void initiateLogs() {

		PropertyConfigurator.configure(log4jpath);
	}

	/**
	 * Function to verify whether the specified textfile exists Returns only true or
	 * false, you need to keep Asserts in script accordingly By default returns true
	 * if the text file is present
	 */

	/*
	 * Method : Wait until the element get visible under the page
	 */

	public void waitforElement(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 150);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	/**
	 * Function to verify whether the specified object exists Returns only true or
	 * false, you need to keep Asserts in script accordingly By default returns true
	 * if the element is present
	 */
	public boolean isElementPresent(WebDriver driver, WebElement element) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 4);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void jsClick(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

	}

	/*
	 * Method : Wait until the text get visible under the page
	 */
	public void waitForTextToAppear(WebDriver driver, String textToAppear, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
	}

	/*
	 * Method : Wait until the element turns into click able mode under the page
	 */

	public void waitUntillElementClickable(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 150);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		wait.until(ExpectedConditions.invisibilityOf(element));

	}

	/*
	 * Method : it will wait until the desired element get invisible (until the
	 * element vanish)
	 */

	public void waitUntillElementInvisible(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 150);
		wait.until(ExpectedConditions.invisibilityOf(element));

	}

	/*
	 * Method : Page get Refresh
	 */

	public void pageRefresh(WebDriver driver) {
		driver.navigate().refresh();
		waitForPageToLoad(driver);
	}

	/*
	 * Method : Navigation back from original page
	 */

	public void pageNavigateBack(WebDriver driver) {
		driver.navigate().back();
		waitForPageToLoad(driver);
	}

	/**
	 * @category Statement will wait to execute until page to load is complete
	 */
	public void waitForPageToLoad(WebDriver driver) {
		try {
			Object result = ((JavascriptExecutor) driver).executeScript("return document['readyState']");
			while (!result.toString().equalsIgnoreCase("complete")) {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				result = ((JavascriptExecutor) driver).executeScript("return document['readyState']");
			}
		} catch (Exception e) {
			log("unable to Load the DOM Elements " + e);
		}
	}

	/*
	 * Method : Navigation forward from original page
	 */

	public void pageNavigateForword(WebDriver driver) {
		driver.navigate().forward();
	}

	/*
	 * Method : To Handle the drop downs, method is to select the value from drop
	 * down list through value reference
	 */

	public void selectDropDownbyValue(WebElement element, String value) {
		WebElement mySelectElement = element;
		Select sel = new Select(mySelectElement);
		sel.selectByValue(value);
	}

	/*
	 * Method : To Handle the drop downs, method is to select the value from drop
	 * down list through index reference
	 */

	public void selectDropDownbyIndex(WebElement element, int index) {
		WebElement mySelectElement = element;
		Select sel = new Select(mySelectElement);
		sel.selectByIndex(index);
	}

	/*
	 * Method : To generate Random String value
	 */

	public String randomString(int count) {
		return RandomStringUtils.randomAlphabetic(count);

	}

	/*
	 * Method : To generate Random integer value
	 */

	public String randomInteger(int count) {
		return RandomStringUtils.randomNumeric(count);

	}

	/*
	 * Method : To generate Random Alpha Numeric value
	 */

	public String randomAlphaNumeric(int count) {
		return RandomStringUtils.randomAlphanumeric(count);

	}

	/*
	 * Method : To generate system current date
	 */

	public String machineTimeStamp() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("_yyyy/MM/dd_HHmmss");
		LocalDateTime now = LocalDateTime.now();
		String todaydate = dtf.format(now);
		log("Capture Time is :" + todaydate);
		return todaydate;
	}

	/*
	 * Method : Method is to wait until the frame get load
	 */

	public WebElement waituntilframeload(WebDriver driver, WebElement element, long timeOutInSeconds,
			String frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		return element;

	}

	/*
	 * Method : Handling the scrolling feature, Method will do scroll until the
	 * element visible
	 */

	public void scrollToElement(WebDriver driver, WebElement elemet) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elemet);

	}

	/*
	 * Method : Handling the scrolling feature, Method will do scroll down the page
	 * 
	 */

	public void scrollDown(WebDriver driver, int down) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight," + down + ")");

	}

	/*
	 * Method : Handling the scrolling feature, Method will do scroll up the page
	 * 
	 */

	public void scrollUp(WebDriver driver, int up) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight,-" + up + ")");

	}

	/*
	 * Method : Handling the scrolling feature, Method will do scroll left to the
	 * page
	 * 
	 */

	public void scrollLeft(WebDriver driver, int left) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(-" + left + ",0)", "");
	}

	/*
	 * Method : Handling the scrolling feature, Method will do scroll right to the
	 * page *
	 */

	public void scrollRight(WebDriver driver, int right) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(" + right + ",0)", "");

	}
	
	/*
	 * Method : Method to double click the desired element
	 */

	public void doubleclick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).doubleClick().perform();
	}

	/*
	 * Method : Mouse hover to an element
	 */

	public void mouseHoverToElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	
	/** 
	 * @category Click an element using mouse actions
	 * @param web element
	 */
	public void mouseClick(WebDriver driver, WebElement element){
		if ((driver != null) && (element != null))
		{
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.moveToElement(element).click().build().perform();
		}
	}

	/*
	 * Method : Handling the frame feature, Method to switch the frame based on
	 * element mapping
	 */

	public void switchtoframeByWebElement(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/*
	 * Method : Handling the frame feature, Method to switch the frame based on
	 * index/value reference
	 */

	public void iframeswitch(WebDriver driver, int i) {

		driver.switchTo().frame(i);
	}

	/*
	 * Method : To switch back original reference index/value reference
	 */

	public void switchtoDefault() {
		driver.switchTo().defaultContent();
	}

	public void waitforApexpageload() {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			log("Page Exception : Unable to load page properly : " + e);
		}
	}

	public void waitinSec(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (Exception e) {
			log("Page Exception : Wait time : " + e);
		}
	}

	/*
	 * Method : Open link in a new tab
	 */

	public void openLinkInNewTab(WebElement element) {
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
		element.sendKeys(selectLinkOpeninNewTab);
	}

	public void log(String info) {
		logger.info(info);
	}

	public Map<String, String> excelReader(String moduleName, String sheetname) throws Exception {

		String excelpath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ApplicationTestData.xlsx";
		String filePath = excelpath;
		String expectedDataTCName = moduleName;

		File srcsan = new File(filePath);
		FileInputStream fisan = new FileInputStream(srcsan);
		XSSFWorkbook wbsan = new XSSFWorkbook(fisan);
		XSSFSheet sheetsan1 = wbsan.getSheet(sheetname); // get the data based on sheet name
		// XSSFSheet sheetsan1 = wbsan.getSheetAt(0); //index no can also be used

		int totalRow = sheetsan1.getLastRowNum() + 1;
		int totalColumn = sheetsan1.getRow(0).getLastCellNum();

		String[][] arr = new String[totalColumn][2];

		if (totalRow != 0) {

			for (int j = 0; j < totalColumn; j++) {
				String cellVal = sheetsan1.getRow(0).getCell(j).getStringCellValue();
				arr[j][0] = cellVal;
			}
		} else {
			log("In Testdata.xls - Rows not found");
		}

		for (int i = 0; i < totalRow; i++) {
			String value = sheetsan1.getRow(i).getCell(0).getStringCellValue();
			if (value.equals(expectedDataTCName)) {
				for (int j = 0; j < totalColumn; j++) {
					String cellVal = sheetsan1.getRow(i).getCell(j).getStringCellValue();
					arr[j][1] = cellVal;
				}
			}
		}

		HashMap<String, String> testDataValue = new HashMap<String, String>();
		for (int i = 0; i <= totalColumn - 1; i++) {
			testDataValue.put(arr[i][0], arr[i][1]);
		}

		wbsan.close();
		return testDataValue;

	}

	public boolean selectDropDrownListFromulList(WebDriver driver, WebElement element, String value) {
		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			WebElement mySelectElement = element;

			if (mySelectElement != null) {
				List<WebElement> options = mySelectElement.findElements(By.tagName("li"));

				for (WebElement option : options) {
					if (option.getText().equals(value)) {
						option.click(); // click the desired option
						break;
					}
				}
			}
		} catch (NoSuchElementException e) {
			log("No Such Element Exception found exception: " + e);
		} catch (Exception e) {
			log("Unable to find Expected Element in DOM level : " + e);
		}

		return false;
	}

	public void selectValueFromaTag(WebDriver driver, WebElement element, String value) {
		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			WebElement mySelectElement = element;

			if (mySelectElement != null) {
				List<WebElement> options = mySelectElement.findElements(By.tagName("a"));
				for (WebElement option : options) {

					if (option.getText().equals(value)) {
						option.click(); // click the desired option
						break;
					}
				}
			}
		} catch (NoSuchElementException e) {
			log("No Such Element Exception found : " + e);
		} catch (Exception e) {
			log("Unable to find Expected Element in DOM : " + e);
		}

	}

	public void selectValueFromListofElements(WebDriver driver, String xpath, String value) {

		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			List<WebElement> ele = driver.findElements(By.xpath(xpath));
			for (WebElement elementvalue : ele) {
				if (elementvalue.getText().contains(value)) {
					elementvalue.click();
					break;
				}
			}

		} catch (NoSuchElementException e) {
			log("No Such Element Exception found : " + e);
		} catch (Exception e) {
			log("Unable to find Expected Element in DOM : " + e);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean waitUntilElementNotDisplayed(WebDriver driver, final WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver arg0) {
				try {
					webElement.isDisplayed();
					return false;
				} catch (NoSuchElementException e) {
					return true;
				} catch (StaleElementReferenceException f) {
					log("Element not found as expected");
					return true;
				}
			}
		};
		wait.until(elementIsDisplayed);
		return true;
	}

	public boolean isDisplayed(final WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Function to verify whether the specified object exists Returns only true or
	 * false, you need to keep Asserts in script accordingly By default returns true
	 * if the element is present
	 */

	public String calanderHandlerPickFuture(int days) {

		LocalDate date = LocalDate.now().plusDays(days);

		int month = date.getMonthValue();
		int year = date.getYear();
		int day = date.getDayOfMonth();
		return (month + "/" + day + "/" + year);
	}

	public String calanderHandlerPickFutureDDMMYYY(int days) {

		LocalDate date = LocalDate.now().plusDays(days);

		int month = date.getMonthValue();
		int year = date.getYear();
		int day = date.getDayOfMonth();
		return (day + "/" + month + "/" + year);
	}

	public boolean staleElementHandlerToClick(WebDriver driver, WebElement element) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 5) {
			try {
				element.click();
				result = true;
				break;
			} catch (Exception e) {
				logger.info("Element has become stale");
			}
			attempts++;
		}
		return result;
	}
}