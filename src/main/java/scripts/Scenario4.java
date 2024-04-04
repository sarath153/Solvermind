package scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

public class Scenario4 extends Keywords{

    
	//Outer
	ArrayList<Integer> verylightweightOuter = new ArrayList<>();
	ArrayList<Integer> lightweightOuter = new ArrayList<>();
	ArrayList<Integer> mediumweightOuter = new ArrayList<>();
	ArrayList<Integer> heavyweightOuter = new ArrayList<>();
	ArrayList<Integer> veryheavyweightOuter = new ArrayList<>();

	//Inner
	ArrayList<Integer> verylightweightInner = new ArrayList<>();
	ArrayList<Integer> lightweightInner = new ArrayList<>();
	ArrayList<Integer> mediumweightInner = new ArrayList<>();
	ArrayList<Integer> heavyweightInner = new ArrayList<>();
	ArrayList<Integer> veryheavyweightInner = new ArrayList<>();

	
    String filePaath1;
    String filePaath;
    String TestfileName1;
    String TestfileName;
    
	public void scenario4test(WebDriver driver, String vessel2,ExtentTest test ,ExtentTest testDetail,String detailreportPath) throws AWTException {
		
		
		String URL = TestNgXml.getdatafromExecution().get("Scenario4");
		String Username = Utils.getDataFromTestData("Solverminds", "Username");
		String Password = Utils.getDataFromTestData("Solverminds", "Password");
		String vessel=vessel2;
		String DSW_GM = Utils.getDataFromTestData(vessel, "DSW_GM");
		String MasterPlanFile = Utils.getDataFromTestData(vessel, "MasterPlanFile");
		String TestPlanFile = Utils.getDataFromTestData(vessel, "TestPlanFile");
		String voyage_from = Utils.getDataFromTestData(vessel, "Voyage_from_4");
		String voyage_to = Utils.getDataFromTestData(vessel, "Voyage_to_4");
	    String service_Code=Utils.getDataFromTestData(vessel, "Service_Code");
	    String BoundFROM=Utils.getDataFromTestData(vessel, "BoundFrom");
	    String BoundTO=Utils.getDataFromTestData(vessel, "BoundTo");
	    String PortCode_1=Utils.getDataFromTestData(vessel, "PortCode_1");
	    String PortCode_2=Utils.getDataFromTestData(vessel, "PortCode_2");
	    String ISOCode=Utils.getDataFromTestData(vessel, "ISO_code");
	    String PlanType = Utils.getDataFromTestData(vessel, "PlanTemplate");

		
		
		String[] weightType= {"VLW","LW","MW","HW","VHW"};
		int[] weights= {6,13,20,24,32};
		String [] DSW= {"1","3","5","7","9","11"};
		
		int[] JYH_outer_X= {985,1025,1015,1015,1015,1000,1000,1000,985,980,980,980,980,980,980,980,980,1045,1025,1025,1025,815};
		int[] JYH_outer_Y= {101,113,103,103,103,105,105,105,110,110,110,110,110,110,110,110,110,125,115,115,130,140};
		
		int[] JYH_Inner_X= {974,1015,996,996,996,988,988,988,965,965,965,965,965,965,965,965,965,1030,1007,1007,1020,800};
		int[] JYH_Inner_Y= {101,113,103,103,103,105,105,105,110,100,100,100,100,100,100,100,100,115,113,112,120,130};
		
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
		
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'"+vessel+"')]")));
		driver.findElement(By.xpath("//*[contains(text(),'"+vessel+"')]")).click();
		
		waitForElement(driver,home_Page);
		
		//driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();
		
		//**********************************************************//////
		
		waitForElement(driver,schedule);
		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']")).click();
		waitForElement(driver,Bay1);
