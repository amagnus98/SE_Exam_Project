package system.view_controller.actions;
import javax.swing.*;

import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.pages.Main;
import java.awt.event.ActionEvent;
import system.model.domain.Project;
import java.util.*;
import system.model.domain.Developer;
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Activity;

public class ViewDeveloperOverviewAction extends AbstractAction {

    JTextField textField;
    Main main;
    JTextField startWeekTextField;
    JTextField startYearTextField;
    JTextField endWeekTextField;
    JTextField endYearTextField;

    public ViewDeveloperOverviewAction(JTextField startWeekTextField, JTextField startYearTextField, JTextField endWeekTextField, JTextField endYearTextField, Main main) {
        putValue(NAME, "Get Overview");
        this.main = main;
        this.startWeekTextField = startWeekTextField;
        this.startYearTextField = startYearTextField;
        this.endWeekTextField = endWeekTextField;
        this.endYearTextField = endYearTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!startWeekTextField.getText().trim().equals("") && !startYearTextField.getText().trim().equals("") && !endWeekTextField.getText().trim().equals("") && !endYearTextField.getText().trim().equals("")) {
            try {
                int startWeek = Integer.parseInt(startWeekTextField.getText().trim());
                int startYear = Integer.parseInt(startYearTextField.getText().trim());
                int endWeek = Integer.parseInt(endWeekTextField.getText().trim());
                int endYear = Integer.parseInt(endYearTextField.getText().trim());

                try {
                    HashMap<Developer, HashMap<Project, ArrayList<Activity>>> developerActivitiesInPeriod = main.app.getDeveloperActivitiesInPeriod(startWeek, startYear, endWeek, endYear);
                    main.viewDeveloperOverview(startWeek, startYear, endWeek, endYear, developerActivitiesInPeriod);
                } catch (OperationNotAllowedException error) {
                    ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
                    errorWindow.showMessage();
                }
            } catch (NumberFormatException error) {
                ErrorWindow errorWindow = new ErrorWindow("The start and end time must be written as integers!");
                errorWindow.showMessage();
            }
        } else {
            ErrorWindow errorWindow = new ErrorWindow("Please insert all values.");
            errorWindow.showMessage();
        }
    }
}