package system.view_controller.actions;
import javax.swing.*;
import system.model.domain.OperationNotAllowedException;
import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.pages.Main;
import java.awt.event.ActionEvent;

public class LogInAction extends AbstractAction {

    JTextField textField;
    Main main;

    public LogInAction(String name, JTextField textField, Main main) {
        putValue(NAME, name);
        this.textField = textField;
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String initials = this.textField.getText();
        try {
            this.main.app.logIn(initials);
            this.main.changeScreen("Navigator");
        } catch (OperationNotAllowedException error) {
            ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
            errorWindow.showMessage();
        }

    }
}