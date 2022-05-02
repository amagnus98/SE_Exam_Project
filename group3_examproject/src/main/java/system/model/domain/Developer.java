package system.model.domain;

import java.util.*;

public class Developer {
    
    // initials of the developer
    private String initials;
    // developer calendar used to see registered hours for each day
    private DeveloperCalendar calendar = new DeveloperCalendar();
    // this variable is only used to see if view calender works properly
    // keys are dateKeys
    // values are lists of hashmaps with three values - projectNumber, activityName and registeredHours
    // shows all the activities worked on for the given day
    private ArrayList<HashMap<String,String>> calendarOutput;
    private ArrayList<Activity> assignedActivities = new ArrayList<>();

    // Constructor of Developer
    public Developer(String initials) {
        this.initials = initials;
    }

    // Setter method for initials 
    public void setInitials(String initials) {
        this.initials = initials;
    }

    // Getter method for initials 
    public String getInitials() {
        return this.initials;
    }

    // get calendar
    public DeveloperCalendar getDeveloperCalendar(){
        return this.calendar;
    }

    // register hours for an activity
    public void registerHours(double hours, int day, int week, int year, String projectNumber, String activityName) {
        this.calendar.setRegisteredHours(hours, day, week, year, projectNumber, activityName);
    }

    // get registered hours for a specific day
    public double getRegisteredHours(int day, int week, int year, String projectNumber, String activityName){
        return this.calendar.getRegisteredHours(day, week, year, projectNumber, activityName);
    }

    // set the calendar output for the developer
    // retrieves all the registered activities for the given day
    public void setCalendarOutput(int day, int week, int year) throws OperationNotAllowedException{
        this.calendarOutput = this.calendar.getRegisteredActivities(day,week,year);
    }

    // check if the developer has registered hours for a given day
    public boolean calendarOutputContainsActivity(double hours, String activityName, String projectNumber){
        for (HashMap<String,String> activity : this.calendarOutput){
            if (activity.get("Project number").equals(projectNumber) &&
                activity.get("Activity name").equals(activityName) &&
                activity.get("Registered hours").equals("" + hours)){
                    return true;
            }
        }
        return false;
    }

    public void assignActivity(Activity activity) {
        this.assignedActivities.add(activity);
    }

    public ArrayList<Activity> getAssignedActivities() {
        return this.assignedActivities;
    }

    public ArrayList<Activity> getCurrentAssignedActivities(int week, int year) {
        ArrayList<Activity> currentActivities = new ArrayList<>();
        for (Activity activity : this.assignedActivities) {
            if (activity.isDateWithinTimeHorizon(year, week)) {
                currentActivities.add(activity);
            }
        }
        return currentActivities;

    }

}
