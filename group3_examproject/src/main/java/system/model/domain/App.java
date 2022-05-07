package system.model.domain;

import static org.junit.Assert.assertTrue;

import java.util.*;

public class App {    
    // keeps track of the current user that is logged in
    private Developer currentUser;
    // Add initial list of developers working in the company
    private ArrayList<Developer> developers = new ArrayList<>(Arrays.asList(new Developer("amag"),
                                                                            new Developer("anbi"),
                                                                            new Developer("kape"),
                                                                            new Developer("mari"),
                                                                            new Developer("mani")));
    // project number for the non work activity project
    private String nonWorkActivityProjectNumber = "00001";
    // predefined non work activities
    private ArrayList<String> initialNonWorkActivities = new ArrayList<>(Arrays.asList("Vacation",
                                                                                "Sick day",
                                                                                "Seminar",
                                                                                "Maternity Leave"));
    // initialize project list with a project for non work activities
    private ArrayList<Project> projects = getInitialProjectList();
    
    // variable used to keep track of current year
    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    // used to keep track of the number of projects for each year
    public HashMap<Integer, Integer> projectCount = new HashMap<>();
    // used as a container for the project report
    private ProjectReport currentProjectReport;

    // log in function
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

    // getter method for the current year - also updates the current year variable
    public int getCurrentYear(){
        this.currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return this.currentYear;
    }

    // Get the list of developers
    public ArrayList<Developer> getDevelopers(){
      return this.developers;
    }

    // initializes project list to contain a project for non work activities
    public ArrayList<Project> getInitialProjectList() {
        ArrayList<Project> initialProjectList = new ArrayList<>();
        initialProjectList.add(createNonWorkActivitiesProject());
        return initialProjectList;
    }

    public String getNonWorkActivitiesProjectNumber() {
        return this.nonWorkActivityProjectNumber;
    }

    // create non work activity project
    public Project createNonWorkActivitiesProject(){
        // assign non work activities to a project called "Non work activities"
        Project nonWorkActivitiesProject = new Project(nonWorkActivityProjectNumber,"Non work activities");
        // mark project as non work activity project
        nonWorkActivitiesProject.setAsNonWorkActivityProject();
        // loop over the predefined initial non work activities
        for (String activityName : initialNonWorkActivities){
            Activity nonWorkActivity = new Activity(activityName);
            nonWorkActivitiesProject.addToActivityList(nonWorkActivity);
        }
        return nonWorkActivitiesProject;
    }
    
    // checks that the project list contains a non work activity project
    public boolean hasNonWorkActivityProject() throws OperationNotAllowedException {
        return (projectExists(nonWorkActivityProjectNumber) && getProject(nonWorkActivityProjectNumber).isNonWorkActivityProject());
    }

    // getter method for the non work activity project
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
    
    // increment project count for current year
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

        // Combine current year and project count into an ID number with 3 digits
        return String.format("%02d",this.getCurrentYear() % 100) + String.format("%03d", currentYearProjectCount);
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

    // Checks whether a project with a given project number exist in the list of projects
    public boolean projectExists(String projectNumber){

        // Precondition
        assertTrue(projectNumber != null);
        boolean result = false;

        for (Project p : projects) {
            if (p.getProjectNumber().equals(projectNumber)) {
                result = true;
                break;
            }
        }

        // Postcondition
        for (Project p : projects) {
            if (p.getProjectNumber().equals(projectNumber)){
                assertTrue(result == true);
                return result;
            }
        }
        assertTrue(result == false);
        return result;
    }
    
    // Get a project from the project number
    public Project getProject(String projectNumber) throws OperationNotAllowedException {
        for (Project p : projects) {
            if (p.getProjectNumber().equals(projectNumber)) {
                return p;
            }
        }
        throw new OperationNotAllowedException("Project with given project number does not exist in the system");
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
        // if the developer is not already assigned the project add him/her as well
        if (!(project.isDeveloperAssigned(developer))) {
            project.addDeveloper(developer);
        }
    }

    // Add activity with a name to a project 
    public void addActivityToProject(String activityName, String projectNumber) throws OperationNotAllowedException{
        Activity activity = new Activity(activityName);
        activity.setAssignedProject(this.getProject(projectNumber));
        
        // if current user is project leader add the activity, else throw an error message
        if (currentUserIsProjectLeader(projectNumber)){
            getProject(projectNumber).addActivity(activity);
        } else {
            throw new OperationNotAllowedException("The activity can't be added to the project, because the user is not the project leader");
        }
    }

    public void setActivityName(String activityName, Activity activity, Project project) throws OperationNotAllowedException{
        if (currentUserIsProjectLeader(project.getProjectNumber())){
            if (!project.containsActivity(activityName) || activityName.equals(activity.getName())){
                activity.setName(activityName);
            } else {
            throw new OperationNotAllowedException("Project already contains an activity with the given name");
            }
        } else {
            throw new OperationNotAllowedException("The activity can't be renamed, because the user is not the project leader");
        }
    }

