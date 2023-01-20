package Practice_package;

import java.security.DrbgParameters.NextBytes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

public class RMGYantra {
	static {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
	}

	public static void main(String[] args) throws SQLException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://rmgtestingserver:8084/");
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		driver.findElement(By.name("projectName")).sendKeys("HMS005");
		// driver.findElement(By.name("teamSize")).sendKeys("3");
		driver.findElement(By.name("createdBy")).sendKeys("jackson");
		WebElement dropdown = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select[@name='status']"));
		Select s = new Select(dropdown);
		s.selectByValue("Created");
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		Connection con = null;
		try {
			Driver drive = new Driver();
			DriverManager.registerDriver(drive);
			con = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects", "root@%", "root");
			Statement state = con.createStatement();
			String query = "select * from project where Created_by='surya';";
			ResultSet result = state.executeQuery(query);
			boolean flag = false;
			while (result.next()) {
				String actualproject = result.getString(4);
				System.out.println(actualproject);
				if (actualproject.equalsIgnoreCase("HMS")) {
					System.out.println("Project Sucessfully Created");
					flag = true;
					break;
				}
			}
			if (flag == false) 
				System.out.println("Project not Created");
		} catch (Exception e) {

		} finally {
			con.close();
			System.out.println("database closed sucessfully");
			driver.close();
		}

	}
	
}
