# Responsible - Andreas Bigom (s200925)

Feature: Create project activity
Description: The user creates a new activity within a project
Actors: Project leader

Scenario: Create activity
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "amag" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" does not contain an activity with name "Activity Name"
  When the current user creates an activity with name "Activity Name" for the project with project number current year plus "001"
  Then the activity with name "Activity Name" is part of the list of activities for the project with project number current year plus "001"
  And the parent project of the activity with name "Activity Name" is set to the project with project number current year plus "001"

Scenario: Activity already exists
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "amag" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  When the current user creates an activity with name "Activity Name" for the project with project number current year plus "001"
  Then the system provides an error message "Project already contains an activity with the given name"
  And the list of activities for the project with project number current year plus "001" does not contain the activity with name "Activity Name" twice

  
Scenario: User is not the project leader of project
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "amag" is not the project leader of the project with project number current year plus "001"
  When the current user creates an activity with name "Activity Name" for the project with project number current year plus "001"
  Then the system provides an error message "The activity can't be added to the project, because the user is not the project leader" 
  And the activity with name "Activity Name" is not part of the list of activities for the project with project number current year plus "001"

Scenario: Project leader renames activity
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "amag" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity 1"
  And the project with project number current year plus "001" does not contain an activity with name "Activity 2"
  When the current user edits the name of the activity with name "Activity 1" of project with project number current year plus "001" to "Activity 2"
  Then the activity with name "Activity 2" is part of the list of activities for the project with project number current year plus "001"
  Then the activity with name "Activity 1" is not part of the list of activities for the project with project number current year plus "001"

Scenario: Non-Project leader renames activity
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "amag" is not the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity 1"
  And the project with project number current year plus "001" does not contain an activity with name "Activity 2"
  When the current user edits the name of the activity with name "Activity 1" of project with project number current year plus "001" to "Activity 2"
  Then the system provides an error message "The activity can't be renamed, because the user is not the project leader" 
  And the activity with name "Activity 1" is part of the list of activities for the project with project number current year plus "001"
  And the activity with name "Activity 2" is not part of the list of activities for the project with project number current year plus "001"

Scenario: Project leader renames activity to a name of another activity
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "amag" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity 1"
  And the project with project number current year plus "001" contains an activity with name "Activity 2"
  When the current user edits the name of the activity with name "Activity 1" of project with project number current year plus "001" to "Activity 2"
  Then the system provides an error message "Project already contains an activity with the given name" 
  And the activity with name "Activity 1" is part of the list of activities for the project with project number current year plus "001"
  And the activity with name "Activity 2" is part of the list of activities for the project with project number current year plus "001"