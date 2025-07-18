package com.zigzag.reporting;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.zigzag.testconfig.TestConfig;
import com.zigzag.util.WebDriverUtil;

public class ExtentManager {

	private static ExtentReports extentReport;
	private static final ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();
	
	public static ExtentReports getExtentReports() {
		if(extentReport == null) {
			var timestamp = WebDriverUtil.getTimeStamp();
//			extentReport = createInstance("test-output/ZigZagAutomationReport"+timestamp+".html");
			extentReport = createInstance("test-output/ZigZag_AutomationReport.html");
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
	
	public static ExtentTest getTest() {
        return extentTestThread.get();
    }

    public static void setTest(ExtentTest test) {
        extentTestThread.set(test);
    }

    public static void removeTest() {
        extentTestThread.remove();

    }
}
