package system.model.domain;

import java.util.*;


public abstract class Event {
    protected String name = "Unnamed Project";
    protected int startYear;
    protected int startWeek;
    protected int endYear;
    protected int endWeek;
    protected List<Developer> currentlyWorking = new ArrayList<Developer>(); 
    protected float totalHoursWorked;

    
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
    public void setStartWeek(int Week) {
        this.startWeek = Week;
    }
    public void setEndWeek(int Week) {
        this.endWeek = Week;
    }
    public void setTotalHoursWorked(float totalHoursWorked){
        this.totalHoursWorked = totalHoursWorked;
    }
    

    public void setTimeline(int startY, int startW, int endY, int endW){
        if (this.isTimeValid(startY, startW, endY, endW)) {
            this.setStartYear(startY);
            this.setStartWeek(startW);
            this.setEndWeek(endW);
            this.setEndYear(endY);
        }
    }

    public void addDeveloper(Developer developer){
        currentlyWorking.add(developer);
    }

    public boolean isDeveloperOnEvent(Developer developer){
        return currentlyWorking.contains(developer);
    }

    public boolean isTimeValid(int startY, int startW, int endY, int endW) {
        return ((startY < endY) || (startY == endY && startW <= endW));
    }
}