//		click(driver,schedule);
//		
//		waitForElement(driver,newschedule);
//		driver.findElement(By.xpath("//img[@src='assets/images/Mastertoolbar-icons/new.svg']")).click();
//		wait(driver,"1");
//		
//		WebDriverWait wait1 = new WebDriverWait(driver, 5);	
//			try {
//				wait1.until(ExpectedConditions.alertIsPresent());
//				Alert1(driver);
//			}catch(Exception e) {
//				System.out.println("Alert not present");
//			}
//		
//		waitForElement(driver, searchservice);
//		click(driver,searchservice);
//			
//		waitForElement(driver,service_Input);
//		click(driver,service_Input);
//		
//		sendKeys(driver,service_Input,service_Code);
//		
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='"+service_Code+"']")));
//		driver.findElement(By.xpath("//*[text()='"+service_Code+"']")).click();
//		
//		waitForElement(driver,OK_);
//		click(driver,OK_);
//		
//		waitForElement(driver, voyagefrom);
//	    sendKeys(driver,voyagefrom,voyage_from);
//		
//		waitForElement(driver, voyageto);
//	    sendKeys(driver,voyageto,voyage_to);
//	  
//	    waitForElement(driver, boundfrom1);
//		click(driver,boundfrom1);
//		
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-value='"+BoundFROM+"']")));
//		driver.findElement(By.xpath("//*[@data-value='"+BoundFROM+"']")).click();
//		
//	    waitForElement(driver, boundTo1);
//		click(driver,boundTo1);
//		
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-value='"+BoundTO+"']")));
//		driver.findElement(By.xpath("//*[@data-value='"+BoundTO+"']")).click();
//		
//		waitForElement(driver,Show);
//		click(driver,Show);
//		
//			try {
//				wait1.until(ExpectedConditions.alertIsPresent());
//				Alert1(driver);
//				
//			}catch(Exception e) {
//				System.out.println("Alert not present");
//			}
//		
//			wait(driver,"2");
//		
//				if(!isDisplayed(driver,servicetable)) {
//				
//					waitForElement(driver,portcodeSearch);
//					click(driver,portcodeSearch);
//					
//					waitForElement(driver,portcode_Input);
//					click(driver,portcode_Input);
//					
//					sendKeys(driver,portcode_Input,PortCode_1);
//					
//					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+PortCode_1+"']")));
//					driver.findElement(By.xpath("//div[text()='"+PortCode_1+"']")).click();
//					
//					waitForElement(driver,OK_);
//					click(driver,OK_);
//					
//					waitForElement(driver,add);
//					click(driver,add);
//					
//					wait(driver,"1");
//		
//					waitForElement(driver,portcodeSearch);
//					click(driver,portcodeSearch);
//					
//					waitForElement(driver,portcode_Input);
//					click(driver,portcode_Input);
//					
//					sendKeys(driver,portcode_Input,PortCode_2);
//					
//					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+PortCode_2+"']")));
//					driver.findElement(By.xpath("//div[text()='"+PortCode_2+"']")).click();
//					
//					waitForElement(driver,OK_);
//					click(driver,OK_);
//					
//					waitForElement(driver,add);
//					click(driver,add);
//					
//					wait(driver,"1");
//					
//					waitForElement(driver,save);
//					click(driver,save);
//				
//				}
//		
//			wait(driver,"2");
//			
//			waitForElement(driver,ScheduleTabClose);
//			click(driver,ScheduleTabClose);
//			
//			waitForElement(driver,dropdown);
//			click(driver,dropdown);
//			
//			waitForElement(driver,add);
//			click(driver,add);
//		
//			List<WebElement> tot=driver.findElements(By.xpath("//div[text()='All Bay Plan']//following::canvas"));
//			int totalSize=tot.size();
//			
//	
//		    wait(driver,"1");
//		    WebElement Abp=driver.findElement(By.xpath("//div[text()='All Bay Plan']"));
//			Actions action2 = new Actions(driver);
//		    wait(driver,"1");
//		    action2.moveToElement(Abp);
//		    
//		    action2.moveByOffset(-48, 66).clickAndHold().moveByOffset(1248, 330).pause(Duration.ofSeconds(3)).release().build().perform();
//		    
//		    addCargo(driver,ISOCode);
//		    
//		    wait(driver,"1");
//		    
//		    // DSW GM Loop start.....................
//		    
//		    for(int d=0;d<DSW.length;d++) {
//		    	
//					waitForElement(driver,dropdown);
//					click(driver,dropdown);
//					
//					waitForElement(driver,plan);
//					click(driver,plan);
//					
//					wait(driver,"2");
//					
//						
//					  Robot robot = new Robot();
//						try {
//							
//							robot.delay(500);
//							robot.mouseMove(88,270);
//							robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
//							robot.delay(500);
//							robot.mouseMove(1330, 600);
//							robot.delay(500);
//							robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
//							robot.delay(2000);
//		
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					
//						
//					if(d==0) {
//						
//					          waitForElement(driver, selectWindow);
//					          click(driver, selectWindow);
//		
//					          waitForElement(driver, internal);
//					          click(driver, internal);
//		
//					          waitForElement(driver, Containerpool);
//					          //click(driver, Containerpool);
//					
//					          driver.findElement(By.xpath("//*[@alt='27-ContainerPool']")).click();
//					
//				        	    try {
//				                		//WebDriverWait wait1 = new WebDriverWait(driver, 5);
//				        	    		wait1.until(ExpectedConditions.alertIsPresent());
//				        	    		Alert1(driver);
//				        	    	}catch(Exception e) {
//				        	    		System.out.println("Alert not present");
//				        	    	}
//		
//					
//				        	    waitForElement(driver,groupValue);
//				        	    doubleClick(driver,groupValue);
//					
//				        	    waitForElement(driver, planPattern_);
//					
//				        	    waitForElement(driver, mini);
//				        	    driver.findElement(By.xpath("//div[text()='Container Pool']//following::button[@aria-label='Minimize']")).click();
//			
//					
//				        	    action2.moveToElement(Abp);
//				        	    action2.moveByOffset(-48, 60).clickAndHold().moveByOffset(1248, 45).pause(Duration.ofSeconds(1)).release().build().perform();
//				        	    wait(driver,"1");
//		
//				        	    action2.moveToElement(Abp);			
//				        	    action2.moveByOffset(-45, 265).clickAndHold().moveByOffset(1235, 50).pause(Duration.ofSeconds(2)).release().build().perform();
//				        	    wait(driver,"2");
//					
//						} //d==0 end
//						
//					driver.findElement(By.xpath("//*[@alt='27-ContainerPool']")).click();
//					
//					waitForElement(driver,countValue);
//					doubleClick(driver,countValue);
//					
//					waitForElement(driver,cargoListTab);
//					waitForElement(driver,weightInput);
//					//click(driver,weightInput);
//					doubleClick(driver,weightInput);
//					
//					robot.delay(1000);
//					robot.keyPress(KeyEvent.VK_6);
//					robot.delay(100);
//					robot.keyRelease(KeyEvent.VK_6);
//					robot.delay(1000);
////					sendKeys(driver,weightInput,6+"");
//					
//					waitForElement(driver,polInput);
//					click(driver,polInput);
//					
//					driver.findElement(By.xpath("//div[text()='CARGOLIST']//following::button[@aria-label='Close']")).click();
//					
//					driver.findElement(By.xpath("//div[text()='Container Pool']//following::button[@aria-label='Minimize']")).click();
//		
//					
//					/// **************************************************************** ?////////
//					
//		               waitForElement(driver, Search); click(driver, Search);
//					 
//					   waitForElement(driver, SearchInput); sendKeys(driver, SearchInput, "Vessel Weight Range");
//					 
//					   Robot key=new Robot();
//					    key.delay(300); 
//					    key.keyPress(KeyEvent.VK_ENTER);
//						key.delay(300);
//						key.keyRelease(KeyEvent.VK_ENTER);
//					    key.delay(300); 
//		
//						waitForElement(driver,export);
//						driver.findElement(By.xpath("//button[@name='Export']")).click();
//						
//						wait(driver,"7");// don't remove this wait 
//						
//					/// Export excel ------------------------------------------------****
//						
//				
//						//new *************
//						
//						//LocalDateTime currentDateTime = LocalDateTime.now();
//						
//						LocalDateTime currentDateTime1 = LocalDateTime.now();
//						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");
//						String formattedDateTime = currentDateTime1.format(formatter)+"_";
//		                String m="_mm";
//						wait(driver,"1");
//						String Testpath= DSW[d]+m+formattedDateTime+vessel;
//		
//						TestfileName = System.getProperty("user.dir")+"\\uploads\\Scenario4\\Inner\\Inner_" + Testpath + ".xlsx";
//						wait(driver,"1");
//
//						String autoITExecutableTest = System.getProperty("user.dir")+"\\driver\\TestDownloadfile.exe " + TestfileName;
//						wait(driver,"1");
//
//						try {
//						    Runtime.getRuntime().exec(autoITExecutableTest);
//						} catch (IOException e) {
//						    e.printStackTrace();
//						}
//						
//		//				wait.until(ExpectedConditions
//		//						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Created successfully')]")));
//		//				
//						
//						/// Export excel ------------------------------------------------****
//						
//						wait(driver,"10");// don't remove this wait
//						waitForElement(driver,Rowtype);
//						click(driver,Rowtype);
//						
//						waitForElement(driver,outerRow);
//						click(driver,outerRow);
//						
//						waitForElement(driver,export);
//						driver.findElement(By.xpath("//button[@name='Export']")).click();
//						
//						wait(driver,"10"); // don't remove this wait
//		
//						robot.delay(3000);// Adjust as needed
//		
//						
//		                 //new *************
//						
//						//LocalDateTime currentDateTime = LocalDateTime.now();
//						
//						LocalDateTime currentDateTime = LocalDateTime.now();
//						//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");
//						String formattedDateTime1 = currentDateTime.format(formatter)+"_";
//						wait(driver,"1");
//						String Testpath1= DSW[d]+m+formattedDateTime1+vessel;
//		
//					    TestfileName1 = System.getProperty("user.dir")+"\\uploads\\Scenario4\\Outer\\Outer_" + Testpath1 + ".xlsx";
//						wait(driver,"1");
//
//						String autoITExecutableTest1 = System.getProperty("user.dir")+"\\driver\\MasterDownloadfile.exe " + TestfileName1;
//						wait(driver,"1");
//
//						try {
//						    Runtime.getRuntime().exec(autoITExecutableTest1);
//						} catch (IOException e) {
//						    e.printStackTrace();
//						}
//						
//		//				/// Export excel ------------------------------------------------****
//						
//						
//						
//						wait(driver,"10");
//		
//						waitForElement(driver,closetab3);
//						click(driver,closetab3);
//						
//						 waitForElement(driver, optimiser);
//					     click(driver, optimiser);
//					     
//					     waitForElement(driver, Plantemplate);
//					     click(driver, Plantemplate);
//					     
//					     //need to modify
////					     waitForElement(driver, Lashing);
////					     click(driver, Lashing);
//					     
//					     clickVWR(driver,PlanType);
//					     
//					     //need to modify
//					     
//					     click(driver,DSWGM);
//					     wait(driver,"1");
//					     doubleClick(driver,DSWGM);
//					     wait(driver,"1");
//					     sendKeys(driver, DSWGM, DSW[d]);// DM
//						 
//					     waitForElement(driver,meanDraft);
//					     click(driver,meanDraft);
//					     wait(driver,"1");
//					     doubleClick(driver,meanDraft);
//					     wait(driver,"1");
//
//					   //  need to modify
//					     sendKeys(driver,meanDraft,"14.5");
//					    
//						 waitForElement(driver, Run); 
//						 click(driver, Run);
//						 
//						 WebDriverWait wait2 = new WebDriverWait (driver, 12000);
//						 
//						 wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@alt='Partial Success']")));
//						 WebElement Success=driver.findElement(By.xpath("//*[@alt='Partial Success']"));
//					     
//						 String message=Success.getText();
//						 System.out.println(message);
//					
//						  driver.findElement(By.xpath("(//*[@alt='Partial Success']//following::button)[1]")).click();
//						  
//						  while(isDisplayed(driver,Run)) {
//							    robot.delay(100);
//								robot.keyPress(KeyEvent.VK_ESCAPE);
//								robot.delay(100);
//								robot.keyRelease(KeyEvent.VK_ESCAPE);
//								robot.delay(100);
//		
//						  }
//						   
		wait(driver,"5"); int totalSize=22;
