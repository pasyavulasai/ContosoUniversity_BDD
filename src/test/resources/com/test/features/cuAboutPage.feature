Feature: Contoso University About page
  As a user
  If I visit the Contoso university application About page,

  @Test01
  Scenario:1.2-Contoso University about page content checking
    Given I launch the contoso university website
    When I click on "About" menu tab with xpath ".//div[3]/div[1]/div[3]/div[1]/ul/li[2]/a"
    Then I will be on the "About Us" page
#    And I should see the "Student Body Statistics" text with xpath ".//div[3]/div[2]/h2"
#    And I should see the data "Student Body Statistics" text with id "MainContent_StudentStatisticsGridView"


