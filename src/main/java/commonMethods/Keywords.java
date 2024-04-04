package commonMethods;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.aventstack.extentreports.Status;
import org.testng.ITestResult;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import java.awt.Color;
//import java.awt.image.BufferedImage;
//import javax.imageio.ImageIO;
//import java.io.File;
//import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Directory;
//import commonMethods.ExtentTestConverter.CustomTestResult;

public class Keywords extends ATUReports implements Solvermindslocator {

	public String ElementWait = Utils.getDataFromTestConfig("Wait Time");
	public int WaitElementSeconds = new Integer(ElementWait);
	public String report_Filepath = Utils.getDataFromTestConfig("Reports  path");
	public String date = getCurrentDate();
	public String folder_name = report_Filepath.concat(date);
	public String folder_name_subfolder = folder_name.concat("/");
	public String report_folder_create = folder_name_subfolder;
	public String report_name = "PocReport";
	public String filepath_date_concat = folder_name_subfolder.concat(report_name).concat(".html");
	public String screenshot_folder_name = folder_name_subfolder.concat("Screenshot");
	public String screenshot_folder_path = screenshot_folder_name.concat("/");
	public String screenshot_folder_create = screenshot_folder_path;

	public boolean failureScreenshot = Directory.TestPassScreenshot;
	String DSW_GM = Utils.getDataFromTestData("TEMPANOS", "DSW_GM");
	private static String Stowagevalue1;
	private static String values;
	private static HashMap<String, String> map3 = new HashMap<>();
	private static HashMap<String, String> map1 = new HashMap<>();
	private static String columnName;
	public static ExtentTest test;
	public static ExtentTest test2;
//	private static ArrayList<String> OOGPassStowageNumberList = new ArrayList<String>();
//	private static ArrayList<String> OOGFailStowageNumberList = new ArrayList<String>();
//	private static ArrayList<String> DGFailStowageNumberList = new ArrayList<String>();
//	private static ArrayList<String> DGPassStowageNumberList = new ArrayList<String>();
//	private static ArrayList<String> DG_NAStowageNumberList = new ArrayList<String>();
//	private static ArrayList<String> CODPassStowageNumberList = new ArrayList<String>();
//	private static ArrayList<String> CODFailStowageNumberList = new ArrayList<String>();
//	private static ArrayList<String> ReeferPassStowageNumberList = new ArrayList<String>();
//	private static ArrayList<String> ReeferFailStowageNumberList = new ArrayList<String>();
//	private static ArrayList<String> SpecialPassStowageNumberList = new ArrayList<String>();
//	private static ArrayList<String> SpecialFailStowageNumberList = new ArrayList<String>();

//	private static Map<String, Integer> stowageSheet1 = new HashMap<>();

	private static Map<String, Integer> passCountMap = new HashMap<>();
	private static Map<String, Integer> failCountMap = new HashMap<>();
//	private static Map<String, Integer> passCountMap1 = new HashMap<>();
	private static Map<String, Integer> failCountMap1 = new HashMap<>();
	private static Map<String, List<String>> failStowageValues = new HashMap<>();

	private static Map<String, List<String>> failStowageValues1 = new HashMap<>();

	Set<String> DGGroupFailuresStowageNumbers = new HashSet<>();
	Set<String> OOGGroupFailuresStowageNumbers = new HashSet<>();
	Set<String> CODGroupFailuresStowageNumbers = new HashSet<>();
	Set<String> ReeferGroupFailuresStowageNumbers = new HashSet<>();
	Set<String> SpecialGroupFailuresStowageNumbers = new HashSet<>();

	static LocalDateTime currentDateTime = LocalDateTime.now();


//	private static List<String> stowagesheet1 = new ArrayList<>();
	private static Workbook workbook;
	static List<String> elsePartData = new ArrayList<>();

	private static Map<String, Integer> totalMap = new HashMap<>();
	StringBuilder failedConditionsLog = new StringBuilder();

	List<String> DG_stow = new ArrayList<>();
	List<String> DG_Excepted = new ArrayList<>();
	List<String> DG_Actual = new ArrayList<>();

	List<String> Rfr_stow = new ArrayList<>();
	List<String> Rfr_Excepted = new ArrayList<>();
	List<String> Rfr_Actual = new ArrayList<>();

	List<String> IsSpl_stow = new ArrayList<>();
	List<String> IsSpl_Excepted = new ArrayList<>();
	List<String> IsSpl_Actual = new ArrayList<>();

	List<String> OOG_stow = new ArrayList<>();
	List<String> OOG_Excepted = new ArrayList<>();
	List<String> OOG_Actual = new ArrayList<>();

	List<String> IsCOD_stow = new ArrayList<>();
	List<String> IsCOD_Excepted = new ArrayList<>();
	List<String> IsCOD_Actual = new ArrayList<>();

	List<String> DGclass_stow = new ArrayList<>();
	List<String> DGclass_Excepted = new ArrayList<>();
	List<String> DGclass_Actual = new ArrayList<>();
	
	List<String> mulHaz_stow = new ArrayList<>();
	List<String> mulHaz_Excepted = new ArrayList<>();
	List<String> mulHaz_Actual = new ArrayList<>();

	List<String> UNNO_stow = new ArrayList<>();
	List<String> UNNO_Excepted = new ArrayList<>();
	List<String> UNNO_Actual = new ArrayList<>();

	List<String> Variant_stow = new ArrayList<>();
	List<String> Variant_Excepted = new ArrayList<>();
	List<String> Variant_Actual = new ArrayList<>();
	

	List<String> FlashPoint_stow = new ArrayList<>();
	List<String> FlashPoint_Excepted = new ArrayList<>();
	List<String> FlashPoint_Actual = new ArrayList<>();

	List<String> DGLQ_stow = new ArrayList<>();
	List<String> DGLQ_Excepted = new ArrayList<>();
	List<String> DGLQ_Actual = new ArrayList<>();
	
	List<String> OOH_stow = new ArrayList<>();
	List<String> OOH_Excepted = new ArrayList<>();
	List<String> OOH_Actual = new ArrayList<>();

	List<String> OLF_stow = new ArrayList<>();
	List<String> OLF_Excepted = new ArrayList<>();
	List<String> OLF_Actual = new ArrayList<>();

	List<String> OLA_stow = new ArrayList<>();
	List<String> OLA_Excepted = new ArrayList<>();
	List<String> OLA_Actual = new ArrayList<>();
	
	List<String> OWP_stow = new ArrayList<>();
	List<String> OWP_Excepted = new ArrayList<>();
	List<String> OWP_Actual = new ArrayList<>();

	List<String> OWS_stow = new ArrayList<>();
	List<String> OWS_Excepted = new ArrayList<>();
	List<String> OWS_Actual = new ArrayList<>();

	List<String> COD_stow = new ArrayList<>();
	List<String> COD_Excepted = new ArrayList<>();
	List<String> COD_Actual = new ArrayList<>();

	List<String> DG_stownumber = new ArrayList<>();
	List<String> DG_Group_Excepted = new ArrayList<>();
	List<String> DG_Group_Actual = new ArrayList<>();

	List<String> DG_Class_stownumber = new ArrayList<>();
	List<String> DG_Class_Group_Excepted = new ArrayList<>();
	List<String> DG_Class_Group_Actual = new ArrayList<>();

	List<String> mul_Haz_stownumber = new ArrayList<>();
	List<String> mul_Haz_Group_Excepted = new ArrayList<>();
	List<String> mul_Haz_Group_Actual = new ArrayList<>();

	List<String> UNNO_stownumber = new ArrayList<>();
	List<String> UNNO_Group_Excepted = new ArrayList<>();
	List<String> UNNO_Group_Actual = new ArrayList<>();

	List<String> Variant_stownumber = new ArrayList<>();
	List<String> Variant_Group_Excepted = new ArrayList<>();
	List<String> Variant_Group_Actual = new ArrayList<>();

	List<String> FlashPoint_stownumber = new ArrayList<>();
	List<String> FlashPoint_Group_Excepted = new ArrayList<>();
	List<String> FlashPoint_Group_Actual = new ArrayList<>();

	List<String> DGLQ_stownumber = new ArrayList<>();
	List<String> DGLQ_Group_Excepted = new ArrayList<>();
	List<String> DGLQ_Group_Actual = new ArrayList<>();

	List<String> OOG_stownumber = new ArrayList<>();
	List<String> OOG_Group_Excepted = new ArrayList<>();
	List<String> OOG_Group_Actual = new ArrayList<>();

	List<String> OOH_stownumber = new ArrayList<>();
	List<String> OOH_Group_Excepted = new ArrayList<>();
	List<String> OOH_Group_Actual = new ArrayList<>();

	List<String> OLF_stownumber = new ArrayList<>();
	List<String> OLF_Group_Excepted = new ArrayList<>();
	List<String> OLF_Group_Actual = new ArrayList<>();

	List<String> OLA_stownumber = new ArrayList<>();
	List<String> OLA_Group_Excepted = new ArrayList<>();
	List<String> OLA_Group_Actual = new ArrayList<>();

	List<String> OWP_stownumber = new ArrayList<>();
	List<String> OWP_Group_Excepted = new ArrayList<>();
	List<String> OWP_Group_Actual = new ArrayList<>();

	List<String> OWS_stownumber = new ArrayList<>();
	List<String> OWS_Group_Excepted = new ArrayList<>();
	List<String> OWS_Group_Actual = new ArrayList<>();

	List<String> Is_COD_stownumber = new ArrayList<>();
	List<String> Is_COD_Group_Excepted = new ArrayList<>();
	List<String> Is_COD_Group_Actual = new ArrayList<>();

	List<String> COD_stownumber = new ArrayList<>();
	List<String> COD_Group_Excepted = new ArrayList<>();
	List<String> COD_Group_Actual = new ArrayList<>();

	List<String> IsSpl_stownumber = new ArrayList<>();
	List<String> IsSpl_Group_Excepted = new ArrayList<>();
	List<String> IsSpl_Group_Actual = new ArrayList<>();

	List<String> Rfr_stownumber = new ArrayList<>();
	List<String> Rfr_Group_Excepted = new ArrayList<>();
	List<String> Rfr_Group_Actual = new ArrayList<>();

	
	public String getCurrentDate() {
		Format formatter = new SimpleDateFormat("dd-MM-YYYY HH-mm-ss");
		Date date = new Date();
		String value = formatter.format(date);
		return value;
	}

	public static String[] splitXpath(String path) {
		String[] a = path.split(">");
		return a;
	}



	public void wait(WebDriver driver, String inputData) {
		try {
			int time = Integer.parseInt(inputData);
			int seconds = time * 1000;
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			add1(driver, "Unable to wait ", LogAs.FAILED, true, "Wait");
			Assert.fail();
		}
	}

	public void waitForElement(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			// int WaitElementSeconds1 = new Integer(ElementWait);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(values[1])));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(values[1])));
			add(driver, "Wait for the Element " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Element Not Found - " + values[0] + "- " + e.getLocalizedMessage() + e, LogAs.FAILED, true,values[0]);
			Extent_fail(driver, "Element is not present " + values[0], test);
			Assert.fail();
		}
	}

	public void waitForElement1(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			// int WaitElementSeconds1 = new Integer(ElementWait);
			driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 6000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(values[1])));
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(values[1])));
			add(driver, "Wait for the Element " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Element Not Found - " + values[0] + "- " + e.getLocalizedMessage() + e, LogAs.FAILED, true,values[0]);
			Assert.fail();
		}
	}

	public void waitForElementtopresent(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			// int WaitElementSeconds1 = new Integer(ElementWait);
			driver.manage().timeouts().implicitlyWait(160, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(values[1])));
			add(driver, "Wait for the Element " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Element Not Found - " + values[0] + "- " + e.getLocalizedMessage() + e, LogAs.FAILED, true,values[0]);
			Assert.fail();
		}
	}

	public void waitForElementWithLessWait(WebDriver driver, String xpath) {

		String[] values = splitXpath(xpath);
		try {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			// System.out.println(driver.getTitle());
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(values[1])));
			add(driver, "Wait for visibility of Element" + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			System.out.println(" Exception " + e);
			add1(driver, "Element not visible " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,values[0]);
			Assert.fail();
		}

	}

	public void click(WebDriver driver, String Xpath) {
		String[] values = splitXpath(Xpath);
		try {
			waitForElement(driver, Xpath);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			WebDriverWait wait1 = new WebDriverWait(driver, 30);
			wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(values[1])));
			WebElement element = driver.findElement(By.xpath(values[1]));
			element.click();
			add(driver, "Click on " + values[0], LogAs.PASSED, true, values[0]);

			Extent_pass(driver, "Click on " + values[0], test);

		} catch (Exception e) {
			e.printStackTrace();
			add1(driver, "Unable to click on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,values[0]);
			Extent_fail(driver, "Unable to click on " + values[0], test);
			Assert.fail();
		}
	
	}

	public void click1(WebDriver driver, String path) {
		String[] values = splitXpath(path);
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement webElement = driver.findElement(By.xpath(values[1]));
//			System.out.println(webElement);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
					webElement);
			js.executeScript("arguments[0].click();", webElement);
			System.out.println(values[0] + " clicked");
			add(driver, "Click1 on " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			System.out.println(" Exception " + e);
			add1(driver, "Unable to click1 on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,values[0]);
			Assert.fail();
		}
	}

	public void clickWithoutFail(WebDriver driver, String path) {
		String[] values = splitXpath(path);
		WebElement webElement = driver.findElement(By.xpath(values[1]));
		webElement.click();
		add(driver, "Click on " + values[0], LogAs.PASSED, true, values[0]);

	}

	
	public void doubleClick(WebDriver driver, String element) {
		String[] values = splitXpath(element);
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			Actions action = new Actions(driver).doubleClick(webElement);
			action.build().perform();
			add(driver, "Double click on " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Unable to click on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,values[0]);

			Assert.fail();
		}
	}

	public String sendKeys(WebDriver driver, String xpaths, String keysToSend) {
		String[] values = splitXpath(xpaths);

		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebDriverWait wait1 = new WebDriverWait(driver, 20);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(values[1])));

			WebElement webElement = driver.findElement(By.xpath(values[1]));
			webElement.sendKeys(keysToSend);

			add(driver, "Type on " + values[0], keysToSend, true, values[0]);
			wait(driver, "1");
		} catch (Exception e) {
			add1(driver, "Unable to type on " + values[0] + "- " + e.getLocalizedMessage(), keysToSend, true,values[0]);

			Assert.fail();
		}
		return keysToSend;

	}

	public static void implicitwait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public String searchelement(WebDriver driver, String xpaths, String keysToSend) {
		String[] values = splitXpath(xpaths);

		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			webElement.sendKeys(keysToSend, Keys.ENTER);

			add(driver, "Type on " + values[0], keysToSend, true, values[0]);

		} catch (Exception e) {
			add1(driver, "Unable to type on " + values[0] + "- " + e.getLocalizedMessage(), keysToSend, true,values[0]);

			Assert.fail();
		}
		return keysToSend;

	}
	
	public String getText(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			String text = webElement.getText();
			return text;

		} catch (Exception e) {
			Assert.fail();
			return null;
		}
	}

	public void newTab2(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.open()");

		// Switch to the new tab
		ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
		wait(driver, "1");
		driver.switchTo().window(tab.get(tab.size() - 1));
		wait(driver, "2");

	}

	public String getTextWithoutFail(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		WebElement webElement = driver.findElement(By.xpath(values[1]));
		String text = webElement.getText();
		add(driver, "The value ' " + text + " ' is retrieved for the element ' " + values[0] + "'", LogAs.PASSED, true,values[0]);
		return text;

	}

	public static void waitTime(WebDriver driver, String waitSeconds) {
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(waitSeconds), TimeUnit.SECONDS);
	}

	public void scrollBottom(WebDriver driver) {
		try {

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scroll(0,350)", "");
			waitTime(driver, "5");
			// add(driver, "Scrolled to the bottom ", LogAs.PASSED, true, "Not");
		} catch (Exception e) {
			// add1(driver, "Unable to scroll to the bottom", LogAs.FAILED, true, "Not");

			Assert.fail();
		}
	}

	public void scrollTop(WebDriver driver) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scroll(0,-200)", "");
			add(driver, "Scrolled to the Top ", LogAs.PASSED, true, "Not");

		} catch (Exception e) {
			add1(driver, "Unable to scroll to the Top", LogAs.FAILED, true, "Not" + "- " + e.getLocalizedMessage());

			Assert.fail();
		}
	}

	
	public boolean isDisplayed(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			return webElement.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isDisplayed1(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			return webElement.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void elementnotvisible(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {

			WebElement webElement = driver.findElement(By.xpath(values[1]));
			for (int i = 1; i <= 100; i++) {

				boolean flag = webElement.isDisplayed();

				if (flag == true) {
					Thread.sleep(3000);
				} else {
					break;
				}
			}

		} catch (Exception e) {

		}
	}

	public String getAttribute(WebDriver driver, String xpath, String attribute) {
		String[] values = splitXpath(xpath);
		try {
			WebElement inputBox = driver.findElement(By.xpath(values[1]));
			String textInsideInputBox = inputBox.getAttribute(attribute);
			add(driver, "Retrieved the text of " + values[0], textInsideInputBox, true, values[0]);
			return textInsideInputBox;
		} catch (NoSuchElementException e) {
			add1(driver, "Unable to retrieve the value " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED,
					true, values[0]);

			Assert.fail();
			return null;
		}

	}

	public void closetab(WebDriver driver) {

		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_W);
			wait(driver, "2");
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_W);
			wait(driver, "2");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(0));
			wait(driver, "5");

		} catch (Exception e) {
		}
	}

	public boolean verifyElementIsNotPresent(WebDriver driver, String xpaths) {
		String[] values = splitXpath(xpaths);
		try {
			WebElement element = driver.findElement(By.xpath(values[1]));
			element.isDisplayed();
			add1(driver, "Element is Present" + values[0], LogAs.FAILED, true, values[0]);
			Assert.fail();
			return true;
		} catch (NoSuchElementException e) {
			add(driver, "Verified Element is not Present" + values[0] + "- " + e.getLocalizedMessage(), values[0], true,
					values[0]);
		}
		return true;

	}

//	public void scrollUsingElement(WebDriver driver, String xpath) {
//		String[] values = splitXpath(xpath);
//		try {
//			WebElement element = driver.findElement(By.xpath(values[1]));
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//			add(driver, "Scrolled to " + values[0], LogAs.PASSED, true, values[0]);
//		} catch (Exception e) {
//			add1(driver, "Unable to scroll " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
//					values[0]);
//
//			Assert.fail();
//		}
//	}
//

//
//	public void waitTillVisibilityElement(WebDriver driver, String xpath) {
//		String[] values = splitXpath(xpath);
//
//		try {
//			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
//			WebElement webElement = driver.findElement(By.xpath(values[1]));
//			WebDriverWait wait = new WebDriverWait(driver, 30);
//			wait.until(ExpectedConditions.visibilityOf(webElement));
//			add(driver, "Waited till the element is visible", LogAs.PASSED, true, values[0]);
//		} catch (Exception e) {
//			add(driver, "Unable to wait till an element is visible", LogAs.FAILED, true,values[0] + "-" + e.getLocalizedMessage());
//
//			Assert.fail();
//
//		}
//	}
//
////	
//	public int getRandomNum(WebDriver driver, int upperlimit) {
//		List<Integer> randomZeroToSeven = new ArrayList<>();
//		for (int i = 1; i <= upperlimit; i++) {
//			randomZeroToSeven.add(i);
//		}
//		Collections.shuffle(randomZeroToSeven);
//
//		return randomZeroToSeven.get(0);
//
//	}
//
//	
//
//	public void deSelectByValue(WebDriver driver, String xpath, String inputData) {
//		String[] values = splitXpath(xpath);
//		try {
//			WebElement webElement = driver.findElement(By.xpath(values[1]));
//			Select selectBox = new Select(webElement);
//			selectBox.deselectByValue(inputData);
//			add(driver, "Deselect the dropdown by index " + values[0], LogAs.PASSED, true, values[1]);
//		} catch (Exception e) {
//			add(driver, "Unable to deselect the dropdown by index" + values[0], LogAs.FAILED, true, values[1]);
//
//			Assert.fail();
//		}
//	}
//
//
//	public void switchDefaultContent(WebDriver driver) {
//		driver.switchTo().defaultContent();
//	}
//
//	public void getAutoit(String exePath) {
//		try {
//			Runtime.getRuntime().exec(exePath);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	}
//
//	public void dragElement(WebDriver driver, String xpath) {
//		String[] values = splitXpath(xpath);
//		try {
//			WebElement webElement = driver.findElement(By.xpath(values[1]));
//			fromElement = webElement;
//			add(driver, "Drag an element " + values[0], LogAs.PASSED, true, values[0]);
//		} catch (Exception e) {
//			add1(driver, "Unable to drag an element " + values[0], LogAs.FAILED, true, values[0]);
//		}
//
//	}
//
//	public void dropElement(WebDriver driver, String xpath) {
//		String[] values = splitXpath(xpath);
//		try {
//			WebElement webElement = driver.findElement(By.xpath(values[1]));
//			Actions action = new Actions(driver);
//			Action dragDrop = action.dragAndDrop(fromElement, webElement).build();
//			dragDrop.perform();
//			add(driver, "Drop an element " + values[0], LogAs.PASSED, true, values[0]);
//		} catch (Exception e) {
//			add1(driver, "Unable to drag an element " + values[0], LogAs.FAILED, true, values[0]);
//		}
//	}
//
//	
	
	public void verifyTextIsNotPresent(WebDriver driver, String NormalXpath, String inputData) {
		try {
			WebDriverWait waits = new WebDriverWait(driver, WaitElementSeconds);
			waits.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(NormalXpath), inputData));
			add(driver, "Text is not present" + inputData, LogAs.PASSED, true, inputData);
		} catch (Exception e) {
			add1(driver, "Text is present" + inputData + "- " + e.getLocalizedMessage(), LogAs.FAILED, true, inputData);
		}

	}


	
	public void quit(WebDriver driver) {
		try {
			driver.quit();
		} catch (Exception e) {
		}
	}

	public void refreshPage(WebDriver driver) {
		try {
			waitTime(driver, "5");
			driver.navigate().refresh();
			waitTime(driver, "5");
		} catch (Exception e) {
			Assert.fail();
		}
	}

	public void maximize(WebDriver driver) {
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
			Assert.fail();
		}
	}

	public void keyTab(WebDriver driver) {
		try {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.TAB).build().perform();

		} catch (Exception e) {
			Assert.fail();
		}
	}

	public void keyboardTab(WebDriver driver) {
		Actions actionObject = new Actions(driver);
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).perform();
	}

	public void enter(WebDriver driver) {
		try {
			Actions actionObject = new Actions(driver);
			actionObject.sendKeys(Keys.ENTER).build().perform();
		} catch (Exception e) {

			Assert.fail();
		}
	}

	public void keyDown(WebDriver driver) {
		Actions actionObject = new Actions(driver);
		actionObject = actionObject.sendKeys(Keys.ARROW_DOWN);
		actionObject.perform();
	}

	public void keyUp(WebDriver driver) {
		Actions actionObject = new Actions(driver);
		actionObject = actionObject.sendKeys(Keys.ARROW_UP);
		actionObject.perform();
	}

	public void keyboardPageUp(WebDriver driver) {
		Actions actionObject = new Actions(driver);
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_UP).perform();
	}

	public void refreshUsingKeys(WebDriver driver) {
		Actions actionObject = new Actions(driver);
		actionObject.sendKeys(Keys.F5).perform();
	}

	public void keyboardPageDown(WebDriver driver) {
		Actions actionObject = new Actions(driver);
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).perform();
		waitTime(driver, "5");
	}

	public void keyboardEnd(WebDriver driver) {
		Actions actionObject = new Actions(driver);
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		waitTime(driver, "5");
	}

	public void keyboardHome(WebDriver driver) {
		Actions actionObject = new Actions(driver);
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).perform();
		waitTime(driver, "5");
	}

	public void keyboardArrowUp(WebDriver driver) {
		Actions actionObject = new Actions(driver);
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_UP).perform();
	}

	public void keyboardArrowDown(WebDriver driver) {
		Actions actionObject = new Actions(driver);
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_DOWN).perform();
	}

	public void keyboardArrowLeft(WebDriver driver) {
		Actions actionObject = new Actions(driver);
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_LEFT).perform();
	}

	public void keyboardArrowRight(WebDriver driver) {
		Actions actionObject = new Actions(driver);
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_RIGHT).perform();
	}

	public void pageMaximizeUsingKey(WebDriver driver) {
		Actions actionObject = new Actions(driver);
		actionObject = actionObject.sendKeys(Keys.F11);
		actionObject.perform();
	}

	public void deleteAllCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();

	}

	public void navigateUrl(WebDriver driver, String inputData) {
		if (inputData == null) {
			add(driver, " Navigated to " + inputData, LogAs.FAILED, true, inputData);

			Assert.fail(inputData);
		} else {
			driver.navigate().to(inputData);
			add(driver, " Navigated to " + inputData, LogAs.PASSED, true, inputData);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

	}

	public void windowhandles(WebDriver driver) {

		Set<String> windowhandles1 = driver.getWindowHandles();
		System.out.println(windowhandles1);
		List<String> list = new ArrayList<>(windowhandles1);
		driver.switchTo().window(list.get(1));
		System.out.println(driver.getCurrentUrl());

	}
	

	public void newTab(WebDriver driver) {
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_T);
			r.keyRelease(KeyEvent.VK_CONTROL);
			wait(driver, "1");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			wait(driver, "1");
			wait(driver, "1");
		} catch (Exception e) {
		}
	}



