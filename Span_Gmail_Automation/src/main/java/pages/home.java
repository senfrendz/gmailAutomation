package pages;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class home extends baseClass {
	
	public static String actualTitle; 
	public static String actualwindowTitle;
	public static WebDriver driver;

	static String gmailNavigation = baseClass.properties.getProperty("gmailNavigationLink");
	static String sendButton = baseClass.properties.getProperty("sendButton");
	static String passwordDetails = baseClass.properties.getProperty("passwordField");
	static String inboxButton = baseClass.properties.getProperty("inbox");
	static String invalidPasswordText = baseClass.properties.getProperty("invalidPasswordValue");
	
	public static String gmailWindowTitle = "https://mail.google.com/mail/u/0/#inbox";

	public static Logger log = Logger.getLogger(home.class);
	
	public static void launchGmail(WebDriver driver) {
		actualTitle = driver.getTitle();
	//  System.out.println(actualTitle);
		assertEquals(actualTitle, "Google");
		log.info("The Expected Title Google "+" and the actual Title "+ actualTitle +"are matched");
	}
	
	public static WebDriver windowHandler(WebDriver driver) {
		Set<String> windowDetails= driver.getWindowHandles();
		List<String> listWindows = new ArrayList<String>();
		listWindows.addAll(windowDetails);
		return driver.switchTo().window(listWindows.get(1));
		}
	
	public static void gmailNavigation(WebDriver driver) {
		driver.findElement(By.linkText("Gmail")).click();
		actualwindowTitle = driver.getTitle();
		assertEquals(actualwindowTitle, "Gmail - Email from Google");
		driver.findElement(By.linkText("Sign in")).click();
		windowHandler(driver);
		String windowTitle = driver.getTitle();
		assertEquals(windowTitle, "Gmail");
	}
	
	public static void credentialValidation(WebDriver driver,String userName,String passWord) throws InterruptedException {
		driver.findElement(By.id("identifierId")).sendKeys(userName);
		driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(passwordDetails)));
		password.sendKeys(passWord);
		driver.findElement(By.xpath(passwordDetails)).sendKeys(Keys.ENTER);
		log.info("The login process has been completed successfully");

		@SuppressWarnings("unused")
		WebDriverWait pageLoadWait = new WebDriverWait(driver, 25); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(inboxButton)));
		assertEquals(driver.getCurrentUrl(), gmailWindowTitle);
		log.info("The Expected Title "+ gmailWindowTitle+" and the actual Title"+ driver.getCurrentUrl() +"are matched");
	}
	
	public static void invalidCredentialValidation(WebDriver driver,String userName,String passWord) throws InterruptedException {
		String originalInvalidText = "Wrong password. Try again or click Forgot password to reset it.";
		driver.findElement(By.id("identifierId")).sendKeys(userName);
		driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(passwordDetails)));
		password.sendKeys(passWord+"invalid");
		driver.findElement(By.xpath(passwordDetails)).sendKeys(Keys.ENTER);
		log.info("The Password value"+ passWord +"and the actual password"+ passWord+"are invalid" +" not matched as well");
		
		@SuppressWarnings("unused")
		WebDriverWait pageLoadWait = new WebDriverWait(driver, 50); 
		WebElement invalidPass = pageLoadWait.until(ExpectedConditions.elementToBeClickable(By.xpath(invalidPasswordText)));
		String invalidPasswordVal = invalidPass.getText();

		assertEquals(invalidPasswordVal, originalInvalidText,"Failed - Please provide Valid Credits");
		log.info("The Password value"+ invalidPasswordVal +"and the actual password"+ originalInvalidText +"are not matched");
		log.info("User has to provide the valid credentials");

	}
}
