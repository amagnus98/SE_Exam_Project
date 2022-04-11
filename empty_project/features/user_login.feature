Feature: Login as a user
Description: Developers are able to login as a user
Actor: Developer

Scenario: User logs in to the system
	Given a developer with initials "bond" exists in the company
	When the user logs into the system with initials "bond"
	Then the current user of the system is set to a developer with initials "bond"

Scenario: User logs in with invalid initials
	Given a developer with initials "amag" does not exists in the company
	When the user logs into the system with initials "amag"
	Then the system provides an error message "No developer with the given initials exists in the system"
	And the system has no current user