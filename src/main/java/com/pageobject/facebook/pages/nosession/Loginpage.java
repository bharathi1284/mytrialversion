package com.pageobject.facebook.pages.nosession;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageobject.facebook.pages.Basepage.Basepage;
import com.pageobject.facebook.pages.session.Landingpage;
import com.pageobject.facebook.util.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Loginpage extends Basepage {
	
	
	public Loginpage(WebDriver driver,ExtentTest test){
		super(driver,test);
	}
	
	@FindBy(id=Constants.email)
	public WebElement email; 
	
	@FindBy(id=Constants.password)
	public WebElement password;
	
	@FindBy(xpath=Constants.login)
	public WebElement login;
	
	@FindBy(id=Constants.landingpgchk)
	public WebElement landingpgcheck;
	

	
	public Object doLogin(String uname,String pwd){
		test.log(LogStatus.INFO, "Entering username and password");
		email.sendKeys(uname);
		password.sendKeys(pwd);
		login.click();
		
		boolean loggedin = isElementDisplayed(landingpgcheck);
		if(loggedin){
			Landingpage landingpage = new Landingpage(driver,test);
			PageFactory.initElements(driver, landingpage);
			test.log(LogStatus.INFO, "Login Success");
			return landingpage;
			
		}
		else{
			Loginpage loginpage = new Loginpage(driver,test);
			PageFactory.initElements(driver, loginpage);
			test.log(LogStatus.INFO, "Login unsuccessful");
			return loginpage;
			
		}
		//isElementPresent("//*[@id='pagelet_welcome_box']");
		//if(isElementPresent(true))
		
		
	}

}
