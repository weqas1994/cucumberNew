Feature: Validating Syntax /jobTitle.php HRMS API

  Background: 
    Given user generates token

  @GETAllJobTitles
  Scenario: Retrieving all Job Titles using /jobTitle.php API
    Given user calls jobTitle API
    When User retrieves response for /jobTitle.php
    Then status code is 200 for jobTitle
    Then user validates job titles
