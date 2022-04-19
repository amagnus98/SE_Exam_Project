Feature: Set start and end time of a project
Description: The user sets the start and end time of a project
Actors: Project leader

Scenario: Set time horizon of a project
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  And the developer with initials "bond" is the project leader of the project with project number "22001"
  When the current user sets the start time to year 2022 and week 30 and end time to year 2022 and week 40 of the project with project number "22001"
  Then the start time of the project with project number "22001" is updated to year 2022 and week 30
  And the end time of the project with project number "22001" is updated to year 2022 and week 40
  
Scenario: Non-project leader edits the time horizon of a project
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  And the developer with initials "bond" is not the project leader of the project with project number "22001"
  And the start time of the project with project number "22001" is year 2022 and week 35
  And the end time of the project with project number "22001" is year 2022 and week 38
  When the current user sets the start time to year 2022 and week 30 and end time to year 2022 and week 40 of the project with project number "22001"
  Then the system provides an error message "The start and end time of the project can't be edited, because the user is not the project leader" 
  And the start time of the project with project number "22001" is not updated to year 2022 and week 30
  And the end time of the project with project number "22001" is not updated to year 2022 and week 40

Scenario: End time occurs before start time for project
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  And the developer with initials "bond" is the project leader of the project with project number "22001"
  And the start time of the project with project number "22001" is year 2022 and week 35
  And the end time of the project with project number "22001" is year 2022 and week 40
  When the current user sets the start time to year 2022 and week 30 and end time to year 2022 and week 25 of the project with project number "22001"
  Then the system provides an error message "The end time can't occur before the start time" 
  And the start time of the project with project number "22001" is not updated to year 2022 and week 30
  And the end time of the project with project number "22001" is not updated to year 2022 and week 25

# if a project contains activities with a given time horizon the user cannot
# set the time horizon of the project so that the activities time horizons will lie outside
Scenario: The user tries to edit the time horizon for a project that conflicts with the time horizon of its activities
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  And the developer with initials "bond" is the project leader of the project with project number "22001"
  And the start time of the project with project number "22001" is year 2022 and week 20
  And the end time of the project with project number "22001" is year 2022 and week 50
  And the project with project number "22001" contains an activity with name "Activity Name"
  And the start time of the activity with name "Activity Name" of project with project number "22001" is year 2022 and week 25
  And the end time of the activity with name "Activity Name" of project with project number "22001" is year 2022 and week 45
  When the current user sets the start time to year 2022 and week 30 and end time to year 2022 and week 40 of the project with project number "22001"
  Then the system provides an error message "The new time horizon of the project conflicts with the time horizon of the activities in the project" 
  And the start time of the project with project number "22001" is not updated to year 2022 and week 30
  And the end time of the project with project number "22001" is not updated to year 2022 and week 40

# Scenario: Start time is before current time
