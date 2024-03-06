package scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonMethods.Keywords;
import commonMethods.TestNgXml;
import commonMethods.Utils;

public class Scenario4 extends Keywords{

	String URL = TestNgXml.getdatafromExecution().get("Scenario1");
	String Username = Utils.getDataFromTestData("Solverminds", "Username");
	String Password = Utils.getDataFromTestData("Solverminds", "Password");
    boolean flag=true;
    String voyage_from = Utils.getDataFromTestData("Solverminds", "Voyage_from_4");
	String voyage_to = Utils.getDataFromTestData("Solverminds", "Voyage_to_4");
	
	@SuppressWarnings("deprecation")
	public void Semiautorun4(WebDriver driver) throws AWTException {
	
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		navigateUrl(driver, URL);
		//Actions action = new Actions(driver);
		waitForElement(driver, username);
		sendKeys(driver, username, Username);

		waitForElement(driver, password);
		sendKeys(driver, password, Password);

		waitForElement(driver, login);
		click(driver, login);

		waitForElement(driver, selectvessel);
		click(driver, selectvessel);
		
		waitForElement(driver,click_JYH);
		click(driver,click_JYH);
		
		waitForElement(driver,home_Page);
		
		//driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();
		
		waitForElement(driver,schedule);
		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();
		click(driver,schedule);
		
		waitForElement(driver,newschedule);
//		click(driver,newschedule);
		//driver.findElement(By.xpath("//img[@src='assets/images/Mastertoolbar-icons/new.svg']")).click();)
		driver.findElement(By.xpath("//img[@src='assets/images/Mastertoolbar-icons/new.svg']")).click();
		wait(driver,"3");
		//
		WebDriverWait wait1 = new WebDriverWait(driver, 5);
//	    if(wait1.until(ExpectedConditions.alertIsPresent())==null) {
//	    	System.out.println("Alert not present");
//	    }else {
//	    	wait.until(ExpectedConditions.alertIsPresent());
//			Alert1(driver);
//	    }
		
		try {
			wait1.until(ExpectedConditions.alertIsPresent());
			Alert1(driver);
		}catch(Exception e) {
			System.out.println("Alert not present");
		}
		
		waitForElement(driver, searchservice);
		click(driver,searchservice);
			
		waitForElement(driver,service_Input);
		click(driver,service_Input);
		
		sendKeys(driver,service_Input,"test");
		
		waitForElement(driver, TEST_service);
		   	    
		doubleClick(driver,TEST_service);
		
		waitForElement(driver, voyagefrom);
	    sendKeys(driver,voyagefrom,voyage_from);
		
		waitForElement(driver, voyageto);
	    sendKeys(driver,voyageto,voyage_to);
	  
	    waitForElement(driver, boundfrom1);
		click(driver,boundfrom1);
		
	    waitForElement(driver, selectboundW);
		click(driver,selectboundW);
		
	    waitForElement(driver, boundTo1);
		click(driver,boundTo1);
		
	    waitForElement(driver, selectboundE);
		click(driver,selectboundE);
		
		waitForElement(driver,Show);
		click(driver,Show);
		
		try {
			wait1.until(ExpectedConditions.alertIsPresent());
			Alert1(driver);
			
		}catch(Exception e) {
			System.out.println("Alert not present");
		}
		
		wait(driver,"2");
		
		if(!isDisplayed(driver,servicetable)) {
			waitForElement(driver,portcodeSearch);
			click(driver,portcodeSearch);
			
			waitForElement(driver,portcode_Input);
			click(driver,portcode_Input);
			sendKeys(driver,portcode_Input,"INMAA");
			
			waitForElement(driver,INMAA_);
			doubleClick(driver,INMAA_);
			
			waitForElement(driver,add);
			click(driver,add);
			
			wait(driver,"2");

			waitForElement(driver,portcodeSearch);
			click(driver,portcodeSearch);
			
			waitForElement(driver,portcode_Input);
			click(driver,portcode_Input);
			sendKeys(driver,portcode_Input,"INNSA");
			
			waitForElement(driver,INNA_);
			doubleClick(driver,INNA_);
			
			waitForElement(driver,add);
			click(driver,add);
			
			wait(driver,"2");
			
			waitForElement(driver,save);
			click(driver,save);
			
		}
		
		waitForElement(driver,ScheduleTabClose);
		click(driver,ScheduleTabClose);
		
		waitForElement(driver,dropdown);
		click(driver,dropdown);
		
		waitForElement(driver,add);
		click(driver,add);
		
		
		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);

		wait(driver, "2");
		
		Actions action=new Actions(driver);
		wait(driver, "1");
		
		action.moveByOffset(-1150, -10).clickAndHold().moveByOffset(1200, 125).pause(Duration.ofSeconds(1)).release().build().perform();
		
	
		wait(driver, "1");
		
