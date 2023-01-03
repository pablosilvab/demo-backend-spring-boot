Feature: Test CRUD method in Projects REST API

  Scenario: The user is able to add and remove projects.
    Given A list of projects are available
    Then I receive valid HTTP response code 200
    When I add a project to system
    Then The project is added
    And The project list has more than one item

