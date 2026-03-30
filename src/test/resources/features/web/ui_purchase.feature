@ui @regression
Feature: OpenCart E2E Purchase Flow
  As a guest customer
  I want to purchase products from the OpenCart store
  So that I can receive the items I ordered

  Background:
    Given the customer is on the OpenCart home page

  @smoke
  Scenario Outline: Guest customer completes a full purchase with two products
    When the customer searches for "<product1>"
    And the customer adds "<product1>" to the cart from the search results
    And the customer searches for "<product2>"
    And the customer adds "<product2>" to the cart from the search results
    When the customer navigates to checkout via shopping cart
    Then the checkout page should display
    When the customer selects guest checkout
    And the customer clicks continue from checkout options
    Then the billing details section should display
    When the customer fills in the billing details with:
      | firstName | <firstName> |
      | lastName  | <lastName>  |
      | email     | <email>     |
      | telephone | <telephone> |
      | address   | <address>   |
      | city      | <city>      |
      | postcode  | <postcode>  |
      | country   | <country>   |
      | region    | <region>    |
    And the customer clicks continue from billing details
    Then the delivery details section should display
    When the customer clicks continue from delivery details
    Then the delivery method section should display
    When the customer clicks continue from delivery method
    Then the payment method section should display
    When the customer agrees to the terms and conditions
    And the customer clicks continue from payment method
    Then the confirm order section should display
    When the customer confirms the order
    Then the order confirmation page should display
    And the order confirmation message should contain "Your order has been placed"

    Examples:
      | product1    | product2            | firstName | lastName | email                    | telephone  | address        | city       | postcode | country       | region     |
      | MacBook     | iPhone              | John      | Doe      | john.doe@testmail.com    | 5551234567 | 123 Main St    | Austin     | 78701    | United States | Texas      |
      | MacBook Pro | Apple Cinema 30     | Jane      | Smith    | jane.smith@testmail.com  | 5559876543 | 456 Oak Ave    | New York   | 10001    | United States | New York   |

  @smoke
  Scenario: Single product purchase flow
    When the customer searches for "MacBook"
    And the customer adds "MacBook" to the cart from the search results
    When the customer navigates to checkout via shopping cart
    When the customer selects guest checkout
    And the customer clicks continue from checkout options
    When the customer fills the billing form with generated data
    And the customer clicks continue from billing details
    And the customer clicks continue from delivery details
    And the customer clicks continue from delivery method
    And the customer agrees to the terms and conditions
    And the customer clicks continue from payment method
    When the customer confirms the order
    Then the order confirmation message should contain "Your order has been placed"
