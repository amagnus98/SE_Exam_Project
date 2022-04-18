package dtu.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.model.domain.Activity;
import system.model.domain.App;
import system.model.domain.Developer;
import system.model.domain.DeveloperCalendar;
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;

public class RegisterHoursSteps {
    private App app;
    private ErrorMessageHolder errorMessage;
    
	public RegisterHoursSteps(App app, ErrorMessageHolder errorMessage) {
        this.app = app;
        this.errorMessage = errorMessage;
	}

    // SCENARIO 1
    @Given("the user with initials {string} is allowed to register hours for the activity with name {string} of project with project number {string}")
    public void the_user_with_initials_is_allowed_to_register_hours_for_the_activity_with_name_of_project_with_project_number(String initials, String activityName, String projectNumber) throws Exception{
        Developer developer = this.app.getDeveloper(initials);
        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);

        activity.addDeveloper(developer);

        assertTrue(activity.canRegisterHours(developer));
    }

    @Given("the user with initials {string} has registered no hours for the activity with name {string} of project with project number {string} for day {int} of week {int} of year {int}")
    public void the_user_with_initials_has_registered_no_hours_for_the_activity_with_name_of_project_with_project_number_for_day_of_week_of_year(String initials, String activityName, String projectNumber, int day, int week, int year) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        Developer developer = this.app.getDeveloper(initials);
        DeveloperCalendar developerCalendar = developer.getDeveloperCalendar();
        
        assertFalse(developerCalendar.hasRegisteredHoursForActivity(day, week, year, projectNumber, activityName));

    }

    @Given("the total hours registered to the project with project number {string} is {double} hours")
    public void the_total_hours_registered_to_the_project_with_project_number_is_hours(String projectNumber, double totalHours) throws Exception{
        Project project = this.app.getProject(projectNumber);
        project.setTotalHoursWorked(totalHours);

        assertTrue(project.getTotalHoursWorked() == totalHours);
    }

    @Given("the total hours registered to the activity with name {string} of the project with project number {string} is {int} hours")
    public void the_total_hours_registered_to_the_activity_with_name_of_the_project_with_project_number_is_hours(String activityName, String projectNumber, double totalHours) throws Exception{
        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);
        activity.setTotalHoursWorked(totalHours);

        assertTrue(activity.getTotalHoursWorked() == totalHours);
    }

    @When("the current user registers {double} hours for day {int} of week {int} of year {int} to the activity with name {string} of project with project number {string}")
    public void the_current_user_registers_hours_for_day_of_week_of_year_to_the_activity_with_name_of_project_with_project_number(double registeredHours, int day, int week, int year, String activityName, String projectNumber) throws Exception {
        this.app.registerHoursToActivity(registeredHours, day, week, year, projectNumber, activityName);

    }

    @Then("{double} hours are registered to the current users personal calender on day {int} of week {int} of year {int} to the activity with name {string} of project with project number {string}")
    public void hours_are_registered_to_the_current_users_personal_calender_on_day_of_week_of_year_to_the_activity_with_name_of_project_with_project_number(double hours, int day, int week, int year, String activityName, String projectNumber) {
        Developer currentUser = this.app.getCurrentUser();
        assertTrue(currentUser.getRegisteredHours(day,week,year,projectNumber,activityName) == hours);
    }   
    
    @Then("the total time of project with project number {string} is set to {double} hours")
    public void the_total_time_of_project_with_project_number_is_set_to_hours(String projectNumber, double totalHours) throws Exception {
        Project project = this.app.getProject(projectNumber);
        assertTrue(project.getTotalHoursWorked() == totalHours);
    }

    @Then("the total time of activity with name {string} of project with project number {string} is set to {double} hours")
    public void the_total_time_of_activity_with_name_of_project_with_project_number_is_set_to_hours(String activityName, String projectNumber, double totalHours) throws Exception {
        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);
        assertTrue(activity.getTotalHoursWorked() == totalHours);
    }

    // SCENARIO 2
    @Given("the user with initials {string} has registered {double} hours for the activity with name {string} of project with project number {string} for day {int} of week {int} of year {int}")
    public void the_user_with_initials_has_registered_hours_for_the_activity_with_name_of_project_with_project_number_for_day_of_week_of_year(String initials, double hours, String activityName, String projectNumber, int day, int week, int year) throws Exception{
        Developer developer = this.app.getDeveloper(initials);
        DeveloperCalendar developerCalendar = developer.getDeveloperCalendar();
        developer.registerHours(hours, day, week, year, projectNumber, activityName);
        
        assertTrue(developerCalendar.getHours(day, week, year, projectNumber, activityName) == hours);

    }

    // SCENARIO 3
    @Given("a project with project number {string} exists in the system for non work activities")
    public void a_project_with_project_number_exists_in_the_system_for_non_work_activities(String projectNumber) throws Exception {
        this.app.createNonWorkActivitiesProject();

        assertTrue(this.app.projectExists(projectNumber));
    }


   
}
