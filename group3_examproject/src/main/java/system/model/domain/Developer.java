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
    //private ArrayList<HashMap<String,String>> calendarOutput;
    private ArrayList<Activity> assignedActivities = new ArrayList<>();

    // Constructor of Developer
    public Developer(String initials) {
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
        this.calendar.setRegisteredHoursForActivity(hours, day, week, year, projectNumber, activityName);
    }

    // get registered hours for a specific day
    public double getRegisteredHours(int day, int week, int year, String projectNumber, String activityName){
        return this.calendar.getRegisteredHoursForActivity(day, week, year, projectNumber, activityName);
    }


    // add activity to developers assigned activities
    // used to keep track of which activity the developers are working on
    public void assignActivity(Activity activity) {
        this.assignedActivities.add(activity);
    }


    public ArrayList<Activity> getAssignedActivities(){
        return this.assignedActivities;
    }

}
