package TestUsingGenericLib;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.hms.GenericUtilites.ExcelUtility;
import com.hms.GenericUtilites.FileUtility;
import com.hms.GenericUtilites.JavaUtility;
import com.hms.GenericUtilites.WebDriverUtility;

public class ContactQueriesTest {

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
		driver.findElement(By.xpath("//a[.='contact']")).click();
		
		ArrayList<String> al2=new ArrayList<String>();
		al2.add("fullname");
		al2.add("emailid");
		al2.add("mobileno");
		al2.add("description");
		
		int sh2 = eu.getRowCount("User's History");
		for(int i=0;i<sh2;i++)
		{
			for(int j=0;j<=eu.getCellCount("User's History", 1);j++)
			{
			String data=eu.getDataFromExcel("User's History", 1, j);
			driver.findElement(By.name(al2.get(j))).sendKeys(data);
			driver.findElement(By.name("submit")).click();
		}
		}
		wdu.acceptAlert(driver);
		Thread.sleep(3000);
		//String ConfirmText = driver.switchTo().alert().getText();
		//System.out.println(ConfirmText+" pop-up is dispalyed");
		//wdu.acceptAlert(driver);
		//driver.findElement(By.name("submit")).click();
		//Thread.sleep(3000);
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
		
	}

}
