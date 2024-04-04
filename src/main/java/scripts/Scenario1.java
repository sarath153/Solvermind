package scripts;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonMethods.Keywords;
import commonMethods.TestNgXml;
import commonMethods.Utils;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

import com.aventstack.extentreports.ExtentTest;

import atu.testng.reports.logging.LogAs;


public class Scenario1 extends Keywords {

	String URL = TestNgXml.getdatafromExecution().get("Scenario1");
	String Username = Utils.getDataFromTestData("Solverminds", "Username");
	String Password = Utils.getDataFromTestData("Solverminds", "Password");
	boolean flag = true;

	public static ExtentTest test;
	private static LocalDateTime currentDateTime = LocalDateTime.now();
	
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");
	private static String formattedDateTime = currentDateTime.format(formatter) + "_";


	public void Semiautorun(WebDriver driver, String vessel2,ExtentTest test ,ExtentTest testDetail,String detailreportPath) throws Exception {
		
		String vessel=vessel2;
		
		String DSW_GM = Utils.getDataFromTestData(vessel, "DSW_GM");
		String VWR_ = Utils.getDataFromTestData(vessel, "VWR");
		String PlanType = Utils.getDataFromTestData(vessel, "PlanTemplate");
		String MasterPlanFile = Utils.getDataFromTestData(vessel, "MasterPlanFile");
		String TestPlanFile = Utils.getDataFromTestData(vessel, "TestPlanFile");
		String Vesselcode = Vesselname(vessel);
		String NewTime = formattedDateTime+Vesselcode;
		String Finalresultpath = System.getProperty("user.dir") + "/Uploads/Scenario1/Resultexcel"+NewTime+".xlsx";
		String path = NewTime;	
		

		Robot robot = new Robot();
		WebDriverWait wait = new WebDriverWait(driver, 30);

		navigateUrl(driver, URL);
		waitForElement(driver, username);
		sendKeys(driver, username, Username);

		waitForElement(driver, password);
		sendKeys(driver, password, Password);

		waitForElement(driver, login);
		click(driver, login);
		/*
		 * waitForElement(driver, selectvessel); click(driver, selectvessel);
		 * 
		 * clickVessel(driver,vessel); waitForElement(driver, Openplantop);
		 * 
		 * driver.findElement(By.
		 * xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']"
		 * )).click();
		 * 
		 * waitForElement(driver, Openplantop); click(driver, Openplantop);
		 * 
		 * waitForElement(driver, Globalplan); click(driver, Globalplan);
		 * 
		 * waitForElement(driver, clickOk); click(driver, clickOk);
		 * 
		 * //new waitForElement(driver,planDescription);
		 * sendKeys(driver,planDescription,MasterPlanFile); // new
		 * 
		 * clickPlan(driver,MasterPlanFile);
		 * 
		 * waitForElement(driver, planOpened);
		 * 
		 * String plan = getText(driver, planOpened);
		 * 
		 * if (plan.contains(MasterPlanFile)) {
		 * System.out.println("Correct Plan is Opened"); add(driver,
		 * "Correct Plan is Opened : " + plan, LogAs.PASSED, true, ""); } else {
		 * System.out.println("Different Plan is Opened"); add1(driver,
		 * "Different Plan is opened : " + plan, LogAs.FAILED, true, ""); }
		 * 
		 * waitForElement(driver, Bay1); doubleClick(driver, Bay1);
		 * 
		 * wait(driver, "1");
		 * 
		 * waitForElement(driver,PreviousBay); if (flag == true) {
		 * takescreenshot1(driver, "/Expected_screenshot/Scenario1/Bay1"); wait(driver,
		 * "1"); }
		 * 
		 * int i = 2; while (isDisplayed(driver, PreviousBay)) { click(driver,
		 * PreviousBay);
		 * 
		 * if (flag == true) { takescreenshot1(driver,
		 * "/Expected_screenshot/Scenario1/Bay" + i);
		 * 
		 * wait(driver, "1"); }
		 * 
		 * i++; }
		 * 
		 * driver.findElement(By.
		 * xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']"
		 * )).click();
		 * 
		 * wait(driver,"3"); // Master container data ... int colCount_M =
		 * columnCountValue(driver); int rowCount_M = RowCountValue(driver);
		 * 
		 * ArrayList<String> headerList_M = headerValueList(driver, colCount_M);
		 * 
		 * Map<String, ArrayList<String>> cellmap_M = cellListMap(driver, colCount_M,
		 * rowCount_M, headerList_M);
		 * 
		 * // important String filePath_M = System.getProperty("user.dir") +
		 * "\\uploads\\Scenario1\\ContainerPoolData\\MasterData_" + path + ".xlsx";
		 * 
		 * System.out.println("Path_T : "+filePath_M);
		 * createExcelForContainerPool(colCount_M, rowCount_M, headerList_M, cellmap_M,
		 * filePath_M);
		 * 
		 * 
		 * 
		 * waitForElement(driver, Search); click(driver, Search);
		 * 
		 * waitForElement(driver, SearchInput); sendKeys(driver, SearchInput,
		 * "Cargo list");
		 * 
		 * waitForElement(driver, Selectlist1); click(driver, Selectlist1);
		 * 
		 * wait(driver,"5");
		 * 
		 * waitForElement(driver, tablevalues);
		 * wait.until(ExpectedConditions.elementToBeClickable(By.
		 * xpath("//*[@alt='Export As Excel']")));
		 * 
		 * WebElement Exportexcel =
		 * driver.findElement(By.xpath("//*[@alt='Export As Excel']")); Actions builder
		 * = new Actions(driver); builder.moveToElement(Exportexcel).click(Exportexcel);
		 * builder.perform(); wait(driver,"6"); while(isDisplayed(driver,excelcheck)) {
		 * System.out.println("ReClicked."); try { Exportexcel.click(); }catch(Exception
		 * e) { System.out.println("Not able click export excel."); } wait(driver, "6");
		 * } wait(driver, "6");
		 * 
		 * String Master = System.getProperty("user.dir") +
		 * "\\Uploads\\Scenario1\\MasterFiles\\Masterfile_" + path + ".xlsx"; String
		 * autoITExecutable = System.getProperty("user.dir") +
		 * "/driver/MasterDownloadfile.exe " + Master;
		 * 
		 * try { Runtime.getRuntime().exec(autoITExecutable); } catch (IOException e) {
		 * e.printStackTrace(); } wait.until(ExpectedConditions
		 * .visibilityOfElementLocated(By.
		 * xpath("//*[contains(text(),'Created successfully')]")));
		 * 
		 * // // Test Plan
		 * 
		 * newTab2(driver); wait(driver, "2"); switchtotab(driver, 1);
		 * robot.keyRelease(KeyEvent.VK_CONTROL); navigateUrl(driver, URL); if
		 * (isDisplayed1(driver, username)) {
		 * 
		 * sendKeys(driver, username, Username);
		 * 
		 * waitForElement(driver, password); sendKeys(driver, password, Password);
		 * 
		 * waitForElement(driver, login); click(driver, login); waitForElement(driver,
		 * selectvessel); click(driver, selectvessel);
		 * 
		 * clickVessel(driver,vessel);
		 * 
		 * } else if (isDisplayed1(driver, Home_Icon)) { waitForElement(driver,
		 * Home_Icon); click(driver, Home_Icon); waitForElement(driver, Exit_popup);
		 * waitForElement(driver, ExitBtn); click(driver, ExitBtn);
		 * 
		 * //
		 * driver.findElement(By.xpath("//input[@id='demo-simple-select-outlined']")).
		 * click(); waitForElement(driver, selectvessel); click(driver, selectvessel);
		 * 
		 * clickVessel(driver,vessel);
		 * 
		 * }
		 * 
		 * waitForElement(driver, Openplantop); driver.findElement(By.
		 * xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']"
		 * )).click(); waitForElement(driver, Bay1); click(driver, Openplantop);
		 * 
		 * waitForElement(driver, Globalplan); click(driver, Globalplan);
		 * 
		 * waitForElement(driver, clickOk); click(driver, clickOk);
		 * 
		 * 
		 * //new waitForElement(driver,planDescription);
		 * sendKeys(driver,planDescription,TestPlanFile); // new
		 * 
		 * clickPlan(driver,TestPlanFile);
		 * 
		 * waitForElement(driver, planOpened);
		 * 
		 * String plan1 = getText(driver, planOpened);
		 * 
		 * if (plan1.contains(TestPlanFile)) {
		 * System.out.println("Correct Plan is Opened"); add(driver,
		 * "Correct Plan is Opened : " + plan1, LogAs.PASSED, true, ""); } else {
		 * System.out.println("Different Plan is Opened"); add1(driver,
		 * "Different Plan is opened : " + plan1, LogAs.FAILED, true, ""); }
		 * 
		 * wait(driver,"2");
		 * 
		 * waitForElement(driver, Allbayplan); click(driver, Allbayplan);
		 * 
		 * waitForElement(driver, RobRefresh); click(driver, RobRefresh);
		 * 
		 * wait(driver, "5"); click(driver, RobRefresh);
		 * 
		 * waitForElement(driver, optimiser); click(driver, optimiser);
		 * 
		 * waitForElement(driver, Plantemplate); click(driver, Plantemplate);
		 * 
		 * // waitForElement(driver, Lashing); // click(driver, Lashing);
		 * 
		 * clickPlanTemplate(driver,PlanType);
		 * 
		 * waitForElement(driver, VWR); click(driver, VWR);
		 * 
		 * clickVWR(driver,VWR_);
		 * 
		 * click(driver,DSWGM); wait(driver,"1"); doubleClick(driver,DSWGM);
		 * wait(driver,"1"); sendKeys(driver, DSWGM, DSW_GM);
		 * 
		 * waitForElement(driver, Run); click(driver, Run);
		 * 
		 * waitForElement1(driver, ClickOk); click(driver, ClickOk);
		 * 
		 * wait(driver, "5");
		 * 
		 * doubleClick(driver, Canvas2);
		 * 
		 * waitForElement(driver, Saveplan); click(driver, Saveplan);
		 * 
		 * waitForElement(driver, Globalsave); click(driver, Globalsave);
		 * 
		 * waitForElement(driver, clickOk); click(driver, clickOk);
		 * 
		 * waitForElement(driver, Saveinput); click(driver, Saveinput);
		 * 
		 * sendKeys(driver, Saveinput, "TD Testplan Scenario1");
		 * 
		 * waitForElement(driver, Clickplanok); click(driver, Clickplanok);
		 * 
		 * waitForElement(driver, Bay1); doubleClick(driver, Bay1);
		 * 
		 * wait(driver, "3");
		 * 
		 * if (flag = true) { takescreenshot1(driver,
		 * "/Actual_screenshot/Scenario1/Bay1"); try { imageComparison(driver,
		 * "/Scenario1/Bay1", "/Scenario1/Bay1"); wait(driver, "2"); } catch
		 * (IOException e1) {
		 * 
		 * e1.printStackTrace(); } }
		 * 
		 * int j = 2;
		 * 
		 * while (isDisplayed(driver, PreviousBay)) { click(driver, PreviousBay);
		 * 
		 * if (flag == true) { takescreenshot1(driver,
		 * "/Actual_screenshot/Scenario1/Bay" + j);
		 * 
		 * try { imageComparison(driver, "/Scenario1/Bay" + j, "/Scenario1/Bay" + j);
		 * wait(driver, "2"); } catch (IOException e1) {
		 * 
		 * e1.printStackTrace(); } wait(driver, "1"); }
		 * 
		 * j++; }
		 * 
		 * 
		 * driver.findElement(By.
		 * xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']"
		 * )).click(); wait(driver,"3");
		 * 
		 * // Test container data ... int colCount_T = columnCountValue(driver); int
		 * rowCount_T = RowCountValue(driver);
		 * 
		 * ArrayList<String> headerList_T = headerValueList(driver, colCount_T);
		 * 
		 * Map<String, ArrayList<String>> cellmap_T = cellListMap(driver, colCount_T,
		 * rowCount_T, headerList_T);
		 * 
		 * // important String filePath_T = System.getProperty("user.dir") +
		 * "\\uploads\\Scenario1\\ContainerPoolData\\TestData_" + path + ".xlsx";
		 * 
		 * System.out.println("Path_T : "+filePath_T);
		 * createExcelForContainerPool(colCount_T, rowCount_T, headerList_T, cellmap_T,
		 * filePath_T);
		 * 
		 * waitForElement(driver, Search); click(driver, Search);
		 * 
		 * waitForElement(driver, SearchInput); sendKeys(driver, SearchInput,
		 * "Cargo list");
		 * 
		 * waitForElement(driver, Selectlist1); click(driver, Selectlist1); wait(driver,
		 * "2");
		 * 
		 * waitForElement(driver, tablevalues);
		 * 
		 * wait.until(ExpectedConditions.elementToBeClickable(By.
		 * xpath("//*[@alt='Export As Excel']"))); WebElement Exportexcel1 =
		 * driver.findElement(By.xpath("//*[@alt='Export As Excel']")); Actions act =
		 * new Actions(driver); act.moveToElement(Exportexcel1).build().perform();
		 * Exportexcel1.click(); wait(driver, "6");
		 * 
		 * while(isDisplayed(driver,excelcheck)) { System.out.println("ReClicked."); try
		 * { Exportexcel.click(); }catch(Exception e) {
		 * System.out.println("Not able click export excel."); } wait(driver, "6"); }
		 * 
		 * wait(driver, "6");
		 * 
		 * LocalDateTime currentDateTime1 = LocalDateTime.now(); String
		 * formattedDateTime1 = currentDateTime1.format(formatter) + "_";
		 * 
		 * wait(driver, "1"); String Testpath = formattedDateTime1+Vesselcode;
		 * 
		 * String Test = System.getProperty("user.dir") +
		 * "\\Uploads\\Scenario1\\TestFiles\\Testfile_" + Testpath + ".xlsx";
		 * 
		 * String autoITExecutableTest = System.getProperty("user.dir") +
		 * "./driver/TestDownloadfile.exe " + Test;
		 * 
		 * try { Runtime.getRuntime().exec(autoITExecutableTest); } catch (IOException
		 * e) { e.printStackTrace(); }
		 * 
		 * wait.until(ExpectedConditions .visibilityOfElementLocated(By.
		 * xpath("//*[contains(text(),'Created successfully')]")));
		 * 
		 * 
		 * Fillocomparison(Master,Test,test,testDetail,detailreportPath);
		 * 
		 * Createexcel(Master,Test,Finalresultpath,test);
		 * 
		 * compareContainerData(filePath_M,filePath_T);
		 */

	}

}
