package commonMethods;

import java.net.MalformedURLException;
import java.util.logging.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import atu.testng.reports.ATUReports;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Config extends Keywords {
	
	public WebDriver driver;
	ATUReports atuRep = new ATUReports();

	public WebDriver getDriver() throws MalformedURLException {
		return this.driver;
       // this methods integrated in Reports script, so don't remove this method
	}

	public void setDriver(WebDriver paramDriver) throws MalformedURLException {
		this.driver = paramDriver;
	    // this method integrated in Reports script, so don't remove this method

	}


	public WebDriver getWebDriver(String browserName) throws MalformedURLException {
		
		if (browserName.equalsIgnoreCase("Chrome")) {

			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			options.addArguments("disable-notifications");
			LoggingPreferences logPrefs = new LoggingPreferences();
		    logPrefs.enable(LogType.BROWSER, Level.ALL);
		    options.setCapability("goog:loggingPrefs", logPrefs);
			driver = new ChromeDriver(options);
			System.out.println("Chrome Browser launched...");	
			setDriver(driver);
			driver.manage().window().maximize();

			}
		
		else if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
			FirefoxOptions option = new FirefoxOptions();
			option.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver(option);
			System.out.println("Firefox Browser launched...");
			setDriver(driver);
			driver.manage().window().maximize();

		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			setDriver(driver);
			driver.manage().window().maximize();

		}
		return driver;
	}


	
}
