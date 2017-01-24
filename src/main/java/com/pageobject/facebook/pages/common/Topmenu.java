package com.pageobject.facebook.pages.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pageobject.facebook.util.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Topmenu {
	
	@FindBy(id=Constants.nav_label)
	public WebElement nav_label;
	
	@FindBy(id=Constants.logout_btn)
	public WebElement logout_btn;
	
	WebDriver driver;
	ExtentTest test;
	
	public Topmenu(WebDriver driver,ExtentTest test){
		this.driver = driver;
		this.test = test;
	}
	
	public void logout(){
		test.log(LogStatus.INFO, "logging out");
		nav_label.click();
		logout_btn.submit();
		
	}
}
