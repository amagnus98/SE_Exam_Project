package dtu.whiteboxtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import system.model.domain.App;
import system.model.domain.Developer;
import system.model.domain.DeveloperCalendar;
import system.model.domain.OperationNotAllowedException;

public class WhiteBoxtestRequestAssistance {

    App app = new App();

  
    @BeforeEach
    public void setUp() throws OperationNotAllowedException, Exception {
        System.out.println("Executing setup");
        app.logIn("kape");
    }

    @Test
    public void requestAssistanceA() throws OperationNotAllowedException{
        // add a project and get projectNumber
        app.addProject();
        String projectNumber = app.getProjectNumber();

        // add an activity to the project
        app.assignProjectLeader(projectNumber, "kape");
        app.addActivityToProject("activityName", projectNumber);

        // define a receiver and sender
        Developer receiver = this.app.getDeveloper("amag");
        Developer sender = this.app.getDeveloper("mani");

        // check that sender is not assigned by project leader to the activity
        assertFalse(app.getProject(projectNumber).getActivity("activityName").isDeveloperAssignedByProjectLeader(sender));

        try {app.getProject(projectNumber).getActivity("activityName").requestAssistance(receiver, sender);}
        catch (OperationNotAllowedException e){
          assertEquals("The current user is not assigned to the activity by the project leader and cannot request assistance", e.getMessage());
      }

    }

    @Test
    public void requestAssistanceB() throws OperationNotAllowedException{
        // add a project and get projectNumber
        app.addProject();
        String projectNumber = app.getProjectNumber();

        // define a receiver and sender
        Developer receiver = this.app.getDeveloper("amag");
        Developer sender = this.app.getDeveloper("anbi");

        // add an activity to the project and assign the receiver and sender
        app.assignProjectLeader(projectNumber, "kape");
        app.addActivityToProject("activityName", projectNumber);
        app.addDeveloperToActivity("amag", "activityName", projectNumber);
        app.addDeveloperToActivity("anbi", "activityName", projectNumber);

        // check that the sender is assigned by the project leader and the receiver is added to the activity
        assertTrue(app.getProject(projectNumber).getActivity("activityName").isDeveloperAssignedByProjectLeader(sender));
        assertTrue(app.getProject(projectNumber).getActivity("activityName").getDevelopers().contains(receiver));

        // Check that the correct message is given when receiver is already added to the activity
        try {app.getProject(projectNumber).getActivity("activityName").requestAssistance(receiver, sender);}
        catch (OperationNotAllowedException e){
          assertEquals("The developer is already working on the given activity", e.getMessage());
      }

    }

    @Test
    public void requestAssistanceC() throws OperationNotAllowedException{
        // add a project and get projectNumber
        app.addProject();
        String projectNumber = app.getProjectNumber();

        // define a receiver and sender
        Developer receiver = this.app.getDeveloper("amag");
        Developer sender = this.app.getDeveloper("anbi");

        // add an activity to the project and assign the sender
        app.assignProjectLeader(projectNumber, "kape");
        app.addActivityToProject("activityName", projectNumber);
        app.addDeveloperToActivity("anbi", "activityName", projectNumber);

        // call the method
        app.getProject(projectNumber).getActivity("activityName").requestAssistance(receiver, sender);

        // assert that the receiver is added to the activity and the activity is added to the receivers calender
        assertTrue(app.getProject(projectNumber).getActivity("activityName").getDevelopers().contains(receiver));
        assertTrue(receiver.getAssignedActivities().contains(app.getProject(projectNumber).getActivity("activityName")));

    }

}
