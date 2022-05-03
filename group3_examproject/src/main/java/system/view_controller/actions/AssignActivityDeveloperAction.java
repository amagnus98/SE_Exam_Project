package system.view_controller.actions;
import javax.swing.*;

import system.model.domain.Activity;
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;
import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.messageWindows.SuccessWindow;
import system.view_controller.pages.Main;
import java.awt.event.ActionEvent;
import java.util.*;

public class AssignActivityDeveloperAction extends AbstractAction {

    JTextField textField;
    Main main;
    Activity activity;
    Project previousProject;

    public AssignActivityDeveloperAction(String name, Project previousProject, JTextField textField, Activity activity, Main main) {
        putValue(NAME, name);
        this.textField = textField;
        this.main = main;
        this.activity = activity;
        this.previousProject = previousProject;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Boolean hasError = false;

        String initials = textField.getText().trim();
        try {
            main.app.addDeveloperToActivity(initials, activity.getName(), previousProject.getProjectNumber());
        } catch (OperationNotAllowedException error) {
            ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
            errorWindow.showMessage();
            hasError = true;
        }

        if (!hasError) {
            SuccessWindow errorWindow = new SuccessWindow("Developer successfully assigned.");
            errorWindow.showMessage();
        }
        main.viewActivity(activity, previousProject);
    }
}