package system.model.domain;
import java.util.*;


public class Company {

    private ArrayList<Developer> developers;
    private ArrayList<Project> projects;
    private int currentYear;
    private Hashtable<Integer, Integer> projectCount = new Hashtable<Integer, Integer>();

    // Constructor
    public Company(ArrayList<Developer> developers, ArrayList<Project> projects) {
        this.developers = developers;
        this.projects = projects;
    }
    
    // Check if a user is working in the company
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


    // add a new project to the list of projects
    public void addProject(Project project){
        this.projects.add(project);
    }

    private int getProjectCount() {
        if (this.projectCount.containsKey(this.currentYear)) {
            this.projectCount.put(this.currentYear,this.projectCount.get(this.currentYear)+1);
            return this.projectCount.get(this.currentYear);
        } else {
            this.projectCount.put(this.currentYear,0);
            return this.getProjectCount();
        }
    }

    public String getProjectID() {
        int count  = this.getProjectCount();

        return this.currentYear + String.format("%03d", count);
    }

}
