package scripts;

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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import atu.testng.reports.logging.LogAs;
import ch.qos.logback.core.joran.action.Action;
import commonMethods.Keywords;
import commonMethods.TestNgXml;
import commonMethods.Utils;

public class Scenario7 extends Keywords {

	String URL = TestNgXml.getdatafromExecution().get("Scenario7");
	String Username = Utils.getDataFromTestData("Solverminds", "Username");
	String Password = Utils.getDataFromTestData("Solverminds", "Password");
	boolean flag = true;
	private static LocalDateTime currentDateTime = LocalDateTime.now();
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");
	private static String formattedDateTime = currentDateTime.format(formatter) + "_";

	public void Masterplan7(WebDriver driver, String vessel2,ExtentTest test ,ExtentTest testDetail,String detailreportPath) throws Exception {

		String vessel = vessel2;
		String DSW_GM = Utils.getDataFromTestData(vessel, "DSW_GM");
		String VWR_ = Utils.getDataFromTestData(vessel, "VWR");
		String MasterPlanFile = Utils.getDataFromTestData(vessel, "MasterPlanFile");
		String TestPlanFile = Utils.getDataFromTestData(vessel, "TestPlanFile");
		String Vesselcode = Vesselname(vessel);
		String NewTime = formattedDateTime + Vesselcode;
		String Finalresultpath = System.getProperty("user.dir") + "\\Uploads\\Scenario7\\Resultexcel" + NewTime + ".xlsx";
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

		clickVessel(driver, vessel);

		waitForElement(driver, home_Page);

		waitForElement(driver, Openplantop);
		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();
        waitForElement(driver,Bay1);
		click(driver, Openplantop);

		waitForElement(driver, Globalplan);
		click(driver, Globalplan);

		waitForElement(driver, clickOk);
		click(driver, clickOk);

		//new
		waitForElement(driver,Plandescription);
		sendKeys(driver,Plandescription,MasterPlanFile);
		// new
		clickPlan(driver, MasterPlanFile);
		waitForElement(driver, planOpened);

		String plan = getText(driver, planOpened);

		if (plan.contains(MasterPlanFile)) {
			System.out.println("Correct Plan is Opened");
		} else {
			System.out.println("Different Plan is Opened");
		}

		wait(driver, "2");
		
		
		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait(driver, "1");

		waitForElement(driver,PreviousBay);
		if (flag == true) {
			takescreenshot1(driver, "/Expected_screenshot/Scenario7/Bay1");
			wait(driver, "1");
		}
		
		int i = 2;
		while (isDisplayed(driver, PreviousBay)) {
			click(driver, PreviousBay);

			if (flag == true) {
				takescreenshot1(driver, "/Expected_screenshot/Scenario7/Bay" + i);

				wait(driver, "1");
			}

			i++;
		}

		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();

		wait(driver,"3");
		// Excel creation for container pool data

		int colCount_M = columnCountValue(driver);
		int rowCount_M = RowCountValue(driver);

		ArrayList<String> headerList_M = headerValueList(driver, colCount_M);

		Map<String, ArrayList<String>> cellmap_M = cellListMap(driver, colCount_M, rowCount_M, headerList_M);

		// important
		String filePath_M = System.getProperty("user.dir") + "\\uploads\\Scenario7\\ContainerPoolData\\master_"
				+ path + ".xlsx";
		
		System.out.println("Path_M : "+filePath_M);

		createExcelForContainerPool(colCount_M, rowCount_M, headerList_M, cellmap_M, filePath_M);

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

			clickVessel(driver, vessel);

		} else if (isDisplayed1(driver, Home_Icon)) {
			click(driver, Home_Icon);
			waitForElement(driver, Exit_popup);
			waitForElement(driver, ExitBtn);
			click(driver, ExitBtn);

			waitForElement(driver, selectvessel);
			click(driver, selectvessel);
			clickVessel(driver, vessel);

		}

		waitForElement(driver, home_Page);

		waitForElement(driver, Openplantop);
		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();

		waitForElement(driver,Bay1);
		click(driver, Openplantop);

		waitForElement(driver, Globalplan);
		click(driver, Globalplan);

		waitForElement(driver, clickOk);
		click(driver, clickOk);

		//new
		waitForElement(driver,Plandescription);
		sendKeys(driver,Plandescription,TestPlanFile);
		// new
		clickPlan(driver, TestPlanFile);
		waitForElement(driver, planOpened);

		String plan1 = getText(driver, planOpened);

		if (plan1.contains(TestPlanFile)) {
			System.out.println("Correct Plan is Opened");
			add(driver, "Correct Plan is Opened : " + plan1, LogAs.PASSED, true, "");
		} else {
			System.out.println("Different Plan is Opened");
			add1(driver, "Different Plan is opened : " + plan1, LogAs.FAILED, true, "");
		}

		wait(driver, "2");

		waitForElement(driver, optimiser);
		click(driver, optimiser);

		waitForElement(driver, masterPlan_optimizer);
		click(driver, masterPlan_optimizer);

		waitForElement(driver, VWR);
		click(driver, VWR);

		clickVWR(driver,VWR_);

		click(driver, DSWGM);
		wait(driver,"1");
		doubleClick(driver,DSWGM);
		wait(driver,"1");
		sendKeys(driver, DSWGM, DSW_GM);

		waitForElement(driver, masterAndStowage);
		click(driver, masterAndStowage);

