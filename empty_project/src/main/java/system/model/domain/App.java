package system.model.domain;

public class App {
    
    private Developer currentUser;
    private Company company;

    public void logInAsUser(String initials) throws Exception {
        //System.out.println(this.company.developerExists(initials));
        //for (Developer d : this.company.getDevelopers()){
        //    System.out.println(d.getInitials());
        //}
        if (this.company.developerExists(initials)) {
            this.setCurrentUser(this.company.getDeveloperFromInitials(initials));
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

    

}
