package system.view_controller.actions;
import javax.swing.*;

import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.pages.Main;
import java.awt.event.ActionEvent;
import system.model.domain.Project;
import java.util.*;
import system.model.domain.Developer;
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Activity;


// View developer overview action // Responsible - Marcus Nielsen (s204126)
public class ViewDeveloperOverviewAction extends AbstractAction {

    JTextField textField;
    Main main;
    JTextField startWeekTextField;
    JTextField startYearTextField;
    JTextField endWeekTextField;
    JTextField endYearTextField;

    public ViewDeveloperOverviewAction(JTextField startWeekTextField, JTextField startYearTextField, JTextField endWeekTextField, JTextField endYearTextField, Main main) {
        putValue(NAME, "Get Overview");
        this.main = main;
        this.startWeekTextField = startWeekTextField;
        this.startYearTextField = startYearTextField;
        this.endWeekTextField = endWeekTextField;
        this.endYearTextField = endYearTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!startWeekTextField.getText().trim().equals("") && !startYearTextField.getText().trim().equals("") && !endWeekTextField.getText().trim().equals("") && !endYearTextField.getText().trim().equals("")) {
            try {
                int startWeek = Integer.parseInt(startWeekTextField.getText().trim());
                int startYear = Integer.parseInt(startYearTextField.getText().trim());
                int endWeek = Integer.parseInt(endWeekTextField.getText().trim());
                int endYear = Integer.parseInt(endYearTextField.getText().trim());

                try {
                    // nested hash map where the outer key is the developer and the inner key is the project
                    HashMap<Developer, HashMap<Project, ArrayList<Activity>>> currentDeveloperActivitiesInPeriod = new HashMap<Developer, HashMap<Project, ArrayList<Activity>>>();
                    if (!(main.app.isWeekFormatValid(startWeek) && main.app.isWeekFormatValid(endWeek))){
                        throw new OperationNotAllowedException("The weeks for the period must be a number between 1 to 52");
                    }

                    if (!main.app.isEndTimeIsAfterStartTime(startYear, startWeek, endYear, endWeek)){
                        throw new OperationNotAllowedException("The end time must come after the start time");
                    }

                    for (Developer d : main.app.getDevelopers()) {
                        HashMap<Project, ArrayList<Activity>> developerActivitiesHashMap = new HashMap<Project, ArrayList<Activity>>();
                        ArrayList<Activity> developerActivitiesInPeriod = new ArrayList<>();
        
                        for (Activity activity : d.getAssignedActivities()) {
                            // check that the end time of the period comes after the activity start time
                            // and the start time of the period comes before the activity end time
                            if ((endYear > activity.getStartYear() || (endYear == activity.getStartYear() && endWeek >= activity.getStartWeek())) &&
                                (startYear < activity.getEndYear() || (startYear == activity.getEndYear() && startWeek <= activity.getEndWeek()))){
                                    developerActivitiesInPeriod.add(activity);
                            }
                        }
                        
                        for (Activity activity : developerActivitiesInPeriod) {
                            Project parentProject;
                            
                            parentProject = activity.getParentProject();

                            // if no previous activities for the developer for the specific project has been registered yet
                            if (!developerActivitiesHashMap.containsKey(parentProject)) {
                                developerActivitiesHashMap.put(parentProject, new ArrayList<>());
                            }
                            developerActivitiesHashMap.get(parentProject).add(activity);
                        }
                        currentDeveloperActivitiesInPeriod.put(d, developerActivitiesHashMap);
                    }
                    
                    main.viewDeveloperOverview(startWeek, startYear, endWeek, endYear, currentDeveloperActivitiesInPeriod);
                    
                } catch (OperationNotAllowedException error) {
                    ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
                    errorWindow.showMessage();
                }
            } catch (NumberFormatException error) {
                ErrorWindow errorWindow = new ErrorWindow("The start and end time must be written as integers!");
                errorWindow.showMessage();
            }
        } else {
            ErrorWindow errorWindow = new ErrorWindow("Please insert all values.");
            errorWindow.showMessage();
        }
    }
}