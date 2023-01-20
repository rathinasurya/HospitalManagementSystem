package com.hms.GenericUtilites;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

public class BaseClass {
	static {
		System.setProperty("webdriver.edge.driver", "./msedgedriver.exe");
		System.setProperty("webdriver.gecko.driver", "./geckodriver.exe");
			}
public 	DatabaseUtility dLib=new DatabaseUtility();
public	FileUtility fLib=new FileUtility();
public ExcelUtility eLib=new ExcelUtility();
public	WebDriverUtility wLib=new WebDriverUtility();
public	JavaUtility jLib=new JavaUtility();
	public static WebDriver driver;
	public static WebDriver sdriver;
	
	@BeforeSuite(groups = {"smoke","regression"})
	//@BeforeSuite
	public void connectToDB() throws Throwable
	{
		//dLib.connectToDB();
		System.out.println("---connect to DB---");
	}
	@Parameters("BROWSER")
	@BeforeClass(groups = {"smoke","regression"})
	//@BeforeClass
	public void openBrowser(String BROWSER) throws Throwable
	{
		//String BROWSER = fLib.getPropertyKeyValue("browser");
		if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			System.out.println("Using firefox browser");
		}
		else if (BROWSER.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
			System.out.println("Using edge browser");
		}
		else if (BROWSER.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
			System.out.println("Using chrome browser");
		}
		else {
			System.out.println("Invalid Browser");
		}
		//sdriver=driver;
		driver.get(fLib.getPropertyKeyValue("url"));
		wLib.waitForPageLoad(driver);
		wLib.maximizeWindow(driver);
	}
	@AfterClass(groups = {"smoke","regression"})
	//@AfterClass
	public void closeBrowser() throws Throwable
	{
		wLib.waitForPageLoad(driver);
		driver.quit();
	}
	@AfterSuite(groups = {"smoke","regression"})
	//@AfterClass
	public void disconnectToDB() throws Throwable
	{
		//dLib.closeDB();
		System.out.println("DB Disconnected");
	}
	
}

