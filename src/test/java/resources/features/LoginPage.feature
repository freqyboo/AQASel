Feature: Testing of LoginPage
  Background:
    Given Set up driver

  Scenario Outline: Test of login with valid credentials

    When I open login page
    And I set valid email "<text1>"
    And I set valid password "<text2>"
    And I click on login button
    Then I see main page
    Examples:
      |text1  | text2 |
      |data@mail.com | 12345678|

  Scenario Outline: Test of login with wrong password

    When I open login page
    And I set valid email "<text1>"
    And I set invalid password "<text2>"
    And I click on login button
    Then I see error message "<text3>"
    Examples:
      |text1  | text2 | text3
      |data@mail.com | 123456777| wrong

  Scenario Outline: Test of login with empty password

    When I open login page
    And I set valid email "<text1>"
    And I leave password empty "<text2>"
    And I click on login button
    Then I see error message "<text3>"
    Examples:
      |text1 |text2 |text3
      |data@mail.com |  |empty

