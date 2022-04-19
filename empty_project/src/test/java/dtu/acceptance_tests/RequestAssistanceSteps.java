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

public class RequestAssistanceSteps {

    private App app;
	private ErrorMessageHolder errorMessage;
    
	public RequestAssistanceSteps(App app, ErrorMessageHolder errorMessage) {
		this.app = app;
		this.errorMessage = errorMessage;
	}

    // SCENARIO 1
    @Given("the developer with initials {string} is not currently working on the activity with name {string} of project with project number {string}")
    public void the_developer_with_initials_is_not_currently_working_on_the_activity_with_name_of_project_with_project_number(String initials, String activityName, String projectNumber) throws Exception {
        Developer developer = this.app.getDeveloper(initials);
        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);
        
        assertFalse(activity.isDeveloperCurrentlyWorking(developer));
    }
    
    @Given("the developer with initials {string} is assigned by the project leader to the activity with name {string} of project with project number {string}")
    public void the_developer_with_initials_is_assigned_by_the_project_leader_to_the_activity_with_name_of_project_with_project_number(String initials, String activityName, String projectNumber) throws Exception {
        Developer developer = this.app.getDeveloper(initials);
        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);

        activity.addAssignedDeveloper(developer);

        assertTrue(activity.isDeveloperAssignedByProjectLeader(developer)); 
    }

    @When("the current user requests assistance from the developer with initials {string} for the activity with name {string} of project with project number {string}")
    public void the_current_user_assistance_from_the_developer_with_initials_for_the_activity_with_name_of_project_with_project_number(String initialsReceiver, String activityName, String projectNumber) throws Exception {
        try {
        // developer is added to activity
         this.app.requestAssistanceForActivity(initialsReceiver,activityName,projectNumber);
        }
        catch (OperationNotAllowedException e){
            this.errorMessage.setErrorMessage(e.getMessage());
        }
  } 

    @Then("the developer with initials {string} is allowed to register hours for the activity with name {string} of project with project number {string}")
    public void the_developer_with_initials_is_allowed_to_register_hours_for_the_activity_with_name_of_project_with_project_number(String initials, String activityName, String projectNumber) throws Exception {
        Developer developer = this.app.getDeveloper(initials);
        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);
        
        assertTrue(activity.isDeveloperCurrentlyWorking(developer));
    }

    // SCENARIO 2
    @Given("the developer with initials {string} is currently working on the activity with name {string} of project with project number {string}")
    public void the_developer_with_initials_is_currently_working_on_the_activity_with_name_of_project_with_project_number(String initials, String activityName, String projectNumber) throws Exception {
        Developer developer = this.app.getDeveloper(initials);
        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);

        activity.addRequestedDeveloper(developer);
    }

    // SCENARIO 3
    @Given("the developer with initials {string} is not assigned by the project leader to the activity with name {string} of project with project number {string}")
    public void the_developer_with_initials_is_not_assigned_by_the_project_leader_to_the_activity_with_name_of_project_with_project_number(String initials, String activityName, String projectNumber)throws Exception {
        Developer developer = this.app.getDeveloper(initials);
        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);

        assertFalse(activity.isDeveloperAssignedByProjectLeader(developer));
    }

    @Then("the developer with initials {string} is not allowed to register hours for the activity with name {string} of project with project number {string}")
    public void the_developer_with_initials_is_not_allowed_to_register_hours_for_the_activity_with_name_of_project_with_project_number(String initials, String activityName, String projectNumber) throws Exception {
        Developer developer = new Developer(initials);
        Project project = this.app.getProject(projectNumber);
        Activity activity = project.getActivity(activityName);

        assertFalse(activity.canRegisterHours(developer));
        
    }





}
