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

        // PROJECT LEADER CHANGE
        if (!project.hasProjectLeader() && !projectLeader.getText().equals("") || project.hasProjectLeader() && !projectLeader.getText().equals(project.getProjectLeader().getInitials())) {
            try {
                main.app.assignProjectLeader(project.getProjectNumber(), projectLeader.getText());
            } catch (OperationNotAllowedException error) {
                ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
                errorWindow.showMessage();
                hasError = true;
            }
        }

        // PROJECT NAME CHANGE
        project.setName(projectName.getText());
    
        if (!hasError) {
            SuccessWindow successWindow = new SuccessWindow("Changes successfully set.");
            successWindow.showMessage();
        }
        main.viewProject(project, previousPage);
    }
}