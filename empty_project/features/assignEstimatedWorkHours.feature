#Feature: Assign estimated work hours
#Description: The estimated amount of work hours is assigned to an activity
#Actors: Project leader
#
#Scenario: A project leader sets the estimated number of work hours for an activity
#	Given that the user with initials xxxx is the project leader of project yyxxx
#	And activity a is part of project yyxxx
#	When the user with initials xxxx sets the number of work hours for activity a of project yyxxx to k hours
#	Then the estimated number of work hours for activity a of project yyxxx is set to k hours
#
#Scenario: The user is not the project leader
#	Given that the user with initials xxxx is not the project leader of project yyxxx
#	And activity a is part of project yyxxx
#	When the user with initials xxxx sets the number of work hours for activity a of project yyxxx to k hours
#	Then the system provides an error message that the user is not the project leader of project yyxxx
#	And the estimated time for activty a of project yyxxx is not set
