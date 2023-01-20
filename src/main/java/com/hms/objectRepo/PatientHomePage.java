package com.hms.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hms.GenericUtilites.ExcelUtility;
import com.hms.GenericUtilites.JavaUtility;

public class PatientHomePage {
	
	//Declaration
	@FindBy(xpath = "//span[.=' Dashboard ']")
	private WebElement DashboardBtn;

	@FindBy(xpath = "//span[.=' Book Appointment ']")
	private WebElement bookApmtBtn;
	
	@FindBy(xpath = "//span[.=' Appointment History ']")
	private WebElement apmtHistoryBtn;
	
	@FindBy(xpath = "//span[.=' Medical History ']")
	private WebElement mediHistoryBtn;
	
	@FindBy(xpath = "//span[@class='username']")
	private WebElement pLogoutDD;
	
	@FindBy(partialLinkText = "My Profile")
	private WebElement pMyProfileDDLink;
	
	@FindBy(partialLinkText = "Change Password")
	private WebElement pChangePwdDDLink;
	
	@FindBy(partialLinkText = "Log Out")
	private WebElement pLogoutDDLink;
	
	@FindBy(xpath = "//a[@href='view-medhistory.php?viewid=1']/i")
	private WebElement eyeIcon;
	
	@FindBy(name = "patname")
	private WebElement pnameTxt;
	
	@FindBy(name = "patcontact")
	private WebElement pContactNoTxt;
	
	@FindBy(name = "patemail")
	private WebElement pEmailTxt;
	
	@FindBy(xpath = "//label[@for='rg-male']")
	private WebElement radioBtn;
	
	@FindBy(name = "pataddress")
	private WebElement pAddtxt;
	
	@FindBy(name = "patage")
	private WebElement pAgetxt;
	
	@FindBy(name = "medhis")
	private WebElement pMedHistorytxt;
	
	@FindBy(name = "submit")
	private WebElement PAddBtn;
	
	
	//Initialization
	public PatientHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	
	public void clickOnPatientMyProfileLink() throws InterruptedException
	{
		pLogoutDD.click();
		pMyProfileDDLink.click();
	}
	
	public void clickOnPatientChangePwdLink()
	{
		pLogoutDD.click();
		pChangePwdDDLink.click();
	}
	
	public void clickOnpatientLogout() throws Throwable
	{
		pLogoutDD.click();
		pLogoutDDLink.click();
	}
	
	public void clickOnBookMyAppointment()
	{
		bookApmtBtn.click();
	}
	
	public void clickOnMedicalHistoryBtn()
	{
		mediHistoryBtn.click();
	}
	public void clickOnMediEyeIcon()
	{
		eyeIcon.click();
	}
	public void addPatient(ExcelUtility eu,JavaUtility ju) throws Throwable
	{
		pnameTxt.sendKeys(eu.getDataFromExcel("Patient Details", 2, 1));
		pContactNoTxt.sendKeys(eu.getDataFromExcel("Patient Details", 2, 2));
		pEmailTxt.sendKeys(ju.getRandomNo()+eu.getDataFromExcel("Patient Details", 2, 3));
		radioBtn.click();
		pAddtxt.sendKeys(eu.getDataFromExcel("Patient Details", 2, 4));
		pAgetxt.sendKeys(eu.getDataFromExcel("Patient Details", 2, 5));
		pMedHistorytxt.sendKeys(eu.getDataFromExcel("Patient Details", 2, 6));
		PAddBtn.click();
		
	}
	
	
}