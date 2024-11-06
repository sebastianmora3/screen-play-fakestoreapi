Feature: As a user I want to authenticate in the system

  Scenario: Successful login with valid credentials
    Given I am connected to the authentication service
    When I try to login with username "mor_2314" and password "83r5^_"
    Then I should receive a valid token
    And the response status code should be 200 for the login request

  Scenario: Failed login with invalid credentials
    Given I am connected to the authentication service
    When I try to login with username "invalid_user" and password "wrong_pass"
    Then I should receive an error message
    And the response status code should be 401 for the login request