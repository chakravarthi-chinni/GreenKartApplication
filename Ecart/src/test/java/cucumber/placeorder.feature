
Feature: place the order from ecart website
  here i purchase order from ecart 
  
  Background:
  Given I landed in the ecommerse page 

  
  @Regression
  Scenario Outline: positive testing
    Given login with username <name> and password <password>
    When I add product <productName> to cart
    And  check the product <productName> in the list and place the order 
    Then finally get the confirmation message "THANKYOU FOR THE ORDER."

    Examples: 
      |  name                |   password      |  productName       |
      | chakri123@gmail.com  |   Chakri@123    |  ADIDAS ORIGINAL   |
