/*
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

public class WhiteBoxtestGetRegisteredActivitiesInformation {

    App app = new App();

  
    @BeforeEach
    public void setUp() throws OperationNotAllowedException, Exception {
        System.out.println("Executing setup");
        app.logIn("kape");
    }

    @Test
    public void getRegisteredActivitiesInformationA() throws OperationNotAllowedException{
        Developer developer = this.app.getDeveloper("kape");
        DeveloperCalendar developerCalendar = developer.getDeveloperCalendar();
        HashMap<String,HashMap<String,HashMap<String,Double>>> calendar = developerCalendar.getCalendar();

        // define input
        int day = 1;
        int week = 1;
        int year = app.getCurrentYear();

        String projectNumber = "22001";
        String activityName = "activityName";

        // Checks that the calender does not contain the datekey
        String dateKey = developerCalendar.generateDateKey(day, week, year);
        assertFalse(calendar.containsKey(dateKey));

        // Checks that the correct error message is thrown
        try{
          developerCalendar.getRegisteredActivitiesInformation(day, week, year);
        } catch (OperationNotAllowedException e){
          assertEquals(e.getMessage(), "The user has registered no hours on the given day");
        }
      }


    @Test
    public void getRegisteredActivitiesInformationB() throws OperationNotAllowedException{
      Developer developer = this.app.getDeveloper("kape");
      DeveloperCalendar developerCalendar = developer.getDeveloperCalendar();
      HashMap<String,HashMap<String,HashMap<String,Double>>> calendar = developerCalendar.getCalendar();

      // define dateKey input
      int day = 1;
      int week = 1;
      int year = app.getCurrentYear();
      String dateKey = developerCalendar.generateDateKey(day, week, year);

      // create a project, get projectNumber, assign currentUser as projectLeader and set time horizon of project
      app.addProject();
      String projectNumber = app.getProjectNumber();
      app.assignProjectLeader(projectNumber, "kape");
      app.setTimeHorizonOfProject(year, week, app.getCurrentYear()+1, 2, projectNumber);
      
      // create project activity and register some hours to it so 
      app.addActivityToProject("activityName", projectNumber);
      app.addDeveloperToActivity(developer.getInitials(), "activityName", projectNumber);
      app.setTimeHorizonOfActivity(year, week, app.getCurrentYear()+1, 2, "activityName", projectNumber);
      app.registerHoursToActivity(5, day, week, year, projectNumber, "activityName");

      // Create expected hashmap
      ArrayList<HashMap<String,String>> expectedOutput = new ArrayList<HashMap<String,String>>();
      HashMap<String,String> expected = new HashMap<String,String>();
      expected.put("Project number",projectNumber);
      expected.put("Activity name", "activityName");
      expected.put("Registered hours", "" + 5.0);
      expectedOutput.add(expected);

      // Safety checks that all preconditions are met before main test
      assertTrue(calendar.containsKey(dateKey)); // datekey exists
      assertTrue(calendar.get(dateKey).containsKey(projectNumber)); // project number is in calender 
      assertTrue(calendar.get(dateKey).get(projectNumber).containsKey("activityName")); // activity is in calender
      assertTrue(developerCalendar.hasRegisteredHoursForActivity(day, week, year, projectNumber, "activityName")); // Hours has been registered to the activity on the given date

      // Test that output is as expected 
      assertEquals(expectedOutput, developerCalendar.getRegisteredActivitiesInformation(day, week, year));
      }


    @Test
    public void getRegisteredActivitiesInformationC() throws OperationNotAllowedException{
      Developer developer = this.app.getDeveloper("kape");
      DeveloperCalendar developerCalendar = developer.getDeveloperCalendar();
      HashMap<String,HashMap<String,HashMap<String,Double>>> calendar = developerCalendar.getCalendar();

      // define dateKey input
      int day = 1;
      int week = 1;
      int year = app.getCurrentYear();
      String dateKey = developerCalendar.generateDateKey(day, week, year);

      // create a project, get projectNumber, assign currentUser as projectLeader and set time horizon of project
      app.addProject();
      String projectNumber = app.getProjectNumber();
      app.assignProjectLeader(projectNumber, "kape");
      app.setTimeHorizonOfProject(year, week, app.getCurrentYear()+1, 2, projectNumber);
      
      // create first project activity and register some hours to it 
      app.addActivityToProject("activityName1", projectNumber);
      app.addDeveloperToActivity(developer.getInitials(), "activityName1", projectNumber);
      app.setTimeHorizonOfActivity(year, week, app.getCurrentYear()+1, 2, "activityName1", projectNumber);
      app.registerHoursToActivity(5, day, week, year, projectNumber, "activityName1");

      // Create second project activity and registers some hours on it
      app.addActivityToProject("activityName2", projectNumber);
      app.addDeveloperToActivity(developer.getInitials(), "activityName2", projectNumber);
      app.setTimeHorizonOfActivity(year, week, app.getCurrentYear()+1, 2, "activityName2", projectNumber);
      app.registerHoursToActivity(2, day, week, year, projectNumber, "activityName2");



      // Create expected hashmap
      ArrayList<HashMap<String,String>> expectedOutput = new ArrayList<HashMap<String,String>>();
      // Adds the first expected element in list
      HashMap<String,String> expected1 = new HashMap<String,String>();
      expected1.put("Project number",projectNumber);
      expected1.put("Activity name", "activityName1");
      expected1.put("Registered hours", "" + 5.0);
      expectedOutput.add(expected1);

      // Adds the second expected element in list
      HashMap<String,String> expected2 = new HashMap<String,String>();
      expected2.put("Project number",projectNumber);
      expected2.put("Activity name", "activityName2");
      expected2.put("Registered hours", "" + 2.0);
      expectedOutput.add(expected2);



      // Safety checks that all preconditions are met before main test
      assertTrue(calendar.containsKey(dateKey)); // datekey exists
      assertTrue(calendar.get(dateKey).containsKey(projectNumber)); // project number is in calender 
      assertTrue(calendar.get(dateKey).get(projectNumber).containsKey("activityName1")); // activities is in calender
      assertTrue(calendar.get(dateKey).get(projectNumber).containsKey("activityName2"));
      assertTrue(developerCalendar.hasRegisteredHoursForActivity(day, week, year, projectNumber, "activityName1")); // Hours has been registered to the activities on the given date
      assertTrue(developerCalendar.hasRegisteredHoursForActivity(day, week, year, projectNumber, "activityName2")); 
      
      
      // Test that output is as expected 
      assertEquals(expectedOutput, developerCalendar.getRegisteredActivitiesInformation(day, week, year));
      }



      @Test
      public void getRegisteredActivitiesInformationD() throws OperationNotAllowedException{
        Developer developer = this.app.getDeveloper("kape");
        DeveloperCalendar developerCalendar = developer.getDeveloperCalendar();
        HashMap<String,HashMap<String,HashMap<String,Double>>> calendar = developerCalendar.getCalendar();
  
        // define dateKey input
        int day = 1;
        int week = 1;
        int year = app.getCurrentYear();
        String dateKey = developerCalendar.generateDateKey(day, week, year);
  
        // Adds first project 
        app.addProject();
        String projectNumber1 = app.getProjectNumber();
        app.assignProjectLeader(projectNumber1, "kape");
        app.setTimeHorizonOfProject(year, week, app.getCurrentYear()+1, 2, projectNumber1);
        
        // Adds second project
        app.addProject();
        String projectNumber2 = app.getProjectNumber();
        app.assignProjectLeader(projectNumber2, "kape");
        app.setTimeHorizonOfProject(year, week, app.getCurrentYear()+1, 2, projectNumber2);


        // Create activity in first project and register some hours to it 
        app.addActivityToProject("activityName1", projectNumber1);
        app.addDeveloperToActivity(developer.getInitials(), "activityName1", projectNumber1);
        app.setTimeHorizonOfActivity(year, week, app.getCurrentYear()+1, 2, "activityName1", projectNumber1);
        app.registerHoursToActivity(5, day, week, year, projectNumber1, "activityName1");
  
        // Create activity in second project and registers some hours on it
        app.addActivityToProject("activityName2", projectNumber2);
        app.addDeveloperToActivity(developer.getInitials(), "activityName2", projectNumber2);
        app.setTimeHorizonOfActivity(year, week, app.getCurrentYear()+1, 2, "activityName2", projectNumber2);
        app.registerHoursToActivity(2, day, week, year, projectNumber2, "activityName2");
  
  
  
        // Create expected hashmap
        ArrayList<HashMap<String,String>> expectedOutput = new ArrayList<HashMap<String,String>>();
        // Adds the first expected element in list
        HashMap<String,String> expected1 = new HashMap<String,String>();
        expected1.put("Project number",projectNumber1);
        expected1.put("Activity name", "activityName1");
        expected1.put("Registered hours", "" + 5.0);
        expectedOutput.add(expected1);
  
        // Adds the second expected element in list
        HashMap<String,String> expected2 = new HashMap<String,String>();
        expected2.put("Project number",projectNumber2);
        expected2.put("Activity name", "activityName2");
        expected2.put("Registered hours", "" + 2.0);
        expectedOutput.add(expected2);
  
  
  
        // Safety checks that all preconditions are met before main test
        assertTrue(calendar.containsKey(dateKey)); // datekey exists
        assertTrue(calendar.get(dateKey).containsKey(projectNumber1)); // projects are in calender 
        assertTrue(calendar.get(dateKey).containsKey(projectNumber2));
        assertTrue(calendar.get(dateKey).get(projectNumber1).containsKey("activityName1")); // activities are in calender
        assertTrue(calendar.get(dateKey).get(projectNumber2).containsKey("activityName2"));
        assertTrue(developerCalendar.hasRegisteredHoursForActivity(day, week, year, projectNumber1, "activityName1")); // Hours has been registered to the activities on the given date
        assertTrue(developerCalendar.hasRegisteredHoursForActivity(day, week, year, projectNumber2, "activityName2")); 
        
        
        // Test that output is as expected 
        assertEquals(expectedOutput, developerCalendar.getRegisteredActivitiesInformation(day, week, year));
        }
    }
*/