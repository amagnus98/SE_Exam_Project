package system.view_controller.actions;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JTextField;

import system.model.domain.OperationNotAllowedException;
import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.messageWindows.SuccessWindow;
import system.view_controller.pages.*;

public class registerTimeAction extends AbstractAction {

    JTextField textField;
    Main main;
    String projectNumber;
    String activityName;
    JTextField hours;
    JTextField day;
    JTextField week;
    JTextField year;

    public registerTimeAction(String projectNumber, String activityName, JTextField hours, JTextField day, JTextField week, JTextField year, Main main) {
        putValue(NAME, "Register Hours");
        this.main = main;
        this.projectNumber = projectNumber;
        this.activityName = activityName;
        this.hours = hours;
        this.day = day;
        this.week = week;
        this.year = year;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        boolean hasError = false;

        double hours  = Double.parseDouble(this.hours.getText());
        int day = Integer.parseInt(this.day.getText());
        int week = Integer.parseInt(this.week.getText());
        int year = Integer.parseInt(this.year.getText());

        try {
            if (main.app.getProject(projectNumber).isNonWorkActivityProject()) {
                main.app.registerHoursToNonWorkActivity(hours, day, week, year, activityName);
                System.out.print(projectNumber);
            } else {
                main.app.registerHoursToActivity(hours, day, week, year, projectNumber, activityName);
            }
        } catch (OperationNotAllowedException error) {
            ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
            errorWindow.showMessage();
            hasError = true;
        }

        if (!hasError) {
            SuccessWindow successWindow = new SuccessWindow("Time successfully registered.");
            successWindow.showMessage();
            main.changeScreen("Time Registration");
        }
    }
}