package Practice_package;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class MakeMytripBuses {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//li[@class='makeFlex hrtlCenter font10 makeRelative lhUser userLoggedOut']")).click();
		driver.findElement(By.xpath("//a[@href='https://www.makemytrip.com/bus-tickets/']")).click();
		driver.findElement(By.xpath("//div[@class='bussw_inputBox selectHtlCity  ']")).click();
		driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("chennai");
		driver.findElement(By.xpath("//span[.='Chennai, Tamil Nadu']")).click();
		driver.findElement(By.xpath("//div[@class='bussw_inputBox selectHtlCity inactiveWidget activeWidget']")).click();
		driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("bangalore");
		driver.findElement(By.xpath("//span[.='Bangalore, Karnataka']")).click();
		driver.findElement(By.id("travelDate")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@aria-label='Fri Jan 13 2023']")).click();
		driver.findElement(By.xpath("//button[.='Search']")).click();
	}

}
