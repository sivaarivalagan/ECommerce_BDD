package com.at.ecom.shopping.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.at.ecom.shopping.utility.Constants;
import com.at.ecom.shopping.utility.TestBase;

public class LoginPage extends TestBase {

	@FindBy(xpath = "//a[contains(text(),'Sign in')]")
	WebElement signIn;
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement userName;

	@FindBy(xpath = "//input[@id='passwd']")
	WebElement password;

	@FindBy(xpath = "//button[@id='SubmitLogin']")
	WebElement loginButton;

	@FindBy(xpath = "(//a[@title='T-shirts'])[2]")
	WebElement linkTshirt;
	
	@FindBy(xpath = "//a[@class='logout']")
	WebElement logoutButton;

	public LoginPage(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	
		
	public void entercred(String username, String pass) {
		
		waitforElement(driver, signIn);
		signIn.click();
		
		assertTrue(driver.getTitle().contains("Login - My Store"));
		
		waitforElement(driver, userName);
        userName.sendKeys(username);
        password.sendKeys(pass);
        loginButton.click();
        
        waitforElement(driver, linkTshirt);
        assertTrue(driver.getTitle().contains(Constants.TXTHOME));
        
    }
	
	public void logout() {
		pageRefresh(driver);
		waitforApexpageload();
		waitforElement(driver, logoutButton);
		logoutButton.click();
		waitforElement(driver, loginButton);
		loginButton.isDisplayed();
	}
	
}
