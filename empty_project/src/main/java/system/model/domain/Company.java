package system.model.domain;
import java.util.*;


public class Company {

    private ArrayList<Developer> developers;

    // Constructor
    public Company(ArrayList<Developer> developers) {
        this.developers = developers;
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

    public Developer getDeveloperFromInitials(String initials) throws Exception {
        for (Developer d : developers) {
            if (d.getInitials().equals(initials)) {
                return d;
            }
        }
        throw new Exception("developer with given initials does not exist in the system");
    }

    
    // Add a developer to the company
    public void addDeveloper(Developer d) {
        this.developers.add(d);
    }

    // get list of develope
    public ArrayList<Developer> getDevelopers(){
      return this.developers;
    }



}
