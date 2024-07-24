package com.opencart.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.constants.AppConstants;
import com.opencart.utility.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By emailId = By.xpath("//input[@id ='input-email']");
	private By password = By.xpath("//input[@id='input-password']");
	private By lgnButton = By.xpath("//input[@value='Login']");
	private By forgetPassword = By.xpath("//div[@class='form-group']//a[text() ='Forgotten Password']");
	private By registerLink = By.linkText("Register");

	public LoginPage(WebDriver driver) {
           this.driver = driver;
           eleUtil = new ElementUtil(driver);
	}
	
	public String getLoginPageTitle(){
		String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Login page title :"+title);
	    return title;
	}
	public String getLoginPageURL(){
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTIONAL_VALUE);
		System.out.println("Login page URL :"+url);
		return url;
	}
	public boolean isForgetPasswordLinkExists(){
		boolean flag = eleUtil.waitForElementPresence(forgetPassword, AppConstants.DEFAULT_LONG_TIME_OUT).isDisplayed();
		return flag;
	}
	public AccountsPage doLogin(String mail, String pwd){
	    eleUtil.waitForElementVisible(emailId, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(mail);
        eleUtil.waitForElementVisible(password, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(pwd);
        eleUtil.waitForElementVisible(lgnButton, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
        return new AccountsPage(driver);
	}
	public RegisterPage navigateToRegisterPage(){
		eleUtil.doClick(registerLink);
	    return new RegisterPage(driver);
	}
}
