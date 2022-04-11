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

public class AssignProjectLeaderSteps {

  private App app;
	private ErrorMessageHolder errorMessage;
  private Project project;

  public AssignProjectLeaderSteps(App app, ErrorMessageHolder errorMessage, MockDateHolder dateHolder) {
		this.app = app;
		this.errorMessage = errorMessage;
	}

  @Given("a project with project number {string} exists in the system")
  public void a_project_with_project_number_exists_in_the_system(String projectNumber) {
    this.app.addProject(projectNumber);
	  assertTrue(this.app.projectExists(projectNumber));
  }

  @When("the current user assigns a developer with initials {string} as project leader of project with project number {string}")
  public void the_current_user_assigns_a_developer_with_initials_as_project_leader_of_project_with_project_number(String initials, String projectNumber)throws Exception{
    try {
      // Developer logs into the app
      this.app.assignProjectLeader(projectNumber,initials);
    }
    catch (OperationNotAllowedException e){
        this.errorMessage.setErrorMessage(e.getMessage());
    }
  }

  @Then("the project leader of project with project number {string} is set to the developer with initials {string}")
  public void the_project_leader_of_project_with_project_number_is_set_to_the_developer_with_initials(String projectNumber, String initials) throws Exception{
      this.project = this.app.getProject(projectNumber);
      assertEquals(this.project.getProjectLeader(), initials);
  }


  /// SCENARIO 2
  @Then("the developer with initials {string} is not assigned as project leader of project with project number {string}")
  public void the_developer_with_initials_is_not_assigned_as_project_leader_of_project_with_project_number(String initials, String projectNumber) throws Exception{
    this.project = this.app.getProject(projectNumber);  
    assertNotEquals(this.project.getProjectLeader(), initials);
  }
}