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

public class AssignDeveloperToActivitySteps {
  private App app;
  private ErrorMessageHolder errorMessage;
    
	public AssignDeveloperToActivitySteps(App app, ErrorMessageHolder errorMessage) {
        this.app = app;
        this.errorMessage = errorMessage;
	}

  
  @Given("the developer with initials {string} is not currently assigned to the activity with name {string} of project with project number {string}")
  public void the_developer_with_initials_is_not_currently_assigned_to_the_activity_with_name_of_project_with_project_number(String initials, String activityName, String projectNumber) throws Exception{
     Developer developer = this.app.getDeveloper(initials);
     Project project = this.app.getProject(projectNumber);
     Activity activity = project.getActivity(activityName);

     assertFalse(activity.isDeveloperAssignedByProjectLeader(developer));
  }

  @When("the current user assigns the developer with initials {string} to activity with name {string} of project with project number {string}")
  public void the_current_user_assigns_the_developer_with_initials_to_activity_with_name_of_project_with_project_number(String initials, String activityName, String projectNumber) throws Exception {
      try {
        // developer is added to activity
        this.app.addDeveloperToActivity(initials, activityName, projectNumber);
      }
      catch (OperationNotAllowedException e){
            this.errorMessage.setErrorMessage(e.getMessage());
      }
  }   

  @Then("the developer with initials {string} is assigned to the activity with name {string} of project with project number {string}")
  public void the_developer_with_initials_is_assigned_to_the_activity_with_name_of_project_with_project_number(String initials, String activityName, String projectNumber) throws Exception{
      Developer developer = new Developer(initials);
      Project project = this.app.getProject(projectNumber);
      Activity activity = project.getActivity(activityName);

      assertTrue(activity.isDeveloperAssignedByProjectLeader(developer));   
  }


  @Then("the developer with initials {string} is not assigned to the activity with name {string} of project with project number {string}")
  public void the_developer_with_initials_is_not_assigned_to_the_activity_with_name_of_project_with_project_number(String initials, String activityName, String projectNumber) throws Exception{
    Developer developer = new Developer(initials);
    Project project = this.app.getProject(projectNumber);
    Activity activity = project.getActivity(activityName);

    assertFalse(activity.isDeveloperAssignedByProjectLeader(developer));  
  }

  // SCENARIO 3
  @Given("the developer with initials {string} is already assigned to the activity with name {string} of project with project number {string}")
  public void the_developer_with_initials_is_already_assigned_to_the_activity_with_name_of_project_with_project_number(String initials, String activityName, String projectNumber) throws Exception{
      Developer developer = this.app.getDeveloper(initials);
      Project project = this.app.getProject(projectNumber);
      Activity activity = project.getActivity(activityName);

      activity.addDeveloper(developer);
      
      assertTrue(activity.isDeveloperAssignedByProjectLeader(developer));
  }
}