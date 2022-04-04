package dtu.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.model.domain.App;
import system.model.domain.Company;
import system.model.domain.Developer;
import system.model.domain.Project;
import system.model.domain.OperationNotAllowedException;

public class ProjectSteps {

	private Developer developer;
	private App app;
	private Company company;
	private ErrorMessageHolder errorMessage;
    
	public ProjectSteps(App app,ErrorMessageHolder errorMessage) {
        this.app = app;
        this.errorMessage = errorMessage;
	}

	@Given("the current user is a developer with initials {string}")
	public void the_current_user_is_a_developer_with_initials(String initials) throws Exception{
		this.app.getCompany().addDeveloper(new Developer(initials));;
		this.app.logIn(initials);
		Developer currentUser = this.app.getCurrentUser();
		assertEquals(initials, currentUser.getInitials());
	}

	@When("the current user creates a new project with name {string}")
	public void the_current_user_creates_a_new_project_with_name(String name) {
		Project newProject = new Project(name);
		this.app.getCompany().addProject(newProject);	
	}

	@Then("a project with a project number {string} is added to the company's list of current projects")
	public void a_project_with_a_project_number_is_added_to_the_company_s_list_of_current_projects(String string) {
			
	}

	@Then("the name of the project with project number {string} is {string}")
	public void the_name_of_the_project_with_project_number_is(String string, String string2) {
			// Write code here that turns the phrase above into concrete actions
			throw new io.cucumber.java.PendingException();
	}


	}

