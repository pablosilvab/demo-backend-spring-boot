Feature: Test CRUD method in products REST API

  Scenario: The user is able to add and remove product.
    Given A list of products are available
    Then I receive valid HTTP response code 200
    When I add a product to system
    Then The product is added
    And The products list has more than one item