    // time helper functions
    // check that hours format is valid
    public boolean isHoursFormatValid(double hours){
        return (hours > 0 && hours <= 24);
    }

    // check that day format is valid
    public boolean isDayFormatValid(int day) throws OperationNotAllowedException{
        return (day >= 1 && day <= 7);
    }

    // check that week format is valid
    public boolean isWeekFormatValid(int week){
        return (week >= 1 && week <= 52);
    }

    public void setTimeHorizonOfProject(int startYear, int startWeek, int endYear, int endWeek, String projectNumber) throws OperationNotAllowedException{
        // check that the current user is the project leader 
        if (!currentUserIsProjectLeader(projectNumber)){
            throw new OperationNotAllowedException("The start and end time of the project can't be edited, because the user is not the project leader");
        }
        Project project = getProject(projectNumber);
        // check if the weeks are between 1 and 52
        if (!(isWeekFormatValid(startWeek) && isWeekFormatValid(endWeek))){
            throw new OperationNotAllowedException("The weeks for the time horizon of the project must be set to a number between 1 to 52");
        }
        // check that the end time is after the start time
        if (!project.isEndTimeIsAfterStartTime(startYear,startWeek, endYear, endWeek)){
            throw new OperationNotAllowedException("The end time can't occur before the start time");
        }
        // check that the time horizon of all of the activities of the project still lies within the new time horizon
        if (!project.isTimeHorizonValidForAllActivities(startYear,startWeek,endYear,endWeek)){
            throw new OperationNotAllowedException("The new time horizon of the project conflicts with the time horizon of the activities in the project");
        }
        

        // precondition
        assertTrue(currentUserIsProjectLeader(projectNumber));
        assertTrue(isWeekFormatValid(startWeek) && isWeekFormatValid(endWeek));
        assertTrue(project.isEndTimeIsAfterStartTime(startYear,startWeek, endYear, endWeek));
        assertTrue(project.isTimeHorizonValidForAllActivities(startYear,startWeek,endYear,endWeek));


        // set project start and end time
        project.setTimeHorizon(startYear,startWeek,endYear,endWeek);


        // Postcondition
        Project p = getProject(projectNumber);
        assertTrue(p.getProjectNumber().equals(projectNumber));
        assertTrue(p.getStartWeek()== startWeek);
        assertTrue(p.getEndWeek() == endWeek);
        assertTrue(p.getStartYear() == startYear);
        assertTrue(p.getEndYear() == endYear);
    }




