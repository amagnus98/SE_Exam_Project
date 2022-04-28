Feature: Create project activity
Description: The user creates a new activity within a project
Actors: Project leader

Scenario: Create activity
  Given the current user is a developer with initials "bond"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "bond" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" does not contain an activity with name "Activity Name"
  When the current user creates an activity with name "Activity Name" for the project with project number current year plus "001"
  Then the activity with name "Activity Name" is added to the list of activities for the project with project number current year plus "001"

Scenario: Activity already exists
  Given the current user is a developer with initials "bond"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "bond" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  When the current user creates an activity with name "Activity Name" for the project with project number current year plus "001"
  Then the system provides an error message "Project already contains an activity with the given name"
  And the list of activities for the project with project number current year plus "001" does not contain the activity with name "Activity Name" twice

  
Scenario: User is not the project leader of project
  Given the current user is a developer with initials "bond"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "bond" is not the project leader of the project with project number current year plus "001"
  When the current user creates an activity with name "Activity Name" for the project with project number current year plus "001"
  Then the system provides an error message "The activity can't be added to the project, because the user is not the project leader" 
  And the activity with name "Activity Name" is not added to the list of activities for the project with project number current year plus "001"
