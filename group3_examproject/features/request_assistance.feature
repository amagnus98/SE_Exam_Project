Feature: Request assistance on activity
Description: A developer requests assistance from another developer for a given activity
Actors: Developer

Scenario: A developer requests assistance succesfully
    Given the current user is a developer with initials "bond"
    And a project with project number current year plus "001" exists in the system
    And the project with project number current year plus "001" contains an activity with name "Activity Name"
    And the developer with initials "bond" is assigned by the project leader to the activity with name "Activity Name" of project with project number current year plus "001"
    And a developer with initials "mani" exists in the company
    And the developer with initials "mani" is not currently working on the activity with name "Activity Name" of project with project number current year plus "001"
    When the current user requests assistance from the developer with initials "mani" for the activity with name "Activity Name" of project with project number current year plus "001"
    Then the developer with initials "mani" is allowed to register hours for the activity with name "Activity Name" of project with project number current year plus "001"

Scenario: Developer is already working on the activity
    Given the current user is a developer with initials "bond"
    And a project with project number current year plus "001" exists in the system
    And the project with project number current year plus "001" contains an activity with name "Activity Name"
    And the developer with initials "bond" is assigned by the project leader to the activity with name "Activity Name" of project with project number current year plus "001"
    And a developer with initials "mani" exists in the company
    And the developer with initials "mani" is currently working on the activity with name "Activity Name" of project with project number current year plus "001"
    When the current user requests assistance from the developer with initials "mani" for the activity with name "Activity Name" of project with project number current year plus "001"
    Then the system provides an error message "The developer is already working on the given activity"

Scenario: The user is not assigned to the activity by the project leader
    Given the current user is a developer with initials "bond"
    And a project with project number current year plus "001" exists in the system
    And the project with project number current year plus "001" contains an activity with name "Activity Name"
    And the developer with initials "bond" is not assigned by the project leader to the activity with name "Activity Name" of project with project number current year plus "001"
    And a developer with initials "mani" exists in the company
    And the developer with initials "mani" is not currently working on the activity with name "Activity Name" of project with project number current year plus "001"
    When the current user requests assistance from the developer with initials "mani" for the activity with name "Activity Name" of project with project number current year plus "001"
    Then the system provides an error message "The current user is not assigned to the activity by the project leader and cannot request assistance"
    And the developer with initials "mani" is not allowed to register hours for the activity with name "Activity Name" of project with project number current year plus "001"

Scenario: The requested developer does not exist in the system
    Given the current user is a developer with initials "bond"
    And a project with project number current year plus "001" exists in the system
    And the project with project number current year plus "001" contains an activity with name "Activity Name"
    And the developer with initials "bond" is not assigned by the project leader to the activity with name "Activity Name" of project with project number current year plus "001"
    And a developer with initials "amag" does not exist in the company
    When the current user requests assistance from the developer with initials "amag" for the activity with name "Activity Name" of project with project number current year plus "001"
    Then the system provides an error message "No developer with the given initials exists in the system"
    And the developer with initials "amag" is not allowed to register hours for the activity with name "Activity Name" of project with project number current year plus "001"
