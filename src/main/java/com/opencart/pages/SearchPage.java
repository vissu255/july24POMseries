package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.constants.AppConstants;
import com.opencart.utility.ElementUtil;

public class SearchPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	private By searchProductResults = By.xpath("//div[contains (@class, 'product-layout')]");

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public int getProductCount() {
		int prodCount = eleutil.waitForElementsVisible(searchProductResults, AppConstants.DEFAULT_MEDIUM_TIME_OUT)
				.size();
		System.out.println("number of products " + prodCount);
		return prodCount;
	}

	public ProductInfoPage selectProduct(String productName) {
		By product = By.linkText(productName);
		eleutil.waitForElementVisible(product, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}
}
