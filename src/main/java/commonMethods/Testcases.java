
package commonMethods;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
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
import scripts.Scenario10;
import scripts.Scenario11;
import scripts.Scenario2;
import scripts.Scenario3;
import scripts.Scenario4;
import scripts.Scenario5;
import scripts.Scenario6;
import scripts.Scenario7;
import scripts.Scenario8;
import scripts.Scenario9;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })

public class Testcases extends Config {
	{
		System.setProperty("atu.reporter.config", System.getProperty("user.dir") + "/atu.properties");
	}
	public String usernameValue;
	public String passwordValue;
	public String project_Name;
	public String version_Name;
	public String environment;
	public String browser;
	public WebDriver driver;
	public String search;

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
	Scenario7 sc7 = new Scenario7();
	Scenario8 sc8 = new Scenario8();
	Scenario9 sc9 = new Scenario9();
	Scenario10 sc10=new Scenario10();
	Scenario11 sc11=new Scenario11();
	
	static ExtentSparkReporter spark;
	static ExtentTest test;
	static ExtentReports extent;
	
	static ExtentSparkReporter spark1;
	static ExtentTest test1;
	static ExtentReports extent1;
	
	static ExtentSparkReporter spark2;
	static ExtentTest test2;
	static ExtentReports extent2;
	
	String resultReoprtpath;
	boolean log = false;

	@BeforeClass
	public void getDataFromConfig() throws Exception {

		System.out.println("******************Script Execution Started******************");
		browser = Utils.getDataFromTestConfig("AppBrowser");
		project_Name = Utils.getDataFromTestConfig("Project_Name");
		version_Name = Utils.getDataFromTestConfig("Version_Name");
		buildname = project_Name + "_" + java.time.LocalDate.now().toString() + "_" + java.time.LocalTime.now();
		Execution = Utils.getDataFromTestConfig("Execution env");
        
	}
	
	@BeforeTest
	public void Extentreport() throws IOException {
		
		String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmm"));
		resultReoprtpath=System.getProperty("user.dir") + "/Extent/DetailedReport/TestDetailReport__" + timeStamp ;//+ ".html";
		String passReportpath=resultReoprtpath+"_Pass.html";
		String failReportPath=resultReoprtpath+"_Fail.html";
		extent = ATUReports.reportsetup();
		extent1=ATUReports.reportsetup1(failReportPath);
		extent2=ATUReports.reportsetup2(passReportpath);

		
	}
	
   
	public void Teardown(ITestResult result,WebDriver driver,ExtentTest test,String vesselName) throws Throwable {
		ATUReports.teardown(driver, result, test,vesselName);
//		driver.quit();

	}
	

    @AfterTest
	public void aftertest() throws Throwable {
		ATUReports.Aftertest(test);
		ATUReports.Aftertest1(test1);
		ATUReports.Aftertest2(test2);
	}
	
