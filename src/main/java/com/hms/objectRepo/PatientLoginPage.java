package com.hms.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatientLoginPage  {
	
	//Declaration
	@FindBy(name = "username")
	private WebElement pUnTxtBox;
	
	@FindBy(name = "password")
	private WebElement PPwdTxtBox;

	@FindBy(name = "submit")
	private WebElement lgnBtn;
	
	@FindBy(partialLinkText = "Create an account")
	private WebElement createAnAcntLink;
	
	@FindBy(partialLinkText = "Forgot Password ?")
	private WebElement pForgotPwdLink;
	
	//Initialization
	public PatientLoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getpUnTxtBox() {
		return pUnTxtBox;
	}

	public WebElement getPPwdTxtBox() {
		return PPwdTxtBox;
	}

	public WebElement getLgnBtn() {
		return lgnBtn;
	}
	
	public WebElement getCreateAnAcntLink() {
		return createAnAcntLink;
	}

	public WebElement getpForgotPwdLink() {
		return pForgotPwdLink;
	}

	public void loginToPatient(String username,String password)  throws Throwable
	{
		pUnTxtBox.sendKeys(username);
		PPwdTxtBox.sendKeys(password);
		lgnBtn.click();
	}
	
	public void clickOnCreateAnAcntLink()
	{
		createAnAcntLink.click();
	}
	
	public void clickOnPatForgotPwdLink()
	{
		pForgotPwdLink.click();
	}

	 
		
	}
	
