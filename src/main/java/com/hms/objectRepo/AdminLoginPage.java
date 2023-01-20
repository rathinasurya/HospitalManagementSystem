package com.hms.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginPage {

		//Declaration
		@FindBy(name = "username")
		private WebElement AdmUnTxtBox;
		
		@FindBy(name = "password")
		private WebElement AdmPwdTxtBox;
		
		@FindBy(name = "submit")
		private WebElement AdmLgnBtn;
		
		//Initialization
		public AdminLoginPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		//Utilization
		public WebElement getAdmUnTxtBox() {
			return AdmUnTxtBox;
		}

		public WebElement getAdmPwdTxtBox() {
			return AdmPwdTxtBox;
		}

		public WebElement getAdmLgnBtn() {
			return AdmLgnBtn;
		}
		
		public void LoginToAdmin(String Adusername, String Adpassword)
		{
			AdmUnTxtBox.sendKeys(Adusername);
			AdmPwdTxtBox.sendKeys(Adpassword);
			AdmLgnBtn.click();
		}
		
		
		
	}


