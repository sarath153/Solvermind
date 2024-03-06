package commonMethods;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;


import scripts.Scenario1;
import scripts.Scenario2;
import scripts.Scenario3;
import scripts.Scenario4;
import scripts.Scenario5;
import scripts.Scenario6;
import scripts.Scenario8;
//import scripts.Filo;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })

public class Testcases extends Config {
	{
		System.setProperty("atu.reporter.config", System.getProperty("user.dir") + "/atu.properties");
	}
	public String appURL;
	public String appURL1;
	public String appURL2;
	public String prurl;
	public String mailinatorurl;
	public String proxy;
	public String usernameValue;
	public String passwordValue;
	public String project_Name;
	public String version_Name;
	public String environment;
	public String browser;
	public WebDriver driver;
	public String search;

	public ChromeOptions options = null;
	String Userflowurl = null;
	File f = new File(report_folder_create + "\\reports");
	public String Execution = "null";
	public String buildname = "null";
	Scenario1 sa = new Scenario1();
	Scenario2 sc2 = new Scenario2();
	Scenario3 sc3 = new Scenario3();
	Scenario4 sc4 = new Scenario4();
	Scenario5 sc5 = new Scenario5();
	Scenario6 sc6 = new Scenario6();
	Scenario8 sc8 = new Scenario8();
	//Filo fc = new Filo();
	
	static ExtentSparkReporter spark;
	static ExtentTest test;
	static ExtentReports extent;
	
	
	boolean log = false;

	@BeforeClass
	public void getDataFromConfig() throws Exception {

		System.out.println("******************Script Execution Started******************");
		appURL = Utils.getDataFromTestConfig("URL");
		appURL1 = Utils.getDataFromTestConfig("URL1");
		appURL2 = Utils.getDataFromTestConfig("URL2");
		prurl = Utils.getDataFromTestConfig("PRURL");
		browser = Utils.getDataFromTestConfig("AppBrowser");
		project_Name = Utils.getDataFromTestConfig("Project_Name");
		version_Name = Utils.getDataFromTestConfig("Version_Name");
		buildname = project_Name + "_" + java.time.LocalDate.now().toString() + "_" + java.time.LocalTime.now();
		Execution = Utils.getDataFromTestConfig("Execution env");
        driver=getWebDriver(browser);
        
		
	}
	
	@BeforeTest
	
	public void Extentreport() throws IOException {
		extent = ATUReports.reportsetup();
	}
	
	@AfterMethod
	public void Teardown(ITestResult result) throws Throwable {
		ATUReports.teardown(driver, result, test);
//		driver.quit();
	}
	
	@AfterTest
	public void aftertest() throws Throwable {
		ATUReports.Aftertest(test);
	}
	
	@Test
	public void Scenario1() throws Exception {

		try {
			test = extent.createTest("Scenario1").assignAuthor("TD").assignCategory("E2E");
			Keywords.ActionTest(test);
			sa.Semiautorun(driver);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void Scenario2(){

		try {
			test = extent.createTest("Scenario2").assignAuthor("TD").assignCategory("E2E");
			Keywords.ActionTest(test);
			sc2.Semiautorun2(driver);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void Scenario3(){

		try {
			test = extent.createTest("Scenario3").assignAuthor("TD").assignCategory("E2E");
			Keywords.ActionTest(test);
			sc3.Semiautorun3(driver);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void Scenario4(){

		try {
			test = extent.createTest("Scenario4").assignAuthor("TD").assignCategory("E2E");
			Keywords.ActionTest(test);
			sc4.Semiautorun4(driver);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void Scenario5(){

		try {
			test = extent.createTest("Scenario5").assignAuthor("TD").assignCategory("E2E");
			Keywords.ActionTest(test);
			sc5.Semiautorun5(driver);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void Scenario6(){

		try {
			test = extent.createTest("Scenario6").assignAuthor("TD").assignCategory("E2E");
			Keywords.ActionTest(test);
			sc6.Semiautorun6(driver);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void Scenario8(){

		try {
			test = extent.createTest("Scenario8").assignAuthor("TD").assignCategory("E2E");
			Keywords.ActionTest(test);
			sc8.Semiautorun8(driver);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	
}

