package com.zigzag.web.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import com.zigzag.pages.HomePage;
import com.zigzag.testbase.WebTestBase;
import com.zigzag.testconfig.PageObjectFactory;

public class HomePageTest extends WebTestBase {

//	@Test
//	public void Verify_AboutUs_Link_Present_In_HomePage_Footer_Section() {
//		var isAboutLinkPresent = new HomePage(testContext).loadHomePage()
//														.isAboutUsLinkDisplayed();
//		assertTrue(isAboutLinkPresent,"About Us Link Not Present -Test Failed");
//		autoReport.pass("About Us Link is Present in the Home Page");
//	}
//	
//	@Test
//	public void Verify_ZigZag_Logo_Present_In_HomePage() {
//		var isZigZagLogoPresent = new HomePage(testContext).loadHomePage()
//														.siteHeader
//														.isZigZagLogoDisplayed();
//		assertTrue(isZigZagLogoPresent,"ZigZag Logo Not Present - Test Failed");
//		autoReport.pass("ZigZag Logo is Present in the Home Page");
//	}
//	
//	@Test
//	public void Verify_Slider_Present_In_HomePage() {
//		var isSliderPresent = new HomePage(testContext).loadHomePage()
//														.isSliderDisplayed();
//		assertTrue(isSliderPresent,"Slider is not loaded - Test Failed");
//		autoReport.pass("Slider is loaded in the Home Page");
//	}
//	
//	@Test
//	public void Verify_Search_Functionality_On_HomePage() {
//		var textToSearch = "kurti";
//		var actualText = new HomePage(testContext).loadHomePage()
//								.siteHeader
//								.setTextInSearchProductsTextBoxAndSearch(textToSearch)
//								.getSearchResultText();
//		if(actualText.contains(textToSearch)) {
//			assertTrue(true);
//			autoReport.pass(textToSearch +" appeared on the search result text - Test Passed");
//		}
//		else {
//			assertTrue(false,textToSearch + " did not appear in the result text - Test Failed");
//		}
//	}
	
	
	
	
	
	
	
	@Test
	public void Verify_AboutUs_Link_Present_In_HomePage_Footer_Section() {
		var isAboutLinkPresent = PageObjectFactory.getHomePageObject(testContext).loadHomePage()
														.isAboutUsLinkDisplayed();
		var testVerificationStep = "Verify About Us Link is present in HomePage";
		assertTrue(isAboutLinkPresent,reportTestStepFailure(testVerificationStep));
		reportTestStepPassed(testVerificationStep);
		
	}
	
	@Test
	public void Verify_ZigZag_Logo_Present_In_HomePage() {
		var isZigZagLogoPresent = PageObjectFactory
											.getHomePageObject(testContext)
											.loadHomePage()
											.siteHeader
											.isZigZagLogoDisplayed();
		var testVerificationStep = "Verify ZigZag logo is present in HomePage";
		assertTrue(isZigZagLogoPresent,reportTestStepFailure(testVerificationStep));
		reportTestStepPassed(testVerificationStep);
	}
	
	@Test
	public void Verify_Slider_Present_In_HomePage() {
		var isSliderPresent = PageObjectFactory
											.getHomePageObject(testContext)
											.loadHomePage()
											.isSliderDisplayed();
		var testVerificationStep = "Verify Slider is present in HomePage";
		assertTrue(isSliderPresent,reportTestStepFailure(testVerificationStep));
		reportTestStepPassed(testVerificationStep);
	}
	
	@Test
	public void Verify_Search_Functionality_On_HomePage() {
		var textToSearch = "kurti";
		var actualText = PageObjectFactory
								.getHomePageObject(testContext)
								.loadHomePage()
								.siteHeader
								.setTextInSearchProductsTextBoxAndSearch(textToSearch)
								.getSearchResultText();
		
		var testVerificationStep = "Verify "+textToSearch+" should Appear in the search result";
		
		if(actualText.contains(textToSearch)) {
			reportTestStepPassed(testVerificationStep);
		}
		else {
			fail(reportTestStepFailure(testVerificationStep));
		}
	}
}
