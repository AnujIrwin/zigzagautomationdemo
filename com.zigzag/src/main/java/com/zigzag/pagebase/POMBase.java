package com.zigzag.pagebase;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zigzag.reporting.AutoReport;
import com.zigzag.testconfig.TestContext;

import net.bytebuddy.description.annotation.AnnotationValue.Sort;

public class POMBase {
	protected final TestContext testContext;
	protected final WebDriver driver;
	protected final AutoReport autoReport;
	private final WebDriverWait wait;
	private final Actions action;
	
	public POMBase(TestContext testContext) {
		this.testContext = testContext;
		this.driver = testContext.getDriver();
		this.autoReport = testContext.getAutoReport();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		this.action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	protected String getClassName(Object obj) {
		return obj.getClass().getSimpleName();
	}
	
	protected String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName()+"()";
	}
	
	protected void waitForElementClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	protected void waitForElementVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	protected void waitForElementInvisible(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	protected void click(WebElement element) {
		waitForElementClickable(element);
		moveToElement(element);
		element.click();
	}
	
	protected void click(By locator) {
		var element = driver.findElement(locator);
		click(element);
	}
	
	protected void sendKeys(WebElement element,String string) {
		waitForElementClickable(element);
		moveToElement(element);
		element.sendKeys(string);;
	}
	
	protected void sendKeys(By locator, String string) {
		var element = driver.findElement(locator);
		sendKeys(element,string);
	}
	
	protected <T> void waitFor(ExpectedCondition<T> condition) {
		wait.until(condition);
	}
	
	protected String getTextFromElement(WebElement element) {
		waitFor(ExpectedConditions.visibilityOf(element));
		moveToElement(element);
		return element.getText();
	}
	
	protected void waitForValidState(String pageObjName,ExpectedCondition<?> ec){
		var wdWait = new WebDriverWait(driver,Duration.ofSeconds(20));
		var failMessage = String.format("Unable to load \"%s\" with valid state. Current URL is %s\n\n"
								, pageObjName,driver.getCurrentUrl());
		wdWait.withMessage(failMessage)
						.until(
								webDriver -> ((JavascriptExecutor)webDriver)
							 	.executeScript("return document.readyState").equals("complete")
								);
		wdWait.withMessage(failMessage).until(ec)			;	
	}
	
	protected boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		}catch(NoSuchElementException e){
			return false;
		}
	}
	
	protected boolean isElementDisplayed(By locator) {
		return isElementDisplayed(driver.findElement(locator));
	}
	
	protected void moveToElement(WebElement element) {
		action.moveToElement(element).perform();
	}
} 
