#@Test01
Feature: Contoso University Students page
  As a user
  If I visit the Contoso university application Students page,

Scenario:1- Contoso University Students page content checking
    Given I launch the contoso university website
    When I click on "Students" menu tab with xpath ".//div[3]/div[1]/div[3]/div[1]/ul/li[3]/a"
    Then I will be on the "" page
    And I should see the "Student List" text with xpath ".//div[3]/div[2]/h2[1]"
    And I should see the data "Student List" text with id "MainContent_StudentsGridView"
    And I should see the data "Student List" text with id "MainContent_SearchGridView"
    And I close the Browser


Scenario:2- CU edit students data from Students page
    Given I launch the contoso university website
    When I click on "Students" menu tab with xpath ".//div[3]/div[1]/div[3]/div[1]/ul/li[3]/a"
    Then I will be on the "" page
    When I click on link xpath ".//div[3]/div[2]/div[1]/table/tbody/tr[4]/td[1]/a[1]"
    And I enter the "01/06/2000" to textbox with xpath ".//div[3]/div[2]/div[1]/table/tbody/tr[4]/td[3]/input"
    And  I click on link xpath ".//div[3]/div[2]/div[1]/table/tbody/tr[4]/td[1]/a[1]"
    Then I should see the "01/06/2000" text with xpath ".//div[3]/div[2]/div[1]/table/tbody/tr[4]/td[3]"
    And I close the Browser

