package com.hms.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {
		//Declaration
		@FindBy(xpath = "//h3[.='Patients']/../div/span/a[.='Click Here']")
		private WebElement pLoginBtn;
		
		@FindBy(xpath = "//h3[.='Doctors Login']/../div/span/a[.='Click Here']")
		private WebElement dLoginBtn;
		
		@FindBy(xpath = "//h3[.='Admin Login']/../div/span/a[.='Click Here']")
		private WebElement aLoginBtn;
		
		@FindBy(xpath = "//div[@class='top-nav']/ul/li/a[.='contact']")
		private WebElement contactBtn;
		
		//Initialization
		public WelcomePage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		//Utilization

		public WebElement getpLoginBtn() {
			return pLoginBtn;
		}

		public WebElement getdLoginBtn() {
			return dLoginBtn;
		}

		public WebElement getaLoginBtn() {
			return aLoginBtn;
		}

		public WebElement getContactBtn() {
			return contactBtn;
		}
		
		public void clickOnPatientLogin()
		{
			pLoginBtn.click();
		}
		
		public void clickOnDoctorLogin()
		{
			dLoginBtn.click();
		}
		
		public void clickOnAdminLogin()
		{
			aLoginBtn.click();
		}
		public void clickOnContactBtn()
		{
			contactBtn.click();
		}
	
		
		
	}


