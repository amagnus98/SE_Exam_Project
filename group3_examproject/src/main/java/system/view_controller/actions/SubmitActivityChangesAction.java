package system.view_controller.actions;
import javax.swing.*;

import system.model.domain.Activity;
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;
import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.messageWindows.SuccessWindow;
import system.view_controller.pages.Main;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SubmitActivityChangesAction extends AbstractAction {

    Main main;
    JTextField activityName;
    JTextField activityLeader;
    Activity activity;
    JTextField activityStartYear;
    JTextField activityStartWeek;
    JTextField activityEndYear;
    JTextField activityEndWeek;
    JTextField estimatedWorkHours;
    Project previousProject;

    public SubmitActivityChangesAction(String name, 
    JTextField activityName, 
    JTextField activityStartYear, 
    JTextField activityStartWeek, 
    JTextField activityEndYear,
    JTextField activityEndWeek,
    JTextField estimatedWorkHours,
    Activity activity, 
    Project previousProject,
    Main main) {
        putValue(NAME, name);
        this.main = main;
        this.activityName = activityName;
        this.activity = activity;
        this.activityStartYear = activityStartYear;
        this.activityStartWeek = activityStartWeek;
        this.activityEndYear = activityEndYear;
        this.activityEndWeek = activityEndWeek;
        this.estimatedWorkHours = estimatedWorkHours;
        this.previousProject = previousProject;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // ERROR BOOLEAN
        Boolean hasError = false;

        // PROJECT NAME CHANGE
        activity.setName(activityName.getText());


        Double estimatedWorkHoursDouble = Double.parseDouble(estimatedWorkHours.getText());

        if (!(activity.getEstimatedWorkHours() == estimatedWorkHoursDouble)) {
            try {
                main.app.setEstimatedWorkHoursForActivity(estimatedWorkHoursDouble, activityName.getText(), activity.getParentProjectNumber());
            } catch (OperationNotAllowedException error) {
                ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
                errorWindow.showMessage();
                hasError = true;
            }
        }


        try {
        int startYear = Integer.parseInt(activityStartYear.getText());
        int startWeek = Integer.parseInt(activityStartWeek.getText());
        int endYear = Integer.parseInt(activityEndYear.getText());
        int endWeek = Integer.parseInt(activityEndWeek.getText());
        if (!(startYear == activity.getStartYear()) || !(startWeek == activity.getStartWeek())  || !(endYear == activity.getEndYear())  || !(endWeek == activity.getEndWeek()))  {
            try {
                main.app.setTimeHorizonOfActivity(startYear, startWeek, endYear, endWeek, activityName.getText(), activity.getParentProjectNumber());
            } catch (OperationNotAllowedException error) {
                ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
                errorWindow.showMessage();
                hasError = true;
            }
           }

        } catch (NumberFormatException error) {
            ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
            errorWindow.showMessage();
            hasError = true;
        }

       if (!hasError) {
        SuccessWindow successWindow = new SuccessWindow("Changes successfully set.");
        successWindow.showMessage();
       }
       main.viewActivitry(activity, previousProject);
    }
}