    public void setTimeHorizonOfActivity(int startYear, int startWeek, int endYear, int endWeek, String activityName, String projectNumber) throws OperationNotAllowedException{
        // if current user is project leader adds the activity, else throws an errormessage
        if (currentUserIsProjectLeader(projectNumber)){
            Project project = getProject(projectNumber);
            if (project.isTimeHorizonDefined()){
                Activity activity = project.getActivity(activityName);
                // check if end time occurs after start time
                if (isWeekFormatValid(startWeek) && isWeekFormatValid(endWeek)){
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
    // the user has to be project leader
    public void addDeveloperToActivity(String initials, String activityName, String projectNumber) throws OperationNotAllowedException{
        if (currentUserIsProjectLeader(projectNumber)){
            Project project = getProject(projectNumber);
            Activity activity = project.getActivity(activityName);
            Developer developer = this.getDeveloper(initials);

            activity.addDeveloper(developer);

            // check if the developer is already assigned to the project or not
            if (!project.isDeveloperAssigned(developer)){
                addDeveloperToProject(initials, projectNumber);
            }
        } else {
            throw new OperationNotAllowedException("Only the project leader can assign developers to this activity");
        }
    }

    // get the activities that each developer is assigned to in a given week and year
    public HashMap<Developer, HashMap<Project, ArrayList<Activity>>> getCurrentDeveloperActivities(int week, int year) throws OperationNotAllowedException {
        // nested hash map where the outer key is the developer and the inner key is the project
        HashMap<Developer, HashMap<Project, ArrayList<Activity>>> currentDeveloperActivities = new HashMap<Developer, HashMap<Project, ArrayList<Activity>>>();

        for (Developer d : this.developers) {
            HashMap<Project, ArrayList<Activity>> developerActivitiesHashMap = new HashMap<Project, ArrayList<Activity>>();
            ArrayList<Activity> developerActivities = d.getCurrentAssignedActivities(week, year);
            for (Activity activity : developerActivities) {
                Project parentProject;
                
                parentProject = activity.getParentProject();

                // if no previous activities for the developer for the specific project has been registered yet
                if (!developerActivitiesHashMap.containsKey(parentProject)) {
                    developerActivitiesHashMap.put(parentProject, new ArrayList<>());
                }
                developerActivitiesHashMap.get(parentProject).add(activity);
            }
            currentDeveloperActivities.put(d, developerActivitiesHashMap);
        }
        
        return currentDeveloperActivities;

    }

    public void setEstimatedWorkHoursForProject(double workHours, String projectNumber) throws OperationNotAllowedException{
        if (currentUserIsProjectLeader(projectNumber)){
            if (workHours >= 0){
                Project project = getProject(projectNumber);
                project.setEstimatedWorkHours(workHours);
            } else {
                throw new OperationNotAllowedException("The estimated number of hours must be non-negative");
            }            
        } else {
            throw new OperationNotAllowedException("Only the project leader can set the estimated number of work hours to this project");
        }
    }

    public void setEstimatedWorkHoursForActivity(double workHours, String activityName, String projectNumber) throws OperationNotAllowedException{
        if (currentUserIsProjectLeader(projectNumber)){
            if (workHours >= 0){
                Project project = getProject(projectNumber);
                Activity activity = project.getActivity(activityName);

                activity.setEstimatedWorkHours(workHours);
            } else {
                throw new OperationNotAllowedException("The estimated number of hours must be non-negative");
            }       
        } else {
            throw new OperationNotAllowedException("Only the project leader can set the estimated number of work hours to this activity");
        }
    }

    // request assistance for an activity
    public void requestAssistanceForActivity(String initialsReceiver, String activityName, String projectNumber) throws OperationNotAllowedException {
        // get developer that is being requested
        Developer receiver = getDeveloper(initialsReceiver);
        Project project = getProject(projectNumber);
        Activity activity = project.getActivity(activityName);
        // the current user request assistance
        activity.requestAssistance(receiver,currentUser);        
    } 

    // register hours to an activity
    public void registerHoursToActivity(double hours, int day, int week, int year, String projectNumber, String activityName) throws OperationNotAllowedException {
        Project project = getProject(projectNumber);
        Activity activity = project.getActivity(activityName);
        if (!isHoursFormatValid(hours)){
            throw new OperationNotAllowedException("Hours must be more than zero and not greater than 24");
        }
        if (!isDayFormatValid(day)){
            throw new OperationNotAllowedException("Days must be more than zero and not greater than 7");
        }
        if (!isWeekFormatValid(week)){
            throw new OperationNotAllowedException("Weeks must be more than zero and not greater than 52");
        }
        if (!(activity.canRegisterHours(this.currentUser) || project.isNonWorkActivityProject())) {
            throw new OperationNotAllowedException("The user is not assigned the given activity");
        }

        if (!(activity.isTimeHorizonDefined() || project.isNonWorkActivityProject())){
            throw new OperationNotAllowedException("The user cannot register hours to the activity, before its time horizon has been defined");
        }

        // check that the user registers hours within the allowed time horizon of the activity
        // this always returns false if the activity is a non work activity since it doesn't have a time horizon
        if (!(activity.isDateWithinTimeHorizon(year, week) || project.isNonWorkActivityProject())){
            throw new OperationNotAllowedException("The user cannot register hours outside of the time horizon of the activity");
        }

        // Precondition
        assertTrue(project.getActivity(activityName) != null);
        assertTrue(isHoursFormatValid(hours));
        assertTrue(isDayFormatValid(day));
        assertTrue(isWeekFormatValid(week));
        assertTrue(activity.canRegisterHours(this.currentUser) || project.isNonWorkActivityProject());
        assertTrue(activity.isDateWithinTimeHorizon(year, week) || project.isNonWorkActivityProject());
        double x = this.currentUser.getRegisteredHours(day, week, year, projectNumber, activityName);
        double y = project.getTotalHoursRegistered();
        double z = activity.getTotalHoursRegistered();
        assertTrue(x == this.currentUser.getRegisteredHours(day, week, year, projectNumber, activityName));
        assertTrue(y == project.getTotalHoursRegistered());
        assertTrue(z == activity.getTotalHoursRegistered());


        // register hours and update the total hours for both the project and the activity
        double currentlyRegisteredHours = this.currentUser.getRegisteredHours(day, week, year, projectNumber, activityName);
        this.currentUser.registerHours(hours, day, week, year, projectNumber, activityName);
        // if no hours have been registered before, currentlyRegisteredHours=0
        // therefore in order to update properly when a user sets or edits the registered hours
        // it should update as follows
        double newProjectTotalHoursRegistered = project.getTotalHoursRegistered() + hours - currentlyRegisteredHours;
        project.setTotalHoursRegistered(newProjectTotalHoursRegistered);
        double newActivityTotalHoursRegistered = activity.getTotalHoursRegistered() + hours - currentlyRegisteredHours;
        activity.setTotalHoursRegistered(newActivityTotalHoursRegistered);


        // Postcondition
        assertTrue(hours == this.currentUser.getRegisteredHours(day, week, year, projectNumber, activityName));
        assertTrue(y + hours - x == project.getTotalHoursRegistered());
        assertTrue(z + hours - x == activity.getTotalHoursRegistered());
    }

    public void registerHoursToNonWorkActivity(double hours, int day, int week, int year, String activityName) throws OperationNotAllowedException{
        registerHoursToActivity(hours, day, week, year, nonWorkActivityProjectNumber, activityName);
    }

    // use the calendar output variable to see activities with registered hours
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

    

}