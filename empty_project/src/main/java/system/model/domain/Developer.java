package system.model.domain;

public class Developer {
    
    private String initials;
    //private Calender calender;

    // Constructor of Developer
    public Developer(String initials) {
        this.initials = initials;
    }

    // Setter method for initials 
    public void setInitials(String initials) {
        this.initials = initials;
    }

    // Getter method for initials 
    public String getInitials() {
        return this.initials;
    }
}
