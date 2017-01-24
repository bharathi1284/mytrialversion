package com.pageobject.facebook.pages.nosession;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.pageobject.facebook.pages.Basepage.Basepage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Launchpage extends Basepage {
	public Launchpage(WebDriver driver,ExtentTest test){
		super(driver,test);		
	}
	
	public Loginpage gotoLoginpage(){
		test.log(LogStatus.INFO,"go to login");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://facebook.com");
		Loginpage loginpage = new Loginpage(driver,test);
		PageFactory.initElements(driver, loginpage);
		return loginpage;
		
	}
}
