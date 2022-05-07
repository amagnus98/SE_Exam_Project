package system.model.domain;
import java.util.*;

public class Activity extends Event{
  
  // Variable used to get the project of the activity
  private Project parentProject;

  // the keys are the initials of the developers
  // the values are booleans representing whether they were added by the project leader or not
  // only developers assigned by project leaders can request assistance
  private HashMap<String,Boolean> isAssignedByProjectLeader = new HashMap<>();

  // constructor to create a named activity
  public Activity (String name){
      // run parent class (Event) to basic functionality
      super();
      // set name of activity
      this.name = name;
  }

  public Project getParentProject() {
    return this.parentProject;
  }

  public void setAssignedProject(Project project){
    this.parentProject = project;
}



  // for developers added to the activity by a project leader
  public void addAssignedDeveloper(Developer d){
      this.assignedDevelopers.add(d);
      this.isAssignedByProjectLeader.put(d.getInitials(),true);
      d.assignActivity(this);
  }

  // for developers requested to the activity by other assigned developers
  public void addRequestedDeveloper(Developer d){
    this.assignedDevelopers.add(d);
    this.isAssignedByProjectLeader.put(d.getInitials(),false);
  }


  // add developer to activity
  public void addDeveloper(Developer d) throws OperationNotAllowedException {
      // check if the developer has already been added to the activity by the project leader
      if (!isDeveloperAssignedByProjectLeader(d)){
        // check if the developer has already been requested to the project
        if (isDeveloperAssigned(d)) {
          changeDeveloperFromRequestedToAssigned(d);
        } else {
          addAssignedDeveloper(d);
        }
      } else {
        throw new OperationNotAllowedException("The developer is already assigned to the given activity by the project leader");
      }
  }

  // change developer status from requested to assigned
  public void changeDeveloperFromRequestedToAssigned(Developer d){
    this.isAssignedByProjectLeader.put(d.getInitials(),true);
  }
  
  // check if the current developer has been assigned to the activity by a project leader
  public boolean isDeveloperAssignedByProjectLeader(Developer d){
    // first check if is currently assigned to the project either by the project leader or by request
    if (this.isDeveloperAssigned(d)){
      return this.isAssignedByProjectLeader.get(d.getInitials());
    }
    return false;
  }

  // check if the current developer has been requested for an activity
  public boolean isDeveloperRequested(Developer d){
    // first check if is currently assigned to the project either by the project leader or by request
    if (this.isDeveloperAssigned(d)){
      return !this.isAssignedByProjectLeader.get(d.getInitials());
    }
    return false;
  }

  // used to check if the user can register hours to the activity
  // only allowed if the developer is assigned to the project either
  // by the project leader or by request
  public boolean canRegisterHours(Developer d) {
    return this.isDeveloperAssigned(d);
  }

  // used for requesting assistance for activity
  public void requestAssistance(Developer receiver, Developer sender) throws OperationNotAllowedException {
    // check if the sender (the one requesting assistance) has been assigned
    // to the activity by the project leader
    if (isDeveloperAssignedByProjectLeader(sender)){
      // check that the receiver (the one who is being requested) is not already assigned to the project
      if (!isDeveloperAssigned(receiver)){
          // add the developer as a requested developer
          addRequestedDeveloper(receiver);
          receiver.assignActivity(this);
      } else {
          throw new OperationNotAllowedException("The developer is already working on the given activity");
      } 
    } else {
      throw new OperationNotAllowedException("The current user is not assigned to the activity by the project leader and cannot request assistance");
    }
  }
  
    
}
