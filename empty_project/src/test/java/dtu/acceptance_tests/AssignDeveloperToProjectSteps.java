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

public class AssignDeveloperToProjectSteps {
    private App app;
    private ErrorMessageHolder errorMessage;
    
	public AssignDeveloperToProjectSteps(App app, ErrorMessageHolder errorMessage) {
        this.app = app;
        this.errorMessage = errorMessage;
	}

    // SCENARIO 1
    @Given("the developer with initials {string} is not currently assigned to the project with project number {string}")
    public void the_developer_with_initials_is_not_currently_assigned_to_the_project_with_project_number(String initials, String projectNumber) throws Exception {
        Project project = this.app.getProject(projectNumber);

        assertFalse(project.isDeveloperAssigned(initials));

    }

    @When("the current user assigns the developer with initials {string} to the project with project number {string}")
    public void the_current_user_assigns_the_developer_with_initials_to_the_project_with_project_number(String initials, String projectNumber) throws Exception {  
      try {
        this.app.addDeveloperToProject(initials, projectNumber);
      }
      catch (OperationNotAllowedException e){
        this.errorMessage.setErrorMessage(e.getMessage());
      }
  } 

    @Then("the developer with initials {string} is assigned to the project with project number {string}")
    public void the_developer_with_initials_is_assigned_to_the_project_with_project_number(String initials, String projectNumber) throws Exception {
        Project project = this.app.getProject(projectNumber);

        assertTrue(project.isDeveloperAssigned(initials));
    }

    // SCENARIO 2
    @Given("the developer with initials {string} is already assigned to the project with project number {string}")
    public void the_developer_with_initials_is_already_assigned_to_the_project_with_project_number(String initials, String projectNumber) throws Exception {
        Developer developer = this.app.getDeveloper(initials);
        Project project = this.app.getProject(projectNumber);
        project.addDeveloper(developer);    
        assertTrue(project.isDeveloperAssigned(initials));
    }

    // SCENARIO 3
    @Then("the developer with initials {string} is not assigned to the project with project number {string}")
    public void the_developer_with_initials_is_not_assigned_to_the_project_with_project_number(String initials, String projectNumber) throws Exception {
        Project project = this.app.getProject(projectNumber);

        assertFalse(project.isDeveloperAssigned(initials));
    }

}