		    addCargo(driver);
			//************1 completed ******************
		    
//	     if(isDisplayed(driver, clickyes)) {
//				click(driver,clickyes);
//			}else {
//				System.out.println("Element dragged");
//			}
	     
			waitForElement(driver, PreviousBay);
			click(driver, PreviousBay);
			
			
			wait(driver, "1");
			action.moveByOffset(-600, -20).clickAndHold().moveByOffset(1200, 200).pause(Duration.ofSeconds(1)).release().build().perform();
			
			
			addCargo(driver);
			
			//************ 2 completed ******************
			waitForElement(driver, PreviousBay);
			click(driver, PreviousBay);
			
			
			wait(driver, "1");
			action.moveByOffset(-600, -30).clickAndHold().moveByOffset(1000, 230).pause(Duration.ofSeconds(1)).release().build().perform();
			
			
			addCargo(driver);
			
			//************ 3 completed ******************
			waitForElement(driver, PreviousBay);
			click(driver, PreviousBay);
			
			wait(driver, "1");
			action.moveByOffset(-600, -20).clickAndHold().moveByOffset(1200, 220).pause(Duration.ofSeconds(1)).release().build().perform();
			
			addCargo(driver);
			
			//************ 4 completed ******************
			waitForElement(driver, PreviousBay);
			click(driver, PreviousBay);
			
			wait(driver, "1");
			action.moveByOffset(-600, -20).clickAndHold().moveByOffset(1200, 210).pause(Duration.ofSeconds(1)).release().build().perform();
			
			addCargo(driver);
			
			//************ 5 completed ******************
			waitForElement(driver, PreviousBay);
			click(driver, PreviousBay);
			
			wait(driver, "1");
			action.moveByOffset(-600, -30).clickAndHold().moveByOffset(1200, 220).pause(Duration.ofSeconds(1)).release().build().perform();
			
			addCargo(driver);
			
			for(int i=0;i<15;i++) {
				
				waitForElement(driver, PreviousBay);
				click(driver, PreviousBay);
				
				wait(driver, "1");
				action.moveByOffset(-600, -30).clickAndHold().moveByOffset(1200, 220).pause(Duration.ofSeconds(1)).release().build().perform();
				
				addCargo(driver);
				
			}
			
			
			waitForElement(driver, PreviousBay);
			click(driver, PreviousBay);
			
			wait(driver, "1");
			action.moveByOffset(-600, -30).clickAndHold().moveByOffset(1000, 210).pause(Duration.ofSeconds(1)).release().build().perform();
			
			addCargo(driver);
			
			
			waitForElement(driver,bayClose);
			click(driver,bayClose);
			
		//*********************
		
			waitForElement(driver,dropdown);
			click(driver,dropdown);
			
			waitForElement(driver,plan);
			click(driver,plan);
			
			
			waitForElement(driver, Bay1);
			doubleClick(driver, Bay1);
			
     		wait(driver,"3");
		
            Robot robot=new Robot();
			robot.delay(1000);
			robot.mouseMove(280,180);
			robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
			robot.mouseMove(600, 180);
			robot.delay(500);
			robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
//			
//			for(int i=0;i<21;i++) {
//				
//				waitForElement(driver, PreviousBay);
//				click(driver, PreviousBay);
//				robot.delay(1000);
//				robot.mouseMove(-600,-30);
//				robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
//				robot.mouseMove(1200, 220);
//				robot.delay(500);
//				robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
//				
//				
//			}
//			
//			waitForElement(driver,bayClose);
//			click(driver,bayClose);
//			
//			waitForElement(driver, selectWindow);
//			click(driver, selectWindow);
//
//			waitForElement(driver, internal);
//			click(driver, internal);
//
//			waitForElement(driver, Containerpool);
//			//click(driver, Containerpool);
//			
//			driver.findElement(By.xpath("//*[@alt='27-ContainerPool']")).click();
//			
//			try {
//				//WebDriverWait wait1 = new WebDriverWait(driver, 5);
//				wait1.until(ExpectedConditions.alertIsPresent());
//				Alert1(driver);
//			}catch(Exception e) {
//				System.out.println("Alert not present");
//			}
//
//				waitForElement(driver, INNSA);
//				doubleClick(driver, INNSA);
//				
//			waitForElement(driver, planPattern_);
//			
//			waitForElement(driver, mini);
//			driver.findElement(By.xpath("//div[text()='Container Pool']//following::button[@aria-label='Minimize']")).click();
//					
//			waitForElement(driver, Bay1);
//			doubleClick(driver, Bay1);
//
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='1']")));
//			
//			Actions action2 = new Actions(driver);
//			
//			wait(driver, "1");
//			action2.moveByOffset(-1150, 20).clickAndHold().moveByOffset(600, 50).pause(Duration.ofSeconds(1)).release().build().perform();
//			
			
	}
}
