Feature: As a user I want to update a cart in the system

  Scenario: Successful cart update
    Given I am connected to the cart service
    When I try to update cart with id "7" with the following data
      | userId    | 3                            |
      | date      | 2019-12-10                   |
      | productId | 1                            |
      | quantity  | 3                            |
    And the response status code should be 200 for the cart request
    Then the cart should contain the correct information