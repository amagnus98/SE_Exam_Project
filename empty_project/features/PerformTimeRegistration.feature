#Feature: Perform time registration
#Description: A developer registers time to a project activity
#Actor: Developer
#
#Scenario: Register time to a project activity
    #Given the user is a developer with initials "wanb"
    #And the user is allowed to register time to activity a of project "yyxxx"
    #When the user registers k hours on day d in week j to activity a of project yyxxx
    #Then k hours are registered to the users personal calender on day d in week j
    #And the total time of project yyxxx and activity a is increased with k hours
#
#Scenario: User is not allowed to register time on the activity
    #Given the user is a developer with initials xxxx
    #And the user is not allowed to register time to activity a of project yyxxx
    #When the user registers k hours on day d in week j to activity a of project yyxxx
    #Then the system provides an error message that the user isn’t currently working on activity a
    #And the system does not register hours to activity a
#
#Scenario: Register time to a non-work activity
    #Given the user is a developer with initials xxxx
    #When the user registers k hours on day d in week j to a non-work activity
    #Then k hours are registered to the users personal calender on day d in week j