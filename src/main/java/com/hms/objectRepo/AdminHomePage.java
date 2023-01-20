package com.hms.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hms.GenericUtilites.ExcelUtility;
import com.hms.GenericUtilites.JavaUtility;
import com.hms.GenericUtilites.WebDriverUtility;

public class AdminHomePage extends WebDriverUtility{
			
	//Declaration
	@FindBy(xpath = "//span[.=' Dashboard ']")
	private WebElement AdmDashboardBtn;
	
	@FindBy(xpath = "//span[.=' Doctors ']")
	private WebElement AdmDoctorsDD;
	
	@FindBy(xpath = "//span[.=' Doctor Specialization ']")
	private WebElement AdmDoctorSpecBtn;
	
	@FindBy(name = "doctorspecilization")
	private WebElement docspecTxt;
	
	@FindBy(xpath = "//span[.=' Add Doctor']")
	private WebElement AdmAddDoctorBtn;
	
	@FindBy(xpath = "//span[.=' Manage Doctors ']")
	private WebElement AdmManageDoctor;
	
	@FindBy(xpath = "//span[.=' Users ']")
	private WebElement AdmUserDD;
	
	@FindBy(xpath = "//span[.=' Manage Users ']")
	private WebElement AdmManageUsersBtn;
	
	@FindBy(xpath = "//span[.=' Patients ']")
	private WebElement AdmPatientsDD;
	
	@FindBy(xpath = "//span[.=' Manage Patients ']")
	private WebElement AdmManagePatientsBtn;
	
	@FindBy(xpath = "//span[.=' Appointment History ']")
	private WebElement AdmAppointmentHistoryBtn;
	
	@FindBy(xpath = "//span[.=' Conatctus Queries ']")
	private WebElement AdmContactQueriesDD;
	
	@FindBy(xpath = "//span[.=' Unread Query ']")
	private WebElement AdmUnreadQueriesBtn;
	
	@FindBy(xpath = "//span[.=' Read Query ']")
	private WebElement AdmReadQueriesBtn;
	
	@FindBy(xpath = "//span[.=' Doctor Session Logs ']")
	private WebElement AdmDoctorSessionLogsBtn;
	
	@FindBy(xpath = "//span[.=' User Session Logs ']")
	private WebElement AdmUserSessionLogsBtn;
	
	@FindBy(xpath = "//span[.=' Reports ']")
	private WebElement AdmReportsDD;
	
	@FindBy(xpath = "//span[.='B/w dates reports ']")
	private WebElement AdmDatereportBtn;
	
	@FindBy(xpath = "//span[.=' Patient Search ']")
	private WebElement AdmPatientSearchBtn;

	@FindBy(xpath = "//li[@class='dropdown current-user open']/a/span/i[@class='ti-angle-down']")
	private WebElement AdmLogoutDD;
	
	@FindBy(partialLinkText = "Change Password")
	private WebElement AdmChangepasswordLink;
	
	@FindBy(partialLinkText = "Log Out")
	private WebElement AdmLogoutLink;
	
	@FindBy(name = "Doctorspecialization")
	private WebElement docSpecDD;
	
	@FindBy(name = "docname")
	private WebElement docNameTxt;
	
	@FindBy(name = "clinicaddress")
	private WebElement clinicAddressTxt;
	
	@FindBy(name = "docfees")
	private WebElement docFeesTxt;
	
	@FindBy(name = "doccontact")
	private WebElement docContactTxt;
	
	@FindBy(name = "docemail")
	private WebElement docEmailTxt;
	
	@FindBy(name = "npass")
	private WebElement docPasswordTxt;
	
	@FindBy(name = "cfpass")
	private WebElement docConfPassTxt;
	
	@FindBy(name = "submit")
	private WebElement subBtn;
	
	@FindBy(xpath = "//span[@class='username']")
	private WebElement ALogoutDD;
	
	@FindBy(partialLinkText = "Log Out")
	private WebElement ALogout;
	
	//Initialization
	public AdminHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	//Business Logic
	public void clickOnAdmDashboard()
	{
		AdmDashboardBtn.click();
	}
	
	public void addDoctorSpecilaization(ExcelUtility eu) throws Throwable
	{
		docspecTxt.sendKeys(eu.getDataFromExcel("Doctors Details", 1, 1));
		subBtn.click();
	}
	
	public void addDoctor(ExcelUtility eu, JavaUtility ju) throws Throwable
	{
		select(eu.getDataFromExcel("Doctors Details", 1, 1), docSpecDD);
		docNameTxt.sendKeys(eu.getDataFromExcel("Doctors Details", 1, 2));
		clinicAddressTxt.sendKeys(eu.getDataFromExcel("Doctors Details", 1, 3));
		docFeesTxt.sendKeys(eu.getDataFromExcel("Doctors Details", 1, 4));
		docContactTxt.sendKeys(eu.getDataFromExcel("Doctors Details", 1, 5));
		docEmailTxt.sendKeys(ju.getRandomNo() +eu.getDataFromExcel("Doctors Details", 1, 6));
		docPasswordTxt.sendKeys(eu.getDataFromExcel("Doctors Details", 1, 7));
		docConfPassTxt.sendKeys(eu.getDataFromExcel("Doctors Details", 1, 8));
		subBtn.click();
		
	}
	
	public void Admlogout()
	{
		ALogoutDD.click();	
		ALogout.click();
	}
	
	public void clickOnDoctorsDD()
	{
		AdmDoctorsDD.click();
	}
	
	public void clickOnDoctorSpecialization()
	{
		AdmDoctorsDD.click();
		AdmDoctorSpecBtn.click();
	}
	
	public void clickOnAddDoctor()
	{
		AdmDoctorsDD.click();
		AdmAddDoctorBtn.click();
	}
	
	public void clickOnManageDoctors()
	{
		AdmDoctorsDD.click();
		AdmManageDoctor.click();
	}
	
	public void clickOnUsersDD()
	{
		AdmUserDD.click();
	}
	
	public void clickOnManageUsers()
	{
		AdmUserDD.click();
		AdmManageUsersBtn.click();
	}
	
	public void clickOnPatientsDD()
	{
		AdmPatientsDD.click();
	}
	
	public void clickOnManagepatients()
	{
		AdmManagePatientsBtn.click();
	}
	
	public void clickOnAdminAppoinmentHistory()
	{
		AdmAppointmentHistoryBtn.click();
	}
	
	public void clickOnContactQueriesDD()
	{
		AdmContactQueriesDD.click();
	}
	
	public void clickOnUnreadQueries() 
	{
		AdmContactQueriesDD.click();
		AdmUnreadQueriesBtn.click();
	}
	
	public void clickOnReadQueries()
	{
		AdmContactQueriesDD.click();
		AdmReadQueriesBtn.click();
	}
	
	public void clickOnDoctorSessionLogs()
	{
		AdmDoctorSessionLogsBtn.click();
	}
	
	public void clickOnUserSessionLogs()
	{
		AdmUserSessionLogsBtn.click();
	}
	
	public void clickOnReportsDD()
	{
		AdmReportsDD.click();
	}
	
	public void clickOnDateReports()
	{
		AdmDatereportBtn.click();
	}
	
	public void clickOnPatientSearch()
	{
		AdmPatientSearchBtn.click();
	}
	
	public void clickOnContactQueries()
	{
		AdmContactQueriesDD.click();
	}
	public void clickOnUnreadQuery()
	{
		AdmUnreadQueriesBtn.click();
	}
	public void clickOnReadQuery()
	{
		AdmReadQueriesBtn.click();
	}
	
}
