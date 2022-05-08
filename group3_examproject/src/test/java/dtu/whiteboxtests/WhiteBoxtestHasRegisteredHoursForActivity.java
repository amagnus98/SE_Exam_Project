// Responsible - Marcus Roberto Nielsen (s204126)

package dtu.whiteboxtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.rmi.server.Operation;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import system.model.domain.App;
import system.model.domain.Developer;
import system.model.domain.DeveloperCalendar;
import system.model.domain.OperationNotAllowedException;

public class WhiteBoxtestHasRegisteredHoursForActivity {

    App app = new App();

  
    @BeforeEach
    public void setUp() throws OperationNotAllowedException, Exception {
        System.out.println("Executing setup");
        app.logIn("kape");
    }

    @Test
    public void hasRegisteredHoursForActivityA() throws OperationNotAllowedException{
        Developer developer = this.app.getDeveloper("kape");
        DeveloperCalendar developerCalendar = developer.getDeveloperCalendar();
        HashMap<String,HashMap<String,HashMap<String,Double>>> calendar = developerCalendar.getCalendar();

        // define input
        int day = 1;
        int week = 1;
        int year = app.getCurrentYear();

        String projectNumber = "22001";
        String activityName = "activityName";

        // check that //1 is false
        String dateKey = developerCalendar.generateDateKey(day, week, year);
        assertFalse(calendar.containsKey(dateKey));

        // check that output is false when //1 is false
        assertFalse(developerCalendar.hasRegisteredHoursForActivity(day, week, year, projectNumber, activityName));
    }  
    
    @Test
    public void hasRegisteredHoursForActivityB() throws OperationNotAllowedException{
        Developer developer = this.app.getDeveloper("kape");
        DeveloperCalendar developerCalendar = developer.getDeveloperCalendar();
        HashMap<String,HashMap<String,HashMap<String,Double>>> calendar = developerCalendar.getCalendar();

        // define dateKey input
        int day = 1;
        int week = 1;
        int year = app.getCurrentYear();

        // create a project, get projectNumber, assign currentUser as projectLeader and set time horizon of project
        app.addProject();
        String projectNumber = app.getProjectNumber();
        app.assignProjectLeader(projectNumber, "kape");
        app.setTimeHorizonOfProject(year, week, app.getCurrentYear()+1, 2, projectNumber);
        
        // create project activity and register some hours to it so //1 is passed
        app.addActivityToProject("activityName", projectNumber);
        app.addDeveloperToActivity(developer.getInitials(), "activityName", projectNumber);
        app.setTimeHorizonOfActivity(year, week, app.getCurrentYear()+1, 2, "activityName", projectNumber);
        app.registerHoursToActivity(5, day, week, year, projectNumber, "activityName");

        // create a new project and get projectNumber
        app.addProject();
        String projectNumber2 = app.getProjectNumber();

        // check that //1 is true
        String dateKey = developerCalendar.generateDateKey(day, week, year);
        assertTrue(calendar.containsKey(dateKey));

        // check that //2 is false
        assertFalse(calendar.get(dateKey).containsKey(projectNumber2));

        // check that output is false when //2 is false
        assertFalse(developerCalendar.hasRegisteredHoursForActivity(day, week, year, projectNumber2, "activityName"));
    }
    
    @Test
    public void hasRegisteredHoursForActivityC() throws OperationNotAllowedException{
        Developer developer = this.app.getDeveloper("kape");
        DeveloperCalendar developerCalendar = developer.getDeveloperCalendar();
        HashMap<String,HashMap<String,HashMap<String,Double>>> calendar = developerCalendar.getCalendar();

        // define dateKey input
        int day = 1;
        int week = 1;
        int year = app.getCurrentYear();

        // create a project, get projectNumber, assign currentUser as projectLeader and set time horizon of project
        app.addProject();
        String projectNumber = app.getProjectNumber();
        app.assignProjectLeader(projectNumber, "kape");
        app.setTimeHorizonOfProject(year, week, app.getCurrentYear()+1, 2, projectNumber);
        
        // create project activity and register some hours to it so //1 is passed
        app.addActivityToProject("activityName", projectNumber);
        app.addDeveloperToActivity(developer.getInitials(), "activityName", projectNumber);
        app.setTimeHorizonOfActivity(year, week, app.getCurrentYear()+1, 2, "activityName", projectNumber);
        app.registerHoursToActivity(5, day, week, year, projectNumber, "activityName");

        // check that //1 is true
        String dateKey = developerCalendar.generateDateKey(day, week, year);
        assertTrue(calendar.containsKey(dateKey));

        // check that //2 is true
        assertTrue(calendar.get(dateKey).containsKey(projectNumber));

        // create a new project activity and don't register hours to it so //3 is not passed
        app.addActivityToProject("activityName2", projectNumber);

        // check that //3 is false
        assertFalse(calendar.get(dateKey).get(projectNumber).containsKey("activityName2"));

        // check that output is false when no hours have been registered to activity with activityName2
        assertFalse(developerCalendar.hasRegisteredHoursForActivity(day, week, year, projectNumber, "activityName2"));

    }
    
    @Test
    public void hasRegisteredHoursForActivityD() throws OperationNotAllowedException{
        Developer developer = this.app.getDeveloper("kape");
        DeveloperCalendar developerCalendar = developer.getDeveloperCalendar();
        HashMap<String,HashMap<String,HashMap<String,Double>>> calendar = developerCalendar.getCalendar();

        // define dateKey input
        int day = 1;
        int week = 1;
        int year = app.getCurrentYear();

        // create a project, get projectNumber, assign currentUser as projectLeader and set time horizon of project
        app.addProject();
        String projectNumber = app.getProjectNumber();
        app.assignProjectLeader(projectNumber, "kape");
        app.setTimeHorizonOfProject(year, week, app.getCurrentYear()+1, 2, projectNumber);
        
        // create project activity and register some hours to it so //1 is passed
        app.addActivityToProject("activityName", projectNumber);
        app.addDeveloperToActivity(developer.getInitials(), "activityName", projectNumber);
        app.setTimeHorizonOfActivity(year, week, app.getCurrentYear()+1, 2, "activityName", projectNumber);
        app.registerHoursToActivity(5, day, week, year, projectNumber, "activityName");

        // check that //1 is true
        String dateKey = developerCalendar.generateDateKey(day, week, year);
        assertTrue(calendar.containsKey(dateKey));

        // check that //2 is true
        assertTrue(calendar.get(dateKey).containsKey(projectNumber));

        // check that //3 is true
        assertTrue(calendar.get(dateKey).get(projectNumber).containsKey("activityName"));

        // check that output is false when no hours have been registered to activity with activityName2
        assertTrue(developerCalendar.hasRegisteredHoursForActivity(day, week, year, projectNumber, "activityName"));
    }
  }
