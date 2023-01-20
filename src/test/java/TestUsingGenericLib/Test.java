package TestUsingGenericLib;



import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.hms.GenericUtilites.ExcelUtility;
import com.hms.GenericUtilites.FileUtility;
import com.hms.GenericUtilites.JavaUtility;
import com.hms.GenericUtilites.WebDriverUtility;

public class Test {

	public static void main(String[] args) throws Throwable 
	{
		FileUtility fu=new FileUtility();
		ExcelUtility eu=new ExcelUtility();
		JavaUtility ju=new JavaUtility();
		WebDriverUtility wdu=new WebDriverUtility();
		String URL=fu.getPropertyKeyValue("url");
		String ADMIN_USERNAME=fu.getPropertyKeyValue("admin_username");
		String ADMIN_PASSWORD=fu.getPropertyKeyValue("admin_password");
		String DOC_USERNAME=fu.getPropertyKeyValue("doc_username");
		String DOC_PASSWORD=fu.getPropertyKeyValue("doc_password");
		String PAT_USERNAME=fu.getPropertyKeyValue("pat_username");
		String PAT_PASSWORD =fu.getPropertyKeyValue("pat_password");
		
		//String docSpec=eu.getDataFromExcel("Doctor", 0, 1);
		
		
		
		//Launch the browser
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


		driver.findElement(By.xpath("//th[.='" + MonthAndYear
				+ "']/../../../../../div[1]/table/tbody/tr/td[@class='day' and .='" + date + "']")).click();
		driver.findElement(By.id("timepicker1")).click();
		
		String hrs=eu.getDataFromExcel("Booking Details", 2, 5);
		String mins = eu.getDataFromExcel("Booking Details", 2, 6);
		String meridiem = eu.getDataFromExcel("Booking Details", 2, 7);
		
		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-hour form-control']")).sendKeys("hrs");
		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-minute form-control']")).sendKeys("mins");
		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-meridian form-control']"))
				.sendKeys("meridiem");
		driver.findElement(By.name("submit")).click();
		
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
			System.out.println(APatName + " appoinment is booked and displayed");
		}
		
		else
			
		{
			System.out.println(APatName + " appoinment is not booked and not displayed");
		}
		
