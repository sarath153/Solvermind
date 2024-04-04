package scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

public class Scenario9 extends Keywords {

	String URL = TestNgXml.getdatafromExecution().get("Scenario9");
	String Username = Utils.getDataFromTestData("Solverminds", "Username");
	String Password = Utils.getDataFromTestData("Solverminds", "Password");
	boolean flag = true;
	boolean flag3 = false;
	public static ExtentTest test;
	
	private static LocalDateTime currentDateTime = LocalDateTime.now();

	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");
	private static String formattedDateTime = currentDateTime.format(formatter) + "_";

	@SuppressWarnings("deprecation")
	public void Semiautorun9(WebDriver driver, String vessel2,ExtentTest test, ExtentTest testDetail, String detailreportPath)
			throws AWTException, InterruptedException, IOException {

		String vessel = vessel2;

		String DSW_GM = Utils.getDataFromTestData(vessel, "DSW_GM");
		String MasterPlanFile = Utils.getDataFromTestData(vessel, "MasterPlanFile");
		String TestPlanFile = Utils.getDataFromTestData(vessel, "TestPlanFile");
		String Vesselcode = Vesselname(vessel);
		String NewTime = formattedDateTime + Vesselcode;
		String Finalresultpath = System.getProperty("user.dir") + "/Uploads/Scenario9/Resultexcel" + NewTime + ".xlsx";
		String path = NewTime;

		navigateUrl(driver, URL);
		waitForElement(driver, username);
		sendKeys(driver, username, Username);

		waitForElement(driver, password);
		sendKeys(driver, password, Password);

		waitForElement(driver, login);
		click(driver, login);

		waitForElement(driver, selectvessel);
		click(driver, selectvessel);

		clickVessel(driver,vessel);

		waitForElement(driver, Openplantop);
		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']"))
				.click();

		waitForElement(driver, Bay1);
		click(driver, Openplantop);

		waitForElement(driver, Globalplan);
		click(driver, Globalplan);

		waitForElement(driver, clickOk);
		click(driver, clickOk);
		
		waitForElement(driver, Plandescription);
		click(driver, Plandescription);
		
		sendKeys(driver, Plandescription, MasterPlanFile);

		clickPlan(driver, MasterPlanFile);
		wait(driver, "5");

		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait(driver, "1");
		
		if (flag == true) {
			takescreenshot1(driver, "/Expected_screenshot/Scenario9/Bay1");
			}
//Screenshot from MAster Plan file
		int i = 2;
		while (isDisplayed(driver, PreviousBay)) {
			click(driver, PreviousBay);

			if (flag == true) {
				takescreenshot1(driver, "/Expected_screenshot/Scenario9/Bay" + i);

				wait(driver, "1");
			}

			i++;
		}

//		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();
//		
//		wait(driver,"3");
//		
//		// Test container data ...
//				int colCount_M = columnCountValue(driver);
//				int rowCount_M = RowCountValue(driver);
//
//				ArrayList<String> headerList_M = headerValueList(driver, colCount_M);
//
//				Map<String, ArrayList<String>> cellmap_M = cellListMap(driver, colCount_M, rowCount_M, headerList_M);
//
//				// important
//				String filePath_M = System.getProperty("user.dir") + "\\uploads\\Scenario9\\ContainerPoolData\\MasterData_"
//						+ path + ".xlsx";
//
//				System.out.println("Path_T : "+filePath_M);
//				createExcelForContainerPool(colCount_M, rowCount_M, headerList_M, cellmap_M, filePath_M);
//				

		waitForElement(driver, Tankdetails);
		click(driver, Tankdetails);

		waitForElement(driver, Tankexport);
		click(driver, Tankexport);

		wait(driver, "10");

		String Master = System.getProperty("user.dir") + "\\Uploads\\Scenario9\\MasterFiles\\Masterfile_" + path + ".xlsx";
		String autoITExecutable = System.getProperty("user.dir") + "/driver/TankDownloadfile1.exe " + Master;

//		String Master = System.getProperty("user.dir") + "\\Uploads\\Scenario1\\Masterfile_" + path + ".xlsx";
//		String autoITExecutable = System.getProperty("user.dir") + "/driver/MasterDownloadfile.exe " + Master;
//
//		try {
//			Runtime.getRuntime().exec(autoITExecutable);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		try {
			Runtime.getRuntime().exec(autoITExecutable);
		} catch (IOException e) {
			e.printStackTrace();
		}

