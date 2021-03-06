# Responsible - Mads Vibe Ringsted (s204144)

Feature: Assign project leader
Description: A developer is assigned as project leader on a project
Actor: Developer

Scenario: A developer assigns a project leader to a project
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And a developer with initials "kape" exists in the company
  When the current user assigns a developer with initials "kape" as project leader of the project with project number current year plus "001"
  Then the project leader of the project with project number current year plus "001" is set to the developer with initials "kape"
  And the developer with initials "kape" is assigned to the project with project number current year plus "001"

Scenario: Developer does not exist
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And a developer with initials "bond" does not exist in the company
  When the current user assigns a developer with initials "bond" as project leader of the project with project number current year plus "001"  
  Then the system provides an error message "No developer with the given initials exists in the system"
  And the developer with initials "bond" is not assigned as project leader of the project with project number current year plus "001"
  And the developer with initials "bond" is not assigned to the project with project number current year plus "001"
  