package system.model.domain;

import java.lang.reflect.Array;
import java.util.*;

import io.cucumber.java.en_old.Ac;

public class App {    

    private Developer currentUser;
    // Add initial list of developers working in the company
    private ArrayList<Developer> developers = new ArrayList<>(Arrays.asList(new Developer("bond"),
                                                                            new Developer("anbi"),
                                                                            new Developer("kape"),
                                                                            new Developer("mari"),
                                                                            new Developer("mani")));
    private String nonWorkActivityProjectNumber = "00001";
    // predefined non work activities
    private ArrayList<String> nonWorkActivities = new ArrayList<>(Arrays.asList("Vacation",
                                                                                "Sickday",
                                                                                "Seminar",
                                                                                "Maternity Leave"));
    // initialize project list with a project for non work activities
    private ArrayList<Project> projects = getInitialProjectList();
    
    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    public HashMap<Integer, Integer> projectCount = new HashMap<>();
    private DateServer dateServer = new DateServer();
    private ProjectReport currentProjectReport;

    public String getnonWorkActivitiesProjectNumber() {
        return this.nonWorkActivityProjectNumber;
    }

    public ArrayList<String> getNonWorkActivities() {
        return this.nonWorkActivities;
    }

    public void logIn(String initials) throws OperationNotAllowedException {
        Developer d = getDeveloper(initials);
        setCurrentUser(d);
    }

    // Setter method for currentUser 
    private void setCurrentUser(Developer newCurrentUser) {
        this.currentUser = newCurrentUser;
    }
    
    // getter method for currentUser 
    public Developer getCurrentUser() {
        return this.currentUser;
    }

    // Checks whether there is a current user or not
    public boolean hasCurrentUser() {
        return this.currentUser != null;
    }
    
    // Removes the current user
    public void logOut() {
        this.currentUser = null;
    }

    // Checks whether a developer defined by a set of initials is in the company
    public boolean developerExists(String initials) {
        for (Developer d : this.developers){
            if (d.getInitials().equals(initials)){
                return true;
            }
        }
        return false;
    }
    
    // Return the developer object corresponding to given initials
    public Developer getDeveloper(String initials) throws OperationNotAllowedException {
        for (Developer d : developers) {
            if (d.getInitials().equals(initials.toLowerCase())) {
                return d;
            }
        }
        throw new OperationNotAllowedException("No developer with the given initials exists in the system");
    }

    // Add a developer to the company
    public void addDeveloperToCompany(Developer d) {
        this.developers.add(d);
    }

    public int getCurrentYear(){
        this.currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return this.currentYear;
    }

    // Get a list of developers
    public ArrayList<Developer> getDevelopers(){
      return this.developers;
    }

    // initializes project list to contain a project for non work activities
    public ArrayList<Project> getInitialProjectList() {
        ArrayList<Project> initialProjectList = new ArrayList<>();
        initialProjectList.add(createNonWorkActivitiesProject());
        return initialProjectList;
    }

    public Project createNonWorkActivitiesProject(){
        // assign non work activities to project number "00001"
        Project nonWorkActivitiesProject = new Project(nonWorkActivityProjectNumber,"Non work activities");
        // mark project as non work activity project
        nonWorkActivitiesProject.setAsNonWorkActivityProject();
        for (String activityName : nonWorkActivities){
            Activity nonWorkActivity = new Activity(activityName);
            nonWorkActivitiesProject.addToActivityList(nonWorkActivity);
        }
        return nonWorkActivitiesProject;
    }

    public boolean hasNonWorkActivityProject() throws OperationNotAllowedException {
        return (projectExists(nonWorkActivityProjectNumber) && getProject(nonWorkActivityProjectNumber).isNonWorkActivityProject());
    }

    public Project getNonWorkActivityProject() throws OperationNotAllowedException {
        return getProject(nonWorkActivityProjectNumber);
    }

    // Add project with no name
    public void addProject(){
        // Increment project count for current year
        incrementProjectCount();
        
        // Get unique id for project and create it
        String projectNumber = getProjectNumber();
        Project newProject = new Project(projectNumber);

        // Add the project to the list of projects and update the counter of projects for that year
        this.projects.add(newProject);
    }