	@Test
	public void Scenario1() throws Throwable {
	    String[] vessels = {"TEM - TEMPANOS"};

	    for (int i = 0; i < vessels.length; i++) {
	    	ITestResult result1=convertToITestResult1(1);;
	        String vessel = vessels[i];
	        test = extent.createTest("Scenario1_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test1 = extent1.createTest("Scenario1_Details_Fail_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test2 = extent2.createTest("Scenario1_Details_Pass_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        Keywords.ActionTest(test,test2);
	        
	        // Initialize WebDriver
	        driver = null;
			driver = getWebDriver(browser);
	        
	        try {
	            sa.Semiautorun(driver, vessel, test, test1, resultReoprtpath);
	            result1.setStatus(1);
	        } catch (Exception e) {
	            System.out.println("Execution failed for the vessel: " + vessel);
	            result1.setStatus(2);
	            e.printStackTrace();
	        } finally {
	           Teardown(result1,driver,test,vessel);
	        }
	    }
	}
	

	
	@Test
	public void Scenario2() throws Throwable {
	    String[] vessels = {"CJV - CMA CGM JULES VERNE"};

	    for (int i = 0; i < vessels.length; i++) {
	    	ITestResult result1=convertToITestResult1(1);;
	        String vessel = vessels[i];
	        test = extent.createTest("Scenario2_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test1 = extent1.createTest("Scenario2_Details_Fail_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test2 = extent2.createTest("Scenario2_Details_Pass_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        Keywords.ActionTest(test,test2);
	        
	        // Initialize WebDriver
	        driver = null;
			driver = getWebDriver(browser);
	        
	        try {
	        	sc2.Semiautorun2(driver, vessel,test,test1,resultReoprtpath);
	            result1.setStatus(1);
	        } catch (Exception e) {
	            System.out.println("Execution failed for the vessel: " + vessel);
	            result1.setStatus(2);
	            e.printStackTrace();
	        } finally {
	           Teardown(result1,driver,test,vessel);
	        }
	    }
	}
	
	
	@Test
	public void Scenario3() throws Throwable {
	    String[] vessels = {"TIR - TIRUA"};

	    for (int i = 0; i < vessels.length; i++) {
	    	ITestResult result1=convertToITestResult1(1);
	        String vessel = vessels[i];
	        test = extent.createTest("Scenario3_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test1 = extent1.createTest("Scenario3_Details_Fail_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test2 = extent2.createTest("Scenario3_Details_Pass_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        Keywords.ActionTest(test,test2);
	        
	        // Initialize WebDriver
	        driver = null;
			driver = getWebDriver(browser);
	        
	        try {
	        	sc3.Semiautorun3(driver, vessel,test,test1,resultReoprtpath);
	            result1.setStatus(1);
	        } catch (Exception e) {
	            System.out.println("Execution failed for the vessel: " + vessel);
	            result1.setStatus(2);
	            e.printStackTrace();
	        } finally {
	           Teardown(result1,driver,test,vessel);
	        }
	    }
	}
	
	
	@Test
	public void Scenario4() throws Throwable{

		String[]   Vessels= {"JYH - AL JMELIYAH"};
		try {
			
			for (int i = 0; i < Vessels.length; i++) {
				String vessel = Vessels[i];
				ITestResult result1 = convertToITestResult1(1);
				test = extent.createTest("Scenario4_" + vessel).assignAuthor("TD").assignCategory("E2E");
				test1 = extent1.createTest("Scenario4_Details_Fail_" + vessel).assignAuthor("TD").assignCategory("E2E");
				test2 = extent2.createTest("Scenario4_Details_Pass_" + vessel).assignAuthor("TD").assignCategory("E2E");
				Keywords.ActionTest(test, test2);

				driver = null;
				driver = getWebDriver(browser);
				try {
					sc4.scenario4test(driver, vessel, test, test1, resultReoprtpath);
					result1.setStatus(1);
				} catch (Exception e) {
					System.out.println(" Execution failed for the vessel : " + vessel);
					result1.setStatus(2);
					e.printStackTrace();
				} finally {
			           Teardown(result1,driver,test,vessel);
		        }

			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}


	@Test
	public void Scenario5() throws Throwable {
	    String[] vessels = {"DEL - DELAWARE EXPRESS","EXA - EXPRESS ATHENS"};

	    for (int i = 0; i < vessels.length; i++) {
	    	ITestResult result1=convertToITestResult1(1);
	        String vessel = vessels[i];
	        test = extent.createTest("Scenario5_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test1 = extent1.createTest("Scenario5_Details_Fail_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test2 = extent2.createTest("Scenario5_Details_Pass_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        Keywords.ActionTest(test,test2);
	        
	        // Initialize WebDriver
	        driver = null;
			driver = getWebDriver(browser);
	        
	        try {
	        	sc5.Semiautorun5(driver, vessel,test,test1,resultReoprtpath);
	            result1.setStatus(1);
	        } catch (Exception e) {
	            System.out.println("Execution failed for the vessel: " + vessel);
	            result1.setStatus(2);
	            e.printStackTrace();
	        } finally {
	           Teardown(result1,driver,test,vessel);
	        }
	    }
	}
	

	
	@Test
	public void Scenario6() throws Throwable {
	    String[] vessels = {"EXA - EXPRESS ATHENS"};

	    for (int i = 0; i < vessels.length; i++) {
	    	ITestResult result1=convertToITestResult1(1);
	        String vessel = vessels[i];
	        test = extent.createTest("Scenario6_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test1 = extent1.createTest("Scenario6_Details_Fail_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test2 = extent2.createTest("Scenario6_Details_Pass_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        Keywords.ActionTest(test,test2);
	        
	        // Initialize WebDriver
	        driver = null;
			driver = getWebDriver(browser);
	        
	        try {
	        	sc6.Semiautorun6(driver, vessel,test,test1,resultReoprtpath);
	            result1.setStatus(1);
	        } catch (Exception e) {
	            System.out.println("Execution failed for the vessel: " + vessel);
	            result1.setStatus(2);
	            e.printStackTrace();
	        } finally {
	           Teardown(result1,driver,test,vessel);
	        }
	    }
	}

	@Test
	public void Scenario7() throws Throwable {
	    String[] vessels = {"AZO - SEASPAN AMAZON"};

	    for (int i = 0; i < vessels.length; i++) {
	    	ITestResult result1=convertToITestResult1(1);
	        String vessel = vessels[i];
	        test = extent.createTest("Scenario7_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test1 = extent1.createTest("Scenario7_Details_Fail_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test2 = extent2.createTest("Scenario7_Details_Pass_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        Keywords.ActionTest(test,test2);
	        
	        // Initialize WebDriver
	        driver = null;
			driver = getWebDriver(browser);
	        
	        try {
	        	sc7.Masterplan7(driver, vessel,test,test1,resultReoprtpath);
	            result1.setStatus(1);
	        } catch (Exception e) {
	            System.out.println("Execution failed for the vessel: " + vessel);
	            result1.setStatus(2);
	            e.printStackTrace();
	        } finally {
	           Teardown(result1,driver,test,vessel);
	        }
	    }
	}
	

	
	@Test
	public void Scenario8() throws Throwable {
	    String[] vessels = {"OSK - OSAKA"};

	    for (int i = 0; i < vessels.length; i++) {
	    	ITestResult result1=convertToITestResult1(1);
	        String vessel = vessels[i];
	        test = extent.createTest("Scenario8_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test1 = extent1.createTest("Scenario8_Details_Fail_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test2 = extent2.createTest("Scenario8_Details_Pass_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        Keywords.ActionTest(test,test2);
	        
	        // Initialize WebDriver
	        driver = null;
			driver = getWebDriver(browser);
	        
	        try {
	        	sc8.Semiautorun8(driver, vessel,test,test1,resultReoprtpath);
	            result1.setStatus(1);
	        } catch (Exception e) {
	            System.out.println("Execution failed for the vessel: " + vessel);
	            result1.setStatus(2);
	            e.printStackTrace();
	        } finally {
	           Teardown(result1,driver,test,vessel);
	        }
	    }
	}
	


	@Test
	public void Scenario9() throws Throwable {
	    String[] vessels = {"SPX - SOUTHAMPTON EXPRESS"};

	    for (int i = 0; i < vessels.length; i++) {
	    	ITestResult result1=convertToITestResult1(1);
	        String vessel = vessels[i];
	        test = extent.createTest("Scenario9_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test1 = extent1.createTest("Scenario9_Details_Fail_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test2 = extent2.createTest("Scenario9_Details_Pass_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        Keywords.ActionTest(test,test2);
	        
	        // Initialize WebDriver
	        driver = null;
			driver = getWebDriver(browser);
	        
	        try {
	        	sc9.Semiautorun9(driver, vessel,test,test1,resultReoprtpath);
	            result1.setStatus(1);
	        } catch (Exception e) {
	            System.out.println("Execution failed for the vessel: " + vessel);
	            result1.setStatus(2);
	            e.printStackTrace();
	        } finally {
	           Teardown(result1,driver,test,vessel);
	        }
	    }
	}
	

	
	@Test
	public void Scenario10() throws Throwable {
	    String[] vessels = {"DEL - DELAWARE EXPRESS"};

	    for (int i = 0; i < vessels.length; i++) {
	    	ITestResult result1=convertToITestResult1(1);
	        String vessel = vessels[i];
	        test = extent.createTest("Scenario10_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test1 = extent1.createTest("Scenario10_Details_Fail_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test2 = extent2.createTest("Scenario10_Details_Pass_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        Keywords.ActionTest(test,test2);
	        
	        // Initialize WebDriver
	        driver = null;
			driver = getWebDriver(browser);
	        
	        try {
	        	sc10.Semiautorun10(driver, vessel, test, test1, resultReoprtpath);
	            result1.setStatus(1);
	        } catch (Exception e) {
	            System.out.println("Execution failed for the vessel: " + vessel);
	            result1.setStatus(2);
	            e.printStackTrace();
	        } finally {
	           Teardown(result1,driver,test,vessel);
	        }
	    }
	}
	
	@Test
	public void Scenario11() throws Throwable {
	    String[] vessels = {"AZO - SEASPAN AMAZON"};

	    for (int i = 0; i < vessels.length; i++) {
	    	ITestResult result1=convertToITestResult1(1);
	        String vessel = vessels[i];
	        test = extent.createTest("Scenario11_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test1 = extent1.createTest("Scenario11_Details_Fail_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        test2 = extent2.createTest("Scenario11_Details_Pass_" + vessel).assignAuthor("TD").assignCategory("E2E");
	        Keywords.ActionTest(test,test2);
	        
	        // Initialize WebDriver
	        driver = null;
			driver = getWebDriver(browser);
	        
	        try {
	        	sc11.Semiautorun11(driver, vessel, test, test1, resultReoprtpath);
	            result1.setStatus(1);
	        } catch (Exception e) {
	            System.out.println("Execution failed for the vessel: " + vessel);
	            result1.setStatus(2);
	            e.printStackTrace();
	        } finally {
	           Teardown(result1,driver,test,vessel);
	        }
	    }
	}
	
	
}

