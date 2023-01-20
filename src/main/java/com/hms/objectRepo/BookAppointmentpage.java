package com.hms.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.hms.GenericUtilites.ExcelUtility;
import com.hms.GenericUtilites.WebDriverUtility;

public class BookAppointmentpage extends WebDriverUtility {
	@FindBy(name = "Doctorspecialization")
	private WebElement docSpecDD;
	
	@FindBy(name = "doctor")
	private WebElement docDD;
	
	@FindBy(name = "appdate")
	private WebElement dateTxtField;
	
	@FindBy(name = "apptime")
	private WebElement timeTxtField;
	
	@FindBy(xpath = "//th[.='\" +MonthAndYear+ \"']/../../../../../div[1]/table/tbody/tr/td[@class='day' and .='\" +date+ \"']")
	private WebElement calenderpopup;
	
	@FindBy(name = "submit")
	private WebElement submitBtn;
	
	public WebElement getCalenderpopup() {
		return calenderpopup;
	}

	public BookAppointmentpage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void bookAppointment(ExcelUtility eu) throws Throwable
	{
		select(eu.getDataFromExcel("Booking Details", 1, 1), docSpecDD);
		select(eu.getDataFromExcel("Booking Details", 1, 2), docDD);
		dateTxtField.sendKeys(eu.getDataFromExcel("Booking Details", 1, 3));
		timeTxtField.sendKeys(eu.getDataFromExcel("Booking Details", 1, 4));
		submitBtn.click();
		
		
	}

}
