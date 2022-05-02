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
        app.logIn("amag");
    }


    @Test
    public void testRegisterHoursToActivityA() throws OperationNotAllowedException{
      // Add a new project
      app.addProject();

      // assign current user as project leader
      String projectNumber = app.getProjectNumber();
      app.assignProjectLeader(projectNumber, "amag");
      
      // Define input
      double hours = 0;
      int day = 2;
      int week = 2;
      int year = app.getCurrentYear();
      String activityName = "Activity Name";
      int startYear = app.getCurrentYear();
      int endYear = app.getCurrentYear()+1;
      int startWeek = 20;
      int endWeek = 25;
      
      // Add activity to project and set time horizon for both project and activity
      app.addActivityToProject("Activity Name", projectNumber);
      app.addDeveloperToActivity("amag", "Activity Name", projectNumber);
      app.setTimeHorizonOfProject(startYear, startWeek, endYear, endWeek, projectNumber);
      app.setTimeHorizonOfActivity(startYear, startWeek, endYear, endWeek, "Activity Name", projectNumber);


      // Check that the correct message is given when hours format is not valid
      try {app.registerHoursToActivity(hours, day, week, year, projectNumber, activityName);}
      catch (OperationNotAllowedException e){
          assertEquals("Hours must be more than zero and not greater than 24", e.getMessage());
      }
    }
    
    @Test
    public void testRegisterHoursToActivityB() throws OperationNotAllowedException{
      // Add a new project
      app.addProject();

      // assign current user as project leader
      String projectNumber = app.getProjectNumber();
      app.assignProjectLeader(projectNumber, "amag");
      
      // Define input
      double hours = 5;
      int day = 8;
      int week = 2;
      int year = app.getCurrentYear();
      String activityName = "Activity Name";
      int startYear = app.getCurrentYear();
      int endYear = app.getCurrentYear()+1;
      int startWeek = 20;
      int endWeek = 25;
      
      // Add activity to project and set time horizon for both project and activity
      app.addActivityToProject("Activity Name", projectNumber);
      app.addDeveloperToActivity("amag", "Activity Name", projectNumber);
      app.setTimeHorizonOfProject(startYear, startWeek, endYear, endWeek, projectNumber);
      app.setTimeHorizonOfActivity(startYear, startWeek, endYear, endWeek, "Activity Name", projectNumber);


      // Check that the correct message is given when day format is not valid
      try {app.registerHoursToActivity(hours, day, week, year, projectNumber, activityName);}
      catch (OperationNotAllowedException e){
          assertEquals("Days must be more than zero and not greater than 7", e.getMessage());
      }
    }


    @Test
    public void testRegisterHoursToActivityC() throws OperationNotAllowedException{
      // Add a new project
      app.addProject();

      // assign current user as project leader
      String projectNumber = app.getProjectNumber();
      app.assignProjectLeader(projectNumber, "amag");
      
      // Define input
      double hours = 5;
      int day = 1;
      int week = 54;
      int year = app.getCurrentYear()+5;
      String activityName = "Activity Name";
      int startYear = app.getCurrentYear();
      int endYear = app.getCurrentYear()+1;
      int startWeek = 20;
      int endWeek = 25;
      
      // Add activity to project and set time horizon for both project and activity
      app.addActivityToProject("Activity Name", projectNumber);
      app.addDeveloperToActivity("amag", "Activity Name", projectNumber);
      app.setTimeHorizonOfProject(startYear, startWeek, endYear, endWeek, projectNumber);
      app.setTimeHorizonOfActivity(startYear, startWeek, endYear, endWeek, "Activity Name", projectNumber);


      // Check that the correct message is given when week format is not valid
      try {app.registerHoursToActivity(hours, day, week, year, projectNumber, activityName);}
      catch (OperationNotAllowedException e){
          assertEquals("Weeks must be more than zero and not greater than 52", e.getMessage());
      }
    }
    

    @Test
    public void testRegisterHoursToActivityD() throws OperationNotAllowedException{
      // Add a new project
      app.addProject();

      // assign current user as project leader
      String projectNumber = app.getProjectNumber();
      app.assignProjectLeader(projectNumber, "amag");
      
      // Define input
      double hours = 5;
      int day = 1;
      int week = 20;
      int year = app.getCurrentYear();
      String activityName = "Activity Name";
      int startYear = app.getCurrentYear();
      int endYear = app.getCurrentYear()+1;
      int startWeek = 20;
      int endWeek = 25;
      
      // Add activity to project and set time horizon for both project and activity
      app.addActivityToProject("Activity Name", projectNumber);
      app.setTimeHorizonOfProject(startYear, startWeek, endYear, endWeek, projectNumber);
      app.setTimeHorizonOfActivity(startYear, startWeek, endYear, endWeek, "Activity Name", projectNumber);

       // Check that the correct message is given when the current user is not assigned to the activity
       try {app.registerHoursToActivity(hours, day, week, year, projectNumber, activityName);}
       catch (OperationNotAllowedException e){
           assertEquals("The user is not assigned the given activity", e.getMessage());
       }
    }



    @Test
    public void testRegisterHoursToActivityE() throws OperationNotAllowedException{
      // Add a new project
      app.addProject();

      // assign current user as project leader
      String projectNumber = app.getProjectNumber();
      app.assignProjectLeader(projectNumber, "amag");
      
      // Define input
      double hours = 5;
      int day = 1;
      int week = 20;
      int year = app.getCurrentYear()-1;
      String activityName = "activityName";
      int startYear = app.getCurrentYear();
      int endYear = app.getCurrentYear()+1;
      int startWeek = 20;
      int endWeek = 25;
      
      // Add activity to project, add current user to activity and set time horizon for both project and activity
      app.addActivityToProject(activityName, projectNumber);
      app.addDeveloperToActivity("amag", activityName, projectNumber);
      app.setTimeHorizonOfProject(startYear, startWeek, endYear, endWeek, projectNumber);
      app.setTimeHorizonOfActivity(startYear, startWeek, endYear, endWeek, activityName, projectNumber);

      // Check that the correct message is given when year is not within the time horizon of the activity
      try {app.registerHoursToActivity(hours, day, week, year, projectNumber, activityName);}
      catch (OperationNotAllowedException e){
          assertEquals("The user cannot register hours outside of the time horizon of the activity", e.getMessage());
      }
    }

    @Test
    public void testRegisterHoursToActivityF() throws OperationNotAllowedException{
      // define input
      double hours = 5;
      int day = 1;
      int week = 20;
      int year = app.getCurrentYear();
      String activityName = "Vacation";
      String projectNumber = app.getNonWorkActivitiesProjectNumber();

      // store previous data to check that it updates
      double prevRegisteredHoursUser = app.getCurrentUser().getRegisteredHours(day, week, year, projectNumber, activityName);
      double prevTotalHoursProject = app.getProject(projectNumber).getTotalHoursRegistered();
      double prevTotalHoursActivity =  app.getProject(projectNumber).getActivity(activityName).getTotalHoursRegistered();

      // call method
      app.registerHoursToActivity(hours, day, week, year, projectNumber, activityName);

      // assert expected output
      assertTrue(app.getCurrentUser().getRegisteredHours(day, week, year, projectNumber, activityName)==hours);
      assertTrue(app.getProject(projectNumber).getTotalHoursRegistered() == prevTotalHoursProject + hours - prevRegisteredHoursUser);
      assertTrue(app.getProject(projectNumber).getActivity(activityName).getTotalHoursRegistered() == prevTotalHoursActivity + hours - prevRegisteredHoursUser);
    }

    @Test
    public void testRegisterHoursToActivityG() throws OperationNotAllowedException{
      // Add a new project
      app.addProject();

      // assign current user as project leader
      String projectNumber = app.getProjectNumber();
      app.assignProjectLeader(projectNumber, "amag");
      
      // Define input
      double hours = 5;
      int day = 1;
      int week = 20;
      int year = app.getCurrentYear();
      String activityName = "activityName";
      int startYear = app.getCurrentYear();
      int endYear = app.getCurrentYear()+1;
      int startWeek = 20;
      int endWeek = 25;
      
      // Add activity to project, add current user to activity and set time horizon for both project and activity
      app.addActivityToProject(activityName, projectNumber);
      app.addDeveloperToActivity("amag", activityName, projectNumber);
      app.setTimeHorizonOfProject(startYear, startWeek, endYear, endWeek, projectNumber);
      app.setTimeHorizonOfActivity(startYear, startWeek, endYear, endWeek, activityName, projectNumber);

      // store previous data to check that it updates
      double prevRegisteredHoursUser = app.getCurrentUser().getRegisteredHours(day, week, year, projectNumber, activityName);
      double prevTotalHoursProject = app.getProject(projectNumber).getTotalHoursRegistered();
      double prevTotalHoursActivity =  app.getProject(projectNumber).getActivity(activityName).getTotalHoursRegistered();

      // call method
      app.registerHoursToActivity(hours, day, week, year, projectNumber, activityName);

      // assert expected output
      assertTrue(app.getCurrentUser().getRegisteredHours(day, week, year, projectNumber, activityName)==hours);
      assertTrue(app.getProject(projectNumber).getTotalHoursRegistered() == prevTotalHoursProject + hours - prevRegisteredHoursUser);
      assertTrue(app.getProject(projectNumber).getActivity(activityName).getTotalHoursRegistered() == prevTotalHoursActivity + hours - prevRegisteredHoursUser);
  }
}