//					     doubleClick(driver, Canvas2);
//						   ** 1st lashing completed 1.0mm with 6 (verylightweight)
						  
//						  System.out.println("After "+DSW[d]+"_mm Very Light weight lashing Checking the Outer and inner values start");
//						  add(driver,"After "+DSW[d]+"_mm Very Light weight lashing Checking the Outer and inner values start", LogAs.PASSED, true, "");
						
						  
						  ArrayList<Integer>[] VLW=readHighestValue1(driver,totalSize,JYH_outer_X,JYH_outer_Y,JYH_Inner_X,JYH_Inner_Y);
						  
					      verylightweightOuter=VLW[0];
						  verylightweightInner=VLW[1];
						  
						  
						  System.out.println("**********Reeading Outer and inner values End ****************");
						  add(driver,"**********Reeading Outer and inner values End ****************", LogAs.PASSED, true, "");
						
						  
//						  waitForElement(driver,savePlan);
//						  click(driver,savePlan);
//						  
//						  waitForElement(driver,globalSave);
//						  click(driver,globalSave);
//						  
//						  waitForElement(driver,OK_);
//						  click(driver,OK_);
//						  
//						  waitForElement(driver,planNameInput);
//						  click(driver,planNameInput);
//						  sendKeys(driver,planNameInput,vessel+" "+DSW[d]+"mm VLW");
//						  
//						  waitForElement(driver,Ok_2);
//						  click(driver,Ok_2);
//						  
//						  for(int i=1;i<weightType.length;i++){
//							  
//							  waitForElement(driver,dropdown);
//							  click(driver,dropdown);
//							  
//							  waitForElement(driver,plan);
//							  click(driver,plan);
//							  
//							  
//								try {
//									
//									robot.delay(500);
//									robot.mouseMove(88,270);
//									robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
//									robot.delay(1000);
//									robot.mouseMove(1330, 600);
//									robot.delay(500);
//									robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
//									robot.delay(100);
//		
//								} catch (Exception e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//								
//								
//								waitForElement(driver,cargoDetails);
//								click(driver,cargoDetails);
//								
//								waitForElement(driver,CDS);
//								waitForElement(driver,allRadioBtn);
//								click(driver,allRadioBtn);
//								
//								waitForElement(driver,weightInput);
//								doubleClick(driver,weightInput);
//								
//								if(i==1) {
//									robot.delay(500);
//									robot.keyPress(KeyEvent.VK_1);
//									robot.delay(100);
//									robot.keyRelease(KeyEvent.VK_1);
//									robot.delay(500);
//									robot.keyPress(KeyEvent.VK_3);
//									robot.delay(100);
//									robot.keyRelease(KeyEvent.VK_3);
//									robot.delay(500);	
//									
//								}else if(i==2) {
//									robot.delay(500);
//									robot.keyPress(KeyEvent.VK_2);
//									robot.delay(100);
//									robot.keyRelease(KeyEvent.VK_2);
//									robot.delay(500);
//									robot.keyPress(KeyEvent.VK_0);
//									robot.delay(100);
//									robot.keyRelease(KeyEvent.VK_0);
//									robot.delay(500);	
//									
//								}else if(i==3) {
//									robot.delay(500);
//									robot.keyPress(KeyEvent.VK_2);
//									robot.delay(100);
//									robot.keyRelease(KeyEvent.VK_2);
//									robot.delay(500);
//									robot.keyPress(KeyEvent.VK_4);
//									robot.delay(100);
//									robot.keyRelease(KeyEvent.VK_4);
//									robot.delay(500);	
//									
//								}else if(i==4) {
//									robot.delay(500);
//									robot.keyPress(KeyEvent.VK_3);
//									robot.delay(100);
//									robot.keyRelease(KeyEvent.VK_3);
//									robot.delay(500);
//									robot.keyPress(KeyEvent.VK_2);
//									robot.delay(100);
//									robot.keyRelease(KeyEvent.VK_2);
//									robot.delay(500);	
//									
//								}
//								
//								
//								waitForElement(driver,PolInput);
//								click(driver,PolInput);
//														
//								if(isDisplayed(driver,portClose)) {
//									click(driver,portClose);
//								}
//														
//								waitForElement(driver,CDS_close);
//								click(driver,CDS_close);
//								
//								//6,13,20,24,32
//								
//								 waitForElement(driver, optimiser);
//							     click(driver, optimiser);
//							     
//							     waitForElement(driver, Plantemplate);
//							     click(driver, Plantemplate);
//							     
//							     //need to modify
////							     waitForElement(driver, Lashing);
////							     click(driver, Lashing);
//							     
//							     clickVWR(driver,PlanType);
//							     
//							     //need to modify
//							     click(driver,DSWGM);
//							     wait(driver,"1");
//							     doubleClick(driver,DSWGM);
//							     
//							     sendKeys(driver, DSWGM, DSW[d]); // DM
//								 
//							     waitForElement(driver,meanDraft);
//							     click(driver,meanDraft);
//							     doubleClick(driver,meanDraft);
//							     
//							   //  need to modify
//							     sendKeys(driver,meanDraft,"14.5");
//							     
//								 waitForElement(driver, Run); 
//								 click(driver, Run);
//								 
//								 //WebDriverWait wait2 = new WebDriverWait (driver, 12000);
//								 
//								 wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@alt='Partial Success']")));
//								 WebElement Success1=driver.findElement(By.xpath("(//*[@alt='Partial Success']//following::span)[1]"));
//							     
//								 String message1=Success1.getText();
//								 System.out.println(message1);
//							
//								  driver.findElement(By.xpath("(//*[@alt='Partial Success']//following::button)[1]")).click();
//								  
//								  while(isDisplayed(driver,Run)) {
//									    robot.delay(100);
//										robot.keyPress(KeyEvent.VK_ESCAPE);
//										robot.delay(100);
//										robot.keyRelease(KeyEvent.VK_ESCAPE);
//										robot.delay(100);
//		
//								  }
//								  
//							     doubleClick(driver, Canvas2);
//								  //lasing finished
//								  
//									  if(i==1) {
//										  System.out.println("After "+DSW[d]+"_mm Light weight lashing Checking the Outer and inner values start");
//										  add(driver,"After "+DSW[d]+"_mm Light weight lashing Checking the Outer and inner values start", LogAs.PASSED, true, "");
//										
//										  ArrayList<Integer>[] LW=readHighestValue1(driver,totalSize,JYH_outer_X,JYH_outer_Y,JYH_Inner_X,JYH_Inner_Y);
//										  lightweightOuter=LW[0];
//										  lightweightInner=LW[1];
//										 
//										  System.out.println("**********Reeading Outer and inner values End ****************");
//										  add(driver,"**********Reeading Outer and inner values End ****************", LogAs.PASSED, true, "");
//										 
//									  }else if(i==2) {
//										  System.out.println("After "+DSW[d]+"_mm Medium weight lashing Checking the Outer and inner values start");
//										  add(driver,"After "+DSW[d]+"_mm Medium weight lashing Checking the Outer and inner values start", LogAs.PASSED, true, "");
//										 
//										  ArrayList<Integer>[] MW=readHighestValue1(driver,totalSize,JYH_outer_X,JYH_outer_Y,JYH_Inner_X,JYH_Inner_Y);
//										  mediumweightOuter=MW[0];
//										  mediumweightInner=MW[1];
//										  
//										  System.out.println("**********Reeading Outer and inner values End ****************");
//										  add(driver,"**********Reeading Outer and inner values End ****************", LogAs.PASSED, true, "");
//										 
//										  
//									  }else if(i==3) {
//										  
//										  System.out.println("After "+DSW[d]+"_mm Heavy weight lashing Checking the Outer and inner values start");
//										  add(driver,"After "+DSW[d]+"_mm Heavy weight lashing Checking the Outer and inner values start", LogAs.PASSED, true, "");
//										 
//										  ArrayList<Integer>[] HW=readHighestValue1(driver,totalSize,JYH_outer_X,JYH_outer_Y,JYH_Inner_X,JYH_Inner_Y);
//										  heavyweightOuter=HW[0];
//										  heavyweightInner=HW[1];
//										  
//										  System.out.println("**********Reeading Outer and inner values End ****************");
//										  add(driver,"**********Reeading Outer and inner values End ****************", LogAs.PASSED, true, "");
//										 
//										  
//									  }else if(i==4) {
//										  
//										  System.out.println("After "+DSW[d]+"_mm Very Heavy weight lashing Checking the Outer and inner values start");
//										  add(driver,"After "+DSW[d]+"_mm Very Heavy weight lashing Checking the Outer and inner values start", LogAs.PASSED, true, "");
//										 
//										  ArrayList<Integer>[] VHW=readHighestValue1(driver,totalSize,JYH_outer_X,JYH_outer_Y,JYH_Inner_X,JYH_Inner_Y);
//										  veryheavyweightOuter=VHW[0];
//										  veryheavyweightInner=VHW[1];
//										  
//										  System.out.println("**********Reeading Outer and inner values End ****************");
//										  add(driver,"**********Reeading Outer and inner values End ****************", LogAs.PASSED, true, "");
//										 
//										  
//									  }
//								  
//								  
//								  waitForElement(driver,savePlan);
//								  click(driver,savePlan);
//								  
//								  waitForElement(driver,globalSave);
//								  click(driver,globalSave);
//								  
//								  waitForElement(driver,OK_);
//								  click(driver,OK_);
//								  
//								  waitForElement(driver,planNameInput);
//								  click(driver,planNameInput);
//								  sendKeys(driver,planNameInput,vessel+" "+DSW[d]+"mm "+weightType[i]);
//								  
//								  waitForElement(driver,Ok_2);
//								  click(driver,Ok_2);	  
//							  
//						  }
//								  
//				
//						  // Excel writing 
//						  
//						 filePaath=TestfileName;
//						 filePaath1=TestfileName1;
//		
//						try {
//							excelWriteGM(filePaath,DSW[d]+".0mm");  // need to modify
//						}catch(Exception e) {
//						}
//							
//						try {
//							excelWriteGM(filePaath1,DSW[d]+".0mm"); // need to modify
//						}catch(Exception e) {
//						}
//						
//						try {
//							typeWeight(filePaath1,"verylightweighttier",verylightweightOuter);
//						}catch(Exception e) {	
//						}
//						
//						try {
//							typeWeight(filePaath1,"lightweighttier",lightweightOuter);
//						}catch(Exception e) {	
//						}
//						
//						try {
//							typeWeight(filePaath1,"mediumweighttier",mediumweightOuter);
//						}catch(Exception e) {	
//						}
//						
//						try {
//							typeWeight(filePaath1,"heavyweighttier",heavyweightOuter);
//						}catch(Exception e) {	
//						}
//						
//						try {
//							typeWeight(filePaath1,"veryheavyweighttier",veryheavyweightOuter);
//						}catch(Exception e) {	
//						}
//						
//						
//						//inner file *****************
//						try {
//							typeWeight(filePaath,"verylightweighttier",verylightweightInner);
//						}catch(Exception e) {	
//						}
//						
//						try {
//							typeWeight(filePaath,"lightweighttier",lightweightInner);
//						}catch(Exception e) {	
//						}
//						
//						try {
//							typeWeight(filePaath,"mediumweighttier",mediumweightInner);
//						}catch(Exception e) {	
//						}
//						
//						try {
//							typeWeight(filePaath,"heavyweighttier",heavyweightInner);
//						}catch(Exception e) {	
//						}
//						
//						try {
//							typeWeight(filePaath,"veryheavyweighttier",veryheavyweightInner);
//						}catch(Exception e) {	
//						}
//						
//						verylightweightOuter.clear();
//						lightweightOuter.clear();
//						mediumweightOuter.clear();
//						heavyweightOuter.clear();
//						veryheavyweightOuter.clear();
//						
//						verylightweightInner.clear();
//						lightweightInner.clear();
//						mediumweightInner.clear();
//						heavyweightInner.clear();
//						veryheavyweightInner.clear();
//				
//						 System.out.println("Excel writing completed for  "+DSW[d]+"_mm");
//						 add(driver,"Excel writing completed for  "+DSW[d]+"_mm", LogAs.PASSED, true, "");
//						 
//				
//	   }	// DSW GM Loop end		
	 	
	}
	
}
