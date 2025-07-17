package com.zigzag.testconfig;

import org.openqa.selenium.WebDriver;

import com.zigzag.reporting.AutoReport;

public class TestContext {
	private WebDriver driver;
	private AutoReport autoReport;
	
	public TestContext(WebDriver driver,AutoReport autoReport) {
		this.driver=driver;
		this.autoReport =autoReport;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public AutoReport getAutoReport() {
		return autoReport;
	}
}
