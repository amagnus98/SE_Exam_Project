package system.model.domain;
import java.util.*;

public class Activity extends Event{
  
  private String parentProjectNumber;
  private double estimatedWorkHours;
  // the keys are the initials of the developers
  // the values are booleans representing whether they were added by the project leader or not
  // only developers assigned by project leaders can request assistance
  private HashMap<String,Boolean> isAssignedByProjectLeader = new HashMap<>();

  public Activity (String name){
      super();
      this.name = name;
  }

  public void setAssignedProject(String projectNumber){
      this.parentProjectNumber = projectNumber;
  }

  public void addDeveloper(Developer d) throws OperationNotAllowedException{
      if (!isDeveloperAssignedByProjectLeader(d)){
        addAssignedDeveloper(d);
      } else {
        throw new OperationNotAllowedException("The developer is already assigned to the given activity by the project leader");
      }
      
  }

  // for developers added to the activity by a project leader
  public void addAssignedDeveloper(Developer d){
      this.assignedDevelopers.add(d);
      this.isAssignedByProjectLeader.put(d.getInitials(),true);
      d.assignActivity(this);
  }

  public void addRequestedDeveloper(Developer d){
    this.assignedDevelopers.add(d);
    this.isAssignedByProjectLeader.put(d.getInitials(),false);
  }

  public void changeDeveloperFromRequestedToAssisgned(Developer d){
    this.isAssignedByProjectLeader.put(d.getInitials(),true);
  }
  
  public boolean isDeveloperAssignedByProjectLeader(Developer d){
    if (isDeveloperCurrentlyWorking(d)){
      return this.isAssignedByProjectLeader.get(d.getInitials());
    }
    return false;
  }

  public boolean isDeveloperAssigned(Developer d){
    return this.assignedDevelopers.contains(d);
  }

  public boolean canRegisterHours(Developer d) {
    return this.isDeveloperAssigned(d);
  }

  public boolean isDeveloperCurrentlyWorking(Developer d){
      return this.isAssignedByProjectLeader.containsKey(d.getInitials());
  }

  public void setEstimatedWorkHours(double hours){
    this.estimatedWorkHours = hours;
  }
  
  public double getEstimatedWorkHours(){
    return this.estimatedWorkHours;
  }

  public void requestAssistance(Developer receiver, Developer sender) throws OperationNotAllowedException {
    if (isDeveloperAssignedByProjectLeader(sender)){
      if (!isDeveloperCurrentlyWorking(receiver)){
          addRequestedDeveloper(receiver);
          receiver.assignActivity(this);
      } else {
          throw new OperationNotAllowedException("The developer is already working on the given activity");
      } 
    } else {
      throw new OperationNotAllowedException("The current user is not assigned to the activity by the project leader and cannot request assistance");
    }
  }

  public String getParentProjectNumber() {
    return this.parentProjectNumber;
  }
  
    
}