		waitForElement(driver, Run);
		click(driver, Run);

		waitForElement(driver, success);
		waitForElement(driver, runOk);
		click(driver, runOk);

//		
		WebDriverWait wait1 = new WebDriverWait(driver, 14000);

		wait1.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//p[contains(text(),'Stowage Plan is Running')]")));

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Finished']")));

		waitForElement(driver, runOk);
		click(driver, runOk);

		Actions action = new Actions(driver);
		while (isDisplayed(driver, Run)) {
	          action.sendKeys(Keys.ESCAPE).build().perform();
		}

		wait(driver, "2");
		//
		
		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait(driver, "3");

		if (flag = true) {
			takescreenshot1(driver, "/Actual_screenshot/Scenario7/Bay1");
			try {
				imageComparison(driver, "/Scenario7/Bay1", "/Scenario7/Bay1");
				wait(driver, "2");
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}

		int j = 2;

		while (isDisplayed(driver, PreviousBay)) {
			click(driver, PreviousBay);

			if (flag == true) {
				takescreenshot1(driver, "/Actual_screenshot/Scenario7/Bay" + j);

				try {
					imageComparison(driver, "/Scenario7/Bay" + j, "/Scenario7/Bay" + j);
					wait(driver, "2");
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				wait(driver, "1");
			}

			j++;
		}
		
		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();
		
		// Excel creation for container pool data

		int colCount_T = columnCountValue(driver);
		int rowCount_T = RowCountValue(driver);

		ArrayList<String> headerList_T = headerValueList(driver, colCount_T);

		Map<String, ArrayList<String>> cellmap_T = cellListMap(driver, colCount_T, rowCount_T, headerList_T);

		// important
		String filePath_T = System.getProperty("user.dir") + "\\uploads\\Scenario7\\ContainerPoolData\\test_"
				+ path + ".xlsx";

		System.out.println("Path_T : "+filePath_T);
		createExcelForContainerPool(colCount_T, rowCount_T, headerList_T, cellmap_T, filePath_T);

		wait(driver, "1");
		// download test excel files...............

		waitForElement(driver, Search);
		click(driver, Search);
		waitForElement(driver, SearchInput);
		sendKeys(driver, SearchInput, "Cargo list");

		waitForElement(driver, Selectlist1);
		click(driver, Selectlist1);

		WebDriverWait wait2 = new WebDriverWait(driver, 120);
		waitForElement(driver, tablevalues);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@alt='Export As Excel']")));
		WebElement ele = driver.findElement(By.xpath("//*[@alt='Export As Excel']"));
		Actions act=new Actions(driver);
		act.moveToElement(ele).build().perform();
		act.click(ele).perform();
		wait(driver, "6");
		while(isDisplayed(driver,excelcheck)) {
			System.out.println("ReClicked.");
			try {
			ele.click();
			}catch(Exception e) {
			  System.out.println("Not able click export excel.");	
			}
			wait(driver, "6");
		}
		wait(driver, "6");
		String Test = System.getProperty("user.dir") + "\\uploads\\Scenario7\\TestFiles\\Test_" + path
				+ ".xlsx";

		String autoITExecutableTest = System.getProperty("user.dir") + "\\driver\\TestDownloadfile.exe " + Test;

		try {
			Runtime.getRuntime().exec(autoITExecutableTest);
		} catch (IOException e) {
			
		}

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Created successfully')]")));

		wait(driver, "2");

		 waitForElement(driver,savePlan);
		  click(driver,savePlan);
		  
		  waitForElement(driver,globalSave);
		  click(driver,globalSave);
		  
		  waitForElement(driver,OK_);
		  click(driver,OK_);
		  
		  waitForElement(driver,planNameInput);
		  click(driver,planNameInput);
		  sendKeys(driver,planNameInput,vessel+"_TD_Run_"+path);
		  
		  waitForElement(driver,Ok_2);
		  click(driver,Ok_2);
		// to be add the container pool validations...

		//

		ArrayList<String> tabGet = new ArrayList<>(driver.getWindowHandles());
		wait(driver, "1");
		driver.switchTo().window(tabGet.get(0));
		wait(driver, "2");

		// download master excel file

		waitForElement(driver, Search);
		click(driver, Search);
		wait(driver, "1");

		waitForElement(driver, SearchInput);
		sendKeys(driver, SearchInput, "Cargo list");
		wait(driver, "2");

		waitForElement(driver, Selectlist1);
		click(driver, Selectlist1);

		wait(driver, "1");
		waitForElement(driver, tablevalues);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@alt='Export As Excel']")));

		WebElement Exportexcel = driver.findElement(By.xpath("//*[@alt='Export As Excel']"));
	    act.moveToElement(Exportexcel).build().perform();
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
		
		
		wait(driver, "6");

		String Master = System.getProperty("user.dir") + "\\uploads\\Scenario7\\MasterFiles\\Master_" + path
				+ ".xlsx";

		String autoITExecutableTest1 = System.getProperty("user.dir") + "\\driver\\TestDownloadfile.exe "
				+ Master;

		try {
			Runtime.getRuntime().exec(autoITExecutableTest1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Created successfully')]")));

		Fillocomparison(Master,Test,test,testDetail,detailreportPath);

		Createexcel(Master, Test, Finalresultpath,test);

		try {
			compareContainerData(filePath_M,filePath_T);
		}catch(Exception e) {
			System.out.println("Container data comparision failed");
		}
		
		
	}

}
