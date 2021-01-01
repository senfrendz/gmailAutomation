package pages;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class emailSentVerification {
	
	public static WebDriver driver;

	public static String mailSubject;
	public static String originalMailSubject;

	public static String sentEmail = baseClass.properties.getProperty("Sent_Email");
	public static String sentHeader = baseClass.properties.getProperty("Sent_EmailHeader");
	public static String sentLabel = baseClass.properties.getProperty("Sent_Label");

	public static Logger log = Logger.getLogger(emailSentVerification.class);

	public static void sentMailValidation(WebDriver driver,String emailSubject) throws InterruptedException {
		
		originalMailSubject = emailSubject;
		WebDriverWait sentEmaill = new WebDriverWait(driver, 80);
		WebElement eMailSent = sentEmaill.until(ExpectedConditions.elementToBeClickable(By.xpath(sentLabel)));
		eMailSent.click();
		log.info("Element Clicked : Sent ");
		
		WebDriverWait verifySentEmaill = new WebDriverWait(driver, 80);
		WebElement verifySentmaill = verifySentEmaill.until(ExpectedConditions.elementToBeClickable(By.xpath(sentEmail)));
		verifySentmaill.click();
		log.info("Element Clicked : Last Sent Mail ");
		
		WebDriverWait sentNotification = new WebDriverWait(driver, 80);
		WebElement eMailSentNotification = sentNotification.until(ExpectedConditions.elementToBeClickable(By.xpath(sentHeader)));
		mailSubject = eMailSentNotification.getText();
		log.info("Element Clicked : Last sent Mail Header ");

		assertEquals(mailSubject, originalMailSubject.trim());
		log.info("Matched :" +"both actual - "+mailSubject + "Original -"+emailSubject );
		log.info("Verified : Email has been sent Successfully" );
		System.out.println("Email has been sent Successfully");
	}
}
