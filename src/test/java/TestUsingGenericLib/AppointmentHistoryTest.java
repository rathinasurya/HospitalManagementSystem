package TestUsingGenericLib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.hms.GenericUtilites.ExcelUtility;
import com.hms.GenericUtilites.FileUtility;
import com.hms.GenericUtilites.JavaUtility;
import com.hms.GenericUtilites.WebDriverUtility;

public class AppointmentHistoryTest {

	public static void main(String[] args) throws Throwable {
		{
			FileUtility fLib=new FileUtility();
			ExcelUtility eu=new ExcelUtility();
			JavaUtility ju=new JavaUtility();
			WebDriverUtility wdu=new WebDriverUtility();
			String URL=fLib.getPropertyKeyValue("url");
			String ADMIN_USERNAME=fLib.getPropertyKeyValue("admin_username");
			String ADMIN_PASSWORD=fLib.getPropertyKeyValue("admin_password");
			String DOC_USERNAME=fLib.getPropertyKeyValue("doc_username");
			String DOC_PASSWORD=fLib.getPropertyKeyValue("doc_password");
			String PAT_USERNAME=fLib.getPropertyKeyValue("pat_username");
			String PAT_PASSWORD =fLib.getPropertyKeyValue("pat_password");
			
			WebDriver driver = new ChromeDriver();
			
			wdu.maximizeWindow(driver);
			wdu.waitForPageLoad(driver);
			driver.get(URL);
			
			//Login to Patients module
			driver.findElement(By.xpath("//h3[.='Patients']/following-sibling::div/span/a")).click();
			Thread.sleep(2000);
			driver.findElement(By.name("username")).sendKeys(PAT_USERNAME);
			driver.findElement(By.name("password")).sendKeys(PAT_PASSWORD);
			driver.findElement(By.xpath("//button")).click();
			
			//click on Book Appointment button
			driver.findElement(By.xpath("//span[.=' Book Appointment ']")).click();
			WebElement docSpec1 = driver.findElement(By.name("Doctorspecialization"));
			
			wdu.select(eu.getDataFromExcel("Booking Details", 2, 1), docSpec1);

			WebElement docName = driver.findElement(By.id("doctor"));
			
			wdu.select(eu.getDataFromExcel("Booking Details", 2, 2), docName);
			Thread.sleep(2000);

			driver.findElement(By.name("appdate")).click();
			Thread.sleep(2000);

			String date = eu.getDataFromExcel("Booking Details", 2, 3);
			String MonthAndYear = eu.getDataFromExcel("Booking Details", 2, 4);


			driver.findElement(By.xpath("//th[.='" +MonthAndYear+ "']/../../../../../div[1]/table/tbody/tr/td[@class='day' and .='" +date+ "']")).click();
			driver.findElement(By.id("timepicker1")).click();
			
			String hrs=eu.getDataFromExcel("Booking Details", 2, 5);
			String mins = eu.getDataFromExcel("Booking Details", 2, 6);
			String meridiem = eu.getDataFromExcel("Booking Details", 2, 7);
			
			driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-hour form-control']")).sendKeys("hrs");
			driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-minute form-control']")).sendKeys("mins");
			driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-meridian form-control']"))
					.sendKeys("meridiem");
			driver.findElement(By.name("submit")).click();
			String ConfText = driver.switchTo().alert().getText();
			System.out.println(ConfText+" popup is displayed");
			
			wdu.acceptAlert(driver);
			
			driver.findElement(By.xpath("//i[@class='ti-angle-down']")).click();
			driver.findElement(By.partialLinkText("Log Out")).click();
			
			//Login to Admin Module
			driver.findElement(By.xpath("//h3[.='Admin Login']/following-sibling::div[@class='button']/span/a")).click();
			driver.findElement(By.name("username")).sendKeys(ADMIN_USERNAME);
			driver.findElement(By.name("password")).sendKeys(ADMIN_PASSWORD);
			driver.findElement(By.xpath("//button")).click();
			
			//Click on Appointment History
			driver.findElement(By.xpath("//span[.=' Appointment History ']")).click();
			
			String APatName = eu.getDataFromExcel("Booking Details", 2, 0);
			
			//Validate the user details is presented or not.
			boolean EpatName = driver.findElement(By.xpath("//td[contains(text(),'"+APatName+"')]")).isDisplayed();

			if (EpatName == true) 
			{
				System.out.println(APatName + " appoinment is booked and Status as 'Active'");
			}
			
			else
				
			{
				System.out.println(APatName + " appoinment is not booked and Status as 'Active'");
			}

			
			Thread.sleep(5000);
			driver.quit();

		}
		
}
}
