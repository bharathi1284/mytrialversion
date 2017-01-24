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
import com.pageobject.facebook.pages.session.Profilepage;
import com.pageobject.facebook.util.Datautil;
import com.relevantcodes.extentreports.LogStatus;

public class EditProfile extends BaseTest {
	String testcasename = "EditProfile";
	@Test(dataProvider="getdata")
	public void testprofile(Hashtable<String,String> data){
		
	
		
		test = rep.startTest("EditProfile");
		
		if (!(Datautil.isTestExecutable(xls,testcasename)) || (data.get("Runmode").equalsIgnoreCase("n"))){
			test.log(LogStatus.SKIP, "Skipping test");
			throw new SkipException(testcasename);
		}
		
		test.log(LogStatus.INFO, "Starting profile test");
		test.log(LogStatus.INFO, "Opening browser");
		init("Mozilla");
		Launchpage launchpage = new Launchpage(driver,test);
		PageFactory.initElements(driver, launchpage);
		launchpage.verifytitle("launch page");
		test.log(LogStatus.INFO, "Go to login page");
		Loginpage loginpage = launchpage.gotoLoginpage();
		test.log(LogStatus.INFO, "Verifying title");
		loginpage.verifytitle("login page");
		Object page  = loginpage.doLogin("bharathi_sbn@yahoo.co.in", "founder3$");
		if(page instanceof Loginpage ){
			Assert.fail("login failed");
		}
		test.log(LogStatus.INFO, "logged in");
		Landingpage landingpage = (Landingpage)page;
		//test.log(LogStatus.INFO, "go to profile page and verify profile");
		Profilepage profilepage = landingpage.gotoprofilepage();
		profilepage.verifyProfile();
		
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
