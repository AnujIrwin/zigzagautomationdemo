package com.zigzag.testconfig;

import com.zigzag.pages.HomePage;
import com.zigzag.pages.ProductDetailsPage;
import com.zigzag.pages.ProductListingPage;

public class PageObjectFactory {
	
	public static ProductDetailsPage getProductDetailsPageObject(TestContext testContext) {
		return new ProductDetailsPage(testContext);
	}
	
	public static HomePage getHomePageObject(TestContext testContext) {
		return new HomePage(testContext);
	}
	
	public static ProductListingPage getProductListingPageObject(TestContext testContext) {
		return new ProductListingPage(testContext);
	}
}
