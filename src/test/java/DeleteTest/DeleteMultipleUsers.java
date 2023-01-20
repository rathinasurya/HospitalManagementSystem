package DeleteTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DeleteMultipleUsers {
	public static void main(String[] args) throws IOException {
	FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");
	Properties pObj1 = new Properties();
	pObj1.load(fis);
	
	String URL = pObj1.getProperty("url");
	String ADMIN_USERNAME = pObj1.getProperty("admin_username");
	String ADMIN_PASSWORD = pObj1.getProperty("admin_password");

	FileInputStream fis1 = new FileInputStream("./src/test/resources/TestData.xlsx");
	WorkbookFactory.create(fis1);

	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get(URL);
	driver.findElement(By.xpath("//h3[.='Admin Login']/following-sibling::div[@class='button']/span/a")).click();
	driver.findElement(By.name("username")).sendKeys(ADMIN_USERNAME);
	driver.findElement(By.name("password")).sendKeys(ADMIN_PASSWORD);
	driver.findElement(By.xpath("//button")).click();
	driver.findElement(By.xpath("//span[.=' Doctors ']")).click();
	driver.findElement(By.xpath("//span[.=' Manage Doctors ']")).click();
	for(int i=0;i<100;i++)
	{
	driver.findElement(By.xpath("(//i[@class='fa fa-times fa fa-white'])[position()>25]")).click();
	driver.switchTo().alert().accept();
	}
	}

	}
