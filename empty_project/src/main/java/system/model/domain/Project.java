package system.model.domain;

import java.util.*;

public class Project extends Event{
    String projectID;

    public Project(String projectID){
        super();
        this.projectID = projectID;
    }
}