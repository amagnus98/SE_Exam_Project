package system.view_controller.actions;
import javax.swing.*;

import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;
import system.model.domain.Activity;
import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.messageWindows.SuccessWindow;
import system.view_controller.pages.Main;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SetEstimatedWorkHoursActivityAction extends AbstractAction {

    Main main;
    JTextField activityName;
    Activity activity;
    JTextField estimatedWorkHours;
    Project previousProject;

    public SetEstimatedWorkHoursActivityAction(String name, 
    JTextField activityName, 
    JTextField estimatedWorkHours,
    Activity activity, 
    Project previousProject,
    Main main) {
        putValue(NAME, name);
        this.main = main;
        this.activityName = activityName;
        this.activity = activity;
        this.estimatedWorkHours = estimatedWorkHours;
        this.previousProject = previousProject;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // ERROR BOOLEAN
        Boolean hasError = false;

        Double estimatedWorkHoursDouble = Double.parseDouble(estimatedWorkHours.getText());

        if (!(activity.getEstimatedWorkHours() == estimatedWorkHoursDouble)) {
            try {
                main.app.setEstimatedWorkHoursForActivity(estimatedWorkHoursDouble, activityName.getText(), activity.getParentProject().getProjectNumber());
            } catch (OperationNotAllowedException error) {
                ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
                errorWindow.showMessage();
                hasError = true;
            }
        }
    
        if (!hasError) {
            SuccessWindow successWindow = new SuccessWindow("Changes successfully set.");
            successWindow.showMessage();
        }
        main.viewActivity(activity, previousProject);
    }
}