Feature: Assign estimated work hours
Description: The estimated amount of work hours is assigned to an activity
Actors: Project leader

Scenario: A project leader sets the estimated number of work hours needed for an activity
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  And the developer with initials "bond" is the project leader of the project with project number "22001"
  And the project with project number "22001" contains an activity with name "Activity Name"
  When the current user sets the estimated number of work hours needed for the activity with name "Activity Name" of project with project number "22001" to 20.5 hours
  Then the estimated number of work hours needed for the activity with name "Activity Name" of project with project number "22001" is set to 20.5 hours

Scenario: User is not project leader and tries to set the estimated number of work hours needed for an activity
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  And the developer with initials "bond" is not the project leader of the project with project number "22001"
  And the project with project number "22001" contains an activity with name "Activity Name"
  And the estimated work hours needed for activity with name "Activity Name" of project with project number "22001" is not 20.5 hours
  When the current user sets the estimated number of work hours needed for the activity with name "Activity Name" of project with project number "22001" to 20.5 hours
  Then the system provides an error message "Only the project leader can set the estimated number of work hours to this activity"
  And the estimated number of work hours needed for the activity with name "Activity Name" of project with project number "22001" is not set to 20.5 hours



