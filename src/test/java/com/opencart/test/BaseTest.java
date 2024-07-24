package com.opencart.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.opencart.factory.DriverFactory;
import com.opencart.pages.AccountsPage;
import com.opencart.pages.LoginPage;
import com.opencart.pages.ProductInfoPage;
import com.opencart.pages.RegisterPage;
import com.opencart.pages.SearchPage;
import com.opencart.utility.ElementUtil;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	Properties prop;
	LoginPage loginpage;
	AccountsPage accountspage;
	SearchPage searchPage;
	ProductInfoPage prodinfoPg;
	SoftAssert softAssert;
	RegisterPage registerPage;
	
	
	@BeforeTest
	public void setUp(){
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initWebdriver(prop);
		loginpage = new LoginPage(driver); 
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}

}
