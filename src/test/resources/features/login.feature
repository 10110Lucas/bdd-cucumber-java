# Author Lucas

@SmokeTest2
Feature: Test login functionality

  Scenario Outline: Check login is successful with valid credentials
    Given opening browser
    And user is on login page
    When user enters "<username>" and "<password>"
    And user click on login
    Then user is navigated to the home page

    Examples:
    | username      | password      |
    | standard_user | secret_sauce  |

  Scenario Outline: Check login is error with locked user
    Given opening browser
    And user is on login page
    When user enters "<username>" and "<password>"
    And user click on login
    Then user is not navigated to the home page

    Examples:
      | username        | password     |
      | locked_out_user | secret_sauce |