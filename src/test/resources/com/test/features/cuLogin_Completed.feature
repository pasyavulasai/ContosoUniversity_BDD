#@Test01
Feature: Contoso University Login page
  As a user
  If I visit the Contoso university application Login page, I expect to be able to log on by entering my user name and password.
  If I enter an invalid username or password, I expect to receive a message and to remain
  not logged on. I expect to have the option to allow the machine to remember me.


  Scenario:1 Invalid User
    Given I launch the contoso university website
    When I click on Login link with "id" "HeadLoginView_HeadLoginStatus"
    And I enter the username "Invalid1" and password "Password1"
    Then I will see the error message "Your login attempt was not successful. Please try again."
    And I will remain on the "Log In" page

  Scenario:2 Missing password
    Given I launch the contoso university website
    When I click on Login link with "id" "HeadLoginView_HeadLoginStatus"
    And I enter the username "Invalid1" and password ""
    Then I will see the password error message "Password is required."
    And I will remain on the "Log In" page

  Scenario:3 valid ussername and password
    Given I launch the contoso university website
    When I click on Login link with "id" "HeadLoginView_HeadLoginStatus"
    And I enter the username "pasyavula" and password "password1"
    Then I will remain on the "Home Page" page
    And I should see username "pasyavula" at the right top of the page