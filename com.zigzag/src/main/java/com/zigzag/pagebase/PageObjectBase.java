package com.zigzag.pagebase;

import com.zigzag.pagecomponents.SiteHeader;
import com.zigzag.testconfig.TestContext;

public class PageObjectBase extends POMBase{

	public SiteHeader siteHeader;
	
	public PageObjectBase(TestContext testContext) {
		super(testContext);
		this.siteHeader=new SiteHeader(testContext);
	}
}
