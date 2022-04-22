Feature: Access project report
Description: A project leader accesses a project report
Actors: Project leader

Scenario: A project leader accesses a project report
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  And the project with project number "22001" contains an activity with name "Activity Name"
  And the total hours registered to the project with project number "22001" is 19 hours
  And the total hours registered to the activity with name "Activity Name" of the project with project number "22001" is 10 hours
  And the estimated number of work hours needed for the activity with name "Activity Name" of project with project number "22001" is set to 20.5 hours
  And the developer with initials "bond" is the project leader of the project with project number "22001"
  When the current user requests the project report for the project with project number "22001"
  Then the project report of the project with project number "22001" is generated
  And the project report of project with project number "22001" shows a total of 19 hours worked on the project with project number "22001"
  And the project report of project with project number "22001" shows a total of 19 hours worked on the activity with name "Activity Name"
  And the project report of project with project number "22001" shows an estimate of 20.5 hours remaining on project with project number "22001"
  And the project report of project with project number "22001" shows an estimate of 20.5 hours worked on the activity with name "Activity Name"

# Scenario: The user is not the project leader
#   Given the current user is a developer with initials "bond"
#   And a project with project number "22001" exists in the system
#   And the developer with initials "bond" is not the project leader of the project with project number "22001"
#   When the current user requests the project report for the project with project number "22001"
#   Then the system provides an error message "Only the project leader can access the project report"
#   And the project report of the project with with project number "22001" is not generated
