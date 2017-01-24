package com.pageobject.facebook.util;

import java.util.Hashtable;

public class Datautil {
	
	public static Object[][] getdata(XLS_Reader xls,String testcasename){
		String strsheetname = Constants.sheet_name;
		int teststartrownum = 1;
		while(!(xls.getCellData(strsheetname, 0, teststartrownum).equals(testcasename))){
			teststartrownum = teststartrownum + 1;
		}
		System.out.println(teststartrownum);
		
		int colstartrownum = teststartrownum + 1;
		int datastartrownum = teststartrownum + 2;
		
		int rows = 0;
		while(!xls.getCellData(strsheetname, 0,datastartrownum+rows).equals("")){
			rows++;
		}
		
		System.out.println("total rows are " + rows);
		
		int cols = 0;
		while(!xls.getCellData(strsheetname, cols, colstartrownum).equals("")){
			cols++;			
		}
		System.out.println("total cols are " + cols);
		
		Object[][] data = new Object[rows][1];
		int datarow= 0;
		Hashtable<String,String> table = null;
		for(int rownum = datastartrownum;rownum<datastartrownum+rows;rownum++ ){
			table = new Hashtable<String,String>();
			for(int colnum = 0;colnum<cols;colnum++){
				String key = xls.getCellData(strsheetname, colnum, colstartrownum);
				String value = xls.getCellData(strsheetname, colnum, rownum);
				System.out.println(key);
				System.out.println(value);
				table.put(key, value);
				//	data[datarow][colnum] = xls.getCellData(strsheetname, colnum, rownum);
			}
			data[datarow][0] = table;
			datarow++;
		}
		
		return data;
		
	}
	
	public static boolean isTestExecutable(XLS_Reader xls,String testcasename){
		boolean executeflag = false;
		int rowcount= xls.getRowCount(Constants.testcases_sheet_name);
		for(int rnum = 2;rnum<=rowcount;rnum++){
			String tcid = xls.getCellData(Constants.testcases_sheet_name, 0, rnum);
			String runmode = xls.getCellData(Constants.testcases_sheet_name, 1, rnum);
			if(testcasename.equalsIgnoreCase(tcid)){
				if(runmode.equalsIgnoreCase("Y")){
					executeflag = true;
				}else{
					executeflag = false;
				}
			}
		}
		return executeflag;
	}
		
	}


