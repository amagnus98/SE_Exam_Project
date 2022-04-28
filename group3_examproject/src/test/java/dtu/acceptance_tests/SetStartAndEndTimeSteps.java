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

import java.util.*;

public class SetStartAndEndTimeSteps {

  private App app;
  private ErrorMessageHolder errorMessage;
    
	public SetStartAndEndTimeSteps(App app, ErrorMessageHolder errorMessage) {
        this.app = app;
        this.errorMessage = errorMessage;
	}

  // PROJECT SCENARIOS
  // SCENARIO 1
  @When("the current user sets the start time to year {int} and week {int} and end time to year {int} and week {int} of the project with project number current year plus {string}")
  public void the_current_user_sets_the_start_time_to_year_and_week_and_end_time_to_year_and_week_of_the_project_with_project_number_current_year_plus(int startYear, int startWeek, int endYear, int endWeek, String trackNumber) throws Exception{
      try {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String projectNumber = currentYear % 100 + trackNumber;
        this.app.setTimeHorizonOfProject(startYear,startWeek,endYear,endWeek,projectNumber);
      } 
      catch (OperationNotAllowedException e){
          this.errorMessage.setErrorMessage(e.getMessage());
      }    
  }

  @Then("the start time of the project with project number current year plus {string} is updated to year {int} and week {int}")
  public void the_start_time_of_the_project_with_project_number_current_year_plus_is_updated_to_year_and_week(String trackNumber, int startYear, int startWeek) throws Exception {
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      String projectNumber = currentYear % 100 + trackNumber;

      // Get project
      Project project = this.app.getProject(projectNumber);
      
      // Test whether start year and week is set correctly
      assertEquals(project.getStartYear(),startYear);
      assertEquals(project.getStartWeek(),startWeek);
  }

  @Then("the end time of the project with project number current year plus {string} is updated to year {int} and week {int}")
  public void the_end_time_of_the_project_with_project_number_current_year_plus_is_updated_to_year_and_week(String trackNumber, int endYear, int endWeek) throws Exception {
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      String projectNumber = currentYear % 100 + trackNumber;

      // Get project
      Project project = this.app.getProject(projectNumber);

      // Test whether end year and week is set correctly
      assertEquals(project.getEndYear(),endYear);
      assertEquals(project.getEndWeek(),endWeek);
  }

  // SCENARIO 2
  @Given("the start time of the project with project number current year plus {string} is year {int} and week {int}")
  public void the_start_time_of_the_project_with_project_number_current_year_plus_is_year_and_week(String trackNumber, int startYear, int startWeek) throws Exception {
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      String projectNumber = currentYear % 100 + trackNumber;

      Project project = this.app.getProject(projectNumber);
      project.setStartYear(startYear);
      project.setStartWeek(startWeek);
      assertEquals(project.getStartYear(), startYear);
      assertEquals(project.getStartWeek(), startWeek);
  }

  @Given("the end time of the project with project number current year plus {string} is year {int} and week {int}")
  public void the_end_time_of_the_project_with_project_number_current_year_plus_is_year_and_week(String trackNumber, int endYear, int endWeek) throws Exception{
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      String projectNumber = currentYear % 100 + trackNumber;

      Project project = this.app.getProject(projectNumber);
      project.setEndYear(endYear);
      project.setEndWeek(endWeek);
      assertEquals(project.getEndYear(), endYear);
      assertEquals(project.getEndWeek(), endWeek);
  }

  @Then("the start time of the project with project number current year plus {string} is not updated to year {int} and week {int}")
  public void the_start_time_of_the_project_with_project_number_current_year_plus_is_not_updated_to_year_and_week(String trackNumber, int startYear, int startWeek) throws Exception{
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      String projectNumber = currentYear % 100 + trackNumber;

      // Get project
      Project project = this.app.getProject(projectNumber);

      // Checks
      boolean notEqualTime = (project.getStartYear() != startYear) || (project.getStartWeek() != startWeek);
      assertTrue(notEqualTime);

  }

  @Then("the end time of the project with project number current year plus {string} is not updated to year {int} and week {int}")
  public void the_end_time_of_the_project_with_project_number_current_year_plus_is_not_updated_to_year_and_week(String trackNumber, int endYear, int endWeek) throws Exception {
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      String projectNumber = currentYear % 100 + trackNumber;

      // Get project
      Project project = this.app.getProject(projectNumber);

      // Checks
      boolean notEqualTime = (project.getEndYear() != endYear) || (project.getEndWeek() != endWeek);
      assertTrue(notEqualTime);
  }

