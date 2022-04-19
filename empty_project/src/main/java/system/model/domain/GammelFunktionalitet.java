package system.model.domain;

public class GammelFunktionalitet {
    /*
    public void setProjectStartTime(int startYear, int startWeek, String projectNumber) throws OperationNotAllowedException{
        if (currentUserIsProjectLeader(projectNumber)){
            Project project = getProject(projectNumber);
            project.setStartYear(startYear);
            project.setStartWeek(startWeek);
        } else {
            throw new OperationNotAllowedException("The start time of the project can't be edited, because the user is not the project leader");
        }

    }

    public void setProjectEndTime(int endYear, int endWeek, String projectNumber) throws OperationNotAllowedException{
        if (currentUserIsProjectLeader(projectNumber)){
            Project project = getProject(projectNumber);
            // check if end time occurs after start time
            if (project.isEndTimeIsAfterStartTime(endYear, endWeek)){
                project.setEndYear(endYear);
                project.setEndWeek(endWeek);
            } else {
                throw new OperationNotAllowedException("The end time can't occur before the start time");
            }
        } else {
            throw new OperationNotAllowedException("The end time of the project can't be edited, because the user is not the project leader");
        }
    }
    */

    /*
    public void setActivityStartTime(int startYear, int startWeek, String activityName, String projectNumber) throws OperationNotAllowedException{
        // if current user is project leader adds the activity, else throws an errormessage
        if (currentUserIsProjectLeader(projectNumber)){
            Project project = getProject(projectNumber);
            if (project.isDateWithinTimeHorizon(startYear,startWeek)){
                Activity activity = project.getActivity(activityName);
                activity.setStartYear(startYear);
                activity.setStartWeek(startWeek);
            } else {
                throw new OperationNotAllowedException("The start time is not within the time horizon for its assigned project");
            }
        } else {
            throw new OperationNotAllowedException("The start time can't be edited, because the user is not the project leader");
        }    
    }
    
    public void setActivityEndTime(int endYear, int endWeek, String activityName, String projectNumber) throws OperationNotAllowedException{
        // if current user is project leader adds the activity, else throws an errormessage
        if (currentUserIsProjectLeader(projectNumber)){
            Project project = getProject(projectNumber);
            Activity activity = project.getActivity(activityName);
            // check if end time occurs after start time
            if (activity.isEndTimeIsAfterStartTime(2,2,endYear, endWeek)){
                if (project.isDateWithinTimeHorizon(endYear,endWeek)){
                    activity.setEndYear(endYear);
                    activity.setEndWeek(endWeek);
                } else {
                    throw new OperationNotAllowedException("The end time is not within the time horizon for its assigned project");
                }
            } else {
                throw new OperationNotAllowedException("The end time can't occur before the start time");
            }
        } else {
            throw new OperationNotAllowedException("The end time can't be edited, because the user is not the project leader");
        }
    }
    */

}
