package com.zigzag.reporting;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;

public class AutoReport {
	
	public void logMethodAction(String className, String methodInfo) {
		var readableClassName = toReadableText(className);
		var readableMethodName = toReadableText(methodInfo.replace("()", ""));
		var actionToReport = String.format("Perform on %s => %s", readableClassName,readableMethodName);
		log(Status.INFO,actionToReport);
	}
	
	public void log(Status status, String details) {
	    ExtentManager.getTest().log(status, details); 
	}
	
	public void log(Status status, Throwable t) {
		ExtentManager.getTest().log(status, t);	
	}
	
	public void log(Status status, String details, Media media) {
		ExtentManager.getTest().log(status, details,media);	
	}
	
	public void pass(String details) {
		ExtentManager.getTest().log(Status.PASS, details);
	}
	
	public void fail(String details) {
		ExtentManager.getTest().log(Status.FAIL, details);
	}
	
	private String toReadableText(String text) {
		return text.replaceAll("([a-z])([A-Z])", "$1 $2") // camelCase to words
		           .replaceAll("([A-Z])([A-Z][a-z])", "$1 $2") // ABBRCase fix
		           .replaceAll("_", " ") // underscore support
		           .replaceFirst("^.", text.substring(0, 1).toUpperCase()); // Capitalize
	}
}
