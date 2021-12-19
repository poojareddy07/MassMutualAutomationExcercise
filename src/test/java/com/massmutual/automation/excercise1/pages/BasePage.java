package com.massmutual.automation.excercise1.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.massmutual.automation.excercise1.utils.AutomationUtil;

public class BasePage {

	
	public static WebDriver webDriver;;

	public void lauchURL() {
		webDriver = AutomationUtil.getDriver();
		String url = AutomationUtil.getPropertyKey("url");
		webDriver.get(url);
		webDriver.manage().window().maximize();
		Reporter.log("Launced URL: " + url);
	}

	public void verifyURL() {
		assertTrue(!webDriver.getCurrentUrl().isEmpty(), "URL is blank");
	}

	public void closeBrowser() {
		webDriver.quit();
		Reporter.log("Closed Browser");
	}

	
}
