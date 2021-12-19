package com.massmutual.automation.excercise1.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.massmutual.automation.excercise1.utils.AutomationUtil;

public class ValuesPage extends BasePage {

	public ValuesPage() {
		PageFactory.initElements(webDriver, this);
	}

	@FindBy(css = "[id*='lbl_val']")
	private List<WebElement> lables;

	@FindBy(css = "[id*='txt_val']")
	private List<WebElement> values;

	@FindBy(id = "lbl_ttl_val")
	private WebElement totalBalanceLabel;

	@FindBy(id = "txt_ttl_val")
	private WebElement totalBalanceValue;

	public void verifyValues() {

		AutomationUtil.waitForWebElements(lables);
		AutomationUtil.waitForWebElements(values);
	}

	public void validateCount(int count) {

		assertEquals(lables.size(), count,
				"Count of labels," + lables.size() + "doesn't match the expected count," + count);
		assertEquals(values.size(), count,
				"Count of values," + values.size() + "doesn't match the expected count" + count);
	}

	public void validateValuesAreGreaterThan(int number) throws ParseException {

		for (WebElement value : values) {

			assertTrue(AutomationUtil.getFloatValue(value.getText()) > number,
					"Value " + value.getText() + " is not greater than " + number);
		}
		assertTrue(AutomationUtil.getFloatValue(totalBalanceValue.getText()) > number,
				"Total Balance "+ totalBalanceValue.getText()+ "is not greater than " + number);
	}

	public void validateTotalBalance() throws ParseException {

		float totalValue = 0;

		for (WebElement value : values) {

			totalValue += AutomationUtil.getFloatValue(value.getText());
		}

		assertEquals(totalValue, AutomationUtil.getFloatValue(totalBalanceValue.getText()),
				"Total balance is not correct based on the values listed on the screen");
	}

	public void validateValuesAreFormatted() {

		for (WebElement value : values) {

			assertTrue(AutomationUtil.isCurrencyPattern(value.getText()),
					"Value " + value.getText() + " is not formatted as currency");
		}

		assertTrue(AutomationUtil.isCurrencyPattern(totalBalanceValue.getText()),
				"Total balance value is not formatted as currency");
	}

}