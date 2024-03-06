package commonMethods;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
//import java.awt.Color;
//import java.awt.image.BufferedImage;
//import javax.imageio.ImageIO;
//import java.io.File;
//import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
//import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Directory;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class Keywords extends ATUReports implements Solvermindslocator {
	private static final String HMAC_SHA1_ALGORITHM = "HMACSHA1";

	public String ElementWait = Utils.getDataFromTestConfig("Wait Time");
	public int WaitElementSeconds = new Integer(ElementWait);
	public String Main_Window = "";
	public ArrayList<String> tabs;
	public WebElement fromElement;
	public ITestResult result;
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
	public String firstValue;
	public String secondValue;
	public boolean failureScreenshot = Directory.TestPassScreenshot;
	String DSW_GM = Utils.getDataFromTestData("TEMPANOS", "DSW_GM");
	private static String Stowagevalue1;
	private static String values;
	private static HashMap<String, String> map3 = new HashMap<>();
	private static HashMap<String, String> map1 = new HashMap<>();
	private static String columnName;
	public static ExtentTest test;

	private static Map<String, Integer> passCountMap = new HashMap<>();
	private static Map<String, Integer> failCountMap = new HashMap<>();
	private static Map<String, Integer> passCountMap1 = new HashMap<>();
	private static Map<String, Integer> failCountMap1 = new HashMap<>();
	private static Map<String, List<String>> failStowageValues = new HashMap<>();

	private static Map<String, List<String>> failStowageValues1 = new HashMap<>();
	
	
	Set<String> DGGroupFailuresStowageNumbers = new HashSet<>();
	Set<String> OOGGroupFailuresStowageNumbers = new HashSet<>();
	Set<String> CODGroupFailuresStowageNumbers = new HashSet<>();
	Set<String> ReeferGroupFailuresStowageNumbers = new HashSet<>();
	Set<String> SpecialGroupFailuresStowageNumbers = new HashSet<>();
	
	
	static List<String> elsePartData = new ArrayList<>();

	private static Map<String, Integer> totalMap = new HashMap<>();
	StringBuilder failedConditionsLog = new StringBuilder();

	List<String> DG_stow = new ArrayList<>();
	List<String> DG_Excepted = new ArrayList<>();
	List<String> DG_Actual = new ArrayList<>();

//	    List <String> DG_stow = new ArrayList<>();
//	    List <String> DG_Excepted = new ArrayList<>();
//	    List <String> DG_Actual = new ArrayList<>();

	List<String> Rfr_stow = new ArrayList<>();
	List<String> Rfr_Excepted = new ArrayList<>();
	List<String> Rfr_Actual = new ArrayList<>();
//	    String Rfr_Excepted;
//	    String Rfr_Actual;

	List<String> IsSpl_stow = new ArrayList<>();
	List<String> IsSpl_Excepted = new ArrayList<>();
	List<String> IsSpl_Actual = new ArrayList<>();
//	    String IsSpl_stow;
//	    String IsSpl_Excepted;
//	    String IsSpl_Actual;

	List<String> OOG_stow = new ArrayList<>();
	List<String> OOG_Excepted = new ArrayList<>();
	List<String> OOG_Actual = new ArrayList<>();
//	    String OOG_stow;
//	    String OOG_Excepted;
//	    String OOG_Actual;

	List<String> IsCOD_stow = new ArrayList<>();
	List<String> IsCOD_Excepted = new ArrayList<>();
	List<String> IsCOD_Actual = new ArrayList<>();
//	    String IsCOD_stow;
//	    String IsCOD_Excepted;
//	    String IsCOD_Actual;

	List<String> DGclass_stow = new ArrayList<>();
	List<String> DGclass_Excepted = new ArrayList<>();
	List<String> DGclass_Actual = new ArrayList<>();
//	    String DGclass_stow;
//	    String DGclass_Excepted;
//	    String DGclass_Actual;

	List<String> mulHaz_stow = new ArrayList<>();
	List<String> mulHaz_Excepted = new ArrayList<>();
	List<String> mulHaz_Actual = new ArrayList<>();
//	    String mulHaz_stow;
//	    String mulHaz_Excepted;
//	    String mulHaz_Actual;

	List<String> UNNO_stow = new ArrayList<>();
	List<String> UNNO_Excepted = new ArrayList<>();
	List<String> UNNO_Actual = new ArrayList<>();
//	    String UNNO_stow;
//	    String UNNO_Excepted;
//	    String UNNO_Actual;

	List<String> Variant_stow = new ArrayList<>();
	List<String> Variant_Excepted = new ArrayList<>();
	List<String> Variant_Actual = new ArrayList<>();
//	    String Variant_stow;
//		String Variant_Excepted;
//		String Variant_Actual;

	List<String> FlashPoint_stow = new ArrayList<>();
	List<String> FlashPoint_Excepted = new ArrayList<>();
	List<String> FlashPoint_Actual = new ArrayList<>();
//		String FlashPoint_stow;
//		String FlashPoint_Excepted;
//		String FlashPoint_Actual;

	List<String> DGLQ_stow = new ArrayList<>();
	List<String> DGLQ_Excepted = new ArrayList<>();
	List<String> DGLQ_Actual = new ArrayList<>();
//		String DGLQ_stow;
//		String DGLQ_Excepted;
//		String DGLQ_Actual;

	List<String> OOH_stow = new ArrayList<>();
	List<String> OOH_Excepted = new ArrayList<>();
	List<String> OOH_Actual = new ArrayList<>();
//		String OOH_stow;
//		String OOH_Excepted;
//		String OOH_Actual;

	List<String> OLF_stow = new ArrayList<>();
	List<String> OLF_Excepted = new ArrayList<>();
	List<String> OLF_Actual = new ArrayList<>();
//		String OLF_stow;
//		String OLF_Excepted;
//		String OLF_Actual;

	List<String> OLA_stow = new ArrayList<>();
	List<String> OLA_Excepted = new ArrayList<>();
	List<String> OLA_Actual = new ArrayList<>();
//		String OLA_stow;
//		String OLA_Excepted;
//		String OLA_Actual;

	List<String> OWP_stow = new ArrayList<>();
	List<String> OWP_Excepted = new ArrayList<>();
	List<String> OWP_Actual = new ArrayList<>();
//		String OWP_stow;
//		String OWP_Excepted;
//		String OWP_Actual;

	List<String> OWS_stow = new ArrayList<>();
	List<String> OWS_Excepted = new ArrayList<>();
	List<String> OWS_Actual = new ArrayList<>();
//		String OWS_stow;
//		String OWS_Excepted;
//		String OWS_Actual;

	List<String> COD_stow = new ArrayList<>();
	List<String> COD_Excepted = new ArrayList<>();
	List<String> COD_Actual = new ArrayList<>();
