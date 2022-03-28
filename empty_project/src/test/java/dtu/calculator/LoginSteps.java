package dtu.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.model.domain.App;
import system.model.domain.Company;
import system.model.domain.Developer;

public class LoginSteps {

    private App app;
    private Developer developer;
    private Company company;
    
	// public LoginSteps(Developer developer, Company company, App app) {
	// 	this.developer = developer;
    //     this.company = company;
    //     this.app = app;
	// }

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

        // Create instance of app system
        this.app = new App();

        // Developer logs into the app
        this.app.logInAsUser(initials);

    }
    
    @Then("the initials of the current user of the system is set to {initials}")
    public void the_initials_of_the_current_user_of_the_system_is_set_to(String initials) {

        // Check if the user is logged in to the system
        assertEquals(initials, this.app.getCurrentUser().getInitials());
        
    }

}