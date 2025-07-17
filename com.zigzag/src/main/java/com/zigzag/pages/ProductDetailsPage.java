package com.zigzag.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.zigzag.pagebase.PageObjectBase;
import com.zigzag.testconfig.TestContext;

public class ProductDetailsPage extends PageObjectBase{

	@FindBy(css="div.product-collection__wrapper")
	List<WebElement> productCardList;
	
	public ProductDetailsPage(TestContext testContext) {
		super(testContext);
	}
	
	

}