//		String COD_stow;
//		String COD_Excepted;
//		String COD_Actual;
	
	
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

	public String screenshot(WebDriver driver, String screenshotName) {
		String image_dest = null;
		try {

			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String currenttime = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
			image_dest = System.getProperty("user.dir").concat("\\snaptrude\\" + currenttime + screenshotName)
					.concat(".png");
			System.out.println(image_dest);
			File destination = new File(image_dest);
			FileUtils.copyFile(source, destination);
			return image_dest;
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			System.out.println("Exception while taking Screenshot" + e.getMessage());
			return e.getMessage();
		}
	}

	public String readimage(WebDriver driver, String filepath) {
		String image_res = null;
		try {
			File imgfile = new File(filepath);
			ITesseract instance = new Tesseract();
			instance.setDatapath("C:\\snapautomation\\Comdex\\testdata");
			// String result=instance.doOCR(imgfile);
			Rectangle rect = new Rectangle(10, 20, 150, 100);
			// String result = instance.doOCR(imgfile,rect);
			String result = instance.doOCR(imgfile, rect);
			System.out.println("Get for the text : " + result);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return image_res;

	}

	public String ScreenCompareImage(WebDriver driver, String img_filepath1, String img_filepath2) {
		String image_res = null;
		try {
			BufferedImage img1 = ImageIO.read(new File(img_filepath1));
			BufferedImage img2 = ImageIO.read(new File(img_filepath2));
			int w1 = img1.getWidth();
			int w2 = img2.getWidth();
			int h1 = img1.getHeight();
			int h2 = img2.getHeight();
			if ((w1 != w2) || (h1 != h2)) {
				System.out.println("Both images should have same dimwnsions");
			} else {
				long diff = 0;
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
				double avg = diff / (w1 * h1 * 3);
				double percentage = (avg / 255) * 100;
				System.out.println("Difference: " + percentage);
				if (percentage == 0.0) {
					image_res = "Pass";
				}

			}
		} catch (Exception e) {
			System.out.println("Exception while taking Screenshot" + e.getMessage());
			return e.getMessage();
		}
		return image_res;
	}

	public void wait(WebDriver driver, String inputData) {
		try {
			int time = Integer.parseInt(inputData);
			int seconds = time * 1000;
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			add1(driver, "Unable to wait ", LogAs.FAILED, true, "Wait");
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public void uploadFileAutoIT(String filelocation) {
		try {
			String autoitscriptpath = System.getProperty("user.dir") + "\\" + "File_upload_selenium_webdriver.au3";

			Runtime.getRuntime().exec("cmd.exe /c Start AutoIt3.exe " + autoitscriptpath + " \"" + filelocation + "\"");

		} catch (Exception exp) {

			Assert.fail();
		}
	}

	public void CopyAll() {
		Robot rb3;
		try {
			rb3 = new Robot();
			rb3.delay(1000);
			rb3.keyPress(KeyEvent.VK_CONTROL);
			rb3.keyPress(KeyEvent.VK_A);
			rb3.keyRelease(KeyEvent.VK_A);
			rb3.keyRelease(KeyEvent.VK_CONTROL);
			rb3.delay(1000);
		} catch (Exception e) {
			System.out.println("robot class its not working");
		}
	}

	public static int getXCoordOffsetFromCentre(WebDriver driver, Point centre, int x, int y, int z) {

		Integer intX = Integer.valueOf(x);
		Integer intY = Integer.valueOf(y);
		Integer intZ = Integer.valueOf(z);
		int firstValue = 0;

		try {

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			Object de = js1.executeScript(

					"const v3= new store.exposed.BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
							+ "		return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",

					// "const v3= new BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
					// + " return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",
					intX, intY, intZ);
			System.out.println(de);

			String str = de.toString();
			String[] first = str.split(",");
			firstValue = Integer.parseInt(first[0].replace("[", "").trim()) - centre.x;
			System.out.println(firstValue);

		}

		catch (Exception e1) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
		return firstValue;

	}

	public static int getYCoordOffsetFromCentre(WebDriver driver, Point centre, int x, int y, int z) {

		Integer intX = Integer.valueOf(x);
		Integer intY = Integer.valueOf(y);
		Integer intZ = Integer.valueOf(z);
		int secondvalue = 0;

		try {

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			Object de = js1.executeScript(

					"const v3= new store.exposed.BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
							+ "		return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",

					// "const v3= new BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
					// + " return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",
					intX, intY, intZ);
			System.out.println(de);

			String str = de.toString();
			String[] first = str.split(",");
			secondvalue = Integer.parseInt(first[1].replace("]", "").trim()) - centre.y;
			System.out.println(secondvalue);

		}

		catch (Exception e1) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
		return secondvalue;

	}

	public static void dropdown(WebDriver driver, String xpath) {

		String[] values = splitXpath(xpath);

		List<WebElement> li = driver.findElements(By.xpath(values[1]));
		try {
			for (int i = 0; i < li.size(); i++) {
				System.out.println(li.get(i).getText());
				Thread.sleep(2000);
				if (li.get(i).getText().contains("Apple iPhone 12")) {

					li.get(i).click();
					break;
				}
			}
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
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
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			add1(driver, "Element Not Found - " + values[0] + "- " + e.getLocalizedMessage() + e, LogAs.FAILED, true,
					values[0]);
			Assert.fail();
		}
	}

	public void waitForElement1(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			// int WaitElementSeconds1 = new Integer(ElementWait);
			driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 600);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(values[1])));
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(values[1])));
			add(driver, "Wait for the Element " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			add1(driver, "Element Not Found - " + values[0] + "- " + e.getLocalizedMessage() + e, LogAs.FAILED, true,
					values[0]);
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
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			add1(driver, "Element Not Found - " + values[0] + "- " + e.getLocalizedMessage() + e, LogAs.FAILED, true,
					values[0]);
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
			add1(driver, "Element not visible " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
					values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}

	}

	public void waitForElement5(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			// int WaitElementSeconds1 = new Integer(ElementWait);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(values[1])));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(values[1])));
			add(driver, "Wait for the Element " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			add1(driver, "Element Not Found - " + values[0] + "- " + e.getLocalizedMessage() + e, LogAs.FAILED, true,
					values[0]);
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
			// JavascriptExecutor executor = (JavascriptExecutor) driver;
			// executor.executeScript("arguments[0].setAttribute('style', 'background:
			// yellow; border: 2px solid red;');",element);
			// executor.executeScript("arguments[0].click();", element);

			add(driver, "Click on " + values[0], LogAs.PASSED, true, values[0]);

			Extent_pass(driver, "Click on " + values[0], test);

			// Object de = executor.executeScript("return
			// store.scene.getEngine().getFps()");
			// System.out.println("Duration of action performed : "+de);

			// this.getfps(driver);

		} catch (Exception e) {
			e.printStackTrace();
			add1(driver, "Unable to click on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
					values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Extent_fail(driver, "Unable to click on " + values[0], test);
			Assert.fail();
		}
		// String[] values = splitXpath(path);
		// try {
		//
		// WebDriverWait wait1 = new WebDriverWait(driver, 30);
		// wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(values[1])));
		// WebElement element = driver.findElement(By.xpath(values[1]));
		// JavascriptExecutor executor = (JavascriptExecutor) driver;
		// executor.executeScript("arguments[0].click();", element);
		//
		// WebElement webElement = driver.findElement(By.xpath(values[1]));
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].setAttribute('style', 'background: yellow;
		// border: 2px solid red;');",
		// webElement);
		// // webElement.click();
		// jsClickByXPath(driver, path);
		// System.out.println(values[0] + " clicked");
		// //add(driver, "Click on " + values[0], LogAs.PASSED, true, values[0]);
		// } catch (Exception e) {
		// System.out.println(" Exception " + e);
		// add1(driver, "Unable to click on " + values[0], LogAs.FAILED, true,
		// values[0]);
		// Assert.fail();
		// }
		// //String[] values = splitXpath(path);
		// /*
		// * try { // waitForElement(driver,Xpath); WebElement element =
		// * driver.findElement(By.xpath(values[1])); JavascriptExecutor executor =
		// * (JavascriptExecutor) driver; executor.
		// * executeScript("arguments[0].setAttribute('style', 'background: yellow;
		// border: 2px solid red;');"
		// * , element); executor.executeScript("arguments[0].click();", element);
		// * add(driver, "Click on " + values[0], LogAs.PASSED, true, values[0]); }
		// catch
		// * (Exception e) { add1(driver, "Unable to click on " + values[0],
		// LogAs.FAILED,
		// * true, values[0]); Assert.fail(); }
		// */
	}

	public void click1(WebDriver driver, String path, int input) {
		String[] values = splitXpath(path);
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement webElement = driver.findElement(By.xpath(values[input]));
			System.out.println(webElement);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
					webElement);
			webElement.click();
			System.out.println(values[0] + " clicked");
			add(driver, "Click1 on " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			System.out.println(" Exception " + e);
			add1(driver, "Unable to click1 on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
					values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public void switchToActiveElement(WebDriver driver) {
		try {
			driver.switchTo().activeElement();
		} catch (Exception e) {
		}
	}

	public void clickByClassName(WebDriver driver, String className) {
		String[] values = splitXpath(className);
		try {
			WebElement webElement = driver.findElement(By.className(values[1]));
			webElement.click();
			// add(driver, "Click on " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			// add1(driver, "Unable to click on " + values[0], LogAs.FAILED, true,
			// values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public void clickWithoutFail(WebDriver driver, String path) {
		String[] values = splitXpath(path);
		WebElement webElement = driver.findElement(By.xpath(values[1]));
		webElement.click();
		add(driver, "Click on " + values[0], LogAs.PASSED, true, values[0]);

	}

	public void jsClickByXPath(WebDriver driver, String Xpath) {
		String[] values = splitXpath(Xpath);
		try {
			// waitForElement(driver,Xpath);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement element = driver.findElement(By.xpath(values[1]));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			add(driver, "Click on " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Unable to click on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
					values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public void waitForTexttopresent(WebDriver driver, String xpath, String text) {
		String[] values = splitXpath(xpath);
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// int WaitElementSeconds1 = new Integer(ElementWait);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(values[1])));
			wait.until(ExpectedConditions.textToBePresentInElement(By.xpath(values[1]), text));
			add(driver, "Wait for the Text " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Text Not Found - " + values[0] + "- " + e.getLocalizedMessage() + e, LogAs.FAILED, true,
					values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public String clearAndType(WebDriver driver, String xpaths, String keysToSend) {
		String[] values = splitXpath(xpaths);
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebDriverWait wait1 = new WebDriverWait(driver, 20);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(values[1])));

			WebElement webElement = driver.findElement(By.xpath(values[1]));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value='';", webElement);
			js.executeScript("arguments[0].click();", webElement);
			// webElement.clear();
			// webElement.sendKeys(keysToSend, Keys.ENTER);
			// JavascriptExecutor jse = (JavascriptExecutor)driver;

			wait(driver, "1");
			js.executeScript("arguments[0].value=" + "\'" + keysToSend + "\'" + ";", webElement);
			// js.executeScript("arguments[0].click();", webElement);
			webElement.sendKeys(Keys.ENTER);

			add(driver, "Clear and Type on " + values[0], keysToSend, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Unable to type on " + values[0] + "- " + e.getLocalizedMessage(), keysToSend, true,
					values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
		return keysToSend;
	}

	public String actionType(WebDriver driver, String xpath, String keysToSend) {
		String[] values = splitXpath(xpath);
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			Actions action = new Actions(driver);
			action.sendKeys(webElement, keysToSend).build().perform();
			add(driver, "Type on " + values[0], keysToSend, true, values[0]);
		} catch (StaleElementReferenceException e) {
			add1(driver, "Unable to type on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
					values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
		return keysToSend;
	}

	public void actionClick(WebDriver driver, String Xpath) {
		String[] values = splitXpath(Xpath);
		WebElement webElement = driver.findElement(By.xpath(values[1]));
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Actions action = new Actions(driver);
			action.click(webElement).build().perform();
			add(driver, "Click on " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Unable to click on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
					values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
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
			add1(driver, "Unable to click on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
					values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
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
			add1(driver, "Unable to type on " + values[0] + "- " + e.getLocalizedMessage(), keysToSend, true,
					values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
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
			add1(driver, "Unable to type on " + values[0] + "- " + e.getLocalizedMessage(), keysToSend, true,
					values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
		return keysToSend;

	}

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

	public void webelementfunction(WebDriver driver, String xpaths) {
		String[] values = splitXpath(xpaths);
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// WebElement webElement = driver.findElement(By.xpath(values[1]));
			// webElement.clear();
			List<WebElement> bakeries = driver.findElements(By.xpath(values[1]));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
					bakeries);
			System.out.println(bakeries.size());
			add(driver, "Clear on " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Unable to clear on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.PASSED, true,
					values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}

	}

	public void selectCheckBox(WebDriver driver, String xpaths) {
		String[] values = splitXpath(xpaths);
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement element = driver.findElement(By.xpath(values[1]));
			if (element.isSelected()) {
			} else {
				element.click();
			}
			add(driver, "Select the checkbox on " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Unable to select the checkbox on " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED,
					true, values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public void deSelectCheckBox(WebDriver driver, String xpaths) {
		String[] values = splitXpath(xpaths);
		try {
			WebElement element = driver.findElement(By.xpath(values[1]));
			if (element.isSelected()) {
				element.click();
			} else {
			}
			add(driver, "Deselect the checkbox on " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Unable to deselect the checkbox on " + values[0] + "- " + e.getLocalizedMessage(),
					LogAs.FAILED, true, values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public void selectByIndex(WebDriver driver, String xpaths, String inputData) {
		String[] values = splitXpath(xpaths);
		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			Integer index = new Integer(inputData);
			Select selectBox = new Select(webElement);
			selectBox.selectByIndex(index);
			add(driver, "Select the Dropdown by Index " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Unable to select the Dropdown by Index " + values[0] + "- " + e.getLocalizedMessage(),
					inputData, true, values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public void selectByText(WebDriver driver, String xpaths, String inputData) {
		String[] values = splitXpath(xpaths);
		try {
			Select selectBox = new Select(driver.findElement(By.xpath(values[1])));
			selectBox.selectByVisibleText(inputData);
			add(driver, "Select the Dropdown by text " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Unable to select the Dropdown by text " + values[0] + "- " + e.getLocalizedMessage(),
					inputData, true, values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public void selectByValue(WebDriver driver, String xpaths, String inputData) {
		String[] values = splitXpath(xpaths);
		try {

			WebElement webElement = driver.findElement(By.xpath(values[1]));
			Select selectBox = new Select(webElement);
			selectBox.selectByValue(inputData);
			add(driver, "Select the Dropdown by Value " + values[0], inputData, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Unable to select the Dropdown by Value " + values[0] + "- " + e.getLocalizedMessage(),
					inputData, true, values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public void checkTwoString(WebDriver driver, String GetText1, String GetText2) {
		try {
			if (GetText1.equalsIgnoreCase(GetText2)) {
				add(driver, "The Value1 " + GetText1 + " and Value2 " + GetText2 + " are matched", LogAs.PASSED, true,
						GetText1);
			} else if (GetText1.equals(null)) {
				add(driver, "The Value1 " + GetText1 + " and Value2 " + GetText2 + " are not matched", LogAs.FAILED,
						true, GetText1);
				Assert.fail();
			} else if (GetText2.equals(null)) {
				add(driver, "The Value1 " + GetText1 + " and Value2 " + GetText2 + " are not matched", LogAs.FAILED,
						true, GetText1);
				Assert.fail();
			} else {
				add(driver, "The Value1 " + GetText1 + " and Value2 " + GetText2 + " are not matched", LogAs.FAILED,
						true, GetText1);
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			add1(driver, "The Value1 " + GetText1 + " and Value2 " + GetText2 + " are not matched", LogAs.FAILED, true,
					GetText1);
			Assert.fail();
		}
	}

	public void checkPartialText(WebDriver driver, String GetText1, String GetText2) {
		try {
			if ((GetText1.contains(GetText2)) || (GetText2.contains(GetText1))) {
				add(driver, "The Value1 " + GetText1 + " and Value2 " + GetText2 + " are matched", LogAs.PASSED, true,
						GetText1);
			} else {
				add(driver, "The Value1 " + GetText1 + " and Value2 " + GetText2 + " are not matched", LogAs.FAILED,
						true, GetText1);
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			add1(driver, "The Value1 " + GetText1 + " and Value2 " + GetText2 + " are not matched", LogAs.FAILED, true,
					GetText1);
			Assert.fail();
		}
	}

	public void close(WebDriver driver) {
		try {
			driver.close();
			add(driver, "Application is closed", LogAs.PASSED, true, "Not Req");
		} catch (Exception e) {
			add1(driver, "Unable to close the application ", LogAs.FAILED, true,
					"Not Req" + "- " + e.getLocalizedMessage());
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public String getText(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			String text = webElement.getText();
			add(driver, "The value ' " + text + " ' is retrieved from the element ' " + values[0] + "'", LogAs.PASSED,
					true, values[0]);
			return text;

		} catch (Exception e) {
			add1(driver, "Unable to retrieve the text " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED,
					true, values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
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
		add(driver, "The value ' " + text + " ' is retrieved for the element ' " + values[0] + "'", LogAs.PASSED, true,
				values[0]);
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
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
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
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public boolean verifyElementIsPresent(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			WebElement element = driver.findElement(By.xpath(values[1]));
			element.isDisplayed();
			add(driver, "Element '" + values[0] + "' is verified ", LogAs.PASSED, true, values[0]);
			return true;
		} catch (NoSuchElementException e) {
			add1(driver, "Element is Not Present " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
					values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
			return true;
		}
	}

	public void verifyElementHasText(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			String text = driver.findElement(By.xpath(values[1])).getText();
			if (!text.equals("")) {
				add(driver, "Element '" + values[0] + "' has text " + text, LogAs.PASSED, true, values[0]);
			} else {
				add1(driver, "No text on the element " + values[0], LogAs.FAILED, true, values[0]);
				((JavascriptExecutor) driver).executeScript("lambda-status=failed");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			add1(driver, "Element is Not Present " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
					values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public boolean isDisplayed(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
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
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
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

	public void scrollUsingElement(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			WebElement element = driver.findElement(By.xpath(values[1]));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			add(driver, "Scrolled to " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Unable to scroll " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
					values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public void goBack(WebDriver driver) {

		try {
			driver.navigate().back();
			add(driver, "Go Back", LogAs.PASSED, true, "goback");

		} catch (Exception e) {
			add(driver, "Unable to Go Back", LogAs.FAILED, true, "goback");
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();

		}
	}

	public void keyBoardEvent(int eventNumber) {
		try {

			Thread.sleep(1000);

			Runtime.getRuntime().exec(

					"cmd /C adb shell input keyevent " + eventNumber);

			Thread.sleep(3000);

		} catch (Throwable t) {

			t.printStackTrace();

		}
	}

	public void team_Arrow(WebDriver driver, String value) {

		driver.findElement(By.xpath("//td[text()='" + value + "@trackd.com']/..//div[@class='action-icon-container']"))
				.click();

	}

	public void type(WebDriver driver, int value) {

		driver.findElement(By.xpath("(//div[@class='sc-jcFjpl fOTaNF'])[" + value + "]")).click();
	}

	public void manufacturer(WebDriver driver, int value, String value1) {

		driver.findElement(By.xpath("(//*[text()='₹']//following::input[1])[" + value + "]")).sendKeys(value1);

		add(driver, "type on " + "manufacture", LogAs.PASSED, true, "");
	}

	public void Description(WebDriver driver, int value, String value1) {
		// *[@placeholder='Description']
		driver.findElement(By.xpath("(//*[text()='₹']//following::input[2])[" + value + "]")).sendKeys(value1);
		add(driver, "type on  " + "Description", LogAs.PASSED, true, "");
	}

	public void cost(WebDriver driver, int value, String value1) {

		driver.findElement(By.xpath("(//*[text()='₹']//preceding::input[1])[" + value + "]")).sendKeys(value1);
		add(driver, "type on  " + "Cost", LogAs.PASSED, true, "");
	}

	public void vendor(WebDriver driver, int value, String value1) {

		driver.findElement(By.xpath("(//*[text()='₹']//following::input[1])[" + value + "]")).sendKeys(value1);
		add(driver, "type on  " + "vendor", LogAs.PASSED, true, "");

	}

	public void familyname(WebDriver driver, int value, String value1) {

		driver.findElement(By.xpath("(//*[@placeholder='Furniture Family'])[" + value + "]")).sendKeys(value1);
		add(driver, "type on  " + "Furniture Family", LogAs.PASSED, true, "");
	}

	public void waitTillVisibilityElement(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);

		try {
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(webElement));
			add(driver, "Waited till the element is visible", LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add(driver, "Unable to wait till an element is visible", LogAs.FAILED, true,
					values[0] + "-" + e.getLocalizedMessage());
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();

		}
	}

	public void waitTillElementIsClickable(WebDriver driver, String xpath) {
		try {
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			String[] values = splitXpath(xpath);
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
			add(driver, "Waited till the element is clickable", LogAs.PASSED, true, "Scroll down");
		} catch (Exception e) {
			add(driver, "Unable to wait till an element is clickable", LogAs.FAILED, true,
					"Scroll down" + "- " + e.getLocalizedMessage());
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();

		}
	}

	public void IsElementEnabled(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			webElement.isEnabled();
			add(driver, "Element is enabled" + values[0], LogAs.PASSED, true, values[0]);

		} catch (NoSuchElementException e) {
			add(driver, "Element is not enabled", LogAs.FAILED, true, values[0]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public int getRandomNum(WebDriver driver, int upperlimit) {
		List<Integer> randomZeroToSeven = new ArrayList<>();
		for (int i = 1; i <= upperlimit; i++) {
			randomZeroToSeven.add(i);
		}
		Collections.shuffle(randomZeroToSeven);

		return randomZeroToSeven.get(0);

	}

	public void deSelectByIndex(WebDriver driver, String xpath, String inputData) {
		String[] values = splitXpath(xpath);
		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			Integer index = new Integer(inputData);
			Select selectBox = new Select(webElement);
			selectBox.deselectByIndex(index);
			add(driver, "Deselect the dropdown by index " + values[0], LogAs.PASSED, true, values[1]);
		} catch (Exception e) {
			add1(driver, "Unable to deselect the dropdown by index" + values[0], LogAs.FAILED, true, values[1]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public void deSelectByValue(WebDriver driver, String xpath, String inputData) {
		String[] values = splitXpath(xpath);
		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			Select selectBox = new Select(webElement);
			selectBox.deselectByValue(inputData);
			add(driver, "Deselect the dropdown by index " + values[0], LogAs.PASSED, true, values[1]);
		} catch (Exception e) {
			add(driver, "Unable to deselect the dropdown by index" + values[0], LogAs.FAILED, true, values[1]);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public void getWindow(WebDriver driver, String path) {
		try {
			waitTime(driver, "5");
			Main_Window = driver.getWindowHandle();
			System.out.println("Main_Window:" + Main_Window);
			String[] values = splitXpath(path);
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			webElement.click();
			Thread.sleep(500);
			ArrayList<String> allWindows = new ArrayList<String>(driver.getWindowHandles());
			System.out.println("2nd Window:" + allWindows.get(1));
			driver.switchTo().window(allWindows.get(1));
		} catch (InterruptedException e) {
		}
	}

	public void switchWindow(WebDriver driver) {
		try {
			driver.switchTo().window(Main_Window);
		} catch (Exception e) {
		}
	}

	public void switchDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void getAutoit(String exePath) {
		try {
			Runtime.getRuntime().exec(exePath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void dragElement(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			fromElement = webElement;
			add(driver, "Drag an element " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Unable to drag an element " + values[0], LogAs.FAILED, true, values[0]);
		}

	}

	public void dropElement(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			Actions action = new Actions(driver);
			Action dragDrop = action.dragAndDrop(fromElement, webElement).build();
			dragDrop.perform();
			add(driver, "Drop an element " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Unable to drag an element " + values[0], LogAs.FAILED, true, values[0]);
		}
	}

	public boolean isElementSelected(WebDriver driver, String xpaths) {
		String[] values = splitXpath(xpaths);
		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			webElement.isSelected();
			add(driver, "Verified Element is selected " + values[0], LogAs.PASSED, true, values[0]);
			return true;
		} catch (NoSuchElementException e) {

			add1(driver, "Element is not selected " + values[0], LogAs.FAILED, true, values[0]);
			return false;
		}
	}

	public void inVisibilityElement(WebDriver driver, String NormalXpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(NormalXpath)));
		} catch (Exception e) {
		}
	}

	public void IstextPresent(WebDriver driver, String inputData) {
		if (driver.getPageSource().contains(inputData)) {
			add(driver, "Text is Present " + inputData, LogAs.PASSED, true, inputData);
		} else {
			add1(driver, "Text is not Present " + inputData, LogAs.FAILED, true, inputData);
		}
	}

	public void waitTillTextIsLoaded(WebDriver driver, String xpath, String inputData) {
		String[] values = splitXpath(xpath);
		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			WebDriverWait waits = new WebDriverWait(driver, WaitElementSeconds);
			waits.until(ExpectedConditions.textToBePresentInElement(webElement, inputData));
			add(driver, "Waited till the text " + inputData + " is loaded", LogAs.PASSED, true, inputData);
		} catch (Exception e) {
			add1(driver, "Unable to Wait till the text " + inputData + "- " + e.getLocalizedMessage() + " is loaded",
					LogAs.FAILED, true, inputData);
		}
	}

	public void verifyTextIsNotPresent(WebDriver driver, String NormalXpath, String inputData) {
		try {
			WebDriverWait waits = new WebDriverWait(driver, WaitElementSeconds);
			waits.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(NormalXpath), inputData));
			add(driver, "Text is not present" + inputData, LogAs.PASSED, true, inputData);
		} catch (Exception e) {
			add1(driver, "Text is present" + inputData + "- " + e.getLocalizedMessage(), LogAs.FAILED, true, inputData);
		}

	}

	public void isElementClickable(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			WebDriverWait waits = new WebDriverWait(driver, WaitElementSeconds);
			waits.until(ExpectedConditions.elementToBeClickable(webElement));
			add(driver, "Element is clickable " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Element is not clickable " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
					values[0]);
		}
	}

	public void isElementSelectable(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			WebDriverWait waits = new WebDriverWait(driver, WaitElementSeconds);
			waits.until(ExpectedConditions.elementToBeSelected(webElement));
			add(driver, "Element is selectable " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Element is not selectable " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
					values[0]);
		}
	}

	public void waitUntilVisibilityOfElement(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
			wait.until(ExpectedConditions.visibilityOf(webElement));
			add(driver, "Wait till the Element is visible " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Element is not visible " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
					values[0]);
		}

	}

	public void waitForElementNotpresent(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		try {
			WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
			wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))));
			add(driver, "Wait till the Element is visible " + values[0], LogAs.PASSED, true, values[0]);
		} catch (Exception e) {
			add1(driver, "Element is not visible " + values[0] + "- " + e.getLocalizedMessage(), LogAs.FAILED, true,
					values[0]);
		}
	}

	public String dynamicSendkeys(WebDriver driver, String inputData, String xpath) {
		String[] values = splitXpath(xpath);
		WebElement webElement = driver.findElement(By.xpath(values[1]));
		webElement.clear();
		try {
			Thread.sleep(500);
			// String currenttime = new
			// SimpleDateFormat("E_yyyyMMddHHmmssa").format(Calendar.getInstance().getTime());
			String currenttime = new SimpleDateFormat("HHmmssa").format(Calendar.getInstance().getTime());
			String originalValue = inputData;
			String combinedValues = currenttime + originalValue;
			sendKeys(driver, xpath, combinedValues);
			return combinedValues;
		} catch (InterruptedException e) {

			return null;
		}

	}

	public void partialTextVerify(String sentence, String word) {
		if (sentence.contains(word)) {
		} else {
		}

	}

	public String enterUniquePhone(WebDriver driver, String path) {
		String[] values = splitXpath(path);
		WebElement webElement = driver.findElement(By.xpath(values[1]));
		webElement.clear();
		try {
			Thread.sleep(500);
			String phonenumber = new SimpleDateFormat("MMddHHmmss").format(Calendar.getInstance().getTime());
			sendKeys(driver, path, phonenumber);
			return phonenumber;
		} catch (InterruptedException e) {
			return null;
		}

	}

	public String dynamicTypeName(WebDriver driver, String inputData, String webElementxPath) {
		String[] values = splitXpath(webElementxPath);
		WebElement webElement = driver.findElement(By.xpath(values[1]));
		webElement.clear();
		try {
			Thread.sleep(500);
			String currenttime = new SimpleDateFormat("HH_mmss").format(Calendar.getInstance().getTime());
			String combinedValues = inputData + currenttime;
			sendKeys(driver, webElementxPath, combinedValues);
			return combinedValues;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String sumOfTwoNumbers(String GetText1, String GetText2) {
		try {
			int string1 = Integer.parseInt(GetText1);
			int string2 = Integer.parseInt(GetText2);
			int sum1 = string1 + string2;
			String sum = Integer.toString(sum1);
			return sum;
		} catch (Exception e) {
			return null;
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

	public void uploadFileRobot(String fileLocation) {
		try {
			StringSelection stringSelection = new StringSelection(fileLocation);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (Exception exp) {
			Assert.fail();
		}
	}

	public void goForward(WebDriver driver) {
		try {
			driver.navigate().forward();

		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
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
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public String alertAccept(WebDriver driver, String path) {
		String[] values = splitXpath(path);

		try {
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			webElement.click();
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			alert.accept();
			return alertText;
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
			return null;
		}
	}

	public void dismissAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public String promptBox(WebDriver driver, String path, String inputData) {
		String[] values = splitXpath(path);
		try {

			WebElement element = driver.findElement(By.xpath(values[1]));
			element.click();
			Alert alert = driver.switchTo().alert();
			driver.switchTo().alert().sendKeys(inputData);
			String alertText = alert.getText();
			alert.accept();
			return alertText;
		} catch (Exception e) {
			return null;
		}
	}

	public void switchToFrame(WebDriver driver, String frameName) {
		String[] values = splitXpath(frameName);
		try {
			WebElement element = driver.findElement(By.xpath(values[1]));
			driver.switchTo().frame(element);

		} catch (NoSuchFrameException e) {

		}
	}

	public void switchToDefaultFrame(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public static void uniqueObjects(WebDriver driver) {

		try {

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("checkBox = store.exposed.getBabylonGUIElementByName(\"arrayCheckbox\");\r\n"
					+ "if (checkBox){\r\n"
					+ "    const currentValue = store.arrayFunctionGlobalVariables.uniqueObjects;\r\n"
					+ "    const newValue = !currentValue;\r\n" + "\r\n" + "    checkBox.isChecked = newValue;\r\n"
					+ "    checkBox.onIsCheckedChangedObservable.notifyObservers(newValue);\r\n" + "}");

		}

		catch (Exception e1) {
			System.out.println(e1);
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();

		}

	}

	public static void zoomin(WebDriver driver, int n, int x, int z, int y) {

		try {
			WebElement canvas = driver.findElement(By.id("canvas"));
			System.out.println(canvas.getLocation());
			Point leftTop = canvas.getLocation();
			Dimension size = canvas.getSize();
			System.out.println(size);
			int centreX = leftTop.x + size.width / 2;
			int centreY = leftTop.y + size.height / 2;
			Point centre = new Point(centreX, centreY);
			System.out.println(centre);
			Actions builder = new Actions(driver);

			driver.findElement(By.xpath("//*[@alt='zoomIn']")).click();

			for (int i = 0; i < n; i++) {
				builder.moveToElement(canvas, getXCoordOffsetFromCentre(driver, centre, x, z, y),
						getYCoordOffsetFromCentre(driver, centre, x, z, y)).click().perform();

			}

			driver.findElement(By.xpath("//*[@alt='zoomIn']")).click();

		}

		catch (Exception e) {
			System.out.println("Can able to view zoomout canvas");
		}

	}

	public static void zoomout(WebDriver driver, int n, int x, int z, int y) {

		try {
			WebElement canvas = driver.findElement(By.id("canvas"));
			System.out.println(canvas.getLocation());
			Point leftTop = canvas.getLocation();
			Dimension size = canvas.getSize();
			System.out.println(size);
			int centreX = leftTop.x + size.width / 2;
			int centreY = leftTop.y + size.height / 2;
			Point centre = new Point(centreX, centreY);
			System.out.println(centre);
			Actions builder = new Actions(driver);

			driver.findElement(By.xpath("//*[@alt='zoomOut']")).click();

			for (int i = 0; i < n; i++) {
				builder.moveToElement(canvas, getXCoordOffsetFromCentre(driver, centre, x, z, y),
						getYCoordOffsetFromCentre(driver, centre, x, z, y)).click().perform();

			}

			driver.findElement(By.xpath("//*[@alt='zoomOut']")).click();

		}

		catch (Exception e) {
			System.out.println("Can able to view zoomout canvas");

		}

	}

	public void escape(WebDriver driver) {

		Robot key;
		try {
			key = new Robot();

			key.keyPress(KeyEvent.VK_ESCAPE);
			key.delay(500);
			key.keyRelease(KeyEvent.VK_ESCAPE);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static int getXCoordOffsetFromCentredouble(WebDriver driver, Point centre, double x, double y, double z) {

		Double doubleX = Double.valueOf(x);
		Double doubleY = Double.valueOf(y);
		Double doubleZ = Double.valueOf(z);
		int firstValue = 0;

		try {

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			Object de = js1.executeScript(

					"const v3= new store.exposed.BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
							+ "		return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",

					// "const v3= new BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
					// + " return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",
					doubleX, doubleY, doubleZ);
			System.out.println(de);

			String str = de.toString();
			String[] first = str.split(",");
			firstValue = Integer.parseInt(first[0].replace("[", "").trim()) - centre.x;
			System.out.println(firstValue);

		}

		catch (Exception e1) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
		return firstValue;

	}

	public static int getYCoordOffsetFromCentredouble(WebDriver driver, Point centre, double x, double y, double z) {

		Double doubleX = Double.valueOf(x);
		Double doubleY = Double.valueOf(y);
		Double doubleZ = Double.valueOf(z);
		int secondvalue = 0;

		try {

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			Object de = js1.executeScript(

					"const v3= new store.exposed.BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
							+ "		return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",

					// "const v3= new BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
					// + " return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",
					doubleX, doubleY, doubleZ);
			System.out.println(de);

			String str = de.toString();
			String[] first = str.split(",");
			secondvalue = Integer.parseInt(first[1].replace("]", "").trim()) - centre.y;
			System.out.println(secondvalue);

		}

		catch (Exception e1) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
		return secondvalue;

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
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail(inputData);
		} else {
			driver.navigate().to(inputData);
			add(driver, " Navigated to " + inputData, LogAs.PASSED, true, inputData);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

	}

	public void highLightElement(WebDriver driver, String xpath) {
		String[] values = splitXpath(xpath);
		WebElement webElement = driver.findElement(By.xpath(values[1]));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement,
				"color: red; border: 3px solid red;");
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "");
	}

	/*
	 * public void newTab(WebDriver driver) { try { Robot r = new Robot();
	 * r.keyPress(KeyEvent.VK_CONTROL); r.keyPress(KeyEvent.VK_T);
	 * if(Config.browserName.equalsIgnoreCase("firefox")) { ArrayList<String> tabs =
	 * new ArrayList<String> (driver.get()); driver.switchTo().window(tabs.get(1));
	 * } else if(Config.browserName.equalsIgnoreCase("chrome")) {
	 * System.out.println("askjdfkdlaj"); ArrayList<String> tabs = new
	 * ArrayList<String> (driver.getWindowHandles());
	 * System.out.println("321356132"); driver.switchTo().window(tabs.get(0));
	 * System.out.println("!@$@#%"); driver.get("http://facebook.com");
	 * System.out.println("{{{{{{{{{{"); } } catch (Exception e) { // TODO: handle
	 * exception }
	 *
	 * }
	 */
	public void windowhandlesframe(WebDriver driver, int values) {

		try {
			ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
			// Set<String>windowhandles1=driver.getWindowHandles();
			System.out.println(tabs1);
			Thread.sleep(5000);
			// List<String>list=new ArrayList<>(windowhandles1)
			driver.switchTo().window(tabs1.get(values));
			System.out.println(driver.getCurrentUrl());
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	public void windowhandles(WebDriver driver) {

		Set<String> windowhandles1 = driver.getWindowHandles();
		System.out.println(windowhandles1);
		List<String> list = new ArrayList<>(windowhandles1);
		driver.switchTo().window(list.get(1));
		System.out.println(driver.getCurrentUrl());

	}

	public void Arrow_Click(WebDriver driver, String xpaths, String name) {
		String[] values = splitXpath(xpaths);

		List<WebElement> titletext1 = driver.findElements(By.xpath(values[1]));

		System.out.println("titlesize" + titletext1.size());

		for (WebElement webElement1 : titletext1) {
			String name1 = webElement1.getText();
			if (name1.contains(name)) {
				webElement1.click();
				System.out.println(name1);
				add(driver, " Click on " + values[0], LogAs.PASSED, true, values[0]);
				wait(driver, "1");
				break;

			}
		}
	}

	public void Folder_Select(WebDriver driver, String xpaths, String name) {
		String[] values = splitXpath(xpaths);

		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 20);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(values[1])));

			List<WebElement> title3 = driver.findElements(By.xpath(values[1]));
			System.out.println("title3" + title3.size());
			for (WebElement webElement3 : title3) {
				String text11 = webElement3.getText();
				if ((text11.equalsIgnoreCase(name))) {
					webElement3.click();
					System.out.println("text11 :" + text11);
					add(driver, " Able to select the folder " + text11, LogAs.PASSED, true, "");
					break;

				}
			}

		} catch (Exception e) {
			// add(driver, " Unable to select the folder " + "", LogAs.FAILED, true, "");
		}
	}

	public void Upload_File(WebDriver driver, String Location, String xpaths) {
		String[] values = splitXpath(xpaths);
		Robot rb1;
		try {
			rb1 = new Robot();
			rb1.delay(1000);
			StringSelection ss = new StringSelection(Location);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

			rb1.keyPress(KeyEvent.VK_CONTROL);
			rb1.keyPress(KeyEvent.VK_V);
			rb1.keyRelease(KeyEvent.VK_V);
			rb1.keyRelease(KeyEvent.VK_CONTROL);
			wait(driver, "2");
			rb1.keyPress(KeyEvent.VK_ENTER);
			wait(driver, "10");
			WebElement webElement = driver.findElement(By.xpath(values[1]));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
					webElement);
			webElement.click();
			wait(driver, "2");
		} catch (AWTException e) {

			e.printStackTrace();
		}

	}

	public void windowhandles1(WebDriver driver) {

		Set<String> windowhandles1 = driver.getWindowHandles();
		System.out.println(windowhandles1);
		List<String> list = new ArrayList<>(windowhandles1);
		driver.switchTo().window(list.get(0));
		System.out.println(driver.getCurrentUrl());

	}

	public void windowhandles2(WebDriver driver) {

		Set<String> windowhandles1 = driver.getWindowHandles();
		System.out.println(windowhandles1);
		List<String> list = new ArrayList<>(windowhandles1);
		driver.switchTo().window(list.get(2));
		System.out.println(driver.getCurrentUrl());

	}

	public void newtapopen(WebDriver driver) {
		// driver.switchTo()
	}

	/*
	 * public void windowhandles1(WebDriver driver) throws InterruptedException {
	 * Robot r; try { r = new Robot(); r.keyPress(KeyEvent.VK_CONTROL);
	 * r.keyPress(KeyEvent.VK_T); Thread.sleep(5000);
	 * Set<String>windowhandles1=driver.getWindowHandles();
	 * System.out.println(windowhandles1); List<String>list=new
	 * ArrayList<>(windowhandles1); driver.switchTo().window(list.get(1));
	 * System.out.println(driver.getCurrentUrl()); Thread.sleep(5000);
	 * driver.navigate().to("https://www.mailinator.com/"); //get(driver,
	 * "https://www.mailinator.com/"); } catch (AWTException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

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

	public void newTabpaste(WebDriver driver) {
		try {
			wait(driver, "5");
			Robot r = new Robot();
			r.delay(1000);
			r.keyPress(KeyEvent.VK_CONTROL);

			r.keyPress(KeyEvent.VK_T);
			wait(driver, "5");
			r.keyRelease(KeyEvent.VK_T);
			r.keyRelease(KeyEvent.VK_CONTROL);

			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			wait(driver, "5");
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			wait(driver, "5");
			r.keyPress(KeyEvent.VK_ENTER);
		} catch (Exception e) {
		}
	}

	public void get(WebDriver driver, String url) {
		Capabilities localCapabilities = ((RemoteWebDriver) driver).getCapabilities();
		String browser = localCapabilities.getBrowserName().toLowerCase();
		driver.get(url);
		if (browser.equalsIgnoreCase("ie") || browser.equalsIgnoreCase("UnKnown")) {
			wait(driver, "5");
			driver.get("javascript:document.getElementById('overridelink').click();");
			wait(driver, "5");
		}

	}

	public void closeTab(WebDriver driver) {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
		// tabs.remove(tabs.get(0));
		driver.switchTo().defaultContent();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
	}

	public void closeTab1(WebDriver driver, int input) {
		driver.findElement(By.xpath("body")).sendKeys(Keys.CONTROL + "w");
		// tabs.remove(tabs.get(0));
		driver.switchTo().defaultContent();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(input));
	}

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

	public Response apiValidRequest(String URL1, String method, String body, String APIName, int statusCode1)
			throws IOException, JSONException {
		String line = body;
		Response res = null;
		JSONObject jsonObject = null;
		try {
			String URL = URL1;
			// String values[] = header.split(":");
			if (method.equalsIgnoreCase("POST")) {
				res = RestAssured.given().body(line).with().contentType("application/json").then().when().post(URL);
			} else if (method.equalsIgnoreCase("get")) {
				res = RestAssured.given().with().contentType("application/json").then().when().get(URL);
			}

			if (res.statusCode() == statusCode1) {
				add("Requested API " + APIName + " and got  " + res.statusCode() + " response code", LogAs.PASSED, true,
						res.print().toString(), (res.getTime()));

			} else {
				add1("Unsuccessfull API hit:" + APIName + " and got  " + res.statusCode() + " response code",
						LogAs.FAILED, true, res.print().toString());
				Assert.fail();
			}

		} catch (Exception e) {
			add1("Unable to hit the API " + APIName, LogAs.FAILED, true, "");

			Assert.fail();
		}
		return res;
	}

	public Response apiValidRequest2(String URL1, String method, String header, String body, String APIName,
			String statusCode1) throws IOException, JSONException {
		String line = null;
		Response res = null;
		try {
			String URL = URL1;
			// String values[] = header.split(":");
			if (method.equalsIgnoreCase("POST")) {

				res = RestAssured.given().header("Authorization", header).body(line).with()
						.contentType("application/json").then().expect().when().post(URL);
			} else if (method.equalsIgnoreCase("get")) {
				res = RestAssured.given().header("Authorization", header).with().contentType("application/json").then()
						.expect().when().get(URL);
			}
			add("Requested API " + APIName + " and got  " + res.statusCode() + " response code", LogAs.PASSED, true,
					res.print().toString(), (res.getTime()));

		} catch (Exception e) {

			add1("Requested API " + APIName + " and got the " + res.statusCode() + " response code", LogAs.FAILED, true,
					"");

			Assert.fail();
		}
		return res;
	}

	public Response apiValidRequest3(String url, String method, String header, String body, String APIName,
			int statuscode1) throws IOException, JSONException {
		String body1 = body;
		// int statuscode= statuscode1;

		Response res = null;

		try {
			String URL = url;

			if (method.equalsIgnoreCase("POST")) {
				res = RestAssured.given().headers("Authorization", header).body(body1).with()
						.contentType("application/json").then().expect().when().post(URL);
			} else if (method.equalsIgnoreCase("GET")) {
				res = RestAssured.given().headers("Authorization", header).with().contentType("application/json").then()
						.expect().when().get(URL);
			}
			add("Requested API " + APIName + " and got  " + res.statusCode() + " as response code", LogAs.PASSED, true,
					res.print().toString(), (res.getTime()));

		} catch (Exception e) {

			add1("Requested API " + APIName + " and got the " + res.statusCode() + " as response code", LogAs.FAILED,
					false, "");

			Assert.fail();
		}

		JSONObject jsonObject = null;
		jsonObject = new JSONObject(res.asString());
		System.out.println("---output----" + jsonObject.toString() + "---output---");
		return res;
	}

	// public static String apigetRequest(String url, String method, String header)
	// throws IOException, JSONException {
	//
	// Response res = null;
	// Response res2 = null;
	//
	// String URL = url;
	// String link = "null";
	// try {
	// if (method.equalsIgnoreCase("GET")) {
	//
	// res = RestAssured.given().header("Authorization",
	// "86cba60611324b9a8297d709f4029698").with().contentType("application/json").then().expect().when().get("https://mailinator.com/api/v2/domains/private/inboxes/:*");
	// System.out.println(res);
	// JSONObject jsonObject = null;
	// jsonObject = new JSONObject(res.asString());
	// String messageID = JsonPath.read(jsonObject.toString(), "$.msgs[0].id");
	// System.out.println(messageID);
	// res2 = RestAssured.given().header("Authorization",
	// "86cba60611324b9a8297d709f4029698").with().contentType("application/json").then().expect().when().get("https://api.mailinator.com/v2/domains/private/inboxes/test1/messages/"+messageID+"/links");
	// System.out.println(res2);
	// jsonObject = new JSONObject(res2.asString());
	// System.out.println(jsonObject.toString());
	// link = JsonPath.read(jsonObject.toString(), "$.links[0]");
	// System.out.println(link);
	// }
	// }catch(Exception e) {
	// e.printStackTrace();
	// System.out.println(e.getLocalizedMessage());
	// }
	// return link;
	// }

	private static Object parse(String json) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void apideleteRequest(String url, String method, String header) throws IOException, JSONException {

		Response res = null;

		String URL = url;
		if (method.equalsIgnoreCase("DELETE")) {
			res = RestAssured.given().headers("Authorization", header).with().contentType("application/json").then()
					.expect().when().delete(URL);
			System.out.println(res);
			JSONObject jsonObject = null;
			jsonObject = new JSONObject(res.asString());
			System.out.println("---output----" + jsonObject.toString() + "---output---");
		}

	}

	public static String apiPutBodyRequest(String url, String method, String header, String body) throws JSONException {
		String body1 = body;
		String URL = url;

		Response res = null;

		if (method.equalsIgnoreCase("PUT")) {
			res = RestAssured.given().headers("Authorization", header).body(body1).with()
					.contentType("application/json").then().expect().when().put(URL);
		}

		JSONObject jsonObject = null;
		jsonObject = new JSONObject(res.asString());
		System.out.println("---output----" + jsonObject.toString() + "---output---");
		return res.asString();

	}

	public static String apiputrequest(String url, String method, String header) throws JSONException {

		String URL = url;

		Response res = null;

		if (method.equalsIgnoreCase("PUT")) {
			res = RestAssured.given().headers("Authorization", header).with().contentType("application/json").then()
					.expect().when().put(URL);
		}

		JSONObject jsonObject = null;
		jsonObject = new JSONObject(res.asString());
		System.out.println("---output----" + jsonObject.toString() + "---output---");
		return res.asString();

	}

	public static int GenerateRandomNumber() {

		System.out.println("Random Numbers:");
		Random rand = new Random();
		int num = rand.nextInt(900000) + 100000;
		System.out.println("***************");

		System.out.println(num);

		return num;
	}

	public void mouseOverAndClick(WebDriver driver, String element) {
		String[] values = splitXpath(element);
		WebElement webElement = driver.findElement(By.xpath(values[1]));
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(webElement).click().build().perform();
			// builder.moveToElement(webElement).build().perform();

		} catch (Exception e) {

		}
	}

	public String movetoelement(WebDriver driver, WebElement element, String filepath, String filepath1,
			String filepath2, String filepath3, String filepath4, String filepath5, String filepath6, String filepath7,
			String filepath8, String filepath9) {

		int number = Integer.parseInt(filepath);
		int number1 = Integer.parseInt(filepath1);
		int number2 = Integer.parseInt(filepath2);
		int number3 = Integer.parseInt(filepath3);
		int number4 = Integer.parseInt(filepath4);
		int number5 = Integer.parseInt(filepath5);
		int number6 = Integer.parseInt(filepath6);
		int number7 = Integer.parseInt(filepath7);
		int number8 = Integer.parseInt(filepath8);
		int number9 = Integer.parseInt(filepath9);

		try {
			Actions builder = new Actions(driver);
			// builder.moveToElement(element, -220, -160).click().moveByOffset(-335,
			// 0).click().moveByOffset(0,
			// 230).click().moveByOffset(335,0).click().moveByOffset(0,
			// -230).click().perform();
			builder.moveToElement(element, number, number1).click().moveByOffset(number2, number3).click()
					.moveByOffset(number4, number5).click().moveByOffset(number6, number7).click()
					.moveByOffset(number8, number9).click().perform();
		} catch (Exception e) {

		}
		return null;

	}

	// Draw rectangle, rotate
	public String Rectangle(WebDriver driver, String xpath, String Path, String Path1, String Path2, String Path3,
			String Path4, String Path5, String Path6, String Path7, String Path8, String Path9) {
		String[] values = splitXpath(xpath);
		int line1 = Integer.parseInt(Path);
		int line2 = Integer.parseInt(Path1);
		int line3 = Integer.parseInt(Path2);
		int line4 = Integer.parseInt(Path3);
		int line5 = Integer.parseInt(Path4);
		int line6 = Integer.parseInt(Path5);
		int line7 = Integer.parseInt(Path6);
		int line8 = Integer.parseInt(Path7);
		int line9 = Integer.parseInt(Path8);
		int line10 = Integer.parseInt(Path9);
		try {
			Actions builder = new Actions(driver);
			WebElement webElement = driver.findElement(By.xpath(values[1]));

			builder.moveToElement(webElement, line1, line2).click().moveByOffset(line3, line4).click()
					.moveByOffset(line5, line6).click().moveByOffset(line7, line8).click().moveByOffset(line9, line10)
					.click().perform();
			wait(driver, "5");
			add(driver, " Draw on the Rectangle Shape " + "", LogAs.PASSED, true, "");

		} catch (Exception e) {
			add(driver, " Unable to draw the rectangle shape " + "", LogAs.FAILED, true, "");
		}
		return null;

	}

	// Draw Triangle
	public String Triangle(WebDriver driver, WebElement element, String Path, String Path1, String Path2, String Path3,
			String Path4, String Path5) {

		int line1 = Integer.parseInt(Path);
		int line2 = Integer.parseInt(Path1);
		int line3 = Integer.parseInt(Path2);
		int line4 = Integer.parseInt(Path3);
		int line5 = Integer.parseInt(Path4);
		int line6 = Integer.parseInt(Path5);

		try {
			Actions builder = new Actions(driver);

			builder.moveToElement(element).click().moveByOffset(line1, line2).click().moveByOffset(line3, line4).click()
					.moveByOffset(line5, line6).click().perform();
		} catch (Exception e) {

		}
		return null;

	}

	// To circle,move the object, measure the object, flip
	public String objectmove(WebDriver driver, String xpath, String Path, String Path1, String Path2, String Path3) {
		String[] values = splitXpath(xpath);
		int line1 = Integer.parseInt(Path);
		int line2 = Integer.parseInt(Path1);
		int line3 = Integer.parseInt(Path2);
		int line4 = Integer.parseInt(Path3);

		try {
			Actions builder = new Actions(driver);
			WebElement webElement = driver.findElement(By.xpath(values[1]));

			builder.moveToElement(webElement, line1, line2).click().moveByOffset(line3, line4).click().perform();
			add(driver, " Able to Object move on " + "", LogAs.PASSED, true, "");
		} catch (Exception e) {
			add(driver, " Unable to Object move on " + "", LogAs.FAILED, true, "");
		}
		return null;

	}

	// To flip the object
	public String Flipmove(WebDriver driver, WebElement element, String Path, String Path1) {

		int line1 = Integer.parseInt(Path);
		int line2 = Integer.parseInt(Path1);

		try {
			Actions builder = new Actions(driver);

			builder.moveToElement(element, line1, line2).click().perform();
			add(driver, "Able to flip move on " + "", LogAs.PASSED, true, "");
		} catch (Exception e) {
			add(driver, "Unable to flip move on " + "", LogAs.FAILED, true, "");
		}
		return null;

	}

	// Click to given day
	public static void clickGivenDay(List<WebElement> elementList, String day) {
		// DatePicker is a table. Thus we can navigate to each cell
		// and if a cell matches with the current date then we will click it.
		/** Functional JAVA version of this method. */
		elementList.stream().filter(element -> element.getText().contains(day)).findFirst()
				.ifPresent(WebElement::click);
	}

	public static String getCurrentDay() {
		// Create a Calendar Object
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

		// Get Current Day as a number
		int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("Today Int: " + todayInt + "\n");

		// Integer to String Conversion
		String todayStr = Integer.toString(todayInt);
		System.out.println("Today Str: " + todayStr + "\n");

		return todayStr;
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
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
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
				if (percentage < 1.5) {
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

	public static void disableToast(WebDriver driver) {

		try {

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			Object de = js1.executeScript("store.exposed.autoSaveConfig.disableToasts()");
			System.out.println(de);

		}

		catch (Exception e1) {
			Assert.fail();
		}

	}

	public void getfps(WebDriver driver, String action) {

		try {

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			Object de = js1.executeScript("return store.scene.getEngine().getFps()");
			System.out.println(action + " FPS value : " + de);

			// add(driver, action+" : FPS", LogAs.PASSED, true, ""+de);
			addfps(driver, action + " FPS", de.toString(), true, "");

		}

		catch (Exception e1) {
			add1(driver, "Could not retrieve FPS value", LogAs.FAILED, true, "");
		}

	}

	public void Upload_PDF_file(WebDriver driver, String Location) {

		Robot rb1;
		try {
			rb1 = new Robot();
			rb1.delay(1000);
			StringSelection ss = new StringSelection(Location);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

			rb1.keyPress(KeyEvent.VK_CONTROL);
			rb1.keyPress(KeyEvent.VK_V);
			rb1.keyRelease(KeyEvent.VK_V);
			rb1.keyRelease(KeyEvent.VK_CONTROL);
			wait(driver, "2");
			rb1.keyPress(KeyEvent.VK_ENTER);

			wait(driver, "2");

		} catch (AWTException e) {

			e.printStackTrace();
		}

	}

	public static int getXCoordOffsetFromCentre1(WebDriver driver, Point centre, int x, int y, double z) {

		Integer intX = Integer.valueOf(x);
		Integer intY = Integer.valueOf(y);
		Double doubleZ = Double.valueOf(z);
		int firstValue = 0;

		try {

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			Object de = js1.executeScript(

					"const v3= new store.exposed.BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
							+ "		return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",

					// "const v3= new BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
					// + " return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()"
					intX, intY, doubleZ);
			System.out.println(de);

			String str = de.toString();
			String[] first = str.split(",");
			firstValue = Integer.parseInt(first[0].replace("[", "").trim()) - centre.x;
			System.out.println(firstValue);

		}

		catch (Exception e1) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
		return firstValue;

	}

	public static int getYCoordOffsetFromCentre1(WebDriver driver, Point centre, int x, int y, double z) {

		Integer intX = Integer.valueOf(x);
		Integer intY = Integer.valueOf(y);
		Double doubleZ = Double.valueOf(z);
		int secondvalue = 0;

		try {

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			Object de = js1.executeScript(

					"const v3= new store.exposed.BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
							+ "		return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",

					// "const v3= new BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
					// + " return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",
					intX, intY, doubleZ);
			System.out.println(de);

			String str = de.toString();
			String[] first = str.split(",");
			secondvalue = Integer.parseInt(first[1].replace("]", "").trim()) - centre.y;
			System.out.println(secondvalue);

		}

		catch (Exception e1) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
		return secondvalue;

	}

	public static int getXCoordOffsetFromCentre2(WebDriver driver, Point centre, double x, int y, int z) {

		Double doubleX = Double.valueOf(x);
		Integer intY = Integer.valueOf(y);
		Integer intZ = Integer.valueOf(z);
		int firstValue = 0;

		try {

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			Object de = js1.executeScript(

					"const v3= new store.exposed.BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
							+ "		return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",

					// "const v3= new BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
					// + " return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",
					doubleX, intY, intZ);
			System.out.println(de);

			String str = de.toString();
			String[] first = str.split(",");
			firstValue = Integer.parseInt(first[0].replace("[", "").trim()) - centre.x;
			System.out.println(firstValue);

		}

		catch (Exception e1) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
		return firstValue;

	}

	public static int getYCoordOffsetFromCentre2(WebDriver driver, Point centre, double x, int y, int z) {

		Double doubleX = Double.valueOf(x);
		Integer intY = Integer.valueOf(y);
		Integer intZ = Integer.valueOf(z);
		int secondvalue = 0;

		try {

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			Object de = js1.executeScript(

					"const v3= new store.exposed.BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
							+ "		return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",

					// "const v3= new BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
					// + " return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",
					doubleX, intY, intZ);
			System.out.println(de);

			String str = de.toString();
			String[] first = str.split(",");
			secondvalue = Integer.parseInt(first[1].replace("]", "").trim()) - centre.y;
			System.out.println(secondvalue);

		}

		catch (Exception e1) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
		return secondvalue;

	}

	public static int getXCoordOffsetFromCentre3(WebDriver driver, Point centre, double x, double y, double z) {

		Double doubleX = Double.valueOf(x);
		Double doubleY = Double.valueOf(y);
		Double doubleZ = Double.valueOf(z);
		int firstValue = 0;

		try {

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			Object de = js1.executeScript(

					"const v3= new store.exposed.BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
							+ "		return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",

					// "const v3= new BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
					// + " return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",
					doubleX, doubleY, doubleZ);
			System.out.println(de);

			String str = de.toString();
			String[] first = str.split(",");
			firstValue = Integer.parseInt(first[0].replace("[", "").trim()) - centre.x;
			System.out.println(firstValue);

		}

		catch (Exception e1) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
		return firstValue;

	}

	public static int getYCoordOffsetFromCentre3(WebDriver driver, Point centre, double x, double y, double z) {

		Double doubleX = Double.valueOf(x);
		Double doubleY = Double.valueOf(y);
		Double doubleZ = Double.valueOf(z);
		int secondvalue = 0;

		try {

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			Object de = js1.executeScript(

					"const v3= new store.exposed.BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
							+ "		return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",

					// "const v3= new BABYLON.Vector3(arguments[0],arguments[1],arguments[2]);\r\n"
					// + " return store.exposed.getV3ProjectedOntoScreenSpace(v3).asArray()",
					doubleX, doubleY, doubleZ);
			System.out.println(de);

			String str = de.toString();
			String[] first = str.split(",");
			secondvalue = Integer.parseInt(first[1].replace("]", "").trim()) - centre.y;
			System.out.println(secondvalue);

		}

		catch (Exception e1) {
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
		return secondvalue;

	}

	public void addCargo(WebDriver driver) {
		waitForElement(driver, AddCargoTab);
		waitForElement(driver, ISOCode);
		doubleClick(driver, ISOCode);
		sendKeys(driver, ISOCode, "45G1");

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

	public void Upload_File1(WebDriver driver, String Location) {
		// String[] values = splitXpath(xpaths);
		Robot rb1;
		try {
			rb1 = new Robot();
			rb1.delay(1000);
			StringSelection ss = new StringSelection(Location);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

			rb1.keyPress(KeyEvent.VK_CONTROL);
			rb1.keyPress(KeyEvent.VK_V);
			rb1.keyRelease(KeyEvent.VK_V);
			rb1.keyRelease(KeyEvent.VK_CONTROL);
			wait(driver, "4");
			rb1.keyPress(KeyEvent.VK_ENTER);
			wait(driver, "10");
			// WebElement webElement = driver.findElement(By.xpath(values[1]));
			wait(driver, "2");

			// JavascriptExecutor js = (JavascriptExecutor) driver;
			wait(driver, "2");

			wait(driver, "2");
		} catch (AWTException e) {

			e.printStackTrace();
		}

	}

	public static void orbit(WebDriver driver, int n, int x, int y) {

		try {
			WebElement canvas = driver.findElement(By.id("canvas"));
			System.out.println(canvas.getLocation());
			Point leftTop = canvas.getLocation();
			Dimension size = canvas.getSize();
			System.out.println(size);
			int centreX = leftTop.x + size.width / 2;
			int centreY = leftTop.y + size.height / 2;
			Point centre = new Point(centreX, centreY);
			System.out.println(centre);
			Actions builder = new Actions(driver);

			driver.findElement(By.xpath("//img[@data-for='orbit']")).click();

			for (int i = 0; i < n; i++) {
				builder.clickAndHold(canvas).moveByOffset(0, 0).moveByOffset(x, y).click().perform();
			}

			driver.findElement(By.xpath("//img[@data-for='orbit']")).click();
		}

		catch (Exception e) {
			System.out.println("Can able to view zoomout canvas");
		}

	}

	public static void orbit1(WebDriver driver, int n, int x, int y, int m, int a, int b) {

		try {
			WebElement canvas = driver.findElement(By.id("canvas"));
			System.out.println(canvas.getLocation());
			Point leftTop = canvas.getLocation();
			Dimension size = canvas.getSize();
			System.out.println(size);
			int centreX = leftTop.x + size.width / 2;
			int centreY = leftTop.y + size.height / 2;
			Point centre = new Point(centreX, centreY);
			System.out.println(centre);
			Actions builder = new Actions(driver);

			driver.findElement(By.xpath("//img[@data-for='orbit']")).click();

			for (int i = 0; i < n; i++) {
				builder.clickAndHold(canvas).moveByOffset(0, 0).moveByOffset(x, y).click().perform();
			}

			for (int i = 0; i < m; i++) {
				builder.clickAndHold(canvas).moveByOffset(0, 0).moveByOffset(a, b).click().perform();
			}

			driver.findElement(By.xpath("//img[@data-for='orbit']")).click();
		}

		catch (Exception e) {
			System.out.println("Can able to view zoomout canvas");
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

	public void uploadfile(WebDriver driver, String path) {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement element = driver.findElement(By.xpath("//input[@type='file']"));
			File file = new File(path);
			System.out.println(file.getAbsolutePath());
			element.sendKeys(file.getAbsolutePath());
			add(driver, "uploaded the file " + path, LogAs.PASSED, true, path);
			wait(driver, "2");
		} catch (Exception e) {
			add1(driver, "upload is falied - " + path + e, LogAs.FAILED, true, e.getLocalizedMessage());
			e.printStackTrace();
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public void uploadfile1(WebDriver driver, String path) {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement element = driver.findElement(By.xpath("//input[contains(@type,'file')]"));
			File file = new File(path);
			System.out.println(file.getAbsolutePath());
			element.sendKeys(file.getAbsolutePath());
			add(driver, "uploaded the file " + path, LogAs.PASSED, true, path);
			wait(driver, "2");
		} catch (Exception e) {
			add1(driver, "upload is falied - " + path + e, LogAs.FAILED, true, e.getLocalizedMessage());
			e.printStackTrace();
			((JavascriptExecutor) driver).executeScript("lambda-status=failed");
			Assert.fail();
		}
	}

	public static void Autosave(WebDriver driver) {

		try {

			int k = 1;

			for (int i = 1; i <= k; i++) {
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				Object de = js1.executeScript("store.exposed.AutoSave.getQueue().length === 0");
				System.out.println(de);
				boolean di = ((Boolean) de).booleanValue();
				if (di == false) {
					Thread.sleep(2000);
					k = k + 1;
					if (k == 150) {
						System.out.println("Waited for 5 min autosave didn't complete");
						break;
					}
					if (di == true) {
						System.out.println("Autosave completed");
					}
				}
			}

		}

		catch (Exception e1) {
			Assert.fail();
		}

	}

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

	public static void ActionTest(ExtentTest extentTest) {
		test = extentTest;
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

//	@Test
	public void Fillocomparison() {
		try {

			System.setProperty("ROW", "11");
			Fillo fillo = new Fillo();

			Connection connection1 = fillo.getConnection("C:\\Users\\RBT\\Pictures\\MASTER CARGO LIST.xlsx");
			Connection connection2 = fillo.getConnection("C:\\Users\\RBT\\Pictures\\TEST CARGO LIST.xlsx");

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
	
			
			while (recordset1.next()) {
				String stowageSheet1 = recordset1.getField("Stowage");

				recordset2.moveFirst();

				boolean matchFound = false;			
				    while (recordset2.next()) {
				        String stowageSheet2 = recordset2.getField("Stowage");

				        if (stowageSheet1 != null && stowageSheet1.trim().equals(stowageSheet2 != null ? stowageSheet2.trim() : "")) {
				            matchFound = true;

				            for (String columnName : columnsToCompare) {
				                String valueSheet1Column = recordset1.getField(columnName);
				                String valueSheet2Column = recordset2.getField(columnName);

				                if ((valueSheet1Column == null && valueSheet2Column == null)
				                        || ((stowageSheet1 == null || stowageSheet1.trim().isEmpty()) && "0".equals(stowageSheet2))
				                        || ((stowageSheet2 == null || stowageSheet2.trim().isEmpty()) && "0".equals(stowageSheet1))
				                        || (valueSheet1Column != null && valueSheet1Column.equals(valueSheet2Column))) {

//				                	 Extent_pass1(test, "Values are matched for " + "Stowage number : " +stowageSheet1 + "||" + " Column name is :" + columnName + "||" + " Master value: " + valueSheet1Column + "||" + " Test value: " + valueSheet2Column);
				                } else {
				                    uniqueFailedStowageNumbers.add(stowageSheet1);
				                    failedStowageMap.computeIfAbsent(columnName, k -> new HashSet<>()).add(stowageSheet1);
				                    failureCountByColumn.put(columnName, failureCountByColumn.getOrDefault(columnName, 0) + 1);
//				                    Extent_fail2(test, "Values are not matched for " + "Stowage number : " + stowageSheet1 + "||"
//				                            + "Column name is :" + columnName + "||" + " Master value: " + valueSheet1Column + "||"
//				                            + " Test value: " + valueSheet2Column);
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
				                    failGroupCountMapnew.put("DG class", failGroupCountMapnew.getOrDefault("DG class", 0) + 1);
				                    DG_Class_stownumber.add(stowageSheet1);
				                    DG_Class_Group_Excepted.add(recordset1.getField("DG class"));
				                    DG_Class_Group_Actual.add(recordset2.getField("DG class"));
				                }
				                
				                if (recordset2.getField("mul Haz") == null
				                        || !recordset1.getField("mul Haz").equals(recordset2.getField("mul Haz"))) {
				                    failGroupCountMapnew.put("mul Haz", failGroupCountMapnew.getOrDefault("mul Haz", 0) + 1);
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
				                    failGroupCountMapnew.put("Variant", failGroupCountMapnew.getOrDefault("Variant", 0) + 1);
				                    Variant_stownumber.add(stowageSheet1);
				                    Variant_Group_Excepted.add(recordset1.getField("Variant"));
				                    Variant_Group_Actual.add(recordset2.getField("Variant"));
				                }
				                
				                if (recordset2.getField("FlashPoint") == null
				                        || !recordset1.getField("FlashPoint").equals(recordset2.getField("FlashPoint"))) {
				                    failGroupCountMapnew.put("FlashPoint", failGroupCountMapnew.getOrDefault("FlashPoint", 0) + 1);
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
				                    failGroupCountMapnew.put("OOH (m)", failGroupCountMapnew.getOrDefault("OOH (m)", 0) + 1);
				                    OOH_stownumber.add(stowageSheet1);
				                    OOH_Group_Excepted.add(recordset1.getField("OOH (m)"));
				                    OOH_Group_Actual.add(recordset2.getField("OOH (m)"));
				                }
				                
				                if (recordset2.getField("OLA (m)") == null
				                        || !recordset1.getField("OLA (m)").equals(recordset2.getField("OLA (m)"))) {
				                    failGroupCountMapnew.put("OLA (m)", failGroupCountMapnew.getOrDefault("OLA (m)", 0) + 1);
				                    OLA_stownumber.add(stowageSheet1);
				                    OLA_Group_Excepted.add(recordset1.getField("OLA (m)"));
				                    OLA_Group_Actual.add(recordset2.getField("OLA (m)"));
				                }
				                
				                if (recordset2.getField("OLF (m)") == null
				                        || !recordset1.getField("OLF (m)").equals(recordset2.getField("OLF (m)"))) {
				                    failGroupCountMapnew.put("OLF (m)", failGroupCountMapnew.getOrDefault("OLF (m)", 0) + 1);
				                    OLF_stownumber.add(stowageSheet1);
				                    OLF_Group_Excepted.add(recordset1.getField("OLF (m)"));
				                    OLF_Group_Actual.add(recordset2.getField("OLF (m)"));
				                }
				            
				                if (recordset2.getField("OWP (m)") == null
				                        || !recordset1.getField("OWP (m)").equals(recordset2.getField("OWP (m)"))) {
				                    failGroupCountMapnew.put("OWP (m)", failGroupCountMapnew.getOrDefault("OWP (m)", 0) + 1);
				                    OWP_stownumber.add(stowageSheet1);
				                    OWP_Group_Excepted.add(recordset1.getField("OWP (m)"));
				                    OWP_Group_Actual.add(recordset2.getField("OWP (m)"));
				                }
				        
				                if (recordset2.getField("OWS (m)") == null
				                        || !recordset1.getField("OWS (m)").equals(recordset2.getField("OWS (m)"))) {
				                    failGroupCountMapnew.put("OWS (m)", failGroupCountMapnew.getOrDefault("OWS (m)", 0) + 1);
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

				            if ("Yes".equals(recordset1.getField("Is COD")) && "Yes".equals(recordset2.getField("Is COD"))) {
				            	
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
				            
				            
//				            if ("Yes".equals(recordset1.getField("OOG")) && "Yes".equals(recordset2.getField("OOG"))) {
//				            	
//				            	
//				            }
				            
				            
				        }
				   
				        if (!matchFound) {
							stowageNotInTest.add(stowageSheet1);
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
				        Extent_group_table(test, DG_Class_stownumber.get(i), "DG Group", "DG Class", DG_Class_Group_Excepted.get(i),
				        		DG_Class_Group_Actual.get(i));
				        DGGroupFailuresStowageNumbers.add(DG_Class_stownumber.get(i));
				    }
				}
				
				if (!mul_Haz_stownumber.isEmpty()) {
				    for (int i = 0; i < mul_Haz_stownumber.size(); i++) {
				        Extent_group_table(test, mul_Haz_stownumber.get(i), "DG Group", "mul Haz", mul_Haz_Group_Excepted.get(i),
				        		mul_Haz_Group_Actual.get(i));
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
				        Extent_group_table(test, Variant_stownumber.get(i), "DG Group", "Variant", Variant_Group_Excepted.get(i),
				        		Variant_Group_Actual.get(i));
				        DGGroupFailuresStowageNumbers.add(Variant_stownumber.get(i));
				    }
				}
			
				if (!FlashPoint_stownumber.isEmpty()) {
				    for (int i = 0; i < FlashPoint_stownumber.size(); i++) {
				        Extent_group_table(test, FlashPoint_stownumber.get(i), "DG Group", "FlashPoint", FlashPoint_Group_Excepted.get(i),
				        		FlashPoint_Group_Actual.get(i));
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
				        Extent_group_table(test, Is_COD_stownumber.get(i), "COD Group", "Is COD", Is_COD_Group_Excepted.get(i),
				        		Is_COD_Group_Actual.get(i));
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
				        Extent_group_table(test, IsSpl_stownumber.get(i), "Special Group", "IsSpl", IsSpl_Group_Excepted.get(i),
				        		IsSpl_Group_Actual.get(i));
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

			System.out.println("Stowage numbers not present in the test sheet: " + stowageNotInTest);

			for (Map.Entry<String, Set<String>> entry : failedStowageMap.entrySet()) {
				String columnName = entry.getKey();
				Set<String> failedStowageNumbers = entry.getValue();
				System.out.println("Failed Stowage values for " + columnName + " are: " + failedStowageNumbers);
			}

			for (Map.Entry<String, Integer> entry : failureCountByColumn.entrySet()) {
				System.out.println("Failure count for column '" + entry.getKey() + "': " + entry.getValue());
			}

			for (Map.Entry<String, Integer> entry : failGroupCountMapnew.entrySet()) {
				System.out.println("Group failure count for column '" + entry.getKey() + "': " + entry.getValue());
			}

			int totalFailedCount = uniqueFailedStowageNumbers.size();
			System.out.println("Total Failed count is : " + totalFailedCount);

			System.out.println("DG Group total fail count : "+ DGGroupFailuresStowageNumbers.size());
			System.out.println("OOG Group total fail count : "+ OOGGroupFailuresStowageNumbers.size());
			System.out.println("COD Group total fail count : "+ CODGroupFailuresStowageNumbers.size());
			System.out.println("Special Group total fail count : "+ SpecialGroupFailuresStowageNumbers.size());
			System.out.println("Reefer Group total fail count : "+ ReeferGroupFailuresStowageNumbers.size());
			
			
			recordset1.close();
			recordset2.close();
			connection1.close();
			connection2.close();
		} catch (FilloException e) {
			e.printStackTrace();
		}
	}

//	@Test
	/*
	 * public void Newexcelcomparison() {
	 * 
	 * try {
	 * 
	 * System.setProperty("ROW", "11");//Table start row
	 * 
	 * Fillo fillo = new Fillo();
	 * 
	 * Connection connection1 =
	 * fillo.getConnection("C:\\Users\\RBT\\Documents\\mas.xlsx"); Connection
	 * connection2 = fillo.getConnection("C:\\Users\\RBT\\Documents\\act.xlsx");
	 * 
	 * String query1 = "Select * from `CARGO LIST`"; Recordset recordset1 =
	 * connection1.executeQuery(query1);
	 * 
	 * String query2 = "Select * from `CARGO LIST`"; Recordset recordset2 =
	 * connection2.executeQuery(query2);
	 * 
	 * List<String> columnsToCompare = Arrays.asList("ISO", "Weight(t)", "POL",
	 * "POD", "Mty", "IsSpl", "Is COD", "Rfr", "OOG", "DG", "mul Haz", "DG class",
	 * "UNNO", "OOH (m)", "OLF (m)", "OLA (m)", "OWP (m)", "OWS (m)", "Booking No",
	 * "Variant", "FlashPoint", "DGLQ");
	 * 
	 * Map<String, Set<String>> failedStowageMap = new HashMap<>(); Map<String,
	 * Integer> failureCountByColumn = new HashMap<>(); Set<String>
	 * uniqueFailedStowageNumbers = new HashSet<>(); Set<String> stowageNotInTest =
	 * new HashSet<>(); Map<String, Integer> failGroupCountMap1 = new HashMap<>();
	 * 
	 * while (recordset1.next()) { String stowageSheet1 =
	 * recordset1.getField("Stowage");
	 * 
	 * recordset2.moveFirst();
	 * 
	 * boolean matchFound = false;
	 * 
	 * // Move these inside the loop List<String> DG_stownumber = new ArrayList<>();
	 * List<String> DG_Group_Excepted = new ArrayList<>(); List<String>
	 * DG_Group_Actual = new ArrayList<>();
	 * 
	 * while (recordset2.next()) { String stowageSheet2 =
	 * recordset2.getField("Stowage");
	 * 
	 * if (stowageSheet1 != null && stowageSheet1.trim().equals(stowageSheet2 !=
	 * null ? stowageSheet2.trim() : "")) { matchFound = true;
	 * 
	 * for (String columnName : columnsToCompare) { String valueSheet1Column =
	 * recordset1.getField(columnName); String valueSheet2Column =
	 * recordset2.getField(columnName);
	 * 
	 * if ((valueSheet1Column == null && valueSheet2Column == null) ||
	 * ((stowageSheet1 == null || stowageSheet1.trim().isEmpty()) &&
	 * "0".equals(stowageSheet2)) || ((stowageSheet2 == null ||
	 * stowageSheet2.trim().isEmpty()) && "0".equals(stowageSheet1)) ||
	 * (valueSheet1Column != null && valueSheet1Column.equals(valueSheet2Column))) {
	 * // Extent_pass1(test, "Values are matched for " + "Stowage number : " + //
	 * stowageSheet1 + "||" + " Column name is :" + columnName + "||" + " Master //
	 * value: " + valueSheet1Column + "||" + " Test value: " + valueSheet2Column); }
	 * else { uniqueFailedStowageNumbers.add(stowageSheet1);
	 * failedStowageMap.computeIfAbsent(columnName, k -> new
	 * HashSet<>()).add(stowageSheet1); // Extent_fail2(test,
	 * "Values are not matched for " + "Stowage number : " + // stowageSheet1 + "||"
	 * + "Column name is :" + columnName + "||" + " Master // value:
	 * " + valueSheet1Column + "||" + " Test value: " + valueSheet2Column);
	 * failureCountByColumn.put(columnName,
	 * failureCountByColumn.getOrDefault(columnName, 0) + 1); } }
	 * 
	 * if (recordset1.getField("DG") != null &&
	 * recordset1.getField("DG").contains("Yes")) { if (recordset2.getField("DG") ==
	 * null || !recordset1.getField("DG").equals(recordset2.getField("DG"))) {
	 * failGroupCountMap1.put("DG", failGroupCountMap1.getOrDefault("DG", 0) + 1);
	 * DG_stownumber.add(stowageSheet1);
	 * DG_Group_Excepted.add(recordset1.getField("DG"));
	 * DG_Group_Actual.add(recordset2.getField("DG")); } } } }
	 * 
	 * 
	 * if (!matchFound) { stowageNotInTest.add(stowageSheet1); }
	 * 
	 * if (DG_stownumber != null) { for (int i = 0; i < DG_stownumber.size(); i++) {
	 * Extent_group_table(test, DG_stownumber.get(i), "DG Group", "DG",
	 * DG_Group_Excepted.get(i), DG_Group_Actual.get(i)); } } }
	 * 
	 * System.out.println("Stowage numbers not present in the test sheet: " +
	 * stowageNotInTest);
	 * 
	 * for (Map.Entry<String, Set<String>> entry : failedStowageMap.entrySet()) {
	 * String columnName = entry.getKey(); Set<String> failedStowageNumbers =
	 * entry.getValue(); System.out.println("Failed Stowage values for " +
	 * columnName + " are: " + failedStowageNumbers); // Extent_fail1(test,
	 * "Failed Stowage values for Column " + columnName + " are: " +
	 * failedStowageNumbers); }
	 * 
	 * for (Map.Entry<String, Integer> entry : failureCountByColumn.entrySet()) {
	 * System.out.println("Failure count for column '" + entry.getKey() + "': " +
	 * entry.getValue()); }
	 * 
	 * for (Map.Entry<String, Integer> entry : failGroupCountMap1.entrySet()) {
	 * System.out.println("Group failure count for column '" + entry.getKey() +
	 * "': " + entry.getValue()); }
	 * 
	 * int totalFailedCount = uniqueFailedStowageNumbers.size();
	 * System.out.println("Total Failed count is : " + totalFailedCount);
	 * 
	 * recordset1.close(); recordset2.close(); connection1.close();
	 * connection2.close(); } catch (FilloException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); }
	 * 
	 * }
	 */

}