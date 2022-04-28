Feature: Assign developer to activity
Description: The user adds a developer to an activity
Actor: Project leader

Scenario: Add developer to activity who is assigned to the project
  Given the current user is a developer with initials "bond"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "bond" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  And a developer with initials "mani" exists in the company
  And the developer with initials "mani" is already assigned to the project with project number current year plus "001"
  And the developer with initials "mani" is not currently assigned to the activity with name "Activity Name" of project with project number current year plus "001"
  When the current user assigns the developer with initials "mani" to activity with name "Activity Name" of project with project number current year plus "001"
  Then the developer with initials "mani" is assigned to the activity with name "Activity Name" of project with project number current year plus "001"

Scenario: Add developer to activity who is not already assigned to the project
  Given the current user is a developer with initials "bond"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "bond" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  And a developer with initials "mani" exists in the company
  And the developer with initials "mani" is not currently assigned to the project with project number current year plus "001"
  And the developer with initials "mani" is not currently assigned to the activity with name "Activity Name" of project with project number current year plus "001"
  When the current user assigns the developer with initials "mani" to activity with name "Activity Name" of project with project number current year plus "001"
  Then the developer with initials "mani" is assigned to the activity with name "Activity Name" of project with project number current year plus "001"
  And the developer with initials "mani" is assigned to the project with project number current year plus "001"

Scenario: User is not project leader
  Given the current user is a developer with initials "bond"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "bond" is not the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  And a developer with initials "mani" exists in the company
  And the developer with initials "mani" is already assigned to the project with project number current year plus "001"
  And the developer with initials "mani" is not currently assigned to the activity with name "Activity Name" of project with project number current year plus "001"
  When the current user assigns the developer with initials "mani" to activity with name "Activity Name" of project with project number current year plus "001"
  Then the system provides an error message "Only the project leader can assign developers to this activity"
  And the developer with initials "mani" is not assigned to the activity with name "Activity Name" of project with project number current year plus "001"

Scenario: Developer is already assigned to the activity
  Given the current user is a developer with initials "bond"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "bond" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  And a developer with initials "mani" exists in the company
  And the developer with initials "mani" is already assigned to the project with project number current year plus "001"
  And the developer with initials "mani" is already assigned to the activity with name "Activity Name" of project with project number current year plus "001"
  When the current user assigns the developer with initials "mani" to activity with name "Activity Name" of project with project number current year plus "001"
  Then the system provides an error message "The developer is already assigned to the given activity by the project leader"

Scenario: Developer does not exist in the system
  Given the current user is a developer with initials "bond"
  And a project with project number current year plus "001" exists in the system
  And the developer with initials "bond" is the project leader of the project with project number current year plus "001"
  And the project with project number current year plus "001" contains an activity with name "Activity Name"
  And a developer with initials "amag" does not exist in the company
  When the current user assigns the developer with initials "amag" to activity with name "Activity Name" of project with project number current year plus "001"
  Then the system provides an error message "No developer with the given initials exists in the system"
  And the developer with initials "amag" is not assigned to the activity with name "Activity Name" of project with project number current year plus "001"

# mulig use case - hvis man prøver at assigne developer til en aktivitet, som ikke er tilknyttet projektet 