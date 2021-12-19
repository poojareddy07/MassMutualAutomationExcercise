Feature: Verify Values and Total Balance

  Scenario: Validate Values and Balances
  	Given URL is launched
  	When all values are displayed on screen
  	Then count of values and amounts should be 5
  	And  values should be greater than 0
  	And  total balance should be correct
  	And  values should be formatted as currencies
  	And  total balance should match the sum of the values
  	