Feature: Login as a user
Description: Developers are able to login as a user
Actor: Developer

Scenario: User logs in to the system
	Given a developer with initials "amag" exists in the company
	When the user logs into the system with initials "amag"
	Then the initials of the current user of the system is set to "amag"

# Scenario: User writes invalid initials
#	Given a developer with initials "amag" does not exists in the company
#	When the user logs into the system with initials "amag"
#	Then the system provides an error message "developer with given initials does not exist in the system"
#	And the initials of the current user of the system is not set to "amag"