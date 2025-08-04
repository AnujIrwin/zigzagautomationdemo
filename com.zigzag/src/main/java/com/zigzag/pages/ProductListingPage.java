package com.zigzag.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zigzag.pagebase.PageObjectBase;
import com.zigzag.testconfig.TestConfig;
import com.zigzag.testconfig.TestContext;

public class ProductListingPage extends PageObjectBase {

	@FindBy(css="div.shopify-section h1")
	WebElement searchResultText;
	
	@FindBy(css = "#CollectionProducts product-item")
	List<WebElement> productItems;
	
	@FindBy(css = "span.irs-slider.from")
	WebElement minSlider;
	
	@FindBy(css = "span.irs-slider.to")
	WebElement maxSlider;
	
	@FindBy(css = "#CollectionCurrentFiltersClone facet-remove[class*=item] a[href*='price']")
	WebElement currentAppliedFilterFacetPrice;
	
	@FindBy(id="SortByClone")
	WebElement sortByCloneComboBox;
	
	@FindBy(css = "span[class='price']")
	List<WebElement> productPrices;
	
	@FindBy(css="product-item")
	List<WebElement> productItemsList;
	
	public ProductListingPage(TestContext testContext) {
		super(testContext);
	}
	
	public String getSearchResultText() {
		return getTextFromElement(searchResultText);
	}
	
	public  ProductListingPage loadUrl(String url) {
		var urlToLoad = TestConfig.getBaseUrlLink()+url;
		autoReport.logMethodAction(getClassName(this), getMethodName()+" : "+urlToLoad);
		testContext.getDriver().get(urlToLoad);
		return this;
	}
	
	public int getProductsCount() {
		autoReport.logMethodAction(getClassName(this), getMethodName());
		waitForCondition(ExpectedConditions.visibilityOfAllElements(productItems));
		return productItems.size();
	}
	
	public boolean isSortByCloneComboBoxDisplayed() {
		autoReport.logMethodAction(getClassName(this), getMethodName());
		return isElementDisplayed(sortByCloneComboBox);
	}
	
	public ProductDetailsPage clickOnProduct(String productName) {
		autoReport.logMethodAction(getClassName(this), getMethodName()+" : "+productName);
		var productItemXpathLocator = "//product-item//a[contains(.,'"+productName+"')]";
		click(By.xpath(productItemXpathLocator));
		return new ProductDetailsPage(testContext);
	}
}
