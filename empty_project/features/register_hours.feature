Feature: Perform time registration
Description: A developer registers time to a project or non-work actitivy 
Actor: Developer

Scenario: Register time to a project activity for the first time on the given date
    Given the current user is a developer with initials "bond"
    And a project with project number "22001" exists in the system
    And the project with project number "22001" contains an activity with name "Activity Name"
    And the user with initials "bond" is allowed to register hours for the activity with name "Activity Name" of project with project number "22001"
    And the user with initials "bond" has registered no hours for the activity with name "Activity Name" of project with project number "22001" for day 1 of week 23 of year 2022 
    And the total hours registered to the project with project number "22001" is 19 hours
    And the total hours registered to the activity with name "Activity Name" of the project with project number "22001" is 10 hours
    When the current user registers 5.5 hours for day 1 of week 23 of year 2022 to the activity with name "Activity Name" of project with project number "22001"
    Then 5.5 hours are registered to the current users personal calender on day 1 of week 23 of year 2022 to the activity with name "Activity Name" of project with project number "22001"
    And the total time of project with project number "22001" is set to 24.5 hours
    And the total time of activity with name "Activity Name" of project with project number "22001" is set to 15.5 hours

Scenario: Register time to a project activity on a date with preregistred hours
    Given the current user is a developer with initials "bond"
    And a project with project number "22001" exists in the system
    And the project with project number "22001" contains an activity with name "Activity Name"
    And the user with initials "bond" is allowed to register hours for the activity with name "Activity Name" of project with project number "22001"
    And the user with initials "bond" has registered 7 hours for the activity with name "Activity Name" of project with project number "22001" for day 1 of week 23 of year 2022
    And the total hours registered to the project with project number "22001" is 19 hours
    And the total hours registered to the activity with name "Activity Name" of the project with project number "22001" is 10 hours
    When the current user registers 5.5 hours for day 1 of week 23 of year 2022 to the activity with name "Activity Name" of project with project number "22001"
    Then 5.5 hours are registered to the current users personal calender on day 1 of week 23 of year 2022 to the activity with name "Activity Name" of project with project number "22001"
    And the total time of project with project number "22001" is set to 17.5 hours
    And the total time of activity with name "Activity Name" of project with project number "22001" is set to 8.5 hours

Scenario: Register time to a non work activity
    Given the current user is a developer with initials "bond"
    And a project with project number "00001" exists in the system for non work activities
    And the project with project number "00001" contains an activity with name "Vacation"
    And the total hours registered to the project with project number "00001" is 19 hours
    And the total hours registered to the activity with name "Vacation" of the project with project number "00001" is 10 hours
    When the current user registers 24 hours for day 1 of week 23 of year 2022 to the activity with name "Vacation" of project with project number "00001"
    Then 24 hours are registered to the current users personal calender on day 1 of week 23 of year 2022 to the activity with name "Vacation" of project with project number "00001"
    And the total time of project with project number "00001" is set to 43 hours
    And the total time of activity with name "Vacation" of project with project number "00001" is set to 34 hours


# et use case med negative timer eller ikke divisible med en 1/2 timer eller mere end 24 timer
# måske tjekke at der registreres timer inden for start og sluttid på aktiviteten