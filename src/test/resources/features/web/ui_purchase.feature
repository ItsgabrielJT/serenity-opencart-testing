@ui
Feature: OpenCart E2E Purchase Flow
  As a guest customer
  I want to purchase products from the OpenCart store
  So that I can receive the items I ordered

  Background:
    Given the customer is on the OpenCart home page

  Scenario: Guest customer completes a full purchase with two products
    When the customer searches for "MacBook"
    And the customer adds "MacBook" to the cart from the search results
    And the customer searches for "iPhone"
    And the customer adds "iPhone" to the cart from the search results
    And the customer clicks the shopping cart link
    Then the shopping cart should contain "MacBook"
    And the shopping cart should contain "iPhone"
    When the customer clicks the checkout button from the shopping cart
    Then the checkout page should display
    When the customer selects guest checkout
    And the customer clicks continue from checkout options
    Then the billing details section should display
    When the customer fills in the billing details with:
      | firstName | John      |
      | lastName  | Doe       |
      | email     | john.doe@testmail.com    |
      | telephone | 5551234567 |
      | address   | 123 Main St    |
      | city      | Austin     |
      | postcode  | 78701    |
      | country   | United States |
      | region    | Texas      |
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
