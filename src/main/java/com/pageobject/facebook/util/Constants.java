package com.pageobject.facebook.util;

import java.util.Hashtable;

public class Constants {
	
	public static final boolean grid_run = false;
	public static final String strpageurl = "http://facebook.com";
	
	public static final String strchromedriverpath = "C:\\Users\\swami\\Downloads\\chromedriver_win32\\chromedriver.exe";
	public static final String email = "email";
	public static final String password = "pass";
	public static final String login = "//label[@id='loginbutton']/input";
	public static final String landingpgchk = "pagelet_welcome_box";
	public static final String nav_label = "userNavigationLabel";
	public static final String logout_btn = "show_me_how_logout_1";
	
	public static final String data_xls = System.getProperty("user.dir")+"\\data\\Data.xlsx";
	public static final String sheet_name = "testdata";
	public static final String testcases_sheet_name = "testcases";
	public static final String reports_path = "C:\\Kalpan\\secondroundofselenium\\pageobject\\test-output";
	
	public static final String ENV = "QA";
	
	//for different environments have the username,pwd and url here
	
	public static Hashtable<String,String> table;
	public static Hashtable<String,String> getEnvdetails(){
		if(table==null){
			table = new Hashtable<String,String>();
			if(ENV.equalsIgnoreCase("QA")){
				table.put("URL", strpageurl);
			}
		}
		//else if dev then dev url
		return table;
		
	}
}
