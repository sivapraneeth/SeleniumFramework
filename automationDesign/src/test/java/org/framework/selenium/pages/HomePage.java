package org.framework.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.framework.resource.*;
import com.relevantcodes.extentreports.LogStatus;


public class HomePage  {
	
	BasePage basePageObj = null;
	WebDriver driver = null;
	ExcelOperations excelop = new ExcelOperations();
	@FindBy(id = "data")
	WebElement dataEle;
	
	/*public HomePage() {
		basePageObj = new BasePage();
		driver = inheritSession(basePageObj);
		System.out.println(" Default Constructor called");
		//test.log(LogStatus.PASS, "Step1 passed");	
	}*/
	@BeforeTest
	public void driverGetter() {
		DriverSingleton.start();
		DriverSingleton.start();
	}
	@Test
	public void fisrtMethod(){
		DriverSingleton.driver.get("https://www.google.com");
	}
	
	/*public void getTitle(){
		System.out.println("Home Page Title: "+pageTitleName());
		//test.log(LogStatus.PASS, "Page Title verified");
	}
	
	public void tearDown(){
		driver.close();
		driver.quit();
	}

	public void setText(String locator, String element, String InputData) {
		//locatorValue(locator, element);
		inputText(locator, element, InputData);
	}

	public void firstNameInput(String fName) {
		click("xpath", "//input[@type='text'][@name='firstname']");
		inputText("xpath", "//input[@type='text'][@name='firstname']", fName );
	}
	
	public void surnameInput(String sName){
		click("xpath", "//input[@name='lastname']");
		inputText("xpath", "//input[@name='lastname']", sName );
	}*/
}

