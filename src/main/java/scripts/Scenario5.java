package scripts;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import atu.testng.reports.logging.LogAs;
import commonMethods.Keywords;
import commonMethods.TestNgXml;
import commonMethods.Utils;

public class Scenario5 extends Keywords {

	String URL = TestNgXml.getdatafromExecution().get("Scenario5");
	String Username = Utils.getDataFromTestData("Solverminds", "Username");
	String Password = Utils.getDataFromTestData("Solverminds", "Password");
	boolean flag = true;
	boolean flag3 = false;
	private static LocalDateTime currentDateTime = LocalDateTime.now();
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");
	private static String formattedDateTime = currentDateTime.format(formatter) + "_";

	public static ExtentTest test;

	@SuppressWarnings("deprecation")
	public void Semiautorun5(WebDriver driver, String vessel2,ExtentTest test ,ExtentTest testDetail,String detailreportPath) throws Exception {
		String vessel = vessel2;
		String DSW_GM = Utils.getDataFromTestData(vessel, "DSW_GM");
		String VWR_ = Utils.getDataFromTestData(vessel, "VWR");
		String MasterPlanFile = Utils.getDataFromTestData(vessel, "MasterPlanFile");
		String TestPlanFile = Utils.getDataFromTestData(vessel, "TestPlanFile");
		String Vesselcode = Vesselname(vessel);
		String NewTime = formattedDateTime + Vesselcode;
		String Finalresultpath = System.getProperty("user.dir") + "/Uploads/Scenario5/Resultexcel" + NewTime + ".xlsx";
		String path = NewTime;

		WebDriverWait wait = new WebDriverWait(driver, 60);

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

		waitForElement(driver,Bay1);
		waitForElement(driver, Openplantop);
		click(driver, Openplantop);

		waitForElement(driver, Globalplan);
		click(driver, Globalplan);

		waitForElement(driver, clickOk);
		click(driver, clickOk);
		

		waitForElement(driver, Plandescription);
		click(driver, Plandescription);
		
		sendKeys(driver, Plandescription, MasterPlanFile);

        clickPlan(driver,MasterPlanFile);

        waitForElement(driver, planOpened);

		String plan = getText(driver, planOpened);

		if (plan.contains(MasterPlanFile)) {
			System.out.println("Correct Plan is Opened");
			add(driver, "Correct Plan is Opened : " + plan, LogAs.PASSED, true, "");
		} else {
			System.out.println("Different Plan is Opened");
			add1(driver, "Different Plan is opened : " + plan, LogAs.FAILED, true, "");
		}
		
		wait(driver, "5");
		waitForElement(driver, dropdown);
		click(driver, dropdown);
		waitForElement(driver, Planpattern);
		click(driver, Planpattern);

		waitForElement(driver, planpatterndropdown);
		click(driver, planpatterndropdown);

		waitForElement(driver, SelectNone);
		click(driver, SelectNone);

		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait(driver, "2");
		
		if (flag == true) {
			takescreenshot1(driver, "/Expected_screenshot/Scenario5/Bay1");
			}

		int i = 2;
		while (isDisplayed(driver, PreviousBay)) {
			click(driver, PreviousBay);

			if (flag == true) {
				takescreenshot1(driver, "/Expected_screenshot/Scenario5/Bay" + i);

				wait(driver, "1");
			}

			i++;
		}
		
		
		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();
		
		wait(driver,"3");
		
		// Test container data ...
				int colCount_M = columnCountValue(driver);
				int rowCount_M = RowCountValue(driver);

				ArrayList<String> headerList_M = headerValueList(driver, colCount_M);

				Map<String, ArrayList<String>> cellmap_M = cellListMap(driver, colCount_M, rowCount_M, headerList_M);

				// important
				String filePath_M = System.getProperty("user.dir") + "\\uploads\\Scenario5\\ContainerPoolData\\MasterData_"
						+ path + ".xlsx";

				System.out.println("Path_T : "+filePath_M);
				createExcelForContainerPool(colCount_M, rowCount_M, headerList_M, cellmap_M, filePath_M);

		waitForElement(driver, Search);
		click(driver, Search);

		waitForElement(driver, SearchInput);
		sendKeys(driver, SearchInput, "Cargo list");

		waitForElement(driver, Selectlist1);
		click(driver, Selectlist1);

		waitForElement(driver, tablevalues);		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@alt='Export As Excel']")));
		
