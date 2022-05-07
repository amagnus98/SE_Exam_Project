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

public class SubmitActivityInformationAction extends AbstractAction {

    Main main;
    JTextField activityName;
    Activity activity;
    Project previousProject;

    public SubmitActivityInformationAction(String name, 
    JTextField activityName, 
    Activity activity, 
    Project previousProject,
    Main main) {
        putValue(NAME, name);
        this.main = main;
        this.activityName = activityName;
        this.activity = activity;
        this.previousProject = previousProject;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // ERROR BOOLEAN
        Boolean hasError = false;

        // ACTIVITY NAME CHANGE
        if (!activityName.getText().trim().equals("")) {
            try {
            this.main.app.setActivityName(activityName.getText().trim(),activity, previousProject);
            } catch (OperationNotAllowedException error){
                ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
                errorWindow.showMessage();
                hasError = true;
            } 
            if (!hasError) {
                SuccessWindow successWindow = new SuccessWindow("Changes successfully set.");
                successWindow.showMessage();
                main.viewActivity(activity, previousProject);
            }
        } else {
            ErrorWindow errorWindow = new ErrorWindow("No changes have been made.");
            errorWindow.showMessage();
        }
    }
}