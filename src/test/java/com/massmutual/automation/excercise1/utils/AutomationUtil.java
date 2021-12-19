package com.massmutual.automation.excercise1.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class AutomationUtil {

	public static WebDriver webDriver;

	public final static int WAIT_SECONDS = 10;

	public static WebDriver getDriver() {
		String browser = getPropertyKey("browser");
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", getPropertyKey("webdriver.chrome.driver"));
				webDriver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", getPropertyKey("webdriver.gecko.driver"));
				webDriver = new FirefoxDriver();
			}
		} catch (Exception e) {
			Assert.fail("Invalid Browser: " + browser);
		}
		return webDriver;
	}

	public static void waitForWebElements(List<WebElement> elements) {
		try {
			new WebDriverWait(webDriver, WAIT_SECONDS).until(ExpectedConditions.visibilityOfAllElements(elements));
			Reporter.log(elements.toString() + " is visible");
		} catch (Exception e) {
			Assert.fail(elements.toString() + " is not visible");
		}
	}

	public static double getFloatValue(String currencyValue) throws ParseException {

		NumberFormat nf = NumberFormat.getCurrencyInstance();
		nf.setMaximumFractionDigits(2);
		Number numberValue = nf.parse(currencyValue.trim());
		
		
		return numberValue.floatValue();
	}

	public static boolean isCurrencyPattern(String currencyValue) {
		return Pattern.matches("^\\$[0-9]{1,3}(\\,[0-9]{3})*\\.[0-9]{2}$", currencyValue.trim());
	}

	public static String getPropertyKey(String key) {
		ResourceBundle rb = ResourceBundle.getBundle("application");
		return rb.getString(key);
	}

	public static void generateReport() {

		List<String> jsonFiles = new ArrayList<String>();
		jsonFiles.add("target/cucumber.json");
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, getReportConfig());
		reportBuilder.generateReports();
	}

	private static Configuration getReportConfig() {
		File reportOutputDirectory = new File("target");
		Configuration configuration = new Configuration(reportOutputDirectory, "MassMutualAutomationExcercise");
		configuration.addClassifications("URL", getPropertyKey("url"));
		configuration.addClassifications("Platform", System.getProperty("os.name"));
		configuration.addClassifications("Browser", getPropertyKey("browser"));

		return configuration;
	}
}
