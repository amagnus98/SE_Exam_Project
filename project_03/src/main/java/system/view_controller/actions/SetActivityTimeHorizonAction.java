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

// Set activity time horizon action // Responsible - Asbjørn Magnussen (s183546)
public class SetActivityTimeHorizonAction extends AbstractAction {

    Main main;
    JTextField activityName;
    Activity activity;
    JTextField activityStartYear;
    JTextField activityStartWeek;
    JTextField activityEndYear;
    JTextField activityEndWeek;
    JTextField estimatedWorkHours;
    Project previousProject;

    public SetActivityTimeHorizonAction(String name, 
    JTextField activityName, 
    JTextField activityStartYear, 
    JTextField activityStartWeek, 
    JTextField activityEndYear,
    JTextField activityEndWeek,
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
        this.previousProject = previousProject;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // ERROR BOOLEAN
        Boolean hasError = false;

        if (!activityStartYear.getText().trim().equals("") && !activityStartWeek.getText().trim().equals("") && !activityEndYear.getText().trim().equals("") && !activityEndWeek.getText().trim().equals("")) {
            try {
            int startYear = Integer.parseInt(activityStartYear.getText().trim());
            int startWeek = Integer.parseInt(activityStartWeek.getText().trim());
            int endYear = Integer.parseInt(activityEndYear.getText().trim());
            int endWeek = Integer.parseInt(activityEndWeek.getText().trim());
        
            try {
                main.app.setTimeHorizonOfActivity(startYear, startWeek, endYear, endWeek, activity.getName(), activity.getParentProject().getProjectNumber());
            } catch (OperationNotAllowedException error) {
                ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
                errorWindow.showMessage();
                hasError = true;
            }
            } catch (NumberFormatException error) {
                ErrorWindow errorWindow = new ErrorWindow("The start and end time must be written as integers!");
                errorWindow.showMessage();
                hasError = true;
            }

            if (!hasError) {
                SuccessWindow successWindow = new SuccessWindow("Changes successfully set.");
                successWindow.showMessage();
                main.viewActivity(activity, previousProject);
            }
        } else {
            ErrorWindow errorWindow = new ErrorWindow("Please define all values!");
            errorWindow.showMessage();
        }
    }
}