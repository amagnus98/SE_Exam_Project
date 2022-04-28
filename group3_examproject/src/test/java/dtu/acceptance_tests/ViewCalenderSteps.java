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

import java.util.*;

public class ViewCalenderSteps {
    
    private App app;
    private ErrorMessageHolder errorMessage;
    
	public ViewCalenderSteps(App app, ErrorMessageHolder errorMessage) {
        this.app = app;
        this.errorMessage = errorMessage;
	}

    // SCENARIO 1

    @When("the current user views their calender for day {int} of week {int} of year {int}")
    public void the_current_user_views_their_calender_for_day_of_week_of_year(int day, int week, int year) throws Exception {
        try {
            this.app.viewActivitiesWithRegisteredHours(day,week,year);
        } 
        catch (OperationNotAllowedException e){
            this.errorMessage.setErrorMessage(e.getMessage());
        }   
    }

    @Then("the current user can see {double} hours registered for the activity with name {string} of project with project number current year plus {string}")
    public void the_current_user_can_see_hours_registered_for_the_activity_with_name_of_project_with_project_number_current_year_plus(double hours, String activityName, String trackNumber) throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String projectNumber = currentYear % 100 + trackNumber;

        assertTrue(this.app.getCurrentUser().calendarOutputContainsActivity(hours,activityName,projectNumber));
    }

    // SCENARIO 2
    @Given("the user with initials {string} has registered no hours for day {int} of week {int} of year {int}")
    public void the_user_with_initials_has_registered_no_hours_for_day_of_week_of_year(String initials, int day, int week, int year) throws Exception{
        Developer developer = this.app.getDeveloper(initials);
        assertFalse(developer.getDeveloperCalendar().hasRegisteredHoursForDay(day,week,year));
    }


}
