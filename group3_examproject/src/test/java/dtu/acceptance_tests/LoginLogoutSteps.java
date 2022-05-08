// Responsible - Marcus Roberto Nielsen (s204126)

package dtu.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.model.domain.App;
import system.model.domain.OperationNotAllowedException;

public class LoginLogoutSteps {

    private App app;
    private ErrorMessageHolder errorMessage;
    
	public LoginLogoutSteps(App app, ErrorMessageHolder errorMessage) {
        this.app = app;
        this.errorMessage = errorMessage;
	}

    @Given("a developer with initials {string} exists in the company")
    public void a_developer_with_initials_exists_in_the_company(String initials) {
        // check if developer is in the company
        assertTrue(this.app.developerExists(initials));
    }
    
    @When("the user logs into the system with initials {string}")
    public void the_user_logs_into_the_system_with_initials(String initials) throws Exception{
        try {
            // Developer logs into the app
            this.app.logIn(initials);
        }
        catch (OperationNotAllowedException e){
            this.errorMessage.setErrorMessage(e.getMessage());
        }
    }
    
    @Then("the current user of the system is set to a developer with initials {string}")
    public void the_current_user_of_the_system_is_set_to_a_developer_with_initials(String initials) {
        // Check if the user is logged in to the system
        assertEquals(initials, this.app.getCurrentUser().getInitials());
    }

    @Given("a developer with initials {string} does not exist in the company")
    public void a_developer_with_initials_does_not_exist_in_the_company(String initials) {
        // check if developer is not in the company
        assertFalse(this.app.developerExists(initials));
    }

    @Then("the system provides an error message {string}")
    public void the_system_provides_an_error_message(String errorMessage) throws Exception {
        // Check that the correct errormessage is provided
        assertEquals(errorMessage, this.errorMessage.getErrorMessage());
    }

    @Then("the system has no current user")
    public void the_system_has_no_current_user() {
        assertFalse(this.app.hasCurrentUser());        
    }


    @Given("that a user is logged in")
    public void that_a_user_is_logged_in()  throws Exception {
        // Developer logs into the app
        this.app.logIn("amag");

        assertTrue(this.app.hasCurrentUser());
    }

    @When("the user logs out")
    public void the_user_logs_out() {
        this.app.logOut();
    }

    @Then("the user is not logged in")
    public void the_user_is_not_logged_in() {
        assertFalse(this.app.hasCurrentUser());
    }
}