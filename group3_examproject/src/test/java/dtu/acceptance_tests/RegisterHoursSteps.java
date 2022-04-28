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

import java.util.*;

public class RegisterHoursSteps {
    private App app;
    private ErrorMessageHolder errorMessage;
    
	public RegisterHoursSteps(App app, ErrorMessageHolder errorMessage) {
        this.app = app;
        this.errorMessage = errorMessage;
	}

    // SCENARIO 1
    @Given("the user with initials {string} is allowed to register hours for the activity with name {string} of project with project number current year plus {string}")
    public void the_user_with_initials_is_allowed_to_register_hours_for_the_activity_with_name_of_project_with_project_number_current_year_plus(String initials, String activityName, String trackNumber) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String projectNumber = currentYear % 100 + trackNumber;

        Developer developer = this.app.getDeveloper(initials);
        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);

        activity.addDeveloper(developer);

        assertTrue(activity.canRegisterHours(developer));
    }

    @Given("the user with initials {string} has registered no hours for the activity with name {string} of project with project number current year plus {string} for day {int} of week {int} of year {int}")
    public void the_user_with_initials_has_registered_no_hours_for_the_activity_with_name_of_project_with_project_number_current_year_plus_for_day_of_week_of_year(String initials, String activityName, String trackNumber, int day, int week, int year) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String projectNumber = currentYear % 100 + trackNumber;

        Developer developer = this.app.getDeveloper(initials);
        DeveloperCalendar developerCalendar = developer.getDeveloperCalendar();
        
        assertFalse(developerCalendar.hasRegisteredHoursForActivity(day, week, year, projectNumber, activityName));
    }

    @Given("the total hours registered to the project with project number current year plus {string} is {double} hours")
    public void the_total_hours_registered_to_the_project_with_project_number_current_year_plus_is_hours(String trackNumber, double totalHours) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String projectNumber = currentYear % 100 + trackNumber;

        Project project = this.app.getProject(projectNumber);
        project.setTotalHoursRegistered(totalHours);

        assertTrue(project.getTotalHoursRegistered() == totalHours);
    }

    @Given("the total hours registered to the activity with name {string} of the project with project number current year plus {string} is {double} hours")
    public void the_total_hours_registered_to_the_activity_with_name_of_the_project_with_project_number_current_year_plus_is_hours(String activityName, String trackNumber, double totalHours) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String projectNumber = currentYear % 100 + trackNumber;

        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);
        activity.setTotalHoursRegistered(totalHours);

        assertTrue(activity.getTotalHoursRegistered() == totalHours);
    }

    @When("the current user registers {double} hours for day {int} of week {int} of year {int} to the activity with name {string} of project with project number current year plus {string}")
    public void the_current_user_registers_hours_for_day_of_week_of_year_to_the_activity_with_name_of_project_with_project_number_current_year_plus(double registeredHours, int day, int week, int year, String activityName, String trackNumber) throws Exception {
        try {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		    String projectNumber = currentYear % 100 + trackNumber;
            this.app.registerHoursToActivity(registeredHours, day, week, year, projectNumber, activityName);
        }
        catch (OperationNotAllowedException e){
            this.errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("{double} hours are registered to the current users personal calender on day {int} of week {int} of year {int} to the activity with name {string} of project with project number current year plus {string}")
    public void hours_are_registered_to_the_current_users_personal_calender_on_day_of_week_of_year_to_the_activity_with_name_of_project_with_project_number_current_year_plus(double hours, int day, int week, int year, String activityName, String trackNumber) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String projectNumber = currentYear % 100 + trackNumber;

        Developer currentUser = this.app.getCurrentUser();
        assertTrue(currentUser.getRegisteredHours(day,week,year,projectNumber,activityName) == hours);
    }

    @Then("the total registered time of the project with project number current year plus {string} is set to {double} hours")
    public void the_total_registered_time_of_the_project_with_project_number_current_year_plus_is_set_to_hours(String trackNumber, double totalHours) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String projectNumber = currentYear % 100 + trackNumber;

        Project project = this.app.getProject(projectNumber);
        assertTrue(project.getTotalHoursRegistered() == totalHours);
    }
    
    @Then("the total registered time of the activity with name {string} of project with project number current year plus {string} is set to {double} hours")
    public void the_total_registered_time_of_the_activity_with_name_of_project_with_project_number_current_year_plus_is_set_to_hours(String activityName, String trackNumber, double totalHours) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String projectNumber = currentYear % 100 + trackNumber;

        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);
        assertTrue(activity.getTotalHoursRegistered() == totalHours);
    }

    // SCENARIO 2
    @Given("the user with initials {string} has registered {double} hours for the activity with name {string} of project with project number current year plus {string} for day {int} of week {int} of year {int}")
    public void the_user_with_initials_has_registered_hours_for_the_activity_with_name_of_project_with_project_number_current_year_plus_for_day_of_week_of_year(String initials, double hours, String activityName, String trackNumber, int day, int week, int year) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String projectNumber = currentYear % 100 + trackNumber;

        Developer developer = this.app.getDeveloper(initials);
        DeveloperCalendar developerCalendar = developer.getDeveloperCalendar();
        developer.registerHours(hours, day, week, year, projectNumber, activityName);
        
        assertTrue(developerCalendar.getHours(day, week, year, projectNumber, activityName) == hours);
    }

    // SCENARIO 3
    @Given("a project exists in the system for non work activities")
    public void a_project_exists_in_the_system_for_non_work_activities() throws Exception {
        assertTrue(this.app.hasNonWorkActivityProject());
    }

    @Given("the project for non work activities contains an activity with name {string}")
    public void the_project_for_non_work_activities_contains_an_activity_with_name(String activityName) throws Exception {
        Project project = this.app.getNonWorkActivityProject();
        assertTrue(project.containsActivity(activityName));
    }

    @Given("the total hours registered to the non work activity project is {double} hours")
    public void the_total_hours_registered_to_the_non_work_activity_project_is_hours(double totalHours) throws Exception {
        Project project = this.app.getNonWorkActivityProject();
        project.setTotalHoursRegistered(totalHours);

        assertTrue(project.getTotalHoursRegistered() == totalHours);
    }

    @Given("the total hours registered to the activity with name {string} of the non work activity project is {double} hours")
    public void the_total_hours_registered_to_the_activity_with_name_of_the_non_work_activity_project_is_hours(String activityName, double totalHours) throws OperationNotAllowedException {
        Project project = this.app.getNonWorkActivityProject();
        Activity activity = project.getActivity(activityName);
        activity.setTotalHoursRegistered(totalHours);

        assertTrue(activity.getTotalHoursRegistered() == totalHours);
    }

    @When("the current user registers {double} hours for day {int} of week {int} of year {int} to the activity with name {string} of the non work activity project")
    public void the_current_user_registers_hours_for_day_of_week_of_year_to_the_activity_with_name_of_the_non_work_activity_project(double registeredHours, int day, int week, int year, String activityName) throws Exception {
        try {
            this.app.registerHoursToNonWorkActivity(registeredHours, day, week, year, activityName);
        }
        catch (OperationNotAllowedException e){
            this.errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("{double} hours are registered to the current users personal calender on day {int} of week {int} of year {int} to the activity with name {string} of the non work activity project")
    public void hours_are_registered_to_the_current_users_personal_calender_on_day_of_week_of_year_to_the_activity_with_name_of_the_non_work_activity_project(double hours, int day, int week, int year, String activityName) throws Exception {
        String projectNumber = this.app.getNonWorkActivityProject().getProjectNumber();
        Developer currentUser = this.app.getCurrentUser();
        assertTrue(currentUser.getRegisteredHours(day,week,year,projectNumber,activityName) == hours);
    }

    @Then("the total registered time of the non work activity project is set to {double} hours")
    public void the_total_registered_time_of_the_non_work_activity_project_is_set_to_hours(double totalHours) throws Exception {
        Project project = this.app.getNonWorkActivityProject();
        assertTrue(project.getTotalHoursRegistered() == totalHours);
    }

    @Then("the total registered time of the activity with name {string} of the non work activity project is set to {double} hours")
    public void the_total_registered_time_of_the_activity_with_name_of_the_non_work_activity_project_is_set_to_hours(String activityName, double totalHours) throws Exception {
        Project project = this.app.getNonWorkActivityProject();
        Activity activity = project.getActivity(activityName);
        assertTrue(activity.getTotalHoursRegistered() == totalHours);
    }




   
}