		WebElement Exportexcel = driver.findElement(By.xpath("//*[@alt='Export As Excel']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(Exportexcel).build().perform();
		Exportexcel.click();
		wait(driver, "6");
		while(isDisplayed(driver,excelcheck)) {
			System.out.println("ReClicked.");
			try {
			Exportexcel.click();
			}catch(Exception e) {
			  System.out.println("Not able click export excel.");	
			}
			wait(driver, "6");
		}

		wait(driver, "8");

		String Master = System.getProperty("user.dir") + "\\Uploads\\Scenario5\\MasterFiles\\Masterfile_" + path + ".xlsx";
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
		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();
		waitForElement(driver,Bay1);
		click(driver, Openplantop);

		waitForElement(driver, Globalplan);
		click(driver, Globalplan);

		waitForElement(driver, clickOk);
		click(driver, clickOk);
		

		waitForElement(driver, Plandescription);
		click(driver, Plandescription);
		
		sendKeys(driver, Plandescription, TestPlanFile);

        clickPlan(driver,TestPlanFile);

        waitForElement(driver, planOpened);

		String plan1 = getText(driver, planOpened);

		if (plan1.contains(TestPlanFile)) {
			System.out.println("Correct Plan is Opened");
			add(driver, "Correct Plan is Opened : " + plan1, LogAs.PASSED, true, "");
		} else {
			System.out.println("Different Plan is Opened");
			add1(driver, "Different Plan is opened : " + plan1, LogAs.FAILED, true, "");
		}
        
		wait(driver, "3");
		waitForElement(driver, dropdown);
		click(driver, dropdown);
		waitForElement(driver, Planpattern);
		click(driver, Planpattern);

		waitForElement(driver, planpatterndropdown);
		click(driver, planpatterndropdown);

		waitForElement(driver, SelectNone);
		click(driver, SelectNone);

		waitForElement(driver, optimiser);
		click(driver, optimiser);

		waitForElement(driver, VWR);
		click(driver, VWR);

		clickVWR(driver,VWR_);

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

		sendKeys(driver, Saveinput, "TD Testplan Scenario5 " + NewTime);

		waitForElement(driver, Clickplanok);
		click(driver, Clickplanok);

		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait(driver, "3");

		if (flag = true) {
			takescreenshot1(driver, "/Actual_screenshot/Scenario5/Bay1");
			try {
				imageComparison(driver, "/Scenario5/Bay1", "/Scenario5/Bay1");
				wait(driver, "2");
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}

		int j = 2;

		while (isDisplayed(driver, PreviousBay)) {
			click(driver, PreviousBay);

			if (flag == true) {
				takescreenshot1(driver, "/Actual_screenshot/Scenario5/Bay" + j);

				try {
					imageComparison(driver, "/Scenario5/Bay" + j, "/Scenario5/Bay" + j);
					wait(driver, "2");
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				wait(driver, "1");
			}

			j++;
		}

		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();
		
		wait(driver,"3");
		
		// Test container data ...
				int colCount_T = columnCountValue(driver);
				int rowCount_T = RowCountValue(driver);

				ArrayList<String> headerList_T = headerValueList(driver, colCount_T);

				Map<String, ArrayList<String>> cellmap_T = cellListMap(driver, colCount_T, rowCount_T, headerList_T);

				// important
				String filePath_T = System.getProperty("user.dir") + "\\uploads\\Scenario5\\ContainerPoolData\\TestData_"
						+ path + ".xlsx";

				System.out.println("Path_T : "+filePath_T);
				createExcelForContainerPool(colCount_T, rowCount_T, headerList_T, cellmap_T, filePath_T);

		waitForElement(driver, Search);
		click(driver, Search);

		waitForElement(driver, SearchInput);
		sendKeys(driver, SearchInput, "Cargo list");

		waitForElement(driver, Selectlist1);
		click(driver, Selectlist1);
		
		waitForElement(driver, tablevalues);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@alt='Export As Excel']")));
		WebElement Exportexcel1 = driver.findElement(By.xpath("//*[@alt='Export As Excel']"));
		builder.moveToElement(Exportexcel1).build().perform();
		Exportexcel1.click();
		
		wait(driver, "6");
		while(isDisplayed(driver,excelcheck)) {
			System.out.println("ReClicked.");
			try {
			Exportexcel.click();
			}catch(Exception e) {
			  System.out.println("Not able click export excel.");	
			}
			wait(driver, "6");
		}
		
		wait(driver, "8");

		String Test = System.getProperty("user.dir") + "\\Uploads\\Scenario5\\TestFiles\\Testfile_" + path + ".xlsx";

		String autoITExecutableTest = System.getProperty("user.dir") + "/driver/TestDownloadfile.exe " + Test;

		try {
			Runtime.getRuntime().exec(autoITExecutableTest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Created successfully')]")));
		Fillocomparison(Master,Test,test,testDetail,detailreportPath);

		Createexcel(Master, Test, Finalresultpath,test);

		compareContainerData(filePath_M,filePath_T);
		
	}
}
