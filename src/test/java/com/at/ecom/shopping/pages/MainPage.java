package com.at.ecom.shopping.pages;
import static org.testng.Assert.assertTrue;
import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.at.ecom.shopping.utility.Constants;
import com.at.ecom.shopping.utility.TestBase;

public class MainPage extends TestBase {

	static Logger mainPageLog = Logger.getLogger(MainPage.class.getName());
	Map<String, String> testdata = null;
	private static String runtimeItem = "";

    @FindBy(xpath = "//a[@class='account']//span")
    WebElement currentLoggedUserName;

    @FindBy(xpath = "//form[@id='searchbox']//button[@type='submit']")
    WebElement searchBoxSubmit;

    // Categories
    @FindBy(xpath = "(//li//a[@title='T-shirts' and not(img)])[2]")
    WebElement subMenuTshirts;

    @FindBy(xpath = "//span[@class='cat-name']")
    WebElement subMenuChosenCategory;

    @FindBy(xpath = "//p[@id='add_to_cart']//span[text()='Add to cart']")
    WebElement addToCart;
   
    @FindBy(xpath = "//a[@class='btn btn-default button button-medium']")
    WebElement btnPrceedTocheckOut;

    @FindBy(xpath = "(//span[contains(text(),'Proceed to checkout')]//i[@class='icon-chevron-right right'])[2]")
    WebElement btnPrceedTocheckOutCart;

    @FindBy(xpath = "//input[@name='cgv']")
    WebElement chkIAgree;

    @FindBy(xpath = "//a[@class='bankwire']")
    WebElement btnBankWire;

    @FindBy(xpath = "(//span[contains(text(),'I confirm my order')]//i[@class='icon-chevron-right right'])")
    WebElement btnIConfirmMyOrder;

    @FindBy(xpath = "//strong[text()='Your order on My Store is complete.']")
    WebElement lblOrderIsComplete;

    
    // Personal Information Section
    @FindBy(xpath = "//span[contains(text(),'My personal information')]")
    WebElement btnPersonalInfo;

    @FindBy(xpath = "//input[@id='firstname']")
    WebElement txtFirstName;

    @FindBy(xpath = "//span[text()='Save']/i")
    WebElement btnSave;

    @FindBy(xpath = "//p[contains(text(),'Your personal information has been successfully up')]")
    WebElement lblInfoSaved;

    @FindBy(xpath = "//input[@id='old_passwd']")
    WebElement txtOldPasswrd;

  
  
    
	public MainPage(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	public void navigateToShirts() {
		waitforElement(driver, subMenuTshirts);
		jsClick(driver, subMenuTshirts);
		log("T-Shirt Page clicked");	
		
		waitforElement(driver, subMenuChosenCategory);
		assertTrue(subMenuChosenCategory.getText().contains(Constants.TXTTSHIRTS));
	}
	
	public void navigateToPersonalInfo() {
		waitforElement(driver, btnPersonalInfo);
		jsClick(driver, btnPersonalInfo);
		log("Personal Information Page clicked");	
		
		waitforElement(driver, txtFirstName);
		assertTrue(txtFirstName.isDisplayed());
	}
	
	public void updateFirstName() {
		waitforElement(driver, txtFirstName);
		txtFirstName.clear();
		String newName = randomString(4); 
		txtFirstName.sendKeys(newName);
		
		txtOldPasswrd.clear();
		txtOldPasswrd.sendKeys("testuser01");
		
		jsClick(driver, btnSave);
		
		waitforElement(driver, lblInfoSaved);
		
		assertTrue(lblInfoSaved.isDisplayed());
		
		pageRefresh(driver);
		
		waitforElement(driver, currentLoggedUserName);

		log("First name is updated");	
	}
	
	public void addingAProductToCart(Map<String, String> testdata) {
		
		
		runtimeItem = testdata.get(Constants.ITEM);
		WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'"+runtimeItem+"')]"));
		
		ele.click();
		waitforElement(driver, addToCart);
		
		addToCart.click();
		waitforElement(driver, btnPrceedTocheckOut);
		assertTrue(btnPrceedTocheckOut.isDisplayed());
		
		log("User is able to Add product to the cart");
	}
	
	public void checkOutAndOrder() {
		
		
		btnPrceedTocheckOut.click();
		
		waitforElement(driver, btnPrceedTocheckOutCart);
		btnPrceedTocheckOutCart.click();
		
		waitforElement(driver, btnPrceedTocheckOutCart);
		btnPrceedTocheckOutCart.click();
		
		waitforElement(driver, btnPrceedTocheckOutCart);

		jsClick(driver, chkIAgree);
		
		btnPrceedTocheckOutCart.click();
		
		waitforElement(driver, btnBankWire);
		btnBankWire.click();
		
		waitforElement(driver, btnIConfirmMyOrder);
		btnIConfirmMyOrder.click();
		
		waitforElement(driver, lblOrderIsComplete);
		
		assertTrue(lblOrderIsComplete.isDisplayed());
		
		log("User is able to Complete the order");
	}
}
