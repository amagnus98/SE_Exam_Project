package system.model.domain;

import java.util.*;

public class App {    

    private Developer currentUser;
    private ArrayList<Developer> developers;
    private ArrayList<Project> projects;
    private int currentYear;
    private Hashtable<Integer, Integer> projectCount = new Hashtable<Integer, Integer>();

    private DateServer dateServer = new DateServer();

    public void logIn(String initials) throws OperationNotAllowedException {
        if (this.developerExists(initials)) {
            this.setCurrentUser(this.getDeveloperFromInitials(initials));
        } else {
            throw new OperationNotAllowedException("Developer with given initials does not exist in the system");
        }
    }

    // Setter method for currentUser 
    private void setCurrentUser(Developer newCurrentUser) {
        this.currentUser = newCurrentUser;
    }
    
    // getter method for currentUser 
    public Developer getCurrentUser() {
        return this.currentUser;
    }

    public boolean hasCurrentUser() {
        return this.currentUser != null;
    }
    
    public void logOut() {
        this.currentUser = null;
    }

    public boolean developerExists(String initials) {
        for (Developer d : this.developers){
            if (d.getInitials().equals(initials)){
                return true;
            }
        }
        return false;
    }
    
    public Developer getDeveloperFromInitials(String initials) throws OperationNotAllowedException {
        for (Developer d : developers) {
            if (d.getInitials().equals(initials)) {
                return d;
            }
        }
        throw new OperationNotAllowedException("Developer with given initials does not exist in the system");
    }

    // Add a developer to the company
    public void addDeveloper(Developer d) {
        this.developers.add(d);
    }

    // get list of develope
    public ArrayList<Developer> getDevelopers(){
      return this.developers;
    }

    // create and add a new project to the list of projects
    public void addProject(Project project){
        
        String projectID = getProjectID();

        Project newProject = new Project(projectID);
        
        this.projects.add(newProject);
    }

    // Returns the number of projects already created in a given year
    private int getProjectCount() {
        if (this.projectCount.containsKey(this.currentYear)) {
            this.projectCount.put(this.currentYear,this.projectCount.get(this.currentYear)+1);
            return this.projectCount.get(this.currentYear);
        } else {
            this.projectCount.put(this.currentYear,0);
            return this.getProjectCount();
        }
    }

    // Creates a unique project id number from the current year and the project count in that year
    public String getProjectID() {
        int count  = this.getProjectCount();

        return this.currentYear + String.format("%03d", count);
    }

    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }
}
