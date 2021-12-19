package com.massmutual.automation.excercise1.runner;

import org.testng.annotations.AfterSuite;

import com.massmutual.automation.excercise1.utils.AutomationUtil;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(features = "features/exercise1.feature", 
				 glue = { "com.massmutual.automation.excercise1.steps" }, 
				 plugin = { "pretty", "html:target/cucumber-reports.html","json:target/cucumber.json" },
				 monochrome = true)

public class TestRunner extends AbstractTestNGCucumberTests {

	@AfterSuite()
	public void generateHTMLReports() {
		AutomationUtil.generateReport();
	}

}
