#Author: sivasurya.prasand@qualitestgroup.com
#Keywords Summary : E-Commerce Automation
@tag 
Feature: Web Automation Task 

Background: 
	Given user launch the e-commerce application 
	
@ECommerce
@OrderTShirt 
Scenario: Order T-Shirt (and Verify in Order History) 

	Given user able to login the application
	Then user adds a product to the cart
	Then checks out and Order the product
	And logout the Application 
	
	
@ECommerce
@PersonalInfoEdit
Scenario: Update Personal Information (First Name) in My Account

	Given user able to login the application
	And navigate to Personal Info page
	Then Update the first name
	And logout the Application 