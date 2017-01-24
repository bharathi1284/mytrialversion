package com.pageobject.facebook.pages.Basepage;


import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.pageobject.facebook.pages.common.Topmenu;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Basepage {
	
	public WebDriver driver;
	public Topmenu menu;
	public ExtentTest test;
	
	public Basepage(){
		
	}
	
	public Basepage(WebDriver driver,ExtentTest test){
		this.driver = driver;
		this.test = test;
		menu = new Topmenu(driver,test);
		PageFactory.initElements(driver, menu);
	}

	public void verifytitle(String exptitle){
		System.out.println(exptitle);
	}
	
	public String verifytext(String locator,String exptest){
		return "";
	}
	
	public boolean isElementPresent(String locator){
		List<WebElement> element1 = driver.findElements(By.xpath(locator));
		if(element1.size()==0){
			return false;
		}else{
			return true;
		}
		
	}
	
	public boolean isElementDisplayed(WebElement element1){
		try{
		if(element1.isDisplayed()){
			return true;
		}}catch(Exception e){
			
		}
		return false;
		
	}
	
	public Topmenu getmenu(){
		return menu;
	}
	
	public void takeScreenshot(){
		Date d = new Date();
		String dateval = d.toString().replace(":", "_").replace(" ", "_");
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destfile = System.getProperty("user.dir") + "\\Screenshots\\Screenshot_" + dateval;
		try{
			FileUtils.copyFile(srcFile, new File(destfile) );
		}catch(Exception e){
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL, test.addScreenCapture(destfile));
		
	}
	
	public void reportfailure(String strerrormsg){
		test.log(LogStatus.FAIL, "Failing the test");
		takeScreenshot();
		Assert.fail(strerrormsg);
	}
}
