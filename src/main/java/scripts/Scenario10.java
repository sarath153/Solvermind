package scripts;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

public class Scenario10 extends Keywords{

	
	String URL = TestNgXml.getdatafromExecution().get("Scenario10");
	String Username = Utils.getDataFromTestData("Solverminds", "Username");
	String Password = Utils.getDataFromTestData("Solverminds", "Password");

	public static ExtentTest test;
	private static LocalDateTime currentDateTime = LocalDateTime.now();
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");
	private static String formattedDateTime = currentDateTime.format(formatter) + "_";

	boolean flag = true;

	@SuppressWarnings("deprecation")
	public void Semiautorun10(WebDriver driver, String vessel2,ExtentTest test ,ExtentTest testDetail,String detailreportPath) throws Exception {
		String vessel=vessel2;
		String DSW_GM = Utils.getDataFromTestData(vessel, "DSW_GM");
		String VWR_ = Utils.getDataFromTestData(vessel, "VWR");
		String MasterPlanFile = Utils.getDataFromTestData(vessel, "ResultPlanFile");
		String TestPlanFile = Utils.getDataFromTestData(vessel, "BasePlanFile");
		String Vesselcode = Vesselname(vessel);
		String NewTime = formattedDateTime+Vesselcode;
		String Finalresultpath = System.getProperty("user.dir") + "\\Uploads\\Scenario10\\Resultexcel"+NewTime+".xlsx";
		String path = NewTime;
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions action=new Actions(driver);
		
		navigateUrl(driver, URL);
		waitForElement(driver, username);
		sendKeys(driver, username, Username);

		waitForElement(driver, password);
		sendKeys(driver, password, Password);

		waitForElement(driver, login);
		click(driver, login);

		waitForElement(driver, selectvessel);
		click(driver, selectvessel);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'"+vessel+"')]")));
		
		driver.findElement(By.xpath("//*[contains(text(),'"+vessel+"')]")).click();

		waitForElement(driver, Openplantop);

		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();

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

		String plan1 = getText(driver, planOpened);

		if (plan1.contains(MasterPlanFile)) {
			System.out.println("Correct Plan is Opened");
			add(driver, "Correct Plan is Opened : " + plan1, LogAs.PASSED, true, "");
		} else {
			System.out.println("Different Plan is Opened");
			add1(driver, "Different Plan is opened : " + plan1, LogAs.FAILED, true, "");
		}


		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait(driver, "1");

		waitForElement(driver,PreviousBay);
		if (flag == true) {
			takescreenshot1(driver, "/Expected_screenshot/Scenario10/Bay1");
			wait(driver, "1");
		}
		
		int i = 2;
		while (isDisplayed(driver, PreviousBay)) {
			click(driver, PreviousBay);

			if (flag == true) {
				takescreenshot1(driver, "/Expected_screenshot/Scenario10/Bay" + i);

				wait(driver, "1");
			}

			i++;
		}

		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();

		wait(driver,"3");
		// master container data ...
		int colCount_M = columnCountValue(driver);
		int rowCount_M = RowCountValue(driver);

		ArrayList<String> headerList_M = headerValueList(driver, colCount_M);

		Map<String, ArrayList<String>> cellmap_M = cellListMap(driver, colCount_M, rowCount_M, headerList_M);

		// important
		String filePath_M = System.getProperty("user.dir") + "\\uploads\\Scenario10\\ContainerPoolData\\MasterData_"
				+ path + ".xlsx";

		System.out.println("Path_T : "+filePath_M);
		createExcelForContainerPool(colCount_M, rowCount_M, headerList_M, cellmap_M, filePath_M);

		wait(driver, "1");
	
		
		waitForElement(driver, Search);
		click(driver, Search);

		waitForElement(driver, SearchInput);
		sendKeys(driver, SearchInput, "Cargo list");

		waitForElement(driver, Selectlist1);
		click(driver, Selectlist1);

		System.out.println("First excel..");	

		waitForElement(driver, tablevalues);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@alt='Export As Excel']")));

		WebElement Exportexcel1 = driver.findElement(By.xpath("//*[@alt='Export As Excel']"));
	    action.moveToElement(Exportexcel1).build().perform();
	    Exportexcel1.click();
	    
		wait(driver, "6");
		
		while(isDisplayed(driver,excelcheck)) {
			System.out.println("ReClicked.");
			try {
			Exportexcel1.click();
			}catch(Exception e) {
			  System.out.println("Not able click export excel.");	
			}
			wait(driver, "6");
		}
		
		// Press Enter to confirm the file download dialog
		//robot.keyRelease(KeyEvent.VK_SHIFT);
		wait(driver, "6");

		String Master = System.getProperty("user.dir")+"\\uploads\\Scenario10\\MasterFiles\\Master_" + path + ".xlsx";

		wait(driver,"1");
		String autoITExecutableTest1 = System.getProperty("user.dir")+"\\driver\\MasterDownloadfile.exe " + Master;
		wait(driver,"1");

		try {
		    Runtime.getRuntime().exec(autoITExecutableTest1);
		} catch (IOException e) {
		    e.printStackTrace();
		}

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Created successfully')]")));
		
	
		
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

			clickVessel(driver, vessel);

		} else if (isDisplayed(driver, Home_Icon)) {
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
				waitForElement(driver,planDescription);
				sendKeys(driver,planDescription,TestPlanFile);
				// new
				clickPlan(driver, TestPlanFile);
				waitForElement(driver, planOpened);
				
				String plan = getText(driver, planOpened);

				if (plan.contains(TestPlanFile)) {
					System.out.println("Correct Plan is Opened");
					add(driver, "Correct Plan is Opened : " + plan, LogAs.PASSED, true, "");
				} else {
					System.out.println("Different Plan is Opened");
					add1(driver, "Different Plan is opened : " + plan, LogAs.FAILED, true, "");
				}
				
				
				waitForElement(driver, optimiser);
				click(driver, optimiser);
				
				waitForElement(driver,masterPattern);
				click(driver,masterPattern);
				
				waitForElement(driver, VWR);
				click(driver, VWR);
			
				clickVWR(driver,VWR_);
				
				waitForElement(driver, Run);
				click(driver, Run);
				
				waitForElement1(driver,success);
				
				waitForElement(driver,runOk);
				click(driver,runOk);
				
				while (isDisplayed(driver, Run)) {
			        action.sendKeys(Keys.ESCAPE).build().perform();
				}
				
				
				// need to add Master Plan view action
//				driver.findElement(By.xpath("//option[contains(@value,'"+VWR_+"')]")).click();
                 waitForElement(driver,masterPlanView);
                 click(driver,masterPlanView);
                 wait(driver,"2");
				
                 if (flag == true) {
     				takescreenshot(driver, "/Scenario10/MasterPlanView/Test_" + path);

     				wait(driver, "1");
     			}
                 
				// need to add Master Plan view action
				
                 waitForElement(driver,masterPlanView);
                 click(driver,masterPlanView);
                 
				waitForElement(driver, optimiser);
				click(driver, optimiser);
				
				waitForElement(driver,semiAutoPlan);
				click(driver,semiAutoPlan);
				
				waitForElement(driver, Run);
				click(driver, Run);
				
				
				waitForElement1(driver,Success);
				WebElement Success = driver.findElement(By.xpath("(//*[@alt='Partial Success']//following::span)[1]"));

				String message = Success.getText();

				System.out.println(message);

				waitForElement(driver, Ok_BTN);
				click(driver, Ok_BTN);

				while (isDisplayed(driver, Run)) {
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

				sendKeys(driver, Saveinput, "TD Testplan Scenario10_"+path);

				waitForElement(driver, Clickplanok);
				click(driver, Clickplanok);

				waitForElement(driver, Bay1);
				doubleClick(driver, Bay1);

				wait(driver, "3");

				if (flag = true) {
					takescreenshot1(driver, "/Actual_screenshot/Scenario10/Bay1");
					try {
						imageComparison(driver, "/Scenario10/Bay1", "/Scenario10/Bay1");
						wait(driver, "2");
					} catch (IOException e1) {

						e1.printStackTrace();
					}
				}

				int j = 2;

				while (isDisplayed(driver, PreviousBay)) {
					click(driver, PreviousBay);

					if (flag == true) {
						takescreenshot1(driver, "/Actual_screenshot/Scenario10/Bay" + j);

						try {
							imageComparison(driver, "/Scenario10/Bay" + j, "/Scenario10/Bay" + j);
							wait(driver, "2");
						} catch (IOException e1) {

							e1.printStackTrace();
						}
						wait(driver, "1");
					}

					j++;
				}
				
				driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();
				
				int colCount_T = columnCountValue(driver);
				int rowCount_T = RowCountValue(driver);

				ArrayList<String> headerList_T = headerValueList(driver, colCount_T);

				Map<String, ArrayList<String>> cellmap_T = cellListMap(driver, colCount_T, rowCount_T, headerList_T);

				LocalDateTime currentDateTime_T = LocalDateTime.now();
				DateTimeFormatter formatter_T = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");
				String formattedDateTime_T = currentDateTime_T.format(formatter_T) + "_";
				wait(driver, "1");
				String Testpath_T = formattedDateTime_T + vessel;

				// important
				String filePath_T = System.getProperty("user.dir") + "\\uploads\\Scenario10\\ContainerPoolData\\testData_"
						+ path + ".xlsx";

				System.out.println("Path_T : "+filePath_T);
				createExcelForContainerPool(colCount_T, rowCount_T, headerList_T, cellmap_T, filePath_T);

				wait(driver, "1");
			
				
				
				waitForElement(driver, Search);
				click(driver, Search);

				waitForElement(driver, SearchInput);
				sendKeys(driver, SearchInput, "Cargo list");

				waitForElement(driver, Selectlist1);
				click(driver, Selectlist1);

				  System.out.println("Second excel..");	

				waitForElement(driver, tablevalues);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@alt='Export As Excel']")));
				WebElement Exportexcel = driver.findElement(By.xpath("//*[@alt='Export As Excel']"));
				wait(driver,"1");
			    action.moveToElement(Exportexcel).build().perform();
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
				//robot.keyRelease(KeyEvent.VK_SHIFT);
				wait(driver, "6");

				String Test = System.getProperty("user.dir")+"\\uploads\\Scenario10\\TestFiles\\Test_" + path + ".xlsx";

				wait(driver,"1");
				String autoITExecutableTest = System.getProperty("user.dir")+ "\\driver\\TestDownloadfile.exe " + Test;
				wait(driver,"1");

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
