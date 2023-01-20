package com.hms.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hms.GenericUtilites.ExcelUtility;

public class DoctorHomePage {
	
	//Declaration
	@FindBy(xpath = "//span[.=' Dashboard ']")
	private WebElement DocDashboardBtn;
	
	@FindBy(xpath = "//span[.=' Appointment History ']")
	private WebElement DocApmtHistoryBtn;
	
	@FindBy(xpath = "//span[.=' Patients ']")
	private WebElement PatientDD;
	
	@FindBy(xpath = "//span[.=' Add Patient']")
	private WebElement AddpatientBtn;
	
	@FindBy(xpath = "//span[.=' Manage Patient ']")
	private WebElement ManagePatientBtn;
	
	@FindBy(xpath = "//span[.=' Search ']")
	private WebElement searchBtn;
	
	@FindBy(name = "searchdata")
	private WebElement searchPatient;
	
	@FindBy(name = "search")
	private WebElement searchingBtn;
	
	@FindBy(xpath = "//a[@href='view-patient.php?viewid=3']/i")
	private WebElement eyeIcon;
	
	@FindBy(xpath = "//button[.='Add Medical History']")
	private WebElement addMediHistoryBtn;
	
	@FindBy(name = "bp")
	private WebElement bpTxt;
	
	@FindBy(name = "bs")
	private WebElement bsTxt;
	
	@FindBy(name = "weight")
	private WebElement weightTxt;
	
	@FindBy(name ="temp" )
	private WebElement temptxt;
	
	@FindBy(name ="pres" )
	private WebElement prescriptionTxt;
	
	@FindBy(xpath = "//button[@class='btn btn-primary']")
	private WebElement submitBtn;
	
	@FindBy(xpath = "//span[@class='username']")
	private WebElement DLogoutDD;
	
	@FindBy(partialLinkText = "Log Out")
	private WebElement DLogout;
	
	@FindBy(xpath = "(//i[@class='fa fa-times fa fa-white'])[position()>25]")
	private WebElement removeDoctor;
	
	//Initialization
	public DoctorHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	
	public void clickOnDocDashboard()
	{
		DocDashboardBtn.click();
	}
	public void clickOnDocApmtHistory()
	{
		DocApmtHistoryBtn.click();
	}
	public void clickOnPatientDD()
	{
		PatientDD.click();
	}
	public void clickOnAddPatient()
	{
		PatientDD.click();
		AddpatientBtn.click();
	}
	public void clickOnManagePatient()
	{
		PatientDD.click();
		ManagePatientBtn.click();
	}
	
	public void clickOnSearchPatient()
	{
		searchBtn.click();
	}
	public void searchpatient(ExcelUtility eu) throws Throwable
	{
		searchPatient.sendKeys(eu.getDataFromExcel("Search Patient", 0, 1));
		searchingBtn.click();
	}
	public void clickOnPatientviewEyeIcon()
	{
		eyeIcon.click();
	}
	public void AddMediHistory(ExcelUtility eu) throws Throwable
	{
		bpTxt.sendKeys(eu.getDataFromExcel("Patient Details", 1, 7));
		bsTxt.sendKeys(eu.getDataFromExcel("Patient Details", 1, 8));
		weightTxt.sendKeys(eu.getDataFromExcel("Patient Details", 1, 9));
		temptxt.sendKeys(eu.getDataFromExcel("Patient Details", 1, 10));
		prescriptionTxt.sendKeys(eu.getDataFromExcel("Patient Details", 1, 11));
		submitBtn.click();
		
	}
	public void clickOnAddMediHistoryBtn()
	{
		addMediHistoryBtn.click();
	}
	
	public void Dlogout()
	{
		DLogoutDD.click();	
		DLogout.click();
	}
	public void removeDoctor()
	{
		removeDoctor.click();
	}
	
	
	
		

}
