$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("com/test/features/cuAboutPage.feature");
formatter.feature({
  "line": 1,
  "name": "Contoso University About page",
  "description": "As a user\r\nIf I visit the Contoso university application About page,",
  "id": "contoso-university-about-page",
  "keyword": "Feature"
});
formatter.before({
  "duration": 1753500,
  "status": "passed"
});
formatter.before({
  "duration": 275500,
  "status": "passed"
});
formatter.before({
  "duration": 10854571200,
  "status": "passed"
});
formatter.before({
  "duration": 1191700,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "1.2-Contoso University about page content checking",
  "description": "",
  "id": "contoso-university-about-page;1.2-contoso-university-about-page-content-checking",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 5,
      "name": "@Test01"
    }
  ]
});
formatter.step({
  "line": 7,
  "name": "I launch the contoso university website",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I click on \"About\" menu tab with xpath \".//div[3]/div[1]/div[3]/div[1]/ul/li[2]/a\"",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "I will be on the \"About Us\" page",
  "keyword": "Then "
});
formatter.match({
  "location": "CUStepDefs.i_launch_the_contoso_university_website()"
});
formatter.result({
  "duration": 18037772500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "About",
      "offset": 12
    },
    {
      "val": ".//div[3]/div[1]/div[3]/div[1]/ul/li[2]/a",
      "offset": 40
    }
  ],
  "location": "CUStepDefs.i_click_on_menu_tab_with_xpath(String,String)"
});
formatter.result({
  "duration": 2885800,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "About Us",
      "offset": 18
    }
  ],
  "location": "CUStepDefs.i_will_be_on_the_page(String)"
});
formatter.result({
  "duration": 84600,
  "status": "passed"
});
formatter.write("http://localhost/MyApp  16062018_170407");
formatter.embedding("image/png", "embedded0.png");
formatter.after({
  "duration": 2065417700,
  "status": "passed"
});
formatter.after({
  "duration": 93200,
  "status": "passed"
});
});