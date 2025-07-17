package com.zigzag.pagecomponents;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.zigzag.pagebase.PageComponentBase;
import com.zigzag.pages.HomePage;
import com.zigzag.pages.ProductListingPage;
import com.zigzag.testconfig.TestContext;

public class SiteHeader extends PageComponentBase {

	@FindBy(css="h1.logo")
	WebElement zigZagLogo;
	
	@FindBy(css = "header [href*='search']")
	WebElement searchButton;
	
	@FindBy(css="div .popup-navigation__search input[type=search]")
	WebElement searchProductsPopUpTextBox;
	
	public SiteHeader(TestContext testContext) {
		super(testContext);
	}

	public boolean isZigZagLogoDisplayed() {
		autoReport.logMethodAction(getClassName(this), getMethodName());
		return zigZagLogo.isDisplayed();
	}
	
	public SiteHeader clickSearchButton() {
		autoReport.logMethodAction(getClassName(this), getMethodName());
		click(searchButton);
		return this;
	}
	
	public boolean isSearchProductsPopUpTextBoxDisplayed() {
		try {
			waitFor(ExpectedConditions.visibilityOf(searchProductsPopUpTextBox));
			return searchProductsPopUpTextBox.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public ProductListingPage setTextInSearchProductsTextBoxAndSearch(String text) {
		autoReport.logMethodAction(getClassName(this), getMethodName()+" as : "+text);
		clickSearchButton();
		if(isSearchProductsPopUpTextBoxDisplayed()) {
			String chordText = Keys.chord(text,Keys.ENTER);
			setTextInSearchProductsPopUpTextBox(chordText);
		}
		else {
			throw new AssertionError("Search Box Not Displayed");
		}		
		return new ProductListingPage(testContext);
	}
	
	public void setTextInSearchProductsPopUpTextBox(String text) {
		sendKeys(searchProductsPopUpTextBox,text);
	}
}
