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

  // SCENARIO 1
  @Given("a project with project number current year plus {string} exists in the system")
  public void a_project_with_project_number_current_year_plus_exists_in_the_system(String trackNumber) {
    // add a project to the system
    this.app.addProject();
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String projectNumber = currentYear % 100 + trackNumber;
    assertTrue(this.app.projectExists(projectNumber));
  }

  @When("the current user assigns a developer with initials {string} as project leader of the project with project number current year plus {string}")
  public void the_current_user_assigns_a_developer_with_initials_as_project_leader_of_the_project_with_project_number_current_year_plus(String initials, String trackNumber) {
      try {
        // Assign project leader
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String projectNumber = currentYear % 100 + trackNumber;
        this.app.assignProjectLeader(projectNumber,initials);
      }
      catch (OperationNotAllowedException e){
          this.errorMessage.setErrorMessage(e.getMessage());
      }
  }

  @Then("the project leader of the project with project number current year plus {string} is set to the developer with initials {string}")
  public void the_project_leader_of_the_project_with_project_number_current_year_plus_is_set_to_the_developer_with_initials(String trackNumber, String initials) throws Exception {
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      String projectNumber = currentYear % 100 + trackNumber;
      this.project = this.app.getProject(projectNumber);
      assertEquals(this.project.getProjectLeader().getInitials(), initials);
  }

  /// SCENARIO 2
  @Then("the developer with initials {string} is not assigned as project leader of the project with project number current year plus {string}")
  public void the_developer_with_initials_is_not_assigned_as_project_leader_of_the_project_with_project_number_current_year_plus(String initials, String trackNumber) throws Exception {
      int currentYear = Calendar.getInstance().get(Calendar.YEAR);
      String projectNumber = currentYear % 100 + trackNumber;
      this.project = this.app.getProject(projectNumber); 
      
      // check whether no project leader was set or that the current project leader does not have the given initials
      assertTrue((!this.project.hasProjectLeader()) || (!this.project.getProjectLeader().getInitials().equals(initials)));
  }

}