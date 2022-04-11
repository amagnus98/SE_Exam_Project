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
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;

public class SetStartAndEndTimeSteps {

  private App app;
  private ErrorMessageHolder errorMessage;
    
	public SetStartAndEndTimeSteps(App app, ErrorMessageHolder errorMessage) {
        this.app = app;
        this.errorMessage = errorMessage;
	  }

  // SCENARIO 1
  @When("the current user sets the start time of the activity with name {string} of project with project number {string} to year {int} and week {int}")
  public void the_current_user_sets_the_start_time_of_the_activity_with_name_of_project_with_project_number_to_year_and_week(String activityName, String projectNumber, int startYear, int startWeek) throws Exception{
      try {
          this.app.setStartTime(startYear, startWeek, activityName, projectNumber);
      } 
      catch (OperationNotAllowedException e){
          this.errorMessage.setErrorMessage(e.getMessage());
      }    
  }

  @Then("the start time of the activity with name {string} of project with project number {string} is updated to year {int} and week {int}")
  public void the_start_time_of_the_activity_with_name_of_project_with_project_number_is_updated_to_year_and_week(String activityName, String projectNumber, int startYear, int startWeek) throws Exception {
    // Get activity 
    Project project = this.app.getProject(projectNumber);
    Activity activity = project.getActivity(activityName);
    
    // Test whether start year and week is set correctly
    assertEquals(activity.getStartYear(),startYear);
    assertEquals(activity.getStartWeek(),startWeek);
  }

  

  
  // SCENARIO 2
  @When("the current user sets the end time of the activity with name {string} of project with project number {string} to year {int} and week {int}")
  public void the_current_user_sets_the_end_time_of_the_activity_with_name_of_project_with_project_number_to_year_and_week(String activityName, String projectNumber, int endYear, int endWeek) throws Exception{
      try {
          this.app.setEndTime(endYear, endWeek, activityName, projectNumber);
      } 
      catch (OperationNotAllowedException e){
          this.errorMessage.setErrorMessage(e.getMessage());
      }    
  }

  @Then("the end time of the activity with name {string} of project with project number {string} is updated to year {int} and week {int}")
  public void the_end_time_of_the_activity_with_name_of_project_with_project_number_is_updated_to_year_and_week(String activityName, String projectNumber, int endYear, int endWeek) throws Exception{
    // Get activity
    Project project = this.app.getProject(projectNumber);
    Activity activity = project.getActivity(activityName);

    // Test whether end year and week is set correctly
    assertEquals(activity.getEndYear(),endYear);
    assertEquals(activity.getEndWeek(),endWeek);
  }




  // SCENARIO 3
  @Given("the start time of the activity with name {string} of project with project number {string} is not already set to year {int} and week {int}")
  public void the_start_time_of_the_activity_with_name_of_project_with_project_number_is_not_already_set_to_year_and_week(String activityName, String projectNumber, int startYear, int startWeek)throws Exception {
    
    // Get activity
    Project project = this.app.getProject(projectNumber);
    Activity activity = project.getActivity(activityName);  

    // Checks
    boolean notEqualTime = (activity.getStartYear() != startYear) || (activity.getStartWeek() != startWeek);
    assertTrue(notEqualTime);
  }

  @Then("the start time of the activity with name {string} of project with project number {string} is not updated to year {int} and week {int}")
  public void the_start_time_of_the_activity_with_name_of_project_with_project_number_is_not_updated_to_year_and_week(String activityName, String projectNumber, int startYear, int startWeek) throws Exception{
    // Get activity
    Project project = this.app.getProject(projectNumber);
    Activity activity = project.getActivity(activityName);  

    // Checks
    boolean notEqualTime = (activity.getStartYear() != startYear) || (activity.getStartWeek() != startWeek);
    assertTrue(notEqualTime);
  }




  // SCENARIO 4
  @Given("the end time of the activity with name {string} of project with project number {string} is not already set to year {int} and week {int}")
  public void the_end_time_of_the_activity_with_name_of_project_with_project_number_is_not_already_set_to_year_and_week(String activityName, String projectNumber, int endYear, int endWeek) throws Exception {
        // Get activity
        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);  

        // Checks
        boolean notEqualTime = (activity.getStartYear() != endYear) || (activity.getStartWeek() != endWeek);
        assertTrue(notEqualTime);
  }
  
  @Then("the end time of the activity with name {string} of project with project number {string} is not updated to year {int} and week {int}")
  public void the_end_time_of_the_activity_with_name_of_project_with_project_number_is_not_updated_to_year_and_week(String activityName, String projectNumber, int endYear, int endWeek) throws Exception{
        // Get activity
        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);  

        // Checks
        boolean notEqualTime = (activity.getEndYear() != endYear) || (activity.getEndWeek() != endWeek);
        assertTrue(notEqualTime);
  }



  // SCENARIO 5
  @Given("the start time of the activity with name {string} of project with project number {string} is set to year {int} and week {int}")
  public void the_start_time_of_the_activity_with_name_of_project_with_project_number_is_set_to_year_and_week(String activityName, String projectNumber, int startYear, int startWeek) throws Exception {
      Project project = this.app.getProject(projectNumber);
      Activity activity = project.getActivity(activityName); 

      activity.setStartYear(startYear);
      activity.setStartWeek(startWeek);
  }
}