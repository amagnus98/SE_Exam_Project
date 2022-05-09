package system.view_controller.actions;
import javax.swing.*;
import system.model.domain.OperationNotAllowedException;
import system.view_controller.pages.Main;
import java.awt.event.ActionEvent;

// Log Out of the system action // Responsible - Mads Ringsted (s204144)
public class LogOutAction extends AbstractAction {

    JTextField textField;
    Main main;

    public LogOutAction(String name, Main main) {
        putValue(NAME, name);
        this.textField = textField;
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        main.app.logOut();
        main.changeScreen("Log In");
    }
}