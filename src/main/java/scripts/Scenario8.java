package scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import ch.qos.logback.core.joran.action.Action;
import commonMethods.Keywords;
import commonMethods.TestNgXml;
import commonMethods.Utils;

public class Scenario8 extends Keywords {

	String URL = TestNgXml.getdatafromExecution().get("Scenario8");
	String Username = Utils.getDataFromTestData("Solverminds", "Username");
	String Password = Utils.getDataFromTestData("Solverminds", "Password");
	boolean flag = true;
	boolean flag3 = false;
	String Vessel = TestNgXml.getdatafromExecution1().get("Scenario8");
	String DSW_GM = Utils.getDataFromTestData(Vessel, "DSW_GM");
	String MasterPlanFile = Utils.getDataFromTestData(Vessel, "MasterPlanFile");
	String TestPlanFile = Utils.getDataFromTestData(Vessel, "TestPlanFile");
	public static ExtentTest test;

	@SuppressWarnings("deprecation")
	public void Semiautorun8(WebDriver driver) throws AWTException {
		
		Robot robot = new Robot();
		WebDriverWait wait = new WebDriverWait(driver, 20);

		navigateUrl(driver, URL);
		Actions action = new Actions(driver);
		waitForElement(driver, username);
		sendKeys(driver, username, Username);

		waitForElement(driver, password);
		sendKeys(driver, password, Password);

		waitForElement(driver, login);
		click(driver, login);

		waitForElement(driver, selectvessel);
		click(driver, selectvessel);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + Vessel + "')]")));

		driver.findElement(By.xpath("//*[contains(text(),'" + Vessel + "')]")).click();

		waitForElement(driver, Openplantop);
		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']"))
				.click();

		waitForElement(driver, Openplantop);
		click(driver, Openplantop);

		waitForElement(driver, Globalplan);
		click(driver, Globalplan);

		waitForElement(driver, clickOk);
		click(driver, clickOk);
	
		WebElement Masterfile = driver.findElement(By.xpath("//*[contains(text(),'" + MasterPlanFile + "')]"));
		wait.until(ExpectedConditions.elementToBeClickable(Masterfile));

		Actions action1 = new Actions(driver).doubleClick(Masterfile);
		action1.build().perform();
		
		wait(driver,"5");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='demo-simple-select-autowidth']")));
		driver.findElement(By.xpath("//*[@id='demo-simple-select-autowidth']")).click();

		waitForElement(driver, Planpattern);
		click(driver, Planpattern);

		waitForElement(driver, planpatterndropdown);
		click(driver, planpatterndropdown);

		waitForElement(driver, SelectNone);
		click(driver, SelectNone);


		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait(driver, "1");
