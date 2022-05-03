package system.model.domain;

import java.util.*;

public class Project extends Event{
    // key project variables
    private String projectNumber;
    private Developer projectLeader;
    private boolean hasProjectLeader = false;
    private boolean isNonWorkActivityProject = false;
    private ArrayList<Activity> activities = new ArrayList<>();

    // constructor for a project that is not given a name
    public Project(String projectNumber){
        super();
        this.projectNumber = projectNumber;
    }

    // constructor for a named project
    public Project(String projectNumber, String name){
        super();
        this.projectNumber = projectNumber;
        this.name = name;
    }

    public String getProjectNumber(){
        return this.projectNumber;
    }

    public Developer getProjectLeader(){
        return this.projectLeader;
    }

    public void setProjectLeader(Developer d){
        this.projectLeader = d;
        this.hasProjectLeader = true;
    }

    public boolean hasProjectLeader(){
        return this.hasProjectLeader;
    }

    // check if project contains a specific activity
    public boolean containsActivity(String activityName){
        for (Activity a : this.activities){
            if (a.getName().equals(activityName)){
                return true;
            }
        }
        return false;
    }

    // Counts the number of occurences of an activity in the activities list
    // used to check that activities don't appear more than once
    public int getActivityOccurences(String activityName){
        int occurences = 0;
        for (Activity a: this.activities){
            if (a.getName().equals(activityName)){
                occurences++;
            }
        }
        return occurences;
    }

    // add activity to activity list
    public void addToActivityList(Activity activity){
        activity.setAssignedProject(this);
        this.activities.add(activity);
    }

    // add activity to project
    public void addActivity(Activity activity) throws OperationNotAllowedException{
        if (!(containsActivity(activity.getName()))){
            this.addToActivityList(activity);
        } else {
            throw new OperationNotAllowedException("Project already contains an activity with the given name");
        }   
    }

    // Get activity
    public Activity getActivity(String activityName) throws OperationNotAllowedException {
        for (Activity a : activities) {
            if (a.getName().equals(activityName)) {
                return a;
            }
        }
        throw new OperationNotAllowedException("The project has no activity with the given name");
    }

    public ArrayList<Activity> getActivities() {
        return this.activities;
    }

    // add developer to project
    public void addDeveloper(Developer d) throws OperationNotAllowedException{
        if (!isDeveloperAssigned(d)){
            this.assignedDevelopers.add(d);    
        } else {
            throw new OperationNotAllowedException("The developer is already assigned to the given project");
        }
    }

    // set project as non work activity project
    public void setAsNonWorkActivityProject(){
        this.isNonWorkActivityProject = true;
    }

    // check if the project is a non work activity project
    public boolean isNonWorkActivityProject(){
        return this.isNonWorkActivityProject;
    }

    // check that a given time horizon for a project does not cause any conflicts with the time horizons of its assigned activities
    // relevant when users decide to edit the time horizon for a project at a later point
    public boolean isTimeHorizonValidForAllActivities(int startYear, int startWeek, int endYear, int endWeek){
        for (Activity activity : this.activities){
            // don't check if the time horizon of an activity hasn't been set yet
            if (!activity.isTimeHorizonDefined()){
                continue;
            } 
            // check if start time of the activity comes before the proposed start time of the project
            if (activity.getStartYear() < startYear || (activity.getStartYear() == startYear && activity.getStartWeek() < startWeek)){
                return false;
            }
            // check if end time of the activity comes after the proposed end time of the project
            if (activity.getEndYear() > endYear || (activity.getEndYear() == endYear && activity.getEndWeek() > endWeek)){
                return false;
            }
        }
        return true;
    }

}