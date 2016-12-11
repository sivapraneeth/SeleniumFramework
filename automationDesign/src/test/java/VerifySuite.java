
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class VerifySuite {
	//Declare Web Driver variables
	private WebDriver driver;
	private String baseurl;
	//Declare Extent Reports
	private static ExtentReports reports;

	@BeforeTest
	public void setup() throws IOException{
		//initialize chrome driver for application to run
		/*File ChromeDriver = new File("F:\\Java_Applications\\Zip Files\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver",ChromeDriver.getAbsolutePath());*/
		driver = new FirefoxDriver();
		baseurl="http://www.wikishown.com"; 

	}

	@Test
	public void VerifyHome() throws IOException{

		//Initiate Extent Reports
		reports = new ExtentReports("D:\\extentReportSelenium\\Testresults.html",true);

		//Declare Start test name
		ExtentTest test = reports.startTest("Verify Home page"); 

		//Maximize the window
		driver.manage().window().maximize();
		test.log(LogStatus.PASS,"Browser is open and window is Maximimzed.");

		//Open Specified url in the browser
		driver.get(baseurl);
		test.log(LogStatus.PASS,"Web application is opened in Browser.");


		//Get title of the Home page
		String title = driver.getTitle();

		//Verify page title with if condition 
		if(title.contains("Welcome to Future Bazaar - India's gifting bazaar")){

			test.log(LogStatus.PASS,"Home page title is displayed.");

		}else{
			test.log(LogStatus.FAIL,"Web application title is displayed.");
		} 

		//Ending the Test
		reports.endTest(test); 
		//writing everything to document
		reports.flush();
		driver.close(); 
	}
}