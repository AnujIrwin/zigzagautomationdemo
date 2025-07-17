package com.zigzag.reporting;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;

public class AutoReport {
	private final ExtentTest extentTest;
	
	public AutoReport(ExtentTest extentTest) {
		this.extentTest = extentTest;
	}
	
	public void logMethodAction(String className, String methodInfo) {
		String readableClassName = toReadableText(className);
		String readableMethodName = toReadableText(methodInfo.replace("()", ""));
		log(Status.INFO,String.format("Perform %s => %s", readableClassName,readableMethodName));
	}

	public void log(Status status, String details) {
		extentTest.log(status, details);	
	}
	
	public void log(Status status, Throwable t) {
		extentTest.log(status, t);	
	}
	
	public void log(Status status, String details, Media media) {
		extentTest.log(status, details,media);	
	}
	
	public void pass(String details) {
		extentTest.log(Status.PASS, details);
	}
	
	public void fail(String details) {
		extentTest.log(Status.FAIL, details);
	}
	
	private String toReadableText(String text) {
		return text.replaceAll("([a-z])([A-Z])", "$1 $2") // camelCase to words
		           .replaceAll("([A-Z])([A-Z][a-z])", "$1 $2") // ABBRCase fix
		           .replaceAll("_", " ") // underscore support
		           .replaceFirst("^.", text.substring(0, 1).toUpperCase()); // Capitalize
	}
}
