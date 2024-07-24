package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.constants.AppConstants;
import com.opencart.utility.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");

	private By registerSuccessMesg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver){
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	public boolean registerUser(String firstName, String lastName, String email, String telephone, String password, String subscribe){
		
		eleutil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(firstName);
		eleutil.waitForElementVisible(this.lastName, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(lastName);
		eleutil.waitForElementVisible(this.email, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(email);
		eleutil.waitForElementVisible(this.telephone, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(telephone);
		eleutil.waitForElementVisible(this.password, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(password);
		eleutil.waitForElementVisible(confirmpassword, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(password);
		
		if(subscribe.equalsIgnoreCase("yes")){
			eleutil.doClick(subscribeYes);
		}else{
			eleutil.doClick(subscribeNo);
		}
		eleutil.doActionsCick(agreeCheckBox);
		eleutil.doClick(continueButton);
		
		String successMessage  = eleutil.waitForElementVisible(registerSuccessMesg, AppConstants.DEFAULT_LONG_TIME_OUT).getText();
		System.out.println("user reg success mesg "+ successMessage);
		
		if(successMessage.contains(AppConstants.USER_REGISTRATION_SUCCESS_MESSAGE)){
			eleutil.doClick(logoutLink);
			eleutil.doClick(registerLink);
			return true;
		}
		return false;
	}
}
