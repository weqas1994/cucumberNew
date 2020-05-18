Feature: Validating Syntax /updateEmployee.php HRMS API
Background:
Given user generates token

@SyntaxHRMSAPIEndToEnd
Scenario: Retrieving created Employee using /updateEmployee.php API
Given user calls updateEmployee API
When User retrieves response for updateEmployee
Then status code is 200 for updateEmployee
Then user verifies employee name was updated
Then user validates employee was updated