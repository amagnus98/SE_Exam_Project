package system.model.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjectReport {
    private Project project;
    // keeps track of how many hours have been worked on and is estimated for each activity
    private HashMap<String,Double> estimatedActivityTime;
    private HashMap<String,Double> registeredActivityTime;
    private double estimatedHoursOnProject;
    private double totalHoursWorkedOnProject;

    public ProjectReport(Project project){
        this.project = project;
        updateProjectReport();
    }

    public void updateProjectReport(){
        setEstimatedActivityTime();
        setRegisteredActivityTime();
        setEstimatedHoursOnProject();
        setTotalHoursWorkedOnProject();
    }

    public void setEstimatedActivityTime(){
        ArrayList<Activity> activities = this.project.getActivities();
        for (Activity a : activities){
            this.estimatedActivityTime.put(a.getName(), a.getEstimatedWorkHours());
        }
    }

    public void setRegisteredActivityTime(){
        ArrayList<Activity> activities = this.project.getActivities();
        for (Activity a : activities){
            this.registeredActivityTime.put(a.getName(), a.getTotalHoursWorked());
        }
    }
    
    public void setEstimatedHoursOnProject(){
        this.estimatedHoursOnProject = project.estimatedWorkHours;
    }
    
    public double getEstimatedHoursWorkedOnProject() {
        return this.estimatedHoursOnProject;
    }

    public void setTotalHoursWorkedOnProject(){
        this.totalHoursWorkedOnProject = project.totalHoursWorked;
    }

    public double getTotalHoursWorkedOnProject(){
        return this.totalHoursWorkedOnProject;
    }

    public String getProjectNumber(){
        return project.getProjectNumber();
    }

    public double getEstimatedHoursWorkedOnActivity(String activityName) {
        return this.estimatedActivityTime.get(activityName);
    }

     public double getTotalHoursWorkedOnActivity(String activityName) {
        return this.registeredActivityTime.get(activityName);
    }
}
