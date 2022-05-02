Feature: Perform time registration
Description: A developer registers time to a project or non-work actitivy 
Actor: Developer

Scenario: Register time to a project activity for the first time on the given date
    Given the current user is a developer with initials "bond"
    And a project with project number current year plus "001" exists in the system
    And the project with project number current year plus "001" contains an activity with name "Activity Name"
    And the user with initials "bond" is allowed to register hours for the activity with name "Activity Name" of project with project number current year plus "001"
    And the user with initials "bond" has registered no hours for the activity with name "Activity Name" of project with project number current year plus "001" for day 1 of week 23 of year 2022 
    And the total hours registered to the project with project number current year plus "001" is 19 hours
    And the total hours registered to the activity with name "Activity Name" of the project with project number current year plus "001" is 10 hours
    And the start time of the activity with name "Activity Name" of project with project number current year plus "001" is year 2022 and week 20
    And the end time of the activity with name "Activity Name" of project with project number current year plus "001" is year 2022 and week 40
    When the current user registers 5.5 hours for day 1 of week 23 of year 2022 to the activity with name "Activity Name" of project with project number current year plus "001"
    Then 5.5 hours are registered to the current users personal calender on day 1 of week 23 of year 2022 to the activity with name "Activity Name" of project with project number current year plus "001"
    And the total registered time of the project with project number current year plus "001" is set to 24.5 hours
    And the total registered time of the activity with name "Activity Name" of project with project number current year plus "001" is set to 15.5 hours

Scenario: Register time to a project activity on a date with preregistred hours
    Given the current user is a developer with initials "bond"
    And a project with project number current year plus "001" exists in the system
    And the project with project number current year plus "001" contains an activity with name "Activity Name"
    And the user with initials "bond" is allowed to register hours for the activity with name "Activity Name" of project with project number current year plus "001"
    And the user with initials "bond" has registered 7 hours for the activity with name "Activity Name" of project with project number current year plus "001" for day 1 of week 23 of year 2022
    And the total hours registered to the project with project number current year plus "001" is 19 hours
    And the total hours registered to the activity with name "Activity Name" of the project with project number current year plus "001" is 10 hours
    And the start time of the activity with name "Activity Name" of project with project number current year plus "001" is year 2022 and week 20
    And the end time of the activity with name "Activity Name" of project with project number current year plus "001" is year 2022 and week 40
    When the current user registers 5.5 hours for day 1 of week 23 of year 2022 to the activity with name "Activity Name" of project with project number current year plus "001"
    Then 5.5 hours are registered to the current users personal calender on day 1 of week 23 of year 2022 to the activity with name "Activity Name" of project with project number current year plus "001"
    And the total registered time of the project with project number current year plus "001" is set to 17.5 hours
    And the total registered time of the activity with name "Activity Name" of project with project number current year plus "001" is set to 8.5 hours

Scenario: Non assigned developer tries to register hours to activity
    Given the current user is a developer with initials "bond"
    And a project with project number current year plus "001" exists in the system
    And the project with project number current year plus "001" contains an activity with name "Activity Name"
    And the user with initials "bond" is not allowed to register hours for the activity with name "Activity Name" of project with project number current year plus "001"
    And the total hours registered to the project with project number current year plus "001" is 19 hours
    And the total hours registered to the activity with name "Activity Name" of the project with project number current year plus "001" is 10 hours
    And the start time of the activity with name "Activity Name" of project with project number current year plus "001" is year 2022 and week 20
    And the end time of the activity with name "Activity Name" of project with project number current year plus "001" is year 2022 and week 40
    When the current user registers 5.5 hours for day 1 of week 23 of year 2022 to the activity with name "Activity Name" of project with project number current year plus "001"
    Then the system provides an error message "The user is not assigned the given activity" 
    And the total registered time of the project with project number current year plus "001" is set to 19 hours
    And the total registered time of the activity with name "Activity Name" of project with project number current year plus "001" is set to 10 hours


Scenario: Register time to a non work activity
    Given the current user is a developer with initials "bond"
    And a project exists in the system for non work activities
    And the project for non work activities contains an activity with name "Vacation"
    And the total hours registered to the non work activity project is 19 hours
    And the total hours registered to the activity with name "Vacation" of the non work activity project is 10 hours
    When the current user registers 24 hours for day 1 of week 23 of year 2022 to the activity with name "Vacation" of the non work activity project
    Then 24 hours are registered to the current users personal calender on day 1 of week 23 of year 2022 to the activity with name "Vacation" of the non work activity project
    And the total registered time of the non work activity project is set to 43 hours
    And the total registered time of the activity with name "Vacation" of the non work activity project is set to 34 hours

Scenario: The user tries to register negative hours
    Given the current user is a developer with initials "bond"
    And a project with project number current year plus "001" exists in the system
    And the project with project number current year plus "001" contains an activity with name "Activity Name"
    And the user with initials "bond" is allowed to register hours for the activity with name "Activity Name" of project with project number current year plus "001"
    And the total hours registered to the project with project number current year plus "001" is 19 hours
    And the total hours registered to the activity with name "Activity Name" of the project with project number current year plus "001" is 10 hours
    When the current user registers -5.5 hours for day 1 of week 23 of year 2022 to the activity with name "Activity Name" of project with project number current year plus "001"
    Then the system provides an error message "Hours must be more than zero and not greater than 24" 
    And the total registered time of the project with project number current year plus "001" is set to 19 hours
    And the total registered time of the activity with name "Activity Name" of project with project number current year plus "001" is set to 10 hours

Scenario: The user tries to register more than 24 hours
    Given the current user is a developer with initials "bond"
    And a project with project number current year plus "001" exists in the system
    And the project with project number current year plus "001" contains an activity with name "Activity Name"
    And the user with initials "bond" is allowed to register hours for the activity with name "Activity Name" of project with project number current year plus "001"
    And the total hours registered to the project with project number current year plus "001" is 19 hours
    And the total hours registered to the activity with name "Activity Name" of the project with project number current year plus "001" is 10 hours
    When the current user registers 25.5 hours for day 1 of week 23 of year 2022 to the activity with name "Activity Name" of project with project number current year plus "001"
    Then the system provides an error message "Hours must be more than zero and not greater than 24" 
    And the total registered time of the project with project number current year plus "001" is set to 19 hours
    And the total registered time of the activity with name "Activity Name" of project with project number current year plus "001" is set to 10 hours

Scenario: Register time to a project activity outside the time horizon of the project
    Given the current user is a developer with initials "bond"
    And a project with project number current year plus "001" exists in the system
    And the project with project number current year plus "001" contains an activity with name "Activity Name"
    And the user with initials "bond" is allowed to register hours for the activity with name "Activity Name" of project with project number current year plus "001"
    And the user with initials "bond" has registered no hours for the activity with name "Activity Name" of project with project number current year plus "001" for day 1 of week 23 of year 2022 
    And the total hours registered to the project with project number current year plus "001" is 19 hours
    And the total hours registered to the activity with name "Activity Name" of the project with project number current year plus "001" is 10 hours
    And the start time of the activity with name "Activity Name" of project with project number current year plus "001" is year 2022 and week 30
    And the end time of the activity with name "Activity Name" of project with project number current year plus "001" is year 2022 and week 40
    When the current user registers 5.5 hours for day 1 of week 23 of year 2022 to the activity with name "Activity Name" of project with project number current year plus "001"
    Then the system provides an error message "The user cannot register hours outside of the time horizon of the activity" 
    And the total registered time of the project with project number current year plus "001" is set to 19 hours
    And the total registered time of the activity with name "Activity Name" of project with project number current year plus "001" is set to 10 hours
