package dtu.whiteboxtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.rmi.server.Operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import system.model.domain.App;
import system.model.domain.OperationNotAllowedException;


public class WhiteBoxtestRegisterHoursToActivity{
  
    App app = new App();

  
    @BeforeEach
    public void setUp() throws OperationNotAllowedException, Exception {
        System.out.println("Executing setup");
        app.logIn("bond");
    }


    @Test
    public void testSetTimeHorizonOfProjectA() throws OperationNotAllowedException{
      // Add a new project
      app.addProject();

      // assign current user as project leader
      String projectNumber = app.getProjectNumber();
      app.assignProjectLeader(projectNumber, "bond");
      
      // Define input
      double hours = -2.0;
      int day = 2;
      int week = 2;
      int year = app.getCurrentYear();
      String activityName = "Activity Name";
      int startYear = app.getCurrentYear();
      int endYear = app.getCurrentYear()+1;
      int startWeek = 11;
      int endWeek = 13;
      
      // Add activity to project and set time horizon for both project and activity
      app.addActivityToProject("Activity Name", projectNumber);
      app.addDeveloperToActivity("bond", "Activity Name", projectNumber);
      app.setTimeHorizonOfProject(startYear, startWeek, endYear, endWeek, projectNumber);
      app.setTimeHorizonOfActivity(startYear, startWeek, endYear, endWeek, "Activity Name", projectNumber);


      // Check that the correct message is given when negative hours are registerred
      try {app.registerHoursToActivity(hours, day, week, year, projectNumber, activityName);}
      catch (OperationNotAllowedException e){
          assertEquals("The user cannot register negative or zero hours", e.getMessage());
      }
    }
    
    @Test
    public void testSetTimeHorizonOfProjectB() throws OperationNotAllowedException{
      // Add a new project
      app.addProject();

      // assign current user as project leader
      String projectNumber = app.getProjectNumber();
      app.assignProjectLeader(projectNumber, "bond");
      
      // Define input
      double hours = 25.0;
      int day = 2;
      int week = 2;
      int year = app.getCurrentYear();
      String activityName = "Activity Name";
      int startYear = app.getCurrentYear();
      int endYear = app.getCurrentYear()+1;
      int startWeek = 11;
      int endWeek = 13;
      
      // Add activity to project and set time horizon for both project and activity
      app.addActivityToProject("Activity Name", projectNumber);
      app.addDeveloperToActivity("bond", "Activity Name", projectNumber);
      app.setTimeHorizonOfProject(startYear, startWeek, endYear, endWeek, projectNumber);
      app.setTimeHorizonOfActivity(startYear, startWeek, endYear, endWeek, "Activity Name", projectNumber);


      // Check that the correct message is given when negative hours are registerred
      try {app.registerHoursToActivity(hours, day, week, year, projectNumber, activityName);}
      catch (OperationNotAllowedException e){
          assertEquals("The user cannot register more than 24 hours for an activity per day", e.getMessage());
      }
    }


    @Test
    public void testSetTimeHorizonOfProjectC() throws OperationNotAllowedException{
      // Add a new project
      app.addProject();

      // assign current user as project leader
      String projectNumber = app.getProjectNumber();
      app.assignProjectLeader(projectNumber, "bond");
      
      // Define input
      double hours = 7.5;
      int day = 2;
      int week = 15;
      int year = app.getCurrentYear()+5;
      String activityName = "Activity Name";
      int startYear = app.getCurrentYear();
      int endYear = app.getCurrentYear()+1;
      int startWeek = 11;
      int endWeek = 13;
      
      // Add activity to project and set time horizon for both project and activity
      app.addActivityToProject("Activity Name", projectNumber);
      app.addDeveloperToActivity("bond", "Activity Name", projectNumber);
      app.setTimeHorizonOfProject(startYear, startWeek, endYear, endWeek, projectNumber);
      app.setTimeHorizonOfActivity(startYear, startWeek, endYear, endWeek, "Activity Name", projectNumber);


      // Check that the correct message is given when negative hours are registerred
      try {app.registerHoursToActivity(hours, day, week, year, projectNumber, activityName);}
      catch (OperationNotAllowedException e){
          assertEquals("The user cannot register hours outside of the time horizon of the activity", e.getMessage());
      }
    }
    

