package TestUsingGenericLib;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.hms.GenericUtilites.ExcelUtility;
import com.hms.GenericUtilites.FileUtility;
import com.hms.GenericUtilites.JavaUtility;
import com.hms.GenericUtilites.WebDriverUtility;

public class ManageDoctorsTest {

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
		
		//Login to Admin Module
				driver.findElement(By.xpath("//h3[.='Admin Login']/following-sibling::div[@class='button']/span/a")).click();
				driver.findElement(By.name("username")).sendKeys(ADMIN_USERNAME);
				driver.findElement(By.name("password")).sendKeys(ADMIN_PASSWORD);
				driver.findElement(By.xpath("//button")).click();
				
				//Click on Doctor Specialization button
				driver.findElement(By.xpath("//span[.=' Doctors ']")).click();
				driver.findElement(By.xpath("//span[.=' Doctor Specialization ']")).click();
				String docSpec=eu.getDataFromExcel("Doctors Details", 1, 1);
				driver.findElement(By.name("doctorspecilization")).sendKeys(docSpec);
				driver.findElement(By.name("submit")).click();
				driver.findElement(By.xpath("//span[.=' Doctors ']")).click();
				driver.findElement(By.xpath("//span[.=' Add Doctor']")).click();

				WebElement dspec = driver.findElement(By.name("Doctorspecialization"));
				
				wdu.select(docSpec, dspec);

				//ju.getRandomNo();

				ArrayList<String> al = new ArrayList<String>();
				al.add("");
				al.add("");
				al.add("docname");
				al.add("clinicaddress");
				al.add("docfees");
				al.add("doccontact");
				al.add("docemail");
				al.add("npass");
				al.add("cfpass");
				
				//int adddoc = eu.getCellCount("Doctors Details", 1);

				for (int i = 0; i <eu.getRowCount("Doctors Details"); i++) 
				{
					for(int j=2;j<eu.getCellCount("Doctors Details", 1);j++)
					{
					if (!(al.get(j).contains("email"))) 
					{
						String value=eu.getDataFromExcel("Doctors Details", 1, j);
						driver.findElement(By.name(al.get(j))).sendKeys(value);
						driver.findElement(By.id("submit")).click();
					} 
					else 
					{
						String value= ju.getRandomNo()+eu.getDataFromExcel("Doctors Details", 1, j);
						driver.findElement(By.name(al.get(j))).sendKeys(value);
						driver.findElement(By.id("submit")).click();
					}

				}
			
					Thread.sleep(2000);
			//	driver.findElement(By.id("submit")).click();

				wdu.acceptAlert(driver);
				Thread.sleep(2000);

				driver.findElement(By.xpath("//span[contains(text(),'Admin')]/i")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-dark']/li[2]/a")).click();

	}
	}
}
