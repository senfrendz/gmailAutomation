package testListener;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import testCases.TC02_mailSentValidtion_negativeScenario;

public class testListener implements ITestListener{

	public static WebDriver driver;

	public void onTestStart(ITestResult result) {
	//	extentReport.startReport();
	}

	public void onTestSuccess(ITestResult result) {
	//	extentReport.passTest();
	}

	public void onTestFailure(ITestResult result) {
		
		  EventFiringWebDriver event = new EventFiringWebDriver(TC02_mailSentValidtion_negativeScenario.driver); 
		  try {
		  File src = event.getScreenshotAs(OutputType.FILE); 
		  File DestFile=new File("screenshots\\fail.png"); 
		  FileUtils.copyFile(src, DestFile); }
		  catch(Exception e) {
		  }
		//  extentReport.failTest();
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
