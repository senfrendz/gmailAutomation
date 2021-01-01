package pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class mailCompose extends baseClass{
	
	public static String maximize = baseClass.properties.getProperty("popOut");
	public static String receiverMail = baseClass.properties.getProperty("To");
	public static String mailSubject = baseClass.properties.getProperty("Subject");
	public static String messageBody = baseClass.properties.getProperty("Message");
	public static String sendButton = baseClass.properties.getProperty("Send");
	public static String Compose = baseClass.properties.getProperty("mailCompose");
	public static Logger log = Logger.getLogger(mailCompose.class);

	public static WebDriver driver;
	
	public static void mailDraft(WebDriver driver,String receiverEmailAddress,String emailSubject,String mailDraft) {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.MINUTES);
	//	System.out.println(Compose);
		driver.findElement(By.xpath(Compose)).click();
		log.info("Clicked : Mail Compose");
		driver.findElement(By.xpath(maximize)).click();
		log.info("Clicked : Maximize Button");

		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement receiver = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(receiverMail)));
		receiver.sendKeys(receiverEmailAddress);
		log.info("Captured : Recepient Mail Address");

		
		WebDriverWait waitSubj = new WebDriverWait(driver, 40);
		WebElement emailSubj = waitSubj.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mailSubject)));
		emailSubj.sendKeys(emailSubject);
		log.info("Captured :  Mail Subject");


		WebDriverWait waitMailDraft = new WebDriverWait(driver, 40);
		WebElement waitMailBodyDraft = waitMailDraft.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(messageBody)));
		waitMailBodyDraft.sendKeys(mailDraft);
		log.info("Captured :  Mail Draft");

		WebDriverWait sendEmailButton = new WebDriverWait(driver, 40);
		WebElement eMailSend = sendEmailButton.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sendButton)));
		eMailSend.click();
		log.info("Clicked : Mail Send Button");

		
		//driver.findElement(By.xpath(messageBody)).sendKeys(mailDraft);
		//driver.findElement(By.xpath(sendButton)).sendKeys(emailSubject);

		
	//	WebDriverWait sendButton = new WebDriverWait(driver, 40);
	//	WebElement send = sendButton.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mailCompose.sendButton)));
	//	send.click();
		
		}
	
}
