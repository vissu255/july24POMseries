package com.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.constants.AppConstants;
import com.opencart.utility.ElementUtil;

public class AccountsPage {
	WebDriver driver;
	ElementUtil elementUtil;
//	SearchPage searchPage;

	By logoutLink = By.xpath("//aside[@id='column-right']//a[text() ='Logout']");
	By search = By.name("search");
	By headerList = By.xpath("//div[@id='content']//h2");
	By searchButton = By.xpath("//div[@id = 'search']//button[@type = 'button']");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getAccountsPageTitle() {
		String accountsPageTitle = elementUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT,
				AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		System.out.println("Accounts Page Title :" + accountsPageTitle);
		return accountsPageTitle;
	}

	public String getAccountsPageURL() {
		String accountsPageURL = elementUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT,
				AppConstants.ACCOUNTS_PAGE_URL_FRACTIONAL_VALUE);
		System.out.println("Accounts Page URL :" + accountsPageURL);
		return accountsPageURL;
	}

	public boolean isLogoutLinkExists() {
		return elementUtil.waitForElementVisible(logoutLink, AppConstants.DEFAULT_LONG_TIME_OUT).isDisplayed();
	}

	public boolean isSearchExists() {
		return elementUtil.waitForElementVisible(search, AppConstants.DEFAULT_LONG_TIME_OUT).isDisplayed();
	}

	public List<String> getAccountsPageHeadersList() {
		List<WebElement> accheaderList = elementUtil.waitForElementsVisible(headerList,
				AppConstants.DEFAULT_LONG_TIME_OUT);
		ArrayList<String> accHeaderNamesList = new ArrayList<String>();
		for (WebElement e : accheaderList) {
			String list = e.getText();
			accHeaderNamesList.add(list);
		}
		return accHeaderNamesList;
	}

	public SearchPage performSearch(String searchKey) {
		if (isSearchExists()) {
			elementUtil.doSendKeys(search, searchKey);
			elementUtil.doClick(searchButton);

			return new SearchPage(driver);
		} else {
			System.out.println("search field is not present");
			return null;
		}

	}
}
