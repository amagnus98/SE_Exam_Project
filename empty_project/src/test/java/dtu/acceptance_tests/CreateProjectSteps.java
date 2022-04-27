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

public class CreateProjectSteps {

	private App app;
	private ErrorMessageHolder errorMessage;
	private MockDateHolder dateHolder;
    
	public CreateProjectSteps(App app, ErrorMessageHolder errorMessage, MockDateHolder dateHolder) {
		this.app = app;
		this.errorMessage = errorMessage;
		this.dateHolder = dateHolder;
	}

	// SCENARIO 1
	@Given("the current user is a developer with initials {string}")
	public void the_current_user_is_a_developer_with_initials(String initials) throws Exception{
    	this.app.logIn(initials);
	}

	@Given("the current year is {int}")
	public void the_current_year_is(int currentYear){
		assertEquals(this.app.getCurrentYear(), currentYear);
	}

	@Given("there are {int} projects in the system for the current year")
	public void there_are_projects_in_the_system_for_the_current_year(int numprojects) {
			
		// Four projects are added to the list of projects
		for (int i = 0; i < numprojects; i++){
			this.app.addProject();
		}
		
		// Checks that the number of projects in the given year is set correctly 
		assertEquals(this.app.getProjectCount(), numprojects);
	}

	@When("the current user creates a new project with name {string}")
	public void the_current_user_creates_a_new_project_with_name(String name) {
		this.app.addProject(name);
	}

	@Then("a project with project number current year plus {string} is added to the company's list of current projects")
	public void a_project_with_project_number_current_year_plus_is_added_to_the_company_s_list_of_current_projects(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("a project with project number {string} is added to the company's list of current projects")
	public void a_project_with_project_number_is_added_to_the_company_s_list_of_current_projects(String projectNumber) {
		assertTrue(this.app.projectExists(projectNumber));
	}

	@Then("the name of the project with project number {string} is set to {string}")
	public void the_name_of_the_project_with_project_number_is_set_to(String projectNumber, String name) throws Exception{
		assertEquals(this.app.getProject(projectNumber).getName(), name);
	}


	// SCENARIO 2	
	@When("the current user creates a new project without a name")
	public void the_current_user_creates_a_new_project_without_a_name() {
		this.app.addProject();
	}

	
}

