package com.pageobject.facebook.util;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;
	static String reportPath = System.getProperty("user.dir") + "\\test-output\\";
	
	public static ExtentReports getInstance(){
		if(extent==null){
			Date d = new Date();
			String fileName=d.toString().replace(":", "_").replace(" ", "_") + ".html";
			extent = new ExtentReports(reportPath + fileName + ".html",true,DisplayOrder.NEWEST_FIRST);
			extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
			extent.addSystemInfo("selenium version","2.53.0").addSystemInfo("Environment","QA");
		}
		
		
		return extent;
		
	}
}
