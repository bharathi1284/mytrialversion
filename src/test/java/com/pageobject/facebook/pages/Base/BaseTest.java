package com.pageobject.facebook.pages.Base;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.pageobject.facebook.pages.nosession.Loginpage;
import com.pageobject.facebook.util.Constants;
import com.pageobject.facebook.util.ExtentManager;
import com.pageobject.facebook.util.XLS_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	public  WebDriver driver;
	public ExtentReports rep = ExtentManager.getInstance();
	public ExtentTest test;
	public XLS_Reader xls = new XLS_Reader(Constants.data_xls);
	
	public void init(String browsername){
		
		
		test.log(LogStatus.INFO,"opening browser");
		
		if(!Constants.grid_run){
		
			if(browsername.equals("Mozilla")){
				driver = new FirefoxDriver();
			}else if(browsername.equals("Chrome")){
			System.out.println(Constants.strchromedriverpath);
				System.setProperty("webdriver.chrome.driver", Constants.strchromedriverpath);
				DesiredCapabilities cap = new DesiredCapabilities();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				cap.setCapability(ChromeOptions.CAPABILITY,options);
				driver=new ChromeDriver(cap);
			}
		else{
			//grid
			DesiredCapabilities cap = null;
			if(browsername.equals("Mozilla")){
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}else if(browsername.equals("Chrome")){
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			
			try{
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"),cap);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(Constants.getEnvdetails().get("URL"));
	}
	
	public void reportfailure(String strerrormsg){
		test.log(LogStatus.FAIL, "Failing the test");
		takeScreenshot();
		Assert.fail(strerrormsg);
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
	
	
}
