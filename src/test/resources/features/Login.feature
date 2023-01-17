Feature: Login functionality

  Scenario Outline: Login with valid credentials
    Given User navigates to login page
    When User enters valid email address <username> into email field
    And User enters valid password <password> into password field
    And User clicks on Login button
    Then User should get successfully logged in
    Examples:
      | username               | password |
      | amotooricap1@gmail.com | 12345    |
      | amotooricap9@gmail.com | 12345    |
      | testluka@gmail.com     | 12345    |

  Scenario: Login with invalid credentials
    Given User navigates to login page
    When User enters invalid email address into email field
    And User enters invalid password "1234567890" into password field
    And User clicks on Login button
    Then User should get a proper warning message about credentials mismatch

  Scenario: Login with valid email and invalid password
    Given User navigates to login page
    When User enters valid email address "testluka@gmail.com" into email field
    And User enters invalid password "1234567890" into password field
    And User clicks on Login button
    Then User should get a proper warning message about credentials mismatch

  Scenario: Login with invalid email and valid password
    Given User navigates to login page
    When User enters invalid email address into email field
    And User enters valid password "12345" into password field
    And User clicks on Login button
    Then User should get a proper warning message about credentials mismatch

  Scenario: Login without providing any credentials
    Given User navigates to login page
    When User doesn't enter email address into email field
    And User doesn't enter password into password field
    And User clicks on Login button
    Then User should get a proper warning message about credentials mismatch

