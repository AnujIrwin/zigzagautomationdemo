package com.zigzag.web.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import com.zigzag.testbase.WebTestBase;
import com.zigzag.testconfig.PageObjectFactory;

public class ProductListPageTest extends WebTestBase {

	@Test
	public void verifyProductsLoadAfterLandingToPLP() {
		var productCount = PageObjectFactory
										.getProductListingPageObject(testContext)
										.loadUrl("/collections/dresses")
										.getProductsCount();
		var testCaseStep = "Products should be visible in the PLP";
		if(productCount>1) {
			reportTestStepPassed(testCaseStep);
		}
		else {
			fail(reportTestStepFailure(testCaseStep));
		}
	}
	
	@Test
	public void verifySortByCloneComboBoxVisibleAfterLandingOnPLP() {
		var isSortByCloneComboBoxDisplayed = PageObjectFactory
													.getProductListingPageObject(testContext)
													.loadUrl("/collections/dresses")
													.isSortByCloneComboBoxDisplayed();
		var testCaseStep = "Sort By Clone ComboBox should be Visible on PLP";
		assertTrue(isSortByCloneComboBoxDisplayed,reportTestStepFailure(testCaseStep));
		reportTestStepPassed(testCaseStep);
	}
}
