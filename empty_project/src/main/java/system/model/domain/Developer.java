package system.model.domain;

import java.util.*;

public class Developer {
    
    private String initials;
    private DeveloperCalendar calendar = new DeveloperCalendar();
    // this variable is only used to see if view calender works properly
    // keys are dateKeys
    // values are lists of hashmaps with three values - projectNumber, activityName and registeredHours
    // shows all the activities worked on for the given day
    private ArrayList<HashMap<String,String>> calendarOutput;
    
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

    // register hours
    public void registerHours(double hours, int day, int week, int year, String projectNumber, String activityName) {
        this.calendar.setHours(hours, day, week, year, projectNumber, activityName);
    }

    public double getRegisteredHours(int day, int week, int year, String projectNumber, String activityName){
        return this.calendar.getHours(day, week, year, projectNumber, activityName);
    }

    public void setCalendarOutput(int day, int week, int year) throws OperationNotAllowedException{
        this.calendarOutput = this.calendar.getRegisteredActivities(day,week,year);
    }

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

}
