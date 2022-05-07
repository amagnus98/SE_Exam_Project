Feature: Assign estimated work hours for an activity
Description: The estimated amount of work hours is assigned to an activity
Actors: Project leader

Scenario: A project leader sets the estimated number of work hours needed for an activity
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "amag" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  When the current user sets the estimated number of work hours needed for the activity with name "Activity Name" of project with project number current year plus "001" to 20.5 hours
  Then the estimated number of work hours needed for the activity with name "Activity Name" of project with project number current year plus "001" is set to 20.5 hours

Scenario: A project leader sets the estimated number of work hours needed for an activity to a negative number
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "amag" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  When the current user sets the estimated number of work hours needed for the activity with name "Activity Name" of project with project number current year plus "001" to -20.5 hours
  Then the system provides an error message "The estimated number of hours must be non-negative" 
  And the estimated number of work hours needed for the activity with name "Activity Name" of project with project number current year plus "001" is not set to -20.5 hours

Scenario: User is not project leader and tries to set the estimated number of work hours needed for an activity
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "amag" is not the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  And the estimated work hours needed for activity with name "Activity Name" of project with project number current year plus "001" is not 20.5 hours
  When the current user sets the estimated number of work hours needed for the activity with name "Activity Name" of project with project number current year plus "001" to 20.5 hours
  Then the system provides an error message "Only the project leader can set the estimated number of work hours to this activity"
  And the estimated number of work hours needed for the activity with name "Activity Name" of project with project number current year plus "001" is not set to 20.5 hours

