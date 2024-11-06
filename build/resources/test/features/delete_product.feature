Feature: As a user I want to delete products

  Scenario: Delete an existing product successfully
    Given I am connected to the product service
    When I try to delete the product with id "6"
    Then I should see the deleted product details
    And the response status code should be 200

  Scenario: Try to delete a non-existent product
    Given I am connected to the product service
    When I try to delete the product with id "999"
    Then I should receive a null response
    And the response status code should be 200