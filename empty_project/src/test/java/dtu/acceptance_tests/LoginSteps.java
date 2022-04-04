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

public class LoginSteps {

    private App app;
    private Developer developer;
    private Company company;
    private ErrorMessageHolder errorMessage;
    
	public LoginSteps(ErrorMessageHolder errorMessage) {
	 	//this.developer = developer;
        //this.company = company;
        //this.app = app;
        this.errorMessage = errorMessage;
	}

    @Given("a developer with initials {string} exists in the company")
    public void a_developer_with_initials_exists_in_the_company(String initials) {
        // create developer with initials
        this.developer = new Developer(initials);
        ArrayList<Developer> developers = new ArrayList<Developer>();

        // add developer to company
        this.company = new Company(developers);
        this.company.addDeveloper(this.developer);

        // check if developer is in the company
        assertTrue(this.company.developerExists(initials));
    }
    
    @When("the user logs into the system with initials {string}")
    public void the_user_logs_into_the_system_with_initials(String initials) throws Exception {
        try {
            // Create instance of app system
            this.app = new App();
            this.app.setCompany(this.company);

            // Developer logs into the app
            this.app.logInAsUser(initials);
            
        } catch (OperationNotAllowedException e){
            this.errorMessage.setErrorMessage(e.getMessage());
        }
    }
    	
    @Then("the initials of the current user of the system is set to {string}")
    public void the_initials_of_the_current_user_of_the_system_is_set_to(String initials) {

        // Check if the user is logged in to the system
        assertEquals(initials, this.app.getCurrentUser().getInitials());
        
    }

    @Given("a developer with initials {string} does not exists in the company")
    public void a_developer_with_initials_does_not_exists_in_the_company(String initials) {
        // Write code here that turns the phrase above into concrete actions
        this.developer = new Developer(initials);
        ArrayList<Developer> developers = new ArrayList<Developer>();

        // add developer to company
        this.company = new Company(developers);
        //this.company.addDeveloper(this.developer);

        // check if developer is not in the company
        assertFalse(this.company.developerExists(initials));
    }

    @Then("the system provides an error message {string}")
    public void the_system_provides_an_error_message(String errorMessage) throws Exception {
            //this.errorMessage.setErrorMessage(errorMessage);
            System.out.println(errorMessage);
            System.out.println(this.errorMessage.getErrorMessage());
            assertEquals(errorMessage, this.errorMessage.getErrorMessage());
        }
    

    @Then("the initials of the current user of the system is not set to {string}")
    public void the_initials_of_the_current_user_of_the_system_is_not_set_to(String initials) {
        assertNotEquals(initials, this.app.getCurrentUser().getInitials());
    }

}