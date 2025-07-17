package com.zigzag.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.zigzag.pagebase.PageObjectBase;
import com.zigzag.testconfig.TestConfig;
import com.zigzag.testconfig.TestContext;

public class HomePage extends PageObjectBase{

	@FindBy(css= ".footer__section-content a[href*='online-customer-care']")
	WebElement customerCareSupportLink;
	
	@FindBy(css = ".footer__section-content a[href*='about-us']")
	WebElement aboutUsLink;
	
	@FindBy(xpath = "//span[text()='Clothing']")
	WebElement clothingDropDown;
	
	@FindBy(css="i.popup-subscription__close")
	WebElement popUpCloseButton;
	
	@FindBy(css="div.slider")
	WebElement slider;
	
	public HomePage(TestContext testContext) {
		super(testContext);
	}
	
	public HomePage loadHomePage() {
		autoReport.logMethodAction(getClassName(this), getMethodName());
		driver.get(TestConfig.getHomePageLink());
		try{
			waitForElementVisible(popUpCloseButton);
			click(popUpCloseButton);
			waitForElementVisible(customerCareSupportLink);
			return this;
		}
		catch(TimeoutException e) {
			waitForElementVisible(customerCareSupportLink);
			return this;
		}
	}
	
	public boolean isAboutUsLinkDisplayed() {
		autoReport.logMethodAction(getClassName(this), getMethodName());
		return aboutUsLink.isDisplayed();
	}
	
	
	public boolean isSliderDisplayed() {
		autoReport.logMethodAction(getClassName(this), getMethodName());
		return slider.isDisplayed();
	}

}
