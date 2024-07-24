package com.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ProductPageInfoTest extends BaseTest{
	
	@BeforeClass
	public void productInfoPageSetUp(){
		accountspage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	@DataProvider
	public Object[][] getProductImagesTestData(){
		return new Object[][]{
			{"MacBook","MacBook Pro",4},
			{"iMac","iMac",3},
			{"Apple","Apple Cinema 30\"",6},
			{"Samsung","Samsung SyncMaster 941BW",1},
		};
	}
	
	@Test(dataProvider="getProductImagesTestData")
	public void productImagesCountTest(String productName, String prodDetails, int prodCount){
		searchPage = accountspage.performSearch(productName);
		prodinfoPg = searchPage.selectProduct(prodDetails);
		int actImageCount = prodinfoPg.getProdImgCount();
		Assert.assertEquals(actImageCount, prodCount);
		
	}
	
	@Test
	public void productInfoTest(){
		searchPage = accountspage.performSearch("MacBook Pro");
		prodinfoPg = searchPage.selectProduct("MacBook Pro");
		Map<String, String>actProdInfo = prodinfoPg.getProductInfo();
//		softAssert.assertEquals(actProdInfo.get("Brand"), "Apple");
	//	softAssert.assertEquals(actProdInfo.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProdInfo.get("productname"), "MacBook Pro");
		//softAssert.assertEquals(actProdInfo.get("productprice"), "$2,000.00");
		
		softAssert.assertAll();
		
	}
	@DataProvider
	public Object[][] getCartTestData(){
		return new Object[][]{
			{"MacBook","MacBook Pro",1},
			{"iMac","iMac",2}
		};
	}
	@Test(dataProvider="getCartTestData")
	public void addToCartTest(String addProd, String selecProd, int quantity){
	     searchPage = accountspage.performSearch(addProd);
	     prodinfoPg = searchPage.selectProduct(selecProd);
	     prodinfoPg.enterQuantity(quantity);
	     String sucMessage = prodinfoPg.addCartToProduct();
	     Assert.assertEquals(sucMessage, "Success: You have added "+selecProd+" to your shopping cart!");
	}

}
