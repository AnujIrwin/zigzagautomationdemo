package com.zigzag.testbase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.zigzag.reporting.AutoReport;
import com.zigzag.reporting.ExtentManager;
import com.zigzag.testconfig.TestConfig;
import com.zigzag.testconfig.TestContext;
import com.zigzag.util.WebDriverUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTestBase {

	protected TestContext testContext;
	protected static ExtentReports extentReport;
	protected AutoReport autoReport;
	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

	@BeforeSuite(alwaysRun = true)
	public void extentReportBeforeSuite() {
		extentReport = ExtentManager.getExtentReports();
		System.out.println("Test Execution started on : " +TestConfig.getBrowserName().toUpperCase());
	}
	
	@BeforeMethod(alwaysRun = true)
	public synchronized void masterSetup(Method method) {
	    setupExtentReport(method);
	    setupDriver();
	}
	
	private void setupExtentReport(Method method) {
	    ExtentTest localTest = extentReport.createTest(method.getName());
	    ExtentManager.setTest(localTest);
	    autoReport = new AutoReport();
	}
	
	private void setupDriver() {
	    WebDriver localDriver = createDriver(TestConfig.getBrowserName());
	    threadLocalDriver.set(localDriver);
	    localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	    localDriver.manage().window().maximize();
	    testContext = new TestContext(localDriver, autoReport);
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		getDriver().quit();
		ExtentManager.removeTest();
	}
	
	public WebDriver createDriver(String browser) {
		return switch(browser.toLowerCase()) {
		case "chrome" ->{
			WebDriverManager.chromedriver().setup();
			var options = new ChromeOptions();
			options.addArguments("--start-maximized").setAcceptInsecureCerts(true);
            yield new ChromeDriver(options);		
		}
		case "firefox" ->{
			WebDriverManager.firefoxdriver().setup();
			var options = new FirefoxOptions();
			options.addArguments("--start-maximized").setAcceptInsecureCerts(true);
            yield new FirefoxDriver(options);		
		}
		case "edge" ->{
			System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
			var options = new EdgeOptions();
			options.addArguments("--start-maximized").setAcceptInsecureCerts(true);
            yield new EdgeDriver(options);		
		}
		default -> throw new IllegalArgumentException("UnSupported Browser " + browser);	
		};	
	}
	
	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}
	
	public static ExtentTest getExtentTest() {
		return ExtentManager.getTest();
	}
	
	@AfterMethod(alwaysRun = true)
	public synchronized void extentReportAfterMethod(ITestResult result) throws IOException {
		
		var testCaseName = result.getMethod().getMethodName();
		var test = getExtentTest();
		
		if(result.getStatus() != ITestResult.SUCCESS)
		{
			var screenshotPath =new WebDriverUtil().takeScreenshot(getDriver(), testCaseName);
			test.addScreenCaptureFromPath(screenshotPath);
			test.fail(result.getThrowable());
		}
		else {
			test.pass("Test Passed");
		}		
		
	}
	
	@AfterSuite(alwaysRun = true)
	public void flushExtentReport() {
		extentReport.flush();
	}
	
}
