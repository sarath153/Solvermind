package scripts;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.postgresql.jdbc.EscapedFunctions;

import com.aventstack.extentreports.ExtentTest;

import commonMethods.Keywords;
import commonMethods.TestNgXml;
import commonMethods.Utils;

public class Scenario11 extends Keywords {

	String URL = TestNgXml.getdatafromExecution().get("Scenario11");
	String Username = Utils.getDataFromTestData("Solverminds", "Username");
	String Password = Utils.getDataFromTestData("Solverminds", "Password");
	boolean flag = true;

	public static ExtentTest test;
	private static LocalDateTime currentDateTime = LocalDateTime.now();

	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");
	private static String formattedDateTime = currentDateTime.format(formatter) + "_";

	public void Semiautorun11(WebDriver driver, String vessel2, ExtentTest test, ExtentTest testDetail,
			String detailreportPath) throws Exception {

		String vessel = vessel2;
		String PlanFile = Utils.getDataFromTestData(vessel, "VWRPlanFile");
		String Vesselcode = Vesselname(vessel);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebDriverWait wait1 = new WebDriverWait(driver, 6000);

		navigateUrl(driver, URL);
		waitForElement(driver, username);
		sendKeys(driver, username, Username);

		waitForElement(driver, password);
		sendKeys(driver, password, Password);

		waitForElement(driver, login);
		click(driver, login);

		waitForElement(driver, selectvessel);
		click(driver, selectvessel);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + vessel + "')]")));

		driver.findElement(By.xpath("//*[contains(text(),'" + vessel + "')]")).click();// modification

		waitForElement(driver, Openplantop);

		driver.findElement(By.xpath("//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']"))
				.click();
		
		

		for (int i = 1; i <= 11; i++) {

			String DSWGM_SAS = String.valueOf(i); 
			for(int j=1; j<=5;j++) {
			String Weight = Utils.getDataFromTestData(vessel, "Weight" + j);
			
			waitForElement(driver, Openplantop);
			click(driver, Openplantop);

			waitForElement(driver, Globalplan);
			click(driver, Globalplan);

			waitForElement(driver, clickOk);
			click(driver, clickOk);

			waitForElement(driver, Plandescription);
			click(driver, Plandescription);

			sendKeys(driver, Plandescription, PlanFile);

			WebElement Masterfile = driver.findElement(By.xpath("//*[contains(text(),'" + PlanFile + "')]"));
			wait.until(ExpectedConditions.elementToBeClickable(Masterfile));

			Actions action1 = new Actions(driver).doubleClick(Masterfile);//modification
			action1.build().perform();

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='ves_topbar_align']")));
               // modific
			waitForElement(driver, cargoDetails);
			click(driver, cargoDetails);

			waitForElement(driver, ClickALL);
			click(driver, ClickALL);

			waitForElement(driver, Weightinput);
			doubleClick(driver, Weightinput);

			Robot robot = new Robot();// modific
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			robot.delay(1000);
			wait(driver,"2");
			sendKeys(driver, Weightinput1, Weight);

			wait(driver,"2");
			Actions act = new Actions(driver);
			act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.RETURN).build().perform();
			
			wait(driver,"2");
			waitForElement(driver, Mininmisecargodetails);
//			click(driver, Mininmisecargodetails);
			WebElement minimise = driver.findElement(By.xpath("(//*[text()='Cargo Details']/following::*[local-name()='svg' and @class='jsPanel-icon']/*[local-name()='path'])[4]"));
			Actions actions = new Actions(driver);
			actions.moveToElement(minimise).click().build().perform();
			
			wait(driver,"5");
			
			if (isDisplayed(driver, CloseOpenplan)) {
				click(driver, CloseOpenplan);
				
			}
			
			waitForElement(driver, optimiser);
			click(driver, optimiser);

			waitForElement(driver, Plantemplate);
			click(driver, Plantemplate);

			waitForElement(driver, Lashing);
			click(driver, Lashing);

			wait(driver,"1");
			click(driver, DSWGM);
			doubleClick(driver, DSWGM);
			wait(driver,"1");
			action1.sendKeys(Keys.BACK_SPACE).build().perform();
			action1.sendKeys(Keys.RETURN).build().perform();
			wait(driver,"5");
			sendKeys(driver, DSWGM, DSWGM_SAS);

			waitForElement(driver, Run);
			click(driver, Run);

			wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@alt='Partial Success']")));
			WebElement Success = driver.findElement(By.xpath("//*[@alt='Partial Success']"));

			String message = Success.getText();

			System.out.println(message);

			waitForElement1(driver, ClickOk);
			click(driver, ClickOk);

			wait(driver, "5");

			while (isDisplayed(driver, Run)) {
				
	          actions.sendKeys(Keys.ESCAPE).build().perform();
			}
			
			
			waitForElement(driver, Saveplan);
			click(driver, Saveplan);

			waitForElement(driver, Globalsave);
			click(driver, Globalsave);

			waitForElement(driver, clickOk);
			click(driver, clickOk);
			
			String Planname="TD "+Vesselcode+" "+ Weight+"mT "+DSWGM_SAS+"m VWR"+formattedDateTime;

			waitForElement(driver, Saveinput);
			click(driver, Saveinput);
			
			clear(driver, Saveinput);
			wait(driver,"1");
			sendKeys(driver, Saveinput, Planname);

			waitForElement(driver, Clickplanok);
			click(driver, Clickplanok);
			}

		}
	}

}
