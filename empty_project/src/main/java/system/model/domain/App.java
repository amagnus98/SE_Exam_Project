package system.model.domain;

import java.util.*;

public class App {    

    private Developer currentUser;
    // Add initial list of developers working in the company
    private ArrayList<Developer> developers = new ArrayList<>(Arrays.asList(new Developer("bond"),
                                                                            new Developer("anbi"),
                                                                            new Developer("kape"),
                                                                            new Developer("mari"),
                                                                            new Developer("mani")));
    private ArrayList<Project> projects = new ArrayList<Project>();
    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    public HashMap<Integer, Integer> projectCount = new HashMap<>();
    private DateServer dateServer = new DateServer();

    public void logIn(String initials) throws OperationNotAllowedException {
        if (this.developerExists(initials)) {
            this.setCurrentUser(this.getDeveloper(initials));
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

    // Checks whether there is a current user or not
    public boolean hasCurrentUser() {
        return this.currentUser != null;
    }
    
    // Removes the current user
    public void logOut() {
        this.currentUser = null;
    }

    // Checks whether a developer defined by a set of initials is in the company
    public boolean developerExists(String initials) {
        for (Developer d : this.developers){
            if (d.getInitials().equals(initials)){
                return true;
            }
        }
        return false;
    }
    
    // Return the developer object corresponding to given initials
    public Developer getDeveloper(String initials) throws OperationNotAllowedException {
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

    public int getCurrentYear(){
        return this.currentYear;
    }

    // Get a list of developers
    public ArrayList<Developer> getDevelopers(){
      return this.developers;
    }

    // Add project with no name
    public void addProject(){
       
        // Sets the current year
        this.currentYear = Calendar.getInstance().get(Calendar.YEAR);
        incrementProjectCount();
        
        // Get unique id for project and create it
        String projectNumber = getProjectNumber();
        Project newProject = new Project(projectNumber);

        // Add the project to the list of projects and update the counter of projects for that year
        this.projects.add(newProject);
    }

    // Add a new project to the list of projects with a name
    public void addProject(String name){

        // Sets the current year
        this.currentYear = Calendar.getInstance().get(Calendar.YEAR);
        incrementProjectCount();

        // Get unique id for project and create it
        String projectNumber = getProjectNumber();
        Project newProject = new Project(projectNumber, name);
        
        // Add the project to the list of projects and update the counter of projects for that year
        this.projects.add(newProject);
    }

    public void incrementProjectCount(){
        this.projectCount.put(this.currentYear, getProjectCount()+1);
    }

    // Returns the number of projects already created in a given year
    public int getProjectCount() {
        
        // If projects are present in the current year
        if (this.projectCount.containsKey(this.currentYear)) {
            return this.projectCount.get(this.currentYear);
        } 
        
        // If no projects are present in the current year. Initialize count to one
        else {
            return 0;
        }
    }

    // Creates a unique project id number from the current year and the project count in that year
    public String getProjectNumber() {

        // Retrieve the project count in the given year
        int count  = this.getProjectCount();

        // Combine current year and project count into an ID number
        return this.currentYear % 100 + String.format("%03d", count);
    }

    // Returns the list of projects
    public ArrayList<Project> getProjects(){
        return this.projects;
    }

    // Checks whether a project with a given project number exixst in the list of projects
    public boolean projectExists(String projectNumber){
        System.out.println(projectNumber);
        for (Project p : projects) {
            if (p.getProjectNumber().equals(projectNumber)) {
                return true;
            }
        }
        return false;
    }
    
    // Get a project from the projects number
    public Project getProject(String projectNumber) throws OperationNotAllowedException {
        for (Project p : projects) {
            if (p.getProjectNumber().equals(projectNumber)) {
                return p;
            }
        }
        throw new OperationNotAllowedException("Project with given project number does not exist in the system");
    }

    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }
}
