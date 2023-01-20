package DeleteTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.ArrayList;
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
import org.openqa.selenium.support.ui.Select;

public class Manage_Doctor_Test {

	public static void main(String[] args) throws Throwable {
		// Input from Properties file
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);

		String URL = pObj.getProperty("url");
		String ADMIN_USERNAME = pObj.getProperty("admin_username");
		String ADMIN_PASSWORD = pObj.getProperty("admin_password");
		String DOC_USERNAME = pObj.getProperty("doc_username");
		String DOC_PASSWORD = pObj.getProperty("doc_password");
		String PAT_USERNAME = pObj.getProperty("pat_username");
		String PAT_PASSWORD = pObj.getProperty("pat_password");

		// Fetch Data from Excel Sheet
		FileInputStream fis1 = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String docSpec = wb.getSheet("Doctor").getRow(0).getCell(1).getStringCellValue();

		// Launch the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);

		// Login to Patients module
		driver.findElement(By.xpath("//h3[.='Patients']/following-sibling::div/span/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("username")).sendKeys(PAT_USERNAME);
		driver.findElement(By.name("password")).sendKeys(PAT_PASSWORD);
		driver.findElement(By.xpath("//button")).click();

		// click on Book Appointment button
		driver.findElement(By.xpath("//span[.=' Book Appointment ']")).click();
		WebElement docSpec1 = driver.findElement(By.name("Doctorspecialization"));

		// Fill all the details present in Appointment booking page
		Select s1 = new Select(docSpec1);
		s1.selectByVisibleText("Ayurveda");

		WebElement docName = driver.findElement(By.id("doctor"));
		Select s2 = new Select(docName);
		s2.selectByVisibleText("surya");

		driver.findElement(By.name("appdate")).click();
		Thread.sleep(2000);

		int date = 25;
		String MonthAndYear = "December 2022";

		driver.findElement(By.xpath("//th[.='" + MonthAndYear
				+ "']/../../../../../div[1]/table/tbody/tr/td[@class='day' and .='" + date + "']")).click();
		driver.findElement(By.id("timepicker1")).click();

		int hrs = 10;
		int mins = 30;
		String meridiem = "AM";

		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-hour form-control']")).sendKeys("hrs");
		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-minute form-control']")).sendKeys("mins");
		driver.findElement(By.xpath("//input[@class='bootstrap-timepicker-meridian form-control']"))
				.sendKeys("meridiem");
		driver.findElement(By.name("submit")).click();

		// Handled Alert Pop-Up
		driver.switchTo().alert().accept();

		driver.findElement(By.xpath("//i[@class='ti-angle-down']")).click();
		driver.findElement(By.partialLinkText("Log Out")).click();

		// Login to Admin Module
		driver.findElement(By.xpath("//h3[.='Admin Login']/following-sibling::div[@class='button']/span/a")).click();
		driver.findElement(By.name("username")).sendKeys(ADMIN_USERNAME);
		driver.findElement(By.name("password")).sendKeys(ADMIN_PASSWORD);
		driver.findElement(By.xpath("//button")).click();

		// Click on Appointment History
		driver.findElement(By.xpath("//span[.=' Appointment History ']")).click();

		String APatName = "Sarita Pandey";

		// Validate the user details is presented or not.
		boolean EpatName = driver.findElement(By.xpath("//td[contains(text(),'Sarita Pandey')]")).isDisplayed();

//		List<WebElement> PN = driver.findElements(By.xpath("//tbody/tr/td[@class='hidden-xs'][2]"));
//		int count = PN.size();
//		for(int i=0;i<count;i++)
//		{
//			String EpatName = PN.get(i).getText();
//			Thread.sleep(5000);
//			String APatName = "sarita pandey";
//			boolean result = EpatName.contains(APatName);
//			System.out.println(EpatName);
//	   }
		if (EpatName == true) {
			System.out.println(APatName + " appoinment is booked and displayed");
		}

		else

		{
			System.out.println(APatName + " appoinment is not booked and not displayed");
		}

		// Click on Doctor Specialization button
		driver.findElement(By.xpath("//span[.=' Doctors ']")).click();
		driver.findElement(By.xpath("//span[.=' Doctor Specialization ']")).click();
		driver.findElement(By.name("doctorspecilization")).sendKeys(docSpec);
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.xpath("//span[.=' Doctors ']")).click();
		driver.findElement(By.xpath("//span[.=' Add Doctor']")).click();

		WebElement dspec = driver.findElement(By.name("Doctorspecialization"));
		Select s3 = new Select(dspec);
		s3.selectByVisibleText(docSpec);

		Random rdm = new Random();
		int random = rdm.nextInt(500);

		ArrayList<String> al = new ArrayList<String>();

		al.add("docname");
		al.add("clinicaddress");
		al.add("docfees");
		al.add("doccontact");
		al.add("docemail");
		al.add("npass");
		al.add("cfpass");

		Sheet adddoc = wb.getSheet("Add Doctor");

		for (int i = 0; i <= adddoc.getLastRowNum(); i++) {
			if (!(al.get(i).contains("email"))) {
				String value = adddoc.getRow(i).getCell(1).getStringCellValue();
				driver.findElement(By.name(al.get(i))).sendKeys(value);
			} else {
				String value = random + adddoc.getRow(i).getCell(1).getStringCellValue();
				driver.findElement(By.name(al.get(i))).sendKeys(value);
			}
		}

		driver.findElement(By.id("submit")).click();

//		Robot r = new Robot();
//		r.keyPress(KeyEvent.VK_ENTER);

		driver.switchTo().alert().accept();
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

		String p_name = wb.getSheet("patient").getRow(1).getCell(0).getStringCellValue();
		driver.findElement(By.id("searchdata")).sendKeys(p_name);
		driver.findElement(By.id("submit")).click();

		Sheet patsheet = wb.getSheet("patient");
		String pat = patsheet.getRow(1).getCell(0).getStringCellValue();
		driver.findElement(By.id("searchdata")).sendKeys(pat);
		driver.findElement(By.id("submit")).click();
		String CreatedPatient = driver.findElement(By.xpath("//th[.='Patient Name']/../../../tbody/tr/td[2]"))
				.getText();

		if (CreatedPatient.equalsIgnoreCase("thanvi")) {
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

		Sheet pat1 = wb.getSheet("MediHistory");

		for (int i = 0; i <= pat1.getLastRowNum(); i++) {
			String medicaldata = pat1.getRow(i).getCell(1).getStringCellValue();
			driver.findElement(By.name(all.get(i))).sendKeys(medicaldata);
		}
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[.='Submit']")).click();
		driver.switchTo().alert().accept();
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
		driver.findElement(By.xpath("//i[@class='ti-angle-down']")).click();
		driver.findElement(By.partialLinkText("Log Out")).click();

		Thread.sleep(3000);
		
		//Integration test cases
		//contact query
		driver.findElement(By.xpath("//a[.='contact']")).click();
		
		ArrayList<String> al2=new ArrayList<String>();
		al2.add("fullname");
		al2.add("emailid");
		al2.add("mobileno");
		al2.add("description");
		
		Sheet sh2 = wb.getSheet("user");
		for(int i=0;i<sh2.getLastRowNum();i++)
		{
			String data = sh2.getRow(i).getCell(1).getStringCellValue();
			driver.findElement(By.name(al2.get(i))).sendKeys(data);
		}
		driver.findElement(By.name("submit")).click();
		String ConfirmText = driver.switchTo().alert().getText();
		System.out.println(ConfirmText+" pop-up is dispalyed");
		driver.switchTo().alert().accept();
		
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
		
		//driver.findElement(By.xpath("//div[@class='clip-radio radio-primary']/label[2]")).click();
		//Thread.sleep(3000);
		ArrayList<String> al3=new ArrayList<String>();
		al3.add("patname");
		al3.add("patcontact");
		al3.add("patemail");
		driver.findElement(By.xpath("//div[@class='clip-radio radio-primary']/label[2]")).click();
		al3.add("pataddress");
		al3.add("patage");
		al3.add("medhis");
		
		 Row sh3 = wb.getSheet("patient").getRow(2);
		for(int i=0;i<sh3.getLastCellNum();i++)
		{
			if(!(al3.get(i).contains("email")))
			{
			String data = sh3.getCell(i).getStringCellValue();
			driver.findElement(By.name(al3.get(i))).sendKeys(data);
			}
			else
			{
				String data =random + sh3.getCell(i).getStringCellValue();
				driver.findElement(By.name(al3.get(i))).sendKeys(data);
			}
		}
		driver.findElement(By.xpath("//button[@name='submit']")).click();
		Thread.sleep(2000);
			
	
		driver.findElement(By.xpath("//i[@class='ti-angle-down']")).click();
		driver.findElement(By.partialLinkText("Log Out")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//h3[.='Admin Login']/following-sibling::div[@class='button']/span/a")).click();
		driver.findElement(By.name("username")).sendKeys(ADMIN_USERNAME);
		driver.findElement(By.name("password")).sendKeys(ADMIN_PASSWORD);
		driver.findElement(By.xpath("//button")).click();
		driver.findElement(By.xpath("//span[.=' Patients ']")).click();
		driver.findElement(By.xpath("//span[.=' Manage Patients ']")).click();
		List<WebElement> paname = driver.findElements(By.xpath("//table[@class='table table-hover']/tbody/tr/td[2]"));
		String patname = "vijay";
		for(int i=0;i<paname.size();i++)
		{
			String pnames = paname.get(i).getText();
			
			if(patname.equalsIgnoreCase(pnames))
			{
				System.out.println(patname+" is displayed");
				break;
			}
			else
			{
				System.out.println("something went wrong");
				break;
			}
		}
		
	driver.quit();
	}
}

