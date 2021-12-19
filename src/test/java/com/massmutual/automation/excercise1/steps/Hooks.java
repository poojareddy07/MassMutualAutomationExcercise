package com.massmutual.automation.excercise1.steps;

import com.massmutual.automation.excercise1.pages.BasePage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

public class Hooks {

	BasePage page = new BasePage();

	@Before
	public void setUp() {
		page.lauchURL();
	}

	@Given("URL is launched")
	public void url_is_launched() {
		page.verifyURL();
	}

	@After
	public void tearDown() {

		page.closeBrowser();
	}

}