		wait(driver,"10");
		// New Test Plan
		wait(driver, "3");
		newTab2(driver);
		wait(driver, "1");
		navigateUrl(driver, URL);

		if (isDisplayed1(driver, username)) {

			sendKeys(driver, username, Username);
			waitForElement(driver, password);
			sendKeys(driver, password, Password);
			waitForElement(driver, login);
			click(driver, login);
			waitForElement(driver, selectvessel);
			click(driver, selectvessel);

			clickVessel(driver,vessel);

		} else if (isDisplayed1(driver, Home_Icon)) {

		waitForElement(driver, Home_Icon);
		click(driver, Home_Icon);
		waitForElement(driver, Exit_popup);
		waitForElement(driver, ExitBtn);
		click(driver, ExitBtn);
		waitForElement(driver, selectvessel);
		click(driver, selectvessel);
		
		clickVessel(driver,vessel);

		}
		waitForElement(driver, Openplantop);
//		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();
		click(driver, Openplantop);

		waitForElement(driver, Globalplan);
		click(driver, Globalplan);

		waitForElement(driver, clickOk);
		click(driver, clickOk);

		waitForElement(driver, Plandescription);
		click(driver, Plandescription);
		
		sendKeys(driver, Plandescription, TestPlanFile);

		
		clickPlan(driver, TestPlanFile);
		wait(driver, "5");

		// Balllast optimizer

		waitForElement(driver, Search);
		click(driver, Search);

		waitForElement(driver, SearchInput);
		sendKeys(driver, SearchInput, "Ballast optimizer");

		waitForElement(driver, selectsearch);
		click(driver, selectsearch);

		waitForElement(driver, radiobutton);
		click(driver, radiobutton);

		waitForElement(driver, valuesend);
		doubleClick(driver, valuesend);
		sendKeys(driver, valuesend, "0");

		waitForElement(driver, Run1);
		click(driver, Run1);

		waitForElement1(driver, Okbutton);
		click(driver, Okbutton);

		waitForElement(driver, Exportok);
		click(driver, Exportok);

		waitForElement1(driver, Tankdetails);
		click(driver, Tankdetails);

		wait(driver, "2");

		waitForElement(driver, Tankexport);
		click(driver, Tankexport);
		wait(driver, "8");


		String Test = System.getProperty("user.dir") + "\\Uploads\\Scenario9\\TestFiles\\Testfile_" + path + ".xlsx";

		String autoITExecutableTest = System.getProperty("user.dir") + "/driver/TankDownloadfile1.exe " + Test;

		try {
			Runtime.getRuntime().exec(autoITExecutableTest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		wait(driver,"10");
		
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

		sendKeys(driver, Saveinput, "TD Testplan Scenario9");

		waitForElement(driver, Clickplanok);
		click(driver, Clickplanok);

		waitForElement(driver, Allbayplan);
		click(driver, Allbayplan);

		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait(driver, "3");

		if (flag = true) {
			takescreenshot1(driver, "/Actual_screenshot/Scenario9/Bay1");
			try {
				imageComparison(driver, "/Scenario9/Bay1", "/Scenario9/Bay1");
				wait(driver, "2");
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}

		int j = 2;

		while (isDisplayed(driver, PreviousBay)) {
			click(driver, PreviousBay);

			if (flag == true) {
				takescreenshot1(driver, "/Actual_screenshot/Scenario9/Bay" + j);

				try {
					imageComparison(driver, "/Scenario9/Bay" + j, "/Scenario9/Bay" + j);
					wait(driver, "2");
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				wait(driver, "1");
			}

			j++;
		}
		

//		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();
//		
//		wait(driver,"3");
//		
//		// Test container data ...
//				int colCount_T = columnCountValue(driver);
//				int rowCount_T = RowCountValue(driver);
//
//				ArrayList<String> headerList_T = headerValueList(driver, colCount_T);
//
//				Map<String, ArrayList<String>> cellmap_T = cellListMap(driver, colCount_T, rowCount_T, headerList_T);
//
//				// important
//				String filePath_T = System.getProperty("user.dir") + "\\uploads\\Scenario9\\ContainerPoolData\\TestData_"
//						+ path + ".xlsx";
//
//				System.out.println("Path_T : "+filePath_T);
//				createExcelForContainerPool(colCount_T, rowCount_T, headerList_T, cellmap_T, filePath_T);

		
		
		Tankdetailscomparison1(Master,Test,test, testDetail,detailreportPath);
		
		
//		compareContainerData(filePath_M,filePath_T);

	}
}
