Feature: Login as a user
Description: Developers are able to login as a user
Actor: Developer

Scenario: User logs in to the system
	Given a developer with initials "amag" exists in the company
	When the user logs into the system with initials "amag"
	Then the current user of the system is set to a developer with initials "amag"

Scenario: User logs in with invalid initials
	Given a developer with initials "amag" does not exists in the company
	When the user logs into the system with initials "amag"
	Then the system provides an error message "Developer with given initials does not exist in the system"
	And the system has no current user

