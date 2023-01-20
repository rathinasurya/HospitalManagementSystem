package com.hms.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hms.GenericUtilites.ExcelUtility;

public class ContactPage {
	@FindBy(name = "fullname")
	private WebElement nameTxt;
	
	@FindBy(name = "emailid")
	private WebElement emailTxt;
	
	@FindBy(name = "mobileno")
	private WebElement MobNoTxt;
	
	@FindBy(name = "description")
	private WebElement descTxt;
	
	@FindBy(name = "submit")
	private WebElement submitBtn;
	
	@FindBy(xpath = "//div[@class='top-nav']/ul/li[1]/a")
	private WebElement homeBtn;
	
	public ContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void CreateNewContact(ExcelUtility eu) throws Throwable
	{
		nameTxt.sendKeys(eu.getDataFromExcel("User's History", 1, 0));
		emailTxt.sendKeys(eu.getDataFromExcel("User's History", 1, 1));
		MobNoTxt.sendKeys(eu.getDataFromExcel("User's History", 1, 2));
		descTxt.sendKeys(eu.getDataFromExcel("User's History", 1, 3));
		submitBtn.click();
	}
	public void clickOnHome()
	{
		
		homeBtn.click();
	}

}
