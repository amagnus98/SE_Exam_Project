package system.model.domain;

import java.util.*;

public class App {
    
    private Developer currentUser;
    private Company company = new Company(new ArrayList<Developer>());

    public void logIn(String initials) throws OperationNotAllowedException {
        if (this.company.developerExists(initials)) {
            this.setCurrentUser(this.company.getDeveloperFromInitials(initials));
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

    // Setter method for company 
    public void setCompany(Company newCompany) {
        this.company = newCompany;
    }

    // getter method for company 
    public Company getCompany() {
        return this.company;
    }

    public boolean hasCurrentUser() {
        return this.currentUser != null;
    }
    
    public void logOut() {
        this.currentUser = null;
    }
}
