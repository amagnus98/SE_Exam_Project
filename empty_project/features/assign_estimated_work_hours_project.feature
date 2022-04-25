Feature: Assign estimated work hours for a project
Description: The estimated amount of work hours is assigned to an activity
Actors: Project leader

Scenario: A project leader sets the estimated number of work hours needed for an activity
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  And the developer with initials "bond" is the project leader of the project with project number "22001"
  When the current user sets the estimated number of work hours needed for the project with project number "22001" to 20.5 hours
  Then the estimated number of work hours needed for the project with project number "22001" is set to 20.5 hours

Scenario: User is not project leader and tries to set the estimated number of work hours needed for a project
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  And the estimated work hours needed for project with project number "22001" is not 20.5 hours
  And the developer with initials "bond" is not the project leader of the project with project number "22001"
  When the current user sets the estimated number of work hours needed for the project with project number "22001" to 20.5 hours
  Then the system provides an error message "Only the project leader can set the estimated number of work hours to this project"
  And the estimated number of work hours needed for the project with project number "22001" is not set to 20.5 hours


  

