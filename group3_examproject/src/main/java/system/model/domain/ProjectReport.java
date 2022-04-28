package system.model.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjectReport {
    private Project project;
    // keeps track of how many hours have been worked on and is estimated for each activity
    private HashMap<String,Double> estimatedActivityTime = new HashMap<>();
    private HashMap<String,Double> registeredActivityTime = new HashMap<>();
    private double estimatedHoursForProject;
    private double totalHoursRegisteredToProject;

    public ProjectReport(Project project){
        this.project = project;
        updateProjectReport();
    }

    public void updateProjectReport(){
        setEstimatedActivityTime();
        setRegisteredActivityTime();
        setEstimatedHoursForProject();
        setTotalHoursRegisteredToProject();
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
            this.registeredActivityTime.put(a.getName(), a.getTotalHoursRegistered());
        }
    }
    
    public void setEstimatedHoursForProject(){
        this.estimatedHoursForProject = project.getEstimatedWorkHours();
    }
    
    public double getEstimatedHoursForProject() {
        return this.estimatedHoursForProject;
    }

    public void setTotalHoursRegisteredToProject(){
        this.totalHoursRegisteredToProject = project.getTotalHoursRegistered();
    }

    public double getTotalHoursRegisteredToProject(){
        return this.totalHoursRegisteredToProject;
    }

    public String getProjectNumber(){
        return project.getProjectNumber();
    }

    public double getEstimatedHoursOnActivity(String activityName) {
        return this.estimatedActivityTime.get(activityName);
    }

     public double getTotalHoursWorkedOnActivity(String activityName) {
        return this.registeredActivityTime.get(activityName);
    }
}
