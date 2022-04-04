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
import system.model.domain.OperationNotAllowedException;

public class ProjectSteps {

	private App app;
	private Developer developer;
	private Company company;
	private ErrorMessageHolder errorMessage;
    
	public ProjectSteps(ErrorMessageHolder errorMessage) {
	 	//this.developer = developer;
        //this.company = company;
        //this.app = app;
        this.errorMessage = errorMessage;
	}



	
	@Given("the user is a developer with initials {string}")
	public void the_user_is_a_developer_with_initials(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
	}

	@When("the user creates a new project with name {string}")
	public void the_user_creates_a_new_project_with_name(String string) {
			// Write code here that turns the phrase above into concrete actions
			throw new io.cucumber.java.PendingException();
	}

	@Then("a project with a project number {string} is added to the company's list of current projects")
	public void a_project_with_a_project_number_is_added_to_the_company_s_list_of_current_projects(String string) {
			// Write code here that turns the phrase above into concrete actions
			throw new io.cucumber.java.PendingException();
	}

	@Then("the name of the project with project number {string} is {string}")
	public void the_name_of_the_project_with_project_number_is(String string, String string2) {
			// Write code here that turns the phrase above into concrete actions
			throw new io.cucumber.java.PendingException();
	}


	}

