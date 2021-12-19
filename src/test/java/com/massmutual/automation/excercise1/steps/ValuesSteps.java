package com.massmutual.automation.excercise1.steps;

import java.text.ParseException;

import com.massmutual.automation.excercise1.pages.ValuesPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ValuesSteps {
	
	ValuesPage valuepage = new ValuesPage();
	
	@When("all values are displayed on screen")
	public void all_values_are_displayed_on_screen() {
		valuepage.verifyValues();
	}

	@Then("count of values and amounts should be {int}")
	public void count_of_values_and_amounts_should_be(int count) {
		valuepage.validateCount(count);
	}
	
	@Then("values should be greater than {int}")
	public void values_should_be_greater_than(int minimumValue) throws ParseException  {
		valuepage.validateValuesAreGreaterThan(minimumValue);
	}
	
	@Then("total balance should be correct")
	public void total_balance_should_be_correct() throws ParseException {
		valuepage.validateTotalBalance();
	}
	
	@Then("values should be formatted as currencies")
	public void values_should_be_formatted_as_currencies() {
		valuepage.validateValuesAreFormatted();
	}
	
	@Then("total balance should match the sum of the values")
	public void total_balance_should_match_the_sum_of_the_values() throws ParseException {
		valuepage.validateTotalBalance();
	}
	
}
