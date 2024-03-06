package scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonMethods.Keywords;
import commonMethods.TestNgXml;
import commonMethods.Utils;

public class Scenario2 extends Keywords {

	String URL = TestNgXml.getdatafromExecution().get("Scenario2");
	String Username = Utils.getDataFromTestData("Solverminds", "Username");
	String Password = Utils.getDataFromTestData("Solverminds", "Password");
	String Vessel = TestNgXml.getdatafromExecution1().get("Scenario2");
	String DSW_GM = Utils.getDataFromTestData(Vessel, "DSW_GM");
	String MasterPlanFile = Utils.getDataFromTestData(Vessel, "MasterPlanFile");
	String TestPlanFile = Utils.getDataFromTestData(Vessel, "TestPlanFile");
	
	boolean flag = true;

	@SuppressWarnings("deprecation")
	public void Semiautorun2(WebDriver driver) throws AWTException {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		navigateUrl(driver, URL);
		waitForElement(driver, username);
		sendKeys(driver, username, Username);

		waitForElement(driver, password);
		sendKeys(driver, password, Password);

		waitForElement(driver, login);
		click(driver, login);

		waitForElement(driver, selectvessel);
		click(driver, selectvessel);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'"+Vessel+"')]")));
		
		driver.findElement(By.xpath("//*[contains(text(),'"+Vessel+"')]")).click();

		waitForElement(driver, Openplantop);

		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']"))
				.click();

		waitForElement(driver, Openplantop);
		click(driver, Openplantop);

		waitForElement(driver, Globalplan);
		click(driver, Globalplan);

		waitForElement(driver, clickOk);
		click(driver, clickOk);

//		waitForElement(driver, VAPPlan_763);
//		doubleClick(driver, VAPPlan_763);

		WebElement Masterfile = driver.findElement(By.xpath("//*[contains(text(),'" + MasterPlanFile + "')]"));
		wait.until(ExpectedConditions.elementToBeClickable(Masterfile));

		Actions action1 = new Actions(driver).doubleClick(Masterfile);
		action1.build().perform();
		
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

		waitForElement(driver, Search);
		click(driver, Search);

		waitForElement(driver, SearchInput);
		sendKeys(driver, SearchInput, "Cargo list");

		waitForElement(driver, Selectlist1);
		click(driver, Selectlist1);

		waitForElement(driver, ExportExcel);
		click(driver, ExportExcel);
	
		wait(driver, "10"); // Adjust as needed
	
		LocalDateTime currentDateTime = LocalDateTime.now();

		// Format the date and time as a string
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");
		String formattedDateTime = currentDateTime.format(formatter)+"_";
		wait(driver,"1");
		String path= formattedDateTime+Vessel;

		String fileName = "C:\\Users\\RBT\\Desktop\\Solver_Minds\\uploads\\02Master_" + path + ".xlsx";

		String autoITExecutable = "C:\\Users\\RBT\\Desktop\\Solver_Minds\\driver\\MasterDownloadfile.exe " + fileName;

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
			click(driver, Home_Icon);
			waitForElement(driver, Exit_popup);
			waitForElement(driver, ExitBtn);
			click(driver, ExitBtn);

			waitForElement(driver, selectvessel);
			click(driver, selectvessel);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'"+Vessel+"')]")));
			
			driver.findElement(By.xpath("//*[contains(text(),'"+Vessel+"')]")).click();

		

		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@class='logo-icon']")));

		waitForElement(driver, Openplantop);
		// driver.findElement(By.xpath("//*[contains(text(),'Single Screen
		// Plan')]/preceding::input[@type='checkbox']")).click();

		waitForElement(driver, Openplantop);
		click(driver, Openplantop);

		waitForElement(driver, Globalplan);
		click(driver, Globalplan);

		waitForElement(driver, clickOk);
		click(driver, clickOk);

