package system.view_controller.actions;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JTextField;

import system.view_controller.pages.*;


// Go to register time page action // Responsible - Mads Ringsted (s204144)
public class RegisterTimeOnActivityAction extends AbstractAction {

    JTextField textField;
    Main main;
    String projectNumber;
    String activityName;

    public RegisterTimeOnActivityAction(String projectNumber, String activityName, Main main) {
        putValue(NAME, activityName);
        this.main = main;
        this.projectNumber = projectNumber;
        this.activityName = activityName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        main.registerTimeOnActivity(this.activityName, this.projectNumber);
    }
}