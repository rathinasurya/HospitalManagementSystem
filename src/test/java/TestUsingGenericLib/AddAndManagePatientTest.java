package TestUsingGenericLib;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.hms.GenericUtilites.ExcelUtility;
import com.hms.GenericUtilites.FileUtility;
import com.hms.GenericUtilites.JavaUtility;
import com.hms.GenericUtilites.WebDriverUtility;

public class AddAndManagePatientTest {

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
		driver.findElement(By.xpath("//span[.=' Patients ']")).click();
		driver.findElement(By.xpath("//span[.=' Add Patient']")).click();
		
		ArrayList<String> al3=new ArrayList<String>();
		al3.add("");
		al3.add("patname");
		al3.add("patcontact");
		al3.add("patemail");
		driver.findElement(By.xpath("//div[@class='clip-radio radio-primary']/label[2]")).click();
		al3.add("pataddress");
		al3.add("patage");
		al3.add("medhis");
	//	eu.getCellCount("Patient Details", 1)
		//eu.getRowCount("Patient Details")
		for(int i=0;i<al3.size();i++)
		{
			for(int j=1;j<=al3.size();j++)
		{
			if(!(al3.get(j).contains("email")))
			{
			String data =eu.getDataFromExcel("Patient Details", 2, j);
			driver.findElement(By.name(al3.get(j))).sendKeys(data);
			driver.findElement(By.id("submit")).click();
			}
			else
			{
				//String data =random + sh3.getCell(i).getStringCellValue();
				String data =ju.getRandomNo()+ eu.getDataFromExcel("Patient Details", 2, j);
				driver.findElement(By.name(al3.get(j))).sendKeys(data);
				driver.findElement(By.id("submit")).click();
		}
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
		String patname = eu.getDataFromExcel("Patient Details", 1, 1);
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


	}

}
