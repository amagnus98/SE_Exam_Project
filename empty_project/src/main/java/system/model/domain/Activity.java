package system.model.domain;
import java.util.*;

public class Activity extends Event{
  
  private String parentProjectNumber;
  private double estimatedWorkHours;
  private ArrayList<String> currentlyWorking = new ArrayList<>(); 
  
  public Activity (String name){
      super();
      this.name = name;
  }

  public void setAssignedProject(String projectNumber){
      this.parentProjectNumber = projectNumber;
  }

  public ArrayList<String> getCurrentlyWorking(){
      return this.currentlyWorking;
  }

  public void addDeveloper(String initials){
      currentlyWorking.add(initials);
  }

  public boolean isDeveloperCurrentlyWorking(String initials){
      return currentlyWorking.contains(initials);
  }

  public void setEstimatedWorkHours(double hours){
    this.estimatedWorkHours = hours;
  }
  
  public double getEstimatedWorkHours(){
    return this.estimatedWorkHours;
  }
}
