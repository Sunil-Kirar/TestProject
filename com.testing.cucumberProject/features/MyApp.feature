Feature: Verify the login functionality

Background: open application
Given Open the browser and navigate to home page

@Regression
Scenario: Data Driven testing
When user click on New Customer
Then user insert the values with data from test data sheet row "1"
And  user click on submit button

@Sanity
Scenario: Data Driven testing
When user click on New Customer
Then user insert the values with data from test data sheet row "2"
And  user click on submit button

@E2E
Scenario: Data Driven testing
When user click on New Customer
Then user insert the values with data from test data sheet row "3"
And  user click on submit button
