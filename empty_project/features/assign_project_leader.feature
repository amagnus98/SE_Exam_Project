Feature: Assign project leader
Description: A developer is assigned as project leader on a project
Actor: Developer

Scenario: A developer assigns a project leader to a project
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  And a developer with initials "kape" exists in the company
  When the current user assigns a developer with initials "kape" as project leader of project with project number "22001"
  Then the project leader of project with project number "22001" is set to the developer with initials "kape"

Scenario: User gives wrong initials for project leader
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  And a developer with initials "amag" does not exists in the company
  When the current user assigns a developer with initials "amag" as project leader of project with project number "22001"  
  Then the system provides an error message "No developer with the given initials exists in the system"
  And the developer with initials "amag" is not assigned as project leader of project with project number "22001"

# MÅSKE TILFØJ DENNE, MEN GIVER DET MENING I GUI'EN. BLIVER DET NOGENSINDE MULIGT?
# Scenario: User assigns a project leader to a project that does not exist
#   Given the current user is a developer with initials "bond"
#   And a project with project number "22001" exists in the system
#   And a developer with initials "amag" does not exists in the company
#   When the current user assigns a developer with initials "amag" as project leader of project with project number "22001"  
#   Then the system provides an error message "No developer with the given initials exists in the system"
#   And the developer with initials "amag" is not assigned as project leader of project with project number "22001"