#@Test01
Feature: Contoso University Add Students page
  As a user
  If I visit the Contoso university application Add Students page,


  Scenario:1- Contoso University Add Students page content checking
    Given I launch the contoso university website
    When I click on "Students" menu tab with submenu with xpath ".//div[3]/div[1]/div[3]/div[1]/ul/li[3]/ul/li/a"
    Then I will be on the "" page
    And I should see the "Add New Students" text with xpath ".//div[3]/div[2]/h2"
    And I should see the data "Student Details" text with id "MainContent_StudentsDetailsView"
    And I close the Browser


 Scenario:2- Contoso University Add Students from Add Students page
    Given I launch the contoso university website
    When I click on "Students" menu tab with submenu with xpath ".//div[3]/div[1]/div[3]/div[1]/ul/li[3]/ul/li/a"
    Then I will be on the "" page
    When I enter the "Test" to textbox with xpath ".//div[3]/div[2]/div/table/tbody/tr[1]/td[2]/input"
    And I enter the "Test" to textbox with xpath ".//div[3]/div[2]/div/table/tbody/tr[2]/td[2]/input"
    And I enter the "10/05/2000" to textbox with xpath ".//div[3]/div[2]/div/table/tbody/tr[3]/td[2]/input"
    And I click on link xpath ".//table/tbody/tr[4]/td/a[1]"
    And I close the Browser


 Scenario:3- Contoso University cancel after adding the Students data from Add Students page
    Given I launch the contoso university website
    When I click on "Students" menu tab with submenu with xpath ".//div[3]/div[1]/div[3]/div[1]/ul/li[3]/ul/li/a"
    Then I will be on the "" page
    When I enter the "Test" to textbox with xpath ".//div[3]/div[2]/div/table/tbody/tr[1]/td[2]/input"
    And I enter the "Test" to textbox with xpath ".//div[3]/div[2]/div/table/tbody/tr[2]/td[2]/input"
    And I enter the "10/05/2000" to textbox with xpath ".//div[3]/div[2]/div/table/tbody/tr[3]/td[2]/input"
    And I click on link xpath ".//table/tbody/tr[4]/td/a[2]"
    And I close the Browser

