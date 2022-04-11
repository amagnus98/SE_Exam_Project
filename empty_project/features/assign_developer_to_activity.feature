Feature: Assign developer to activity
Description: The user adds a developer to an activity
Actor: Project leader

Scenario: Add developer to activity
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  And the developer with initials "bond" is the project leader of the project with project number "22001"
  And the project with project number "22001" contains an activity with name "Activity Name"
  And a developer with initials "mani" exists in the company
  And a developer with initials "mani" is not currently working on the activity with name "Activity Name" of project with project number "22001"
  When the current user assigns a developer with initials "mani" to activity with name "Activity Name" of project with project number "22001"
  Then the developer with initials "mani" is assigned to the activity with name "Activity Name" of project with project number "22001"

Scenario: User is not project leader
  Given the current user is a developer with initials "bond"
  And a project with project number "22001" exists in the system
  And the developer with initials "bond" is not the project leader of the project with project number "22001"
  And the project with project number "22001" contains an activity with name "Activity Name"
  And a developer with initials "mani" exists in the company
  And a developer with initials "mani" is not currently working on the activity with name "Activity Name" of project with project number "22001"
  When the current user assigns a developer with initials "mani" to activity with name "Activity Name" of project with project number "22001"
  Then the system provides an error message "Only the project leader can add developers to this activity"
  And the developer with initials "mani" is not assigned to the activity with name "Activity Name" of project with project number "22001"

# Scenario: Developer is already assigned to the activity
#   Given the current user is a developer with initials "bond"
#   And the developer with initials "bond" is not the project leader of the project with project number "22001"
#   And the project with project number "22001" contains an activity with name "Activity Name"
#   And a developer with initials "mani" exists in the company
#   And a developer with initials "mani" is currently working on the activity with name "Activity Name" of project with project number "22001"
#   When the current user assigns a developer with initials "mani" to activity with name "Activity Name" of project with project number "22001"
#   Then the system provides an error message "Only the project leader can add developers to this activity"
#   And the developer with initials "mani" is not assigned to the activity with name "Activity Name" of project with project number "22001"