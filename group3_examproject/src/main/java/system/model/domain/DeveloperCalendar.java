// Responsible - Asbjørn Magnussen (s183546)
package system.model.domain;

import static org.junit.Assert.assertTrue;

import java.util.*;

public class DeveloperCalendar {
    // nested hashmap with the following order:
    // dateKey -> projectNumber -> activityName
    private HashMap<String, HashMap<String, HashMap<String, Double>>> calendar = new HashMap<String, HashMap<String, HashMap<String,Double>>>();
    
    // Getter for the calendar
    public HashMap<String, HashMap<String, HashMap<String, Double>>> getCalendar(){
        return this.calendar;
    }

    // generates date keys for the hashmap
    // e.g. generateDateKey(6,1,2022) = "20220106"
    public String generateDateKey(int day, int week, int year){
        return year + String.format("%02d", week) + String.format("%02d", day);
    }

    // set registered hours for a given day
    public void setRegisteredHoursForActivity(double hours, int day, int week, int year, String projectNumber, String activityName){
        // get key that corresponds to the given date
        String dateKey = generateDateKey(day, week, year);
        // check if any hours have been registered already for the given day
        if (!calendar.containsKey(dateKey)){
            calendar.put(dateKey, new HashMap<String, HashMap<String, Double>>());
        }
        HashMap<String, HashMap<String, Double>> projectCalendar = calendar.get(dateKey);
        // check if any hours have been registered already for the project on the given day
        if (!projectCalendar.containsKey(projectNumber)){
            projectCalendar.put(projectNumber, new HashMap<String,Double>());
        }
        HashMap<String,Double> activityCalendar = projectCalendar.get(projectNumber);
        // set the registered hours for the activity
        activityCalendar.put(activityName,hours);
    }
    
    // get registered hours for an activity on a given day
    public double getRegisteredHoursForActivity(int day, int week, int year, String projectNumber, String activityName){
        // if no hours have been registered return 0
        if (!hasRegisteredHoursForActivity(day, week, year, projectNumber, activityName)){
            return 0;
        }
        // get key that corresponds to the given date
        String dateKey = generateDateKey(day, week, year);
        // get all information about registered hours on the given date
        HashMap<String, HashMap<String, Double>> projectCalendar = calendar.get(dateKey);
        // get all information about registered hours on the given date for the specific project
        HashMap<String,Double> activityCalendar = projectCalendar.get(projectNumber);
        return activityCalendar.get(activityName);
    }

    // check if the user has registered any hours for the given activity on the specific day
    public boolean hasRegisteredHoursForActivity(int day, int week, int year, String projectNumber, String activityName){
        
        // Precondition
        assertTrue(day >= 1 && day <= 7);
        assertTrue(week >= 1 && day <= 52);
        assertTrue(year >= 0);
        assertTrue(projectNumber != null);
        assertTrue(activityName != null);
        boolean result = false;
        
        // get key that corresponds to the given date
        String dateKey = generateDateKey(day, week, year);

        // check if the user has registered any hours for the given day
        if (hasRegisteredHoursForDay(day, week, year)) {
            // get all projects of the activities that have been registered hours to for the specific day
            HashMap<String, HashMap<String, Double>> projectCalendar = calendar.get(dateKey);
            if (projectCalendar.containsKey(projectNumber)){
                // check if there has been registered any hours for the specific day
                HashMap<String,Double> activityCalendar = projectCalendar.get(projectNumber);
                if (activityCalendar.containsKey(activityName)){
                    result = true;
                }
            }
        } 

        // Postcondition
        if (hasRegisteredHoursForDay(day, week, year)) {
            // get all projects of the activities that have been registered hours to for the specific day
            HashMap<String, HashMap<String, Double>> projectCalendar = calendar.get(dateKey);
            if (projectCalendar.containsKey(projectNumber)){
                // check if there has been registered any hours for the specific day
                HashMap<String,Double> activityCalendar = projectCalendar.get(projectNumber);
                if (activityCalendar.containsKey(activityName)){
                    assertTrue(result == true);
                    return result;
                }
            }
        } 
        assertTrue(result == false);
        return result;
    }

    // check if there has been registered any hours for the specific day
    public boolean hasRegisteredHoursForDay(int day, int week, int year){
        String dateKey = generateDateKey(day, week, year);
        return calendar.containsKey(dateKey);
    }

}
