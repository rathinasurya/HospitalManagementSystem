package Practice_package;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DiffAnnotation {
	@BeforeSuite
	public void connectToServer()
	{
	System.out.println("connect to DB server");	
	}
	@BeforeClass
	public void openBrowser()
	{
		System.out.println("launch the browser");
	}
	@BeforeMethod
	public void loginToApp()
	{
		System.out.println("Login to the application");
	}
	@Test
	public void createAcc()
	{
		System.out.println("Write Script for Create new account ");
	}
	@AfterMethod
	public void logoutToApp()
	{
		System.out.println("Logout to the application");
	}
	@AfterClass
	public void closeBrowser()
	{
		System.out.println("close the browser");
	}
	@AfterSuite
	public void disconnectToServer()
	{
		System.out.println("disconnect to the server ");
	}
}
