Feature: Toggle Theme
  In order to be able to switch between dark and light theme
  As a user
  I want to customize the app's appearance to my preference

  Scenario Outline: Toggle App Theme
    Given the app is in <initial_theme> mode
    When I click the toggle theme button
    Then the app should switch to the <expected_theme>

    Examples:
      | initial_theme | expected_theme |
      | light         | dark           |

    Scenario: Theme persistence after app restart
      Given the app was in dark mode
      When I close and reopen the app
      Then the app should still be in dark mode