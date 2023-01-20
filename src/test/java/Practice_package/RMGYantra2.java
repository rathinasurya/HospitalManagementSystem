package Practice_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

public class RMGYantra2 {

	public static void main(String[] args) throws SQLException {
		Connection con = null;
		Driver driver1 = new Driver();
		int result = 0;

		try {
			DriverManager.registerDriver(driver1);
			con = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects", "root@%", "root");
			Statement state = con.createStatement();
			String query = "insert into project values('Project_005','21/12/2022','surya','HMS_002','created',3);";
			result = state.executeUpdate(query);

		} catch (Exception e) {
		} finally {
			con.close();
			if (result == 1) {
				System.out.println("project was created");

			} else {
				System.out.println("project not created");
			}
			System.out.println("database sucessfully closed");

		}
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://rmgtestingserver:8084/");
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		driver.findElement(By.linkText("Projects")).click();
		boolean myproject = driver.findElement(By.xpath("//td[.='Project_005']")).isDisplayed();
		if (myproject == true) {
			System.out.println("My project is dispalyed");
		} else
			System.out.println("My project is not visible");
	}

}
