package TestUsingGenericLib;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.hms.GenericUtilites.ExcelUtility;
import com.hms.GenericUtilites.FileUtility;
import com.hms.GenericUtilites.JavaUtility;
import com.hms.GenericUtilites.WebDriverUtility;

public class GenerateReportForPatientTest {

	public static void main(String[] args) throws Throwable {
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
		
		WebDriver driver = new ChromeDriver();
		
		wdu.maximizeWindow(driver);
		wdu.waitForPageLoad(driver);
		driver.get(URL);
		
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
		all.add("");
		all.add("");
		all.add("");
		all.add("");
		all.add("");
		all.add("");
		all.add("");
		all.add("bp");
		all.add("bs");
		all.add("weight");
		all.add("temp");
		all.add("pres");

		//int pat1 = eu.getRowCount("Patient Details");
		//short pat1 = eu.getCellCount("Patient Details", 1);

		for (int i = 0; i <eu.getRowCount("Patient Details"); i++) 
		{
			for(int j=7;j<eu.getCellCount("Patient Details", 1);j++)
			{
			String medicaldata=eu.getDataFromExcel("Patient Details", 1, j);
			driver.findElement(By.name(all.get(j))).sendKeys(medicaldata);
			driver.findElement(By.xpath("//button[.='Submit']")).click();
		}
			Thread.sleep(3000);
			wdu.acceptAlert(driver);
			break;
		}
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

	}
	}

