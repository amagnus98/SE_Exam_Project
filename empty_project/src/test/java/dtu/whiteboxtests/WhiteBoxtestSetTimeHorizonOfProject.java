package dtu.whiteboxtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.rmi.server.Operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import system.model.domain.App;
import system.model.domain.OperationNotAllowedException;


public class WhiteBoxtestSetTimeHorizonOfProject {
  
    App app = new App();

  
    @BeforeEach
    public void setUp() throws OperationNotAllowedException, Exception {
        System.out.println("Executing setup");
        app.logIn("bond");
    }


    @Test
    public void testSetTimeHorizonOfProjectA(){
        // Add a new project
        app.addProject();
        
        // Define input
        String projectNumber = app.getProjectNumber();
        int startYear = app.getCurrentYear();
        int startWeek = 10;
        int endYear = app.getCurrentYear()+1;
        int endWeek = 10;

        // Check that the correct message is given when current user is not project leader
        try {app.setTimeHorizonOfProject(startYear, startWeek, endYear, endWeek, projectNumber);}
        catch (OperationNotAllowedException e){
            assertEquals(e.getMessage(), "The start and end time of the project can't be edited, because the user is not the project leader");
        }
    }
    
    
    @Test
    public void testSetTimeHorizonOfProjectB() throws OperationNotAllowedException {
        // add a new project and get project number
        app.addProject();

        // Define input
        String projectNumber = app.getProjectNumber();
        int startYear = app.getCurrentYear();
        int startWeek = 10;
        int endYear = app.getCurrentYear()-1;
        int endWeek = 10;

        // assign current user as project leader
        app.assignProjectLeader(projectNumber, "bond");

        // Check that the correct message is given when end time is before start time
        try {app.setTimeHorizonOfProject(startYear, startWeek, endYear, endWeek, projectNumber);}
        catch (OperationNotAllowedException e){
            assertEquals(e.getMessage(), "The end time can't occur before the start time");
        }
    }


    @Test
    public void testSetTimeHorizonOfProjectC() throws OperationNotAllowedException{
        // add a new project and get project number
        app.addProject();

        // Define input
        String projectNumber = app.getProjectNumber();
        int startYear1 = app.getCurrentYear()-1;
        int startWeek = 10;
        int endYear = app.getCurrentYear();
        int endWeek = 10;
        int startYear2 = app.getCurrentYear();

        // assign current user as project leader
        app.assignProjectLeader(projectNumber, "bond");

        // set initial project time horizon
        app.setTimeHorizonOfProject(startYear1, startWeek, endYear, endWeek, projectNumber);

        // add activity to project
        app.addActivityToProject("ActivityName", projectNumber);

        // set time horizon of activity 
        app.setTimeHorizonOfActivity(startYear1, startWeek, endYear, endWeek, "ActivityName", projectNumber);

        // Set new time horizon of project and check correct message is given when time horizon does not span activity timeline
        try {app.setTimeHorizonOfProject(startYear2, startWeek, endYear, endWeek, projectNumber);}
        catch (OperationNotAllowedException e){
            assertEquals(e.getMessage(), "The new time horizon of the project conflicts with the time horizon of the activities in the project");
        }
    }

    @Test
    public void testSetTimeHorizonOfProjectD() throws OperationNotAllowedException{
        // add a new project and get project number
        app.addProject();

        // Define input
        String projectNumber = app.getProjectNumber();
        int startYear1 = app.getCurrentYear()-1;
        int startWeek1 = 10;
        int endYear1 = app.getCurrentYear()+1;
        int endWeek1 = 15;
        int startYear2 = app.getCurrentYear()-1;
        int startWeek2 = 11;
        int endYear2 = app.getCurrentYear()+2;
        int endWeek2 = 13;


        // assign current user as project leader
        app.assignProjectLeader(projectNumber, "bond");

        // set initial project time horizon
        app.setTimeHorizonOfProject(startYear1, startWeek1, endYear1, endWeek1, projectNumber);

        // add activity to project
        app.addActivityToProject("ActivityName", projectNumber);

        // set time horizon of activity 
        app.setTimeHorizonOfActivity(startYear1, startWeek2, endYear1, endWeek2, "ActivityName", projectNumber);

        // set new time horizon of project
        app.setTimeHorizonOfProject(startYear2, startWeek1, endYear2, endWeek1, projectNumber);

        // assert that the start and end time of the project corresponds to the given start and end time
        assertEquals(app.getProject(projectNumber).getStartYear(),startYear2);
        assertEquals(app.getProject(projectNumber).getEndYear(),endYear2);
        assertEquals(app.getProject(projectNumber).getStartWeek(),startWeek1);
        assertEquals(app.getProject(projectNumber).getEndWeek(),endWeek1);
    }

  }