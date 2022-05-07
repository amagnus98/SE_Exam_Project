package system.view_controller.actions;
import javax.swing.*;

import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;
import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.messageWindows.SuccessWindow;
import system.view_controller.pages.Main;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SetEstimatedWorkHoursProjectAction extends AbstractAction {

    Main main;
    JTextField projectName;
    JTextField projectLeader;
    JTextField estimatedWorkHours;
    Project project;
    String previousPage;

    public SetEstimatedWorkHoursProjectAction(String name, 
    JTextField projectName, 
    JTextField projectLeader, 
    JTextField estimatedWorkHours,
    Project project, 
    String previousPage,
    Main main) {
        putValue(NAME, name);
        this.main = main;
        this.projectName = projectName;
        this.projectLeader = projectLeader;
        this.project = project;
        this.previousPage = previousPage;
        this.estimatedWorkHours = estimatedWorkHours;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // ERROR BOOLEAN
        Boolean hasError = false;

        if (!estimatedWorkHours.getText().trim().equals("")) {
            try {
                Double estimatedWorkHoursDouble = Double.parseDouble(estimatedWorkHours.getText().trim());

                if (!(project.getEstimatedWorkHours() == estimatedWorkHoursDouble)) {
                    try {
                        main.app.setEstimatedWorkHoursForProject(estimatedWorkHoursDouble, project.getProjectNumber());
                    } catch (OperationNotAllowedException error) {
                        ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
                        errorWindow.showMessage();
                        hasError = true;
                    } 
                }
            } catch (NumberFormatException error){
                ErrorWindow errorWindow = new ErrorWindow("This field only accepts integers or floats!");
                errorWindow.showMessage();
                hasError = true;
            }
        
            if (!hasError) {
                SuccessWindow successWindow = new SuccessWindow("Changes successfully set.");
                successWindow.showMessage();
                main.viewProject(project, previousPage);
            }
        } else {
            ErrorWindow errorWindow = new ErrorWindow("No value has been inserted!");
            errorWindow.showMessage();
        }
    }
}