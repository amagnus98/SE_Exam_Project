Feature: Set start and end time of a project
Description: The user sets the start and end time of a project
Actors: Developer

Scenario: Set start time of a project
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  When the current user sets the start time of the project with project number "22001" to year 2022 and week 30
  Then the start time of the project with project number "22001" is updated to year 2022 and week 30
  
Scenario: Set end time of a project
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  When the current user sets the end time of the project with project number "22001" to year 2022 and week 40
  Then the end time of the project with project number "22001" is updated to year 2022 and week 40

Scenario: End time occurs before start time
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  And the start time of the project with project number "22001" is set to year 2022 and week 30
  When the current user sets the end time of the project with project number "22001" to year 2022 and week 29
  Then the system provides an error message "The end time can't occur before the start time" 
  And the end time of the project with project number "22001" is not updated to year 2022 and week 29


# Scenario: Start time is before current time