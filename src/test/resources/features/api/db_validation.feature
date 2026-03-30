@db @regression
Feature: OpenCart Database Validation
  As a QA engineer
  I want to validate data persisted in the database
  So that I can ensure data integrity after business operations

  Background:
    Given the QA engineer has database access configured

  @smoke
  Scenario: Verify order exists in database after creation
    Given an order with id "1" was placed in the system
    When the QA engineer queries the database for order "1"
    Then the database should return at least 1 row
    And the order status should be "1"

  Scenario: Verify customer data is persisted correctly
    Given a customer with email "john.doe@testmail.com" completed a purchase
    When the QA engineer queries customer data by email "john.doe@testmail.com"
    Then the database should return at least 1 row
    And the customer firstname should be "John"
