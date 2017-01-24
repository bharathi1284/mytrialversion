package com.pageobject.facebook.pages.testcase;


import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageobject.facebook.pages.Base.BaseTest;
import com.pageobject.facebook.pages.nosession.Launchpage;
import com.pageobject.facebook.pages.nosession.Loginpage;
import com.pageobject.facebook.pages.session.Landingpage;
import com.pageobject.facebook.util.Datautil;
import com.relevantcodes.extentreports.LogStatus;

public class ChangePassword extends BaseTest {
	
	String testcasename = "ChangePassword";
	@Test(dataProvider = "getData")
	public void changepwd(Hashtable<String,String> data){
		test = rep.startTest(testcasename);
		if(data.get("Runmode").equalsIgnoreCase("N")){
			test.log(LogStatus.SKIP, "Skipping Changepassword test");
			throw new SkipException("skipping change password test");
		}
		test.log(LogStatus.INFO, "Starting Changepassword test");
		init(data.get("BROWSER"));
		Launchpage launchpage = new Launchpage(driver,test);
		PageFactory.initElements(driver, launchpage);
		Loginpage loginpage = launchpage.gotoLoginpage();
		PageFactory.initElements(driver, loginpage);
		test.log(LogStatus.INFO, "Verifying title");
		loginpage.verifytitle("login page");
		Object page = loginpage.doLogin(data.get("Username"), data.get("Password"));
		String actualresult = "";
		if(page instanceof Landingpage){
			actualresult = "Success";}
		else{
			actualresult = "Unsuccessful";
		}
		
		if(!actualresult.equalsIgnoreCase(data.get("ExpectedResult"))){
			reportfailure("Exp Result " + data.get("ExpectedResult") + "--" + "Act Result: " + actualresult);
		}
		
		Landingpage landingpage = (Landingpage)page;
		
	}
	
	@DataProvider
	public Object[][] getData(){
		return Datautil.getdata(xls, testcasename);
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
}
