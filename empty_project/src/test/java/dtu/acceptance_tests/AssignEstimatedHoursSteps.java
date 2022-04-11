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

public class AssignEstimatedHoursSteps {
  
  private App app;
  private ErrorMessageHolder errorMessage;

  public AssignEstimatedHoursSteps(App app, ErrorMessageHolder errorMessage, MockDateHolder dateHolder) {
		this.app = app;
		this.errorMessage = errorMessage;
	}

  // SCENARIO 1
  @When("the current user sets the estimated number of work hours needed for the activity with name {string} of project with project number {string} to {double} hours")
  public void the_current_user_sets_the_estimated_number_of_work_hours_needed_for_the_activity_with_name_of_project_with_project_number_to_hours(String activityName, String projectNumber, double workHours) throws Exception{  
    try {
      this.app.setEstimatedWorkHoursForActivity(workHours, activityName, projectNumber);
    }
    catch (OperationNotAllowedException e){
        this.errorMessage.setErrorMessage(e.getMessage());
    }
  }

  @Then("the estimated number of work hours needed for the activity with name {string} of project with project number {string} is set to {double} hours")
  public void the_estimated_number_of_work_hours_needed_for_the_activity_with_name_of_project_with_project_number_is_set_to_hours(String activityName, String projectNumber, double workHours) throws Exception{
    Project project = this.app.getProject(projectNumber);
    Activity activity = project.getActivity(activityName);  
    
    assertTrue(activity.getEstimatedWorkHours() == workHours);
  }


 // SCENARIO 2
  @Given("the estimated work hours needed for activity with name {string} of project with project number {string} is not {double} hours")
  public void the_estimated_work_hours_needed_for_activity_with_name_of_project_with_project_number_is_not_hours(String activityName, String projectNumber, double workHours) throws Exception {
    Project project = this.app.getProject(projectNumber);
    Activity activity = project.getActivity(activityName);  
    
    assertFalse(activity.getEstimatedWorkHours() == workHours);
  }

  @Then("the estimated number of work hours needed for the activity with name {string} of project with project number {string} is not set to {double} hours")
  public void the_estimated_number_of_work_hours_needed_for_the_activity_with_name_of_project_with_project_number_is_not_set_to_hours(String activityName, String projectNumber, double workHours) throws Exception{
     Project project = this.app.getProject(projectNumber);
    Activity activity = project.getActivity(activityName);  
    
    assertFalse(activity.getEstimatedWorkHours() == workHours);
  }
}