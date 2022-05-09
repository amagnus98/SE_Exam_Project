# Responsible - Asbjørn Magnussen (s183546)

Feature: Set start and end time of an activity
Description: The user sets the start and end time of an activity
Actors: Project leader

Scenario: Set time horizon of an activity
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "amag" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  And the start time of the project with project number current year plus "001" is year 2022 and week 29
  And the end time of the project with project number current year plus "001" is year 2022 and week 45
  When the current user sets the start time to year 2022 and week 30 and end time to year 2022 and week 40 for the activity with name "Activity Name" of project with project number current year plus "001"
  Then the start time of the activity with name "Activity Name" of project with project number current year plus "001" is updated to year 2022 and week 30
  And the end time of the activity with name "Activity Name" of project with project number current year plus "001" is updated to year 2022 and week 40
  
Scenario: Non-project leader edits the time horizon of an actitivy
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "amag" is not the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  And the start time of the activity with name "Activity Name" of project with project number current year plus "001" is year 2022 and week 29
  And the end time of the activity with name "Activity Name" of project with project number current year plus "001" is year 2022 and week 45
  When the current user sets the start time to year 2022 and week 30 and end time to year 2022 and week 40 for the activity with name "Activity Name" of project with project number current year plus "001"
  Then the system provides an error message "The start and end time of the activity can't be edited, because the user is not the project leader" 
  And the start time of the activity with name "Activity Name" of project with project number current year plus "001" is not updated to year 2022 and week 30
  And the end time of the activity with name "Activity Name" of project with project number current year plus "001" is not updated to year 2022 and week 40

Scenario: User sets time horizon of an activity when the time horizon of the project has not been set yet
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "amag" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  And the time horizon of the project with project number current year plus "001" has not been set
  When the current user sets the start time to year 2022 and week 30 and end time to year 2022 and week 40 for the activity with name "Activity Name" of project with project number current year plus "001"
  Then the system provides an error message "The start and end time for the activity cannot be set until the time horizon of its assigned project has been defined" 
  And the start time of the activity with name "Activity Name" of project with project number current year plus "001" is not updated to year 2022 and week 30
  And the end time of the activity with name "Activity Name" of project with project number current year plus "001" is not updated to year 2022 and week 40

Scenario: End time occurs before start of actitivy
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "amag" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  And the start time of the project with project number current year plus "001" is year 2022 and week 29
  And the end time of the project with project number current year plus "001" is year 2022 and week 45
  And the start time of the activity with name "Activity Name" of project with project number current year plus "001" is year 2022 and week 29
  And the end time of the activity with name "Activity Name" of project with project number current year plus "001" is year 2022 and week 45
  When the current user sets the start time to year 2022 and week 30 and end time to year 2022 and week 25 for the activity with name "Activity Name" of project with project number current year plus "001"
  Then the system provides an error message "The end time can't occur before the start time" 
  And the start time of the activity with name "Activity Name" of project with project number current year plus "001" is not updated to year 2022 and week 30
  And the end time of the activity with name "Activity Name" of project with project number current year plus "001" is not updated to year 2022 and week 25

Scenario: User sets time horizon of an activity outside of the time horizon of the project
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "amag" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  And the start time of the project with project number current year plus "001" is year 2022 and week 39
  And the end time of the project with project number current year plus "001" is year 2022 and week 45
  When the current user sets the start time to year 2022 and week 50 and end time to year 2023 and week 17 for the activity with name "Activity Name" of project with project number current year plus "001"
  Then the system provides an error message "The given time horizon for the activity is not within the time horizon of its assigned project" 
  And the start time of the activity with name "Activity Name" of project with project number current year plus "001" is not updated to year 2022 and week 50
  And the end time of the activity with name "Activity Name" of project with project number current year plus "001" is not updated to year 2023 and week 17

Scenario: Format of time horizon for activity is invalid
  Given the current user is a developer with initials "amag"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "amag" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  And the start time of the project with project number current year plus "001" is year 2022 and week 29
  And the end time of the project with project number current year plus "001" is year 2022 and week 45
  And the start time of the activity with name "Activity Name" of project with project number current year plus "001" is year 2022 and week 29
  And the end time of the activity with name "Activity Name" of project with project number current year plus "001" is year 2022 and week 45
  When the current user sets the start time to year 2022 and week 56 and end time to year 2022 and week 60 for the activity with name "Activity Name" of project with project number current year plus "001"
  Then the system provides an error message "The weeks for the time horizon of the activity must be set to a number between 1 to 52" 
  And the start time of the activity with name "Activity Name" of project with project number current year plus "001" is not updated to year 2022 and week 56
  And the end time of the activity with name "Activity Name" of project with project number current year plus "001" is not updated to year 2022 and week 60



# Scenario: Start time is before current time