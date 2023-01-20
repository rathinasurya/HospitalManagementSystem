package com.hms.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DoctorLoginPage {
	
	//Declaration
	@FindBy(name = "username")
	private WebElement DUnTxtBox;
	
	@FindBy(name = "password")
	private WebElement DPwdTxtBox;
	
	@FindBy(name = "submit")
	private WebElement DLgnBtn;
	
	@FindBy(partialLinkText = "Forgot Password ?")
	private WebElement DForgotPwdLink;

	//Initialization
	public DoctorLoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getDUnTxtBox() {
		return DUnTxtBox;
	}

	public WebElement getDPwdTxtBox() {
		return DPwdTxtBox;
	}

	public WebElement getDLgnBtn() {
		return DLgnBtn;
	}

	public WebElement getDocForgotPwdLink() {
		return DForgotPwdLink;
	}
	
	public void LoginToDoctor(String username,String password)
	{
		DUnTxtBox.sendKeys(username);
		DPwdTxtBox.sendKeys(password);
		DLgnBtn.click();
	}
	
	public void clickOnDocForgotPwdLink()
	{
		DForgotPwdLink.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
