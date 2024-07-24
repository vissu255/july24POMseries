package com.opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.constants.AppConstants;
import com.opencart.utility.ExcelUtil;

public class RegisterPagetest extends BaseTest{
	
	@BeforeClass
	public void regPagesetUp(){
		registerPage = loginpage.navigateToRegisterPage();
	}

	public String getRandomEmail(){
		Random random = new Random();
		
		String email = "automation"+System.currentTimeMillis()+"@gmail.com";
		
		return email;
	}
	@DataProvider
	public Object[][] getRegTestData(){
		 Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
			return regData;
		}
	@Test(dataProvider="getRegTestData")
	public void userRegTest(String firstName, String lastName, String email, String telephone, String password, String subscribe){
           Assert.assertTrue(registerPage.registerUser(firstName, lastName, email, telephone, password, subscribe));		
	}
	}

