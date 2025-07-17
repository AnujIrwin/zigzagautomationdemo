package com.zigzag.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class WebDriverUtil {
	
	private static final String timeStamp = getTimeStamp();
	
	public String takeScreenshot(WebDriver driver,String screenShotName) throws IOException {
		File takenScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		var folderPath = "test-output/screenshots/"+timeStamp;
//		new File(folderPath).mkdirs();
		FileUtils.copyFile(takenScreenshot, new File(folderPath+"/"+screenShotName+".jpg"));
		return folderPath+"/"+screenShotName+".jpg";
	}
	
	public static String getTimeStamp() {
		return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
	}

}
