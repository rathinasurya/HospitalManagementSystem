package Practice_package;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.hms.GenericUtilites.WebDriverUtility;

public class MicTest {

	public static void main(String[] args) throws Throwable {
//		ChromeOptions option=new ChromeOptions();
//		option.addArguments("--disable-popup-notifications");
		WebDriver driver=new ChromeDriver();
		driver.get("https://mictests.com/");
		Thread.sleep(3000);
		driver.findElement(By.id("mic-launcher")).click();
		Thread.sleep(3000);
		Set<String> win = driver.getWindowHandles();
		Iterator<String> it = win.iterator();
		while(it.hasNext())
		{
			String winId = it.next();
		driver.switchTo().window(winId).close();
		}
		
		
		
	}

}
