package com.hms.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatientDashboardPage {

	//Declaration
	@FindBy(xpath = "//div[@class='panel-body']/p/a[contains(text(),'Update Profile')]")
	private WebElement PatientMyProfileLink;
	
	@FindBy(xpath = "//a[contains(text(),'View Appointment History')]")
	private WebElement PatientMyAppointmentsLink;
	
	@FindBy(xpath = "//a[contains(text(),'Book Appointment')]")
	private WebElement patientBookMyAppointmentLink;
	
	//Initialization
	public PatientDashboardPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getPatientMyProfileLink() {
		return PatientMyProfileLink;
	}

	public WebElement getPatientMyAppointmentsLink() {
		return PatientMyAppointmentsLink;
	}

	public WebElement getPatientBookMyAppointmentLink() {
		return patientBookMyAppointmentLink;
	}
	
	//Business Logic
	public void clickOnpatientMyProfile()
	{
		PatientMyProfileLink.click();
	}
	
	public void clickOnPatientMyAppointment()
	{
		PatientMyAppointmentsLink.click();
	}
	
	public void clickOnpatientBookMyAppointment()
	{
		patientBookMyAppointmentLink.click();
	}
}
