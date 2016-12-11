package org.framework.selenium.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.framework.selenium.webdriver.WebdriverBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;


public class BasePage {
	/**
	 * @author praneeth
	 */
	/**
	 * Extent report 
	 */
	public static ExtentReports extent = new ExtentReports("D:\\extentReportSelenium\\googleResults.html",true);//Provide Desired Report Directory Location and Name;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception; 
	/*
	 * the properties file path
	 */
	protected final String PROPERTIESFILEPATH = "./src/resources/config.properties";
	/**
	 * Perform screen shot. default is <code>false</code>.
	 */
	private boolean doScreenshot = false;

	/**
	 * Default timeout for page loading in milliseconds (ms). Default is <code>50000</code> (50 seconds). 
	 */
	protected String timeout = "50000";
	protected long limit=2000;

	/**
	 * Selenium interface instance.
	 */
	protected WebDriver driver=null;
	/**
	 * Maximize Browser Window. Default is <code>true</code>.
	 */
	protected boolean maximizeBrowserWindow = true;

	protected String browser = "";
	protected String webUrl = "";
	protected final String DRIVERLOCATION = "./src/drivers/";
	@Test
	public void init(){
		WebdriverBrowser driverBrowser = new WebdriverBrowser();
		try{
			Properties prop = new Properties();

			InputStream inputData = new FileInputStream(PROPERTIESFILEPATH);
			prop.load(inputData);
			browser = prop.getProperty("browser");
			System.out.println("--Browser:"+browser);
			webUrl = prop.getProperty("url");
			System.out.println("--WebUrl:"+webUrl);
			this.driver = driverBrowser.getDriver(browser, DRIVERLOCATION);
			
			driver.manage().window().maximize();
			driver.get(webUrl);
			//Report Directory and Report Name
			//Initiate Extent Reports
			
			 /*// for its presence once every 5 seconds.
			   Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(30, SECONDS)
			       .pollingEvery(5, SECONDS)
			       .ignoring(NoSuchElementException.class);
			 
			   WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
			       return driver.findElement(By.id("foo"));
			     }
			   });*/
			
			ExtentTest test = extent.startTest("Initialized Browser and Url"); 
			test.log(LogStatus.PASS,"Initiated");
			
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}    
	public ExtentReports getExtentReportObject(){
		return extent;
	}
	public void tearDown(){
		extent.endTest(test);
		extent.flush();
		extent.close();  // close the Test Suite
		driver.close();
		driver.quit();
	}
	public WebDriver inheritSession(BasePage basePageObj){
		System.out.println("inherit session called");
		init();
		return getDriver();	
	}
	
	
	private WebDriver getDriver() {
		return driver;
		
	}
	public String pageTitleName(){
	
		return driver.getTitle();
	}
	public void click(String locatorType, String value) {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			element.click();
			
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to perform click" + e);
		}
	}
	public void inputText(String locatorType, String value, String textInput) {
		try {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			element.sendKeys(textInput);
			
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to perform click" + e);
		}
	}
	
	public By locatorValue(String locatorTpye, String value) {
		By by;
		switch (locatorTpye) {
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "css":
			by = By.cssSelector(value);
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		default:
			by = null;
			break;
		}
		return by;
	}



}
