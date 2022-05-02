Feature: Assign developer to project
Description: The user adds a developer to a project
Actor: Developer

Scenario: Add developer to project
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And a developer with initials "mani" exists in the company
  And the developer with initials "mani" is not currently assigned to the project with project number current year plus "001"
  When the current user assigns the developer with initials "mani" to the project with project number current year plus "001"
  Then the developer with initials "mani" is assigned to the project with project number current year plus "001"

Scenario: Developer is already assigned to the project
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And a developer with initials "mani" exists in the company
  And the developer with initials "mani" is already assigned to the project with project number current year plus "001"
  When the current user assigns the developer with initials "mani" to the project with project number current year plus "001"
  Then the system provides an error message "The developer is already assigned to the given project"

Scenario: Developer does not exist in the system
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And a developer with initials "bond" does not exist in the company
  When the current user assigns the developer with initials "bond" to the project with project number current year plus "001"
  Then the system provides an error message "No developer with the given initials exists in the system"
  And the developer with initials "bond" is not assigned to the project with project number current year plus "001"