//		waitForElement(driver, VAPPlan_764);
//		doubleClick(driver, VAPPlan_764);

		WebElement Testfile = driver.findElement(By.xpath("//*[contains(text(),'" + TestPlanFile + "')]"));
		wait.until(ExpectedConditions.elementToBeClickable(Testfile));

		Actions action2 = new Actions(driver).doubleClick(Testfile);
		action2.build().perform();
		
		
		wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("//*[@class='ves_topbar_align']"),
				"VAPPlan-764 - VAPPlan-755 - Auto test 1"));

		String Planname2 = driver.findElement(By.xpath("//*[@class='ves_topbar_align']")).getText();

		if (Planname2.equals("VAPPlan-764 - VAPPlan-755 - Auto test 1")) {
			System.out.println("Selected VAPPlan-764 is opened");
		} else {
			System.out.println("Different plan is opned");
		}

		waitForElement(driver, selectWindow);
		click(driver, selectWindow);

		waitForElement(driver, internal);
		click(driver, internal);

		waitForElement(driver, Containerpool);
		click(driver, Containerpool);

		if (isDisplayed(driver, CNNB2)) {
			doubleClick(driver, CNNB2);
		} else {

			waitForElement(driver, CNNB);
			doubleClick(driver, CNNB);
		}

		waitForElement(driver, planPattern_);

		waitForElement(driver, mini);
		driver.findElement(By.xpath("//div[text()='Container Pool']//following::button[@aria-label='Minimize']"))
				.click();

		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='1']")));
		Actions action = new Actions(driver);

		wait(driver, "1");
		action.moveByOffset(-1150, 20).clickAndHold().moveByOffset(600, 50).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		// 1 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-600, -50).clickAndHold().moveByOffset(1000, 70).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 2 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-1000, -70).clickAndHold().moveByOffset(650, 70).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 1");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 3 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-650, -65).clickAndHold().moveByOffset(650, 60).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 2");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 4 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-600, -70).clickAndHold().moveByOffset(650, 70).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 3");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 5 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-600, -60).clickAndHold().moveByOffset(650, 70).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 4");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 6 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-650, -70).clickAndHold().moveByOffset(650, 70).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 5");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 7 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-700, -80).clickAndHold().moveByOffset(650, 80).pause(Duration.ofSeconds(2)).release()
				.build().perform();

		System.out.println("Element dragged 5");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 8 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-700, -85).clickAndHold().moveByOffset(700, 80).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 5");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 9 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-600, -70).clickAndHold().moveByOffset(750, 80).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 5");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 10 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-750, -85).clickAndHold().moveByOffset(650, 80).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 11");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 11 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-700, -80).clickAndHold().moveByOffset(650, 80).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 12");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 12 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-700, -80).clickAndHold().moveByOffset(650, 80).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 13");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 13 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-600, -80).clickAndHold().moveByOffset(650, 80).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 14");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 14 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-600, -80).clickAndHold().moveByOffset(650, 80).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 15");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 15 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-600, -80).clickAndHold().moveByOffset(650, 80).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 16");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 16 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-700, -70).clickAndHold().moveByOffset(650, 70).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 17");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 17 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-700, -80).clickAndHold().moveByOffset(650, 70).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 18");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 18 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-500, -70).clickAndHold().moveByOffset(700, 130).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 19");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 19 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-700, -80).clickAndHold().moveByOffset(650, 65).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 20");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 20 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-700, -80).clickAndHold().moveByOffset(650, 70).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 21");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 21 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-700, -80).clickAndHold().moveByOffset(650, 80).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 22");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 22 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-700, -90).clickAndHold().moveByOffset(650, 60).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 18");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 23 completed

		waitForElement(driver, PreviousBay);
		click(driver, PreviousBay);

		wait(driver, "1");
		action.moveByOffset(-500, -80).clickAndHold().moveByOffset(700, 120).pause(Duration.ofSeconds(1)).release()
				.build().perform();

		System.out.println("Element dragged 24");

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

		// 24 completed

		wait(driver, "1");
		waitForElement(driver, bayClose);
		click(driver, bayClose);

		waitForElement(driver, optimiser);
		click(driver, optimiser);

		waitForElement(driver, Plantemplate);
		click(driver, Plantemplate);

		waitForElement(driver, Lashing);
		click(driver, Lashing);

		waitForElement(driver, VWR);
		click(driver, VWR);

		waitForElement(driver, select5);
		click(driver, select5);

		sendKeys(driver, DSWGM, DSW_GM);

		waitForElement(driver, Run);
		click(driver, Run);

		WebDriverWait wait2 = new WebDriverWait(driver, 12000);

		wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@alt='Partial Success']")));
		WebElement Success = driver.findElement(By.xpath("//*[@alt='Partial Success']"));

		String message = Success.getText();

		System.out.println(message);

		waitForElement(driver, Ok_BTN);
		click(driver, Ok_BTN);

		waitForElement(driver, ABP);

		Escape(driver);

		waitForElement(driver, Saveplan);
		click(driver, Saveplan);

		waitForElement(driver, Globalsave);
		click(driver, Globalsave);

		waitForElement(driver, clickOk);
		click(driver, clickOk);

		waitForElement(driver, Saveinput);
		click(driver, Saveinput);

		sendKeys(driver, Saveinput, "TD Testplan Scenario2");

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
		waitForElement(driver, bayClose);
		click(driver, bayClose);

		waitForElement(driver, Search);
		click(driver, Search);

		waitForElement(driver, SearchInput);
		sendKeys(driver, SearchInput, "Cargo list");

		waitForElement(driver, Selectlist1);
		click(driver, Selectlist1);

		waitForElement(driver, ExportExcel);
		click(driver, ExportExcel);

		wait(driver, "1");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@alt='Export As Excel']")));
		driver.findElement(By.xpath("//*[@alt='Export As Excel']")).click();

		// Press Enter to confirm the file download dialog
		wait(driver, "10");

		LocalDateTime currentDateTime1 = LocalDateTime.now();
		String formattedDateTime1 = currentDateTime1.format(formatter)+"_";

		wait(driver,"1");
		String Testpath= formattedDateTime1+Vessel;

		String TestfileName = "C:\\Users\\RBT\\Desktop\\Solver_Minds\\uploads\\02Test_" + Testpath + ".xlsx";

		String autoITExecutableTest = "C:\\Users\\RBT\\Desktop\\Solver_Minds\\driver\\TestDownloadfile.exe " + TestfileName;

		try {
		    Runtime.getRuntime().exec(autoITExecutableTest);
		} catch (IOException e) {
		    e.printStackTrace();
		}

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Created successfully')]")));
		
	}

}
