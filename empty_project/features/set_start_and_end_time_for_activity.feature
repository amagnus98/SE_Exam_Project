Feature: Set start and end time of an activity
Description: The user sets the start and end time of an activity
Actors: Project leader

Scenario: Set start time of an activity
  Given the current user is a developer with initials "bond"
  And the developer with initials "bond" is the project leader of the project with project number "22001"
  And the project with project number "22001" contains an activity with name "Activity Name"
  When the current user sets the start time of the activity with name "Activity Name" of project with project number "22001" to year 2022 and week 30
  Then the start time of the activity with name "Activity Name" of project with project number "22001" is updated to year 2022 and week 30
  
Scenario: Set end time of an activity
  Given the current user is a developer with initials "bond"
  And the developer with initials "bond" is the project leader of the project with project number "22001"
  And the project with project number "22001" contains an activity with name "Activity Name"
  When the current user sets the end time of the activity with name "Activity Name" of project with project number "22001" to year 2022 and week 40
  Then the end time of the activity with name "Activity Name" of project with project number "22001" is updated to year 2022 and week 40

Scenario: Non-project leader edits start time
  Given the current user is a developer with initials "bond"
  And the developer with initials "bond" is not the project leader of the project with project number "22001"
  And the project with project number "22001" contains an activity with name "Activity Name"
  And the start time of the activity with name "Activity Name" of project with project number "22001" is not already set to year 2022 and week 30
  When the current user sets the start time of the activity with name "Activity Name" of project with project number "22001" to year 2022 and week 30
  Then the system provides an error message "The start time can't be edited, because the user is not the project leader" 
  And the start time of the activity with name "Activity Name" of project with project number "22001" is not updated to year 2022 and week 30

Scenario: Non-project leader edits end time
  Given the current user is a developer with initials "bond"
  And the developer with initials "bond" is not the project leader of the project with project number "22001"
  And the project with project number "22001" contains an activity with name "Activity Name"
  And the end time of the activity with name "Activity Name" of project with project number "22001" is not already set to year 2022 and week 40
  When the current user sets the end time of the activity with name "Activity Name" of project with project number "22001" to year 2022 and week 40
  Then the system provides an error message "The end time can't be edited, because the user is not the project leader" 
  And the end time of the activity with name "Activity Name" of project with project number "22001" is not updated to year 2022 and week 40

Scenario: End time occurs before start time
  Given the current user is a developer with initials "bond"
  And the developer with initials "bond" is the project leader of the project with project number "22001"
  And the project with project number "22001" contains an activity with name "Activity Name"
  And the start time of the activity with name "Activity Name" of project with project number "22001" is set to year 2022 and week 30
  When the current user sets the end time of the activity with name "Activity Name" of project with project number "22001" to year 2022 and week 29
  Then the system provides an error message "The end time can't occur before the start time" 
  And the end time of the activity with name "Activity Name" of project with project number "22001" is not updated to year 2022 and week 29


# Scenario: Start time is before current time