    // Add a new project to the list of projects with a name
    public void addProject(String name){
        // update the counter of projects for the current year
        incrementProjectCount();

        // Get unique id for project and create it
        String projectNumber = getProjectNumber();
        Project newProject = new Project(projectNumber, name);
        
        // Add the project to the list of projects
        this.projects.add(newProject);
    }

    public void incrementProjectCount(){
        this.projectCount.put(this.getCurrentYear(), getProjectCountForCurrentYear()+1);
    }

    // Returns the number of projects already created in a given year
    public int getProjectCountForCurrentYear() {
        
        // If projects are present in the current year
        if (this.projectCount.containsKey(this.getCurrentYear())) {
            return this.projectCount.get(this.getCurrentYear());
        } 
        
        // If no projects are present in the current year. Initialize count to zero
        else {
            return 0;
        }
    }

    // Creates a unique project id number from the current year and the project count in that year
    public String getProjectNumber() {

        // Retrieve the project count in the given year
        int currentYearProjectCount  = this.getProjectCountForCurrentYear();

        // Combine current year and project count into an ID number
        return this.getCurrentYear() % 100 + String.format("%03d", currentYearProjectCount);
    }

    // Returns the list of projects
    public ArrayList<Project> getProjects(){
        return this.projects;
    }

    public Project getMostRecentProject() throws OperationNotAllowedException{
        if (this.projects.size() > 0) {
            return this.projects.get(this.projects.size()-1);
        }
        throw new OperationNotAllowedException("Project with given project number does not exist in the system");
    }

    // Checks whether a project with a given project number exixst in the list of projects
    public boolean projectExists(String projectNumber){
        for (Project p : projects) {
            if (p.getProjectNumber().equals(projectNumber)) {
                return true;
            }
        }
        return false;
    }
    
