package com.zigzag.web.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.zigzag.testbase.WebTestBase;
import com.zigzag.testconfig.PageObjectFactory;

public class ProductDetailsPageTest extends WebTestBase {

	@Test
	public void verify_Product_Title_On_PDP_After_Clicking_The_Product_On_PLP() {
		var productItemName = "Vneck Sequin Bodycon Dress";
		var actualProductTitle = PageObjectFactory.getProductListingPageObject(testContext)
												.loadUrl("/collections/dresses")
												.clickOnProduct(productItemName)
												.getProductTitle();
		var testCaseStep = "Product Title on PDP should be same as the one clicked on PLP";
		
		assertEquals(actualProductTitle,productItemName,reportTestStepFailure(testCaseStep));
		reportTestStepPassed(testCaseStep);					
	}
	
	@Test
	public void verify_Product_Title_On_PDP_E2E() {
		var productItemName = "Vneck Sequin Bodycon Dress";
		var actualProductTitle =PageObjectFactory
										.getHomePageObject(testContext)
										.loadHomePage()
										.siteHeader
										.clickDressesMenu()
										.clickOnProduct(productItemName)
										.getProductTitle();
		var testCaseStep = "Product Title on PDP should be same as the one clicked on PLP";	
		assertEquals(actualProductTitle,productItemName,reportTestStepFailure(testCaseStep));
		reportTestStepPassed(testCaseStep);
	}
	
	@Test
	public void verify_NoteText_Present_In_Word_Section() {
		var expectedNoteText = "Note: Product Colour May Slightly Vary Due To Photographic Lighting Sources Or Your Monitor Setting.";
		var actualNoteText = PageObjectFactory
									.getProductDetailsPageObject(testContext)
									.loadUrl("/collections/dresses/products/white-satin-dress")
									.getWordSectionNoteText();
		var testCaseStep = "Note should be present in the page with the full text";
		
		assertEquals(actualNoteText,expectedNoteText,reportTestStepFailure(testCaseStep));
		reportTestStepPassed(testCaseStep);
	}
}
