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

    // SCENARIO 1
    @Given("the estimated number of work hours needed for the project with project number current year plus {string} is {double} hours")
    public void the_estimated_number_of_work_hours_needed_for_the_project_with_project_number_current_year_plus_is_hours(String trackNumber, double estimatedHours) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String projectNumber = currentYear % 100 + trackNumber;

        Project project = this.app.getProject(projectNumber);
        project.setEstimatedWorkHours(estimatedHours);
        
        assertTrue(project.getEstimatedWorkHours() == estimatedHours);
    }

    @Given("the estimated number of work hours needed for the activity with name {string} of project with project number current year plus {string} is {double} hours")
    public void the_estimated_number_of_work_hours_needed_for_the_activity_with_name_of_project_with_project_number_current_year_plus_is_hours(String activityName, String trackNumber, double estimatedHours) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String projectNumber = currentYear % 100 + trackNumber;

        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);
        activity.setEstimatedWorkHours(estimatedHours);
        
        assertTrue(project.getEstimatedWorkHours() == estimatedHours);
    }

    @When("the current user requests the project report for the project with project number current year plus {string}")
    public void the_current_user_requests_the_project_report_for_the_project_with_project_number_current_year_plus(String trackNumber) throws Exception{
        try {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            String projectNumber = currentYear % 100 + trackNumber;
            this.app.generateProjectReport(projectNumber); 
        } 
        catch (OperationNotAllowedException e){
            this.errorMessage.setErrorMessage(e.getMessage());
        }
    }
    
    @Then("the project report of the project with project number current year plus {string} is generated")
    public void the_project_report_of_the_project_with_project_number_current_year_plus_is_generated(String trackNumber) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String projectNumber = currentYear % 100 + trackNumber;

        ProjectReport projectReport = this.app.getCurrentProjectReport();
        assertEquals(projectReport.getProjectNumber(), projectNumber);
    }

    @Then("the project report of project with project number current year plus {string} shows a total of {double} hours worked on the project")
    public void the_project_report_of_project_with_project_number_current_year_plus_shows_a_total_of_hours_worked_on_the_project(String trackNumber, double workHours) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String projectNumber = currentYear % 100 + trackNumber;

        ProjectReport projectReport = this.app.getCurrentProjectReport();
        assertEquals(projectReport.getProjectNumber(), projectNumber);
        assertTrue(projectReport.getTotalHoursRegisteredToProject() == workHours);
    }

    @Then("the project report of project with project number current year plus {string} shows a total of {double} hours worked on the activity with name {string}")
    public void the_project_report_of_project_with_project_number_current_year_plus_shows_a_total_of_hours_worked_on_the_activity_with_name(String trackNumber, double workHours, String activityName) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String projectNumber = currentYear % 100 + trackNumber;
        
        ProjectReport projectReport = this.app.getCurrentProjectReport();
        assertEquals(projectReport.getProjectNumber(), projectNumber);
        assertTrue(projectReport.getTotalHoursWorkedOnActivity(activityName) == workHours);
    }

    @Then("the project report of project with project number current year plus {string} shows the estimated work hours needed is {double} hours for the project")
    public void the_project_report_of_project_with_project_number_current_year_plus_shows_the_estimated_work_hours_needed_is_hours_for_the_project(String trackNumber, double workHours) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String projectNumber = currentYear % 100 + trackNumber;

        ProjectReport projectReport = this.app.getCurrentProjectReport();
        assertEquals(projectReport.getProjectNumber(), projectNumber);
        assertTrue(projectReport.getEstimatedHoursForProject() == workHours);
    }

    @Then("the project report of project with project number current year plus {string} shows the estimated work hours needed is {double} hours for the activity with name {string}")
    public void the_project_report_of_project_with_project_number_current_year_plus_shows_the_estimated_work_hours_needed_is_hours_for_the_activity_with_name(String trackNumber, double workHours, String activityName) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String projectNumber = currentYear % 100 + trackNumber;

        ProjectReport projectReport = this.app.getCurrentProjectReport();
        assertEquals(projectReport.getProjectNumber(), projectNumber);
        assertTrue(projectReport.getEstimatedHoursOnActivity(activityName) == workHours);
    }

    // SCENARIO 2
    @Then("the project report of the project with project number current year plus {string} is not generated")
    public void the_project_report_of_the_project_with_project_number_current_year_plus_is_not_generated(String trackNumber) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String projectNumber = currentYear % 100 + trackNumber;

        boolean hasProjectReport = this.app.hasCurrentProjectReport();

        assertTrue((!hasProjectReport) || this.app.getCurrentProjectReport().getProjectNumber().equals(projectNumber));
    }


}
