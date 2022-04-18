Feature: Perform time registration
Description: A developer registers time to a project or non-work actitivy 
Actor: Developer

Scenario: Register time to a project activity
    Given the current user is a developer with initials "bond"
    And a project with project number "22001" exists in the system
    And the project with project number "22001" contains an activity with name "Activity Name"
    And the user with initials "bond" is allowed to register hours for the activity with name "Activity Name" of project with project number "22001"
    When the user registers 5.5 hours on day 1 in week 23 to the activity with name "Activity Name" of project with project number "22001"
    Then 5.5 hours are registered to the users personal calender on day 1 in week 23 to the activity with name "Activity Name" of project with project number "22001"
    And the total time of project with project Number "22001" and the activity with name "Activity name" is increased with 5.5 hours

# Find activity
# developer.registerHours(activityName,...)
# this.findActivityCalender(a)

# developer.registerHours(acit..)
# project.addToTotalHours

# activityCalendar = developer.calender.findActivity(String projectNumber, String activityName) : Hashmap<String,int>
# dateKey = generateDateKey(int year, int week, int day)
# registeredHours = 5
# activityCalendar.put(dateKey,registeredHours)
# key: developer.calendar.get(year).get(week).get(day).get

# Scenario: User is not allowed to register time on the activity
#     Given the user is a developer with initials xxxx
#     And the user is not allowed to register time to activity a of project yyxxx
#     When the user registers k hours on day d in week j to activity a of project yyxxx
#     Then the system provides an error message that the user isn’t currently working on activity a
#     And the system does not register hours to activity a

# Scenario: Register time to a non work activity
#     Given the user is a developer with initials xxxx
#     When the user registers k hours on day d in week j to a non work activity
#     Then k hours are registered to the users personal calender on day d in week j