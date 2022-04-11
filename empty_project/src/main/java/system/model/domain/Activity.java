package system.model.domain;

public class Activity extends Event{
  
  private String parentProjectNumber;
  
  public Activity (String name){
      super();
      this.name = name;
  }

  public void setAssignedProject(String projectNumber){
      this.parentProjectNumber = projectNumber;
  }
  
}
