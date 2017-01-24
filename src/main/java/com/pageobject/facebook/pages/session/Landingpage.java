package com.pageobject.facebook.pages.session;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageobject.facebook.pages.Basepage.Basepage;
import com.relevantcodes.extentreports.ExtentTest;

public class Landingpage extends Basepage{
	

	@FindBy(xpath="//a[@title='Profile']")
	public WebElement Profile;
	
	public Landingpage(WebDriver driver,ExtentTest test){
		super(driver,test);
	}

	public Profilepage gotoprofilepage() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Profile.click();
		Profilepage profilepage = new Profilepage(driver,test);
		PageFactory.initElements(driver, profilepage);
		return profilepage;
	}
}
