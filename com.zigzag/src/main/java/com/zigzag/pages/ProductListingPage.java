package com.zigzag.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.zigzag.pagebase.PageObjectBase;
import com.zigzag.testconfig.TestContext;

public class ProductListingPage extends PageObjectBase {

	@FindBy(css="div.shopify-section h1")
	WebElement searchResultText;
	
	public ProductListingPage(TestContext testContext) {
		super(testContext);
	}
	
	public String getSearchResultText() {
		return getTextFromElement(searchResultText);
	}
	
	

}
