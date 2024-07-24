package com.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver>tlDriver = new ThreadLocal<WebDriver>();


	public WebDriver initWebdriver(Properties prop ) {
		
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		System.out.println("browser name :" + prop);
		if (browserName.equalsIgnoreCase("chrome")) {
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			//driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
		} else if (browserName.equalsIgnoreCase("edge")) {
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		} else {
			System.out.println("please pass the valid browser");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}
	public synchronized static WebDriver getDriver(){
		return tlDriver.get();
	}

	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
            prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
