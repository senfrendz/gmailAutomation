package pages;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

public class baseClass {

	public static Properties properties = null;
	public static WebDriver driver;
	public static Logger log = Logger.getLogger(baseClass.class);

	public Properties loadPropertyFile() throws IOException{
		    FileInputStream readingObj = new FileInputStream("config.properties");
		    properties  = new Properties();
		    properties.load(readingObj);
		    log.info("Properties loaded Successfully");
		return properties;
	}
		public void launchBrowser() throws IOException {
			loadPropertyFile();
			
		}
			}



