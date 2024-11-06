# language: en
# Author: Huber Steven Arroyave - Sebastian Mora
# language: en

  Feature: As a user I want to see the list of available products

    Scenario: Get the list of products
      Given I am connect to capacities of the service
      When I get the information of products
      Then I can see all information about the products