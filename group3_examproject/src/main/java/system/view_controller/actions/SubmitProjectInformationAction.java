package system.view_controller.actions;
import javax.swing.*;

import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;
import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.messageWindows.SuccessWindow;
import system.view_controller.pages.Main;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SubmitProjectInformationAction extends AbstractAction {

    Main main;
    JTextField projectName;
    JTextField projectLeader;
    Project project;
    JTextField estimatedWorkHours;
    String previousPage;

    public SubmitProjectInformationAction(String name, 
    JTextField projectName, 
    JTextField projectLeader, 
    Project project, 
    String previousPage,
    Main main) {
        putValue(NAME, name);
        this.main = main;
        this.projectName = projectName;
        this.projectLeader = projectLeader;
        this.project = project;
        this.previousPage = previousPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // ERROR BOOLEAN
        Boolean hasError = false;
        Boolean hasChanged = false;

        // PROJECT LEADER CHANGE
        if (!projectLeader.getText().trim().equals("")) {
            try {
                main.app.assignProjectLeader(project.getProjectNumber(), projectLeader.getText().trim());
                hasChanged = true;
            } catch (OperationNotAllowedException error) {
                ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
                errorWindow.showMessage();
                hasError = true;
            }
        }
        if (!projectName.getText().trim().equals("")) {
            // PROJECT NAME CHANGE
            project.setName(projectName.getText().trim());
            hasChanged = true;
        }
    
        if (!hasError && hasChanged) {
            SuccessWindow successWindow = new SuccessWindow("Changes successfully set.");
            successWindow.showMessage();
            main.viewProject(project, previousPage);
        } else if (!hasError && !hasChanged) {
            ErrorWindow errorWindow = new ErrorWindow("No changes have been made.");
            errorWindow.showMessage();
        }
    }
}