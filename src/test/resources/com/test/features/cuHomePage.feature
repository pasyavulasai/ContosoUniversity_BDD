Feature: Contoso University Home page
  As a user
  If I visit the Contoso university application Home page,

#  @Test01
  Scenario: Contoso University home page content checking
    Given I launch the contoso university website
    When I click on "Home" menu tab with xpath ".//div[3]/div[1]/div[3]/div[1]/ul/li[1]/a"
    Then I will be on the "Home Page" page
    And I should see the "Contoso University" text with xpath ".//div[3]/div[1]/div[1]/h1"
    And I should see the "Welcome to Contoso University!" text with xpath ".//div[3]/div[2]/h2"


