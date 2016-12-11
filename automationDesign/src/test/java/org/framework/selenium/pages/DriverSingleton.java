package org.framework.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {
	public static WebDriver driver = null;
	
	public static void start(){
		if(driver == null)
			driver = new FirefoxDriver();
	}
}
