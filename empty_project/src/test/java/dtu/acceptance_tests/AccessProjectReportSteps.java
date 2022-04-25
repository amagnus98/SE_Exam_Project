package dtu.acceptance_tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.model.domain.*;

import system.model.domain.Activity;
import system.model.domain.App;
import system.model.domain.Developer;
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;

public class AccessProjectReportSteps {
  private App app;
  private ErrorMessageHolder errorMessage;
    
	public AccessProjectReportSteps(App app, ErrorMessageHolder errorMessage) {
        this.app = app;
        this.errorMessage = errorMessage;
	}

    @When("the current user requests the project report for the project with project number {string}")
    public void the_current_user_requests_the_project_report_for_the_project_with_project_number(String projectNumber) throws Exception {
        this.app.accessProjectReport(projectNumber);
    }

    @Then("the project report of the project with project number {string} is generated")
    public void the_project_report_of_the_project_with_project_number_is_generated(String projectNumber) throws Exception {
        ProjectReport projectReport = this.app.getCurrentProjectReport();
        assertEquals(projectReport.getProjectNumber(), projectNumber);
    }

    @Then("the project report of project with project number {string} shows a total of {double} hours worked on the project")
    public void the_project_report_of_project_with_project_number_shows_a_total_of_hours_worked_on_the_project(String projectNumber, double workHours) throws Exception {
        ProjectReport projectReport = this.app.getCurrentProjectReport();
        assertTrue(projectReport.getTotalHoursWorkedOnProject() == workHours);
    }

    @Then("the project report of project with project number {string} shows a total of {double} hours worked on the activity with name {string}")
    public void the_project_report_of_project_with_project_number_shows_a_total_of_hours_worked_on_the_activity_with_name(String projectNumber, double workHours, String activityName) {
        ProjectReport projectReport = this.app.getCurrentProjectReport();
        assertTrue(projectReport.getTotalHoursWorkedOnActivity(activityName) == workHours);
    }

    @Then("the project report of project with project number {string} shows the estimated work hours needed is {double} hours for the project")
    public void the_project_report_of_project_with_project_number_shows_the_estimated_work_hours_needed_is_hours_for_the_project(String projectNumber, double workHours) {
        ProjectReport projectReport = this.app.getCurrentProjectReport();
        assertTrue(projectReport.getEstimatedHoursWorkedOnProject() == workHours);
    }

    @Then("the project report of project with project number {string} shows the estimated work hours needed is {double} hours for the activity with name {string}")
    public void the_project_report_of_project_with_project_number_shows_the_estimated_work_hours_needed_is_hours_for_the_activity_with_name(String string, Double double1, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



}
