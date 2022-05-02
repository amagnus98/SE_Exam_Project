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

public class CreateNewProjectActivityAction extends AbstractAction {

    JTextField textField;
    Main main;
    Project project;
    String previousPage;

    public CreateNewProjectActivityAction(String name, String previousPage, JTextField textField, Project project, Main main) {
        putValue(NAME, name);
        this.textField = textField;
        this.main = main;
        this.project = project;
        this.previousPage = previousPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Boolean hasError = false;

        String activityName = textField.getText();
        try {
            if (project.isNonWorkActivityProject()) {
                project.addToActivityList(new Activity(activityName));
            } else {
                main.app.addActivityToProject(activityName, project.getProjectNumber());
            }
        } catch (OperationNotAllowedException error) {
            ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
            errorWindow.showMessage();
            hasError = true;
        }

        if (!hasError) {
            SuccessWindow errorWindow = new SuccessWindow("Activity successfully created.");
            errorWindow.showMessage();
            if (project.isNonWorkActivityProject()) {
                main.viewNonWorkActivityProject(project, previousPage);
            } else {
                main.viewProject(project, previousPage);
            }
        }

        

    }
}