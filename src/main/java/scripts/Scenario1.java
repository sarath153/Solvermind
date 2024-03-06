package scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonMethods.Keywords;
import commonMethods.TestNgXml;
import commonMethods.Utils;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.aventstack.extentreports.ExtentTest;

import atu.testng.reports.logging.LogAs;

public class Scenario1 extends Keywords {

	String URL = TestNgXml.getdatafromExecution().get("Scenario1");
	String Username = Utils.getDataFromTestData("Solverminds", "Username");
	String Password = Utils.getDataFromTestData("Solverminds", "Password");
	String Vessel = TestNgXml.getdatafromExecution1().get("Scenario1");
	boolean flag = true;
	String DSW_GM = Utils.getDataFromTestData(Vessel, "DSW_GM");
	String MasterPlanFile = Utils.getDataFromTestData(Vessel, "MasterPlanFile");
	String TestPlanFile = Utils.getDataFromTestData(Vessel, "TestPlanFile");
	public static ExtentTest test;

	public void Semiautorun(WebDriver driver) throws AWTException {

		Robot robot = new Robot();
		WebDriverWait wait = new WebDriverWait(driver, 30);

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
/*
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

		wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("//*[@class='ves_topbar_align']"),
				"VAPPlan-766 - VAPPlan-624 - TEM Auto Test Final Stowage Result 3m GM Lashing"));

		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait(driver, "1");

		int i = 2;
		while (isDisplayed(driver, PreviousBay)) {
			click(driver, PreviousBay);

			if (flag == true) {
				takescreenshot1(driver, "/Expected_screenshot/Scenario1/Bay" + i);

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

		wait(driver, "10");

		LocalDateTime currentDateTime = LocalDateTime.now();

		// Format the date and time as a string
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");
		String formattedDateTime = currentDateTime.format(formatter) + "_";
		wait(driver, "1");
		String path = formattedDateTime;

//		String fileName = "C:\\Users\\RBT\\Desktop\\Solver_Minds\\uploads\\01Master_" + path + ".xlsx";
//
//		String autoITExecutable = "C:\\Users\\RBT\\Desktop\\Solver_Minds\\driver\\MasterDownloadfile.exe " + fileName;
//
//		try {
//		    Runtime.getRuntime().exec(autoITExecutable);
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}

		String Master = System.getProperty("user.dir") + "\\Uploads\\01Masterfile_" + path + ".xlsx";
		String autoITExecutable = System.getProperty("user.dir") + "/driver/MasterDownloadfile.exe " + Master;

		try {
			Runtime.getRuntime().exec(autoITExecutable);
		} catch (IOException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Created successfully')]")));

//		// Test Plan

		newTab2(driver);
		wait(driver, "2");
		switchtotab(driver, 1);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		navigateUrl(driver, URL);
//		if (isDisplayed(driver, username)) {
//
//			sendKeys(driver, username, Username);
//
//			waitForElement(driver, password);
//			sendKeys(driver, password, Password);
//
//			waitForElement(driver, login);
//			click(driver, login);
//			waitForElement(driver, selectvessel);
//			click(driver, selectvessel);
//
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'"+Vessel+"')]")));
//			
//			driver.findElement(By.xpath("//*[contains(text(),'"+Vessel+"')]")).click();
//		} else if (isDisplayed(driver, Home_Icon)) {
		waitForElement(driver, Home_Icon);
		click(driver, Home_Icon);
		waitForElement(driver, Exit_popup);
		waitForElement(driver, ExitBtn);
		click(driver, ExitBtn);

		driver.findElement(By.xpath("//input[@id='demo-simple-select-outlined']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + Vessel + "')]")));

		driver.findElement(By.xpath("//*[contains(text(),'" + Vessel + "')]")).click();

//		}

		waitForElement(driver, Openplantop);
		click(driver, Openplantop);

		waitForElement(driver, Globalplan);
		click(driver, Globalplan);

		waitForElement(driver, clickOk);
		click(driver, clickOk);

//		waitForElement(driver, VAPPlan_765);
//		doubleClick(driver, VAPPlan_765);

		WebElement Testfile = driver.findElement(By.xpath("//*[contains(text(),'" + TestPlanFile + "')]"));
		wait.until(ExpectedConditions.elementToBeClickable(Testfile));

		Actions action2 = new Actions(driver).doubleClick(Testfile);
		action2.build().perform();

		wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("//*[@class='ves_topbar_align']"),
				"VAPPlan-765 - VAPPlan-623 - TEM Auto Test Case SAS with Template 3m GM Lashing"));

		String Planname2 = driver.findElement(By.xpath("//*[@class='ves_topbar_align']")).getText();

		if (Planname2.equals("VAPPlan-765 - VAPPlan-623 - TEM Auto Test Case SAS with Template 3m GM Lashing")) {
			System.out.println("Selected VAPPlan-765 is opened");
		} else {
			System.out.println("Different plan is opned");
		}

		waitForElement(driver, Allbayplan);
		click(driver, Allbayplan);

		waitForElement(driver, RobRefresh);
		click(driver, RobRefresh);

		wait(driver, "5");
		click(driver, RobRefresh);

		waitForElement(driver, optimiser);
		click(driver, optimiser);

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

		waitForElement(driver, Saveplan);
		click(driver, Saveplan);

		waitForElement(driver, Globalsave);
		click(driver, Globalsave);

		waitForElement(driver, clickOk);
		click(driver, clickOk);

		waitForElement(driver, Saveinput);
		click(driver, Saveinput);

		sendKeys(driver, Saveinput, "TD Testplan Scenario1");

		waitForElement(driver, Clickplanok);
		click(driver, Clickplanok);

		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait(driver, "3");

		if (flag = true) {
			takescreenshot1(driver, "/Actual_screenshot/Scenario1/Bay1");
			try {
				imageComparison(driver, "/Scenario1/Bay1", "/Scenario1/Bay1");
				wait(driver, "2");
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}

		int j = 2;

		while (isDisplayed(driver, PreviousBay)) {
			click(driver, PreviousBay);

			if (flag == true) {
				takescreenshot1(driver, "/Actual_screenshot/Scenario1/Bay" + j);

				try {
					imageComparison(driver, "/Scenario1/Bay" + j, "/Scenario1/Bay" + j);
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
		wait(driver, "15");

		LocalDateTime currentDateTime1 = LocalDateTime.now();
		String formattedDateTime1 = currentDateTime1.format(formatter) + "_";

		wait(driver, "1");
		String Testpath = formattedDateTime1;

//		String TestfileName = "C:\\Users\\RBT\\Desktop\\Solver_Minds\\uploads\\01Test_" + Testpath + ".xlsx";
//
//		String autoITExecutableTest = "C:\\Users\\RBT\\Desktop\\Solver_Minds\\driver\\TestDownloadfile.exe " + TestfileName;
//
//		try {
//		    Runtime.getRuntime().exec(autoITExecutableTest);
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}

//		String Test = System.getProperty("user.dir") + "./Uploads/01Testfile_" + Testpath + ".xlsx";
        
//		String filename=Test;
		String Test = System.getProperty("user.dir") + "\\Uploads\\01Testfile_" + Testpath + ".xlsx";

		String autoITExecutableTest = System.getProperty("user.dir") + "./driver/TestDownloadfile.exe " + Test;

		try {
			Runtime.getRuntime().exec(autoITExecutableTest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Created successfully')]")));
		
		
		Fillocomparison();
		*/
		
//		try {
//			Excel1(Master,Test);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

}
