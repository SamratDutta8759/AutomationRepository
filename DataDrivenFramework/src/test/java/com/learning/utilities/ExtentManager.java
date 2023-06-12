package com.learning.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.learning.base.TestBase;

public class ExtentManager extends TestBase {

	private static ExtentReports extent;
	public static String reportName;
	public static String reportPath;
	
	public static String getReportPath() {
		//reportName = TestUtils.getUniqueName()+".html";
		reportName = "extent.html";
		reportPath = System.getProperty("user.dir")+"\\Output\\Reports\\"+reportName;
		return reportPath;
	}
	
	public static ExtentReports getinstance() {
		
		if(extent == null) {
			ExtentSparkReporter htmlReporter = new ExtentSparkReporter(getReportPath()); 
			htmlReporter.config().setTheme(Theme.DARK);
	        htmlReporter.config().setDocumentTitle(reportName);
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setReportName(reportName);
	        
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	        extent.setSystemInfo("Automation Tester", "Samrat Dutta");
	        extent.setSystemInfo("Organization", "Learning");
	        extent.setSystemInfo("Build no", "W2A-1234");
		}
		return extent;
	}
}
