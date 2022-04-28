Feature: Access project report
Description: A project leader accesses a project report
Actors: Project leader

Scenario: A project leader accesses a project report
  Given the current user is a developer with initials "bond"
  And a project with project number current year plus "001" exists in the system
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  And the total hours registered to the project with project number current year plus "001" is 19 hours
  And the total hours registered to the activity with name "Activity Name" of the project with project number current year plus "001" is 10 hours
  And the estimated number of work hours needed for the project with project number current year plus "001" is 20.5 hours
  And the estimated number of work hours needed for the activity with name "Activity Name" of project with project number current year plus "001" is 20.5 hours
  And the developer with initials "bond" is the project leader of the project with project number current year plus "001"
  When the current user requests the project report for the project with project number current year plus "001"
  Then the project report of the project with project number current year plus "001" is generated
  And the project report of project with project number current year plus "001" shows a total of 19 hours worked on the project
  And the project report of project with project number current year plus "001" shows a total of 10 hours worked on the activity with name "Activity Name"
  And the project report of project with project number current year plus "001" shows the estimated work hours needed is 20.5 hours for the project
  And the project report of project with project number current year plus "001" shows the estimated work hours needed is 20.5 hours for the activity with name "Activity Name"


Scenario: A non project leader accesses a project report
  Given the current user is a developer with initials "bond"
  And a project with project number current year plus "001" exists in the system
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  And the developer with initials "bond" is not the project leader of the project with project number current year plus "001"
  When the current user requests the project report for the project with project number current year plus "001"
  Then the system provides an error message "Only the project leader can access the project report"
  And the project report of the project with project number current year plus "001" is not generated




