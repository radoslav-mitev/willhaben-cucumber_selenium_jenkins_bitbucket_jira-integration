# The following scenarios aim to test three of more possible error messages during the login process on www.willhaben.at
# The first two scenarios retrieve test data from a MySQL database.
# The third one uses the data table below.


Feature: WillhabenLoginErrorAlerts
  Background:
    Given an user opens www.willhaben.at in a chrome browser

  @database
  Scenario: Login test using incorrect e-mail format (address without @ and domain)
    When trying to log into account using non-valid e-mail address format
    Then should see [Bitte gib eine gueltige E-Mail-Adresse an]

  @database @last
  Scenario: Login test using correct e-mail format but non valid credentials
    When trying to log into account using an e-mail address in valid format
    Then user should see [Login fehlgeschlagen]

  Scenario Outline: Login test using correct e-mail format but without a password
    When trying to log into account using an "<e-mail address>" in valid format without a "<password>"
    Then user should see [Bitte gib dein Passwort ein]

    Examples:
      | e-mail address | password |
      | test@gmail.at  |          |
      | test2@gmail.at |          |