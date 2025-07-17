package com.zigzag.reporting;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.zigzag.testconfig.TestConfig;
import com.zigzag.util.WebDriverUtil;

public class ExtentManager {

	private static ExtentReports extentReport;
	
	public static ExtentReports getInstance() {
		if(extentReport == null) {
			var timestamp = WebDriverUtil.getTimeStamp();
			extentReport = createInstance("test-output/ZigZagAutomationReport"+timestamp+".html");
		}	
		return extentReport;
	}
	
	public static ExtentReports createInstance(String fileName) {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setDocumentTitle("ZigZag Automation Report");
		sparkReporter.config().setEncoding("utf-8");
		sparkReporter.config().setReportName("ZigZag Automation Report");
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		return extentReport;
	}
}
