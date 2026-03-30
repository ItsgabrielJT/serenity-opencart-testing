@api @regression
Feature: OpenCart API Orders Management
  As an API consumer
  I want to manage orders through the REST API
  So that I can automate order processing workflows

  Background:
    Given an API consumer is connected to the OpenCart API

  @smoke
  Scenario: Get list of products returns HTTP 200
    When the consumer requests the list of products
    Then the response status code should be 200
    And the response body should not be empty

  @smoke
  Scenario: Create a new order returns HTTP 200
    Given the consumer has valid order data for customer "api.test@testmail.com"
    When the consumer submits the order creation request
    Then the response status code should be 200
    And the response body should contain an order reference

  Scenario: Get order status for an existing order
    Given an order with id "1" exists in the system
    When the consumer requests the status of order "1"
    Then the response status code should be 200
    And the response body should contain a "status" field

  Scenario: Get products with invalid endpoint returns HTTP 404
    When the consumer requests resource from "/invalid-endpoint"
    Then the response status code should be 404
