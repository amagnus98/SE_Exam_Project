package system.model.domain;

import java.util.*;


public abstract class Event {
    protected String name = "Unnamed";
    // initialized to zero to test whether they have been defined before or not
    // if the value is 0 they have not been set yet
    protected int startYear = 0;
    protected int startWeek = 0;
    protected int endYear = 0;
    protected int endWeek = 0;
    protected double totalHoursWorked;
    protected ArrayList<Developer> assignedDevelopers = new ArrayList<>();

    
    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setStartYear(int year) {
        this.startYear = year;
    }
    public void setEndYear(int year) {
        this.endYear = year;
    }
    public void setStartWeek(int week) {
        this.startWeek = week;
    }
    public void setEndWeek(int week) {
        this.endWeek = week;
    }

    public int getStartYear() {
        return this.startYear;
    }    
    public int getEndYear() {
        return this.endYear;
    }
    public int getStartWeek() {
        return this.startWeek;
    }
    public int getEndWeek() {
        return this.endWeek;
    }

    public void setTimeHorizon(int startYear, int startWeek, int endYear, int endWeek){
        this.setStartYear(startYear);
        this.setStartWeek(startWeek);
        this.setEndYear(endYear);
        this.setEndWeek(endWeek);
    }

    public void setTotalHoursWorked(double totalHoursWorked){
        this.totalHoursWorked = totalHoursWorked;
    }

    public double getTotalHoursWorked(){
        return this.totalHoursWorked;
    }

    public boolean isEndTimeIsAfterStartTime(int startYear, int startWeek, int endYear, int endWeek) {
        return (startYear < endYear || (startYear == endYear && startWeek <= endWeek));
    }

    public boolean isDateWithinTimeHorizon(int year, int week){
        int eventStartYear = this.getStartYear();
        int eventStartWeek = this.getStartWeek();
        int eventEndYear = this.getEndYear();
        int eventEndWeek = this.getEndWeek();

        if (year > eventStartYear || (year == eventStartYear && week >= eventStartWeek)){
            if (year < eventEndYear || (year == eventEndYear && week <= eventEndWeek)){
                return true;
            }
        }
        return false;
    }

    public boolean isTimeHorizonDefined(){
        // if their value is zero it means they are uninitialized
        return (this.getStartYear() != 0 && this.getStartWeek() != 0 && this.getEndYear() != 0 && this.getEndWeek() != 0);
    }

}