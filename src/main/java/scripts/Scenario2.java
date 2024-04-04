package scripts;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class Scenario2 extends Keywords {

	String URL = TestNgXml.getdatafromExecution().get("Scenario2");
	String Username = Utils.getDataFromTestData("Solverminds", "Username");
	String Password = Utils.getDataFromTestData("Solverminds", "Password");

	public static ExtentTest test;
	private static LocalDateTime currentDateTime = LocalDateTime.now();
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");
	private static String formattedDateTime = currentDateTime.format(formatter) + "_";

	
	boolean flag = true;

	@SuppressWarnings("deprecation")
	public void Semiautorun2(WebDriver driver, String vessel2,ExtentTest test ,ExtentTest testDetail,String detailreportPath) throws Exception {
		String vessel=vessel2;
		String DSW_GM = Utils.getDataFromTestData(vessel, "DSW_GM");
		String VWR_ = Utils.getDataFromTestData(vessel, "VWR");
		String PlanType = Utils.getDataFromTestData(vessel, "PlanTemplate");
		String MasterPlanFile = Utils.getDataFromTestData(vessel, "MasterPlanFile");
		String TestPlanFile = Utils.getDataFromTestData(vessel, "TestPlanFile");
		String Vesselcode = Vesselname(vessel);
		String NewTime = formattedDateTime+Vesselcode;
		String Finalresultpath = System.getProperty("user.dir") + "\\Uploads\\Scenario2\\Resultexcel"+NewTime+".xlsx";
		String path = NewTime;
	
//		Robot robot = new Robot();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		
		int[] CJV_Start_X= {-1150,-600,-1000,-650,-600,-600,-650,-700,-700,-600,-750,-700,-700,-600,-600,-600,-700,-700,-500,-700,-700,-700,-700,-500};
		int[] CJV_Start_Y= {20,-50,-70,-65,-70,-60,-70,-80,-85,-70,-85,-80,-80,-80,-80,-80,-70,-80,-70,-80,-80,-80,-90,-80};

		int[] CJV_End_X= {600,1000,650,650,650,650,650,650,700,750,650,650,650,650,650,650,650,650,700,650,650,650,650,700};
		int[] CJV_End_Y= {50,70,70,60,70,70,70,80,80,80,80,80,80,80,80,80,70,70,130,65,70,80,60,120};

		
		
//		WebDriverWait wait = new WebDriverWait(driver, 20);
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

		
		//new
		waitForElement(driver,planDescription);
		sendKeys(driver,planDescription,MasterPlanFile);
		// new
		clickPlan(driver, MasterPlanFile);
		waitForElement(driver, planOpened);
		
		String plan = getText(driver, planOpened);

		if (plan.contains(MasterPlanFile)) {
			System.out.println("Correct Plan is Opened");
			add(driver, "Correct Plan is Opened : " + plan, LogAs.PASSED, true, "");
		} else {
			System.out.println("Different Plan is Opened");
			add1(driver, "Different Plan is opened : " + plan, LogAs.FAILED, true, "");
		}
		
		
		
		waitForElement(driver, dropdown);
		click(driver, dropdown);

		waitForElement(driver, Planpattern);
		click(driver, Planpattern);

		waitForElement(driver, planpatterndropdown);
		click(driver, planpatterndropdown);

		waitForElement(driver, PODPreference);
		click(driver, PODPreference);
		
		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait(driver, "2");

		int i = 2;
		while (isDisplayed(driver, PreviousBay)) {
		    click(driver, PreviousBay);

		    if (flag == true) {
		        takescreenshot1(driver, "/Expected_screenshot/Scenario2/Bay" + i);
		        
		       
		        wait(driver, "1");
		    }

		    i++;
		}

	     driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();
		
		wait(driver,"3");
		
		// Master container data ...
				int colCount_M = columnCountValue(driver);
				int rowCount_M = RowCountValue(driver);

				ArrayList<String> headerList_M = headerValueList(driver, colCount_M);

				Map<String, ArrayList<String>> cellmap_M = cellListMap(driver, colCount_M, rowCount_M, headerList_M);

				// important
				String filePath_M = System.getProperty("user.dir") + "\\uploads\\Scenario2\\ContainerPoolData\\MasterData_"
						+ path + ".xlsx";

				System.out.println("Path_T : "+filePath_M);
				createExcelForContainerPool(colCount_M, rowCount_M, headerList_M, cellmap_M, filePath_M);

		waitForElement(driver, Search);
		click(driver, Search);

		waitForElement(driver, SearchInput);
		sendKeys(driver, SearchInput, "Cargo list");

		waitForElement(driver, Selectlist1);
		click(driver, Selectlist1);

//		waitForElement(driver, ExportExcel);
//		click(driver, ExportExcel);
//	
		WebDriverWait wait2 = new WebDriverWait(driver, 120);
		wait(driver, "3");
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
		wait(driver, "5");

		LocalDateTime currentDateTime = LocalDateTime.now();

		// Format the date and time as a string
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");
		String formattedDateTime = currentDateTime.format(formatter)+"_";
		wait(driver,"1");
		String Masterpath= formattedDateTime+vessel;

		String Master = System.getProperty("user.dir")+"\\uploads\\Scenario2\\MasterFiles\\Master_" + path + ".xlsx";
		
		System.out.println("Location : "+Master);
		
		String autoITExecutable = System.getProperty("user.dir")+"\\driver\\MasterDownloadfile.exe " + Master;

		try {
		    Runtime.getRuntime().exec(autoITExecutable);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Created successfully')]")));
		
		//Test Plan

		newTab2(driver);

		navigateUrl(driver, URL);

		if (isDisplayed(driver, username)) {

			sendKeys(driver, username, Username);

			waitForElement(driver, password);
			sendKeys(driver, password, Password);

			waitForElement(driver, login);
			click(driver, login);
			waitForElement(driver, selectvessel);
			click(driver, selectvessel);

			clickVessel(driver,vessel);
		} else if (isDisplayed(driver, Home_Icon)) {
			click(driver, Home_Icon);
			waitForElement(driver, Exit_popup);
			waitForElement(driver, ExitBtn);
			click(driver, ExitBtn);

			waitForElement(driver, selectvessel);
			click(driver, selectvessel);

			clickVessel(driver,vessel);

		}
		

		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@class='logo-icon']")));

		waitForElement(driver, Openplantop);
		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();

		waitForElement(driver, Openplantop);
		click(driver, Openplantop);

		waitForElement(driver, Globalplan);
		click(driver, Globalplan);

		waitForElement(driver, clickOk);
		click(driver, clickOk);

		//new
		waitForElement(driver,planDescription);
		sendKeys(driver,planDescription,TestPlanFile);
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
      
		wait(driver,"2");

		List<WebElement> tot=driver.findElements(By.xpath("//div[text()='All Bay Plan']//following::canvas"));
		int totalSize=tot.size();
		//need to use
		
		waitForElement(driver, selectWindow);
		click(driver, selectWindow);

		waitForElement(driver, internal);
		click(driver, internal);

		waitForElement(driver, Containerpool);
		click(driver, Containerpool);

		
		waitForElement(driver,groupValue);
		doubleClick(driver,groupValue);

		waitForElement(driver, planPattern_);

		waitForElement(driver, mini);
		driver.findElement(By.xpath("//div[text()='Container Pool']//following::button[@aria-label='Close']"))
				.click();

		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait(driver,"4");

		for(int k=0;k<totalSize;k++) {
			
			Actions action = new Actions(driver);

			wait(driver,"1");
			action.moveByOffset(CJV_Start_X[k], CJV_Start_Y[k]).clickAndHold().moveByOffset(CJV_End_X[k], CJV_End_Y[k]).pause(Duration.ofSeconds(1)).release()
			.build().perform();
			
			if (isDisplayed(driver, clickyes)) {
				click(driver, clickyes);
			} else {
				System.out.println("Element dragged");
			}
			
			
			if(k!=(totalSize-1)) {
				waitForElement(driver, PreviousBay);
				click(driver, PreviousBay);
			}

		}
		
	

		wait(driver, "1");
//		waitForElement(driver, bayClose);
//		click(driver, bayClose);
		bayClose(driver);
		waitForElement(driver, optimiser);
		click(driver, optimiser);

		waitForElement(driver, Plantemplate);
		click(driver, Plantemplate);

//		waitForElement(driver, Lashing);
//		click(driver, Lashing);
		
		clickPlanTemplate(driver,PlanType);

		waitForElement(driver, VWR);
		click(driver, VWR);

		clickVWR(driver,VWR_);

		click(driver,DSWGM);
		wait(driver,"1");
		doubleClick(driver,DSWGM);
		wait(driver,"1");
		sendKeys(driver, DSWGM, DSW_GM);

		waitForElement(driver, Run);
		click(driver, Run);

		WebDriverWait wait3 = new WebDriverWait(driver, 12000);

		wait3.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@alt='Partial Success']")));
		WebElement Success = driver.findElement(By.xpath("//*[@alt='Partial Success']"));

		String message = Success.getText();

		System.out.println(message);

		waitForElement(driver, Ok_BTN);
		click(driver, Ok_BTN);

		Actions action=new Actions(driver);
		while (isDisplayed(driver, Run)) {
//			
          action.sendKeys(Keys.ESCAPE).build().perform();
		}
		waitForElement(driver, Saveplan);
		click(driver, Saveplan);

		waitForElement(driver, Globalsave);
		click(driver, Globalsave);

		waitForElement(driver, clickOk);
		click(driver, clickOk);

		waitForElement(driver, Saveinput);
		click(driver, Saveinput);

		sendKeys(driver, Saveinput, "TD Testplan Scenario2_"+path);

		waitForElement(driver, Clickplanok);
		click(driver, Clickplanok);

		// Taking Actual Screenshots

		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait(driver, "3");

		if (flag = true) {
			takescreenshot1(driver, "/Actual_screenshot/Scenario2/Bay1");

			try {
				imageComparison(driver, "/Scenario2/Bay1", "/Scenario2/Bay1");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		int j=2;

		while (isDisplayed(driver, PreviousBay)) {
		    click(driver, PreviousBay);

		    if (flag == true) {
		        takescreenshot1(driver, "/Actual_screenshot/Scenario2/Bay" + j);
		        
		        try {
					imageComparison(driver, "/Scenario2/Bay" + j, "/Scenario2/Bay" + j);
					wait(driver, "2");
				} catch (IOException e1) {

					e1.printStackTrace();
				}
		        wait(driver, "1");
		    }

		    j++;
		}

		wait(driver, "1");
		bayClose(driver);
		wait(driver,"1");
		
		waitForElement(driver, selectWindow);
		click(driver, selectWindow);

		waitForElement(driver, external);
		click(driver, external);
		
	    driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();
		
		wait(driver,"3");
		
		// Test container data ...
				int colCount_T = columnCountValue(driver);
				int rowCount_T = RowCountValue(driver);

				ArrayList<String> headerList_T = headerValueList(driver, colCount_T);

				Map<String, ArrayList<String>> cellmap_T = cellListMap(driver, colCount_T, rowCount_T, headerList_T);

				// important
				String filePath_T = System.getProperty("user.dir") + "\\uploads\\Scenario2\\ContainerPoolData\\TestData_"
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
		// Press Enter to confirm the file download dialog
		wait(driver, "6");


		String Test = System.getProperty("user.dir")+"\\uploads\\Scenario7\\TestFiles\\Test_" + path + ".xlsx";

		String autoITExecutableTest = System.getProperty("user.dir")+"\\driver\\TestDownloadfile.exe " + Test;

		try {
		    Runtime.getRuntime().exec(autoITExecutableTest);
		} catch (IOException e) {
		    e.printStackTrace();
		}

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Created successfully')]")));
	
		
		Fillocomparison(Master,Test,test,testDetail,detailreportPath);
		
		Createexcel(Master,Test,Finalresultpath,test);
		
		compareContainerData(filePath_M,filePath_T);
	}

	
}