    @Test
    public void testSetTimeHorizonOfProjectD() throws OperationNotAllowedException{
      // Add a new project
      app.addProject();

      // assign current user as project leader
      String projectNumber = app.getProjectNumber();
      app.assignProjectLeader(projectNumber, "bond");
      
      // Define input
      double hours = 7.5;
      int day = 2;
      int week = 15;
      int year = app.getCurrentYear();
      String activityName = "Activity Name";
      int startYear = app.getCurrentYear();
      int endYear = app.getCurrentYear()+1;
      int startWeek = 11;
      int endWeek = 13;
      
      // Add activity to project and set time horizon for both project and activity
      app.addActivityToProject("Activity Name", projectNumber);
      app.addDeveloperToActivity("bond", "Activity Name", projectNumber);
      app.setTimeHorizonOfProject(startYear, startWeek, endYear, endWeek, projectNumber);
      app.setTimeHorizonOfActivity(startYear, startWeek, endYear, endWeek, "Activity Name", projectNumber);

      // Store the previous data to check that it updates
      double currentlyRegisteredHoursUser = app.getCurrentUser().getRegisteredHours(day, week, year, projectNumber, activityName);
      double currentlyRegisteredHoursProject = app.getProject(projectNumber).getTotalHoursRegistered();
      double currentlyRegisteredHoursActivity =  app.getProject(projectNumber).getActivity(activityName).getTotalHoursRegistered();

      // call method
      app.registerHoursToActivity(hours, day, week, year, projectNumber, activityName);

      // check that the correct updates have been made
      assertTrue(app.getCurrentUser().getRegisteredHours(day, week, year, projectNumber, activityName) == (currentlyRegisteredHoursUser+7.5));
      assertTrue(app.getProject(projectNumber).getTotalHoursRegistered() == (currentlyRegisteredHoursProject+7.5));
      assertTrue(app.getProject(projectNumber).getActivity(activityName).getTotalHoursRegistered() == (currentlyRegisteredHoursActivity+7.5));
    }



    @Test
    public void testSetTimeHorizonOfProjectE() throws OperationNotAllowedException{
      // Add a new project
      app.createNonWorkActivitiesProject();

      // assign current user as project leader
      String projectNumber = "00001";
      app.assignProjectLeader(projectNumber, "bond");
      
      // Define input
      double hours = 7.5;
      int day = 2;
      int week = 15;
      int year = app.getCurrentYear()+5;
      String activityName = "Activity Name";
      int startYear = app.getCurrentYear();
      int endYear = app.getCurrentYear()+1;
      int startWeek = 11;
      int endWeek = 13;
      
      // Add activity to project and set time horizon for both project and activity
      app.addActivityToProject("Activity Name", projectNumber);
      app.setTimeHorizonOfProject(startYear, startWeek, endYear, endWeek, projectNumber);
      app.setTimeHorizonOfActivity(startYear, startWeek, endYear, endWeek, "Activity Name", projectNumber);

      // Store the previous data to check that it updates
      double currentlyRegisteredHoursUser = app.getCurrentUser().getRegisteredHours(day, week, year, projectNumber, activityName);
      double currentlyRegisteredHoursProject = app.getProject(projectNumber).getTotalHoursRegistered();
      double currentlyRegisteredHoursActivity =  app.getProject(projectNumber).getActivity(activityName).getTotalHoursRegistered();

      // call method
      app.registerHoursToActivity(hours, day, week, year, projectNumber, activityName);

      // check that the correct updates have been made
      assertTrue(app.getCurrentUser().getRegisteredHours(day, week, year, projectNumber, activityName) == (currentlyRegisteredHoursUser+7.5));
      assertTrue(app.getProject(projectNumber).getTotalHoursRegistered() == (currentlyRegisteredHoursProject+7.5));
      assertTrue(app.getProject(projectNumber).getActivity(activityName).getTotalHoursRegistered() == (currentlyRegisteredHoursActivity+7.5));
    }
  }