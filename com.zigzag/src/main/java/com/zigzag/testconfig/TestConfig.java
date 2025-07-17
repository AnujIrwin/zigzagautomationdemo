package com.zigzag.testconfig;

import java.io.FileReader;

import java.util.Properties;

import com.zigzag.util.WebDriverUtil;

public class TestConfig {

	private final static String TEST_CONFIG_FILE_PATH = "test-config.properties";
	private final static Properties TEST_CONFIG = loadProperties();
	public  final static String timestamp = WebDriverUtil.getTimeStamp();
//	public  final static String EXTENT_REPORT_FILE_PATH = "ZigZagAutomationReport_"+timestamp+".html";
	public  final static String EXTENT_REPORT_FILE_PATH = "ZigZagAutomationReport.html";
	
	
	private static Properties loadProperties() {
		var testConfig = new Properties();
		try {
			testConfig.load(new FileReader(TEST_CONFIG_FILE_PATH));
		}
		catch (Exception e) {
			throw new IllegalStateException(String.format("Unable to read file %s due to %s", TEST_CONFIG_FILE_PATH,e));																
		}
		return testConfig;
	}
	
	public static String getHomePageLink() {
		return TEST_CONFIG.get("homepagelink").toString();
	}
	
	public static String getBrowserName() {
		return TEST_CONFIG.getProperty("browser").toString();
	}
}
