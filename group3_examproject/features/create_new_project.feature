Feature: Create new project
Description: The current user creates a new project
Actor: Developer

Scenario: Create a new project with a name in the current year
	Given the current user is a developer with initials "amag"
	And there are 4 projects in the system for the current year
  	When the current user creates a new project with name "Project Name"
	Then a project with project number current year plus "005" is added to the company's list of current projects
	And the name of the project with project number current year plus "005" is set to "Project Name"

Scenario: Create a project without a name in the current year
  	Given the current user is a developer with initials "amag"
	And there are 4 projects in the system for the current year
  	When the current user creates a new project without a name
	Then a project with project number current year plus "005" is added to the company's list of current projects
	And the name of the project with project number current year plus "005" is set to "Unnamed"

Scenario: Change name of project
  	Given the current user is a developer with initials "amag"
	And there are 4 projects in the system for the current year
	And the name of the project with project number current year plus "004" is "Project name 1"
  	When the current user edits the name of the project with project number "004" to "Project name 2"
	Then the name of the project with project number current year plus "004" is set to "Project name 2"



# create use case where the year is 2030