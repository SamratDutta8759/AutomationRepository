package com.learning.utilities;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import com.learning.base.TestBase;

public class TestUtils extends TestBase {
	
	public static String screenShotPath;
	public static String screenShotName;
	
	public static String getUniqueName() {
		Date d = new Date();
		String name = d.toGMTString().replace(" ", "_").replace(":", "");
		return name;
	}
	
	public static void createFolder(String path) {
		path=path+getUniqueName();
		File f1 = new File(path);
		f1.mkdir();
	}
	
	public static void captureScreenshot() {
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		screenShotName = getUniqueName()+".jpg";
		screenShotPath = System.getProperty("user.dir")+"\\Output\\Screenshots\\"+screenShotName;
		try {
			FileUtils.copyFile(screenshot, new File(screenShotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static boolean isTestRunnable(String testCaseName, ExcelReader excel) {
		
		String sheetName = "testBaseSetup";
		int rows = excel.getRowCount(sheetName);
		for(int i =2; i<=rows; i++) {
			String testName = excel.getCellData(sheetName, "TCID" , i);
			if(testCaseName.equals(testName)) {
				String runMode =  excel.getCellData(sheetName, "RunMode" , i);
				if(runMode.equalsIgnoreCase("Y")||runMode.equalsIgnoreCase("Yes")) {
					return true;
				}
				else 
					return false;
			}
		}
		return false;
	}
	
	@DataProvider(name="getExcelData")
	public Object[][] getData(Method m){
		String sheetname = m.getName();
		HashMap<String,String> map=null;
		int rows = excel.getRowCount(sheetname);
		int cols = excel.getColumnCount(sheetname);
		Object[][] data = new Object[rows-1][1];
		
		for(int rownum = 2; rownum <=rows; rownum++) {
			map = new HashMap<String,String>();
			for(int colnum = 0; colnum< cols; colnum++) {
				map.put(excel.getCellData(sheetname, colnum, 1),excel.getCellData(sheetname, colnum, rownum));
				data[rownum-2][0]=map;
			}
		}
		
		return data;
	}

	
}