//Screenshot from MAster Plan file
		int i = 2;
		while (isDisplayed(driver, PreviousBay)) {
			click(driver, PreviousBay);

			if (flag == true) {
				takescreenshot1(driver, "/Expected_screenshot/Scenario8/Bay" + i);

				wait(driver, "1");
			}

			i++;
		}


	    
	    
	  
		waitForElement(driver, Search);
		click(driver, Search);

		waitForElement(driver, SearchInput);
		sendKeys(driver, SearchInput, "Cargo list");

		waitForElement(driver, Selectlist1);
		click(driver, Selectlist1);

		waitForElement(driver, ExportExcel);
		click(driver, ExportExcel);


		wait(driver, "8");

		LocalDateTime currentDateTime = LocalDateTime.now();

		// Format the date and time as a string
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");
		String formattedDateTime = currentDateTime.format(formatter) + "_";
		wait(driver, "1");
		String path = formattedDateTime;

		String Master = System.getProperty("user.dir") + "\\Uploads\\08Masterfile_" + path + ".xlsx";
		String autoITExecutable = System.getProperty("user.dir") + "/driver/MasterDownloadfile.exe " + Master;

		try {
			Runtime.getRuntime().exec(autoITExecutable);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Created successfully')]")));
		
		
		wait(driver, "3");
		newTab2(driver);
		wait(driver, "2");
		switchtotab(driver, 1);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		navigateUrl(driver, URL);

		waitForElement(driver, Home_Icon);
		click(driver, Home_Icon);
		waitForElement(driver, Exit_popup);
		waitForElement(driver, ExitBtn);
		click(driver, ExitBtn);
		waitForElement(driver, selectvessel);
		click(driver, selectvessel);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + Vessel + "')]")));
		driver.findElement(By.xpath("//*[contains(text(),'" + Vessel + "')]")).click();
		
		waitForElement(driver, Openplantop);
		click(driver, Openplantop);

		waitForElement(driver, Globalplan);
		click(driver, Globalplan);

		waitForElement(driver, clickOk);
		click(driver, clickOk);
		
		WebElement Testfile = driver.findElement(By.xpath("//*[contains(text(),'" + TestPlanFile + "')]"));
		wait.until(ExpectedConditions.elementToBeClickable(Testfile));

		Actions action2 = new Actions(driver).doubleClick(Testfile);
		action2.build().perform();

		waitForElement(driver, optimiser);
		click(driver, optimiser);

		waitForElement(driver, Masterplan);
		click(driver, Masterplan);

		
		waitForElement(driver, VWR);
		click(driver, VWR);

		waitForElement(driver, select2);
		click(driver, select2);

		
		waitForElement(driver, Run);
		click(driver, Run);

		waitForElement1(driver, ClickOk);
		click(driver, ClickOk);
		
		
		
		//Selectng Stowage plan
		waitForElement(driver, Storageplan);
		click(driver, Storageplan);

		
		waitForElement(driver, Plantemplate);
		click(driver, Plantemplate);

		waitForElement(driver, Lashing);
		click(driver, Lashing);
		
		waitForElement(driver, VWR);
		click(driver, VWR);

		waitForElement(driver, select2);
		click(driver, select2);

		
		sendKeys(driver, DSWGM, DSW_GM);
		
		waitForElement(driver, Run);
		click(driver, Run);

		waitForElement1(driver, ClickOk);
		click(driver, ClickOk);
		
		
		
		
		

		wait(driver, "5");
		doubleClick(driver, Canvas2);

		wait(driver, "2");
		
		waitForElement(driver, Saveplan);
		click(driver, Saveplan);
		
		waitForElement(driver, Globalsave);
		click(driver, Globalsave);
		
		waitForElement(driver, clickOk);
		click(driver, clickOk);
		
		waitForElement(driver, Saveinput);
		click(driver, Saveinput);
				
		sendKeys(driver,Saveinput , "TD Testplan Scenario8");

		waitForElement(driver, Clickplanok);
		click(driver, Clickplanok);

		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait(driver, "3");

		if (flag = true) {
			takescreenshot1(driver, "/Actual_screenshot/Scenario8/Bay1");
			try {
				imageComparison(driver, "/Scenario8/Bay1", "/Scenario8/Bay1");
				wait(driver, "2");
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}

		int j=2;

		while (isDisplayed(driver, PreviousBay)) {
		    click(driver, PreviousBay);

		    if (flag == true) {
		        takescreenshot1(driver, "/Actual_screenshot/Scenario8/Bay" + j);
		        
		        try {
					imageComparison(driver, "/Scenario8/Bay" + j, "/Scenario8/Bay" + j);
					wait(driver, "2");
				} catch (IOException e1) {

					e1.printStackTrace();
				}
		        wait(driver, "1");
		    }

		    j++;
		}

		waitForElement(driver, Search);
		click(driver, Search);

		waitForElement(driver, SearchInput);
		sendKeys(driver, SearchInput, "Cargo list");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='react-autowhatever-1--item-0']")));
		driver.findElement(By.xpath("//*[@id='react-autowhatever-1--item-0']")).click();

		wait(driver, "2");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@alt='Export As Excel']")));
		waitForElement(driver, ExportExcel);
		click(driver, ExportExcel);
		wait(driver, "10");
		
		LocalDateTime currentDateTime1 = LocalDateTime.now();
		String formattedDateTime1 = currentDateTime1.format(formatter) + "_";

		wait(driver, "1");
		String Testpath = formattedDateTime1;

String Test = System.getProperty("user.dir") + "\\Uploads\\08Testfile_" + Testpath + ".xlsx";

		String autoITExecutableTest = System.getProperty("user.dir") + "./driver/TestDownloadfile.exe " + Test;

		try {
			Runtime.getRuntime().exec(autoITExecutableTest);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
