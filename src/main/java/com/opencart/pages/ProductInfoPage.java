package com.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.opencart.constants.AppConstants;
import com.opencart.utility.ElementUtil;

public class ProductInfoPage {

	WebDriver driver;
	ElementUtil eleutil;
	private By prodHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By cartSuccessMessg = By.cssSelector("div.alert.alert-success");


	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public String prodHeaderValue() {
		String prodHead = eleutil.waitForElementVisible(prodHeader, AppConstants.DEFAULT_LONG_TIME_OUT).getText();
		System.out.println("producct header :" + prodHead);
		return prodHead;
	}

	public int getProdImgCount() {
		int prodImages = eleutil.waitForElementsVisible(productImages, AppConstants.DEFAULT_LONG_TIME_OUT).size();
		System.out.println("product images count" + productImages);
		return prodImages;
	}
	public void enterQuantity(int quant){
		System.out.println("enter product quantity "+quant);
		eleutil.doSendKeys(quantity, String.valueOf(quant));
	}
	public String addToCartButton(){
		eleutil.waitForElementToBeClickable(AppConstants.DEFAULT_LONG_TIME_OUT, addToCartBtn).click();
		String successMsg = eleutil.waitForElementVisible(cartSuccessMessg, AppConstants.DEFAULT_LONG_TIME_OUT).getText();
		 return successMsg;
	}
	public String addCartToProduct(){
		eleutil.doClick(addToCartBtn);
		String sucMsg = eleutil.waitForElementVisible(cartSuccessMessg, AppConstants.DEFAULT_LONG_TIME_OUT).getText();
		StringBuilder sb = new StringBuilder(sucMsg);
		String msg = sb.substring(0, sucMsg.length()-1).replace("\n", "");
		System.out.println("cart success mesg"+msg);
		return msg;
	}

	public Map<String, String> getProductInfo() {
		Map<String, String> productInfo = new HashMap<String, String>();

		productInfo.put("productname", prodHeaderValue());
		getProductPriceData();
        getProductMetaData();
	    return productInfo;
	}
	
    public void getProductPriceData(){
		Map<String, String> productInfo = new HashMap<String, String>();

		List<WebElement> priceList = eleutil.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String extax = priceList.get(1).getText();
		String extaxValue = extax.split(":")[1].trim();

		productInfo.put("productprice", price);
		productInfo.put("prodExtaxValue", extaxValue);
	}

	public void getProductMetaData() {
		Map<String, String> productInfo = new HashMap<String, String>();

		List<WebElement> metaData = eleutil.getElements(productMetaData);
		for (WebElement e : metaData) {
			String meta = e.getText();
			String metaInfo[] = meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfo.put(key, value);
		}
	}
	

}
