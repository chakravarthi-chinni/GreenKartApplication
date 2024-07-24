
@tag
Feature: ErrorHandling
  here we handle the error using cucumber

  @sanity
  Scenario Outline: Error validation
    Given I landed in the ecommerse page
    Given login with username <name> and password <password>
    Then getting error message "Incorrect email or password."

    Examples: 
      | name  							| 		password 			| 
      | chakri123@gmail.com |     Chakri@12 		| 
