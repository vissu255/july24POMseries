package com.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.pages.SearchPage;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup(){
		accountspage = loginpage.doLogin("viswanathbuchi@gmail.com", "9347273161");
	}
	@Test
	public void accPageTitleTest(){
		String actualAccPageTitle = accountspage.getAccountsPageTitle();
		Assert.assertEquals(actualAccPageTitle, "My Account");
	}
	@Test
	public void accPageURLTest(){
		String actualAccPageURL = accountspage.getAccountsPageURL();
		Assert.assertTrue(actualAccPageURL.contains("route=account/account"));
	}
	@Test
	public void logoutLinkExistsTest(){
		Assert.assertTrue(accountspage.isLogoutLinkExists());
	}
	@Test
	public void searchExistsTest(){
		Assert.assertTrue(accountspage.isSearchExists());
	}
	@Test
	public void accPageHeadersTest(){
		List<String>accPageHeadersLis = accountspage.getAccountsPageHeadersList();
		Assert.assertEquals(accPageHeadersLis.size(), 4);
	}
	@DataProvider
	public Object[][] getProductData(){
		return new Object[][]{
			{"MacBook"},
			{"Apple"},
			{"iMac"},
			{"Samsung"}
		};
	}
	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey){
		searchPage = accountspage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getProductCount()>0);
	}
	@DataProvider
	public Object[][] getProductTestData(){
		return new Object[][]{
			{"MacBook", "MacBook Air"},
			{"MacBook", "MacBook Pro"},
			{"Apple","Apple Cinema 30\""},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Samsung", "Samsung Galaxy Tab 10.1"},
			
		};
	}
	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String searchKey, String searchProd){
		searchPage = accountspage.performSearch(searchKey);
		if(searchPage.getProductCount()>0){
			prodinfoPg = searchPage.selectProduct(searchProd);
			String actualProdHeader = prodinfoPg.prodHeaderValue();
			Assert.assertEquals(actualProdHeader, searchProd);
		}
	}
}
