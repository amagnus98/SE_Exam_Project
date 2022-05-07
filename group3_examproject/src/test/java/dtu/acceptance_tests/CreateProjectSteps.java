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
    
	public CreateProjectSteps(App app, ErrorMessageHolder errorMessage) {
		this.app = app;
		this.errorMessage = errorMessage;
	}

	// SCENARIO 1
	@Given("the current user is a developer with initials {string}")
	public void the_current_user_is_a_developer_with_initials(String initials) throws Exception{
    	this.app.logIn(initials);
	}

	@Given("there are {int} projects in the system for the current year")
	public void there_are_projects_in_the_system_for_the_current_year(int numprojects) {
			
		// Four projects are added to the list of projects
		for (int i = 0; i < numprojects; i++){
			this.app.addProject();
		}
		
		// Checks that the number of projects in the given year is set correctly 
		assertEquals(this.app.getProjectCountForCurrentYear(), numprojects);
	}

	@When("the current user creates a new project with name {string}")
	public void the_current_user_creates_a_new_project_with_name(String name) {
		this.app.addProject(name);
	}

	@Then("a project with project number current year plus {string} is added to the company's list of current projects")
	public void a_project_with_project_number_current_year_plus_is_added_to_the_company_s_list_of_current_projects(String trackNumber) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String projectNumber = currentYear % 100 + trackNumber;
		assertTrue(this.app.projectExists(projectNumber));
	}

	@Then("the name of the project with project number current year plus {string} is set to {string}")
	public void the_name_of_the_project_with_project_number_current_year_plus_is_set_to(String trackNumber, String projectName) throws Exception {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String projectNumber = currentYear % 100 + trackNumber;
		assertEquals(this.app.getProject(projectNumber).getName(), projectName);
	}

	// SCENARIO 2	
	@When("the current user creates a new project without a name")
	public void the_current_user_creates_a_new_project_without_a_name() {
		this.app.addProject();
	}

	// SCENARIO 3
	@Given("the name of the project with project number current year plus {string} is {string}")
	public void the_name_of_the_project_with_project_number_current_year_plus_is(String trackNumber, String name) throws Exception{
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String projectNumber = currentYear % 100 + trackNumber;
		Project project = this.app.getProject(projectNumber);
		project.setName(name);
		assertTrue(project.getName().equals(name));

	}

	@When("the current user edits the name of the project with project number {string} to {string}")
	public void the_current_user_edits_the_name_of_the_project_with_project_number_to(String trackNumber, String name) throws Exception {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String projectNumber = currentYear % 100 + trackNumber;
		Project project = this.app.getProject(projectNumber);
		project.setName(name);
	}

	
}

