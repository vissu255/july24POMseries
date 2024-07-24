package com.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest{
	
	@Test(priority = 1)
	public void loginPageTitleTest(){
		String loginPageActualTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(loginPageActualTitle, "Account Login");
	}
	@Test(priority = 2)
	public void loginPageURLTest(){
		String loginPageActualURL = loginpage.getLoginPageURL();
		Assert.assertTrue(loginPageActualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTIONAL_VALUE));
	}
	@Test(priority = 3)
	public void isForgetPwdLinkExistsTest(){
	       Assert.assertTrue(loginpage.isForgetPasswordLinkExists());
	}
	@Test(priority = 4)
	public void loginTest(){
		accountspage =loginpage.doLogin("viswanathbuchi@gmail.com", "9347273161");
		Assert.assertTrue(accountspage.isLogoutLinkExists());
	}

}
