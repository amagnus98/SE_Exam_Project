package system.model.domain;

import java.util.*;

public class Project extends Event{
    private String projectNumber;
    private Developer projectLeader;
    private boolean hasProjectLeader = false;
    private String nonWorkActivitiesProjectNumber = "00001";
    private ArrayList<Activity> activities = new ArrayList<>();
    private ProjectReport projectReport;

    public Project(String projectNumber){
        super();
        this.projectNumber = projectNumber;
    }

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

    public boolean containsActivity(String activityName){
        for (Activity a : this.activities){
            if (a.getName().equals(activityName)){
                return true;
            }
        }
        return false;
    }

    // Counts the number of occurences of an activity 
    public int getActivityOccurences(String activityName){
        int occurences = 0;
        for (Activity a: this.activities){
            if (a.getName().equals(activityName)){
                occurences++;
            }
        }
        return occurences;
    }

    public void addActivity(Activity activity) throws OperationNotAllowedException{
        if (!(containsActivity(activity.getName()))){
            activity.setAssignedProject(this.projectNumber);
            this.activities.add(activity);
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

    // Checks if the developer is assigned to the project
    public boolean isDeveloperAssigned(String initials){
        for (Developer d : this.assignedDevelopers){
            if (d.getInitials().equals(initials)){
                return true;
            } 
        }
        return false;
    }

    public void addDeveloper(Developer d) throws OperationNotAllowedException{
        if (!isDeveloperAssigned(d.getInitials())){
            this.assignedDevelopers.add(d);    
        } else {
            throw new OperationNotAllowedException("The developer is already assigned to the given project");
        }
    }

    public boolean isNonWorkActivity(String projectNumber){
        return this.nonWorkActivitiesProjectNumber.equals(projectNumber);
    }

    public boolean isTimeHorizonValidForAllActivities(int startYear, int startWeek, int endYear, int endWeek){
        for (Activity activity : this.activities){
            // check if start time of the activity comes before the project start time
            if (activity.getStartYear() < startYear || (activity.getStartYear() == startYear && activity.getStartWeek() < startWeek)){
                return false;
            }
            // check if end time of the activity comes after the project end time
            if (activity.getEndYear() > endYear || (activity.getEndYear() == endYear && activity.getEndWeek() > endWeek)){
                return false;
            }
        }
        return true;
    }




    
    

}