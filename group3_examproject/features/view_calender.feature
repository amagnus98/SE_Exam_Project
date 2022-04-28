Feature: View calender
Description: A user views their calender
Actors: Developer

Scenario: View calendar for a specific day
    Given the current user is a developer with initials "bond"
    And a project with project number current year plus "001" exists in the system
    And a project with project number current year plus "002" exists in the system
    And the project with project number current year plus "001" contains an activity with name "Activity 1"
    And the user with initials "bond" has registered 5 hours for the activity with name "Activity 1" of project with project number current year plus "001" for day 1 of week 23 of year 2022
    And the project with project number current year plus "001" contains an activity with name "Activity 2"
    And the user with initials "bond" has registered 7.5 hours for the activity with name "Activity 2" of project with project number current year plus "001" for day 1 of week 23 of year 2022
    And the project with project number current year plus "002" contains an activity with name "Activity 3"
    And the user with initials "bond" has registered 1.5 hours for the activity with name "Activity 3" of project with project number current year plus "002" for day 1 of week 23 of year 2022
    When the current user views their calender for day 1 of week 23 of year 2022
    Then the current user can see 5 hours registered for the activity with name "Activity 1" of project with project number current year plus "001"
    And the current user can see 7.5 hours registered for the activity with name "Activity 2" of project with project number current year plus "001"
    And the current user can see 1.5 hours registered for the activity with name "Activity 3" of project with project number current year plus "002"

Scenario: View calendar a specific day with no registered hours
    Given the current user is a developer with initials "bond"
    And the user with initials "bond" has registered no hours for day 1 of week 23 of year 2022
    When the current user views their calender for day 1 of week 23 of year 2022
    Then the system provides an error message "The user has registered no hours on the given day"