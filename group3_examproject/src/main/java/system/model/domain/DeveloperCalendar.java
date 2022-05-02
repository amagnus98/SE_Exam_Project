package system.model.domain;

import java.util.*;

public class DeveloperCalendar {
    // nested hashmap with the following order:
    // dateKey -> projectNumber -> activityName
    private HashMap<String, HashMap<String, HashMap<String, Double>>> calendar = new HashMap<String, HashMap<String, HashMap<String,Double>>>();
    
    public String generateDateKey(int day, int week, int year){
        return year + String.format("%02d", week) + String.format("%02d", day);
    }

    public ArrayList<String> getSortedDateKeys(){
        ArrayList<String> dateKeys = new ArrayList<>(this.calendar.keySet());
        ArrayList<Integer> sortedDateKeysInts = new ArrayList<>();
        for(String s : dateKeys) sortedDateKeysInts.add(Integer.valueOf(s));
        Collections.sort(sortedDateKeysInts);
        ArrayList<String> sortedDateKeys = new ArrayList<>();
        for(Integer i : sortedDateKeysInts) sortedDateKeys.add(Integer.toString(i));
        return sortedDateKeys;
    }

    public HashMap<String, HashMap<String, HashMap<String, Double>>> getCalendar(){
        return this.calendar;
    }

    public void setHours(double hours, int day, int week, int year, String projectNumber, String activityName){
        // get key that corresponds to the given date
        String dateKey = generateDateKey(day, week, year);
        // get all information about registered hours on the given date
        if (!calendar.containsKey(dateKey)){
            calendar.put(dateKey, new HashMap<String, HashMap<String, Double>>());
        }
        HashMap<String, HashMap<String, Double>> projectCalendar = calendar.get(dateKey);
        // get all information about registered hours on the given date for the specific project
        if (!projectCalendar.containsKey(projectNumber)){
            projectCalendar.put(projectNumber, new HashMap<String,Double>());
        }
        HashMap<String,Double> activityCalendar = projectCalendar.get(projectNumber);
        activityCalendar.put(activityName,hours);
    }
    
    public double getHours(int day, int week, int year, String projectNumber, String activityName){
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

    public boolean hasRegisteredHoursForActivity(int day, int week, int year, String projectNumber, String activityName){
        // get key that corresponds to the given date
        String dateKey = generateDateKey(day, week, year);

        if (calendar.containsKey(dateKey)){
            HashMap<String, HashMap<String, Double>> projectCalendar = calendar.get(dateKey);
            if (projectCalendar.containsKey(projectNumber)){
                HashMap<String,Double> activityCalendar = projectCalendar.get(projectNumber);
                if (activityCalendar.containsKey(activityName)){
                    return true;
                }
            }
        } 
        return false;
    }

    public boolean hasRegisteredHoursForDay(int day, int week, int year){
        String dateKey = generateDateKey(day, week, year);
        return calendar.containsKey(dateKey);
    }

    public ArrayList<HashMap<String,String>> getRegisteredActivities(int day,int week,int year) throws OperationNotAllowedException{
        ArrayList<HashMap<String,String>> registeredActivities = new ArrayList<HashMap<String,String>>();
        String dateKey = generateDateKey(day, week, year);
        if (calendar.containsKey(dateKey)){
            HashMap<String, HashMap<String, Double>> projectCalendar = calendar.get(dateKey);
            for (String projectNumber : projectCalendar.keySet()){
                HashMap<String,Double> activityCalendar = projectCalendar.get(projectNumber);
                for (String activityName : activityCalendar.keySet()){
                    double registeredHours = activityCalendar.get(activityName);
                    HashMap<String,String> activityInformation = getActivityInformation(activityName, projectNumber, registeredHours);
                    registeredActivities.add(activityInformation);
                }
            }
            return registeredActivities;
        } else {
            throw new OperationNotAllowedException("The user has registered no hours on the given day");
        }
    }

    // Helper Method: Creates and returns a hashmap containing information of both 
    // projectNumber, activityName, and registerred Hours associated with an activity
    public HashMap<String,String> getActivityInformation(String activityName, String projectNumber, double registeredHours){
        HashMap<String,String> activityInformation = new HashMap<String,String>();
        activityInformation.put("Project number",projectNumber);
        activityInformation.put("Activity name", activityName);
        activityInformation.put("Registered hours", "" + registeredHours);
        return activityInformation;
    }
}
