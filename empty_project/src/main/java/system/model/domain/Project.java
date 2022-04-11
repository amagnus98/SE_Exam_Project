package system.model.domain;

import java.util.*;

public class Project extends Event{
    String projectNumber;

    public Project(String projectNumber){
        super();
        this.projectNumber = projectNumber;
    }

    public Project(String projectNumber, String name){
        super();
        this.projectNumber = projectNumber;
        this.name = name;
    }

    public String getProjectNumber(){
        return this.projectNumber;
    }
}