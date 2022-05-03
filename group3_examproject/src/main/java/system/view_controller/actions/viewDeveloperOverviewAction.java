package system.view_controller.actions;
import javax.swing.*;

import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.pages.Main;
import java.awt.event.ActionEvent;
import system.model.domain.Project;

public class ViewDeveloperOverviewAction extends AbstractAction {

    JTextField textField;
    Main main;
    JTextField weekTextField;
    JTextField yearTextField;

    public ViewDeveloperOverviewAction(JTextField weekTextField, JTextField yearTextField, Main main) {
        putValue(NAME, "Get Overview");
        this.main = main;
        this.weekTextField = weekTextField;
        this.yearTextField = yearTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            int week = Integer.parseInt(weekTextField.getText().trim());
            int year = Integer.parseInt(yearTextField.getText().trim());
            if (week >= 1 && week <= 52 && year >= 1) {
                main.viewDeveloperOverview(week, year);
            } else {
                ErrorWindow errorWindow = new ErrorWindow("The current week or year is not valid.");
                errorWindow.showMessage();
            }
           } catch (NumberFormatException error) {
            ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
            errorWindow.showMessage();
           }
    }
}