package dtu.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.model.domain.App;
import system.model.domain.OperationNotAllowedException;
import system.model.domain.*;

public class CreateProjectActivitySteps {
  
  private App app;
  private ErrorMessageHolder errorMessage;
    
	public CreateProjectActivitySteps(App app, ErrorMessageHolder errorMessage) {
        this.app = app;
        this.errorMessage = errorMessage;
	}



  
  // SCENARIO 1
  @Given("the developer with initials {string} is the project leader of the project with project number {string}")
  public void the_developer_with_initials_is_the_project_leader_of_the_project_with_project_number(String initials, String projectNumber) throws Exception{
      // Assign project leader
      this.app.assignProjectLeader(projectNumber,initials);
      assertEquals(this.app.getProject(projectNumber).getProjectLeader(),initials);
  }

  @Given("the project with project number {string} does not contain an activity with name {string}")
  public void the_project_with_project_number_does_not_contain_an_activity_with_name(String projectNumber, String activityName) throws Exception {
      Project project = this.app.getProject(projectNumber);
      assertFalse(project.containsActivity(activityName));
  }

  @When("the current user creates an activity with name {string} for the project with project number {string}")
  public void the_current_user_creates_an_activity_with_name_for_the_project_with_project_number(String activityName, String projectNumber) throws Exception{
      try {
        // try to add activity
        this.app.addActivity(activityName, projectNumber);
      }
      catch (OperationNotAllowedException e){
        this.errorMessage.setErrorMessage(e.getMessage());
      }  
  }

  @Then("the activity with name {string} is added to the list of activities for the project with project number {string}")
  public void the_activity_with_name_is_added_to_the_list_of_activities_for_the_project_with_project_number(String activityName, String projectNumber) throws Exception{
      Project project = this.app.getProject(projectNumber);
      assertTrue(project.containsActivity(activityName));
  }




  // SCENARIO 2
  @Given("the project with project number {string} contains an activity with name {string}")
  public void the_project_with_project_number_contains_an_activity_with_name(String projectNumber, String activityName) throws Exception{
    Project project = this.app.getProject(projectNumber);
    project.addActivity(new Activity(activityName));
    assertTrue(project.containsActivity(activityName));
  }

  @Then("the list of activities for the project with project number {string} does not contain the activity with name {string} twice")
  public void the_list_of_activities_for_the_project_with_project_number_does_not_contain_the_activity_with_name_twice(String projectNumber, String activityName) throws Exception{
    Project project = this.app.getProject(projectNumber);
    assertEquals(1, project.getActivityOccurences(activityName));
  }




  
  // SCENARIO 3
  @Given("the developer with initials {string} is not the project leader of the project with project number {string}")
  public void the_developer_with_initials_is_not_the_project_leader_of_the_project_with_project_number(String initials, String projectNumber) throws Exception {
      
    // Create new project (will have project number 22001)
    this.app.addProject();

    // assign current project leader to a different developer than "bond"
    this.app.assignProjectLeader(projectNumber,"kape");
    assertNotEquals(this.app.getProject(projectNumber).getProjectLeader(), initials);
  }

  @Then("the activity with name {string} is not added to the list of activities for the project with project number {string}")
  public void the_activity_with_name_is_not_added_to_the_list_of_activities_for_the_project_with_project_number(String activityName, String projectNumber) throws Exception{
    
    // Retrieve project
    Project project = this.app.getProject(projectNumber);

    // Check that activity is not contained within the project
    assertFalse(project.containsActivity(activityName));
  }
}