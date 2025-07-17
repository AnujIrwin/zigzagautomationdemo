package com.zigzag.pagebase;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zigzag.reporting.AutoReport;
import com.zigzag.testconfig.TestContext;

public class POMBase {
	protected final TestContext testContext;
	protected final WebDriver driver;
	protected final AutoReport autoReport;
	private final WebDriverWait wait;
	
	public POMBase(TestContext testContext) {
		this.testContext = testContext;
		this.driver = testContext.getDriver();
		this.autoReport = testContext.getAutoReport();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
	}
	
	public String getClassName(Object obj) {
		return obj.getClass().getSimpleName();
	}
	
	public String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName()+"()";
	}
	
	public void waitForElementClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForElementVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementInvisible(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void click(WebElement element) {
		waitForElementClickable(element);
		element.click();
	}
	
	public void click(By locator) {
		var element = driver.findElement(locator);
		click(element);
	}
	
	public void sendKeys(WebElement element,String string) {
		waitForElementClickable(element);
		element.sendKeys(string);;
	}
	
	public void sendKeys(By locator, String string) {
		var element = driver.findElement(locator);
		sendKeys(element,string);
	}
	
	public <T> void waitFor(ExpectedCondition<T> condition) {
		wait.until(condition);
	}
	
	public String getTextFromElement(WebElement element) {
		waitFor(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}
} 
