package system.view_controller.actions;
import javax.swing.*;

import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;
import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.messageWindows.SuccessWindow;
import system.view_controller.pages.Main;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SetProjectTimeHorizonAction extends AbstractAction {

    Main main;
    JTextField projectName;
    JTextField projectLeader;
    Project project;
    JTextField projectStartYear;
    JTextField projectStartWeek;
    JTextField projectEndYear;
    JTextField projectEndWeek;
    JTextField estimatedWorkHours;
    String previousPage;

    public SetProjectTimeHorizonAction(String name, 
    JTextField projectName, 
    JTextField projectLeader, 
    JTextField projectStartYear, 
    JTextField projectStartWeek, 
    JTextField projectEndYear,
    JTextField projectEndWeek,
    JTextField estimatedWorkHours,
    Project project, 
    String previousPage,
    Main main) {
        putValue(NAME, name);
        this.main = main;
        this.projectName = projectName;
        this.projectLeader = projectLeader;
        this.project = project;
        this.projectStartYear = projectStartYear;
        this.projectStartWeek = projectStartWeek;
        this.projectEndYear = projectEndYear;
        this.projectEndWeek = projectEndWeek;
        this.estimatedWorkHours = estimatedWorkHours;
        this.previousPage = previousPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // ERROR BOOLEAN
        Boolean hasError = false;

        try {
            int startYear = Integer.parseInt(projectStartYear.getText());
            int startWeek = Integer.parseInt(projectStartWeek.getText());
            int endYear = Integer.parseInt(projectEndYear.getText());
            int endWeek = Integer.parseInt(projectEndWeek.getText());
            try {
                main.app.setTimeHorizonOfProject(startYear, startWeek, endYear, endWeek, project.getProjectNumber());
            } catch (NumberFormatException error) {
                ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
                errorWindow.showMessage();
                hasError = true;
            } catch (OperationNotAllowedException error) {
                ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
                errorWindow.showMessage();
                hasError = true;
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
        main.viewProject(project, previousPage);
    }
}