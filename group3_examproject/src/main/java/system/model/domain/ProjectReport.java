package system.model.domain;

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
        // generate project report
        generateProjectReport();
    }

    public String getProjectNumber(){
        return project.getProjectNumber();
    }

    public void generateProjectReport(){
        setEstimatedActivityTime();
        setRegisteredActivityTime();
        setEstimatedHoursForProject();
        setTotalHoursRegisteredToProject();
    }

    // retrieve the estimated hours for each activity and put it into the project report hash map
    public void setEstimatedActivityTime(){
        ArrayList<Activity> activities = this.project.getActivities();
        for (Activity a : activities){
            this.estimatedActivityTime.put(a.getName(), a.getEstimatedWorkHours());
        }
    }

    // retrieve the total registered hours for each activity and put it into the project report hash map
    public void setRegisteredActivityTime(){
        ArrayList<Activity> activities = this.project.getActivities();
        for (Activity a : activities){
            this.registeredActivityTime.put(a.getName(), a.getTotalHoursRegistered());
        }
    }

    public double getEstimatedHoursOnActivity(String activityName) {
        return this.estimatedActivityTime.get(activityName);
    }

     public double getTotalHoursWorkedOnActivity(String activityName) {
        return this.registeredActivityTime.get(activityName);
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

}
