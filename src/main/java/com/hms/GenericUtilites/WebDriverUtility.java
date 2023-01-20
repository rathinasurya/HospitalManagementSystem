package com.hms.GenericUtilites;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class is used to create WebDriver specific methods
 * @author surya
 *
 */
public class WebDriverUtility {
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public void AlertSIsPresent(WebDriver driver)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	public String getAlertText(WebDriver driver)
	{
		String text = driver.switchTo().alert().getText();
		return text;
	}
	
	public void elementToBeVisible(WebDriver driver, WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void select(WebElement element, String value)
	{
		Select sel=new Select(element);
		sel.selectByValue(value);
	}
	
	public void select(WebElement element, int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	public void select(String visibleText, WebElement element)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(visibleText);
	}
	
	public void mouseHover(WebDriver driver, WebElement src, WebElement dst)
	{
		Actions act=new Actions(driver);
		act.dragAndDrop(src, dst);
	}

	public void doubleClickAction(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.doubleClick().perform();
	}
	
	public void rightClick(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	
	public void enterKeyPress(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	}
	
	public void enterKey(WebDriver driver) throws AWTException
	{
		Robot rb=new Robot();
		rb.keyPress(KeyEvent.VK_ENTER);
	}
	
	public void enterRelease(WebDriver driver) throws AWTException
	{
		Robot rb=new Robot();
		rb.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void switchToFrame(WebDriver driver, String NameOrId)
	{
		driver.switchTo().frame(NameOrId);
	}
	
	public void switchToFrame(WebDriver driver, WebElement address)
	{
		driver.switchTo().frame(address);
	}
	
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	public void cancelAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public void switchToWindow(WebDriver driver, String partialTitle)
	{
		//step 1: getWindowHandles to capture all the windows
		Set<String> windows = driver.getWindowHandles();
		
		//step 2: iterate through the windows
		Iterator<String> it=windows.iterator();
		
		//step 3: check whether there is new window
		while(it.hasNext())
		{
			//step 4: capture current window id
			String winId = it.next();
			
			//step 5: switch to current window and capture title
			String currentWinTitle = driver.switchTo().window(winId).getTitle();
			
			//step 6: check whether current window is expected
			if(currentWinTitle.equals(partialTitle))
			{
				break;
			}
			
		}
	}
	
	public static String getScreenShot(WebDriver driver, String screenShotName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path=".//screenshot//"+screenShotName+".png";
		File dst=new File(path);
		FileUtils.copyFile(src, dst);
		return path;
	}
	
	public void scrollBarAction(WebDriver driver)
	{
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,800)", "");
	}
	
	public void scrollAction(WebDriver driver, WebElement element)
	{
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		int y=element.getLocation().getY();
		jse.executeScript("window.scrollBy(0,"+y+")", element);
		//jse.executeScript("argument[0].scrollIntoView()", element);
	}
}

