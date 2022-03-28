Feature: Create new project
Description: A user creates a new project
Actor: Developer

Scenario: Create a new project with a name
	Given that the user is a developer with initials "amag"
	When the user creates a new project
	And the sets the name of the project to "Project Name" 
	Then a project with a project number "22001" is added to Softwarehuset A/Ss list of current
		  projects
	And the name of the project with project number "22001" is "Project name"

Scenario: Create a project without a name
	Given that the user is a developer with initials "amag"
	When the user creates a new project
	Then a project with a project number "22001" is added to Softwarehuset A/Ss list of current
		projects

