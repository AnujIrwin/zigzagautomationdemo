package com.zigzag.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.zigzag.pagebase.PageObjectBase;
import com.zigzag.testconfig.TestConfig;
import com.zigzag.testconfig.TestContext;

public class ProductDetailsPage extends PageObjectBase{

	@FindBy(css="div.product-collection__wrapper")
	List<WebElement> productCardList;
	
	@FindBy(css = "div.product-page-info__title")
	WebElement productTitle;
	
	@FindBy(xpath="//div[@class='WordSection1']//span[contains(.,'Note')]")
	WebElement wordSectionNoteText;
	
	public ProductDetailsPage(TestContext testContext) {
		super(testContext);
	}
	
	public  ProductDetailsPage loadUrl(String url) {
		var urlToLoad = TestConfig.getBaseUrlLink()+url;
		autoReport.logMethodAction(getClassName(this), getMethodName()+" : "+urlToLoad);
		testContext.getDriver().get(urlToLoad);
		return this;
	}
	
	public String getProductTitle() {
		autoReport.logMethodAction(getClassName(this), getMethodName());
		return getTextFromElement(productTitle);
	}
	
	public String getWordSectionNoteText() {
		autoReport.logMethodAction(getClassName(this), getMethodName());
		return getTextFromElement(wordSectionNoteText);
	}
	
	

}
