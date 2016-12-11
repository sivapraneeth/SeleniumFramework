package org.framework.selenium.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class WebdriverBrowser {


	/**
	 * @author praneeth
	 * 
	 * Driver instance will be created for browsers
	 * chrome
	 * IE
	 * Gecko
	 * Firefox
	 * Opera
	 * safari
	 * NOTE: User has to verify binary webdrivers available on particular locations except FIREFOX broser
	 * Default browser: FIREFOX
	 */


	/**
	 * WebdriverBroser Constructor
	 */
	public WebdriverBrowser(){

	}
	public WebDriver getDriver(String driverId, String driverLocation) throws Exception{
		driverId = driverId.toLowerCase();
		WebDriver driver = null;
		switch(driverId){
		case "firefox"	: 	final FirefoxProfile firefoxProfile = new FirefoxProfile();
	    					firefoxProfile.setPreference("xpinstall.signatures.required", false);
	    					driver = new FirefoxDriver(firefoxProfile);
							break;
		case "chrome"	: 	System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
							driver = new ChromeDriver();
							break;
		case "ie"		:	System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer.exe");
							break;
		case ""			:	driver = new FirefoxDriver();
							break;
		default 	: 	throw new Exception("Error: could not find a Selenium implementation for the webDriverId: " + driverId);
		}
		return driver;

	}
}
