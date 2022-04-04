package system.model.domain;

import java.util.*;



public abstract class Event {
    protected String name;
    protected int startYear;
    protected int startWeek;
    protected int endYear;
    protected int endWeek;
    protected List<Developer> currentlyWorking; 
    protected float totalHoursWorked;

    
    public Event(String name, int startYear, int startWeek, int endYear, int endWeek, List currentlyWorking, float totalHoursWorked){
        this.name = name;
        this.startYear = startYear;
        this.startWeek = startWeek;
        this.endYear = endYear;
        this.endWeek = endWeek;
        this.currentlyWorking = currentlyWorking;
        this.totalHoursWorked = totalHoursWorked;
    }
    public Event() {
        
    }

    public void setTimeline(int startY, int startW, int endY, int endW){

    }
}