		//Click on Doctor Specialization button
		driver.findElement(By.xpath("//span[.=' Doctors ']")).click();
		driver.findElement(By.xpath("//span[.=' Doctor Specialization ']")).click();
		String docSpec=eu.getDataFromExcel("Doctors Details", 0, 2);
		driver.findElement(By.name("doctorspecilization")).sendKeys(docSpec);
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.xpath("//span[.=' Doctors ']")).click();
		driver.findElement(By.xpath("//span[.=' Add Doctor']")).click();

		WebElement dspec = driver.findElement(By.name("Doctorspecialization"));
		
		wdu.select(docSpec, dspec);

		//ju.getRandomNo();

		ArrayList<String> al = new ArrayList<String>();
		
		al.add("docname");
		al.add("clinicaddress");
		al.add("docfees");
		al.add("doccontact");
		al.add("docemail");
		al.add("npass");
		al.add("cfpass");

		 int adddoc = eu.getRowCount("Doctors Details");

		for (int i = 1; i <= adddoc; i++) 
		{
			if (!(al.get(i).contains("email"))) 
			{
				String value=eu.getDataFromExcel("Doctors Details", 1, i);
				driver.findElement(By.name(al.get(i))).sendKeys(value);
			} 
			else 
			{
				String value= ju.getRandomNo()+eu.getDataFromExcel("Doctors Details", 1, i);
				driver.findElement(By.name(al.get(i))).sendKeys(value);
			}
		}

		driver.findElement(By.id("submit")).click();

		wdu.acceptAlert(driver);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[contains(text(),'Admin')]/i")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-dark']/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//h3[.='Doctors Login']/following-sibling::div/span/a")).click();
		driver.findElement(By.name("username")).sendKeys(DOC_USERNAME);
		driver.findElement(By.name("password")).sendKeys(DOC_PASSWORD);
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.xpath("//span[.=' Search ']")).click();
		
		String pat = eu.getDataFromExcel("Search Patient", 0, 1);
		driver.findElement(By.id("searchdata")).sendKeys(pat);
		driver.findElement(By.id("submit")).click();
		String CreatedPatient = driver.findElement(By.xpath("//th[.='Patient Name']/../../../tbody/tr/td[2]"))
				.getText();

		if (CreatedPatient.equalsIgnoreCase("thanvi")) 
		{
			System.out.println(pat + " is displayed");
			driver.findElement(By.xpath("//a[@href='view-patient.php?viewid=58']")).click();
		}
		
		else
			
		{
			System.out.println(pat + " is not displayed");
		}

		driver.findElement(By.xpath("//button[.='Add Medical History']")).click();
		Thread.sleep(2000);

		ArrayList<String> all = new ArrayList<String>();
		
		all.add("bp");
		all.add("bs");
		all.add("weight");
		all.add("temp");
		all.add("pres");

		//int pat1 = eu.getRowCount("Patient Details");
		short pat1 = eu.getCellCount("Patient Details", 1);

		for (int i = 0; i <= pat1; i++) 
		{
			String medicaldata=eu.getDataFromExcel("Patient Details", 1, i);
			driver.findElement(By.name(all.get(i))).sendKeys(medicaldata);
		}
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[.='Submit']")).click();
		wdu.acceptAlert(driver);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//i[@class='ti-angle-down']")).click();
		driver.findElement(By.partialLinkText("Log Out")).click();

		driver.findElement(By.xpath("//h3[.='Patients']/following-sibling::div/span/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("username")).sendKeys(PAT_USERNAME);
		driver.findElement(By.name("password")).sendKeys(PAT_PASSWORD);
		driver.findElement(By.xpath("//button")).click();
		driver.findElement(By.xpath("//span[.=' Medical History ']")).click();
		driver.findElement(By.xpath("//i[@class='fa fa-eye']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//i[@class='ti-angle-down']")).click();
		driver.findElement(By.partialLinkText("Log Out")).click();
		
		//Integration test cases
				//contact query
				driver.findElement(By.xpath("//a[.='contact']")).click();
				
				ArrayList<String> al2=new ArrayList<String>();
				al2.add("fullname");
				al2.add("emailid");
				al2.add("mobileno");
				al2.add("description");
				
				int sh2 = eu.getRowCount("user");
				for(int i=0;i<sh2;i++)
				{
					String data=eu.getDataFromExcel("User's History", i, 1);
					driver.findElement(By.name(al2.get(i))).sendKeys(data);
				}
				driver.findElement(By.name("submit")).click();
				String ConfirmText = driver.switchTo().alert().getText();
				System.out.println(ConfirmText+" pop-up is dispalyed");
				wdu.acceptAlert(driver);
				driver.findElement(By.xpath("//a[.='Home']")).click();
				
				driver.findElement(By.xpath("//h3[.='Admin Login']/following-sibling::div[@class='button']/span/a")).click();
				driver.findElement(By.name("username")).sendKeys(ADMIN_USERNAME);
				driver.findElement(By.name("password")).sendKeys(ADMIN_PASSWORD);
				driver.findElement(By.xpath("//button")).click();
				
				driver.findElement(By.xpath("//span[.=' Conatctus Queries ']")).click();
				driver.findElement(By.xpath("//span[.=' Unread Query ']")).click();
				driver.findElement(By.xpath("//td[.='Nitish']/following-sibling::td[4]/div/a/i")).click();
				String queryPg = driver.findElement(By.xpath("//h5[@class='over-title margin-bottom-15']/span")).getText();
				System.out.println(queryPg+" page is dispalyed");
				driver.findElement(By.xpath("//span[contains(text(),'Admin')]/i")).click();
				driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-dark']/li[2]/a")).click();
				
				//Integration test cases
				//Add patient by Doctor Module
				driver.findElement(By.xpath("//h3[.='Doctors Login']/following-sibling::div/span/a")).click();
				driver.findElement(By.name("username")).sendKeys(DOC_USERNAME);
				driver.findElement(By.name("password")).sendKeys(DOC_PASSWORD);
				driver.findElement(By.name("submit")).click();
				driver.findElement(By.xpath("//span[.=' Patients ']")).click();
				driver.findElement(By.xpath("//span[.=' Add Patient']")).click();
				
				ArrayList<String> al3=new ArrayList<String>();
				al3.add("patname");
				al3.add("patcontact");
				al3.add("patemail");
				driver.findElement(By.xpath("//div[@class='clip-radio radio-primary']/label[2]")).click();
				al3.add("pataddress");
				al3.add("patage");
				al3.add("medhis");
				
				int sh3 = eu.getRowCount("patient");
				for(int i=0;i<sh3;i++)
				{
					if(!(al3.get(i).contains("email")))
					{
						String data =eu.getDataFromExcel("patient", 2, i);
					driver.findElement(By.name(al3.get(i))).sendKeys(data);
					driver.findElement(By.id("submit")).click();
					break;
					}
					else
					{
						//String data =random + sh3.getCell(i).getStringCellValue();
						String data =ju.getRandomNo()+ eu.getDataFromExcel("patient", 2, i);
						driver.findElement(By.name(al3.get(i))).sendKeys(data);
						driver.findElement(By.id("submit")).click();
						break;
					}
				}
				
				Thread.sleep(2000);
				driver.findElement(By.xpath("//li[@class='dropdown current-user']/a/span/i")).click();
				driver.findElement(By.partialLinkText("Log Out")).click();

				
				driver.findElement(By.xpath("//h3[.='Admin Login']/following-sibling::div[@class='button']/span/a")).click();
				driver.findElement(By.name("username")).sendKeys(ADMIN_USERNAME);
				driver.findElement(By.name("password")).sendKeys(ADMIN_PASSWORD);
				driver.findElement(By.xpath("//button")).click();
				driver.findElement(By.xpath("//span[.=' Patients ']")).click();
				driver.findElement(By.xpath("//span[.=' Manage Patients ']")).click();
				String patname = eu.getDataFromExcel("patient search", 0, 1);
				List<WebElement> paname = driver.findElements(By.xpath("//table[@class='table table-hover']/tbody/tr/td[2]"));
				Thread.sleep(5000);
				boolean flag=false;
				for(int i=0;i<paname.size();i++)
				{
					String pnames = paname.get(i).getText();
					if(patname.equalsIgnoreCase(pnames))
					{   
						flag=true;
						break;
					}
					
				}
				if(flag)
					System.out.println(patname+" is displayed");
				else
					System.out.println(patname+" is not displayed");

	driver.quit();
	}
}