    // Get a project from the projects number
    public Project getProject(String projectNumber) throws OperationNotAllowedException {
        for (Project p : projects) {
            if (p.getProjectNumber().equals(projectNumber)) {
                return p;
            }
        }
        throw new OperationNotAllowedException("Project with given project number does not exist in the system");
    }

    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }

    // Checks whether the current user is the project leader of a given project
    public boolean currentUserIsProjectLeader(String projectNumber) throws OperationNotAllowedException{
        if (!getProject(projectNumber).hasProjectLeader()) {
            return false;
        }
        return getProject(projectNumber).getProjectLeader().getInitials().equals(this.currentUser.getInitials());
    }

    // Assign a developer as project leader on a project
    public void assignProjectLeader(String projectNumber,String initials) throws OperationNotAllowedException{
        Developer developer = getDeveloper(initials);
        Project project = getProject(projectNumber);
        project.setProjectLeader(developer);
        // add developer to project
        if (!(project.isDeveloperAssigned(initials))) {
            project.addDeveloper(developer);
        }
    }

    // Add activity with a name to a project 
    public void addActivityToProject(String activityName, String projectNumber) throws OperationNotAllowedException{
        Activity activity = new Activity(activityName);
        activity.setAssignedProject(projectNumber);
        
        // if current user is project leader adds the activity, else throws an errormessage
        if (currentUserIsProjectLeader(projectNumber)){
            getProject(projectNumber).addActivity(activity);
        } else {
            throw new OperationNotAllowedException("The activity can't be added to the project, because the user is not the project leader");
        }
    }

    public void setTimeHorizonOfProject(int startYear, int startWeek, int endYear, int endWeek, String projectNumber) throws OperationNotAllowedException{
        if (currentUserIsProjectLeader(projectNumber)){
            Project project = getProject(projectNumber);
            // check if the weeks are between 1 and 52
            if (project.isWeekFormatValid(startWeek,endWeek)){
                if (project.isEndTimeIsAfterStartTime(startYear,startWeek, endYear, endWeek)){
                    // check that the time horizon of all of the activities still lies within the new time horizon
                    if (project.isTimeHorizonValidForAllActivities(startYear,startWeek,endYear,endWeek)){
                        // set project start and end time
                        project.setTimeHorizon(startYear,startWeek,endYear,endWeek);
                    } else {
                        throw new OperationNotAllowedException("The new time horizon of the project conflicts with the time horizon of the activities in the project");
                    }
                } else {
                    throw new OperationNotAllowedException("The end time can't occur before the start time");
                }
            } else {
                throw new OperationNotAllowedException("The weeks for the time horizon of the project must be set to a number between 1 to 52");
            }
        } else {
            throw new OperationNotAllowedException("The start and end time of the project can't be edited, because the user is not the project leader");
        }
    }

    public void setTimeHorizonOfActivity(int startYear, int startWeek, int endYear, int endWeek, String activityName, String projectNumber) throws OperationNotAllowedException{
        // if current user is project leader adds the activity, else throws an errormessage
        if (currentUserIsProjectLeader(projectNumber)){
            Project project = getProject(projectNumber);
            if (project.isTimeHorizonDefined()){
                Activity activity = project.getActivity(activityName);
                // check if end time occurs after start time
                if (activity.isWeekFormatValid(startWeek, endWeek)){
                    if (activity.isEndTimeIsAfterStartTime(startYear,startWeek,endYear, endWeek)){
                        // check if both the start and end time are within the time horizon of the project
                        if (project.isDateWithinTimeHorizon(startYear,startWeek) && project.isDateWithinTimeHorizon(endYear, endWeek)){
                            activity.setStartYear(startYear);
                            activity.setStartWeek(startWeek);
                            activity.setEndYear(endYear);
                            activity.setEndWeek(endWeek);
                        } else {
                            throw new OperationNotAllowedException("The given time horizon for the activity is not within the time horizon of its assigned project");
                        }
                    } else {
                        throw new OperationNotAllowedException("The end time can't occur before the start time");
                    }
                } else {
                    throw new OperationNotAllowedException("The weeks for the time horizon of the activity must be set to a number between 1 to 52");
                }
            } else {
                throw new OperationNotAllowedException("The start and end time for the activity cannot be set until the time horizon of its assigned project has been defined");
            }
            
        } else {
            throw new OperationNotAllowedException("The start and end time of the activity can't be edited, because the user is not the project leader");
        }
    }

    // add developer to project
    public void addDeveloperToProject(String initials, String projectNumber) throws OperationNotAllowedException {
        Developer developer = getDeveloper(initials);
        Project project = getProject(projectNumber);

        project.addDeveloper(developer);
    }

    // Adds a developer to the list of currently working developers on an activity
    public void addDeveloperToActivity(String initials, String activityName, String projectNumber) throws OperationNotAllowedException{
        if (currentUserIsProjectLeader(projectNumber)){
            Project project = getProject(projectNumber);
            Activity activity = project.getActivity(activityName);
            Developer developer = this.getDeveloper(initials);

            if (activity.isDeveloperAssigned(developer) && !activity.isDeveloperAssignedByProjectLeader(developer)) {
                activity.changeDeveloperFromRequestedToAssisgned(developer);
            } else {
                // check if the developer is assigned to the project or not
                activity.addDeveloper(developer);
            }
            if (!project.isDeveloperAssigned(initials)){
                addDeveloperToProject(initials, projectNumber);
            }
        } else {
            throw new OperationNotAllowedException("Only the project leader can assign developers to this activity");
        }
    }

    public void setEstimatedWorkHoursForActivity(double workHours, String activityName, String projectNumber) throws OperationNotAllowedException{
        if (currentUserIsProjectLeader(projectNumber)){
            Project project = getProject(projectNumber);
            Activity activity = project.getActivity(activityName);

            activity.setEstimatedWorkHours(workHours);
        } else {
            throw new OperationNotAllowedException("Only the project leader can set the estimated number of work hours to this activity");
        }
    }

     public void setEstimatedWorkHoursForProject(double workHours, String projectNumber) throws OperationNotAllowedException{
        if (currentUserIsProjectLeader(projectNumber)){
            Project project = getProject(projectNumber);
            project.setEstimatedWorkHours(workHours);
        } else {
            throw new OperationNotAllowedException("Only the project leader can set the estimated number of work hours to this project");
        }
    }

    // request assistance for activity
    public void requestAssistanceForActivity(String initialsReceiver, String activityName, String projectNumber) throws OperationNotAllowedException {
        Developer receiver = getDeveloper(initialsReceiver);
        Project project = getProject(projectNumber);
        Activity activity = project.getActivity(activityName);
        activity.requestAssistance(receiver,currentUser);        
    } 

    public void registerHoursToActivity(double hours, int day, int week, int year, String projectNumber, String activityName) throws OperationNotAllowedException {
        Project project = getProject(projectNumber);
        Activity activity = project.getActivity(activityName);
        if (activity.canRegisterHours(this.currentUser) || project.isNonWorkActivityProject()) {
            if (hours >= 0){
                if (hours <= 24){
                    if (activity.isRegisterWeekFormatValid(week)) {
                        if (activity.isRegisterDayFormatValid(day)) {
                            if (activity.isDateWithinTimeHorizon(year, week) || project.isNonWorkActivityProject()){
                                double currentlyRegisteredHours = this.currentUser.getRegisteredHours(day, week, year, projectNumber, activityName);
                                this.currentUser.registerHours(hours, day, week, year, projectNumber, activityName);
                                project.setTotalHoursRegistered(project.getTotalHoursRegistered() + hours - currentlyRegisteredHours);
                                activity.setTotalHoursRegistered(activity.getTotalHoursRegistered() + hours - currentlyRegisteredHours);
                            } else {
                                throw new OperationNotAllowedException("The user cannot register hours outside of the time horizon of the activity");
                            } 
                        } else {
                            throw new OperationNotAllowedException("Day must be a number between 1 and 7");
                        }
                    } else {
                        throw new OperationNotAllowedException("Week must be a number between 1 and 52");
                    }
                } else {
                    throw new OperationNotAllowedException("The user cannot register more than 24 hours for an activity per day");
                }
            } else {
                throw new OperationNotAllowedException("The user cannot register negative or zero hours");
            }
        } else {
            throw new OperationNotAllowedException("The user is not assigned the given activity");
        }
    }

    public void registerHoursToNonWorkActivity(double hours, int day, int week, int year, String activityName) throws OperationNotAllowedException{
        registerHoursToActivity(hours, day, week, year, nonWorkActivityProjectNumber, activityName);
    }

    public boolean isNonWorkActivity(String activity){
        return nonWorkActivities.contains(activity);
    }

    public void viewActivitiesWithRegisteredHours(int day, int week, int year) throws OperationNotAllowedException{
        this.currentUser.setCalendarOutput(day, week, year);
    }

    public void generateProjectReport(String projectNumber) throws OperationNotAllowedException{
        if (currentUserIsProjectLeader(projectNumber)) {
        Project project = getProject(projectNumber);
        this.currentProjectReport = new ProjectReport(project); }
        else {
            throw new OperationNotAllowedException("Only the project leader can access the project report");
        }

    }

    public ProjectReport getCurrentProjectReport() throws OperationNotAllowedException{
        if (hasCurrentProjectReport()){
            return this.currentProjectReport;
        } else {
            throw new OperationNotAllowedException("No project report has been requested");
        }   
    }

    public boolean hasCurrentProjectReport(){
        return (!(this.currentProjectReport == null));
    }

    public HashMap<Developer, HashMap<Project, ArrayList<Activity>>> getCurrentDeveloperActivities(int week, int year) throws OperationNotAllowedException {
        
        HashMap<Developer, HashMap<Project, ArrayList<Activity>>> currentDeveloperActivities = new HashMap<Developer, HashMap<Project, ArrayList<Activity>>>();

        for (Developer d : this.developers) {
            HashMap<Project, ArrayList<Activity>> developerActivitiesHashMap = new HashMap<Project, ArrayList<Activity>>();
            ArrayList<Activity> developerActivities = d.getCurrentAssignedActivities(week, year);
            for (Activity activity : developerActivities) {
                Project parentProject;
                
                parentProject = this.getProject(activity.getParentProjectNumber());

                if (!developerActivitiesHashMap.containsKey(parentProject)) {
                    developerActivitiesHashMap.put(parentProject, new ArrayList<>());
                }
                developerActivitiesHashMap.get(parentProject).add(activity);
            }
            currentDeveloperActivities.put(d, developerActivitiesHashMap);
        }
        
        return currentDeveloperActivities;

    }

}