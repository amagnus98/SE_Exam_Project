package system.view_controller.actions;
import javax.swing.*;
import system.view_controller.pages.Main;
import java.awt.event.ActionEvent;

import system.model.domain.Activity;
import system.model.domain.Project;

// Activity for viewing activity // Responsible - Asbjørn Magnussen (s183546)
public class ActivityButtonAction extends AbstractAction {

    JTextField textField;
    Main main;
    Activity activity;
    Project previousProject;

    public ActivityButtonAction(String activityName, Activity activity, Project previousProject, Main main) {
        if (activityName.equals("")) {
            putValue(NAME, "Unnamed ");
        } else {
            putValue(NAME, activityName);
        }
        this.main = main;
        this.activity = activity;
        this.previousProject = previousProject;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        main.viewActivity(activity, previousProject);
    }
}