# Feature: Access project report
# Description: A project leader accesses a project report
# Actors: Project leader

#   Scenario: A project leader accesses a project report
#   Given the current user is a developer with initials "bond"
#   And a project with project number "22001" exists in the system
#   And the developer with initials "bond" is the project leader of the project with project number "22001"
#   When the current user requests the project report for the project with project number "22001"
#   Then the project report of the project with with project number "22001" is generated

# Scenario: The user is not the project leader
#   Given the current user is a developer with initials "bond"
#   And a project with project number "22001" exists in the system
#   And the developer with initials "bond" is not the project leader of the project with project number "22001"
#   When the current user requests the project report for the project with project number "22001"
#   Then the system provides an error message "Only the project leader can access the project report"
#   And the project report of the project with with project number "22001" is not generated
