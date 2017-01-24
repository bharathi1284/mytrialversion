package com.pageobject.facebook.pages.testcase;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageobject.facebook.pages.Base.BaseTest;
import com.pageobject.facebook.pages.nosession.Launchpage;
import com.pageobject.facebook.pages.nosession.Loginpage;
import com.pageobject.facebook.pages.session.Landingpage;
import com.pageobject.facebook.util.Constants;
import com.pageobject.facebook.util.Datautil;
import com.pageobject.facebook.util.ExtentManager;
import com.pageobject.facebook.util.XLS_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends BaseTest {
	
	String testcasename = "LoginTest";
	@Test(dataProvider="getdata")
	public  void doLogin(Hashtable<String,String> data){
		test = rep.startTest("LoginTest");
		
		if (!(Datautil.isTestExecutable(xls,testcasename)) || (data.get("Runmode").equalsIgnoreCase("n"))){
			test.log(LogStatus.SKIP, "Skipping test");
			throw new SkipException(testcasename);
		}
		test.log(LogStatus.INFO, "Starting login test");
		test.log(LogStatus.INFO, "Opening browser");
		init(data.get("Browser"));
		Launchpage launchpage = new Launchpage(driver,test);
		PageFactory.initElements(driver, launchpage);
		launchpage.verifytitle("launch page");
		test.log(LogStatus.INFO, "Go to login page");
		Loginpage loginpage = launchpage.gotoLoginpage();
		test.log(LogStatus.INFO, "Verifying title");
		loginpage.verifytitle("login page");
		Object page  = loginpage.doLogin(data.get("Username"), data.get("Password"));
		String actualresult = "";
		if(page instanceof Landingpage){
			actualresult = "Success";
		}
		else{
			actualresult = "Unsuccessful";	
		}
			
		if(!actualresult.equalsIgnoreCase(data.get("ExpectedResult"))){
			reportfailure("Exp Result " + data.get("ExpectedResult") + "--" + "Act Result: " + actualresult);
		}
		
		Landingpage landingpage = (Landingpage)page;
		landingpage.getmenu().logout();
		test.log(LogStatus.INFO, "logged out");
		test.log(LogStatus.PASS, "logging in passed");
		
		
		//landingpage.gotoprofilepage();
	}
	
	@AfterMethod
	public void endtest(){
		if(rep!=null){
			rep.endTest(test);
			rep.flush();
		}
		if(driver!=null){
			driver.quit();
		}
	}	
	
	@DataProvider
	public Object[][] getdata(){
		return Datautil.getdata(xls, testcasename);
		
	}
	
}

