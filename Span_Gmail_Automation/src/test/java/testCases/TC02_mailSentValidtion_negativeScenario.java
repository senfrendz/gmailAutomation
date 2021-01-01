package testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import dataParse.readExcel;
import pages.baseClass;
import pages.emailSentVerification;
import pages.home;
import pages.mailCompose;

public class TC02_mailSentValidtion_negativeScenario extends baseClass  {
	
	public static WebDriver driver;
	
	public static Logger log = Logger.getLogger(TC01_mailSentValidtion_positiveScenario.class);

	ExtentReports extent;
	ExtentHtmlReporter htmlReporter;
	ExtentTest testCase;
	ExtentTest logger;

	
	@DataProvider
	public Iterator<Object[]> testDataParse() throws IOException {
			ArrayList<Object[]> excelData= readExcel.readFromExcel();
		return excelData.iterator();
	}
	
	@BeforeTest
	public void configExtentReport() {
		 extent = new ExtentReports();
		 extent
         .setSystemInfo("User Name", "Senthil Raju");
		 htmlReporter = new ExtentHtmlReporter("ExtentReportFail.html");
		extent.attachReporter(htmlReporter);	
	}
	
	
	@BeforeMethod
	public void Setup() {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.google.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test(dataProvider="testDataParse")
	public void launch(String userName,String passWord,String receiverEmailAddress,String emailSubject,String mailDraft) throws InterruptedException {
		testCase = extent.createTest("Gmail - Mail Sent Functionality - Negative Scenario");
		home.launchGmail(driver);
		home.gmailNavigation(driver);
		home.invalidCredentialValidation(driver,userName,passWord);
		mailCompose.mailDraft(driver,receiverEmailAddress,emailSubject,mailDraft);
		emailSentVerification.sentMailValidation(driver,emailSubject);
		Assert.assertTrue(false);
		log.error("TC02_mailSentValidation_negativeScenario Failed");	
		}
	
	@AfterTest
	void tearDown() {
		extent.flush();
		driver.quit();
	}

}