//	public void closeTab(WebDriver driver) {
//		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
//		driver.switchTo().defaultContent();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(tabs1.get(0));
//	}

	public void switchtotab(WebDriver driver, int inputData) {
		Capabilities localCapabilities = ((RemoteWebDriver) driver).getCapabilities();
		String BROWSER_NAME = localCapabilities.getBrowserName().toLowerCase();
		if (BROWSER_NAME.equalsIgnoreCase("firefox")) {
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
			driver.switchTo().defaultContent();
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(inputData));
		}
		if (BROWSER_NAME.equalsIgnoreCase("chrome")) {
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
			driver.switchTo().defaultContent();
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(inputData));
			wait(driver, "2");
		}
	}



//	
	
	public void mouseOverAndClick(WebDriver driver, String element) {
		String[] values = splitXpath(element);
		WebElement webElement = driver.findElement(By.xpath(values[1]));
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(webElement).click().build().perform();

		} catch (Exception e) {

		}
	}

	
	
	public void scrolltill(WebDriver driver) {
		try {

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scroll(0,12200)", "");
			waitTime(driver, "5");
			// add(driver, "Scrolled to the bottom ", LogAs.PASSED, true, "Not");
		} catch (Exception e) {
			// add1(driver, "Unable to scroll to the bottom", LogAs.FAILED, true, "Not");
			Assert.fail();

		}
	}

	public void Alert1(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void Escape(WebDriver driver) {

		Robot rb1;
		try {
			rb1 = new Robot();
			rb1.delay(1000);
			rb1.keyPress(KeyEvent.VK_ESCAPE);
			rb1.delay(500);
			rb1.keyRelease(KeyEvent.VK_ESCAPE);

		} catch (Exception e) {
			System.out.println("escape its not working");
		}

	}

	public void takescreenshot(WebDriver driver, String screenshot_path) {
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "/Screenshots" + screenshot_path + ".png");
		try {
			FileUtils.copyFile(scr, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		add(driver, "Captured the screenshot " + "", LogAs.PASSED, true, "");
		Extent_pass(driver, "Click on " + "Screenshot", test);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void takescreenshot1(WebDriver driver, String screenshot_path) {
		try {
			Robot robot = new Robot();

			// Define the region to capture using top-left and bottom-right coordinates
			int x1 = 70; // x-coordinate of the top-left corner
			int y1 = 200; // y-coordinate of the top-left corner
			int x2 = 1350; // x-coordinate of the bottom-right corner
			int y2 = 500; // y-coordinate of the bottom-right corner

			Rectangle captureRect = new Rectangle(x1, y1, x2, y2);

			BufferedImage screenCapture = robot.createScreenCapture(captureRect);

			// Save the screenshot
			File dest = new File(System.getProperty("user.dir") + "/Screenshots" + screenshot_path + ".png");
			ImageIO.write(screenCapture, "png", dest);

			System.out.println("Screenshot captured successfully!");

			add(driver, "Captured the screenshot " + "", LogAs.PASSED, true, "");
			Extent_pass(driver, "Taken Screenshot for " + screenshot_path, test);

		} catch (AWTException | IOException e) {
			e.printStackTrace();
		}

	}

	public String imageComparision(WebDriver driver, String ExpectedImage_path, String actualImage_path)
			throws IOException {
		String image_res = null;
		try {
			BufferedImage img1 = ImageIO.read(new File(
					System.getProperty("user.dir") + "/Screenshots/Expected_screenshot" + ExpectedImage_path + ".png"));
			BufferedImage img2 = ImageIO.read(new File(
					System.getProperty("user.dir") + "/Screenshots/Actual_screenshot" + actualImage_path + ".png"));
			int w1 = img1.getWidth();
			int w2 = img2.getWidth();
			int h1 = img1.getHeight();
			int h2 = img2.getHeight();
			long diff = 0;
			if ((w1 != w2) || (h1 != h2)) {
				System.out.println("Both images should have same dimwnsions");
			} else {

				for (int j = 0; j < h1; j++) {
					for (int i = 0; i < w1; i++) {
						// Getting the RGB values of a pixel
						int pixel1 = img1.getRGB(i, j);
						Color color1 = new Color(pixel1, true);
						int r1 = color1.getRed();
						int g1 = color1.getGreen();
						int b1 = color1.getBlue();
						int pixel2 = img2.getRGB(i, j);
						Color color2 = new Color(pixel2, true);
						int r2 = color2.getRed();
						int g2 = color2.getGreen();
						int b2 = color2.getBlue();
						// sum of differences of RGB values of the two images
						long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
						diff = diff + data;
					}
				}
				String s[] = ExpectedImage_path.split("/");
				double avg = diff / (w1 * h1 * 3);
				double percentage = (avg / 255) * 100;
				System.out.println("Difference: " + percentage);
				if (percentage < 0.5) {
					image_res = "Pass";

					add(driver, s[1] + " - " + s[2] + " - Image compared sucessfully" + "", LogAs.PASSED, true,
							"diff.hasDiff()");
					System.out.println(s[1] + " - " + s[2] + " - Image compared sucessfully");

					Extent_pass(driver, "Click on " + " Expected and Actual images are same", test);

				} else {
					add1(driver, s[1] + " - " + s[2] + " - Expected and Actual images are not same " + "", LogAs.FAILED,
							true, "diff.hasDiff()");
					System.out.println(s[1] + " - " + s[2] + " - Expected and Actual images are not same ");
					Extent_fail(driver, "Unable to click on " + "Expected and Actual images are not same", test);
					Assert.fail();

				}

			}
		} catch (Exception e) {
			System.out.println("Exception while taking Screenshot" + e.getMessage());
			return e.getMessage();
		}
		return image_res;

	}

	
	public void addCargo(WebDriver driver) {  
		waitForElement(driver, AddCargoTab);
		waitForElement(driver, ISOCode);
		doubleClick(driver, ISOCode);
		sendKeys(driver, ISOCode, "45G1"); // need to change

		waitForElement(driver, selectISO);
		click(driver, selectISO);

		waitForElement(driver, add_Ok);
		click(driver, add_Ok);

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

	}

	

	public void verifyElementText(WebDriver driver, String xpath, String expectedtext) {
		String[] values = splitXpath(xpath);
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			waitForElement(driver, xpath);
			String text = driver.findElement(By.xpath(values[1])).getText();
			if (text.equals(expectedtext)) {
				add(driver, "Exepected text is present" + values[0] + "" + text, LogAs.PASSED, true, values[0]);
			}
		} catch (NoSuchElementException e) {
			add1(driver, "Expected text is Not Present " + values[0], LogAs.FAILED, true, values[0]);
			Assert.fail();
		}
	}

	public String imageComparison(WebDriver driver, String ExpectedImage_path, String actualImage_path)
			throws IOException {
		String image_res = null;

		try {
			BufferedImage img1 = ImageIO
					.read(new File("./Screenshots/Expected_screenshot" + ExpectedImage_path + ".png"));
			BufferedImage img2 = ImageIO.read(new File("./Screenshots/Actual_screenshot" + actualImage_path + ".png"));

			int w1 = img1.getWidth();
			int w2 = img2.getWidth();
			int h1 = img1.getHeight();
			int h2 = img2.getHeight();
			long diff = 0;

			if ((w1 != w2) || (h1 != h2)) {
				System.out.println("Both images should have the same dimensions");
			} else {
				for (int j = 0; j < h1; j++) {
					for (int i = 0; i < w1; i++) {
						int pixel1 = img1.getRGB(i, j);
						Color color1 = new Color(pixel1, true);
						int r1 = color1.getRed();
						int g1 = color1.getGreen();
						int b1 = color1.getBlue();

						int pixel2 = img2.getRGB(i, j);
						Color color2 = new Color(pixel2, true);
						int r2 = color2.getRed();
						int g2 = color2.getGreen();
						int b2 = color2.getBlue();

						long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
						diff = diff + data;
					}
				}

				String[] s = ExpectedImage_path.split("/");
				double avg = (double) diff / (w1 * h1 * 3);
				double percentage = (avg / 255) * 100;
				System.out.println("Difference: " + percentage);

				if (percentage < 0.4) {
					image_res = "Pass";
					add(driver, s[1] + " - " + s[2] + " - Image compared successfully", LogAs.PASSED, true,
							"diff.hasDiff()");
					System.out.println(s[1] + " - " + s[2] + " - Image compared successfully");
					Extent_pass(driver, s[1] + " - " + s[2] + " - Expected and Actual images are same", test);
				} else {
					add1(driver, s[1] + " - " + s[2] + " - Expected and Actual images are not the same", LogAs.FAILED,
							true, "diff.hasDiff()");
					System.out.println(s[1] + " - " + s[2] + " - Expected and Actual images are not the same ");
					Extent_fail(driver, s[1] + " - " + s[2] + " - Expected and Actual images are not same", test);
					// Assert.fail();
				}
			}
		} catch (Exception e) {
			System.out.println("Exception while taking Screenshot: " + e.getMessage());
			e.printStackTrace(); // Log the exception stack trace
			return e.getMessage();
		}
		return image_res;
	}

//	public void uploadfile(WebDriver driver, String path) {
//		try {
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//			WebElement element = driver.findElement(By.xpath("//input[@type='file']"));
//			File file = new File(path);
//			System.out.println(file.getAbsolutePath());
//			element.sendKeys(file.getAbsolutePath());
//			add(driver, "uploaded the file " + path, LogAs.PASSED, true, path);
//			wait(driver, "2");
//		} catch (Exception e) {
//			add1(driver, "upload is falied - " + path + e, LogAs.FAILED, true, e.getLocalizedMessage());
//			e.printStackTrace();
//
//			Assert.fail();
//		}
//	}



	public void clickVessel(WebDriver driver, String Vesseltype) {

		WebDriverWait wait = new WebDriverWait(driver, 20);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + Vesseltype + "')]")));
		driver.findElement(By.xpath("//*[contains(text(),'" + Vesseltype + "')]")).click();

	}

	//new
	public void clickVWR(WebDriver driver, String VWR_value) {

		WebDriverWait wait = new WebDriverWait(driver, 20);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(@value,'"+VWR_value+"')]")));
		driver.findElement(By.xpath("//option[contains(@value,'"+VWR_value+"')]")).click();
		
	}
	
	//new
		public void clickPlanTemplate(WebDriver driver, String plantypeValue) {

			WebDriverWait wait = new WebDriverWait(driver, 20);
			//option[text()='Lashing']
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[text()='"+plantypeValue+"']")));
			driver.findElement(By.xpath("//option[text()='"+plantypeValue+"']")).click();
			
		}
	
	//new  
	public void bayClose(WebDriver driver) {
		wait(driver, "1");
		List<WebElement> closeIcon=driver.findElements(By.xpath("(//div[contains(@id,'TwinBayCanvas')]//preceding::button[@aria-label='Close'])"));
		int l=closeIcon.size();
		
		driver.findElement(By.xpath("(//div[contains(@id,'TwinBayCanvas')]//preceding::button[@aria-label='Close'])["+l+"]")).click();		
	}
	
	public void clickPlan(WebDriver driver, String planName) {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'" + planName + "')]")));

		try {
			WebElement element = driver.findElement(By.xpath("//*[contains(text(),'" + planName + "')]"));
			Actions action = new Actions(driver).doubleClick(element);
			action.build().perform();
			add(driver, "Double click on " + planName, LogAs.PASSED, true, "");
		} catch (Exception e) {
			add1(driver, "Unable to click on " + planName, LogAs.FAILED, true, "");
			Assert.fail();
		}
	}

	public int columnCountValue(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<WebElement> elements = driver.findElements(
				By.xpath("(//div[@ref='rightContainer'])[1]/preceding::div[@aria-colindex and @tabindex='-1']"));
		String colId;
		int colCount = 0;
		int act;

		for (WebElement element : elements) {
			colId = element.getAttribute("aria-colindex");
			act = Integer.parseInt(colId.trim());
			if (colCount <= act) {
				colCount = act;
			}
		}
		System.out.println("Final Column Count : " + colCount);

		return colCount;
	}

	// 7th validation 2nd
	public int RowCountValue(WebDriver driver) {

		WebElement ele = driver.findElement(By.xpath("(//div[contains(@class,'ag-row-last')])[2]"));
//				"(//div[contains(@class,'ag-row-level-0 ag-row-position-absolute ag-row-last')])[2]"));
//		         (//div[@class='ag-row ag-row-no-focus ag-row-odd ag-row-level-0 ag-row-position-absolute ag-row-last'])[2]
		String rowValue = ele.getAttribute("aria-rowindex");
		int lastRowCount = Integer.parseInt(rowValue);
		System.out.println("Last row count : " + lastRowCount);

		return lastRowCount;
	}

	// 7th validation 3rd
	public ArrayList<String> headerValueList(WebDriver driver, int colCount) {

		ArrayList<String> headerValues = new ArrayList<String>();

		for (int i = 1; i <= colCount; i++) {
			WebElement headElement = driver.findElement(
					By.xpath("((//div[@ref='rightContainer'])[1]/preceding::div[@aria-colindex='" + i + "'])[1]"));
			String header = headElement.getAttribute("col-id");
			headerValues.add(header);
		}

		return headerValues;
	}

	// 7th validation 4th
	public Map<String, ArrayList<String>> cellListMap(WebDriver driver, int colCount, int lastRowCount,
			ArrayList<String> headerValues) {

		Map<String, ArrayList<String>> cellvalues = new HashMap<String, ArrayList<String>>();

		for (int j = 0; j < colCount; j++) {
			ArrayList<String> firstColoumn = new ArrayList<String>();
			String head = headerValues.get(j);

			for (int l = 1; l < lastRowCount; l++) {

				WebElement CelElement = driver.findElement(By.xpath(
						"(//div[@class='ag-cell ag-cell-not-inline-editing ag-cell-auto-height ag-cell-value' and @col-id='"
								+ head + "'])[" + l + "]"));
				String CellText = CelElement.getText();
				firstColoumn.add(CellText);
			}
			cellvalues.put(head, firstColoumn);
			// System.out.println("Text taken and stored...");
		}

		return cellvalues;
	}

	// 7th validation 5th
	public void createExcelForContainerPool(int colCount, int lastRowCount, ArrayList<String> headerValues,
			Map<String, ArrayList<String>> cellvalues, String filePath) {

		int rowCount = lastRowCount;
		int columnCount = colCount;

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");

		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			Row row = sheet.createRow(rowIndex);
			if (rowIndex == 0) {

				for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {

					Cell cell = row.createCell(columnIndex);
					cell.setCellValue(headerValues.get(columnIndex));
				}

			} else {

				for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
					String key = headerValues.get(columnIndex);
					ArrayList<String> value = cellvalues.get(key);
					String b = value.get(rowIndex - 1);
					Cell cell = row.createCell(columnIndex);
					cell.setCellValue(b);

				}
			}

		}

		try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
			workbook.write(outputStream);
			System.out.println("Excel sheet created successfully at: " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void CompareContainerData(String mastFilePath, String testFilePath) throws Exception {

		Fillo fillo = new Fillo();

//			System.setProperty("Row", "2");

		Connection connection1 = fillo.getConnection(mastFilePath);
		Connection connection2 = fillo.getConnection(testFilePath);

		Recordset recordset1 = connection1.executeQuery("Select * from `Sheet1`");
		Recordset recordset2 = connection2.executeQuery("Select * from `Sheet1`");

		List<String> columnsToCompare1 = recordset1.getFieldNames();
		List<String> columnsToCompare2 = recordset2.getFieldNames();

		// Compare column counts
		if (columnsToCompare1.size() != columnsToCompare2.size()) {
			System.out.println("Column count mismatch between two sheets.");

		}

		while (recordset1.next()) {
			recordset2.next(); // Move to the next row in recordset2
			for (String columnName : columnsToCompare1) {
				// Check if the column exists in connection2
				if (!columnsToCompare2.contains(columnName)) {
					System.out.println("Column '" + columnName + "' is present in connection1 but not in connection2");
					continue; // Skip this column and proceed to the next one
				}

				String valueSheet1Column = recordset1.getField(columnName);
				String valueSheet2Column = recordset2.getField(columnName);

				// Perform comparison
				if (valueSheet1Column.equals(valueSheet2Column)) {
//		                System.out.println("Matched - POD Name: " + recordset1.getField("sPod") + " || Column name: " + columnName + " || Expected value: " + valueSheet1Column + " || Actual value: " + valueSheet2Column);
				} else {
					System.out.println("Not matched - POD Name: " + recordset1.getField("sPod") + " || Column name: "
							+ columnName + " || Expected value: " + valueSheet1Column + " || Actual value: "
							+ valueSheet2Column);
				}
			}
		}

		recordset1.close();
		recordset2.close();
		connection1.close();
		connection2.close();

	}

	// ******************* Script_4 start ************************

	// script_4
	public void addCargo(WebDriver driver, String Code) {

		waitForElement(driver, AddCargoTab);
		waitForElement(driver, ISOCode);
		doubleClick(driver, ISOCode);
		sendKeys(driver, ISOCode, Code);

		try {
			Robot robot = new Robot();
			robot.delay(100);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(100);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(300);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		waitForElement(driver, add_Ok);
		click(driver, add_Ok);

		if (isDisplayed(driver, clickyes)) {
			click(driver, clickyes);
		} else {
			System.out.println("Element dragged");
		}

	}

	// script_4
	public void writeToExcel(String filePath, String sheetName, int rowNum, int colNum, String value) {
		try (FileInputStream fis = new FileInputStream(new File(filePath)); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			Row row = sheet.getRow(rowNum - 1);
			if (row == null) {
				row = sheet.createRow(rowNum - 1);
			}

			Cell cell = row.createCell(colNum - 1);
			cell.setCellValue(value);

			try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
				workbook.write(fos);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// script_4
	public void writeToExcelInteger(String filePath, String sheetName, int rowNum, int colNum, int value) {
		try (FileInputStream fis = new FileInputStream(new File(filePath)); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			Row row = sheet.getRow(rowNum - 1);
			if (row == null) {
				row = sheet.createRow(rowNum - 1);
			}

			Cell cell = row.createCell(colNum - 1);
			cell.setCellValue(value);

			try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
				workbook.write(fos);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// script_4
	public void excelWriteGM(String filePathValue, String gmValue) throws IOException {

		FileInputStream fis = new FileInputStream(filePathValue);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet("Vessel Weight Range");

		int lastrow = ws.getLastRowNum() + 1;
		for (int i = 0; i < lastrow; i++) {
			short row = ws.getRow(i).getLastCellNum();
			for (int k = 0; k <= row; k++) {
				XSSFCell columnname = ws.getRow(i).getCell(k);
				String clmname = columnname.getStringCellValue().toString();
				if (clmname.contains("gm")) {
					for (int j = 2; j < lastrow + 1; j++) {
						writeToExcel(filePathValue, "Vessel Weight Range", j, k + 1, gmValue);
					}

				}

				if (clmname.contains("bayno")) {
					for (int j = 2; j < lastrow + 1; j++) {
						writeToExcelInteger(filePathValue, "Vessel Weight Range", j, k + 1, j - 1);
					}
				}
			}
		}

	}

	// script_4
	public void typeWeight(String filePath, String weightType, ArrayList<Integer> arrayList) throws IOException {

		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet("Vessel Weight Range");

		int lastrow = ws.getLastRowNum() + 1;
		for (int i = 0; i < lastrow; i++) {
			short row = ws.getRow(i).getLastCellNum();
			for (int k = 0; k <= row; k++) {
				XSSFCell columnname = ws.getRow(i).getCell(k);
				String clmname = columnname.getStringCellValue().toString();

				if (clmname.equals(weightType)) {
					for (int j = 2; j <= arrayList.size() + 1; j++) {

						writeToExcelInteger(filePath, "Vessel Weight Range", j, k + 1, arrayList.get(j - 2));
					}

				}
			}
		}
	}

	// script_4
	public ArrayList<Integer>[] readHighestValue1(WebDriver driver, int size, int[] out_X, int[] out_Y, int[] inner_X,
			int[] inner_Y) {

		ArrayList<Integer>[] result = new ArrayList[2];

		ArrayList<Integer> weightListOuter = new ArrayList<Integer>();
		ArrayList<Integer> weightListInner = new ArrayList<Integer>();
		waitForElement(driver, Bay1);
		doubleClick(driver, Bay1);
		wait(driver, "4");
		int outer;
		int inner;

		for (int k = 0; k < size; k++) {
			Actions action3 = new Actions(driver);
			WebElement Bay_element = driver.findElement(By.xpath("//div[contains(text(),' / ')]"));

			// moving to outer first stow
			action3.moveToElement(Bay_element);
			action3.moveByOffset(out_X[k], out_Y[k]).pause(Duration.ofSeconds(1)).build().perform();

			outer = getStowNoOuter(driver);
			weightListOuter.add(outer);

			// moving to inner first stow

			action3.moveToElement(Bay_element);
			action3.moveByOffset(inner_X[k], inner_Y[k]).pause(Duration.ofSeconds(1)).build().perform();

			inner = getStowNoInner(driver);
			weightListInner.add(inner);

			if (k != (size - 1)) {

				waitForElement(driver, PreviousBay);
				click(driver, PreviousBay);
			}

		}

		waitForElement(driver, bayClose);
		click(driver, bayClose);

		result[0] = weightListOuter;
		result[1] = weightListInner;

		return result;

	}

	// script_4
	public int getStowNoInner(WebDriver driver) {

		waitForElement(driver, stowNo);
		WebElement stow;
		String stowNoVal;
		String stowNoValue;
		String lastTwoDigits;
		int expectStowNo;
		//

		String st = driver.findElement(By.xpath("(//div[@class='tooltiptext'])[1]")).getText();
		String middleTwo = st.substring(st.indexOf('=') + 4, st.indexOf('=') + 6);
		int target = Integer.parseInt(middleTwo);
		int b = target - 1;

		Actions act = new Actions(driver);
		while (isDisplayed(driver, stowNo)) {

			int left = 1;
			while (b <= target + 1) {

				if (isDisplayed(driver, WeightTrue)) {
					WebElement weightEle = driver.findElement(By.xpath("(//div[@class='tooltiptext'])[1]"));
					stowNoValue = weightEle.getText();
					System.out.println(" *************** ");
					System.out.println(" Inner Highest StowageNo : " + stowNoValue);

					lastTwoDigits = stowNoValue.substring(stowNoValue.length() - 2);
					expectStowNo = Integer.parseInt(lastTwoDigits);
					System.out.println(" *************** ");
					System.out.println("Last two digits : " + expectStowNo);
					System.out.println();
					add(driver, stowNoValue + " : Last two digits : " + expectStowNo, LogAs.PASSED, true, "");

					return expectStowNo;
				} else {

					if ((b + 1) > (target + 1)) {
						break;

					}

					if (b == target + 1) {
						break;
					}
					act.moveByOffset(-5, 0).build().perform();

					if (b <= (target + 1)) {
						if (!isDisplayed(driver, stowNo)) {
							act.moveByOffset(4, 0).build().perform();
						}
					}

//						System.out.println(left+" Completed");
//						left++;
				}

				st = driver.findElement(By.xpath("(//div[@class='tooltiptext'])[1]")).getText();
				middleTwo = st.substring(st.indexOf('=') + 4, st.indexOf('=') + 6);
				b = Integer.parseInt(middleTwo);

			}

			act.moveByOffset(0, 7).build().perform();
			wait(driver, "1");

			if (!isDisplayed(driver, stowNo)) {
				return 0;
			}

			String rightmove = driver.findElement(By.xpath("(//div[@class='tooltiptext'])[1]")).getText();
			middleTwo = rightmove.substring(rightmove.indexOf('=') + 4, rightmove.indexOf('=') + 6);
			int c = Integer.parseInt(middleTwo);

			int k = 0;
			while (c < (target + 2)) {

				if (isDisplayed(driver, WeightTrue)) {
					WebElement weightEle = driver.findElement(By.xpath("(//div[@class='tooltiptext'])[1]"));
					stowNoValue = weightEle.getText();
					System.out.println(" *************** ");
					System.out.println(" Inner Highest StowageNo : " + stowNoValue);

					lastTwoDigits = stowNoValue.substring(stowNoValue.length() - 2);
					expectStowNo = Integer.parseInt(lastTwoDigits);
					System.out.println(" *************** ");
					System.out.println("Last two digits : " + expectStowNo);
					System.out.println();
					add(driver, stowNoValue + " : Last two digits : " + expectStowNo, LogAs.PASSED, true, "");
					return expectStowNo;
				} else {

					act.moveByOffset(6, 0).build().perform();
					if (k > 3) {

						if ((c + 2) == (target + 2)) {
							break;
						}
					}

					if (c < (target + 2)) {
						if (!isDisplayed(driver, stowNo)) {
							act.moveByOffset(-3, 0).build().perform();
						}
					}

					rightmove = driver.findElement(By.xpath("(//div[@class='tooltiptext'])[1]")).getText();
					middleTwo = rightmove.substring(rightmove.indexOf('=') + 4, rightmove.indexOf('=') + 6);
					c = Integer.parseInt(middleTwo);
					k++;
				}

			}

			act.moveByOffset(0, 7).build().perform();

			if (!isDisplayed(driver, stowNo)) {
				break;
			}
			wait(driver, "1");
			String leftmove = driver.findElement(By.xpath("(//div[@class='tooltiptext'])[1]")).getText();
			middleTwo = leftmove.substring(leftmove.indexOf('=') + 4, leftmove.indexOf('=') + 6);
			b = Integer.parseInt(middleTwo);

		}

		///

		return 0;
	}

	public int getStowNoOuter(WebDriver driver) {

		waitForElement(driver, stowNo);
		WebElement stow;
		String stowNoVal;
		String stowNoValue;
		String lastTwoDigits;
		int expectStowNo;
		Actions act = new Actions(driver);
		while (isDisplayed(driver, stowNo)) {

			if (isDisplayed(driver, WeightTrue)) {
				WebElement weightEle = driver.findElement(By.xpath("(//div[@class='tooltiptext'])[1]"));
				stowNoValue = weightEle.getText();
				System.out.println(" *************** ");
				System.out.println(" Outer Highest StowageNo : " + stowNoValue);

				lastTwoDigits = stowNoValue.substring(stowNoValue.length() - 2);
				expectStowNo = Integer.parseInt(lastTwoDigits);
				System.out.println(" *************** ");
				System.out.println("Last two digits : " + expectStowNo);
				add(driver, stowNoValue + " : Last two digits : " + expectStowNo, LogAs.PASSED, true, "");

				System.out.println();
				return expectStowNo;
			} else {
				act.moveByOffset(0, 6).build().perform();
			}
		}

		return 0;
	}

	// ***************************** Script_4 End ****************************

	public void Excel1(String master, String actual) {
		try {
			FileInputStream fis = new FileInputStream(actual);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheet("CARGO LIST");
			HashMap<String, List<HashMap<String, String>>> map2 = new HashMap<>();
			List<HashMap<String, List<HashMap<String, String>>>> finallist = new ArrayList<>();
			List<HashMap<String, String>> list = new ArrayList<>();
			List<Map<String, String>> citiesStates = new ArrayList<>();
			List<HashMap<String, String>> clonelist = new ArrayList<>();

			List<Object> mainvalue1 = new ArrayList<>();
			List<String> values = null;
			boolean flag1 = false;
			boolean flag2 = false;
			int lastrow = ws.getLastRowNum() + 1;
			int count = 0;

			for (int i = 10; i < lastrow; i++) {

				short row = ws.getRow(i).getLastCellNum();
				for (int j = 0; j < row; j++) {
					XSSFCell columnname = ws.getRow(i).getCell(j);

					// System.out.println("Last row is : "+ lastrow);

					if (columnname != null) {
						String clmname = columnname.getStringCellValue().toString();

						if (clmname.equals("Stowage")) {

							values = Arrays.asList("S.No", "Cntr No", "ISO", "Weight(t)", "Is Vgm", "POL", "POD",
									"FDest", "Opr", "Mty", "Status", "Restow From", "Stowage", "IsSpl", "Is COD", "COD",
									"Rfr", "Is WCL", "RF Min", "RF Avg", "RF Max", "OOG", "OOH (m)", "OLF (m)",
									"OLA (m)", "OWP (m)", "OWS (m)", "Booking No", "PoOrigin", "EQP Type", "Eqp Status",
									"Remarks", "DG", "mul Haz", "DG class", "UNNO", "Variant", "DGRegulation",
									"HasubPageNo", "FlashPoint", "MeaUnitQua", "Pack Group", "EMS", "MFAG",
									"Hald Upper", "Hald Lower", "DGLQ", "Mar Pol", "SunLt", "FTXBool", "TextSubQua",
									"Seal No", "Customer", "IID", "lastpol", "Restow Reason", "Party to Claim",
									"Party of Faul", "Restow Remarks");

							for (int k = i + 1; k < lastrow; k++) {

								XSSFCell Stowage = ws.getRow(k).getCell(j);
								Stowagevalue1 = Stowage.getStringCellValue().toString();
								mainvalue1.add(Stowagevalue1);

								for (int r = 0; r < values.size(); r++) {
									flag1 = true;
									XSSFCell dataCell = ws.getRow(k).getCell(r);

									if (dataCell != null) {
										String cellValue;
										if (dataCell.getCellTypeEnum() == CellType.NUMERIC) {
											// Use DataFormatter to format numeric value as string
											cellValue = new DataFormatter().formatCellValue(dataCell);
										} else {
											cellValue = dataCell.getStringCellValue();
										}
										map3.put(values.get(r), cellValue);
									}
								}

								excelmaster(Stowagevalue1, map3, master);

							}
						}

						if (flag1 == true) {
							break;
						}

					}

					if (flag1 == true) {
						break;
					}

				}
				if (flag1 == true) {
					break;
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("|**********************************************************************|");

		printCounts();

		printCounts1();

		System.out.println("|**********************************************************************|");

		if ((DG_stow != null)) {

			for (int i = 0; i < DG_stow.size(); i++) {

				Extent_group_table(test, DG_stow.get(i), "DG Group", "DG", DG_Excepted.get(i), DG_Actual.get(i));

			}

		}

		if ((DGclass_stow != null)) {
			for (int i = 0; i < DGclass_stow.size(); i++) {
				Extent_group_table(test, DGclass_stow.get(i), "DG Group", "DG class", DGclass_Excepted.get(i),
						DGclass_Actual.get(i));
			}
		}

		if ((mulHaz_stow != null)) {
			for (int i = 0; i < mulHaz_stow.size(); i++) {
				Extent_group_table(test, mulHaz_stow.get(i), "DG Group", "mul Haz", mulHaz_Excepted.get(i),
						mulHaz_Actual.get(i));
			}
		}

		if ((UNNO_stow != null)) {
			for (int i = 0; i < UNNO_stow.size(); i++) {
				Extent_group_table(test, UNNO_stow.get(i), "DG Group", "UNNO", UNNO_Excepted.get(i),
						UNNO_Actual.get(i));
			}
		}

		if ((Variant_stow != null)) {
			for (int i = 0; i < Variant_stow.size(); i++) {
				Extent_group_table(test, Variant_stow.get(i), "DG Group", "Variant", Variant_Excepted.get(i),
						Variant_Actual.get(i));
			}
		}

		if ((FlashPoint_stow != null)) {
			for (int i = 0; i < FlashPoint_stow.size(); i++) {
				Extent_group_table(test, FlashPoint_stow.get(i), "DG Group", "FlashPoint", FlashPoint_Excepted.get(i),
						FlashPoint_Actual.get(i));
			}
		}

		if ((DGLQ_stow != null)) {
			for (int i = 0; i < DGLQ_stow.size(); i++) {
				Extent_group_table(test, DGLQ_stow.get(i), "DG Group", "DGLQ", DGLQ_Excepted.get(i),
						DGLQ_Actual.get(i));
			}
		}

		if ((OOG_stow != null)) {
			for (int i = 0; i < OOG_stow.size(); i++) {
				Extent_group_table(test, OOG_stow.get(i), "OOG Group", "OOG", OOG_Excepted.get(i), OOG_Actual.get(i));
			}
		}

		if ((OOH_stow != null)) {
			for (int i = 0; i < OOH_stow.size(); i++) {
				Extent_group_table(test, OOH_stow.get(i), "OOG Group", "OOH", OOH_Excepted.get(i), OOH_Actual.get(i));
			}
		}

		if ((OLF_stow != null)) {
			for (int i = 0; i < OLF_stow.size(); i++) {
				Extent_group_table(test, OLF_stow.get(i), "OOG Group", "OLF", OLF_Excepted.get(i), OLF_Actual.get(i));
			}
		}
		if ((OLA_stow != null)) {

			for (int i = 0; i < OLA_stow.size(); i++) {
				Extent_group_table(test, OLA_stow.get(i), "OOG Group", "OLA", OLA_Excepted.get(i), OLA_Actual.get(i));
			}
		}

		if ((OWP_stow != null)) {
			for (int i = 0; i < OWP_stow.size(); i++) {
				Extent_group_table(test, OWP_stow.get(i), "OOG Group", "OWP", OWP_Excepted.get(i), OWP_Actual.get(i));
			}
		}

		if ((OWS_stow != null)) {
			for (int i = 0; i < OWS_stow.size(); i++) {
				Extent_group_table(test, OWS_stow.get(i), "OOG Group", "OWS", OWS_Excepted.get(i), OWS_Actual.get(i));
			}
		}

		if ((IsCOD_stow != null)) {
			for (int i = 0; i < OLA_stow.size(); i++) {
				Extent_group_table(test, IsCOD_stow.get(i), "COD Group", "Is COD", IsCOD_Excepted.get(i),
						IsCOD_Actual.get(i));
			}
		}

		if ((COD_stow != null)) {
			for (int i = 0; i < COD_stow.size(); i++) {
				Extent_group_table(test, COD_stow.get(i), "COD Group", "COD", COD_Excepted.get(i), COD_Actual.get(i));
			}
		}

		if ((Rfr_stow != null)) {
			for (int i = 0; i < Rfr_stow.size(); i++) {
				Extent_group_table(test, Rfr_stow.get(i), "Rfr Group", "Rfr", Rfr_Excepted.get(i), Rfr_Actual.get(i));
			}
		}

		if ((IsSpl_stow != null)) {
			for (int i = 0; i < IsSpl_stow.size(); i++) {
				Extent_group_table(test, IsSpl_stow.get(i), "Spl Group", "IsSpl", IsSpl_Excepted.get(i),
						IsSpl_Actual.get(i));
			}
		}

	}

	public void excelmaster(String stowagevalue1, HashMap<String, String> map3, String master) throws Exception {

		FileInputStream fis = new FileInputStream(master);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet("CARGO LIST");
		HashMap<String, List<HashMap<String, String>>> map2 = new HashMap<>();
		List<HashMap<String, List<HashMap<String, String>>>> finallist = new ArrayList<>();
		List<HashMap<String, String>> list = new ArrayList<>();
		List<Map<String, String>> citiesStates = new ArrayList<>();
		List<HashMap<String, String>> clonelist = new ArrayList<>();
		String flag4 = "";

		List<Object> mainvalue = new ArrayList<>();

		List<String> values = null;
		boolean flag1 = false;
		boolean flag2 = false;
		int lastrow = ws.getLastRowNum() + 1;
		int count = 0;

		for (int i = 10; i < lastrow; i++) {

			short row = ws.getRow(i).getLastCellNum();
			for (int j = 0; j < row; j++) {
				XSSFCell columnname = ws.getRow(i).getCell(j);

				// System.out.println("Column name : "+ columnname.toString());
				String clmname = columnname.getStringCellValue().toString();

				if (clmname.equals("Stowage")) {

					values = Arrays.asList("S.No", "Cntr No", "ISO", "Weight(t)", "Is Vgm", "POL", "POD", "FDest",
							"Opr", "Mty", "Status", "Restow From", "Stowage", "IsSpl", "Is COD", "COD", "Rfr", "Is WCL",
							"RF Min", "RF Avg", "RF Max", "OOG", "OOH (m)", "OLF (m)", "OLA (m)", "OWP (m)", "OWS (m)",
							"Booking No", "PoOrigin", "EQP Type", "Eqp Status", "Remarks", "DG", "mul Haz", "DG class",
							"UNNO", "Variant", "DGRegulation", "HasubPageNo", "FlashPoint", "MeaUnitQua", "Pack Group",
							"EMS", "MFAG", "Hald Upper", "Hald Lower", "DGLQ", "Mar Pol", "SunLt", "FTXBool",
							"TextSubQua", "Seal No", "Customer", "IID", "lastpol", "Restow Reason", "Party to Claim",
							"Party of Faul", "Restow Remarks");

					// HashMap<String, String> map1 = new HashMap<>();
					String Stowagevalue = null;

					for (int k = i + 1; k < lastrow; k++) {

						XSSFCell Stowage = ws.getRow(k).getCell(j);
						Stowagevalue = Stowage.getStringCellValue().toString();
						mainvalue.add(Stowagevalue);

						if (Stowage.toString().trim().equals(stowagevalue1)) {

							flag4 = "is presented";
							// System.out.println("Excepted Stowage value : " + Stowage + " || Actual
							// Stowage value : "
							// + stowagevalue1);

							for (int r = 0; r < values.size(); r++) {
								flag1 = true;
								XSSFCell dataCell = ws.getRow(k).getCell(r);

								if (dataCell != null) {
									String cellValue;
									if (dataCell.getCellTypeEnum() == CellType.NUMERIC) {
										// Use DataFormatter to format numeric value as string
										cellValue = new DataFormatter().formatCellValue(dataCell);
									} else {
										cellValue = dataCell.getStringCellValue();
									}
									map1.put(values.get(r), cellValue);
								}
							}

							String ISO_com_Status;
							String Weight_com_Status;
							String POL_com_Status;
							String POD_com_Status;
							String Mty_com_Status;
							String IsSpl_com_Status;
							String IsCOD_com_Status;
							String Rfr_com_Status;
							String OOG_com_Status;
							String DG_com_Status;
							String mulHaz_com_Status;
							String DGclass_com_Status;
							String UNNO_com_Status;
							String OOH_com_Status;
							String OLF_com_Status;
							String OLA_com_Status;
							String OWP_com_Status;
							String OWS_com_Status;
							String BookingNo_com_Status;
							String Variant_com_Status;
							String FlashPoint_com_Status;
							String DGLQ_com_Status;
							String Html_ISO;
							String Html_Weight;
							String Html_POL;
							String Html_POD;
							String Html_Mty;
							String Html_IsSpl;
							String Html_IsCOD;
							String Html_Rfr;
							String Html_OOG;
							String Html_DG;
							String Html_mulHaz;
							String Html_DGclass;
							String Html_UNNO;
							String Html_OOH;
							String Html_OLF;
							String Html_OLA;
							String Html_OWP;
							String Html_OWS;
							String Html_BookingNo;
							String Html_Variant;
							String Html_FlashPoint;
							String Html_DGLQ;

							// group

							String fail_DGclass_group;

							try {

								System.out.print(" || " + "Stowage value : " + stowagevalue1 + " || ");
								compareAndIncrementCount1("ISO", map1.get("ISO"), map3.get("ISO"), stowagevalue1);
								compareAndIncrementCount1("Weight(t)", map1.get("Weight(t)"), map3.get("Weight(t)"),
										stowagevalue1);
								compareAndIncrementCount1("POL", map1.get("POL"), map3.get("POL"), stowagevalue1);
								compareAndIncrementCount1("POD", map1.get("POD"), map3.get("POD"), stowagevalue1);
								compareAndIncrementCount1("Mty", map1.get("Mty"), map3.get("Mty"), stowagevalue1);
								compareAndIncrementCount1("IsSpl", map1.get("IsSpl"), map3.get("IsSpl"), stowagevalue1);
								compareAndIncrementCount1("Is COD", map1.get("Is COD"), map3.get("Is COD"),
										stowagevalue1);
								compareAndIncrementCount1("Rfr", map1.get("Rfr"), map3.get("Rfr"), stowagevalue1);
								compareAndIncrementCount1("OOG", map1.get("OOG"), map3.get("OOG"), stowagevalue1);
								compareAndIncrementCount1("DG", map1.get("DG"), map3.get("DG"), stowagevalue1);
								compareAndIncrementCount1("mul Haz", map1.get("mul Haz"), map3.get("mul Haz"),
										stowagevalue1);
								compareAndIncrementCount1("DG class", map1.get("DG class"), map3.get("DG class"),
										stowagevalue1);
								compareAndIncrementCount1("UNNO", map1.get("UNNO"), map3.get("UNNO"), stowagevalue1);
								compareAndIncrementCount1("OOH (m)", map1.get("OOH (m)"), map3.get("OOH (m)"),
										stowagevalue1);
								compareAndIncrementCount1("OLF (m)", map1.get("OLF (m)"), map3.get("OLF (m)"),
										stowagevalue1);
								compareAndIncrementCount1("OLA (m)", map1.get("OLA (m)"), map3.get("OLA (m)"),
										stowagevalue1);
								compareAndIncrementCount1("OWP (m)", map1.get("OWP (m)"), map3.get("OWP (m)"),
										stowagevalue1);
								compareAndIncrementCount1("OWS (m)", map1.get("OWS (m)"), map3.get("OWS (m)"),
										stowagevalue1);
								compareAndIncrementCount1("Booking No", map1.get("Booking No"), map3.get("Booking No"),
										stowagevalue1);
								compareAndIncrementCount1("Variant", map1.get("Variant"), map3.get("Variant"),
										stowagevalue1);
								compareAndIncrementCount1("FlashPoint", map1.get("FlashPoint"), map3.get("FlashPoint"),
										stowagevalue1);
								compareAndIncrementCount1("DGLQ", map1.get("DGLQ"), map3.get("DGLQ"), stowagevalue1);

								System.out.println("\n |*************************************|");

								if ((map1.get("ISO") == null && map3.get("ISO") == null)
										|| (map1.get("ISO") != null && map1.get("ISO").equals(map3.get("ISO")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									ISO_com_Status = "Pass";

								} else {
//										 failCountMap1.put("ISO", failCountMap1.getOrDefault("ISO", 0) + 1);
									ISO_com_Status = "Fail";
								}

								Html_ISO = "<th>" + "ISO" + "</th><th>" + map1.get("ISO") + "</th><th>"
										+ map3.get("ISO") + "</th><th>" + ISO_com_Status + "</th>";

								if ((map1.get("Weight(t)") == null && map3.get("Weight(t)") == null)
										|| (map1.get("Weight(t)") != null
												&& map1.get("Weight(t)").equals(map3.get("Weight(t)")))) {
									Weight_com_Status = "Pass";

								} else {
									Weight_com_Status = "Fail";
//										failCountMap1.put("Weight(t)", failCountMap1.getOrDefault("Weight(t)", 0) + 1);
								}

								Html_Weight = "<th>" + "Weight(t)" + "</th><th>" + map1.get("Weight(t)") + "</th><th>"
										+ map3.get("Weight(t)") + "</th><th>" + Weight_com_Status + "</th>";

								if ((map1.get("POL") == null && map3.get("POL") == null)
										|| (map1.get("POL") != null && map1.get("POL").equals(map3.get("POL")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									POL_com_Status = "Pass";

								} else {
//										 failCountMap1.put("POL", failCountMap1.getOrDefault("POL", 0) + 1);
									POL_com_Status = "Fail";
								}

								Html_POL = "<th>" + "POL" + "</th><th>" + map1.get("POL") + "</th><th>"
										+ map3.get("POL") + "</th><th>" + POL_com_Status + "</th>";

								if ((map1.get("POD") == null && map3.get("POD") == null)
										|| (map1.get("POD") != null && map1.get("POD").equals(map3.get("POD")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									POD_com_Status = "Pass";

								} else {
//										 failCountMap1.put("POD", failCountMap.getOrDefault("POD", 0) + 1);
									POD_com_Status = "Fail";
								}

								Html_POD = "<th>" + "POD" + "</th><th>" + map1.get("POD") + "</th><th>"
										+ map3.get("POD") + "</th><th>" + POD_com_Status + "</th>";

								if ((map1.get("Mty") == null && map3.get("Mty") == null)
										|| (map1.get("Mty") != null && map1.get("Mty").equals(map3.get("Mty")))) {
									Mty_com_Status = "Pass";

								} else {
									Mty_com_Status = "Fail";
//										failCountMap1.put("Mty", failCountMap1.getOrDefault("Mty", 0) + 1);
								}

								Html_Mty = "<th>" + "Mty" + "</th><th>" + map1.get("Mty") + "</th><th>"
										+ map3.get("Mty") + "</th><th>" + Mty_com_Status + "</th>";

								if ((map1.get("IsSpl") == null && map3.get("IsSpl") == null)
										|| (map1.get("IsSpl") != null && map1.get("IsSpl").equals(map3.get("IsSpl")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									IsSpl_com_Status = "Pass";

								} else {
//										 failCountMap1.put("IsSpl", failCountMap1.getOrDefault("IsSpl", 0) + 1);
									IsSpl_com_Status = "Fail";
								}

								Html_IsSpl = "<th>" + "IsSpl" + "</th><th>" + map1.get("IsSpl") + "</th><th>"
										+ map3.get("IsSpl") + "</th><th>" + IsSpl_com_Status + "</th>";

								if ((map1.get("Is COD") == null && map3.get("Is COD") == null)
										|| (map1.get("Is COD") != null
												&& map1.get("Is COD").equals(map3.get("Is COD")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									IsCOD_com_Status = "Pass";

								} else {
//										 failCountMap1.put("Is COD", failCountMap1.getOrDefault("Is COD", 0) + 1);
									IsCOD_com_Status = "Fail";
								}

								Html_IsCOD = "<th>" + "Is COD" + "</th><th>" + map1.get("Is COD") + "</th><th>"
										+ map3.get("Is COD") + "</th><th>" + IsCOD_com_Status + "</th>";

//									if ((map1.get("Rfr") == null && map3.get("Rfr") == null)
//											|| (map1.get("Rfr") != null && map1.get("Rfr").equals(map3.get("Rfr")))) {
//	//													            passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
//										Rfr_com_Status = "Pass";
//	
//									} else {
//										 failCountMap1.put("Rfr", failCountMap.getOrDefault("Rfr", 0) + 1);
//										Rfr_com_Status = "Fail";
//									}
//	
//									Html_Rfr = "<th>" + "Rfr" + "</th><th>" + map1.get("Rfr") + "</th><th>"
//											+ map3.get("Rfr") + "</th><th>" + Rfr_com_Status + "</th>";

								if ((map1.get("Rfr") == null && map3.get("Rfr") == null)
										|| (map1.get("Rfr") != null && map1.get("Rfr").equals(map3.get("Rfr")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									Rfr_com_Status = "Pass";

								} else {
//										 failCountMap1.put("Rfr", failCountMap1.getOrDefault("Rfr", 0) + 1);
									Rfr_com_Status = "Fail";
								}

								Html_Rfr = "<th>" + "Rfr" + "</th><th>" + map1.get("Rfr") + "</th><th>"
										+ map3.get("Rfr") + "</th><th>" + Rfr_com_Status + "</th>";

								if ((map1.get("OOG") == null && map3.get("OOG") == null)
										|| (map1.get("OOG") != null && map1.get("OOG").equals(map3.get("OOG")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									OOG_com_Status = "Pass";

								} else {
//										 failCountMap1.put("OOG", failCountMap1.getOrDefault("OOG", 0) + 1);
									OOG_com_Status = "Fail";
								}

								Html_OOG = "<th>" + "OOG" + "</th><th>" + map1.get("OOG") + "</th><th>"
										+ map3.get("OOG") + "</th><th>" + OOG_com_Status + "</th>";

								if ((map1.get("DG") == null && map3.get("DG") == null)
										|| (map1.get("DG") != null && map1.get("DG").equals(map3.get("DG")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									DG_com_Status = "Pass";

								} else {
//										 failCountMap1.put("DG", failCountMap1.getOrDefault("DG", 0) + 1);
									DG_com_Status = "Fail";
								}

								Html_DG = "<th>" + "DG" + "</th><th>" + map1.get("DG") + "</th><th>" + map3.get("DG")
										+ "</th><th>" + DG_com_Status + "</th>";

								if ((map1.get("mul Haz") == null && map3.get("mul Haz") == null)
										|| (map1.get("mul Haz") != null
												&& map1.get("mul Haz").equals(map3.get("mul Haz")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									mulHaz_com_Status = "Pass";

								} else {
//										 failCountMap1.put("mul Haz", failCountMap1.getOrDefault("mul Haz", 0) + 1);
									mulHaz_com_Status = "Fail";
								}

								Html_mulHaz = "<th>" + "mul Haz" + "</th><th>" + map1.get("mul Haz") + "</th><th>"
										+ map3.get("mul Haz") + "</th><th>" + mulHaz_com_Status + "</th>";

								if ((map1.get("DG class") == null && map3.get("DG class") == null)
										|| (map1.get("DG class") != null
												&& map1.get("DG class").equals(map3.get("DG class")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									DGclass_com_Status = "Pass";

								} else {
//										 failCountMap1.put("DG class", failCountMap1.getOrDefault("DG class", 0) + 1);
									DGclass_com_Status = "Fail";
								}

								Html_DGclass = "<th>" + "DG class" + "</th><th>" + map1.get("DG class") + "</th><th>"
										+ map3.get("DG class") + "</th><th>" + DGclass_com_Status + "</th>";

								if ((map1.get("UNNO") == null && map3.get("UNNO") == null)
										|| (map1.get("UNNO") != null && map1.get("UNNO").equals(map3.get("UNNO")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									UNNO_com_Status = "Pass";

								} else {
//										 failCountMap1.put("UNNO", failCountMap1.getOrDefault("UNNO", 0) + 1);
									UNNO_com_Status = "Fail";
								}

								Html_UNNO = "<th>" + "UNNO" + "</th><th>" + map1.get("UNNO") + "</th><th>"
										+ map3.get("UNNO") + "</th><th>" + UNNO_com_Status + "</th>";

								if ((map1.get("OOH (m)") == null && map3.get("OOH (m)") == null)
										|| (map1.get("OOH (m)") != null
												&& map1.get("OOH (m)").equals(map3.get("OOH (m)")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									OOH_com_Status = "Pass";

								} else {
//										 failCountMap1.put("OOH (m)", failCountMap1.getOrDefault("OOH (m)", 0) + 1);
									OOH_com_Status = "Fail";
								}

								Html_OOH = "<th>" + "OOH (m)" + "</th><th>" + map1.get("OOH (m)") + "</th><th>"
										+ map3.get("OOH (m)") + "</th><th>" + OOH_com_Status + "</th>";

								if ((map1.get("OLF (m)") == null && map3.get("OLF (m)") == null)
										|| (map1.get("OLF (m)") != null
												&& map1.get("OLF (m)").equals(map3.get("OLF (m)")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									OLF_com_Status = "Pass";

								} else {
//										 failCountMap1.put("OLF (m)", failCountMap1.getOrDefault("OLF (m)", 0) + 1);
									OLF_com_Status = "Fail";
								}

								Html_OLF = "<th>" + "OLF (m)" + "</th><th>" + map1.get("OLF (m)") + "</th><th>"
										+ map3.get("OLF (m)") + "</th><th>" + OLF_com_Status + "</th>";

								if ((map1.get("OLA (m)") == null && map3.get("OLA (m)") == null)
										|| (map1.get("OLA (m)") != null
												&& map1.get("OLA (m)").equals(map3.get("OLA (m)")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									OLA_com_Status = "Pass";

								} else {
//										 failCountMap1.put("OLA (m)", failCountMap1.getOrDefault("OLA (m)", 0) + 1);
									OLA_com_Status = "Fail";
								}

								Html_OLA = "<th>" + "OLA (m)" + "</th><th>" + map1.get("OLA (m)") + "</th><th>"
										+ map3.get("OLA (m)") + "</th><th>" + OLA_com_Status + "</th>";

								if ((map1.get("OWP (m)") == null && map3.get("OWP (m)") == null)
										|| (map1.get("OWP (m)") != null
												&& map1.get("OWP (m)").equals(map3.get("OWP (m)")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									OWP_com_Status = "Pass";

								} else {
//										 failCountMap1.put("OWP (m)", failCountMap1.getOrDefault("OWP (m)", 0) + 1);
									OWP_com_Status = "Fail";
								}

								Html_OWP = "<th>" + "OWP (m)" + "</th><th>" + map1.get("OWP (m)") + "</th><th>"
										+ map3.get("OWP (m)") + "</th><th>" + OWP_com_Status + "</th>";

								if ((map1.get("OWS (m)") == null && map3.get("OWS (m)") == null)
										|| (map1.get("OWS (m)") != null
												&& map1.get("OWS (m)").equals(map3.get("OWS (m)")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									OWS_com_Status = "Pass";

								} else {
//										 failCountMap1.put("ISO", failCountMap1.getOrDefault("ISO", 0) + 1);
									OWS_com_Status = "Fail";
								}

								Html_OWS = "<th>" + "OWS (m)" + "</th><th>" + map1.get("OWS (m)") + "</th><th>"
										+ map3.get("OWS (m)") + "</th><th>" + OWS_com_Status + "</th>";

								if ((map1.get("Booking No") == null && map3.get("Booking No") == null)
										|| (map1.get("Booking No") != null
												&& map1.get("Booking No").equals(map3.get("Booking No")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									BookingNo_com_Status = "Pass";

								} else {
									// failCountMap.put("ISO", failCountMap.getOrDefault("ISO", 0) + 1);
									BookingNo_com_Status = "Fail";
								}

								Html_BookingNo = "<th>" + "Booking No" + "</th><th>" + map1.get("Booking No")
										+ "</th><th>" + map3.get("Booking No") + "</th><th>" + BookingNo_com_Status
										+ "</th>";

								if ((map1.get("Variant") == null && map3.get("Variant") == null)
										|| (map1.get("Variant") != null
												&& map1.get("Variant").equals(map3.get("Variant")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									Variant_com_Status = "Pass";

								} else {
									// failCountMap.put("ISO", failCountMap.getOrDefault("ISO", 0) + 1);
									Variant_com_Status = "Fail";
								}

								Html_Variant = "<th>" + "Variant" + "</th><th>" + map1.get("Variant") + "</th><th>"
										+ map3.get("Variant") + "</th><th>" + Variant_com_Status + "</th>";

								if ((map1.get("FlashPoint") == null && map3.get("FlashPoint") == null)
										|| (map1.get("FlashPoint") != null
												&& map1.get("FlashPoint").equals(map3.get("FlashPoint")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									FlashPoint_com_Status = "Pass";

								} else {
									// failCountMap.put("ISO", failCountMap.getOrDefault("ISO", 0) + 1);
									FlashPoint_com_Status = "Fail";
								}

								Html_FlashPoint = "<th>" + "FlashPoint" + "</th><th>" + map1.get("FlashPoint")
										+ "</th><th>" + map3.get("FlashPoint") + "</th><th>" + FlashPoint_com_Status
										+ "</th>";

								if ((map1.get("DGLQ") == null && map3.get("DGLQ") == null)
										|| (map1.get("DGLQ") != null && map1.get("DGLQ").equals(map3.get("DGLQ")))) {
									// passCountMap.put("ISO", passCountMap.getOrDefault("ISO", 0) + 1);
									DGLQ_com_Status = "Pass";

								} else {
									// failCountMap.put("ISO", failCountMap.getOrDefault("ISO", 0) + 1);
									DGLQ_com_Status = "Fail";
								}

								Html_DGLQ = "<th>" + "DGLQ" + "</th><th>" + map1.get("DGLQ") + "</th><th>"
										+ map3.get("DGLQ") + "</th><th>" + DGLQ_com_Status + "</th>";

//									Extent_table_pass2(test, stowagevalue1, Html_ISO, Html_Weight, Html_POL, Html_POD,
//											Html_Mty, Html_IsSpl, Html_IsCOD, Html_Rfr, Html_OOG, Html_DG, Html_mulHaz,
//											Html_DGclass, Html_UNNO, Html_OOH, Html_OLF, Html_OLA, Html_OWP, Html_OWS,
//											Html_BookingNo, Html_Variant, Html_FlashPoint, Html_DGLQ);

//									compareAndIncrementCount("DG", map1.get("DG"), map3.get("DG"), stowagevalue1);
//									compareAndIncrementCount("OOG", map1.get("OOG"), map3.get("OOG"), stowagevalue1);
//									compareAndIncrementCount("Rfr", map1.get("Rfr"), map3.get("Rfr"), stowagevalue1);
//									compareAndIncrementCount("IsSpl", map1.get("IsSpl"), map3.get("IsSpl"), stowagevalue1);
//									compareAndIncrementCount("Is COD", map1.get("Is COD"), map3.get("Is COD"),
//											stowagevalue1);

								System.out.println(
										"|**********************************************************************|");

								if (map1.get("DG").contains("Yes")) {

									if ((map1.get("DG") == null && map3.get("DG") == null)
											|| (map1.get("DG") != null && map1.get("DG").equals(map3.get("DG")))) {
//											passCountMap1.put("DG", passCountMap1.getOrDefault("DG", 0) + 1);

									} else {
										List<String> stowageValues = failStowageValues1.computeIfAbsent("DG",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);
										failCountMap1.put("DG", failCountMap1.getOrDefault("DG", 0) + 1);
										DG_stow.add(stowagevalue1);
										DG_Excepted.add(map1.get("DG"));
										DG_Actual.add(map3.get("DG"));

										System.out.println("DG fail stow : " + stowagevalue1);

									}

									if ((map1.get("DG class") == null && map3.get("DG class") == null)
											|| (map1.get("DG class") != null
													&& map1.get("DG class").equals(map3.get("DG class")))) {
//											passCountMap1.put("DG class", passCountMap1.getOrDefault("DG class", 0) + 1);

									} else {

										List<String> stowageValues = failStowageValues1.computeIfAbsent("DG class",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);
										failCountMap1.put("DG class", failCountMap1.getOrDefault("DG class", 0) + 1);

										DGclass_stow.add(stowagevalue1);
										DGclass_Excepted.add(map1.get("DG class"));
										DGclass_Actual.add(map3.get("DG class"));

									}

									if ((map1.get("mul Haz") == null && map3.get("mul Haz") == null)
											|| (map1.get("mul Haz") != null
													&& map1.get("mul Haz").equals(map3.get("mul Haz")))) {
//											passCountMap1.put("mul Haz", passCountMap1.getOrDefault("mul Haz", 0) + 1);

									} else {
										List<String> stowageValues = failStowageValues1.computeIfAbsent("mul Haz",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);
										failCountMap1.put("mul Haz", failCountMap1.getOrDefault("mul Haz", 0) + 1);
										mulHaz_stow.add(stowagevalue1);
										mulHaz_Excepted.add(map1.get("mul Haz"));
										mulHaz_Actual.add(map3.get("mul Haz"));
									}

									if ((map1.get("UNNO") == null && map3.get("UNNO") == null)
											|| (map1.get("UNNO") != null
													&& map1.get("UNNO").equals(map3.get("UNNO")))) {
//											passCountMap1.put("UNNO", passCountMap1.getOrDefault("UNNO", 0) + 1);

									} else {
										failCountMap1.put("UNNO", failCountMap1.getOrDefault("UNNO", 0) + 1);
										List<String> stowageValues = failStowageValues1.computeIfAbsent("UNNO",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);
										UNNO_stow.add(stowagevalue1);
										UNNO_Excepted.add(map1.get("UNNO"));
										UNNO_Actual.add(map3.get("UNNO"));
									}

									if ((map1.get("Variant") == null && map3.get("Variant") == null)
											|| (map1.get("Variant") != null
													&& map1.get("Variant").equals(map3.get("Variant")))) {
//											passCountMap1.put("Variant", passCountMap1.getOrDefault("Variant", 0) + 1);

									} else {
										failCountMap1.put("Variant", failCountMap1.getOrDefault("Variant", 0) + 1);
										List<String> stowageValues = failStowageValues1.computeIfAbsent("Variant",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);
										Variant_stow.add(stowagevalue1);
										Variant_Excepted.add(map1.get("Variant"));
										Variant_Actual.add(map3.get("Variant"));
									}

									if ((map1.get("FlashPoint") == null && map3.get("FlashPoint") == null)
											|| (map1.get("FlashPoint") != null
													&& map1.get("FlashPoint").equals(map3.get("FlashPoint")))) {
//											passCountMap1.put("FlashPoint", passCountMap1.getOrDefault("FlashPoint", 0) + 1);

									} else {
										List<String> stowageValues = failStowageValues1.computeIfAbsent("FlashPoint",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);
										failCountMap1.put("FlashPoint",
												failCountMap1.getOrDefault("FlashPoint", 0) + 1);
										FlashPoint_stow.add(stowagevalue1);
										FlashPoint_Excepted.add(map1.get("FlashPoint"));
										FlashPoint_Actual.add(map3.get("FlashPoint"));
									}

									if ((map1.get("DGLQ") == null && map3.get("DGLQ") == null)
											|| (map1.get("DGLQ") != null
													&& map1.get("DGLQ").equals(map3.get("DGLQ")))) {
//											passCountMap1.put("DGLQ", passCountMap1.getOrDefault("DGLQ", 0) + 1);

									} else {
										failCountMap1.put("DGLQ", failCountMap1.getOrDefault("DGLQ", 0) + 1);

										List<String> stowageValues = failStowageValues1.computeIfAbsent("DGLQ",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);
										DGLQ_stow.add(stowagevalue1);
										DGLQ_Excepted.add(map1.get("DGLQ"));
										DGLQ_Actual.add(map3.get("DGLQ"));
									}
								}

								if (map1.get("OOG").contains("Yes")) {

									if ((map1.get("OOG") == null && map3.get("OOG") == null)
											|| (map1.get("OOG") != null && map1.get("OOG").equals(map3.get("OOG")))) {
//											passCountMap1.put("OOG", passCountMap1.getOrDefault("OOG", 0) + 1);

									} else {
										List<String> stowageValues = failStowageValues1.computeIfAbsent("OOG",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);

										failCountMap1.put("OOG", failCountMap1.getOrDefault("OOG", 0) + 1);
										OOG_stow.add(stowagevalue1);
										OOG_Excepted.add(map1.get("OOG"));
										OOG_Actual.add(map3.get("OOG"));
									}

									if ((map1.get("OOH (m)") == null && map3.get("OOH (m)") == null)
											|| (map1.get("OOH (m)") != null
													&& map1.get("OOH (m)").equals(map3.get("OOH (m)")))) {

//											passCountMap1.put("OOH (m)", passCountMap1.getOrDefault("OOH (m)", 0) + 1);

									} else {
										List<String> stowageValues = failStowageValues1.computeIfAbsent("OOH (m)",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);

										failCountMap1.put("OOH (m)", failCountMap1.getOrDefault("OOH (m)", 0) + 1);
										OOH_stow.add(stowagevalue1);
										OOH_Excepted.add(map1.get("OOH (m)"));
										OOH_Actual.add(map3.get("OOH (m)"));
									}

									if ((map1.get("OLF (m)") == null && map3.get("OLF (m)") == null)
											|| (map1.get("OLF (m)") != null
													&& map1.get("OLF (m)").equals(map3.get("OLF (m)")))) {
//											passCountMap1.put("OLF (m)", passCountMap1.getOrDefault("OLF (m)", 0) + 1);

									} else {
										List<String> stowageValues = failStowageValues1.computeIfAbsent("OLF (m)",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);

										failCountMap1.put("OLF (m)", failCountMap1.getOrDefault("OLF (m)", 0) + 1);
										OLF_stow.add(stowagevalue1);
										OLF_Excepted.add(map1.get("OLF (m)"));
										OLF_Actual.add(map3.get("OLF (m)"));
									}

//										if ((map1.get("OLF (m)") == null && map3.get("OLF (m)") == null) 
//										        || (map1.get("OLF (m)") != null && map3.get("OLF (m)") != null 
//										                && map1.get("OLF (m)").equals(map3.get("OLF (m)"))) 
//										        || (map1.get("OLF (m)") != null && map3.get("OLF (m)") == null 
//										                && (map1.get("OLF (m)").equals("o") || map1.get("OLF (m)").equals(null))) // map1 is not null, map3 is null, and map1 is "o" or null
//										        || (map1.get("OLF (m)") == null && map3.get("OLF (m)") != null 
//										                && ("0".equals(map3.get("OLF (m)")) || map3.get("OLF (m)").isEmpty()))) { // map1 is null, map3 is not null, and map3 is "0" or empty
//										    // passCountMap1.put("OLF (m)", passCountMap1.getOrDefault("OLF (m)", 0) + 1);
//										} else {
//										    List<String> stowageValues = failStowageValues1.computeIfAbsent("OLF (m)", a -> new ArrayList<>());
//										    stowageValues.add(stowagevalue1);
//
//										    failCountMap1.put("OLF (m)", failCountMap1.getOrDefault("OLF (m)", 0) + 1);
//										    OLF_stow.add(stowagevalue1);
//										    OLF_Excepted.add(map1.get("OLF (m)"));
//										    OLF_Actual.add(map3.get("OLF (m)"));
//										}

									if ((map1.get("OLA (m)") == null && map3.get("OLA (m)") == null)
											|| (map1.get("OLA (m)") != null
													&& map1.get("OLA (m)").equals(map3.get("OLA (m)")))) {
//											passCountMap1.put("OLA (m)", passCountMap1.getOrDefault("OLA (m)", 0) + 1);

									} else {
										List<String> stowageValues = failStowageValues1.computeIfAbsent("OLA (m)",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);

										failCountMap1.put("OLA (m)", failCountMap1.getOrDefault("OLA (m)", 0) + 1);
										OLA_stow.add(stowagevalue1);
										OLA_Excepted.add(map1.get("OLA (m)"));
										OLA_Actual.add(map3.get("OLA (m)"));
									}

									if ((map1.get("OWP (m)") == null && map3.get("OWP (m)") == null)
											|| (map1.get("OWP (m)") != null
													&& map1.get("OWP (m)").equals(map3.get("OWP (m)")))) {
//											passCountMap1.put("OWP (m)", passCountMap1.getOrDefault("OWP (m)", 0) + 1);

									} else {
										List<String> stowageValues = failStowageValues1.computeIfAbsent("OWP (m)",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);

										failCountMap1.put("OWP (m)", failCountMap1.getOrDefault("OWP (m)", 0) + 1);
										OWP_stow.add(stowagevalue1);
										OWP_Excepted.add(map1.get("OWP (m)"));
										OWP_Actual.add(map3.get("OWP (m)"));
									}

									if ((map1.get("OWS (m)") == null && map3.get("OWS (m)") == null)
											|| (map1.get("OWS (m)") != null
													&& map1.get("OWS (m)").equals(map3.get("OWS (m)")))) {
//											passCountMap1.put("OWS (m)", passCountMap1.getOrDefault("OWS (m)", 0) + 1);

									} else {
										List<String> stowageValues = failStowageValues1.computeIfAbsent("OWS (m)",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);

										failCountMap1.put("OWS (m)", failCountMap1.getOrDefault("OWS (m)", 0) + 1);
										OWS_stow.add(stowagevalue1);
										OWS_Excepted.add(map1.get("OWS (m)"));
										OWS_Actual.add(map3.get("OWS (m)"));
									}
								}

								if (map1.get("Is COD").contains("Yes")) {

									if ((map1.get("Is COD") == null && map3.get("Is COD") == null)
											|| (map1.get("Is COD") != null
													&& map1.get("Is COD").equals(map3.get("Is COD")))) {
//											passCountMap1.put("Is COD", passCountMap1.getOrDefault("Is COD", 0) + 1);

									} else {
										List<String> stowageValues = failStowageValues1.computeIfAbsent("Is COD",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);

										failCountMap1.put("IS COD", failCountMap1.getOrDefault("Is COD", 0) + 1);
										IsCOD_stow.add(stowagevalue1);
										IsCOD_Excepted.add(map1.get("Is COD"));
										IsCOD_Actual.add(map3.get("Is COD"));
									}

									if ((map1.get("COD") == null && map3.get("COD") == null)
											|| (map1.get("COD") != null && map1.get("COD").equals(map3.get("COD")))) {
//											passCountMap1.put("COD", passCountMap1.getOrDefault("COD", 0) + 1);

									} else {
										List<String> stowageValues = failStowageValues1.computeIfAbsent("COD",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);

										failCountMap1.put("COD", failCountMap1.getOrDefault("COD", 0) + 1);
										COD_stow.add(stowagevalue1);
										COD_Excepted.add(map1.get("COD"));
										COD_Actual.add(map3.get("COD"));
									}

								}

								if (map1.get("Rfr").contains("Yes")) {

									if ((map1.get("Rfr") == null && map3.get("Rfr") == null)
											|| (map1.get("Rfr") != null && map1.get("Rfr").equals(map3.get("Rfr")))) {
//											passCountMap1.put("Rfr", passCountMap1.getOrDefault("Rfr", 0) + 1);

									} else {
										List<String> stowageValues = failStowageValues1.computeIfAbsent("Rfr",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);

										failCountMap1.put("Rfr", failCountMap1.getOrDefault("Rfr", 0) + 1);

										Rfr_stow.add(stowagevalue1);
										Rfr_Excepted.add(map1.get("Rfr"));
										Rfr_Actual.add(map3.get("Rfr"));

									}
								}

								if (map1.get("IsSpl").contains("IsSpl")) {

									if ((map1.get("IsSpl") == null && map3.get("IsSpl") == null)
											|| (map1.get("IsSpl") != null
													&& map1.get("IsSpl").equals(map3.get("IsSpl")))) {
//												passCountMap1.put("IsSpl", passCountMap1.getOrDefault("IsSpl", 0) + 1);

									} else {
										List<String> stowageValues = failStowageValues1.computeIfAbsent("IsSpl",
												a -> new ArrayList<>());
										stowageValues.add(stowagevalue1);

										failCountMap1.put("IsSpl", failCountMap1.getOrDefault("IsSpl", 0) + 1);
										IsSpl_stow.add(stowagevalue1);
										IsSpl_Excepted.add(map1.get("IsSpl"));
										IsSpl_Actual.add(map3.get("IsSpl"));

									}

								}

							} catch (Exception e) {
								e.printStackTrace();
							}

							break;

						} else {
							flag4 = "is not presented";
						}

					}

					if (flag4.equalsIgnoreCase("is not presented")) {
						System.out.println("Stow value is not present " + stowagevalue1);

						// Create a list to store the stowage values
						List<String> stowagevalue2 = new ArrayList<>();
						stowagevalue2.add(stowagevalue1); // Add stowagevalue1 to the list

						Extent_fail1(test, "Stow value is not present " + stowagevalue2);
						break;
					}

				}

				/*
				 * if (flag1 == true || j==row ||flag4.equalsIgnoreCase("is not presented")
				 * ||flag4.equalsIgnoreCase("is presented")) { flag3=true; break; }
				 */

			}

			if (flag4.equalsIgnoreCase("is not presented") || flag4.equalsIgnoreCase("is presented")) {
				break;
			}

		}

		System.out.println("");

	}

	public static void ActionTest(ExtentTest extentTest,ExtentTest extentTest2) {
		test = extentTest;
		test2=extentTest2;
	}

	public static void compareAndIncrementCount(String category, String expected, String actual, String stowageValue) {

		try {
			if ("Yes".equals(expected) && "Yes".equals(actual)) {

			} else if ("Yes".equals(expected) && "No".equals(actual)) {
				failCountMap1.put(category, failCountMap1.getOrDefault(category, 0) + 1);
				System.out.println("Not matched : " + " Stowage no : " + stowageValue + " ]" + " --- " + "Expected "
						+ category + " value: " + expected + " and Actual " + category + " value :" + actual);
				Extent_fail1(test, "Not matched : " + " Stowage no : " + stowageValue + " ]" + " --- " + "Expected "
						+ category + " value: " + expected + " and Actual " + category + " value :" + actual);

				List<String> stowageValues2 = failStowageValues.computeIfAbsent(category, k -> new ArrayList<>());
				stowageValues2.add(stowageValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void compareAndIncrementCount1(String category, String expected, String actual, String stowageValue) {
		try {
			if ((expected == null && actual == null) || (expected != null && expected.equals(actual))
					|| (expected != null && "o".equals(actual)) || ("0".equals(expected) && actual.isEmpty())) {
				// Check if actual is "o" when expected is null
				// Check if expected is "0" and actual is empty

				passCountMap.put(category, passCountMap.getOrDefault(category, 0) + 1);

				System.out.print(" Matched: --- " + " Stowage no: " + stowageValue + " ]" + " Expected " + category
						+ " value: " + expected + " and Actual " + category + " value: " + actual);
				Extent_pass1(test, " Matched: --- " + " Stowage no: " + stowageValue + " ]" + " Expected " + category
						+ " value: " + expected + " and Actual " + category + " value: " + actual);
				Map<String, String> valuesMap = new HashMap<>();
				valuesMap.put("expected", expected);
				valuesMap.put("actual", actual);

			} else {
				failCountMap.put(category, failCountMap.getOrDefault(category, 0) + 1);
				System.out.print(" Not matched: --- " + " Stowage no: " + stowageValue + " ]" + " Expected " + category
						+ " value: " + expected + " and Actual " + category + " value :" + actual);

				Map<String, String> valuesMap = new HashMap<>();
				valuesMap.put("expected", expected);
				valuesMap.put("actual", actual);
				Extent_fail1(test, " Not matched: --- " + " Stowage no: " + stowageValue + " ]" + " Expected "
						+ category + " value: " + expected + " and Actual " + category + " value :" + actual);

				List<String> stowageValues = failStowageValues.computeIfAbsent(category, k -> new ArrayList<>());
				stowageValues.add(stowageValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printCounts() {
		int totalFailCount = 0;

		System.out.println("Category Counts:");
		for (String category : passCountMap.keySet()) {
			int passCount = passCountMap.getOrDefault(category, 0);
			int failCount = failCountMap.getOrDefault(category, 0);

			System.out.println(category + " ---- " + " Pass Count: " + passCount + " || " + "Fail Count: " + failCount);
			Extent_pass1(test, category + " ---- " + " Pass Count: " + passCount + " || " + "Fail Count: " + failCount);
			totalFailCount += failCount;
//		System.out.println(category +"Group Fail Count: " + failCount1);

		}

		System.out.println("/**********************************************************************|");
		System.out.println("Total Fail Count: " + totalFailCount);
		Extent_fail1(test, "Total Fail Count: " + totalFailCount);

		// Print failed stowage values
		for (Map.Entry<String, List<String>> entry : failStowageValues.entrySet()) {
			String category = entry.getKey();
			List<String> stowageValues = entry.getValue();

			System.out.println(category + " - Failed Stowage Values: " + stowageValues);
			Extent_fail2(test, category + " - Failed Stowage Values: " + stowageValues);

		}
	}

	public static void printCounts1() {
		System.out.println("/**********************************************************************|");

		int dgGroupCount = 0;
		int oogGroupCount = 0;
		int reeferGroupCount = 0;
		int specialGroupCount = 0;
		int codGroupCount = 0;

		for (Map.Entry<String, Integer> entry : failCountMap1.entrySet()) {

			String category2 = entry.getKey();
			int count = entry.getValue();
			int failCount = failCountMap1.getOrDefault(category2, 0);

			System.out.println(category2 + " ---- " + "|| " + "Group Fail Count for each coulmn: " + failCount);
			Extent_fail1(test, category2 + " ---- " + "|| " + "Group Fail Count for each column: " + failCount);

			if (category2.equals("DG") || category2.equals("DG class") || category2.equals("mul Haz")
					|| category2.equals("UNNO") || category2.equals("Variant") || category2.equals("FlashPoint")
					|| category2.equals("DGLQ")) {
				dgGroupCount += count;
			} else if (category2.equals("OOG") || category2.equals("OOH (m)") || category2.equals("OLF (m)")
					|| category2.equals("OLA (m)") || category2.equals("OWP (m)") || category2.equals("OWS (m)")) {
				oogGroupCount += count;
			} else if (category2.equals("Rfr")) {
				reeferGroupCount += count;
			} else if (category2.equals("IsSpl")) {
				specialGroupCount += count;
			} else if (category2.equals("Is COD") || category2.equals("COD")) {
				codGroupCount += count;
			}

		}

		Extent_fail2(test, "DG Group fail count: " + dgGroupCount);
		Extent_fail2(test, "OOG Group fail count: " + oogGroupCount);
		Extent_fail2(test, "Reefer Group fail count: " + reeferGroupCount);
		Extent_fail2(test, "Special Group fail count: " + specialGroupCount);
		Extent_fail2(test, "COD Group fail count: " + codGroupCount);

		System.out.println("DG Group fail count: " + dgGroupCount);
		System.out.println("OOG Group fail count: " + oogGroupCount);
		System.out.println("Reefer Group fail count: " + reeferGroupCount);
		System.out.println("Special Group fail count: " + specialGroupCount);
		System.out.println("COD Group fail count: " + codGroupCount);

		for (Map.Entry<String, List<String>> entry1 : failStowageValues1.entrySet()) {
			String category1 = entry1.getKey();
			List<String> stowageValues = entry1.getValue();

			System.out.println(category1 + " - Failed group Stowage Values: " + stowageValues);
			Extent_fail2(test, category1 + " - Failed group Stowage Values: " + stowageValues);

		}

	}

	public void Fillocomparison(String Master, String Test, ExtentTest test, ExtentTest testDetail, String detailreportPath) {
		try {

			System.setProperty("ROW", "11");
			Fillo fillo = new Fillo();

			Connection connection1 = fillo.getConnection(Master);
			Connection connection2 = fillo.getConnection(Test);

			String query1 = "Select * from `CARGO LIST`";
			Recordset recordset1 = connection1.executeQuery(query1);

			String query2 = "Select * from `CARGO LIST`";
			Recordset recordset2 = connection2.executeQuery(query2);

			List<String> columnsToCompare = Arrays.asList("ISO", "Weight(t)", "POL", "POD", "Mty", "IsSpl", "Is COD",
					"Rfr", "OOG", "DG", "mul Haz", "DG class", "UNNO", "OOH (m)", "OLF (m)", "OLA (m)", "OWP (m)",
					"OWS (m)", "Booking No", "Variant", "FlashPoint", "DGLQ");

			Map<String, Set<String>> failedStowageMap = new HashMap<>();
			Map<String, Integer> failureCountByColumn = new HashMap<>();
			Set<String> uniqueFailedStowageNumbers = new HashSet<>();
			Set<String> stowageNotInTest = new HashSet<>();
			Map<String, Integer> failGroupCountMapnew = new HashMap<>();

			String passReportpath=detailreportPath+"_Pass.html";
			String failReportPath=detailreportPath+"_Fail.html";
			Extent_passLink(test,"Detailed Pass Result",passReportpath);
			Extent_passLink(test,"Detailed Failed Result",failReportPath);

			while (recordset1.next()) {
				String stowageSheet1 = recordset1.getField("Stowage");

				recordset2.moveFirst();

				boolean matchFound = false;
				while (recordset2.next()) {
					String stowageSheet2 = recordset2.getField("Stowage");

					if (stowageSheet1 != null
							&& stowageSheet1.trim().equals(stowageSheet2 != null ? stowageSheet2.trim() : "")) {
						matchFound = true;

						for (String columnName : columnsToCompare) {
							String valueSheet1Column = recordset1.getField(columnName);
							String valueSheet2Column = recordset2.getField(columnName);

							if ((valueSheet1Column == null && valueSheet2Column == null)
									|| ((stowageSheet1 == null || stowageSheet1.trim().isEmpty())
											&& "0".equals(stowageSheet2))
									|| ((stowageSheet2 == null || stowageSheet2.trim().isEmpty())
											&& "0".equals(stowageSheet1))
									|| (valueSheet1Column != null && valueSheet1Column.equals(valueSheet2Column))) {

								Extent_pass1(test2,
										"Values are matched for " + "Stowage number : " + stowageSheet1 + "||"
												+ " Column name is :" + columnName + "||" + " Master value: "
												+ valueSheet1Column + "||" + " Test value: " + valueSheet2Column);
							} else {
								uniqueFailedStowageNumbers.add(stowageSheet1);
								failedStowageMap.computeIfAbsent(columnName, k -> new HashSet<>()).add(stowageSheet1);
								failureCountByColumn.put(columnName,
										failureCountByColumn.getOrDefault(columnName, 0) + 1);
								Extent_fail2(testDetail,
										"Values are not matched for " + "Stowage number : " + stowageSheet1 + "||"
												+ "Column name is :" + columnName + "||" + " Master value: "
												+ valueSheet1Column + "||" + " Test value: " + valueSheet2Column);
							}
						}

						if (recordset1.getField("DG") != null && recordset1.getField("DG").contains("Yes")) {
							if (recordset2.getField("DG") == null
									|| !recordset1.getField("DG").equals(recordset2.getField("DG"))) {
								failGroupCountMapnew.put("DG", failGroupCountMapnew.getOrDefault("DG", 0) + 1);
								DG_stownumber.add(stowageSheet1);
								DG_Group_Excepted.add(recordset1.getField("DG"));
								DG_Group_Actual.add(recordset2.getField("DG"));
							}
						}

						if ("Yes".equals(recordset1.getField("DG")) && "Yes".equals(recordset2.getField("DG"))) {
							if (recordset2.getField("DG class") == null
									|| !recordset1.getField("DG class").equals(recordset2.getField("DG class"))) {
								failGroupCountMapnew.put("DG class",
										failGroupCountMapnew.getOrDefault("DG class", 0) + 1);
								DG_Class_stownumber.add(stowageSheet1);
								DG_Class_Group_Excepted.add(recordset1.getField("DG class"));
								DG_Class_Group_Actual.add(recordset2.getField("DG class"));
							}

							if (recordset2.getField("mul Haz") == null
									|| !recordset1.getField("mul Haz").equals(recordset2.getField("mul Haz"))) {
								failGroupCountMapnew.put("mul Haz",
										failGroupCountMapnew.getOrDefault("mul Haz", 0) + 1);
								mul_Haz_stownumber.add(stowageSheet1);
								mul_Haz_Group_Excepted.add(recordset1.getField("mul Haz"));
								mul_Haz_Group_Actual.add(recordset2.getField("mul Haz"));
							}

							if (recordset2.getField("UNNO") == null
									|| !recordset1.getField("UNNO").equals(recordset2.getField("UNNO"))) {
								failGroupCountMapnew.put("UNNO", failGroupCountMapnew.getOrDefault("UNNO", 0) + 1);
								UNNO_stownumber.add(stowageSheet1);
								UNNO_Group_Excepted.add(recordset1.getField("UNNO"));
								UNNO_Group_Actual.add(recordset2.getField("UNNO"));
							}

							if (recordset2.getField("Variant") == null
									|| !recordset1.getField("Variant").equals(recordset2.getField("Variant"))) {
								failGroupCountMapnew.put("Variant",
										failGroupCountMapnew.getOrDefault("Variant", 0) + 1);
								Variant_stownumber.add(stowageSheet1);
								Variant_Group_Excepted.add(recordset1.getField("Variant"));
								Variant_Group_Actual.add(recordset2.getField("Variant"));
							}

							if (recordset2.getField("FlashPoint") == null
									|| !recordset1.getField("FlashPoint").equals(recordset2.getField("FlashPoint"))) {
								failGroupCountMapnew.put("FlashPoint",
										failGroupCountMapnew.getOrDefault("FlashPoint", 0) + 1);
								FlashPoint_stownumber.add(stowageSheet1);
								FlashPoint_Group_Excepted.add(recordset1.getField("FlashPoint"));
								FlashPoint_Group_Actual.add(recordset2.getField("FlashPoint"));
							}

							if (recordset2.getField("DGLQ") == null
									|| !recordset1.getField("DGLQ").equals(recordset2.getField("DGLQ"))) {
								failGroupCountMapnew.put("DGLQ", failGroupCountMapnew.getOrDefault("DGLQ", 0) + 1);
								DGLQ_stownumber.add(stowageSheet1);
								DGLQ_Group_Excepted.add(recordset1.getField("DGLQ"));
								DGLQ_Group_Actual.add(recordset2.getField("DGLQ"));
							}
						}

						if (recordset1.getField("OOG") != null && recordset1.getField("OOG").contains("Yes")) {
							if (recordset2.getField("OOG") == null
									|| !recordset1.getField("OOG").equals(recordset2.getField("OOG"))) {
								failGroupCountMapnew.put("OOG", failGroupCountMapnew.getOrDefault("OOG", 0) + 1);
								OOG_stownumber.add(stowageSheet1);
								OOG_Group_Excepted.add(recordset1.getField("OOG"));
								OOG_Group_Actual.add(recordset2.getField("OOG"));
							}
						}

						if ("Yes".equals(recordset1.getField("OOG")) && "Yes".equals(recordset2.getField("OOG"))) {
							if (recordset2.getField("OOH (m)") == null
									|| !recordset1.getField("OOH (m)").equals(recordset2.getField("OOH (m)"))) {
								failGroupCountMapnew.put("OOH (m)",
										failGroupCountMapnew.getOrDefault("OOH (m)", 0) + 1);
								OOH_stownumber.add(stowageSheet1);
								OOH_Group_Excepted.add(recordset1.getField("OOH (m)"));
								OOH_Group_Actual.add(recordset2.getField("OOH (m)"));
							}

							if (recordset2.getField("OLA (m)") == null
									|| !recordset1.getField("OLA (m)").equals(recordset2.getField("OLA (m)"))) {
								failGroupCountMapnew.put("OLA (m)",
										failGroupCountMapnew.getOrDefault("OLA (m)", 0) + 1);
								OLA_stownumber.add(stowageSheet1);
								OLA_Group_Excepted.add(recordset1.getField("OLA (m)"));
								OLA_Group_Actual.add(recordset2.getField("OLA (m)"));
							}

							if (recordset2.getField("OLF (m)") == null
									|| !recordset1.getField("OLF (m)").equals(recordset2.getField("OLF (m)"))) {
								failGroupCountMapnew.put("OLF (m)",
										failGroupCountMapnew.getOrDefault("OLF (m)", 0) + 1);
								OLF_stownumber.add(stowageSheet1);
								OLF_Group_Excepted.add(recordset1.getField("OLF (m)"));
								OLF_Group_Actual.add(recordset2.getField("OLF (m)"));
							}

							if (recordset2.getField("OWP (m)") == null
									|| !recordset1.getField("OWP (m)").equals(recordset2.getField("OWP (m)"))) {
								failGroupCountMapnew.put("OWP (m)",
										failGroupCountMapnew.getOrDefault("OWP (m)", 0) + 1);
								OWP_stownumber.add(stowageSheet1);
								OWP_Group_Excepted.add(recordset1.getField("OWP (m)"));
								OWP_Group_Actual.add(recordset2.getField("OWP (m)"));
							}

							if (recordset2.getField("OWS (m)") == null
									|| !recordset1.getField("OWS (m)").equals(recordset2.getField("OWS (m)"))) {
								failGroupCountMapnew.put("OWS (m)",
										failGroupCountMapnew.getOrDefault("OWS (m)", 0) + 1);
								OWS_stownumber.add(stowageSheet1);
								OWS_Group_Excepted.add(recordset1.getField("OWS (m)"));
								OWS_Group_Actual.add(recordset2.getField("OWS (m)"));
							}

						}

						if (recordset1.getField("Is COD") != null && recordset1.getField("Is COD").contains("Yes")) {
							if (recordset2.getField("Is COD") == null
									|| !recordset1.getField("Is COD").equals(recordset2.getField("Is COD"))) {
								failGroupCountMapnew.put("Is COD", failGroupCountMapnew.getOrDefault("Is COD", 0) + 1);
								Is_COD_stownumber.add(stowageSheet1);
								Is_COD_Group_Excepted.add(recordset1.getField("Is COD"));
								Is_COD_Group_Actual.add(recordset2.getField("Is COD"));
							}
						}

						if ("Yes".equals(recordset1.getField("Is COD"))
								&& "Yes".equals(recordset2.getField("Is COD"))) {

							if (recordset2.getField("COD") == null
									|| !recordset1.getField("COD").equals(recordset2.getField("COD"))) {
								failGroupCountMapnew.put("COD", failGroupCountMapnew.getOrDefault("COD", 0) + 1);
								COD_stownumber.add(stowageSheet1);
								COD_Group_Excepted.add(recordset1.getField("COD"));
								COD_Group_Actual.add(recordset2.getField("COD"));
							}

						}

						if (recordset1.getField("IsSpl") != null && recordset1.getField("IsSpl").contains("Yes")) {
							if (recordset2.getField("IsSpl") == null
									|| !recordset1.getField("IsSpl").equals(recordset2.getField("IsSpl"))) {
								failGroupCountMapnew.put("IsSpl", failGroupCountMapnew.getOrDefault("IsSpl", 0) + 1);
								IsSpl_stownumber.add(stowageSheet1);
								IsSpl_Group_Excepted.add(recordset1.getField("IsSpl"));
								IsSpl_Group_Actual.add(recordset2.getField("IsSpl"));
							}
						}

						if (recordset1.getField("Rfr") != null && recordset1.getField("Rfr").contains("Yes")) {
							if (recordset2.getField("Rfr") == null
									|| !recordset1.getField("Rfr").equals(recordset2.getField("Rfr"))) {
								failGroupCountMapnew.put("Rfr", failGroupCountMapnew.getOrDefault("Rfr", 0) + 1);
								Rfr_stownumber.add(stowageSheet1);
								Rfr_Group_Excepted.add(recordset1.getField("Rfr"));
								Rfr_Group_Actual.add(recordset2.getField("Rfr"));
							}
						}

					}

				}

			}

			// Print Extent_group_table after the loop is completed
			if (!DG_stownumber.isEmpty()) {
				for (int i = 0; i < DG_stownumber.size(); i++) {
					Extent_group_table(test, DG_stownumber.get(i), "DG Group", "DG", DG_Group_Excepted.get(i),
							DG_Group_Actual.get(i));
					DGGroupFailuresStowageNumbers.add(DG_stownumber.get(i));
				}
			}

			if (!DG_Class_stownumber.isEmpty()) {
				for (int i = 0; i < DG_Class_stownumber.size(); i++) {
					Extent_group_table(test, DG_Class_stownumber.get(i), "DG Group", "DG Class",
							DG_Class_Group_Excepted.get(i), DG_Class_Group_Actual.get(i));
					DGGroupFailuresStowageNumbers.add(DG_Class_stownumber.get(i));
				}
			}

			if (!mul_Haz_stownumber.isEmpty()) {
				for (int i = 0; i < mul_Haz_stownumber.size(); i++) {
					Extent_group_table(test, mul_Haz_stownumber.get(i), "DG Group", "mul Haz",
							mul_Haz_Group_Excepted.get(i), mul_Haz_Group_Actual.get(i));
					DGGroupFailuresStowageNumbers.add(mul_Haz_stownumber.get(i));
				}
			}

			if (!UNNO_stownumber.isEmpty()) {
				for (int i = 0; i < UNNO_stownumber.size(); i++) {
					Extent_group_table(test, UNNO_stownumber.get(i), "DG Group", "UNNO", UNNO_Group_Excepted.get(i),
							UNNO_Group_Actual.get(i));
					DGGroupFailuresStowageNumbers.add(UNNO_stownumber.get(i));
				}
			}

			if (!Variant_stownumber.isEmpty()) {
				for (int i = 0; i < Variant_stownumber.size(); i++) {
					Extent_group_table(test, Variant_stownumber.get(i), "DG Group", "Variant",
							Variant_Group_Excepted.get(i), Variant_Group_Actual.get(i));
					DGGroupFailuresStowageNumbers.add(Variant_stownumber.get(i));
				}
			}

			if (!FlashPoint_stownumber.isEmpty()) {
				for (int i = 0; i < FlashPoint_stownumber.size(); i++) {
					Extent_group_table(test, FlashPoint_stownumber.get(i), "DG Group", "FlashPoint",
							FlashPoint_Group_Excepted.get(i), FlashPoint_Group_Actual.get(i));
					DGGroupFailuresStowageNumbers.add(FlashPoint_stownumber.get(i));
				}
			}

			if (!DGLQ_stownumber.isEmpty()) {
				for (int i = 0; i < DGLQ_stownumber.size(); i++) {
					Extent_group_table(test, DGLQ_stownumber.get(i), "DG Group", "DGLQ", DGLQ_Group_Excepted.get(i),
							DGLQ_Group_Actual.get(i));
					DGGroupFailuresStowageNumbers.add(DGLQ_stownumber.get(i));
				}
			}

			if (!OOG_stownumber.isEmpty()) {
				for (int i = 0; i < OOG_stownumber.size(); i++) {
					Extent_group_table(test, OOG_stownumber.get(i), "OOG Group", "OOG", OOG_Group_Excepted.get(i),
							OOG_Group_Actual.get(i));
					OOGGroupFailuresStowageNumbers.add(OOG_stownumber.get(i));
				}
			}

			if (!OOH_stownumber.isEmpty()) {
				for (int i = 0; i < OOH_stownumber.size(); i++) {
					Extent_group_table(test, OOH_stownumber.get(i), "OOG Group", "OOH (m)", OOH_Group_Excepted.get(i),
							OOH_Group_Actual.get(i));
					OOGGroupFailuresStowageNumbers.add(OOH_stownumber.get(i));
				}
			}

			if (!OLA_stownumber.isEmpty()) {
				for (int i = 0; i < OLA_stownumber.size(); i++) {
					Extent_group_table(test, OLA_stownumber.get(i), "OOG Group", "OLA (m)", OLA_Group_Excepted.get(i),
							OLA_Group_Actual.get(i));
					OOGGroupFailuresStowageNumbers.add(OLA_stownumber.get(i));
				}
			}

			if (!OWS_stownumber.isEmpty()) {
				for (int i = 0; i < OWS_stownumber.size(); i++) {
					Extent_group_table(test, OWS_stownumber.get(i), "OOG Group", "OWS (m)", OWS_Group_Excepted.get(i),
							OWS_Group_Actual.get(i));
					OOGGroupFailuresStowageNumbers.add(OWS_stownumber.get(i));
				}
			}

			if (!OWP_stownumber.isEmpty()) {
				for (int i = 0; i < OWP_stownumber.size(); i++) {
					Extent_group_table(test, OWP_stownumber.get(i), "OOG Group", "OWP (m)", OWP_Group_Excepted.get(i),
							OWP_Group_Actual.get(i));
					OOGGroupFailuresStowageNumbers.add(OWP_stownumber.get(i));
				}
			}

			if (!Is_COD_stownumber.isEmpty()) {
				for (int i = 0; i < Is_COD_stownumber.size(); i++) {
					Extent_group_table(test, Is_COD_stownumber.get(i), "COD Group", "Is COD",
							Is_COD_Group_Excepted.get(i), Is_COD_Group_Actual.get(i));
					CODGroupFailuresStowageNumbers.add(Is_COD_stownumber.get(i));
				}
			}

			if (!COD_stownumber.isEmpty()) {
				for (int i = 0; i < COD_stownumber.size(); i++) {
					Extent_group_table(test, COD_stownumber.get(i), "COD Group", "COD", COD_Group_Excepted.get(i),
							COD_Group_Actual.get(i));
					CODGroupFailuresStowageNumbers.add(Is_COD_stownumber.get(i));
				}
			}

			if (!IsSpl_stownumber.isEmpty()) {
				for (int i = 0; i < IsSpl_stownumber.size(); i++) {
					Extent_group_table(test, IsSpl_stownumber.get(i), "Special Group", "IsSpl",
							IsSpl_Group_Excepted.get(i), IsSpl_Group_Actual.get(i));
					SpecialGroupFailuresStowageNumbers.add(IsSpl_stownumber.get(i));
				}
			}

			if (!Rfr_stownumber.isEmpty()) {
				for (int i = 0; i < Rfr_stownumber.size(); i++) {
					Extent_group_table(test, Rfr_stownumber.get(i), "Reefer Group", "Rfr", Rfr_Group_Excepted.get(i),
							Rfr_Group_Actual.get(i));
					ReeferGroupFailuresStowageNumbers.add(Rfr_stownumber.get(i));
				}
			}

			for (Map.Entry<String, Set<String>> entry : failedStowageMap.entrySet()) {
				String columnName = entry.getKey();
				Set<String> failedStowageNumbers = entry.getValue();
				System.out.println("Failed Stowage values for " + columnName + " are: " + failedStowageNumbers);
				Extent_fail2(testDetail, "Failed Stowage values for " + columnName + " are: " + failedStowageNumbers);

			}

			for (Map.Entry<String, Integer> entry : failureCountByColumn.entrySet()) {
				System.out.println("Failure count for column '" + entry.getKey() + "': " + entry.getValue());
			}

//			Extent_fail2(test, "DG Group total fail count : " + DGGroupFailuresStowageNumbers.size());
//			Extent_fail2(test, "OOG Group total fail count : " + OOGGroupFailuresStowageNumbers.size());
//			Extent_fail2(test, "COD Group total fail count : " + CODGroupFailuresStowageNumbers.size());
//			Extent_fail2(test, "Special Group total fail count : " + SpecialGroupFailuresStowageNumbers.size());
//			Extent_fail2(test, "Reefer Group total fail count : " + ReeferGroupFailuresStowageNumbers.size());
//
//			System.out.println("DG Group total fail count : " + DGGroupFailuresStowageNumbers.size());
//			System.out.println("OOG Group total fail count : " + OOGGroupFailuresStowageNumbers.size());
//			System.out.println("COD Group total fail count : " + CODGroupFailuresStowageNumbers.size());
//			System.out.println("Special Group total fail count : " + SpecialGroupFailuresStowageNumbers.size());
//			System.out.println("Reefer Group total fail count : " + ReeferGroupFailuresStowageNumbers.size());

//			for (Map.Entry<String, Integer> entry : failGroupCountMapnew.entrySet()) {
//				System.out.println("Group failure count for column '" + entry.getKey() + "': " + entry.getValue());
//				Extent_fail2(test, "Group failure count for column '" + entry.getKey() + "': " + entry.getValue());
//
//			}

			int totalFailedCount = uniqueFailedStowageNumbers.size();
			
			

			recordset1.close();
			recordset2.close();
			connection1.close();
			connection2.close();
		} catch (FilloException e) {
			e.printStackTrace();
		}

	}


	public static void Createexcel(String MasterplanExcel, String TestplanExcel, String Finalresultpath,ExtentTest test)
			throws Exception {

        workbook=new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("MastertoTest");
		System.setProperty("ROW", "11");
		Fillo fillo = new Fillo();
		
		Font boldFont = workbook.createFont();
		boldFont.setBold(true);

		// Create a cell style with the bold font
		CellStyle boldCellStyle = workbook.createCellStyle();
		boldCellStyle.setFont(boldFont);

		Connection connection1 = fillo.getConnection(MasterplanExcel);
		Connection connection2 = fillo.getConnection(TestplanExcel);

		String query1 = "Select * from `CARGO LIST`";
		Recordset recordset1 = connection1.executeQuery(query1);

		String query2 = "Select * from `CARGO LIST`";
		Recordset recordset2 = connection2.executeQuery(query2);

		List<String> columnsToCompare = Arrays.asList("ISO", "Weight(t)", "POL", "POD", "Mty", "IsSpl", "Is COD", "COD",
				"Rfr", "OOG", "DG", "mul Haz", "DG class", "UNNO", "OOH (m)", "OLF (m)", "OLA (m)", "OWP (m)",
				"OWS (m)", "Booking No", "Variant", "FlashPoint", "DGLQ");

		// Create a row and add headers outside of the loop
		Row headerRow = sheet.createRow(0);

		// Add headers for Stowage and columnsToCompare
		String[] headers = new String[columnsToCompare.size() * 2 + 1];
		headers[0] = "Stowage";
		for (int i = 0; i < columnsToCompare.size(); i++) {
			headers[i * 2 + 1] = "Master_" + columnsToCompare.get(i);
			headers[i * 2 + 2] = "Test_" + columnsToCompare.get(i);
		}
		
		for (int i = 0; i < headers.length; i++) {
		    Cell cell = headerRow.createCell(i);
		    cell.setCellValue(headers[i]);
		    cell.setCellStyle(boldCellStyle); // Apply bold font style to header cells
		}

		int numberOfNewHeaders = 3;
		
		String[] newHeaders = {"MasterCombinedvalue", "TestCombinedvalue", "Comparisonstatus"};

		// Add the new headers to the combined headers
		String[] combinedHeaders = new String[headers.length + newHeaders.length];
		System.arraycopy(headers, 0, combinedHeaders, 0, headers.length);
		System.arraycopy(newHeaders, 0, combinedHeaders, headers.length, newHeaders.length);

		// Set headers in the Excel sheet and make them bold
		for (int i = 0; i < combinedHeaders.length; i++) {
		    Cell cell = headerRow.createCell(i);
		    cell.setCellValue(combinedHeaders[i]);
		    cell.setCellStyle(boldCellStyle); // Apply bold font style to header cells
		}

		Set<String> processedStowages = new HashSet<>(); // Set to track processed stowage values

		while (recordset1.next()) {
			String stowageSheet1 = recordset1.getField("Stowage");

			// Check if the stowage has already been processed
			if (!processedStowages.contains(stowageSheet1)) {
				Row row = sheet.createRow(sheet.getLastRowNum() + 1); // Start from the last row

				Cell stowageCell = row.createCell(0); // Stowage is in the first column (index 0)
				stowageCell.setCellValue(stowageSheet1);

				boolean matchFound = false; // Flag to check if a match is found in recordset2

				// Iterate through columnsToCompare and set cell values for recordset1
				for (int i = 0; i < columnsToCompare.size(); i++) {
					String columnName = columnsToCompare.get(i);
					String valueSheet1Column = recordset1.getField(columnName);

					Cell cell = row.createCell(i * 2 + 1);

					if ((valueSheet1Column.matches("\\d+") && valueSheet1Column.equals("0"))
							|| "0".equals(valueSheet1Column)) {
						cell.setCellValue("");
					} else {
						cell.setCellValue(valueSheet1Column);
					}
				}
				// Find the corresponding row in recordset2
				// Reset recordset2 to the beginning
				while (recordset2.next()) {
					String stowageSheet2 = recordset2.getField("Stowage");

					if (stowageSheet1.equals(stowageSheet2)) {
						matchFound = true;

						// Iterate through columnsToCompare and set cell values for recordset2
						for (int i = 0; i < columnsToCompare.size(); i++) {
							String columnName = columnsToCompare.get(i);
							String valueSheet2Column = recordset2.getField(columnName);

							Cell cell = row.createCell(i * 2 + 2);

							if ((valueSheet2Column.matches("\\d+") && valueSheet2Column.equals("0"))
									|| "0".equals(valueSheet2Column)) {
								cell.setCellValue("");
							} else {
								cell.setCellValue(valueSheet2Column);
							}
						}
					}
				}

				// Create a new row for recordset1 if no match is found in recordset2
				if (!matchFound) {
					for (int i = 0; i < columnsToCompare.size(); i++) {
						String columnName = columnsToCompare.get(i);
						String valueSheet1Column = recordset1.getField(columnName);

						Cell cell = row.createCell(i * 2 + 1);
						cell.setCellValue(valueSheet1Column);
					}
				}

				processedStowages.add(stowageSheet1);
				recordset2.moveFirst();
				// Add the processed stowage to the set
			}

		}
		// Write the workbook to a file
		try (FileOutputStream fileOut = new FileOutputStream(Finalresultpath)) {
			System.out.println("Excel path : " +Finalresultpath);
			workbook.write(fileOut);
			System.out.println("Excel file created successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		recordset1.close(); // Close the recordset after use
		recordset2.close();
		connection1.close();
		connection2.close();

		Testtomastercomparison(MasterplanExcel,TestplanExcel,Finalresultpath,test);
		Concatenation(Finalresultpath,test);
	}

	
	public static void Concatenation(String Finalresultpath, ExtentTest test) {

		try {
			FileInputStream file = new FileInputStream(Finalresultpath);
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheet("MastertoTest");

			// Get the first row for headings
			Row headingRow = sheet.getRow(0);

			// Process each row starting from the second row
			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row currentRow = sheet.getRow(rowIndex);

				StringBuilder concatenatedValues_M = new StringBuilder();
				StringBuilder concatenatedValues_T = new StringBuilder();

				// Concatenate cell values under specific headings for Master and Test
				for (int cellIndex = 0; cellIndex < headingRow.getLastCellNum() - 3; cellIndex++) {
					String heading = headingRow.getCell(cellIndex).getStringCellValue();

					if (heading.startsWith("Master_")) {
						Cell currentCell = currentRow.getCell(cellIndex);
						if (currentCell != null) {
							// concatenatedValues_M.append(currentCell.getStringCellValue())
							switch (currentCell.getCellType()) {
							case Cell.CELL_TYPE_STRING:
								concatenatedValues_M.append(currentCell.getStringCellValue());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								concatenatedValues_M.append(currentCell.getNumericCellValue());
								break;

							default:

							}
						}
					} else if (heading.startsWith("Test_")) {
						Cell currentCell = currentRow.getCell(cellIndex);
						if (currentCell != null) {
							// concatenatedValues_T.append(currentCell.getStringCellValue());

							switch (currentCell.getCellType()) {
							case Cell.CELL_TYPE_STRING:
								concatenatedValues_T.append(currentCell.getStringCellValue());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								concatenatedValues_T.append(currentCell.getNumericCellValue());
								break;

							default:

							}

						}
					}
				}

				int masterFullCellIndex = headingRow.getLastCellNum() - 3; // Adjust index to the appropriate column
				Cell masterFullCell = currentRow.getCell(masterFullCellIndex);
				if (masterFullCell == null) {
					// If the cell doesn't exist, create a new one
					masterFullCell = currentRow.createCell(masterFullCellIndex);
				}
				masterFullCell.setCellValue(concatenatedValues_M.toString());

				int testFullCellIndex = headingRow.getLastCellNum() - 2; // Adjust index to the appropriate column
				Cell testFullCell = currentRow.getCell(testFullCellIndex);
				if (testFullCell == null) {
					testFullCell = currentRow.createCell(testFullCellIndex);
				}
				testFullCell.setCellValue(concatenatedValues_T.toString());

//				System.out.println("Row Completed ..." + rowIndex);
			}

			// Write the changes to the Excel file
			FileOutputStream outFile = new FileOutputStream(Finalresultpath);
			workbook.write(outFile);
			outFile.close();
			workbook.close();

			System.out.println("Completed...................!!");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Finalcomparison(Finalresultpath,test);
	}

	
	public static void Finalcomparison(String Finalresultpath,ExtentTest test) {

		try {
			FileInputStream fileInputStream = new FileInputStream(Finalresultpath);
			Workbook workbook = WorkbookFactory.create(fileInputStream);

			Sheet sheet = workbook.getSheet("MastertoTest");

			int columnIndexToUpdate = findColumnIndex(sheet, "Comparisonstatus");
			
			int truecount = 0;
			int falsecount = 0;
			int NACount=0;

			// Iterate through the rows in the sheet
			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
				Cell masterCell = row.getCell(findColumnIndex(sheet, "MasterCombinedvalue"));
				Cell testCell = row.getCell(findColumnIndex(sheet, "TestCombinedvalue"));

				masterCell.toString();
				testCell.toString();

				// Compare values and write the result in the existing column
				Cell resultCell = row.createCell(columnIndexToUpdate);

				if (masterCell != null) {
					String masterValue = getCellValueAsString(masterCell);

					if (testCell == null || testCell.getCellTypeEnum() == CellType.BLANK) {
						// MasterValue is a string, and TestValue is null or blank
						resultCell.setCellValue("NA");
						NACount++;
					} else {
						String testValue = getCellValueAsString(testCell);

						if (masterValue.equals(testValue)) {
							resultCell.setCellValue("True");
							truecount++;
						} else if (testValue.isEmpty()) {
							resultCell.setCellValue("NA");
							NACount++;
						} else {
							resultCell.setCellValue("False");
							falsecount++;
						}
					}
				} else {
					// Handle the case where MasterCell is null
					resultCell.setCellValue("NA");
					NACount++;
				}
			}
			
			System.out.println("Total Pass count is : " + truecount);
			System.out.println("Total fail count is : "+ falsecount);
			System.out.println("Total NA count is : "+ NACount);
			
			Extent_pass1(test,"Total Pass count is : " + truecount);
			Extent_pass1(test,"Total fail count is : "+ falsecount);
			Extent_pass1(test,"Total NA count is : "+ NACount);
			
			
			try (FileOutputStream fileOut = new FileOutputStream(Finalresultpath)) {
				workbook.write(fileOut);
				System.out.println("Updated Excel file created successfully.");
			} catch (IOException e) {
				e.printStackTrace();
			}

			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Extent_passLink(test,"Final excel file",Finalresultpath);
		String[] groups = { "Master_IsSpl", "Master_Rfr", "Master_DG", "Master_Mty", "Master_OOG" };

		for (int j = 0; j < groups.length; j++) {
			filterGroups(Finalresultpath, groups[j],test);
		}
	}

	
	public static void filterGroups(String Finalresultpath, String group,ExtentTest test) {

		int trueCount = 0;
		int falseCount = 0;
		int NA_Count = 0;
		int totalCount = 0;

		ArrayList<String> StowageNumberList_true = new ArrayList<String>();
		ArrayList<String> StowageNumberList_false = new ArrayList<String>();
		ArrayList<String> StowageNumberList_NA = new ArrayList<String>();

		String masterCheck = group;

		try (FileInputStream fileInputStream = new FileInputStream(Finalresultpath);
				Workbook workbook = new XSSFWorkbook(fileInputStream)) {

			Sheet sheet = workbook.getSheet("MastertoTest");

			Sheet newSheet = workbook.createSheet(masterCheck);// -

			Row headerRow = sheet.getRow(0);
			int Master_index = -1;
			int Result_index = -1;
			int Stowage_index = -1;

			for (Cell cell : headerRow) {
				String cellValue = cell.getStringCellValue();
				if (cellValue.equals(masterCheck)) {
					Master_index = cell.getColumnIndex();//
				} else if (cellValue.equals("Comparisonstatus")) {
					Result_index = cell.getColumnIndex();
				} else if (cellValue.equals("Stowage")) {
					Stowage_index = cell.getColumnIndex();
				}
			}

			// Iterate through rows
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);

				Cell Master_Cell = row.getCell(Master_index); //

				Cell Result_Cell = row.getCell(Result_index);
				Cell Stowage_Cell = row.getCell(Stowage_index);

				if (Master_Cell != null && Master_Cell.getStringCellValue().equalsIgnoreCase("Yes")) {

					totalCount++;

					if (Result_Cell != null && Result_Cell.getStringCellValue().equalsIgnoreCase("True")) {
						String result_true = Stowage_Cell.getStringCellValue();
						StowageNumberList_true.add(result_true);
						trueCount++;
					} else if (Result_Cell != null && Result_Cell.getStringCellValue().equalsIgnoreCase("False")) {
						String result_false = Stowage_Cell.getStringCellValue();
						StowageNumberList_false.add(result_false);
						falseCount++;
					} else if (Result_Cell != null && Result_Cell.getStringCellValue().equalsIgnoreCase("NA")) {
						String result_NA = Stowage_Cell.getStringCellValue();
						StowageNumberList_NA.add(result_NA);
						NA_Count++;
					}
				
				}
				
			}

			System.out.println("Count checking for " + masterCheck + "...................");
			System.out.println("Total Count for : " + totalCount);
			System.out.println("True Count: " + trueCount + " || " + "False Count: " + falseCount + " || "
					+ "NA Count: " + NA_Count);
			Extent_fail1(test, masterCheck+" True count : "+trueCount+" || "+ " False count "+falseCount + " || "+ " NA Count "+NA_Count);
			Extent_fail1(test, masterCheck + " false stowage numbers are : "+ StowageNumberList_false);
			Extent_fail1(test, masterCheck + " NA stowage numbers are : "+ StowageNumberList_NA);
			
			
			System.out.println();
			
//			System.out.println("***********************************************************");
//			System.out.println("True stowage list for " + masterCheck);
//			for (int k = 0; k < StowageNumberList_true.size(); k++) {
//				
//				System.out.println(StowageNumberList_true.get(k));
//			}

			System.out.println("***********************************************************");
			System.out.println("False stowage list for " + masterCheck);
			for (int k = 0; k < StowageNumberList_false.size(); k++) {
				System.out.println(StowageNumberList_false.get(k));
				
			}

			System.out.println("***********************************************************");
			System.out.println("NA stowage list " + masterCheck);
			for (int k = 0; k < StowageNumberList_NA.size(); k++) {
		
				System.out.println(StowageNumberList_NA.get(k));
			}

			System.out.println();
			System.out.println("************************ Finished  ***********************************");
			System.out.println();

			int a = StowageNumberList_NA.size();
			int b = StowageNumberList_true.size();
			int c = StowageNumberList_false.size();

			int rowCreateCount = findLargest(a, b, c);
			for (int i = 0; i <= rowCreateCount + 5; i++) {
				Row rowNewSheet = newSheet.createRow(i);
			}
			
			Font boldFont = workbook.createFont();
			boldFont.setBold(true);

			// Create a cell style with the bold font
			CellStyle boldCellStyle = workbook.createCellStyle();
			boldCellStyle.setFont(boldFont);

			// Assuming you have created the new sheet elsewhere in your code
			Row headerNewSheet = newSheet.getRow(0);

			// Create headers
			String[] headers = {masterCheck, "Yes", "StowageNumberList_false", "StowageNumberList_NA"};

			// Set headers in the Excel sheet and make them bold
			for (int i = 0; i < headers.length; i++) {
			    Cell cell = headerNewSheet.createCell(i);
			    cell.setCellValue(headers[i]);
			    cell.setCellStyle(boldCellStyle); // Apply bold font style to header cells
			}

//			Row headerNewSheet = newSheet.getRow(0);
//			headerNewSheet.createCell(0).setCellValue(masterCheck);
//			headerNewSheet.createCell(1).setCellValue("Yes");
//			headerNewSheet.createCell(2).setCellValue("StowageNumberList_false");
//			headerNewSheet.createCell(3).setCellValue("StowageNumberList_NA");

			Row trueCountRow = newSheet.getRow(1);
			if (trueCountRow == null) {
				trueCountRow = newSheet.createRow(2); // Create the row if it's null
			}
			trueCountRow.createCell(0).setCellValue("TrueCount");
			trueCountRow.createCell(1).setCellValue(trueCount);
			
//			Row trueCountRow = newSheet.getRow(1);
//			trueCountRow.createCell(0).setCellValue("TrueCount");
//			trueCountRow.createCell(1).setCellValue(trueCount);

			Row falseCountRow = newSheet.getRow(2);
			if (falseCountRow == null) {
			    falseCountRow = newSheet.createRow(2); // Create the row if it's null
			}
			falseCountRow.createCell(0).setCellValue("FalseCount");
			falseCountRow.createCell(1).setCellValue(falseCount);

			Row naCountRow = newSheet.getRow(3);
			if (naCountRow == null) {
			    naCountRow = newSheet.createRow(3); // Create the row if it's null
			}
			naCountRow.createCell(0).setCellValue("NA_Count");
			naCountRow.createCell(1).setCellValue(NA_Count);

			Row totalCountRow = newSheet.getRow(4);
			if (totalCountRow == null) {
			    totalCountRow = newSheet.createRow(4); // Create the row if it's null
			}
			totalCountRow.createCell(0).setCellValue("TotalCount");
			totalCountRow.createCell(1).setCellValue(totalCount);


			for (int i = 1; i <= StowageNumberList_false.size(); i++) {
				Row stowageRow = newSheet.getRow(i);
				stowageRow.createCell(2).setCellValue(StowageNumberList_false.get(i - 1));
			}

			for (int i = 1; i <= StowageNumberList_NA.size(); i++) {
				Row stowageRow = newSheet.getRow(i);
				stowageRow.createCell(3).setCellValue(StowageNumberList_NA.get(i - 1));
			}

			try (FileOutputStream outputStream = new FileOutputStream(Finalresultpath)) {
				workbook.write(outputStream);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void Testtomastercomparison(String MasterplanExcel, String TestplanExcel, String Finalresultpath,ExtentTest test)
			throws Exception {

		Sheet sheet = workbook.createSheet("Testtomaster");
		System.setProperty("ROW", "11");
		Fillo fillo = new Fillo();

		Connection connection1 = fillo.getConnection(TestplanExcel);
		Connection connection2 = fillo.getConnection(MasterplanExcel);

		String query1 = "Select * from `CARGO LIST`";
		Recordset recordset1 = connection1.executeQuery(query1);

		String query2 = "Select * from `CARGO LIST`";
		Recordset recordset2 = connection2.executeQuery(query2);

		List<String> columnsToCompare = Arrays.asList("ISO", "Weight(t)", "POL", "POD", "Mty", "IsSpl", "Is COD", "COD",
				"Rfr", "OOG", "DG", "mul Haz", "DG class", "UNNO", "OOH (m)", "OLF (m)", "OLA (m)", "OWP (m)",
				"OWS (m)", "Booking No", "Variant", "FlashPoint", "DGLQ");

		Font boldFont = workbook.createFont();
		boldFont.setBold(true);

		// Create a cell style with the bold font
		CellStyle boldCellStyle = workbook.createCellStyle();
		boldCellStyle.setFont(boldFont);

		
		// Create a row and add headers outside of the loop
		Row headerRow = sheet.createRow(0);

		// Add headers for Stowage and columnsToCompare
		String[] headers = new String[columnsToCompare.size() * 2 + 1];
		headers[0] = "Stowage";
		for (int i = 0; i < columnsToCompare.size(); i++) {
			headers[i * 2 + 1] = "Test_" + columnsToCompare.get(i);
			headers[i * 2 + 2] = "Master_" + columnsToCompare.get(i);
		}

		int numberOfNewHeaders = 3;

		String[] newHeaders = {"MasterCombinedvalue", "TestCombinedvalue", "Comparisonstatus"};

		// Add the new headers to the combined headers
		String[] combinedHeaders = new String[headers.length + newHeaders.length];
		System.arraycopy(headers, 0, combinedHeaders, 0, headers.length);
		System.arraycopy(newHeaders, 0, combinedHeaders, headers.length, newHeaders.length);

		// Set headers in the Excel sheet and make them bold
		for (int i = 0; i < combinedHeaders.length; i++) {
		    Cell cell = headerRow.createCell(i);
		    cell.setCellValue(combinedHeaders[i]);
		    cell.setCellStyle(boldCellStyle); // Apply bold font style to header cells
		}
		
		Set<String> processedStowages = new HashSet<>(); // Set to track processed stowage values

		while (recordset1.next()) {
			String stowageSheet1 = recordset1.getField("Stowage");

			// Check if the stowage has already been processed
			if (!processedStowages.contains(stowageSheet1)) {
				Row row = sheet.createRow(sheet.getLastRowNum() + 1); // Start from the last row

				Cell stowageCell = row.createCell(0); // Stowage is in the first column (index 0)
				stowageCell.setCellValue(stowageSheet1);

				boolean matchFound = false; // Flag to check if a match is found in recordset2

				// Iterate through columnsToCompare and set cell values for recordset1
				for (int i = 0; i < columnsToCompare.size(); i++) {
					String columnName = columnsToCompare.get(i);
					String valueSheet1Column = recordset1.getField(columnName);

					Cell cell = row.createCell(i * 2 + 1);

					if ((valueSheet1Column.matches("\\d+") && valueSheet1Column.equals("0"))
							|| "0".equals(valueSheet1Column)) {
						cell.setCellValue("");
					} else {
						cell.setCellValue(valueSheet1Column);
					}
				}
				// Find the corresponding row in recordset2
				// Reset recordset2 to the beginning
				while (recordset2.next()) {
					String stowageSheet2 = recordset2.getField("Stowage");

					if (stowageSheet1.equals(stowageSheet2)) {
						matchFound = true;

						// Iterate through columnsToCompare and set cell values for recordset2
						for (int i = 0; i < columnsToCompare.size(); i++) {
							String columnName = columnsToCompare.get(i);
							String valueSheet2Column = recordset2.getField(columnName);

							Cell cell = row.createCell(i * 2 + 2);

							if ((valueSheet2Column.matches("\\d+") && valueSheet2Column.equals("0"))
									|| "0".equals(valueSheet2Column)) {
								cell.setCellValue("");
							} else {
								cell.setCellValue(valueSheet2Column);
							}
						}
					}
				}

				// Create a new row for recordset1 if no match is found in recordset2
				if (!matchFound) {
					for (int i = 0; i < columnsToCompare.size(); i++) {
						String columnName = columnsToCompare.get(i);
						String valueSheet1Column = recordset1.getField(columnName);

						Cell cell = row.createCell(i * 2 + 1);
						cell.setCellValue(valueSheet1Column);
					}
				}

				processedStowages.add(stowageSheet1);
				recordset2.moveFirst();
				// Add the processed stowage to the set
			}

		}
		// Write the workbook to a file
		try (FileOutputStream fileOut = new FileOutputStream(Finalresultpath)) {
			workbook.write(fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		}

		recordset1.close(); // Close the recordset after use
		recordset2.close();
		connection1.close();
		connection2.close();

		Concatenation1(Finalresultpath);
	}

	
	public static void Concatenation1(String Finalresultpath) {

		try {
			FileInputStream file = new FileInputStream(Finalresultpath);
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheet("Testtomaster");

			// Get the first row for headings
			Row headingRow = sheet.getRow(0);

			// Process each row starting from the second row
			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row currentRow = sheet.getRow(rowIndex);

				StringBuilder concatenatedValues_M = new StringBuilder();
				StringBuilder concatenatedValues_T = new StringBuilder();

				// Concatenate cell values under specific headings for Master and Test
				for (int cellIndex = 0; cellIndex < headingRow.getLastCellNum() - 3; cellIndex++) {
					String heading = headingRow.getCell(cellIndex).getStringCellValue();

					if (heading.startsWith("Test_")) {
						Cell currentCell = currentRow.getCell(cellIndex);
						if (currentCell != null) {
							// concatenatedValues_M.append(currentCell.getStringCellValue())
							switch (currentCell.getCellType()) {
							case Cell.CELL_TYPE_STRING:
								concatenatedValues_M.append(currentCell.getStringCellValue());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								concatenatedValues_M.append(currentCell.getNumericCellValue());
								break;

							default:

							}
						}
					} else if (heading.startsWith("Master_")) {
						Cell currentCell = currentRow.getCell(cellIndex);
						if (currentCell != null) {
							// concatenatedValues_T.append(currentCell.getStringCellValue());

							switch (currentCell.getCellType()) {
							case Cell.CELL_TYPE_STRING:
								concatenatedValues_T.append(currentCell.getStringCellValue());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								concatenatedValues_T.append(currentCell.getNumericCellValue());
								break;

							default:

							}

						}
					}
				}

				int masterFullCellIndex = headingRow.getLastCellNum() - 3; // Adjust index to the appropriate column
				Cell masterFullCell = currentRow.getCell(masterFullCellIndex);
				if (masterFullCell == null) {
					// If the cell doesn't exist, create a new one
					masterFullCell = currentRow.createCell(masterFullCellIndex);
				}
				masterFullCell.setCellValue(concatenatedValues_M.toString());

				int testFullCellIndex = headingRow.getLastCellNum() - 2; // Adjust index to the appropriate column
				Cell testFullCell = currentRow.getCell(testFullCellIndex);
				if (testFullCell == null) {
					testFullCell = currentRow.createCell(testFullCellIndex);
				}
				testFullCell.setCellValue(concatenatedValues_T.toString());

//				System.out.println("Row Completed ..." + rowIndex);
			}

			// Write the changes to the Excel file
			FileOutputStream outFile = new FileOutputStream(Finalresultpath);
			workbook.write(outFile);
			outFile.close();
			workbook.close();

			System.out.println("Completed...................!!");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Finalcomparison1(Finalresultpath);
	}

	
	public static void Finalcomparison1(String Finalresultpath) {

		try {
			FileInputStream fileInputStream = new FileInputStream(Finalresultpath);
			Workbook workbook = WorkbookFactory.create(fileInputStream);

			Sheet sheet = workbook.getSheet("Testtomaster");

			int columnIndexToUpdate = findColumnIndex(sheet, "Comparisonstatus");

			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
				Cell masterCell = row.getCell(findColumnIndex(sheet, "MasterCombinedvalue"));
				Cell testCell = row.getCell(findColumnIndex(sheet, "TestCombinedvalue"));

				masterCell.toString();
				testCell.toString();

				Cell resultCell = row.createCell(columnIndexToUpdate);

				if (testCell != null) {
					String testValue = getCellValueAsString(testCell);

					if (masterCell == null || masterCell.getCellTypeEnum() == CellType.BLANK) {
						resultCell.setCellValue("NA");
					} else {
						String mastervalue = getCellValueAsString(masterCell);

						if (testValue.equals(mastervalue)) {
							resultCell.setCellValue("True");
						} else if (mastervalue.isEmpty()) {
							resultCell.setCellValue("NA");
						} else {
							resultCell.setCellValue("False");
						}
					}
				} else {
					resultCell.setCellValue("NA");
				}
			}
			try (FileOutputStream fileOut = new FileOutputStream(Finalresultpath)) {
				workbook.write(fileOut);
				System.out.println("Updated Excel file created successfully.");
			} catch (IOException e) {
				e.printStackTrace();
			}

			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		String[] groups = { "Master_IsSpl", "Master_Rfr", "Master_DG", "Master_Mty", "Master_OOG" };
//
//		for (int j = 0; j < groups.length; j++) {
//			filterGroups1(Finalresultpath, groups[j]);
//		}
	}

	
//	public static void filterGroups1(String Finalresultpath, String group) {
//
//		int trueCount = 0;
//		int falseCount = 0;
//		int NA_Count = 0;
//		int totalCount = 0;
//
//		ArrayList<String> StowageNumberList_true = new ArrayList<String>();
//		ArrayList<String> StowageNumberList_false = new ArrayList<String>();
//		ArrayList<String> StowageNumberList_NA = new ArrayList<String>();
//
//		String masterCheck = group;
//
//		try (FileInputStream fileInputStream = new FileInputStream(Finalresultpath);
//				Workbook workbook = new XSSFWorkbook(fileInputStream)) {
//
//			Sheet sheet = workbook.getSheet("MastertoTest");
//
//			Sheet newSheet = workbook.createSheet(masterCheck);// -
//
//			Row headerRow = sheet.getRow(0);
//			int Master_index = -1;
//			int Result_index = -1;
//			int Stowage_index = -1;
//
//			for (Cell cell : headerRow) {
//				String cellValue = cell.getStringCellValue();
//				if (cellValue.equals(masterCheck)) {
//					Master_index = cell.getColumnIndex();//
//				} else if (cellValue.equals("Comparisonstatus")) {
//					Result_index = cell.getColumnIndex();
//				} else if (cellValue.equals("Stowage")) {
//					Stowage_index = cell.getColumnIndex();
//				}
//			}
//
//			// Iterate through rows
//			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//				Row row = sheet.getRow(i);
//
//				Cell Master_Cell = row.getCell(Master_index); //
//
//				Cell Result_Cell = row.getCell(Result_index);
//				Cell Stowage_Cell = row.getCell(Stowage_index);
//
//				if (Master_Cell != null && Master_Cell.getStringCellValue().equalsIgnoreCase("Yes")) {
//
//					totalCount++;
//
//					if (Result_Cell != null && Result_Cell.getStringCellValue().equalsIgnoreCase("True")) {
//						String result_true = Stowage_Cell.getStringCellValue();
//						StowageNumberList_true.add(result_true);
//						trueCount++;
//					} else if (Result_Cell != null && Result_Cell.getStringCellValue().equalsIgnoreCase("False")) {
//						String result_false = Stowage_Cell.getStringCellValue();
//						StowageNumberList_false.add(result_false);
//						falseCount++;
//					} else if (Result_Cell != null && Result_Cell.getStringCellValue().equalsIgnoreCase("NA")) {
//						String result_NA = Stowage_Cell.getStringCellValue();
//						StowageNumberList_NA.add(result_NA);
//						NA_Count++;
//					}
//				
//				}
//				
//			}
//
//			System.out.println("Count checking for " + masterCheck + "...................");
//			System.out.println("Total Count for : " + totalCount);
//			System.out.println("True Count: " + trueCount + " || " + "False Count: " + falseCount + " || "
//					+ "NA Count: " + NA_Count);
//			Extent_fail1(test, masterCheck+" True count : "+trueCount+" || "+ " False count "+falseCount + " || "+ " NA Count "+NA_Count);
//			Extent_fail1(test, masterCheck + " false stowage numbers are : "+ StowageNumberList_false);
//			Extent_fail1(test, masterCheck + " NA stowage numbers are : "+ StowageNumberList_NA);
//			
//			
//			System.out.println();
//			
//			System.out.println("***********************************************************");
//			System.out.println("True stowage list for " + masterCheck);
//			for (int k = 0; k < StowageNumberList_true.size(); k++) {
//				
//				System.out.println(StowageNumberList_true.get(k));
//			}
//
//			System.out.println("***********************************************************");
//			System.out.println("False stowage list for " + masterCheck);
//			for (int k = 0; k < StowageNumberList_false.size(); k++) {
//				System.out.println(StowageNumberList_false.get(k));
//				
//			}
//
//			System.out.println("***********************************************************");
//			System.out.println("NA stowage list " + masterCheck);
//			for (int k = 0; k < StowageNumberList_NA.size(); k++) {
//		
//				System.out.println(StowageNumberList_NA.get(k));
//			}
//
//			System.out.println();
//			System.out.println("************************ Finished  ***********************************");
//			System.out.println();
//
//			int a = StowageNumberList_NA.size();
//			int b = StowageNumberList_true.size();
//			int c = StowageNumberList_false.size();
//
//			int rowCreateCount = findLargest(a, b, c);
//			for (int i = 0; i <= rowCreateCount + 1; i++) {
//				Row rowNewSheet = newSheet.createRow(i);
//			}
//
//			Row headerNewSheet = newSheet.getRow(0);
//			headerNewSheet.createCell(0).setCellValue(masterCheck);
//			headerNewSheet.createCell(1).setCellValue("Yes");
//			headerNewSheet.createCell(2).setCellValue("StowageNumberList_false");
//			headerNewSheet.createCell(3).setCellValue("StowageNumberList_NA");
//
//			Row trueCountRow = newSheet.getRow(1);
//			trueCountRow.createCell(0).setCellValue("TrueCount");
//			trueCountRow.createCell(1).setCellValue(trueCount);
//
//			Row falseCountRow = newSheet.getRow(2);
//			falseCountRow.createCell(0).setCellValue("FalseCount");
//			falseCountRow.createCell(1).setCellValue(falseCount);
//
//			Row naCountRow = newSheet.getRow(3);
//			naCountRow.createCell(0).setCellValue("NA_Count");
//			naCountRow.createCell(1).setCellValue(NA_Count);
//
//			Row totalCountRow = newSheet.getRow(4);
//			totalCountRow.createCell(0).setCellValue("TotalCount");
//			totalCountRow.createCell(1).setCellValue(totalCount);
//
//			for (int i = 1; i <= StowageNumberList_false.size(); i++) {
//				Row stowageRow = newSheet.getRow(i);
//				stowageRow.createCell(2).setCellValue(StowageNumberList_false.get(i - 1));
//			}
//
//			for (int i = 1; i <= StowageNumberList_NA.size(); i++) {
//				Row stowageRow = newSheet.getRow(i);
//				stowageRow.createCell(3).setCellValue(StowageNumberList_NA.get(i - 1));
//			}
//
//			try (FileOutputStream outputStream = new FileOutputStream(Finalresultpath)) {
//				workbook.write(outputStream);
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}

	private static String getCellValueAsString(Cell cell) {
		switch (cell.getCellTypeEnum()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			// Handle numeric values if needed
			return String.valueOf(cell.getNumericCellValue());
		default:
			return "";
		}
	}

	public static int findLargest(int a, int b, int c) {
		if (a >= b && a >= c) {
			return a;
		} else if (b >= a && b >= c) {
			return b;
		} else {
			return c;
		}
	}

	private static int findColumnIndex(Sheet sheet, String header) {
		int columnIndex = -1;

		// Assuming the header is in the first row (index 0)
		Row headerRow = sheet.getRow(0);

		for (Cell cell : headerRow) {
			if (cell.getStringCellValue().equalsIgnoreCase(header)) {
				columnIndex = cell.getColumnIndex();
				break;
			}
		}

		return columnIndex;
	}

	public String Vesselname(String str) {

		if (str.length() < 3) {
			return str;
		} else {
			return str.substring(0, 3);
		}
	}
	
	   public static void compareContainerData(String masterFilePath, String testFilePath) throws IOException {
	        FileInputStream masterFileInputStream = new FileInputStream(masterFilePath);
	        FileInputStream testFileInputStream = new FileInputStream(testFilePath);

	        Workbook masterWorkbook = new XSSFWorkbook(masterFileInputStream);
	        Workbook testWorkbook = new XSSFWorkbook(testFileInputStream);

	        Sheet masterSheet = masterWorkbook.getSheetAt(0);
	        Sheet testSheet = testWorkbook.getSheetAt(0);

	        // Get headings from both sheets
	        Row masterHeaderRow = masterSheet.getRow(0);
	        Row testHeaderRow = testSheet.getRow(0);

	        List<String> masterHeadings = getHeadings(masterHeaderRow);
	        List<String> testHeadings = getHeadings(testHeaderRow);
//
	        int testSheetLastRow=testSheet.getLastRowNum();
	        
	        // Check for new headings in test file
	        for (String heading : testHeadings) {
	            if (!masterHeadings.contains(heading)) {
	                System.out.println("New heading in test file: " + heading);
	                String result_1="New heading in test file: " + heading;
	                Extent_fail2(test,result_1);
	                
	                int newHeadIndex=0;
	                
	                for (Cell cell : testHeaderRow) {
	    				String cellValue = cell.getStringCellValue();
	    				if (cellValue.contains(heading)) {
	    					newHeadIndex = cell.getColumnIndex();//
	    				}
	    				
	    			}
	                
	               // int headIndex=r.get
	        		System.out.println();
	        		System.out.println("New Cell Values under "+heading +" heading  :  ");
	        		String result_2="New Cell Values under "+heading +" heading  :  ";
	        		Extent_fail2(test,result_2);
	                
	                for(int r=1;r<=testSheetLastRow;r++) {
	                	
	                	Row newRow=testSheet.getRow(r);
	    	        	Cell newCell=newRow.getCell(newHeadIndex);
	    	        	String newCellValue=newCell.getStringCellValue();
	  
	    	        	String sPodValue=newRow.getCell(0).getStringCellValue();
    	        		System.out.println(sPodValue+" & "+heading+ "  :  "+newCellValue);
    	        		String result_3=sPodValue+" & "+heading+ "  :  "+newCellValue;
    	        		Extent_fail2(test,result_3);
    	                
	                }
	            }
	        }
	        
	        boolean containerMatch=false;
	        Row masHeadrow=masterSheet.getRow(0);
	        for(int m=1;m<masHeadrow.getLastCellNum();m++) {
	        	Cell masHeadCell=masHeadrow.getCell(m);
               String mastHead=masHeadCell.getStringCellValue();
	        for (int i = 1; i <= masterSheet.getLastRowNum(); i++) {
	            
	        	Row masRow=masterSheet.getRow(i);
	        	Cell masCell=masRow.getCell(m);
	        	String mastCellValue=masCell.getStringCellValue();
	        	
	        	
	        	Row testHeadRow=testSheet.getRow(0);
	        	
	        	for (int k=1;k<testHeadRow.getLastCellNum();k++) {
	        		Cell testHeadCell=testHeadRow.getCell(k);
	                String testHead=testHeadCell.getStringCellValue();
	        		if(testHead.equals(mastHead)) {
	        			Row testRow=testSheet.getRow(i);
	    	        	Cell testCell=testRow.getCell(k);
	    	        	String testCellValue=testCell.getStringCellValue();
	    	        	
	    	        	if(testCellValue.equals(mastCellValue)) {
	    	        		String sPodValue=masRow.getCell(0).getStringCellValue();
	    	        		System.out.println();
	    	        		System.out.println("Passed..");
	    	        		System.out.println("Master and test value matched for "+sPodValue+" & "+mastHead+ " : ");
	    	        		System.out.println("Master value : "+mastCellValue+ "  ||  "+"Test Value : "+testCellValue);
	    	        	}else {
	    	        		String sPodValue=masRow.getCell(0).getStringCellValue();
	    	        		System.out.println();
	    	        		System.out.println("Failed..!");
	    	        		String failed_result1="Master and test value Mismatched for "+sPodValue+" & "+mastHead+ " : ";
	    	        		String failed_result2="Master value : "+mastCellValue+ "  ||  "+"Test Value : "+testCellValue;
	    	        		
	    	        		Extent_fail2(test,failed_result1);
	    	        		Extent_fail2(test,failed_result2);
	    	        		
	    	        		System.out.println(failed_result1);
	    	        		System.out.println(failed_result2);
	    	        	}
	    	        	
	    	        	break;
	        		}
	        		
	        		//new condition for testing 
	        		if(k==(testHeadRow.getLastCellNum()-1)) {
	        			if(!testHead.equals(mastHead)) {
	        				if(containerMatch==false) {
	    	        		System.out.println();
	    	        		System.out.println("Failed..!");
	        				System.out.println(mastHead+" row is not present in Test Result...");
	        				String resultFail=mastHead+" row is not present in Test Result...";
	    	        		Extent_fail2(test,resultFail);
	        				System.out.println();
	        				containerMatch=true;
	        				}
	        			}
	        		}
	        	}
	        }

	        }
	        
	        
	        masterWorkbook.close();
	        testWorkbook.close();
	        masterFileInputStream.close();
	        testFileInputStream.close();
	    }

	    private static List<String> getHeadings(Row headerRow) {
	        List<String> headings = new ArrayList<>();
	        Iterator<Cell> cellIterator = headerRow.cellIterator();
	        while (cellIterator.hasNext()) {
	            Cell cell = cellIterator.next();
	            headings.add(cell.getStringCellValue());
	        }
	        return headings;
	    }
	
	    public void Tankdetailscomparison(String Master, String Test, ExtentTest test, ExtentTest testDetail, String detailreportPath) {
	        try {
	        	System.setProperty("ROW", "2");
	        	Fillo fillo = new Fillo();

	        	Connection connection1 = fillo.getConnection(Master);
	        	Connection connection2 = fillo.getConnection(Test);

	        	String query1 = "Select * from `Tank Details`";
	        	Recordset recordset1 = connection1.executeQuery(query1);

	        	String query2 = "Select * from `Tank Details`";
	        	Recordset recordset2 = connection2.executeQuery(query2);

	        	List<String> columnsToCompare = Arrays.asList("Tank Category", "Tank Name", "Weight", "Tank Code");

	        	Set<String> uniqueFailedTanknames = new HashSet<>();
	        	Set<String> uniquePassedTanknames = new HashSet<>();
	        	Set<String> missingTankNames = new HashSet<>();

//	        	Extent_passLink(test, "Detailed Result", detailreportPath);
	        	String passReportpath=detailreportPath+"_Pass"+".html";
				String failReportPath=detailreportPath+"_Fail"+".html";
				Extent_passLink(test,"Detailed Pass Result",passReportpath);
				Extent_passLink(test,"Detailed Failed Result",failReportPath);

	        	while (recordset1.next()) {
	        	    String Tankdetails1 = recordset1.getField("Tank Name");
	        	    String columnName = "";
	        	    String valueSheet1Column = "";
	        	    String valueSheet2Column = "";

	        	    recordset2.moveFirst();

	        	    boolean matchFound = false;
	        	    boolean allColumnsMatch = true; // Flag to track if all columns match

	        	    while (recordset2.next()) {
	        	        String Tankdetails2 = recordset2.getField("Tank Name");

	        	        if (Tankdetails1 != null && Tankdetails1.trim().equals(Tankdetails2 != null ? Tankdetails2.trim() : "")) {
	        	            matchFound = true;

	        	            for (String colName : columnsToCompare) {
	        	                columnName = colName;
	        	                valueSheet1Column = recordset1.getField(colName);
	        	                valueSheet2Column = recordset2.getField(colName);

	        	                if ((valueSheet1Column == null && valueSheet2Column == null) ||
	        	                        ((Tankdetails1 == null || Tankdetails1.trim().isEmpty()) && "0".equals(Tankdetails2)) ||
	        	                        ((Tankdetails2 == null || Tankdetails2.trim().isEmpty()) && "0".equals(Tankdetails1)) ||
	        	                        (valueSheet1Column != null && valueSheet1Column.equals(valueSheet2Column))) {
	        	                    // Values match, continue to next column
	        	                } else {
	        	                    // Values don't match, set flag to false and break the loop
	        	                    allColumnsMatch = false;
	        	                    break;
	        	                }
	        	            }

	        	            if (allColumnsMatch) {
	        	                uniquePassedTanknames.add(Tankdetails1); // Add passed Tank Name to set
	        	                
	        	                Extent_pass1(test2,
										"Values are matched for " + "Tank Name : " + Tankdetails1 + "||"
												+ " Column name is :" + columnName + "||" + " Master value: "
												+ valueSheet1Column + "||" + " Test value: " + valueSheet2Column);
	        	            } else {
	        	                uniqueFailedTanknames.add(Tankdetails1); // Add failed Tank Name to set
	        	                Extent_fail1(testDetail, "Values are not matched for Tank Name : " + Tankdetails1 + "||" +
	        	                        " Column name is :" + columnName + "||" +
	        	                        " Master value: " + valueSheet1Column + "||" +
	        	                        " Test value: " + valueSheet2Column);
	        	        		Extent_group_table1(test, Tankdetails1,columnName, valueSheet1Column, valueSheet2Column);

	        	            }

	        	            // Reset the flag for the next iteration
	        	            allColumnsMatch = true;
	        	        }
	        	    }

	        	    if (!matchFound) {
	        	        // No matching Tank Name found in the test dataset
	        	        missingTankNames.add(Tankdetails1); // Add missing Tank Name to set
	        	        Extent_fail1(testDetail, "Tank Name not found in test dataset: " + Tankdetails1);
	        	    }
	        	}

	        	// Print missing Tank Names as "NA"
	        

	        	// Count unique passed and failed rows
	        	int passRowCount = uniquePassedTanknames.size();
	        	int failRowCount = uniqueFailedTanknames.size();

	        	// Print unique passed Tank Names
	        	System.out.println("Unique passed Tank Names:");
	        	for (String tankName : uniquePassedTanknames) {
	        	    System.out.println(tankName);
	        	}
        	
	        	System.out.println("Total pass count is : "+passRowCount);
				System.out.println("Total fail count is : "+failRowCount);
				System.out.println("Failed tank names are : "+ uniqueFailedTanknames);
				Extent_fail1(test, "Total pass count is : "+passRowCount);
				Extent_fail1(test, "Total fail count is : "+failRowCount);
				Extent_fail1(test, "Failed tank names are : "+ uniqueFailedTanknames);
							
	        	
	        	for (String missingTank : missingTankNames) {
	        	    System.out.println("Missing tank names are : " + missingTank);
	        	}

	            recordset1.close();
	            recordset2.close();
	            connection1.close();
	            connection2.close();
	        } catch (FilloException e) {
	            e.printStackTrace();
	        }
	    }

	    public void Tankdetailscomparison1(String Master, String Test, ExtentTest test, ExtentTest testDetail, String detailreportPath) {
	        try {
	        	System.setProperty("ROW", "2");
	        	Fillo fillo = new Fillo();

	        	Connection connection1 = fillo.getConnection(Master);
	        	Connection connection2 = fillo.getConnection(Test);

	        	String query1 = "Select * from `Tank Details`";
	        	Recordset recordset1 = connection1.executeQuery(query1);

	        	String query2 = "Select * from `Tank Details`";
	        	Recordset recordset2 = connection2.executeQuery(query2);

	        	List<String> columnsToCompare = Arrays.asList("Tank Category", "Tank Name", "Weight", "Tank Code");

	        	Set<String> uniqueFailedTanknames = new HashSet<>();
	        	Set<String> uniquePassedTanknames = new HashSet<>();
	        	Set<String> missingTankNames = new HashSet<>();

//	        	Extent_passLink(test, "Detailed Result", detailreportPath);
	        	String passReportpath=detailreportPath+"_Pass"+".html";
				String failReportPath=detailreportPath+"_Fail"+".html";
				Extent_passLink(test,"Detailed Pass Result",passReportpath);
				Extent_passLink(test,"Detailed Failed Result",failReportPath);

	        	while (recordset1.next()) {
	        	    String Tankdetails1 = recordset1.getField("Tank Name");
	        	    String columnName = "";
	        	    String valueSheet1Column = "";
	        	    String valueSheet2Column = "";

	        	    recordset2.moveFirst();

	        	    boolean matchFound = false;
	        	    boolean allColumnsMatch = true; // Flag to track if all columns match

	        	    while (recordset2.next()) {
	        	        String Tankdetails2 = recordset2.getField("Tank Name");

	        	        if (Tankdetails1 != null && Tankdetails1.trim().equals(Tankdetails2 != null ? Tankdetails2.trim() : "")) {
	        	            matchFound = true;

	        	            for (String colName : columnsToCompare) {
	        	                columnName = colName;
	        	                valueSheet1Column = recordset1.getField(colName);
	        	                valueSheet2Column = recordset2.getField(colName);

	        	                if ((valueSheet1Column == null && valueSheet2Column == null) ||
	        	                        ((Tankdetails1 == null || Tankdetails1.trim().isEmpty()) && "0".equals(Tankdetails2)) ||
	        	                        ((Tankdetails2 == null || Tankdetails2.trim().isEmpty()) && "0".equals(Tankdetails1)) ||
	        	                        (valueSheet1Column != null && valueSheet1Column.equals(valueSheet2Column))) {
	        	                    // Values match, continue to next column
	        	                } else {
	        	                    // Values don't match, set flag to false and break the loop
	        	                    allColumnsMatch = false;
	        	                    break;
	        	                }
	        	            }

	        	            if (allColumnsMatch) {
	        	                uniquePassedTanknames.add(Tankdetails1); // Add passed Tank Name to set
	        	                
	        	                Extent_pass1(test2,
										"Values are matched for " + "Tank Name : " + Tankdetails1 + "||"
												+ " Column name is :" + columnName + "||" + " Master value: "
												+ valueSheet1Column + "||" + " Test value: " + valueSheet2Column);
	        	            } else {
	        	                uniqueFailedTanknames.add(Tankdetails1); // Add failed Tank Name to set
	        	                Extent_fail1(testDetail, "Values are not matched for Tank Name : " + Tankdetails1 + "||" +
	        	                        " Column name is :" + columnName + "||" +
	        	                        " Master value: " + valueSheet1Column + "||" +
	        	                        " Test value: " + valueSheet2Column);
	        	        		Extent_group_table1(test, Tankdetails1,columnName, valueSheet1Column, valueSheet2Column);

	        	            }

	        	            // Reset the flag for the next iteration
	        	            allColumnsMatch = true;
	        	        }
	        	    }

	        	    if (!matchFound) {
	        	        // No matching Tank Name found in the test dataset
	        	        missingTankNames.add(Tankdetails1); // Add missing Tank Name to set
	        	        Extent_fail1(testDetail, "Tank Name not found in test dataset: " + Tankdetails1);
	        	    }
	        	}

	        	// Print missing Tank Names as "NA"
	        	 
	        	int passRowCount = uniquePassedTanknames.size();
	        	int failRowCount = uniqueFailedTanknames.size();
	        	int NARowCount = missingTankNames.size();
	        	int totalcount = passRowCount + failRowCount + NARowCount;

	        	// Print unique passed Tank Names
	        	System.out.println("Unique passed Tank Names:");
	        	for (String tankName : uniquePassedTanknames) {
	        	    System.out.println(tankName);
	        	}
        	
	        	System.out.println("Total pass count is : "+passRowCount);
				System.out.println("Total fail count is : "+failRowCount);
				System.out.println("NA Tank count is : "+ NARowCount);
				System.out.println("Failed tank names are : "+ uniqueFailedTanknames);
				Extent_pass1(test, "Total row count is : "+totalcount);
				Extent_pass1(test, "Total pass count is : "+passRowCount);
				Extent_fail1(test, "Total fail count is : "+failRowCount);
				Extent_fail1(test, "NA Tank count is : "+ NARowCount);
				Extent_fail1(test, "Failed tank names are : "+ uniqueFailedTanknames);
							
	        	
	        	for (String missingTank : missingTankNames) {
	        	    System.out.println("Missing tank names are : " + missingTank);
	        	}

	            recordset1.close();
	            recordset2.close();
	            connection1.close();
	            connection2.close();
	        } catch (FilloException e) {
	            e.printStackTrace();
	        }
	    }


	    
//		public void uploadFileAutoIT(String filelocation) {
//		try {
//			String autoitscriptpath = System.getProperty("user.dir") + "\\" + "File_upload_selenium_webdriver.au3";
//
//			Runtime.getRuntime().exec("cmd.exe /c Start AutoIt3.exe " + autoitscriptpath + " \"" + filelocation + "\"");
//
//		} catch (Exception exp) {
//
//			Assert.fail();
//		}
//	}
//		public void clear(WebDriver driver, String xpaths) {
//		String[] values = splitXpath(xpaths);
//		try {
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//			WebElement webElement = driver.findElement(By.xpath(values[1]));
//			webElement.clear();
//			add(driver, "Clear on " + values[0], LogAs.PASSED, true, values[0]);
//		} catch (Exception e) {
//			add1(driver, "Unable to clear on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.PASSED, true,
//					values[0]);
//
//			Assert.fail();
//		}
//	}
//	
//	public void selectCheckBox(WebDriver driver, String xpaths) {
//		String[] values = splitXpath(xpaths);
//		try {
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//			WebElement element = driver.findElement(By.xpath(values[1]));
//			if (element.isSelected()) {
//			} else {
//				element.click();
//			}
//			add(driver, "Select the checkbox on " + values[0], LogAs.PASSED, true, values[0]);
//		} catch (Exception e) {
//			add1(driver, "Unable to select the checkbox on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED,true, values[0]);
//
//			Assert.fail();
//		}
//	}
////
//	public void jsClickByXPath(WebDriver driver, String Xpath) {
//		String[] values = splitXpath(Xpath);
//		try {
//			// waitForElement(driver,Xpath);
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//			WebElement element = driver.findElement(By.xpath(values[1]));
//			JavascriptExecutor executor = (JavascriptExecutor) driver;
//			executor.executeScript("arguments[0].click();", element);
//			add(driver, "Click on " + values[0], LogAs.PASSED, true, values[0]);
//		} catch (Exception e) {
//			add1(driver, "Unable to click on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
//					values[0]);
//			Assert.fail();
//		}
//	}
//      
//	public String clearAndType(WebDriver driver, String xpaths, String keysToSend) {
//		String[] values = splitXpath(xpaths);
//		try {
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//			WebDriverWait wait1 = new WebDriverWait(driver, 20);
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(values[1])));
//
//			WebElement webElement = driver.findElement(By.xpath(values[1]));
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript("arguments[0].value='';", webElement);
//			js.executeScript("arguments[0].click();", webElement);
//			// webElement.clear();
//			// webElement.sendKeys(keysToSend, Keys.ENTER);
//			// JavascriptExecutor jse = (JavascriptExecutor)driver;
//
//			wait(driver, "1");
//			js.executeScript("arguments[0].value=" + "\'" + keysToSend + "\'" + ";", webElement);
//			// js.executeScript("arguments[0].click();", webElement);
//			webElement.sendKeys(Keys.ENTER);
//
//			add(driver, "Clear and Type on " + values[0], keysToSend, true, values[0]);
//		} catch (Exception e) {
//			add1(driver, "Unable to type on " + values[0] + "- " + e.getLocalizedMessage(), keysToSend, true,values[0]);
//			Assert.fail();
//		}
//		return keysToSend;
//	}
//
//	public String actionType(WebDriver driver, String xpath, String keysToSend) {
//		String[] values = splitXpath(xpath);
//		try {
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//			WebElement webElement = driver.findElement(By.xpath(values[1]));
//			Actions action = new Actions(driver);
//			action.sendKeys(webElement, keysToSend).build().perform();
//			add(driver, "Type on " + values[0], keysToSend, true, values[0]);
//		} catch (StaleElementReferenceException e) {
//			add1(driver, "Unable to type on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,values[0]);
//			Assert.fail();
//		}
//		return keysToSend;
//	}
//	public void deSelectCheckBox(WebDriver driver, String xpaths) {
//		String[] values = splitXpath(xpaths);
//		try {
//			WebElement element = driver.findElement(By.xpath(values[1]));
//			if (element.isSelected()) {
//				element.click();
//			} else {
//			}
//			add(driver, "Deselect the checkbox on " + values[0], LogAs.PASSED, true, values[0]);
//		} catch (Exception e) {
//			add1(driver, "Unable to deselect the checkbox on " + values[0] + "- " + e.getLocalizedMessage(),LogAs.FAILED, true, values[0]);
//
//			Assert.fail();
//		}
//	}
//
//	

//		public String promptBox(WebDriver driver, String path, String inputData) {
//			String[] values = splitXpath(path);
//			try {
	//
//				WebElement element = driver.findElement(By.xpath(values[1]));
//				element.click();
//				Alert alert = driver.switchTo().alert();
//				driver.switchTo().alert().sendKeys(inputData);
//				String alertText = alert.getText();
//				alert.accept();
//				return alertText;
//			} catch (Exception e) {
//				return null;
//			}
//		}
	//
//		public void partialTextVerify(String sentence, String word) {
//			if (sentence.contains(word)) {
//			} else {
//			}
	//
//		}
	//
//		public void waitForElementNotpresent(WebDriver driver, String xpath) {
//			String[] values = splitXpath(xpath);
//			try {
//				WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
//				wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))));
//				add(driver, "Wait till the Element is visible " + values[0], LogAs.PASSED, true, values[0]);
//			} catch (Exception e) {
//				add1(driver, "Element is not visible " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
//						values[0]);
//			}
//		}
	//
//		public String enterUniquePhone(WebDriver driver, String path) {
//			String[] values = splitXpath(path);
//			WebElement webElement = driver.findElement(By.xpath(values[1]));
//			webElement.clear();
//			try {
//				Thread.sleep(500);
//				String phonenumber = new SimpleDateFormat("MMddHHmmss").format(Calendar.getInstance().getTime());
//				sendKeys(driver, path, phonenumber);
//				return phonenumber;
//			} catch (InterruptedException e) {
//				return null;
//			}
	//
//		}
	//
	////	
//		public void goForward(WebDriver driver) {
//			try {
//				driver.navigate().forward();
	//
//			} catch (Exception e) {
	//
//				Assert.fail();
//			}
//		}
	//
	//	
//		public String dynamicTypeName(WebDriver driver, String inputData, String webElementxPath) {
//			String[] values = splitXpath(webElementxPath);
//			WebElement webElement = driver.findElement(By.xpath(values[1]));
//			webElement.clear();
//			try {
//				Thread.sleep(500);
//				String currenttime = new SimpleDateFormat("HH_mmss").format(Calendar.getInstance().getTime());
//				String combinedValues = inputData + currenttime;
//				sendKeys(driver, webElementxPath, combinedValues);
//				return combinedValues;
//			} catch (Exception e) {
//				e.printStackTrace();
//				return null;
//			}
//		}
	//
//		public String sumOfTwoNumbers(String GetText1, String GetText2) {
//			try {
//				int string1 = Integer.parseInt(GetText1);
//				int string2 = Integer.parseInt(GetText2);
//				int sum1 = string1 + string2;
//				String sum = Integer.toString(sum1);
//				return sum;
//			} catch (Exception e) {
//				return null;
//			}
//		}
	//
	//
//		public void switchToFrame(WebDriver driver, String frameName) {
//			String[] values = splitXpath(frameName);
//			try {
//				WebElement element = driver.findElement(By.xpath(values[1]));
//				driver.switchTo().frame(element);
	//
//			} catch (NoSuchFrameException e) {
	//
//			}
//		}
	//
//		public void switchToDefaultFrame(WebDriver driver) {
//			try {
//				driver.switchTo().defaultContent();
//			} catch (Exception e) {
	//
//				Assert.fail();
//			}
//		}

	    public void clear(WebDriver driver, String xpaths) {
			String[] values = splitXpath(xpaths);
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				WebElement webElement = driver.findElement(By.xpath(values[1]));
				webElement.clear();
				add(driver, "Clear on " + values[0], LogAs.PASSED, true, values[0]);
			} catch (Exception e) {
				add1(driver, "Unable to clear on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.PASSED, true,
						values[0]);
				((JavascriptExecutor) driver).executeScript("lambda-status=failed");
				Assert.fail();
			}
		}
	    public static ITestResult convertToITestResult1(int value) {
	        ITestResult result = new CustomTestResult();
	        result.setStatus(value);
	        return result;
	    }
  
	    public static ITestResult convertToITestResult(ExtentTest extentTest) {
	        ITestResult result = new CustomTestResult();
	        result.setStatus(getTestNGStatus(extentTest.getStatus()));
	        return result;
	    }

	    private static int getTestNGStatus(Status extentStatus) {
	        switch (extentStatus) {
	            case PASS:
	                return ITestResult.SUCCESS;
	            case FAIL:
	                return ITestResult.FAILURE;
	            case SKIP:
	                return ITestResult.SKIP;
	            default:
	                return ITestResult.FAILURE; // Default to failure if status is unknown
	        }
	    }

	    // Custom implementation of ITestResult for simplicity
	    private static class CustomTestResult implements ITestResult {
	        private int status;

	        @Override
	        public int getStatus() {
	            return status;
	        }

	        public void setStatus(int status) {
	            this.status = status;
	        }

			@Override
			public Object getAttribute(String name) {
				// TODO Auto-generated method stub
				return name;
			}

			
			@Override
			public void setAttribute(String name, Object value) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Set<String> getAttributeNames() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object removeAttribute(String name) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int compareTo(ITestResult o) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ITestNGMethod getMethod() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object[] getParameters() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setParameters(Object[] parameters) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public IClass getTestClass() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Throwable getThrowable() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setThrowable(Throwable throwable) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public long getStartMillis() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long getEndMillis() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void setEndMillis(long millis) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isSuccess() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String getHost() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getInstance() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getTestName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getInstanceName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ITestContext getTestContext() {
				// TODO Auto-generated method stub
				return null;
			}

	        // Other methods from ITestResult interface can be implemented as needed
	        // For simplicity, we only include methods required for setting status in this example
	    }
	    
//	    ITestResult.SUCCESS for pass status, which has an integer value of 1.
//	    ITestResult.FAILURE for failure status, which has an integer value of 2.
//	    ITestResult.SKIP for skip status, which has an integer value of 3.

}
