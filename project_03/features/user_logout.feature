# Responsible - Kasper Helverskov Petersen (s203294)

Feature: User logout
	Description: The user logs out from the system
	Actor: Developer
	
Scenario: User logs out of the system
	Given that a user is logged in
	When the user logs out
	Then the user is not logged in