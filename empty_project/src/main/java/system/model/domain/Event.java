package system.model.domain;

import java.util.*;


public abstract class Event {
    protected String name = "Unnamed";
    protected int startYear;
    protected int startWeek;
    protected int endYear;
    protected int endWeek;
    protected float totalHoursWorked;
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

    public void setTotalHoursWorked(float totalHoursWorked){
        this.totalHoursWorked = totalHoursWorked;
    }

    public boolean endTimeIsValid(int endYear, int endWeek) {
        return (getStartYear() < endYear || (getStartYear() == endYear && getStartWeek() <= endWeek));
    }  
}