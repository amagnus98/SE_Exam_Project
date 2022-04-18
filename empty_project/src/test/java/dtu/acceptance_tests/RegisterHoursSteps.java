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
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;

public class RegisterHoursSteps {
    private App app;
    private ErrorMessageHolder errorMessage;
    
	public RegisterHoursSteps(App app, ErrorMessageHolder errorMessage) {
        this.app = app;
        this.errorMessage = errorMessage;
	}


    @Given("the user with initials {string} is allowed to register hours for the activity with name {string} of project with project number {string}")
    public void the_user_with_initials_is_allowed_to_register_hours_for_the_activity_with_name_of_project_with_project_number(String initials, String activityName, String projectNumber) throws Exception{
        // Write code here that turns the phrase above into concrete actions
        Developer developer = this.app.getDeveloper(initials);
        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);

        activity.addDeveloper(developer);

        assertTrue(activity.canRegisterHours(developer));
    }

    @When("the user registers {double} hours on day {int} in week {int} to the activity with name {string} of project with project number {string}")
    public void the_user_registers_hours_on_day_in_week_to_the_activity_with_name_of_project_with_project_number(Double double1, Integer int1, Integer int2, String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("{double} hours are registered to the users personal calender on day {int} in week {int} to the activity with name {string} of project with project number {string}")
    public void hours_are_registered_to_the_users_personal_calender_on_day_in_week_to_the_activity_with_name_of_project_with_project_number(Double double1, Integer int1, Integer int2, String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the total time of project with project Number {string} and the activity with name {string} is increased with {double} hours")
    public void the_total_time_of_project_with_project_number_and_the_activity_with_name_is_increased_with_hours(String string, String string2, Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