  // ACTIVITY SCENARIOS  
  // SCENARIO 1
  @When("the current user sets the start time to year {int} and week {int} and end time to year {int} and week {int} for the activity with name {string} of project with project number current year plus {string}")
  public void the_current_user_sets_the_start_time_to_year_and_week_and_end_time_to_year_and_week_for_the_activity_with_name_of_project_with_project_number_current_year_plus(int startYear, int startWeek, int endYear, int endWeek, String activityName, String trackNumber) throws Exception{
      try {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String projectNumber = currentYear % 100 + trackNumber;
        this.app.setTimeHorizonOfActivity(startYear,startWeek,endYear,endWeek,activityName,projectNumber);
      } 
      catch (OperationNotAllowedException e){
          this.errorMessage.setErrorMessage(e.getMessage());
      }
  }
  
  @Then("the start time of the activity with name {string} of project with project number current year plus {string} is updated to year {int} and week {int}")
  public void the_start_time_of_the_activity_with_name_of_project_with_project_number_current_year_plus_is_updated_to_year_and_week(String activityName, String trackNumber, int startYear, int startWeek) throws Exception {
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      String projectNumber = currentYear % 100 + trackNumber;

      // Get activity 
      Project project = this.app.getProject(projectNumber);
      Activity activity = project.getActivity(activityName);
      
      // Test whether start year and week is set correctly
      assertEquals(activity.getStartYear(),startYear);
      assertEquals(activity.getStartWeek(),startWeek);
  }

  @Then("the end time of the activity with name {string} of project with project number current year plus {string} is updated to year {int} and week {int}")
  public void the_end_time_of_the_activity_with_name_of_project_with_project_number_current_year_plus_is_updated_to_year_and_week(String activityName, String trackNumber, int endYear, int endWeek) throws Exception {
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      String projectNumber = currentYear % 100 + trackNumber;

      // Get activity
      Project project = this.app.getProject(projectNumber);
      Activity activity = project.getActivity(activityName);

      // Test whether end year and week is set correctly
      assertEquals(activity.getEndYear(),endYear);
      assertEquals(activity.getEndWeek(),endWeek);
  }


  // SCENARIO 2
  @Given("the start time of the activity with name {string} of project with project number current year plus {string} is year {int} and week {int}")
  public void the_start_time_of_the_activity_with_name_of_project_with_project_number_current_year_plus_is_year_and_week(String activityName, String trackNumber, int startYear, int startWeek) throws Exception {
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      String projectNumber = currentYear % 100 + trackNumber;

      Project project = this.app.getProject(projectNumber);
      Activity activity = project.getActivity(activityName);
      activity.setStartYear(startYear);
      activity.setStartWeek(startWeek);
      assertEquals(activity.getStartYear(), startYear);
      assertEquals(activity.getStartWeek(), startWeek);
  }

  @Given("the end time of the activity with name {string} of project with project number current year plus {string} is year {int} and week {int}")
  public void the_end_time_of_the_activity_with_name_of_project_with_project_number_current_year_plus_is_year_and_week(String activityName, String trackNumber, int endYear, int endWeek) throws Exception {
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      String projectNumber = currentYear % 100 + trackNumber;

      Project project = this.app.getProject(projectNumber);
      Activity activity = project.getActivity(activityName);
      activity.setEndYear(endYear);
      activity.setEndWeek(endWeek);
      assertEquals(activity.getEndYear(), endYear);
      assertEquals(activity.getEndWeek(), endWeek);
  }

  @Then("the start time of the activity with name {string} of project with project number current year plus {string} is not updated to year {int} and week {int}")
  public void the_start_time_of_the_activity_with_name_of_project_with_project_number_current_year_plus_is_not_updated_to_year_and_week(String activityName, String trackNumber, int startYear, int startWeek) throws Exception {
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      String projectNumber = currentYear % 100 + trackNumber;

      // Get activity
      Project project = this.app.getProject(projectNumber);
      Activity activity = project.getActivity(activityName);  

      // Checks
      boolean notEqualTime = (activity.getStartYear() != startYear) || (activity.getStartWeek() != startWeek);
      assertTrue(notEqualTime);
  }

  @Then("the end time of the activity with name {string} of project with project number current year plus {string} is not updated to year {int} and week {int}")
  public void the_end_time_of_the_activity_with_name_of_project_with_project_number_current_year_plus_is_not_updated_to_year_and_week(String activityName, String trackNumber, int endYear, int endWeek) throws Exception {
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      String projectNumber = currentYear % 100 + trackNumber;

      // Get activity
      Project project = this.app.getProject(projectNumber);
      Activity activity = project.getActivity(activityName);  

      // Checks
      boolean notEqualTime = (activity.getEndYear() != endYear) || (activity.getEndWeek() != endWeek);
      assertTrue(notEqualTime);
  }


  // SCENARIO 5
  @Given("the time horizon of the project with project number current year plus {string} has not been set")
  public void the_time_horizon_of_the_project_with_project_number_current_year_plus_has_not_been_set(String trackNumber) throws Exception {
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      String projectNumber = currentYear % 100 + trackNumber;

      Project project = this.app.getProject(projectNumber);

      assertFalse(project.isTimeHorizonDefined());
  }

}